package service;

import java.util.*;
import model.Booking;
import model.Room;

public class BookingService {

    private List<Room> rooms = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();
    private int bookingId = 1;

    public BookingService() {
        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Deluxe", 2500));
        rooms.add(new Room(103, "Suite", 4000));
        rooms.add(new Room(104, "Luxury", 5000));
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Booking bookRoom(String customer, int roomId, int days) {

        for (Room r : rooms) {
            if (r.roomId == roomId && r.isAvailable) {

                double total = r.price * days;

                service.PaymentService.pay(total);

                r.isAvailable = false;

                 Booking b = new Booking(
        bookingId++,
        customer,
        r,
        days);

bookings.add(b);

FileManager.saveBooking(b);

return b;
            }
        }
        return null;
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}