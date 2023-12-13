import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.border.EmptyBorder;

public class UserProfilePage extends JPanel {

    private MainApp mainApp;
    private String username;

    public UserProfilePage(MainApp mainApp, String username) {
        this.setLayout(new BorderLayout());
        this.add(new SharedMenuBar(mainApp, username), BorderLayout.NORTH);
        this.mainApp = mainApp;
        this.username = username;

        setBorder(new EmptyBorder(50, 50, 50, 50));
        setLayout(new BorderLayout());


        JLabel title = new JLabel("User Profile Page", SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 24));
        add(title, BorderLayout.NORTH);

        JPanel detailsPanel = new JPanel(new GridLayout(3, 1));


        String[] userDetails = new String[3];
        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[0].equals(username)) {
                    userDetails[0] = "Username: " + details[0];
                    userDetails[1] = "Date of Birth: " + details[1];
                    userDetails[2] = "Email: " + details[2];
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String detail : userDetails) {
            JPanel detailPanel = new JPanel(new GridBagLayout());
            JLabel detailLabel = new JLabel(detail, SwingConstants.CENTER);
            detailLabel.setFont(new Font(detailLabel.getFont().getName(), Font.PLAIN, 18));
            detailPanel.add(detailLabel);
            detailsPanel.add(detailPanel);
        }

        add(detailsPanel, BorderLayout.CENTER);

        JPanel edit = new JPanel(new GridBagLayout());
        JButton editButton = new JButton("Edit");
        edit.add(editButton);
        add(edit, BorderLayout.SOUTH);


        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserProfileEditPage userProfileEditPage = new UserProfileEditPage(mainApp, mainApp.getLoggedInUser());
                mainApp.getCardPanel().add(userProfileEditPage, "UserProfileEditPage");
                mainApp.showCard("UserProfileEditPage");
            }
        });
    }
}
