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

public class SavedRectanglesPage extends JPanel {
    private MainApp mainApp;
    private String username;

    public SavedRectanglesPage(MainApp mainApp, String username) {
        this.mainApp = mainApp;
        this.username = username;


        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));


        this.add(Box.createVerticalGlue());


        JLabel titleLabel = new JLabel("Your rectangles");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);


        this.add(Box.createRigidArea(new Dimension(0, 50)));


        List<Rectangle> rectangles = loadRectangles();
        for (Rectangle rectangle : rectangles) {
            JButton button = new JButton(rectangle.getName());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    ResultRectanglePage resultRectanglePage = new ResultRectanglePage(mainApp, username, rectangle);
                    mainApp.getCardPanel().add(resultRectanglePage, "ResultRectanglePage");
                    mainApp.showCard("ResultRectanglePage");
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

                RectanglePage rectanglePage = new RectanglePage(mainApp, username);
                mainApp.getCardPanel().add(rectanglePage, "RectanglePage");
                mainApp.showCard("RectanglePage");
            }
        });
        this.add(backButton);


        this.add(Box.createVerticalGlue());
    }

    private List<Rectangle> loadRectangles() {
        List<Rectangle> rectangles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(username + "rectangles.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String name = line;
                String widthStr = reader.readLine();
                String heightStr = reader.readLine();
                String redStr = reader.readLine();
                String greenStr = reader.readLine();
                String blueStr = reader.readLine();

                if (widthStr == null || heightStr == null || redStr == null || greenStr == null || blueStr == null ) {
                    throw new IOException("Corrupted data file, missing data for rectangle " + name);
                }

                int width = Integer.parseInt(widthStr);
                int height = Integer.parseInt(heightStr);
                int red = Integer.parseInt(redStr);
                int green = Integer.parseInt(greenStr);
                int blue = Integer.parseInt(blueStr);


                rectangles.add(new Rectangle(width, height, new Color(red, green, blue), name));
            }
        } catch (IOException e) {

        }

        return rectangles;
    }
}
