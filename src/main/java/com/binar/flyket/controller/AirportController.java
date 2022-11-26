package com.binar.flyket.controller;

import com.binar.flyket.dto.model.AirportDTO;
import com.binar.flyket.dto.request.InputAirportRequest;
import com.binar.flyket.dto.response.Response;
import com.binar.flyket.dto.response.ResponseError;
import com.binar.flyket.exception.FlyketException;
import com.binar.flyket.service.AirportService;
import com.binar.flyket.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public ResponseEntity<?> getAirports() {
        return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                Constants.SUCCESS_MSG, airportService.getAirports()));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAirport(@RequestBody @Valid InputAirportRequest inputAirportRequest) {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    Constants.SUCCESS_MSG, airportService.addAirport(inputAirportRequest)));
        } catch (FlyketException.DuplicateEntityException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(),
                    new Date(),e.getMessage()), e.getStatusCode());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAirportById(@RequestParam("code") String code) {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    Constants.SUCCESS_MSG, airportService.deleteAirportById(code)));
        } catch (FlyketException.EntityNotFoundException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(),
                    new Date(),e.getMessage()), e.getStatusCode());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> getAirportById(@RequestParam("code") String IATACode) {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    Constants.SUCCESS_MSG, airportService.getAirportById(IATACode)));
        } catch (FlyketException.EntityNotFoundException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(),
                    new Date(),e.getMessage()), e.getStatusCode());
        }
    }

}
