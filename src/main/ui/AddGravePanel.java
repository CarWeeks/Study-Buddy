package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents panel for choosing to put dead Buddy in graveyard or not
public class AddGravePanel extends JPanel implements ActionListener {

    CurrState currState;
    JButton yesButton = new JButton("Yes");
    JButton noButton = new JButton("No");
    JPanel cardPanel;
    CardLayout cardLayout;
    JLabel addBuddy;
    GraveyardPanel graveyardPanel;

    // EFFECTS: creates a panel with yes and no button
    public AddGravePanel(CurrState currState, JPanel cardPanel, GraveyardPanel graveyardPanel) {
        setBackground(new Color(69, 142, 175, 255));
        this.currState = currState;
        this.cardPanel = cardPanel;
        this.cardLayout = (CardLayout) cardPanel.getLayout();
        this.graveyardPanel = graveyardPanel;
        this.addBuddy = new JLabel("Would you like to add your Buddy to the graveyard?");
        this.add(addBuddy);
        this.add(yesButton);
        yesButton.addActionListener(this);
        this.add(noButton);
        noButton.addActionListener(this);
    }

    // MODIFIES: currState, cardLayout
    // EFFECTS: interprets what to do in case of button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == yesButton) {
            this.currState.getGraveyard().addBuddy(this.currState.getCurrBuddy());
            graveyardPanel.updatePanel();
            this.cardLayout.show(cardPanel, "PBP");
        }
        if (e.getSource() == noButton) {
            graveyardPanel.updatePanel();
            this.cardLayout.show(cardPanel, "PBP");
        }
    }
}
