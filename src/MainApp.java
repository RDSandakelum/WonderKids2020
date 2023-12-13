import javax.swing.*;
import java.awt.*;

public class MainApp {

    private JFrame frame;
    private CardLayout cl;
    private JPanel cardPanel;
    private String loggedInUser;
    private SharedMenuBar sharedMenuBar;

    public MainApp() {
        frame = new JFrame("Wonderkids Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        cl = new CardLayout();
        cardPanel = new JPanel(cl);

        WelcomePage welcomePage = new WelcomePage(this);
        LoginPage loginPage = new LoginPage(this);
        RegisterPage registerPage = new RegisterPage(this);

        cardPanel.add(welcomePage, "WelcomePage");
        cardPanel.add(registerPage, "RegisterPage");
        cardPanel.add(loginPage, "LoginPage");

        frame.add(cardPanel);
        frame.setVisible(true);
    }

    public void showCard(String card) {
        cl.show(cardPanel, card);
    }

    public static void main(String[] args) {
        new MainApp();
    }

    public JFrame getFrame() {
        return frame;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;


        if (loggedInUser != null) {
            sharedMenuBar = new SharedMenuBar(this, loggedInUser);
            frame.setJMenuBar(sharedMenuBar);
            frame.validate();
        }
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }
}
