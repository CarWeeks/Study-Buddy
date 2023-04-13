package ui;

import model.Buddy;
import model.EventLog;
import model.Event;
import org.json.JSONException;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// creates panel for choosing a Buddy or viewing graveyard
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

    // EFFECTS: creates a pick Buddy panel
    public PickBuddyPanel(CurrState currState, JPanel cardPanel) {
        setPreferredSize(new Dimension(640, 480));
        setBackground(new Color(69, 142, 175, 255));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.buddyNameEntry.setMaximumSize(new Dimension(200, 30));
        styleButtons();
        this.currState = currState;
        this.cardPanel = cardPanel;
        this.cardLayout = (CardLayout) cardPanel.getLayout();
        jsonReader = new JsonReader(JSON_STORE);
        buddyName.setLabelFor(buddyNameEntry);
        this.add(buddyName);
        this.add(buddyNameEntry);
        this.addButtons();
    }

    // MODIFIES: this
    // EFFECTS: aligns and styles buttons shown on the panel
    private void styleButtons() {
        this.buddyName.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.buddyName.setFont(new Font("Papyrus", Font.BOLD, 14));
        this.createBuddy.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.loadBuddy.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.viewGraveyard.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // MODIFIES: this, createBuddy, loadBuddy, exitButton
    // EFFECTS: adds buttons to panel
    public void addButtons() {
        this.add(createBuddy);
        createBuddy.addActionListener(this);
        this.add(loadBuddy);
        loadBuddy.addActionListener(this);
        this.add(viewGraveyard);
        viewGraveyard.addActionListener(this);
        this.add(exitButton);
        exitButton.addActionListener(this);
    }

    // MODIFIES: currState
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

    // MODIFIES: this, currState, cardLayout
    // EFFECTS: reads button input and acts according to which button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createBuddy) {
            this.currState.setIdle(false);
            this.currState.setCurrBuddy(new Buddy(buddyNameEntry.getText()));
            this.cardLayout.show(this.cardPanel, "BSP");
        }
        if (e.getSource() == loadBuddy) {
            this.currState.setIdle(false);
            loadBuddyAndGraveyard();
            this.cardLayout.show(this.cardPanel, "BSP");
        }
        if (e.getSource() == viewGraveyard) {
            this.cardLayout.show(cardPanel, "GP");
        }
        if (e.getSource() == exitButton) {
            for (Event event : EventLog.getInstance()) {
                System.out.println(event.getDate() + ": " + event.getDescription());
            }
            System.exit(0);
        }
    }
}
