import javax.swing.SwingUtilities;
public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ModernChatbotGUIV2  gui =
                    new ModernChatbotGUIV2();

            gui.setVisible(true);

        });
    }
}