import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.*;

public class ResultPolygonPage extends JPanel {
    private MainApp mainApp;
    private String username;
    private Polygon polygon;
    private int rotationAngle = 0;  // New variable to store the rotation angle

    public ResultPolygonPage(MainApp mainApp, String username, Polygon polygon) {
        this.mainApp = mainApp;
        this.username = username;
        this.polygon = polygon;

        setBorder(new EmptyBorder(50, 50, 100, 50));
        setLayout(new BorderLayout());

        JLabel polygonLabel = new JLabel("Your polygon - " + polygon.getName(), SwingConstants.CENTER);
        polygonLabel.setFont(new Font(polygonLabel.getFont().getName(), Font.BOLD, 24));
        add(polygonLabel, BorderLayout.NORTH);


        JPanel polygonPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(polygon.getColor());

                AffineTransform old = g2d.getTransform();
                g2d.rotate(Math.toRadians(rotationAngle), getWidth() / 2, getHeight() / 2);


                int xpoints[] = new int[polygon.getSides()];
                int ypoints[] = new int[polygon.getSides()];

                for (int i = 0; i < polygon.getSides(); i++) {
                    xpoints[i] = (int) (getWidth() / 2 + polygon.getSideLength() * Math.cos(2 * Math.PI * i / polygon.getSides()));
                    ypoints[i] = (int) (getHeight() / 2 + polygon.getSideLength() * Math.sin(2 * Math.PI * i / polygon.getSides()));
                }

                g2d.drawPolygon(xpoints, ypoints, polygon.getSides());

                g2d.setTransform(old);
            }
        };
        add(polygonPanel, BorderLayout.CENTER);


        JSlider rotationSlider = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
        rotationSlider.addChangeListener(e -> {
            rotationAngle = rotationSlider.getValue();
            polygonPanel.repaint();
        });
        add(rotationSlider, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));


        JCheckBox threeDCheckBox = new JCheckBox("3D");
        buttonPanel.add(threeDCheckBox);

        JPanel savePanel = new JPanel(new GridLayout());
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                savePolygon();
            }
        });
        savePanel.add(saveButton);
        buttonPanel.add(savePanel);


        JPanel editPanel = new JPanel(new GridLayout());
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletePolygon();
                CreatePolygonPage createPolygonPage = new CreatePolygonPage(mainApp, username);
                mainApp.getCardPanel().add(createPolygonPage, "CreatePolygonPage");
                mainApp.showCard("CreatePolygonPage");
            }
        });
        editPanel.add(editButton);
        buttonPanel.add(editPanel);


        JPanel deletePanel = new JPanel(new GridLayout());
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletePolygon();

                PolygonPage polygonPage = new PolygonPage(mainApp, username);
                mainApp.getCardPanel().add(polygonPage, "PolygonPage");
                mainApp.showCard("PolygonPage");
            }
        });
        deletePanel.add(deleteButton);
        buttonPanel.add(deletePanel);

        add(buttonPanel, BorderLayout.SOUTH);
    }


    private void savePolygon() {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(username + "polygons.txt", true)))) {
            out.println(polygon.getName());
            out.println(polygon.getSides());
            out.println(polygon.getSideLength());
            out.println(polygon.getColor().getRed());
            out.println(polygon.getColor().getGreen());
            out.println(polygon.getColor().getBlue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deletePolygon() {
        File file = new File(username + "polygons.txt");
        File tempFile = new File(username + "polygons_temp.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String line = reader.readLine();
            while (line != null) {

                String name = line;
                int sides = Integer.parseInt(reader.readLine());
                int sideLength = Integer.parseInt(reader.readLine());
                int red = Integer.parseInt(reader.readLine());
                int green = Integer.parseInt(reader.readLine());
                int blue = Integer.parseInt(reader.readLine());


                if (!name.equals(polygon.getName())) {
                    writer.println(name);
                    writer.println(sides);
                    writer.println(sideLength);
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
