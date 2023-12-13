import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.*;

public class ResultRectanglePage extends JPanel {
    private MainApp mainApp;
    private String username;
    private Rectangle rectangle;
    private int rotationAngle = 0;

    public ResultRectanglePage(MainApp mainApp, String username, Rectangle rectangle) {
        this.mainApp = mainApp;
        this.username = username;
        this.rectangle = rectangle;

        setBorder(new EmptyBorder(50, 50, 100, 50));
        setLayout(new BorderLayout());

        JLabel rectangleLabel = new JLabel("Your rectangle - " + rectangle.getName(), SwingConstants.CENTER);
        rectangleLabel.setFont(new Font(rectangleLabel.getFont().getName(), Font.BOLD, 24));
        add(rectangleLabel, BorderLayout.NORTH);


        JPanel rectanglePanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(rectangle.getColor());

                AffineTransform old = g2d.getTransform();
                g2d.rotate(Math.toRadians(rotationAngle), 50 + rectangle.getWidth()/2, 50 + rectangle.getHeight()/2);
                g2d.fillRect(50, 50, rectangle.getWidth(), rectangle.getHeight());
                g2d.setTransform(old);
            }
        };
        add(rectanglePanel, BorderLayout.CENTER);


        JSlider rotationSlider = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
        rotationSlider.addChangeListener(e -> {
            rotationAngle = rotationSlider.getValue();
            rectanglePanel.repaint();
        });
        add(rotationSlider, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));


        JCheckBox threeDCheckBox = new JCheckBox("3D");
        buttonPanel.add(threeDCheckBox);


        JPanel savePanel = new JPanel(new GridLayout());
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveRectangle();
            }
        });
        savePanel.add(saveButton);
        buttonPanel.add(savePanel);


        JPanel editPanel = new JPanel(new GridLayout());
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteRectangle();
                CreateRectanglePage createRectanglePage = new CreateRectanglePage(mainApp, username);
                mainApp.getCardPanel().add(createRectanglePage, "CreateRectanglePage");
                mainApp.showCard("CreateRectanglePage");
            }
        });
        editPanel.add(editButton);
        buttonPanel.add(editPanel);


        JPanel deletePanel = new JPanel(new GridLayout());
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteRectangle();


                RectanglePage rectanglePage = new RectanglePage(mainApp, username);
                mainApp.getCardPanel().add(rectanglePage, "RectanglePage");
                mainApp.showCard("RectanglePage");
            }
        });
        deletePanel.add(deleteButton);
        buttonPanel.add(deletePanel);

        add(buttonPanel, BorderLayout.SOUTH);
    }


    private void saveRectangle() {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(username + "rectangles.txt", true)))) {
            out.println(rectangle.getName());
            out.println(rectangle.getWidth());
            out.println(rectangle.getHeight());
            out.println(rectangle.getColor().getRed());
            out.println(rectangle.getColor().getGreen());
            out.println(rectangle.getColor().getBlue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteRectangle() {

        File file = new File(username + "rectangles.txt");
        File tempFile = new File(username + "rectangles_temp.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String line = reader.readLine();
            while (line != null) {
                // Read the properties of the next circle
                String name = line;
                int width = Integer.parseInt(reader.readLine());
                int height = Integer.parseInt(reader.readLine());
                int red = Integer.parseInt(reader.readLine());
                int green = Integer.parseInt(reader.readLine());
                int blue = Integer.parseInt(reader.readLine());


                if (!name.equals(rectangle.getName())) {
                    writer.println(name);
                    writer.println(width);
                    writer.println(height);
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
