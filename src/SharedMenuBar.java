import javax.swing.*;

public class SharedMenuBar extends JMenuBar {
    private MainApp mainApp;
    private String username;

    public SharedMenuBar(MainApp mainApp, String username) {
        this.mainApp = mainApp;
        this.username = username;

        JMenu menu = new JMenu("Menu");
        this.add(menu);

        JMenuItem userProfileMenuItem = new JMenuItem("User Profile");
        menu.add(userProfileMenuItem);
        userProfileMenuItem.addActionListener(e -> {
            UserProfilePage userProfilePage = new UserProfilePage(mainApp, username);
            mainApp.getCardPanel().add(userProfilePage, "UserProfilePage");
            mainApp.showCard("UserProfilePage");
        });

        JMenu shapesMenu = new JMenu("Shapes");
        menu.add(shapesMenu);

        JMenuItem circleMenuItem = new JMenuItem("Circle");
        shapesMenu.add(circleMenuItem);
        circleMenuItem.addActionListener(e -> {
            CirclePage circlePage = new CirclePage(mainApp, username);
            mainApp.getCardPanel().add(circlePage, "CirclePage");
            mainApp.showCard("CirclePage");
        });

        JMenuItem rectangleMenuItem = new JMenuItem("Rectangle");
        shapesMenu.add(rectangleMenuItem);
        rectangleMenuItem.addActionListener(e -> {
            RectanglePage rectanglePage = new RectanglePage(mainApp, username);
            mainApp.getCardPanel().add(rectanglePage, "RectanglePage");
            mainApp.showCard("RectanglePage");
        });

        JMenuItem triangleMenuItem = new JMenuItem("Triangle");
        shapesMenu.add(triangleMenuItem);
        triangleMenuItem.addActionListener(e -> {
            TrianglePage trianglePage = new TrianglePage(mainApp, username);
            mainApp.getCardPanel().add(trianglePage, "TrianglePage");
            mainApp.showCard("TrianglePage");
        });

        JMenuItem polygonMenuItem = new JMenuItem("Polygon");
        shapesMenu.add(polygonMenuItem);
        polygonMenuItem.addActionListener(e -> {
            PolygonPage polygonPage = new PolygonPage(mainApp, username);
            mainApp.getCardPanel().add(polygonPage, "PolygonPage");
            mainApp.showCard("PolygonPage");
        });

        JMenuItem aboutUsMenuItem = new JMenuItem("About Us");
        menu.add(aboutUsMenuItem);
        aboutUsMenuItem.addActionListener(e -> {
            AboutUs aboutUsPage = new AboutUs(mainApp);
            mainApp.getCardPanel().add(aboutUsPage, "AboutUsPage");
            mainApp.showCard("AboutUsPage");
        });
    }

    public void updateUsername(String username) {
        this.username = username;
    }

}
