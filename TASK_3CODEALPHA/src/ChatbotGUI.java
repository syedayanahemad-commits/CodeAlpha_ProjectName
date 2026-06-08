import java.awt.*;
import java.time.LocalTime;
import javax.swing.*;

public class ChatbotGUI extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    private ChatbotEngine chatbot;

    public ChatbotGUI() {

        chatbot = new ChatbotEngine();

        setTitle("CodeAlpha AI Chatbot");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        chatArea = new JTextArea();
        chatArea.setBackground(new Color(25,25,25));
chatArea.setForeground(Color.WHITE);
chatArea.setCaretColor(Color.WHITE);
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 15));

        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        inputField.setBackground(new Color(40,40,40));
inputField.setForeground(Color.WHITE);
inputField.setCaretColor(Color.WHITE);

        sendButton = new JButton("➤");

sendButton.setBackground(new Color(88,101,242));
sendButton.setForeground(Color.WHITE);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        JPanel sidebar = new JPanel();
sidebar.setPreferredSize(new Dimension(220,0));
sidebar.setBackground(new Color(18,18,18));

sidebar.setLayout(new BoxLayout(
        sidebar,
        BoxLayout.Y_AXIS));

JButton newChat =
        new JButton("+ New Chat");

JButton javaPrompt =
        new JButton("What is Java?");

JButton aiPrompt =
        new JButton("What is AI?");

JButton sqlPrompt =
        new JButton("Explain SQL");

sidebar.add(Box.createVerticalStrut(20));
sidebar.add(newChat);
sidebar.add(Box.createVerticalStrut(20));
sidebar.add(javaPrompt);
sidebar.add(aiPrompt);
sidebar.add(sqlPrompt);

add(sidebar, BorderLayout.WEST);

javaPrompt.addActionListener(e -> {

    inputField.setText("What is Java?");
    sendMessage();
});

aiPrompt.addActionListener(e -> {

    inputField.setText(
            "What is Artificial Intelligence?");
    sendMessage();
});

sqlPrompt.addActionListener(e -> {

    inputField.setText(
            "Explain SQL");
    sendMessage();
});
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        chatArea.append("Bot: Hello! I am your AI Chatbot.\n\n");

        sendButton.addActionListener(e -> sendMessage());

        inputField.addActionListener(e -> sendMessage());
    }

    private void sendMessage() {

        String userMessage = inputField.getText().trim();

        if(userMessage.isEmpty()) {
            return;
        }

        String time = LocalTime.now().withNano(0).toString();

        chatArea.append("\n👤 You\n");
chatArea.append(userMessage + "\n\n");

        String response =
        GeminiService.askGemini(userMessage);

        chatArea.append("🤖 AI Assistant\n");
chatArea.append(response + "\n\n");
chatArea.setCaretPosition(
        chatArea.getDocument().getLength());

        inputField.setText("");
    }
}