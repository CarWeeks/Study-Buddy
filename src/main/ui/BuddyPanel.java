package ui;

import model.Buddy;
import model.Event;
import model.EventLog;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// creates panel to show and interact with current Buddy
public class BuddyPanel extends JPanel implements ActionListener {

    CurrState currState;
    private static final String JSON_STORE = "./data/currentState.json";
    private JsonWriter jsonWriter;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    JButton feedButton = new JButton("Feed");
    JButton killButton = new JButton("Kill");
    JButton saveButton = new JButton("Save");
    JButton exitButton = new JButton("Exit");
    JLabel buddyName;
    JLabel happyBuddy;
    JLabel sadBuddy;

    // EFFECTS: creates panel with current Buddy images
    public BuddyPanel(CurrState currState, JPanel cardPanel) {
        setPreferredSize(new Dimension(800, 480));
        setBackground(new Color(69, 142, 175, 255));
        jsonWriter = new JsonWriter(JSON_STORE);
        this.currState = currState;
        this.cardPanel = cardPanel;
        this.cardLayout = (CardLayout) cardPanel.getLayout();
        this.buddyName = new JLabel(currState.getCurrBuddy().getName());
        this.add(buddyName, BorderLayout.SOUTH);
        this.addButtons();
        try {
            BufferedImage myPicture1 = ImageIO.read(new File("./data/Dog copy 2.png"));
            BufferedImage myPicture2 = ImageIO.read(new File("./data/Dog sad copy 2.png"));
            happyBuddy = new JLabel(new ImageIcon(myPicture1));
            sadBuddy = new JLabel(new ImageIcon(myPicture2));
            add(happyBuddy);
            add(sadBuddy);
            sadBuddy.setVisible(false);
        } catch (IOException e) {
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates name being shown on panel
    public void updateName() {
        buddyName.setText(currState.getCurrBuddy().getName());
    }

    // MODIFIES: this
    // EFFECTS: paints happy or sad image of Buddy on screen
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.currState.getCurrBuddy().getFood() < Buddy.MAX_BAR / 2) {
            this.happyBuddy.setVisible(false);
            this.sadBuddy.setVisible(true);
        } else {
            this.happyBuddy.setVisible(true);
            this.sadBuddy.setVisible(false);
        }
    }

    // MODIFIES: this, feedButton, killButton, saveButton, exitButton
    // EFFECTS: creates necessary buttons and gives them actionListeners
    public void addButtons() {
        this.add(feedButton, BoxLayout.X_AXIS);
        feedButton.addActionListener(this);
        this.add(killButton, BoxLayout.X_AXIS);
        killButton.addActionListener(this);
        this.add(saveButton, BoxLayout.X_AXIS);
        saveButton.addActionListener(this);
        this.add(exitButton, BoxLayout.X_AXIS);
        exitButton.addActionListener(this);
    }

    // MODIFIES: this, currState, cardLayout
    // EFFECTS: reads button input and acts according to which button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == feedButton) {
            currState.getCurrBuddy().increaseFood(5);
        }
        if (e.getSource() == saveButton) {
            this.saveBuddyAndGraveyard();
        }
        if (e.getSource() == exitButton) {
            for (Event event : EventLog.getInstance()) {
                System.out.println(event.getDate() + ": " + event.getDescription());
            }
            System.exit(0);
        }
        if (e.getSource() == killButton) {
            this.currState.setIdle(true);
            this.currState.getCurrBuddy().kill();
            this.cardLayout.show(cardPanel, "AGP");
        }
    }

    // MODIFIES: jsonWriter
    // EFFECTS: saves the current Buddy and Graveyard to file
    private void saveBuddyAndGraveyard() {
        try {
            jsonWriter.open();
            jsonWriter.write(currState.getCurrBuddy(), currState.getGraveyard());
            jsonWriter.close();
            System.out.println("Saved " + currState.getCurrBuddy().getName() + " and your graveyard to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}
