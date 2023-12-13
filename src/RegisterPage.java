import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.border.EmptyBorder;

public class RegisterPage extends JPanel {

    private MainApp mainApp;

    public RegisterPage(MainApp mainApp) {
        this.mainApp = mainApp;

        setBorder(new EmptyBorder(50, 50, 50, 50));
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Register Page", SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 24));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));

        JPanel usernameLabel = new JPanel(new GridBagLayout());
        JLabel usernameTxt = new JLabel("Username: ");
        usernameLabel.add(usernameTxt);
        form.add(usernameLabel);

        JPanel usernameField = new JPanel(new GridBagLayout());
        JTextField username = new JTextField(20);
        usernameField.add(username);
        form.add(usernameField);

        JPanel dobLabel = new JPanel(new GridBagLayout());
        JLabel dobTxt = new JLabel("Date of Birth: ");
        dobLabel.add(dobTxt);
        form.add(dobLabel);

        JPanel dobField = new JPanel(new GridBagLayout());
        JTextField dob = new JTextField(20);
        dobField.add(dob);
        form.add(dobField);

        JPanel emailLabel = new JPanel(new GridBagLayout());
        JLabel emailTxt = new JLabel("Email: ");
        emailLabel.add(emailTxt);
        form.add(emailLabel);

        JPanel emailField = new JPanel(new GridBagLayout());
        JTextField email = new JTextField(20);
        emailField.add(email);
        form.add(emailField);

        JPanel passwordLabel = new JPanel(new GridBagLayout());
        JLabel passwordTxt = new JLabel("Password: ");
        passwordLabel.add(passwordTxt);
        form.add(passwordLabel);

        JPanel passwordField = new JPanel(new GridBagLayout());
        JPasswordField password = new JPasswordField(20);
        passwordField.add(password);
        form.add(passwordField);

        JPanel register = new JPanel(new GridBagLayout());
        JButton registerButton = new JButton("Register");
        register.add(registerButton);
        form.add(register);

        JPanel back = new JPanel(new GridBagLayout());
        JButton backButton = new JButton("Back");
        back.add(backButton);
        form.add(back);

        add(form, BorderLayout.CENTER);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (username.getText().isEmpty() || dob.getText().isEmpty() ||
                        email.getText().isEmpty() || password.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(RegisterPage.this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true));
                    writer.write(username.getText() + "," +
                            dob.getText() + "," +
                            email.getText() + "," +
                            new String(password.getPassword()) + "\n");
                    writer.close();


                    JOptionPane.showMessageDialog(RegisterPage.this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(RegisterPage.this, "An error occurred while registering.", "Error", JOptionPane.ERROR_MESSAGE);
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
