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

public class SavedTrianglesPage extends JPanel {
    private MainApp mainApp;
    private String username;

    public SavedTrianglesPage(MainApp mainApp, String username) {
        this.mainApp = mainApp;
        this.username = username;


        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));


        this.add(Box.createVerticalGlue());


        JLabel titleLabel = new JLabel("Your triangles");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);


        this.add(Box.createRigidArea(new Dimension(0, 50)));


        List<Triangle> triangles = loadTriangles();
        for (Triangle triangle : triangles) {
            JButton button = new JButton(triangle.getName());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    ResultTrianglePage resultTrianglePage = new ResultTrianglePage(mainApp, username, triangle);
                    mainApp.getCardPanel().add(resultTrianglePage, "ResultTrianglePage");
                    mainApp.showCard("ResultTrianglePage");
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

                TrianglePage trianglePage = new TrianglePage(mainApp, username);
                mainApp.getCardPanel().add(trianglePage, "TrianglePage");
                mainApp.showCard("TrianglePage");
            }
        });
        this.add(backButton);


        this.add(Box.createVerticalGlue());
    }

    private List<Triangle> loadTriangles() {
        List<Triangle> triangles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(username + "triangles.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String name = line;
                String baseStr = reader.readLine();
                String heightStr = reader.readLine();
                String redStr = reader.readLine();
                String greenStr = reader.readLine();
                String blueStr = reader.readLine();
                if (baseStr == null || heightStr == null || redStr == null || greenStr == null || blueStr == null ) {
                    throw new IOException("Corrupted data file, missing data for triangle " + name);
                }

                int base = Integer.parseInt(baseStr);
                int height = Integer.parseInt(heightStr);
                int red = Integer.parseInt(redStr);
                int green = Integer.parseInt(greenStr);
                int blue = Integer.parseInt(blueStr);


                triangles.add(new Triangle(base, height, new Color(red, green, blue), name));
            }
        } catch (IOException e) {

        }

        return triangles;
    }
}
