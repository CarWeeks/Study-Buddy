package ui;

import model.Buddy;
import org.json.JSONException;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PickBuddyPanel extends JPanel implements ActionListener {
    private static final String JSON_STORE = "./data/currentState.json";
    CurrState currState;
    JPanel cardPanel;
    CardLayout cardLayout;
    private JsonReader jsonReader;
    JButton createBuddy = new JButton("Make new Buddy");
    JButton loadBuddy = new JButton("Load previous state");
    JButton exitButton = new JButton("Exit");
    JButton viewGraveyard = new JButton("See graveyard");
    TextField buddyNameEntry = new TextField(15);
    JLabel buddyName = new JLabel("Enter new Buddy name here: ");

    public PickBuddyPanel(CurrState currState, JPanel cardPanel) {
        setPreferredSize(new Dimension(640, 480));
        setBackground(Color.GRAY);
        this.currState = currState;
        this.cardPanel = cardPanel;
        this.cardLayout = (CardLayout) cardPanel.getLayout();
        jsonReader = new JsonReader(JSON_STORE);
        buddyName.setLabelFor(buddyNameEntry);
        this.add(buddyName);
        this.add(buddyNameEntry);
        this.addButtons();
    }

    public void addButtons() {
        this.add(createBuddy);
        createBuddy.addActionListener(this);
        this.add(loadBuddy);
        loadBuddy.addActionListener(this);
        this.add(exitButton);
        exitButton.addActionListener(this);
        this.add(viewGraveyard);
        viewGraveyard.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadBuddyAndGraveyard() {
        try {
            this.currState.setCurrBuddy(jsonReader.readBuddy());
            this.currState.setGraveyard(jsonReader.readGraveyard());
            System.out.println("Loaded " + this.currState.getCurrBuddy().getName()
                    + " and graveyard from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (JSONException e) {
            System.out.println("It looks like you don't have a current Buddy saved in: " + JSON_STORE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createBuddy) {
            this.currState.setCurrBuddy(new Buddy(buddyNameEntry.getText()));
            this.cardLayout.show(this.cardPanel, "BSP");
        }
        if (e.getSource() == loadBuddy) {
            loadBuddyAndGraveyard();
            this.cardLayout.show(this.cardPanel, "BSP");
        }
        if (e.getSource() == viewGraveyard) {
            this.cardLayout.show(cardPanel, "GP");
        }
        if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
}
