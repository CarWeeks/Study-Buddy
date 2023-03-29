package ui;

import model.Buddy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuddyStatsPanel extends JPanel implements ActionListener {
    private static final int INTERVAL = 1;
    private Buddy fillerBuddy;
    BuddyPanel bp;
    StatsPanel sp;
    CurrState currState;
    JPanel cardPanel;
    CardLayout cardLayout;
    JLabel buddyName;


    public BuddyStatsPanel(CurrState currState, JPanel cardPanel) {
        setPreferredSize(new Dimension(640, 480));
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

    // Set up timer
    // modifies: none
    // effects:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    public void addTimer() {
        Timer t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!currState.getReload()) {
                    currState.getCurrBuddy().updateStats();
                    bp.repaint();
                    sp.update();
                    bp.updateName();
                    updateFillerBuddy();
                    updateReload();
                } else {
                    cardLayout.show(cardPanel, "AGP");
                }
            }
        });
        t.start();
    }

    private void updateFillerBuddy() {
        if (this.currState.getCurrBuddy().getHealth() == 0) {
            this.fillerBuddy = new Buddy(
                    this.currState.getCurrBuddy().getName(), this.currState.getCurrBuddy().getCreationTime(),
                    this.currState.getCurrBuddy().getDeathTime(), this.currState.getCurrBuddy().getTimeAlive(),
                    false, 10000, 10000,
                    this.currState.getCurrBuddy().getEnergy(), this.currState.getCurrBuddy().getHappiness());
        }
        if (this.fillerBuddy != null) {
            fillerBuddy.increaseFood(5);
        }
    }

    private void updateReload() {
        if (currState.getCurrBuddy().getHealth() == 0) {
            this.currState.setCurrBuddy(fillerBuddy);
            this.currState.setReload(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bp.exitButton) {
            System.exit(0);
        }
    }
}
