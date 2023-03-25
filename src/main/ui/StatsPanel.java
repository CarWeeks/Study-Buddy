package ui;

import model.Buddy;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {
    private static final String HEALTH_TXT = "Health: ";
    private static final String FOOD_TXT = "Food: ";
    private static final String ENERGY_TXT = "Energy: ";
    private static final String HAPPINESS_TXT = "Happiness: ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    private JLabel healthLbl;
    private JLabel foodLbl;
    private JLabel energyLbl;
    private JLabel happinessLbl;
    private Buddy currBuddy;

    public StatsPanel(Buddy b) {
        this.currBuddy = b;
        setBackground(new Color(180, 180, 180));
        healthLbl = new JLabel(HEALTH_TXT + currBuddy.getHealth());
        healthLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        foodLbl = new JLabel(FOOD_TXT + currBuddy.getFood());
        foodLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        energyLbl = new JLabel(ENERGY_TXT + currBuddy.getEnergy());
        energyLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        happinessLbl = new JLabel(HAPPINESS_TXT + currBuddy.getHappiness());
        healthLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(healthLbl);
        add(Box.createHorizontalStrut(10));
        add(foodLbl);
        add(Box.createHorizontalStrut(10));
        add(energyLbl);
        add(Box.createHorizontalStrut(10));
        add(happinessLbl);
    }

    public void update() {
        healthLbl.setText(HEALTH_TXT + currBuddy.getHealth());
        foodLbl.setText(FOOD_TXT + currBuddy.getFood());
        energyLbl.setText(ENERGY_TXT + currBuddy.getEnergy());
        happinessLbl.setText(HAPPINESS_TXT + currBuddy.getHappiness());
        repaint();
    }
}
