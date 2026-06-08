package model;

public class Room {
    public int roomId;
    public String type;
    public double price;
    public boolean isAvailable;

    public Room(int roomId, String type, double price) {
        this.roomId = roomId;
        this.type = type;
        this.price = price;
        this.isAvailable = true;
    }
}