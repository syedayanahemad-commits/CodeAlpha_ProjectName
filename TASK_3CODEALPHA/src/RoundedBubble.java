import java.awt.*;
import javax.swing.*;

/**
 * Custom JPanel that draws a ChatGPT-style rounded message bubble.
 * Supports:
 * - Left (AI) and Right (User) alignment
 * - Dark theme colors
 * - Auto text wrapping
 */
public class RoundedBubble extends JPanel {


    
    private final String text;
    private final boolean isUser;

    private final Color userColor = new Color(0, 132, 255);     // blue
    private final Color aiColor = new Color(60, 60, 60);        // dark gray
    private final Color textColor = Color.WHITE;

    public RoundedBubble(String text, boolean isUser) {
        this.text = text;
        this.isUser = isUser;

        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        int arc = 20;

        // Bubble color
        g2.setColor(isUser ? userColor : aiColor);

        // Draw rounded rectangle bubble
        g2.fillRoundRect(0, 0, width, height, arc, arc);

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        int maxWidth = 400;

        FontMetrics fm = getFontMetrics(getFont());
        int lineHeight = fm.getHeight();

        // simple wrapping calculation
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();

        int lines = 1;
        int currentWidth = 0;

        for (String word : words) {
            int wordWidth = fm.stringWidth(word + " ");

            if (currentWidth + wordWidth > maxWidth) {
                lines++;
                currentWidth = wordWidth;
            } else {
                currentWidth += wordWidth;
            }

            line.append(word).append(" ");
        }

        int height = lines * lineHeight + 20;

        return new Dimension(maxWidth, height);
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2.setColor(textColor);
        g2.setFont(getFont());

        int x = 15;
        int y = 25;

        FontMetrics fm = g2.getFontMetrics();

        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        int maxWidth = 380;

        for (String word : words) {
            if (fm.stringWidth(line + word) > maxWidth) {
                g2.drawString(line.toString(), x, y);
                y += fm.getHeight();
                line = new StringBuilder(word + " ");
            } else {
                line.append(word).append(" ");
            }
        }

        g2.drawString(line.toString(), x, y);

        g2.dispose();
    }
}
