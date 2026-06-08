package service;

import java.io.*;
import model.Booking;

public class FileManager {

    public static void saveBooking(Booking b) {

        try {

            FileWriter fw =
                    new FileWriter(
                            "bookings.txt",
                            true);

            fw.write(
                    "Booking ID: "
                    + b.bookingId + "\n");

            fw.write(
                    "Customer: "
                    + b.customerName + "\n");

            fw.write(
                    "Room: "
                    + b.room.type + "\n");

            fw.write(
                    "Days: "
                    + b.days + "\n");

            fw.write(
                    "Total: ₹"
                    + b.totalAmount + "\n");

            fw.write(
                    "------------------\n");

            fw.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}