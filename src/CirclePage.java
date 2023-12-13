import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CirclePage extends JPanel {

    private MainApp mainApp;
    private String username;

    public CirclePage(MainApp mainApp, String username) {
        this.mainApp = mainApp;
        this.username = username;

        setBorder(new EmptyBorder(50, 50, 50, 50));
        setLayout(new BorderLayout());
        this.add(new SharedMenuBar(mainApp, username), BorderLayout.NORTH);


        JLabel title = new JLabel("Circle Page", SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));


        JButton createButton = new JButton("Create New");
        createButton.setPreferredSize(new Dimension(80, 30));
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateCirclePage createCirclePage = new CreateCirclePage(mainApp, username);
                mainApp.getCardPanel().add(createCirclePage, "CreateCirclePage");
                mainApp.showCard("CreateCirclePage");
            }
        });

        buttonsPanel.add(createButton);


        JButton viewEditButton = new JButton("View/Edit Existing");
        viewEditButton.setPreferredSize(new Dimension(80, 30));
        viewEditButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SavedCirclesPage savedCirclesPage = new SavedCirclesPage(mainApp, username);
                mainApp.getCardPanel().add(savedCirclesPage, "SavedCirclesPage");
                mainApp.showCard("SavedCirclesPage");
            }
        });

        buttonsPanel.add(viewEditButton);


        add(buttonsPanel, BorderLayout.CENTER);
    }
}
