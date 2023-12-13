import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class AboutUs extends JPanel {

    private MainApp mainApp;

    public AboutUs(MainApp mainApp) {
        this.mainApp = mainApp;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(50, 50, 100, 50));

        JLabel greetingLabel = new JLabel("Howdy ", SwingConstants.CENTER);
        greetingLabel.setFont(new Font(greetingLabel.getFont().getName(), Font.BOLD, 30));
        add(greetingLabel, BorderLayout.CENTER);

    }
}
