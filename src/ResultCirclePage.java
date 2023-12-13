import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ResultCirclePage extends JPanel {
    private MainApp mainApp;
    private String username;
    private Circle circle;

    public ResultCirclePage(MainApp mainApp, String username, Circle circle) {
        this.mainApp = mainApp;
        this.username = username;
        this.circle = circle;

        setBorder(new EmptyBorder(50, 50, 100, 50));
        setLayout(new BorderLayout());

        JLabel circleLabel = new JLabel("Your circle - " + circle.getName(), SwingConstants.CENTER);
        circleLabel.setFont(new Font(circleLabel.getFont().getName(), Font.BOLD, 24));
        add(circleLabel, BorderLayout.NORTH);

        JPanel circlePanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(circle.getColor());
                int radius = circle.getRadius();
                int diameter = radius * 2;

                int x = (this.getWidth() - diameter) / 2;
                int y = (this.getHeight() - diameter) / 2;
                g.fillOval(x, y, diameter, diameter);
            }
        };
        add(circlePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 5, 5));


        JCheckBox checkBox = new JCheckBox("3D");
        checkBox.setHorizontalAlignment(SwingConstants.CENTER);


        buttonPanel.add(checkBox);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveCircle();
            }
        });
        buttonPanel.add(saveButton);

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCircle();
                CreateCirclePage createCirclePage = new CreateCirclePage(mainApp, username);
                mainApp.getCardPanel().add(createCirclePage, "CreateCirclePage");
                mainApp.showCard("CreateCirclePage");
            }
        });
        buttonPanel.add(editButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCircle();


                CirclePage circlePage = new CirclePage(mainApp, username);
                mainApp.getCardPanel().add(circlePage, "CirclePage");
                mainApp.showCard("CirclePage");
            }
        });
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void saveCircle() {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(username + "circles.txt", true)))) {
            out.println(circle.getName());
            out.println(circle.getRadius());
            out.println(circle.getColor().getRed());
            out.println(circle.getColor().getGreen());
            out.println(circle.getColor().getBlue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteCircle() {
        File file = new File(username + "circles.txt");
        File tempFile = new File(username + "circles_temp.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String line = reader.readLine();
            while (line != null) {

                String name = line;
                int radius = Integer.parseInt(reader.readLine());
                int red = Integer.parseInt(reader.readLine());
                int green = Integer.parseInt(reader.readLine());
                int blue = Integer.parseInt(reader.readLine());


                if (!name.equals(circle.getName())) {
                    writer.println(name);
                    writer.println(radius);
                    writer.println(red);
                    writer.println(green);
                    writer.println(blue);
                }

                line = reader.readLine();
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        file.delete();
        tempFile.renameTo(file);
    }
}
