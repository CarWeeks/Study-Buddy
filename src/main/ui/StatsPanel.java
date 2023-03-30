package ui;

import javax.swing.*;
import java.awt.*;

// represents panel that prints current statistic for current Buddy
public class StatsPanel extends JPanel {

    private static final String HEALTH_TXT = "Health: ";
    private static final String FOOD_TXT = "Food: ";
    private static final String ENERGY_TXT = "Energy: ";
    private static final String HAPPINESS_TXT = "Happiness: ";
    private static final int LBL_WIDTH = 150;
    private static final int LBL_HEIGHT = 30;
    private JLabel healthLbl;
    private JLabel foodLbl;
    private JLabel energyLbl;
    private JLabel happinessLbl;
    private CurrState currState;

    // EFFECTS: constructs stats panel
    public StatsPanel(CurrState currState) {
        this.currState = currState;
        setPreferredSize(new Dimension(800, 50));
        setBackground(new Color(46, 92, 116));
        healthLbl = new JLabel(HEALTH_TXT + currState.getCurrBuddy().getHealth());
        healthLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        foodLbl = new JLabel(FOOD_TXT + currState.getCurrBuddy().getFood());
        foodLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        energyLbl = new JLabel(ENERGY_TXT + currState.getCurrBuddy().getEnergy());
        energyLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        happinessLbl = new JLabel(HAPPINESS_TXT + currState.getCurrBuddy().getHappiness());
        happinessLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(healthLbl);
        add(Box.createHorizontalStrut(10));
        add(foodLbl);
        add(Box.createHorizontalStrut(10));
        add(energyLbl);
        add(Box.createHorizontalStrut(10));
        add(happinessLbl);
    }

    // MODIFIES: healthLBL, foodLBL, energyLBL, happinessLBL
    // EFFECTS: updates values to be printed for health, food, energy, and happiness stats
    public void update() {
        healthLbl.setText(HEALTH_TXT + currState.getCurrBuddy().getHealth());
        foodLbl.setText(FOOD_TXT + currState.getCurrBuddy().getFood());
        energyLbl.setText(ENERGY_TXT + currState.getCurrBuddy().getEnergy());
        happinessLbl.setText(HAPPINESS_TXT + currState.getCurrBuddy().getHappiness());
        repaint();
    }
}
