import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateRectanglePage extends JPanel {
    private MainApp mainApp;
    private String username;
    private Color selectedColor;
    private JTextField rotationField = new JTextField();

    public CreateRectanglePage(MainApp mainApp, String username) {
        this.mainApp = mainApp;
        this.username = username;

        setBorder(new EmptyBorder(50, 50, 100, 50));
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Create Rectangle", SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 24));
        add(title, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(contentPanel, BorderLayout.CENTER);

        JLabel widthLabel = new JLabel("Width:");
        contentPanel.add(widthLabel);
        JTextField widthField = new JTextField();
        contentPanel.add(widthField);

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
            String widthStr = widthField.getText().trim();
            String heightStr = heightField.getText().trim();
            String name = nameField.getText().trim();
            try {
                if (widthStr.isEmpty() || heightStr.isEmpty() ||  name.isEmpty()) {
                    throw new IllegalArgumentException("Please fill all fields.");
                }
                int width = Integer.parseInt(widthStr);
                int height = Integer.parseInt(heightStr);

                if (width <= 0 || height <= 0) {
                    throw new IllegalArgumentException("Width and height must be positive.");
                }

                // Add the new conditions here
                if (width > 400) {
                    throw new IllegalArgumentException("Width must be less than or equal to 400.");
                }
                if (height > 200) {
                    throw new IllegalArgumentException("Height must be less than or equal to 200.");
                }

                Rectangle rectangle = new Rectangle(width, height, selectedColor, name);

                ResultRectanglePage resultRectanglePage = new ResultRectanglePage(mainApp, username, rectangle);
                mainApp.getCardPanel().add(resultRectanglePage, "ResultRectanglePage");

                mainApp.showCard("ResultRectanglePage");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(CreateRectanglePage.this, "Invalid input. Please enter valid numbers for width and height.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(CreateRectanglePage.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        create.add(createButton);
        add(create, BorderLayout.SOUTH);
    }
}
