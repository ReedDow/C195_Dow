package test;

import controller.Appointments;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentsTest {

    @Test
    void testBusinessHoursTrue() {
        var appointments = new Appointments();
        boolean hoursCheck = appointments.checkBusinessHours(LocalDateTime.parse("2022-06-30T10:34:50.63"), LocalDateTime.parse("2022-06-30T11:34:50.63"), LocalDate.parse("2022-06-30"), LocalDate.parse("2022-06-30"));
        assertTrue(hoursCheck);
        System.out.println("Testing that appointment within business hours returns True");

    }

    @Test
    void testBusinessHoursFalse() {
        var appointments = new Appointments();
        boolean hoursCheck = appointments.checkBusinessHours(LocalDateTime.parse("2022-06-30T22:34:50.63"), LocalDateTime.parse("2022-06-30T23:34:50.63"), LocalDate.parse("2022-06-30"), LocalDate.parse("2022-06-30"));
        assertFalse(hoursCheck);
        System.out.println("Testing that appointment outside of business hours returns False");

    }

}