import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JPanel {

    private MainApp mainApp;

    public WelcomePage(MainApp mainApp) {
        this.mainApp = mainApp;

        setLayout(new GridLayout(2, 1));

        JLabel label = new JLabel("Welcome to Wonder Kids Shapes Designer", SwingConstants.CENTER);
        label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 20));
        add(label);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font(loginButton.getFont().getName(), Font.PLAIN, 18));
        loginButton.setPreferredSize(new Dimension(200, 50));

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainApp.showCard("LoginPage");
            }
        });

        JPanel loginButtonPanel = new JPanel(); // New panel for the login button
        loginButtonPanel.add(loginButton);
        buttonPanel.add(loginButtonPanel);

        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font(registerButton.getFont().getName(), Font.PLAIN, 18)); // Change font size
        registerButton.setPreferredSize(new Dimension(200, 50));

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainApp.showCard("RegisterPage");
            }
        });

        JPanel registerButtonPanel = new JPanel();
        registerButtonPanel.add(registerButton);
        buttonPanel.add(registerButtonPanel);

        add(buttonPanel);
    }
}
