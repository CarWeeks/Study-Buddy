package ui;

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

public class BuddyPanel extends JPanel implements ActionListener {
    CurrState currState;
    private static final String JSON_STORE = "./data/currentState.json";
    private JsonWriter jsonWriter;
    JPanel cardPanel;
    CardLayout cardLayout;
    JButton feedButton = new JButton("Feed");
    JButton killButton = new JButton("Kill");
    JButton saveButton = new JButton("Save");
    JButton exitButton = new JButton("Exit");
    JLabel buddyName;

    // Constructs a game panel
    // effects:  sets size and background colour of panel,
    //           updates this with the game to be displayed
    public BuddyPanel(CurrState currState, JPanel cardPanel) {
        setPreferredSize(new Dimension(640, 480));
        setBackground(Color.GRAY);
        jsonWriter = new JsonWriter(JSON_STORE);
        this.currState = currState;
        this.cardPanel = cardPanel;
        this.cardLayout = (CardLayout) cardPanel.getLayout();
        this.buddyName = new JLabel(currState.getCurrBuddy().getName());
        this.add(buddyName, BorderLayout.SOUTH);
        this.addButtons();
        try {
            BufferedImage myPicture = ImageIO.read(new File("./data/Wolf (1).jpeg"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            add(picLabel);
        } catch (IOException e) {
            System.exit(0);
        }
    }

    public void updateName() {
        buddyName.setText(currState.getCurrBuddy().getName());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //drawGame(g);
    }

    // Draws the game
    // modifies: g
    // effects:  draws the game onto g
//    private void drawGame(Graphics g) {
//        drawBuddy(g);
//    }

    // Draw the tank
    // modifies: g
    // effects:  draws the tank onto g
//    private void drawBuddy(Graphics g) {
//        try {
//            BufferedImage myPicture = ImageIO.read(new File("./data/Wolf (1).jpeg"));
//            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//            add(picLabel);
//        } catch (IOException e) {
//            System.exit(0);
//        }
//    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == feedButton) {
            currState.getCurrBuddy().increaseFood(5);
        }
        if (e.getSource() == saveButton) {
            this.saveBuddyAndGraveyard();
        }
        if (e.getSource() == exitButton) {
            System.exit(0);
        }
        if (e.getSource() == killButton) {
            this.currState.getCurrBuddy().kill();
            this.cardLayout.show(cardPanel, "AGP");
        }
    }

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
