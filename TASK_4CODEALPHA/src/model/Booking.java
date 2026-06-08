package model;

public class Booking {
    public int bookingId;
    public String customerName;
    public Room room;
    public int days;
    public double totalAmount;

    public Booking(int id, String name, Room room, int days) {
        this.bookingId = id;
        this.customerName = name;
        this.room = room;
        this.days = days;
        this.totalAmount = room.price * days;
    }
}