import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCirclePage extends JPanel {
    private MainApp mainApp;
    private String username;
    private Color selectedColor;

    public CreateCirclePage(MainApp mainApp, String username) {
        this.mainApp = mainApp;
        this.username = username;

        setBorder(new EmptyBorder(50, 50, 100, 50));
        setLayout(new BorderLayout());



        JLabel title = new JLabel("Create Circle", SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 24));
        add(title, BorderLayout.NORTH);


        JPanel contentPanel = new JPanel((new GridLayout(4,2,5,5)));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));


        JPanel radius = new JPanel(new GridLayout());
        JLabel radiusLabel = new JLabel("Radius:");
        radius.add(radiusLabel);
        contentPanel.add(radius);


        JPanel radiusF = new JPanel(new GridLayout());
        JTextField radiusField = new JTextField();
        radiusF.add(radiusField);
        contentPanel.add(radiusF);


        JPanel color = new JPanel(new GridLayout());
        JLabel colorLabel = new JLabel("Color:");
        color.add(colorLabel);
        contentPanel.add(color);

        JPanel colorB = new JPanel(new GridLayout());
        JButton colorButton = new JButton("Choose Color");
        colorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedColor = JColorChooser.showDialog(null, "Choose a Color", selectedColor);
                if (selectedColor == null) {
                    selectedColor = Color.BLACK;
                }
            }
        });
        colorB.add(colorButton);
        contentPanel.add(colorB);


        JPanel name = new JPanel(new GridLayout());
        JLabel nameLabel = new JLabel("Name:");
        name.add(nameLabel);
        contentPanel.add(name);


        JPanel txt = new JPanel(new GridLayout());
        JTextField nameField = new JTextField();
        txt.add(nameField);
        contentPanel.add(txt);



        add(contentPanel, BorderLayout.CENTER);

        JPanel create = new JPanel(new GridLayout());
        create.setBorder(new EmptyBorder(0, 200, 0, 200));
        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String radiusStr = radiusField.getText().trim();
                String name = nameField.getText().trim();

                try {
                    if (radiusStr.isEmpty() || name.isEmpty()) {
                        throw new IllegalArgumentException("Please fill all fields.");
                    }

                    int radius = Integer.parseInt(radiusStr);
                    if (radius <= 0) {
                        throw new IllegalArgumentException("Radius must be positive.");
                    }

                    // Add the new condition here
                    if (radius > 150) {
                        throw new IllegalArgumentException("Radius must be less than or equal to 150.");
                    }

                    Circle circle = new Circle(radius, selectedColor, name);

                    ResultCirclePage resultCirclePage = new ResultCirclePage(mainApp, username, circle);
                    mainApp.getCardPanel().add(resultCirclePage, "ResultCirclePage");

                    mainApp.showCard("ResultCirclePage");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CreateCirclePage.this, "Invalid radius. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(CreateCirclePage.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        create.add(createButton);
        add(create, BorderLayout.SOUTH);
    }
}
