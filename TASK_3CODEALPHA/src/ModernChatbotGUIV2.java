import java.awt.*;
import javax.swing.*;

public class ModernChatbotGUIV2 extends JFrame {

    private JPanel chatPanel;
    private JScrollPane scrollPane;
    private JTextField inputField;
    private JButton sendButton;
    private JPanel sidebar;
    

    public ModernChatbotGUIV2() {

        setTitle("My AI Studio");
        setSize(1000, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🌑 MAIN BACKGROUND (ChatGPT dark feel)
        getContentPane().setBackground(new Color(10, 11, 13));

        initSidebar();
        initChatArea();
        initInputArea();

        setVisible(true);
    }

    // ================= SIDEBAR =================
    private void initSidebar() {

        sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(240, getHeight()));
        sidebar.setBackground(new Color(18, 19, 22));
        sidebar.setLayout(new BorderLayout());

        // TOP BRAND
        JPanel top = new JPanel();
        top.setBackground(new Color(18, 19, 22));
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.setBorder(BorderFactory.createEmptyBorder(20, 15, 10, 15));

        JLabel logo = new JLabel("🤖 My AI Studio");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel sub = new JLabel("Smart Assistant");
        sub.setForeground(new Color(150, 150, 150));
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        top.add(logo);
        top.add(Box.createVerticalStrut(5));
        top.add(sub);

        // MENU
        JPanel menu = new JPanel();
        menu.setBackground(new Color(18, 19, 22));
        menu.setLayout(new GridLayout(0, 1, 8, 8));
        menu.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        menu.add(sideBtn("🔍 Search"));
        menu.add(sideBtn("💬 Chats"));
        menu.add(sideBtn("📁 Projects"));
        menu.add(sideBtn("📚 Library"));

        // NEW CHAT BUTTON
        JButton newChat = new JButton("+ New Chat");
        newChat.setFocusPainted(false);
        newChat.setBackground(new Color(0, 132, 255));
        newChat.setForeground(Color.WHITE);
        newChat.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(new Color(18, 19, 22));
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 10, 15, 10));
        bottom.add(newChat);

        sidebar.add(top, BorderLayout.NORTH);
        sidebar.add(menu, BorderLayout.CENTER);
        sidebar.add(bottom, BorderLayout.SOUTH);

        add(sidebar, BorderLayout.WEST);
    }
//send message
private void sendMessage() {

    String text = inputField.getText().trim();
    if (text.isEmpty()) return;

    addMessage(text, true);   // user message
    inputField.setText("");

    showThinking();

    new Thread(() -> {
        try {
            String response = GeminiService.askGemini(text);

            SwingUtilities.invokeLater(() -> {
                removeThinking();
                addMessage(response, false);
            });

        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> {
                removeThinking();
                addMessage("Error: " + e.getMessage(), false);
            });
        }
    }).start();
}
    // ================= CHAT AREA =================
    private void initChatArea() {

        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatPanel.setBackground(new Color(10, 11, 13));
        chatPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        scrollPane = new JScrollPane(chatPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(new Color(10, 11, 13));

        add(scrollPane, BorderLayout.CENTER);

        showWelcome();
    }

    // ================= INPUT AREA =================
    private void initInputArea() {

    JPanel container = new JPanel(new BorderLayout());
    container.setBackground(new Color(10, 11, 13));
    container.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

    JPanel inputPanel = new JPanel(new BorderLayout());
    inputPanel.setBackground(new Color(30, 31, 35));
    inputPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

    inputField = new JTextField();
    inputField.setBackground(new Color(30, 31, 35));
    inputField.setForeground(Color.WHITE);
    inputField.setCaretColor(Color.WHITE);
    inputField.setBorder(null);
    inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

    sendButton = new JButton("➤");
    sendButton.setFocusPainted(false);
    sendButton.setBackground(new Color(0, 132, 255));
    sendButton.setForeground(Color.WHITE);
    sendButton.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

    inputPanel.add(inputField, BorderLayout.CENTER);
    inputPanel.add(sendButton, BorderLayout.EAST);

    container.add(inputPanel, BorderLayout.CENTER);

    add(container, BorderLayout.SOUTH);

    sendButton.addActionListener(e -> sendMessage());
    inputField.addActionListener(e -> sendMessage());
}
//helper method
private void animateMessage(JPanel panel) {
    panel.setOpaque(true);
    panel.setBackground(new Color(10, 11, 13));

    new Thread(() -> {
        try {
            for (int i = 0; i <= 10; i++) {
                float alpha = i / 10f;

                SwingUtilities.invokeLater(() -> {
                    panel.repaint();
                });

                Thread.sleep(15);
            }
        } catch (Exception ignored) {}
    }).start();
}
//add
private JPanel thinkingPanel;
private javax.swing.Timer typingTimer;
private int dotState = 0;


   //
   private void showThinking() {
    thinkingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    thinkingPanel.setBackground(new Color(10, 11, 13));

    JLabel label = new JLabel("Thinking");
    label.setForeground(new Color(180, 180, 180));
    label.setFont(new Font("Segoe UI", Font.PLAIN, 14));

    thinkingPanel.add(label);

    chatPanel.add(thinkingPanel);
    refresh();

    if (typingTimer != null) {
        typingTimer.stop();
    }

    typingTimer = new javax.swing.Timer(500, e -> {
        dotState = (dotState + 1) % 4;
        label.setText("Thinking" + ".".repeat(dotState));
    });

    typingTimer.start();
}
//
private void removeThinking() {

    if (typingTimer != null) {
        typingTimer.stop();
        typingTimer = null;
    }

    if (thinkingPanel != null) {
        chatPanel.remove(thinkingPanel);
        thinkingPanel = null;
    }

    refresh();
}
    // ================= MESSAGE UI =================
    private void addMessage(String text, boolean isUser) {

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(10, 11, 13));

        RoundedBubble bubble = new RoundedBubble(text, isUser);

        JPanel align = new JPanel(new FlowLayout(
                isUser ? FlowLayout.RIGHT : FlowLayout.LEFT
        ));
        align.setBackground(new Color(10, 11, 13));

        align.add(bubble);

        wrapper.add(align, BorderLayout.CENTER);

animateMessage(wrapper);

chatPanel.add(wrapper);
chatPanel.add(Box.createVerticalStrut(12));
        refresh();
    }

    // ================= THINKING =================
    
    // ================= HELPERS =================
    private void refresh() {
        chatPanel.revalidate();
        chatPanel.repaint();

        JScrollBar bar = scrollPane.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }

    private void showWelcome() {
        addMessage("Hello 👋\nHow can I help you today?", false);
    }

    private JButton sideBtn(String text) {
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setBackground(new Color(28, 29, 33));
        b.setForeground(Color.WHITE);
        b.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        b.setHorizontalAlignment(SwingConstants.LEFT);
        return b;
    }

    // ================= MAIN =================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ModernChatbotGUIV2::new);
    }
}