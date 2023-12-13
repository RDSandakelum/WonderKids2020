import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import javax.swing.border.EmptyBorder;

public class UserProfileEditPage extends JPanel {

    private MainApp mainApp;
    private String username;
    private JTextField usernameField, dobField, emailField;
    private JPasswordField passwordField;

    public UserProfileEditPage(MainApp mainApp, String username) {
        this.setLayout(new BorderLayout());
        this.add(new SharedMenuBar(mainApp, username), BorderLayout.NORTH);
        this.mainApp = mainApp;
        this.username = username;

        setBorder(new EmptyBorder(50, 50, 50, 50));
        setLayout(new BorderLayout());


        JLabel title = new JLabel("Edit User Profile", SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 24));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));


        form.add(new JLabel("Username:"));
        usernameField = new JTextField(20);
        form.add(usernameField);

        form.add(new JLabel("Date of Birth:"));
        dobField = new JTextField(20);
        form.add(dobField);

        form.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        form.add(emailField);

        form.add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        form.add(passwordField);


        String[] userDetails = new String[3];
        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[0].equals(username)) {
                    userDetails[0] = details[0];
                    userDetails[1] = details[1];
                    userDetails[2] = details[2];
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        usernameField.setText(userDetails[0]);
        dobField.setText(userDetails[1]);
        emailField.setText(userDetails[2]);

        add(form, BorderLayout.CENTER);

        JPanel change = new JPanel(new GridBagLayout());
        JButton changeButton = new JButton("Change");
        changeButton.setPreferredSize(new Dimension(100, 50));
        change.add(changeButton);
        form.add(change);

        add(form, BorderLayout.CENTER);


        changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check if any field is empty
                if(usernameField.getText().isEmpty() || dobField.getText().isEmpty() || emailField.getText().isEmpty() || new String(passwordField.getPassword()).isEmpty()) {
                    JOptionPane.showMessageDialog(UserProfileEditPage.this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<String> userDetailsList = new ArrayList<>();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        userDetailsList.add(line);
                    }
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                for (int i = 0; i < userDetailsList.size(); i++) {
                    String[] details = userDetailsList.get(i).split(",");
                    if (details[0].equals(username)) {
                        // Update user details
                        details[0] = usernameField.getText();
                        details[1] = dobField.getText();
                        details[2] = emailField.getText();
                        details[3] = new String(passwordField.getPassword());

                        // Update the line in the list
                        userDetailsList.set(i, String.join(",", details));
                        break;
                    }
                }

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
                    for (String line : userDetailsList) {
                        writer.write(line);
                        writer.newLine();
                    }
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                UserProfilePage userProfilePage = new UserProfilePage(mainApp, usernameField.getText());
                mainApp.getCardPanel().add(userProfilePage, "UserProfilePage");
                mainApp.showCard("UserProfilePage");
            }
        });

    }
}
