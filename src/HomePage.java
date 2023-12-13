import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JPanel {

    private MainApp mainApp;
    private String username;

    public HomePage(MainApp mainApp, String username) {
        this.mainApp = mainApp;
        this.username = username;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(50, 50, 100, 50));

        JLabel greetingLabel = new JLabel("Howdy " + username, SwingConstants.CENTER);
        greetingLabel.setFont(new Font(greetingLabel.getFont().getName(), Font.BOLD, 30));
        add(greetingLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton userProfileButton = new JButton("User Profile");

        userProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserProfilePage userProfilePage = new UserProfilePage(mainApp, mainApp.getLoggedInUser());
                mainApp.getCardPanel().add(userProfilePage, "UserProfilePage");
                mainApp.showCard("UserProfilePage");
            }
        });
        buttonPanel.add(userProfileButton);

        JButton shapesButton = new JButton("Shapes");

        shapesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ShapesPage shapesPage = new ShapesPage(mainApp, username);
                mainApp.getCardPanel().add(shapesPage, "ShapesPage");
                mainApp.showCard("ShapesPage");
            }
        });

        buttonPanel.add(shapesButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainApp.showCard("WelcomePage");
            }
        });
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.SOUTH);


    }
}
