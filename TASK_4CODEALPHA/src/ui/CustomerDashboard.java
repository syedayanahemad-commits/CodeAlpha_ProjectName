package ui;

import java.awt.*;
import javax.swing.*;

public class CustomerDashboard extends JFrame {

    public CustomerDashboard() {

        setTitle("Hotel Reservation System");

        setSize(1400,800);

        setDefaultCloseOperation(
                EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
// Top Search Bar
JPanel top = new JPanel(
        new FlowLayout(
                FlowLayout.CENTER,
                20,
                15));

top.setBackground(Color.WHITE);
JLabel logo =
        new JLabel("🏨 HotelHub");

logo.setFont(
        new Font("Segoe UI",
                Font.BOLD,
                26));

logo.setForeground(
        new Color(0,180,80));

top.add(logo);

JLabel locationLabel =
        new JLabel("📍 Location");

locationLabel.setFont(
        new Font("Segoe UI",
                Font.BOLD,
                15));

JTextField search =
        new JTextField();

search.setPreferredSize(
        new Dimension(600,45));

search.setFont(
        new Font("Segoe UI",
                Font.PLAIN,
                16));
                search.setText(
        "Search hotels, cities...");

JButton searchBtn =
        new JButton("Search");

searchBtn.setBackground(
        new Color(0,180,80));

searchBtn.setForeground(
        Color.WHITE);

searchBtn.setFocusPainted(false);

searchBtn.setPreferredSize(
        new Dimension(120,40));

top.add(locationLabel);
top.add(search);
top.add(searchBtn);

add(top, BorderLayout.NORTH);
        // Filters
        JPanel filters =
                new JPanel();
                filters.setBackground(Color.WHITE);

        filters.setPreferredSize(
                new Dimension(300,700));
                filters.setBackground(Color.WHITE);

        filters.setLayout(
                new BoxLayout(
                        filters,
                        BoxLayout.Y_AXIS));

        filters.add(
                new JLabel("Filters"));

        JCheckBox standard =
        new JCheckBox("Standard");

JCheckBox deluxe =
        new JCheckBox("Deluxe");

JCheckBox suite =
        new JCheckBox("Suite");

standard.setFont(
        new Font("Segoe UI",
                Font.BOLD,
                18));

deluxe.setFont(
        new Font("Segoe UI",
                Font.BOLD,
                18));

suite.setFont(
        new Font("Segoe UI",
                Font.BOLD,
                18));

filters.add(standard);
filters.add(Box.createVerticalStrut(10));

filters.add(deluxe);
filters.add(Box.createVerticalStrut(10));

filters.add(suite);
        add(filters,
                BorderLayout.WEST);

        // Hotel List
        JPanel hotels =
                new JPanel();
                hotels.setBackground(
        new Color(245,245,245));

        hotels.setLayout(
                new BoxLayout(
                        hotels,
                        BoxLayout.Y_AXIS));

        hotels.add(new RoomCard(
                "images/room1.jpg",
                "Hotel Paradise",
                "Mumbai",
                "4.6",
                "₹1500"));

        hotels.add(Box.createVerticalStrut(25));

        hotels.add(new RoomCard(
                "images/room2.jpg",
                "Royal Residency",
                "Pune",
                "4.8",
                "₹2500"));

        hotels.add(Box.createVerticalStrut(25));

        hotels.add(new RoomCard(
                "images/room3.jpg",
                "Grand Palace",
                "Nagpur",
                "4.7",
                "₹3500"));
                hotels.add(Box.createVerticalStrut(25));

hotels.add(new RoomCard(
        "images/room4.jpg",
        "Ocean View Resort",
        "Goa",
        "4.9",
        "₹5000"));

        JScrollPane scroll =
        new JScrollPane(hotels);

scroll.getVerticalScrollBar()
      .setUnitIncrement(18);

scroll.setBorder(null);

scroll.getViewport()
      .setBackground(
              new Color(245,245,245));

add(scroll,
        BorderLayout.CENTER);

        scroll.getVerticalScrollBar()
      .setUnitIncrement(24);

        setVisible(true);
    }
}