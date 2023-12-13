import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.border.EmptyBorder;

public class LoginPage extends JPanel {

    private MainApp mainApp;

    public LoginPage(MainApp mainApp) {
        this.mainApp = mainApp;

        setBorder(new EmptyBorder(50, 50, 50, 50));
        setLayout(new BorderLayout());


        JLabel title = new JLabel("Login Page", SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 24));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));


        JPanel usernameLabel = new JPanel(new GridBagLayout());
        JLabel usernameTxt = new JLabel("Username: ");
        usernameTxt.setPreferredSize(new Dimension(100, 50));
        usernameLabel.add(usernameTxt);
        form.add(usernameLabel);

        JPanel usernameField = new JPanel(new GridBagLayout());
        JTextField username = new JTextField(20); // Change this line
        username.setPreferredSize(new Dimension(50, 50));
        usernameField.add(username);
        form.add(usernameField);

        JPanel passwordLabel = new JPanel(new GridBagLayout());
        JLabel passwordTxt = new JLabel("Password: ");
        passwordTxt.setPreferredSize(new Dimension(100, 50));
        passwordLabel.add(passwordTxt);
        form.add(passwordLabel);

        JPanel passwordField = new JPanel(new GridBagLayout());
        JPasswordField password = new JPasswordField(20);
        password.setPreferredSize(new Dimension(50, 50));
        passwordField.add(password);
        form.add(passwordField);

        JPanel login = new JPanel(new GridBagLayout());
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 50));
        login.add(loginButton);
        form.add(login);

        JPanel back = new JPanel(new GridBagLayout());
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 50));
        back.add(backButton);
        form.add(back);

        add(form);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String user = username.getText();
                String pass = new String(password.getPassword());


                try {
                    BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
                    String line;
                    boolean loginSuccessful = false;
                    while ((line = reader.readLine()) != null) {
                        String[] details = line.split(",");
                        if (details[0].equals(user) && details[3].equals(pass)) {

                            mainApp.setLoggedInUser(user);

                            HomePage homePage = new HomePage(mainApp, mainApp.getLoggedInUser());
                            mainApp.getCardPanel().add(homePage, "HomePage");
                            mainApp.showCard("HomePage");

                            loginSuccessful = true;
                            break;
                        }
                    }


                    if (!loginSuccessful) {
                        JOptionPane.showMessageDialog(LoginPage.this, "Login failed. Incorrect username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(LoginPage.this, "An error occurred while reading the user file.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainApp.showCard("WelcomePage");
            }
        });
    }
}
