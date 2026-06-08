import java.awt.*;
import java.time.LocalTime;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MessageBubble extends JPanel {

    public MessageBubble(String text, boolean user) {

        setLayout(new FlowLayout(
                user ? FlowLayout.RIGHT : FlowLayout.LEFT));

        setOpaque(false);

        String time =
                LocalTime.now()
                        .withNano(0)
                        .toString();

        JLabel label = new JLabel(
                "<html><div style='width:450px;'>"
                        + text.replace("\n", "<br>")
                        + "<br><br><small>"
                        + time
                        + "</small></div></html>");

        label.setBorder(
                new EmptyBorder(12,15,12,15));

        label.setOpaque(true);

        if(user){

            label.setBackground(
                    new Color(88,101,242));

            label.setForeground(
                    Color.WHITE);

        }else{

            label.setBackground(
                    new Color(55,55,55));

            label.setForeground(
                    Color.WHITE);
        }

        setBorder(
                BorderFactory.createEmptyBorder(
                        10,20,10,20));

        add(label);
    }
}