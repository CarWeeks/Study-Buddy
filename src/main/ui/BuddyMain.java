package ui;

import model.Buddy;

import java.awt.*;

import javax.swing.*;

public class BuddyMain extends JFrame {

    private JPanel cardPanel;
    private CardLayout cardLayout;
    private BuddyStatsPanel bsp;
    private PickBuddyPanel pbp;
    private AddGravePanel agp;
    private GraveyardPanel gp;
    CurrState cs;

    public BuddyMain() {
        super("Buddy");
        this.setPreferredSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        cs = new CurrState();
        // card panel
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(cardLayout);
        this.add(cardPanel);
        this.bsp = new BuddyStatsPanel(cs, this.cardPanel);
        this.pbp = new PickBuddyPanel(cs, this.cardPanel);
        this.gp = new GraveyardPanel(this.cs, this.cardPanel);
        this.agp = new AddGravePanel(this.cs, this.cardPanel, this.gp);
        cardPanel.add(bsp);
        cardPanel.add(pbp);
        cardPanel.add(agp);
        cardPanel.add(gp);
        cardLayout.addLayoutComponent("BSP", bsp);
        cardLayout.addLayoutComponent("PBP", pbp);
        cardLayout.addLayoutComponent("AGP", agp);
        cardLayout.addLayoutComponent("GP", gp);
        cardLayout.show(cardPanel, "PBP");
        pack();
        centreOnScreen();
        setVisible(true);
        bsp.addTimer();
    }

    // REQUIRES: inputted name must not contain spaces
    // MODIFIES: this
    // EFFECTS: creates a new Buddy object based on user input
    private void createCurrBuddy(String buddyName) {
        this.cs.setCurrBuddy(new Buddy(buddyName));
    }

    private void printOpening() {
        System.out.println("\u001B[35mWelcome to the Study Buddy Application!\u001B[0m");
        System.out.println("In this application you will be able to name your Buddy and help care for it.");
        System.out.println("Your Buddy will get hungry fast, so be sure to feed it regularly!");
        System.out.println("While your Buddy is alive you will be able to enter the following commands:");
        System.out.println("\u001B[33m"
                + "f: to feed your Buddy\nk: to kill your Buddy\ng: to view your graveyard in the console\ns: to save"
                + " your current Buddy and graveyard"
                + "\u001B[0m");
        System.out.println("Enjoy, and don't let your Buddy die!");
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }
}