package com.example.booking.controllers;

import java.time.LocalDate;
import com.example.booking.services.AppointmentService;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import com.example.booking.models.Appointment;
import com.example.booking.models.Appointment.AppointmentStatus;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/appointments")
@Api(tags = "Appointments")
class AppointmentController {

    private final AppointmentService appointmentService;

    AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/")
    @ApiOperation(value = "List existing appointments; sorted by date, descending")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden")})
    Page<Appointment> listAppointments(
            @ApiParam(value = "Filter appointments by date, minimum date")
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate minDate,
            @ApiParam(value = "Filter appointments by date, maximum date")
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate maxDate,
            @ApiParam(value = "List only the appointments of a specific client")
            @RequestParam(required = false)
            Long clientId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return appointmentService.listAppointments(minDate, maxDate, clientId, page, pageSize);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find an appointment by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    Appointment getAppointment(@PathVariable @ApiParam(value = "Appointment ID") Long id) {
        return appointmentService.getAppointment(id);
    }

    @PostMapping("/")
    @ApiOperation(value = "Add a new appointment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden")})
    @ApiImplicitParams({ @ApiImplicitParam(
            name = "newAppointment", value = "New appointment", dataType = "AppointmentPost")})
    Appointment createAppointment(@RequestBody @Valid Appointment newAppointment) {
        return appointmentService.createAppointment(newAppointment);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing appointment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @ApiImplicitParams({ @ApiImplicitParam(
            name = "actualAppointment", value = "Actual appointment", dataType = "AppointmentPut")})
    Appointment updateAppointment(
            @PathVariable @ApiParam(value = "Appointment ID") Long id,
            @RequestBody @Valid Appointment actualAppointment) {
        return appointmentService.updateAppointment(id, actualAppointment);
    }

    @PutMapping("/{id}/reset")
    @ApiOperation(value = "Set appointment status to SCHEDULED")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    Appointment resetAppointment(@PathVariable @ApiParam(value = "Appointment ID") Long id) {
        return appointmentService.setAppointmentStatus(id, AppointmentStatus.SCHEDULED);
    }

    @PutMapping("/{id}/attend")
    @ApiOperation(value = "Set appointment status to ATTENDED")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    Appointment attendAppointment(@PathVariable @ApiParam(value = "Appointment ID") Long id) {
        return appointmentService.setAppointmentStatus(id, AppointmentStatus.ATTENDED);
    }

    @PutMapping("/{id}/notAttend")
    @ApiOperation(value = "Set appointment status to NOT_ATTENDED")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    Appointment notAttendAppointment(@PathVariable @ApiParam(value = "Appointment ID") Long id) {
        return appointmentService.setAppointmentStatus(id, AppointmentStatus.NOT_ATTENDED);
    }

    @PutMapping("/{id}/cancel")
    @ApiOperation(value = "Set appointment status to CANCELLED")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    Appointment cancelAppointment(@PathVariable @ApiParam(value = "Appointment ID") Long id) {
        return appointmentService.setAppointmentStatus(id, AppointmentStatus.CANCELLED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an appointment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    void deleteAppointment(@PathVariable @ApiParam(value = "Appointment ID") Long id) {
        appointmentService.deleteAppointment(id);
    }
}
