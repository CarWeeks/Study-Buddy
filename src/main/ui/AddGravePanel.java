package ui;

import model.Graveyard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGravePanel extends JPanel implements ActionListener {
    CurrState currState;
    JButton yesButton = new JButton("Yes");
    JButton noButton = new JButton("No");
    JPanel cardPanel;
    CardLayout cardLayout;
    JLabel addBuddy;
    GraveyardPanel graveyardPanel;

    public AddGravePanel(CurrState currState, JPanel cardPanel, GraveyardPanel graveyardPanel) {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == yesButton) {
            this.currState.setReload(false);
            this.currState.getGraveyard().addBuddy(this.currState.getCurrBuddy());
            graveyardPanel.updatePanel();
            this.cardLayout.show(cardPanel, "PBP");
        }
        if (e.getSource() == noButton) {
            this.currState.setReload(false);
            this.cardLayout.show(cardPanel, "PBP");
        }
    }
}
