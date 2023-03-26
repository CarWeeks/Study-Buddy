package ui;

import model.Buddy;
import model.Graveyard;
import org.json.JSONException;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PickBuddyPanel extends JPanel implements ActionListener {
    private static final String JSON_STORE = "./data/currentState.json";
    Buddy currBuddy;
    Graveyard graveyard;
    private JsonReader jsonReader;
    JButton createBuddy = new JButton("Make new Buddy");
    JButton loadBuddy = new JButton("Load previous state");
    JButton exitButton = new JButton("Exit");

    public PickBuddyPanel(Buddy currBuddy, Graveyard graveyard) {
        setPreferredSize(new Dimension(640, 480));
        setBackground(Color.GRAY);
        this.currBuddy = currBuddy;
        this.graveyard = graveyard;
        jsonReader = new JsonReader(JSON_STORE);
        this.addButtons();
    }

    public void addButtons() {
        this.add(createBuddy);
        createBuddy.addActionListener(this);
        this.add(loadBuddy);
        loadBuddy.addActionListener(this);
        this.add(exitButton);
        exitButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadBuddyAndGraveyard() {
        try {
            this.currBuddy = jsonReader.readBuddy();
            this.graveyard = jsonReader.readGraveyard();
            System.out.println("Loaded " + currBuddy.getName() + " and graveyard from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (JSONException e) {
            System.out.println("It looks like you don't have a current Buddy saved in: " + JSON_STORE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createBuddy) {
            //this.currBuddy = (newBuddyName.getText());
        }
        if (e.getSource() == loadBuddy) {
            //loadBuddyAndGraveyard();
            //pbp.setVisible(false);
            //bp.setVisible(true);
            //sp.setVisible(true);
            //op.setVisible(false);
        }
    }
}
