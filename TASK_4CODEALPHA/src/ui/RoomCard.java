package ui;

import java.awt.*;
import javax.swing.*;
import model.Room;
import model.Booking;
import service.BookingService;

public class RoomCard extends JPanel {
    private static BookingService bookingService =
        new BookingService();

    public RoomCard(
            String imagePath,
            String hotelName,
            String location,
            String rating,
            String price) {

        setLayout(new BorderLayout());
        setPreferredSize(
        new Dimension(1050,320));
        setBackground(Color.WHITE);
        setBorder(
        BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(220,220,220),1),
                BorderFactory.createEmptyBorder(
                        10,10,10,10)
        ));
        setBorder(BorderFactory.createLineBorder(
                new Color(220,220,220)));
                setBorder(
    BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(
            new Color(220,220,220),1),
        BorderFactory.createEmptyBorder(
            10,10,10,10)
    )
);

        // Image
        
       ImageIcon icon = new ImageIcon(imagePath);

Image img = icon.getImage().getScaledInstance(
        380,
        220,
        Image.SCALE_SMOOTH);

JLabel imageLabel =
        new JLabel(new ImageIcon(img));

imageLabel.setBorder(
        BorderFactory.createEmptyBorder(
                10,10,10,10));

add(imageLabel, BorderLayout.WEST);

        // Info Panel
        JPanel info = new JPanel();
        info.setLayout(
                new BoxLayout(info,
                        BoxLayout.Y_AXIS));

        info.setBackground(Color.WHITE);
        info.setBorder(
        BorderFactory.createEmptyBorder(
                20,30,20,20));


        JLabel name =
                new JLabel(hotelName);

        name.setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        22));

        JLabel loc =
                new JLabel(location);

        JLabel rate =
        new JLabel("⭐ " + rating);
        rate.setFont(
        new Font(
                "Segoe UI Symbol",
                Font.BOLD,
                14));

rate.setOpaque(true);

rate.setBackground(
        new Color(34,139,34));

rate.setForeground(Color.WHITE);

rate.setFont(
        new Font("Segoe UI",
                Font.BOLD,
                14));

        JLabel amenities =
                new JLabel(
                        "Free WiFi • AC • TV • Geyser");

        JLabel priceLabel =
                new JLabel(price);
                JLabel discount =
        new JLabel("25% OFF");

discount.setForeground(
        new Color(0,150,0));

discount.setFont(
        new Font("Segoe UI",
                Font.BOLD,
                16));

        priceLabel.setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        32));

        JButton details =
                new JButton("View Details");

        JButton book =
                new JButton("Book Now");
                details.setFocusPainted(false);
book.setFocusPainted(false);

details.setBackground(Color.WHITE);

book.setBackground(
        new Color(0,180,80));

book.setForeground(Color.WHITE);

        JPanel btnPanel =
                new JPanel();

        btnPanel.setBackground(Color.WHITE);

        btnPanel.add(details);
        btnPanel.add(book);
        book.addActionListener(e -> {

    String customer =
            JOptionPane.showInputDialog(
                    this,
                    "Enter Customer Name");

    if(customer == null ||
       customer.trim().isEmpty()) {

        return;
    }

    String daysStr =
            JOptionPane.showInputDialog(
                    this,
                    "Number of Days");

    if(daysStr == null) {
        return;
    }

    int days;

    try {

        days = Integer.parseInt(daysStr);

    } catch(Exception ex) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid Days");

        return;
    }

    int roomId = 101;

    if(hotelName.equals("Royal Residency"))
        roomId = 102;

    if(hotelName.equals("Grand Palace"))
        roomId = 103;

    if(hotelName.equals("Ocean View Resort"))
        roomId = 104;

    Booking booking =
            bookingService.bookRoom(
                    customer,
                    roomId,
                    days);

    if(booking != null) {

        JOptionPane.showMessageDialog(
                this,
                "Payment Successful\n\n"
                + "Booking ID: "
                + booking.bookingId
                + "\nCustomer: "
                + booking.customerName
                + "\nTotal: ₹"
                + booking.totalAmount);

    } else {

        JOptionPane.showMessageDialog(
                this,
                "Room Not Available");
    }
});

        info.add(Box.createVerticalStrut(10));
        info.add(name);
        info.add(Box.createVerticalStrut(5));
        info.add(loc);
        info.add(Box.createVerticalStrut(5));
        info.add(rate);
        info.add(Box.createVerticalStrut(5));
        info.add(amenities);
        info.add(Box.createVerticalStrut(10));
        info.add(priceLabel);
        info.add(discount);
        info.add(Box.createVerticalStrut(10));
        info.add(btnPanel);

        add(info, BorderLayout.CENTER);
    }
}