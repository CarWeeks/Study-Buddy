package ui;

import java.awt.*;

import javax.swing.*;

// main class that runs Buddy Application
public class BuddyMain extends JFrame {

    private JPanel cardPanel;
    private CardLayout cardLayout;
    private BuddyStatsPanel bsp;
    private PickBuddyPanel pbp;
    private AddGravePanel agp;
    private GraveyardPanel gp;
    CurrState cs;

    // EFFECTS: constructs main Frame and attaches extra panels
    public BuddyMain() {
        super("Buddy");
        this.setPreferredSize(new Dimension(800, 565));
        setBackground(new Color(1, 56, 77, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        cs = new CurrState();
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(cardLayout);
        this.add(cardPanel);
        this.bsp = new BuddyStatsPanel(cs, this.cardPanel);
        this.pbp = new PickBuddyPanel(cs, this.cardPanel);
        this.gp = new GraveyardPanel(this.cs, this.cardPanel);
        this.agp = new AddGravePanel(this.cs, this.cardPanel, this.gp);
        setupCardPanel();
        pack();
        centreOnScreen();
        setVisible(true);
        bsp.addTimer();
    }

    // MODIFIES: cardPanel
    // EFFECTS: adds panels to cardPanel and sets up cardLayout
    public void setupCardPanel() {
        cardPanel.add(bsp);
        cardPanel.add(pbp);
        cardPanel.add(agp);
        cardPanel.add(gp);
        cardLayout.addLayoutComponent("BSP", bsp);
        cardLayout.addLayoutComponent("PBP", pbp);
        cardLayout.addLayoutComponent("AGP", agp);
        cardLayout.addLayoutComponent("GP", gp);
        cardLayout.show(cardPanel, "PBP");
    }

    // MODIFIES: this
    // EFFECTS: location of frame is set so frame is centred on desktop
    // borrowed from CPSC 210 Space Invaders app
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }
}