package ui;

import model.Buddy;
import model.Graveyard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraveyardPanel extends JPanel implements ActionListener {
    CurrState currState;
    JPanel cardPanel;
    CardLayout cardLayout;
    JButton backButton = new JButton("Back");
    
    public GraveyardPanel(CurrState cs, JPanel cardPanel) {
        this.currState = cs;
        this.cardPanel = cardPanel;
        this.cardLayout = (CardLayout) cardPanel.getLayout();
        this.add(backButton);
        backButton.addActionListener(this);
    }

    public void updatePanel() {
        this.removeAll();
        this.add(backButton);

        for (int i = 0; i < currState.getGraveyard().getLength(); i++) {
            Buddy b = currState.getGraveyard().getBuddy(i);
            this.add(new JLabel(b.getName() + ": " + Integer.toString(b.getTimeAlive())));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            cardLayout.show(cardPanel, "PBP");
        }
    }
}
