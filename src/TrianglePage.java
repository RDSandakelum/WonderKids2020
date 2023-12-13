import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrianglePage extends JPanel {

    private MainApp mainApp;
    private String username;

    public TrianglePage(MainApp mainApp, String username) {
        this.mainApp = mainApp;
        this.username = username;

        setBorder(new EmptyBorder(50, 50, 50, 50));
        setLayout(new BorderLayout());
        this.add(new SharedMenuBar(mainApp, username), BorderLayout.NORTH);


        JLabel title = new JLabel("Triangle Page", SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));


        JButton createButton = new JButton("Create New");
        createButton.setPreferredSize(new Dimension(80, 30));
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateTrianglePage createTrianglePage = new CreateTrianglePage(mainApp, username);
                mainApp.getCardPanel().add(createTrianglePage, "CreateTrianglePage");
                mainApp.showCard("CreateTrianglePage");
            }
        });

        buttonsPanel.add(createButton);


        JButton viewEditButton = new JButton("View/Edit Existing");
        viewEditButton.setPreferredSize(new Dimension(80, 30));
        viewEditButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SavedTrianglesPage savedTrianglesPage = new SavedTrianglesPage(mainApp, username);
                mainApp.getCardPanel().add(savedTrianglesPage, "SavedTrianglesPage");
                mainApp.showCard("SavedTrianglesPage");
            }
        });

        buttonsPanel.add(viewEditButton);


        add(buttonsPanel, BorderLayout.CENTER);
    }
}
