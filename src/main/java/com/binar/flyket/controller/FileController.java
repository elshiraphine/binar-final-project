package com.binar.flyket.controller;

import com.binar.flyket.dto.model.FileDB;
import com.binar.flyket.dto.request.InvoiceRequest;
import com.binar.flyket.dto.response.ResponseError;
import com.binar.flyket.exception.FlyketException;
import com.binar.flyket.service.FileService;
import com.google.zxing.WriterException;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;

@Tag(name = "File")
@RestController
@RequestMapping("/api/file")
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(value = "/download/invoice")
    public ResponseEntity<?> downloadInvoice(@RequestBody InvoiceRequest request) {
        try {
            FileDB fileDB = fileService.getInvoice(request);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION)
                    .body(new ByteArrayResource(fileDB.getData()));
        } catch (FlyketException.EntityNotFoundException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()), e.getStatusCode());
        } catch (JRException | IOException | WriterException e) {
            return new ResponseEntity<>(new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}