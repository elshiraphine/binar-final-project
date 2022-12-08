package com.binar.flyket.service;

import com.binar.flyket.dto.request.BookingRequest;
import com.binar.flyket.dto.response.BookingResponse;
import com.binar.flyket.exception.FlyketException;
import com.binar.flyket.model.AircraftDetail;
import com.binar.flyket.model.FlightSchedule;
import com.binar.flyket.model.user.User;
import com.binar.flyket.repository.*;
import com.binar.flyket.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final FlightScheduleRepository flightScheduleRepository;

    public BookingServiceImpl(FlightScheduleRepository flightScheduleRepository,
                              UserRepository userRepository,
                              SeatRepository seatRepository,
                              TicketRepository ticketRepository,
                              PaymentMethodRepository paymentMethodRepository) {
        this.flightScheduleRepository = flightScheduleRepository;
        this.userRepository = userRepository;
        this.seatRepository = seatRepository;
        this.ticketRepository = ticketRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public BookingResponse addBooking(String userId, BookingRequest request) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty())
            throw new FlyketException.EntityNotFoundException(HttpStatus.NOT_FOUND, "User with id " + Constants.NOT_FOUND_MSG);

        Optional<FlightSchedule> schedule = flightScheduleRepository.findById(request.getScheduleId());
        if(schedule.isEmpty())
            throw new FlyketException.EntityNotFoundException(HttpStatus.NOT_FOUND, "Flight Schedule with id " + Constants.NOT_FOUND_MSG);

        return null;
    }

    private void insertPassenger(BookingRequest request, FlightSchedule flightSchedule) {
        String uid = UUID.randomUUID().toString();
        AircraftDetail aircraftDetail = flightSchedule.getAircraftDetail();

    }

    private Boolean isSeatAvailable(FlightSchedule flightSchedule) {
return null;
    }


    @Override
    public Boolean validateBooking(String userId, String bookingId) {
        return null;
    }
}
