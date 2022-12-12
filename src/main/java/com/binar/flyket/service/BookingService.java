package com.binar.flyket.service;

import com.binar.flyket.dto.model.BookingDetailDTO;
import com.binar.flyket.dto.request.BookingRequest;
import com.binar.flyket.dto.request.PaymentRequest;
import com.binar.flyket.dto.response.BookingResponse;
import com.binar.flyket.dto.response.PaymentResponse;

public interface BookingService {

    BookingDetailDTO getBookingDetail(String bookingId);

    BookingResponse addBooking(String userId, BookingRequest request);

    Boolean validateBooking(String userId, String bookingId);

    PaymentResponse setPaymentMethod(PaymentRequest request);
}
