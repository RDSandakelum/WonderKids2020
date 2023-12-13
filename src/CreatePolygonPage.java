import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreatePolygonPage extends JPanel {
    private MainApp mainApp;
    private String username;
    private Color selectedColor;
    private JTextField rotationField = new JTextField();

    public CreatePolygonPage(MainApp mainApp, String username) {
        this.mainApp = mainApp;
        this.username = username;

        setBorder(new EmptyBorder(50, 50, 100, 50));
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Create Polygon", SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 24));
        add(title, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(contentPanel, BorderLayout.CENTER);

        JLabel sidesLabel = new JLabel("Sides:");
        contentPanel.add(sidesLabel);
        JTextField sidesField = new JTextField();
        contentPanel.add(sidesField);

        JLabel lengthLabel = new JLabel("Side Length:");
        contentPanel.add(lengthLabel);
        JTextField lengthField = new JTextField();
        contentPanel.add(lengthField);

        JLabel colorLabel = new JLabel("Color:");
        contentPanel.add(colorLabel);
        JButton colorButton = new JButton("Choose Color");
        colorButton.addActionListener(e -> {
            selectedColor = JColorChooser.showDialog(null, "Choose a Color", selectedColor);
            if (selectedColor == null) {
                selectedColor = Color.BLACK;
            }
        });
        contentPanel.add(colorButton);


        JLabel nameLabel = new JLabel("Name:");
        contentPanel.add(nameLabel);
        JTextField nameField = new JTextField();
        contentPanel.add(nameField);

        JPanel create = new JPanel(new GridLayout());
        create.setBorder(new EmptyBorder(0, 200, 0, 200));
        JButton createButton = new JButton("Create");
        createButton.addActionListener(e -> {
            String sidesStr = sidesField.getText().trim();
            String lengthStr = lengthField.getText().trim();
            String name = nameField.getText().trim();
            try {
                if (sidesStr.isEmpty() || lengthStr.isEmpty() ||  name.isEmpty()) {
                    throw new IllegalArgumentException("Please fill all fields.");
                }
                int sides = Integer.parseInt(sidesStr);
                int sideLength = Integer.parseInt(lengthStr);

                if (sides <= 2) {
                    throw new IllegalArgumentException("Sides must be greater than 2.");
                }
                if (sideLength <= 0) {
                    throw new IllegalArgumentException("Side Length must be positive.");
                }

                // Add the new condition here
                if (sideLength > 150) {
                    throw new IllegalArgumentException("Side Length must be less than or equal to 150.");
                }

                switch (sides){
                    case 3: name = "Triangle"; break;
                    case 4: name = "Quadrilateral"; break;
                    case 5: name = "Pentagon"; break;
                    case 6: name = "Hexagon"; break;
                    case 7: name = "Heptagon"; break;
                    case 8: name = "Octagon"; break;
                    default: name = name;
                }
                Polygon polygon = new Polygon(sides, sideLength, selectedColor, name);
                ResultPolygonPage resultPolygonPage = new ResultPolygonPage(mainApp, username, polygon);
                mainApp.getCardPanel().add(resultPolygonPage, "ResultPolygonPage");
                mainApp.showCard("ResultPolygonPage");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(CreatePolygonPage.this, "Invalid input. Please enter valid numbers for sides, length, and rotation.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(CreatePolygonPage.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        create.add(createButton);
        add(create, BorderLayout.SOUTH);
    }
}
