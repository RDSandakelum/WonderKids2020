import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SavedCirclesPage extends JPanel {
    private MainApp mainApp;
    private String username;

    public SavedCirclesPage(MainApp mainApp, String username) {
        this.mainApp = mainApp;
        this.username = username;


        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));


        this.add(Box.createVerticalGlue());


        JLabel titleLabel = new JLabel("Your circles");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);


        this.add(Box.createRigidArea(new Dimension(0, 50)));


        List<Circle> circles = loadCircles();
        for (Circle circle : circles) {
            JButton button = new JButton(circle.getName());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    ResultCirclePage resultCirclePage = new ResultCirclePage(mainApp, username, circle);
                    mainApp.getCardPanel().add(resultCirclePage, "ResultCirclePage");
                    mainApp.showCard("ResultCirclePage");
                }
            });
            button.setFont(new Font(button.getFont().getName(), Font.PLAIN, 18));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(button);

            this.add(Box.createRigidArea(new Dimension(0, 25)));
        }


        this.add(Box.createRigidArea(new Dimension(0, 50)));


        JButton backButton = new JButton("Back");
        backButton.setFont(new Font(backButton.getFont().getName(), Font.PLAIN, 18));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CirclePage circlePage = new CirclePage(mainApp, username);
                mainApp.getCardPanel().add(circlePage, "CirclePage");
                mainApp.showCard("CirclePage");
            }
        });
        this.add(backButton);


        this.add(Box.createVerticalGlue());
    }

    private List<Circle> loadCircles() {
        List<Circle> circles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(username + "circles.txt"))) {
            String line = reader.readLine();
            while (line != null) {

                String name = line;
                int radius = Integer.parseInt(reader.readLine());
                int red = Integer.parseInt(reader.readLine());
                int green = Integer.parseInt(reader.readLine());
                int blue = Integer.parseInt(reader.readLine());


                circles.add(new Circle(radius, new Color(red, green, blue), name));

                line = reader.readLine();
            }
        } catch (IOException e) {

        }

        return circles;
    }
}
