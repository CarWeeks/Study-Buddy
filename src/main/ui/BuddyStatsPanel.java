package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// creates panel containing Buddy panel and stats panel
public class BuddyStatsPanel extends JPanel {

    private static final int INTERVAL = 100;
    BuddyPanel bp;
    StatsPanel sp;
    CurrState currState;
    JPanel cardPanel;
    CardLayout cardLayout;
    JLabel buddyName;

    // EFFECTS: creates buddy stats panel with a given buddy panel and given stats panel
    public BuddyStatsPanel(CurrState currState, JPanel cardPanel) {
        setPreferredSize(new Dimension(640, 480));
        this.setBackground(new Color(69, 142, 175, 255));
        this.currState = currState;
        setBackground(Color.GRAY);
        this.cardPanel = cardPanel;
        this.cardLayout = (CardLayout) cardPanel.getLayout();
        this.bp = new BuddyPanel(currState, this.cardPanel);
        this.sp = new StatsPanel(currState);
        this.add(bp, BorderLayout.NORTH);
        this.add(sp, BorderLayout.CENTER);
        this.buddyName = new JLabel(currState.getCurrBuddy().getName());
        this.add(buddyName, BorderLayout.SOUTH);
    }

    // MODIFIES: currState, cardLayout, bp, sp
    // EFFECTS:  initializes a timer that updates game each INTERVAL milliseconds
    public void addTimer() {
        Timer t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                currState.getCurrBuddy().updateStats();
                bp.repaint();
                sp.update();
                bp.updateName();
                if (currState.getCurrBuddy().getHealth() == 0) {
                    currState.setIdle(true);
                    currState.getCurrBuddy().increaseHealth(1);
                    currState.getCurrBuddy().increaseFood(10);
                    cardLayout.show(cardPanel, "AGP");
                }
                if (currState.getIdle()) {
                    currState.getCurrBuddy().increaseFood(1);
                }
            }
        });
        t.start();
    }
}