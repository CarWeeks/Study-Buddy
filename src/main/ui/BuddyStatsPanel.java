package ui;

import model.Buddy;
import model.Graveyard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuddyStatsPanel extends JPanel implements ActionListener {
    private static final int INTERVAL = 100;
    BuddyPanel bp;
    StatsPanel sp;
    Buddy currBuddy;
    Graveyard graveyard;

    public BuddyStatsPanel(Buddy b, Graveyard g) {
        setPreferredSize(new Dimension(640, 480));
        this.currBuddy = b;
        this.graveyard = g;
        setBackground(Color.GRAY);
        this.bp = new BuddyPanel(b, g);
        this.sp = new StatsPanel(b);
        this.add(bp, BorderLayout.NORTH);
        this.add(sp, BorderLayout.SOUTH);
    }

    // Set up timer
    // modifies: none
    // effects:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    public void addTimer() {
        Timer t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (currBuddy.isLiving()) {
                    currBuddy.updateStats();
                    bp.repaint();
                    sp.update();
                }
            }
        });
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bp.exitButton) {
            System.exit(0);
        }
    }
}
