import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapesPage extends JPanel {

    private MainApp mainApp;
    private String username;

    public ShapesPage(MainApp mainApp, String username) {
        this.mainApp = mainApp;
        this.username = username;

        setBorder(new EmptyBorder(50, 50, 50, 50));
        setLayout(new BorderLayout());
        this.add(new SharedMenuBar(mainApp, username), BorderLayout.NORTH);


        JLabel title = new JLabel("Shapes!!", SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 24));
        add(title, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 1, 5, 5));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));


        JButton circleButton = new JButton("Circle");
        circleButton.setPreferredSize(new Dimension(100, 50));
        circleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CirclePage circlePage = new CirclePage(mainApp, username);
                mainApp.getCardPanel().add(circlePage, "CirclePage");
                mainApp.showCard("CirclePage");
            }
        });
        buttonsPanel.add(circleButton);

        JButton rectangleButton = new JButton("Rectangle");
        rectangleButton.setPreferredSize(new Dimension(100, 50));
        rectangleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                RectanglePage rectanglePage = new RectanglePage(mainApp, username);
                mainApp.getCardPanel().add(rectanglePage, "RectanglePage");
                mainApp.showCard("RectanglePage");
            }
        });
        buttonsPanel.add(rectangleButton);

        JButton triangleButton = new JButton("Triangle");
        triangleButton.setPreferredSize(new Dimension(100, 50));
        triangleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                TrianglePage trianglePage = new TrianglePage(mainApp, username);
                mainApp.getCardPanel().add(trianglePage, "TrianglePage");
                mainApp.showCard("TrianglePage");
            }
        });
        buttonsPanel.add(triangleButton);

        JButton polygonButton = new JButton("Polygon");
        polygonButton.setPreferredSize(new Dimension(100, 50));
        polygonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                PolygonPage polygonPage = new PolygonPage(mainApp, username);
                mainApp.getCardPanel().add(polygonPage, "PolygonPage");
                mainApp.showCard("PolygonPage");
            }
        });
        buttonsPanel.add(polygonButton);

        add(buttonsPanel, BorderLayout.CENTER);
    }
}
