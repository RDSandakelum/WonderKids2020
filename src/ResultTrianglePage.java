import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.*;

public class ResultTrianglePage extends JPanel {
    private MainApp mainApp;
    private String username;
    private Triangle triangle;
    private int rotationAngle = 0;

    public ResultTrianglePage(MainApp mainApp, String username, Triangle triangle) {
        this.mainApp = mainApp;
        this.username = username;
        this.triangle = triangle;

        setBorder(new EmptyBorder(50, 50, 100, 50));
        setLayout(new BorderLayout());

        JLabel triangleLabel = new JLabel("Your triangle - " + triangle.getName(), SwingConstants.CENTER);
        triangleLabel.setFont(new Font(triangleLabel.getFont().getName(), Font.BOLD, 24));
        add(triangleLabel, BorderLayout.NORTH);


        JPanel trianglePanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(triangle.getColor());

                AffineTransform old = g2d.getTransform();
                g2d.rotate(Math.toRadians(rotationAngle), 50 + triangle.getBase()/2, 50 + triangle.getHeight()/2);
                g2d.fillPolygon(new int[] {50, 50 + triangle.getBase()/2, 50 + triangle.getBase()}, new int[] {50 + triangle.getHeight(), 50, 50 + triangle.getHeight()}, 3);
                g2d.setTransform(old);
            }
        };
        add(trianglePanel, BorderLayout.CENTER);


        JSlider rotationSlider = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
        rotationSlider.addChangeListener(e -> {
            rotationAngle = rotationSlider.getValue();
            trianglePanel.repaint();
        });
        add(rotationSlider, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));


        JCheckBox threeDCheckBox = new JCheckBox("3D");
        buttonPanel.add(threeDCheckBox);


        JPanel savePanel = new JPanel(new GridLayout());
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveTriangle();
            }
        });
        savePanel.add(saveButton);
        buttonPanel.add(savePanel);


        JPanel editPanel = new JPanel(new GridLayout());
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteTriangle();
                CreateTrianglePage createTrianglePage = new CreateTrianglePage(mainApp, username);
                mainApp.getCardPanel().add(createTrianglePage, "CreateTrianglePage");
                mainApp.showCard("CreateTrianglePage");
            }
        });
        editPanel.add(editButton);
        buttonPanel.add(editPanel);


        JPanel deletePanel = new JPanel(new GridLayout());
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteTriangle();


                TrianglePage trianglePage = new TrianglePage(mainApp, username);
                mainApp.getCardPanel().add(trianglePage, "TrianglePage");
                mainApp.showCard("TrianglePage");
            }
        });
        deletePanel.add(deleteButton);
        buttonPanel.add(deletePanel);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void saveTriangle() {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(username + "triangles.txt", true)))) {
            out.println(triangle.getName());
            out.println(triangle.getBase());
            out.println(triangle.getHeight());
            out.println(triangle.getColor().getRed());
            out.println(triangle.getColor().getGreen());
            out.println(triangle.getColor().getBlue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteTriangle() {

        File file = new File(username + "triangles.txt");
        File tempFile = new File(username + "triangles_temp.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String line = reader.readLine();
            while (line != null) {

                String name = line;
                int base = Integer.parseInt(reader.readLine());
                int height = Integer.parseInt(reader.readLine());
                int red = Integer.parseInt(reader.readLine());
                int green = Integer.parseInt(reader.readLine());
                int blue = Integer.parseInt(reader.readLine());


                if (!name.equals(triangle.getName())) {
                    writer.println(name);
                    writer.println(base);
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
