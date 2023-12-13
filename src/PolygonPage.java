import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PolygonPage extends JPanel {
    private MainApp mainApp;
    private String username;

    public PolygonPage(MainApp mainApp, String username) {
        this.mainApp = mainApp;
        this.username = username;

        setBorder(new EmptyBorder(50, 50, 50, 50));
        setLayout(new BorderLayout());
        this.add(new SharedMenuBar(mainApp, username), BorderLayout.NORTH);


        JLabel title = new JLabel("Polygon Page", SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));


        JButton createPolygonButton = new JButton("Create Polygon");
        createPolygonButton.setPreferredSize(new Dimension(80, 30));
        createPolygonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreatePolygonPage createPolygonPage = new CreatePolygonPage(mainApp, username);
                mainApp.getCardPanel().add(createPolygonPage, "CreatePolygonPage");
                mainApp.showCard("CreatePolygonPage");
            }
        });
        buttonsPanel.add(createPolygonButton);


        JButton viewSavedPolygonsButton = new JButton("View Saved Polygons");
        viewSavedPolygonsButton.setPreferredSize(new Dimension(80, 30));
        viewSavedPolygonsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SavedPolygonsPage savedPolygonsPage = new SavedPolygonsPage(mainApp, username);
                mainApp.getCardPanel().add(savedPolygonsPage, "SavedPolygonsPage");
                mainApp.showCard("SavedPolygonsPage");
            }
        });
        buttonsPanel.add(viewSavedPolygonsButton);


        add(buttonsPanel, BorderLayout.CENTER);
    }
}
