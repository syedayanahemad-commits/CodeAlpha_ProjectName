import java.awt.*;
import javax.swing.*;

public class ModernChatbotGUI extends JFrame {

    private JPanel messagesPanel;
    private JTextField inputField;
    private JButton sendButton;
    private JScrollPane scrollPane;

    public ModernChatbotGUI() {

        setTitle("CodeAlpha AI Assistant");
        setSize(1400, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // SIDEBAR

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setBackground(new Color(20,20,20));
        sidebar.setLayout(new BoxLayout(
                sidebar,
                BoxLayout.Y_AXIS));

        JButton newChat =
                new JButton("+ New Chat");

        JButton javaBtn =
                new JButton("What is Java?");

        JButton aiBtn =
                new JButton("What is AI?");

        JButton sqlBtn =
                new JButton("Explain SQL");
                JButton[] buttons = {
        newChat,
        javaBtn,
        aiBtn,
        sqlBtn
};

for(JButton btn : buttons){

    btn.setMaximumSize(
            new Dimension(180,40));

    btn.setBackground(
            new Color(45,45,45));

    btn.setForeground(
            Color.WHITE);

    btn.setFocusPainted(false);

    btn.setBorderPainted(false);
}

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(newChat);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(javaBtn);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(aiBtn);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(sqlBtn);

        add(sidebar, BorderLayout.WEST);

        // MESSAGE AREA

        messagesPanel = new JPanel();
        messagesPanel.setLayout(
                new BoxLayout(
                        messagesPanel,
                        BoxLayout.Y_AXIS));

        messagesPanel.setBackground(
                new Color(24,24,24));

                messagesPanel.add(
    new MessageBubble(
        "Hello! I am your AI Assistant. Ask me anything about Java, programming, technology, history, or general knowledge.",
        false));
        scrollPane =
                new JScrollPane(messagesPanel);
                scrollPane.getVerticalScrollBar()
          .setUnitIncrement(25);

scrollPane.setWheelScrollingEnabled(true);
              

        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);

        // INPUT AREA

        JPanel bottomPanel =
                new JPanel(new BorderLayout());
                bottomPanel.setBackground(
        new Color(32,32,32));

        inputField =
        new JTextField();

inputField.setBackground(
        new Color(40,40,40));

inputField.setForeground(
        Color.WHITE);

inputField.setCaretColor(
        Color.WHITE);

inputField.setBorder(
        BorderFactory.createEmptyBorder(
                12,15,12,15));

        inputField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16));

        sendButton =
        new JButton("➤");

sendButton.setBackground(
        new Color(88,101,242));

sendButton.setForeground(
        Color.WHITE);

sendButton.setFocusPainted(false);

        bottomPanel.add(
                inputField,
                BorderLayout.CENTER);

        bottomPanel.add(
                sendButton,
                BorderLayout.EAST);

        add(bottomPanel,
                BorderLayout.SOUTH);

        // EVENTS

        sendButton.addActionListener(
                e -> sendMessage());

        inputField.addActionListener(
                e -> sendMessage());

        javaBtn.addActionListener(
                e -> {
                    inputField.setText(
                            "What is Java?");
                    sendMessage();
                });

        aiBtn.addActionListener(
                e -> {
                    inputField.setText(
                            "What is Artificial Intelligence?");
                    sendMessage();
                });

        sqlBtn.addActionListener(
                e -> {
                    inputField.setText(
                            "Explain SQL");
                    sendMessage();
                });

       newChat.addActionListener(
        e -> {

            messagesPanel.removeAll();

            messagesPanel.add(
    new MessageBubble(
        "👋 Welcome to CodeAlpha AI Assistant!\n\nAsk me about Java, SQL, AI, Programming, History, Technology and General Knowledge.",
        false));

            messagesPanel.revalidate();

            messagesPanel.repaint();
        });
    }
    
    private void sendMessage() {

        String userMessage =
                inputField.getText().trim();

        if(userMessage.isEmpty())
            return;

        MessageBubble userBubble =
                new MessageBubble(
                        userMessage,
                        true);

        messagesPanel.add(userBubble);

        messagesPanel.revalidate();

        messagesPanel.repaint();

        inputField.setText("");

        SwingUtilities.invokeLater(() -> {

            MessageBubble thinking =
                    new MessageBubble(
                            "Thinking...",
                            false);

            messagesPanel.add(thinking);

            messagesPanel.revalidate();

            messagesPanel.repaint();
            new Thread(() -> {

   

                String tempResponse =
        GeminiService.askGemini(
                userMessage);

if(tempResponse.contains("busy")
        || tempResponse.contains("No response")
        || tempResponse.contains("Error")) {

    tempResponse =
            getFallbackResponse(
                    userMessage);
}

final String response = tempResponse;
    SwingUtilities.invokeLater(() -> {

        messagesPanel.remove(thinking);

        MessageBubble aiBubble =
                new MessageBubble(
                        response,
                        false);

        messagesPanel.add(aiBubble);

        messagesPanel.revalidate();

        messagesPanel.repaint();

    });

}).start();

    

            SwingUtilities.invokeLater(() -> {
    JScrollBar vertical =
            scrollPane.getVerticalScrollBar();

    vertical.setValue(
            vertical.getMaximum());
});

        });
    }
    private String getFallbackResponse(String question) {

    question = question.toLowerCase();

    if(question.contains("java")) {

        return "Java is an object-oriented programming language used for web, desktop, mobile and enterprise applications.";
    }

    if(question.contains("ai")
            || question.contains("artificial intelligence")) {

        return "Artificial Intelligence enables computers to perform tasks that normally require human intelligence.";
    }

    if(question.contains("sql")) {

        return "SQL stands for Structured Query Language and is used to manage and query databases.";
    }

    if(question.contains("processor")) {

        return "A processor (CPU) is the brain of a computer that executes instructions and performs calculations.";
    }

    return "I'm currently unable to contact the AI server. Please try again later.";
}
}