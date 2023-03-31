package ui;

import model.Buddy;
import model.Graveyard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// creates panel to show each past Buddy stored in the graveyard
public class GraveyardPanel extends JPanel implements ActionListener {

    CurrState currState;
    JPanel cardPanel;
    CardLayout cardLayout;
    JButton backButton = new JButton("Back");
    JButton clearButton = new JButton("Clear graveyard");

    // EFFECTS: constructs graveyard panel
    public GraveyardPanel(CurrState cs, JPanel cardPanel) {
        setBackground(new Color(69, 142, 175, 255));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.currState = cs;
        this.cardPanel = cardPanel;
        this.cardLayout = (CardLayout) cardPanel.getLayout();
        this.add(clearButton);
        this.clearButton.addActionListener(this);
        this.add(backButton);
        this.backButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: clears panel and iterates through graveyard and adds Buddies back to panel
    public void updatePanel() {
        this.removeAll();
        this.add(backButton);
        this.add(clearButton);

        for (int i = 0; i < currState.getGraveyard().getLength(); i++) {
            Buddy b = currState.getGraveyard().getBuddy(i);
            this.add(new JLabel(b.getName() + ": " + Integer.toString(b.getTimeAlive()) + " seconds"));
        }
    }

    // MODIFIES: cardLayout
    // EFFECTS: returns to prior screen if back button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            cardLayout.show(cardPanel, "PBP");
        }
        if (e.getSource() == clearButton) {
            this.currState.setGraveyard(new Graveyard());
            this.updatePanel();
            this.repaint();
        }
    }
}
