import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreateTrianglePage extends JPanel {
    private MainApp mainApp;
    private String username;
    private Color selectedColor;
    private JTextField rotationField = new JTextField();

    public CreateTrianglePage(MainApp mainApp, String username) {
        this.mainApp = mainApp;
        this.username = username;

        setBorder(new EmptyBorder(50, 50, 100, 50));
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Create Triangle", SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 24));
        add(title, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(contentPanel, BorderLayout.CENTER);

        JLabel baseLabel = new JLabel("Base:");
        contentPanel.add(baseLabel);
        JTextField baseField = new JTextField();
        contentPanel.add(baseField);

        JLabel heightLabel = new JLabel("Height:");
        contentPanel.add(heightLabel);
        JTextField heightField = new JTextField();
        contentPanel.add(heightField);

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
            String baseStr = baseField.getText().trim();
            String heightStr = heightField.getText().trim();
            String name = nameField.getText().trim();
            try {
                if (baseStr.isEmpty() || heightStr.isEmpty() ||  name.isEmpty()) {
                    throw new IllegalArgumentException("Please fill all fields.");
                }
                int base = Integer.parseInt(baseStr);
                int height = Integer.parseInt(heightStr);

                if (base <= 0) {
                    throw new IllegalArgumentException("Base must be positive.");
                }
                if (height <= 0) {
                    throw new IllegalArgumentException("Height must be positive.");
                }

                // Add new constraints here
                if (base >= 350) {
                    throw new IllegalArgumentException("Base must be less than 350.");
                }
                if (height >= 100) {
                    throw new IllegalArgumentException("Height must be less than 100.");
                }

                Triangle triangle = new Triangle(base, height, selectedColor, name);

                ResultTrianglePage resultTrianglePage = new ResultTrianglePage(mainApp, username, triangle);
                mainApp.getCardPanel().add(resultTrianglePage, "ResultTrianglePage");

                mainApp.showCard("ResultTrianglePage");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(CreateTrianglePage.this, "Invalid input. Please enter valid numbers for base and height.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(CreateTrianglePage.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        create.add(createButton);
    add(create, BorderLayout.SOUTH);
}
}

