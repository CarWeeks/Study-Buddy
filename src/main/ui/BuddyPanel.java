package ui;

import model.Buddy;
import model.Graveyard;
import org.json.JSONException;
import persistence.JsonReader;
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
    Buddy buddy;
    Graveyard graveyard;
    private static final String JSON_STORE = "./data/currentState.json";
    private JsonWriter jsonWriter;
    JButton feedButton = new JButton("Feed");
    JButton killButton = new JButton("Kill");
    JButton saveButton = new JButton("Save");
    JButton exitButton = new JButton("Exit");

    // Constructs a game panel
    // effects:  sets size and background colour of panel,
    //           updates this with the game to be displayed
    public BuddyPanel(Buddy buddy, Graveyard graveyard) {
        setPreferredSize(new Dimension(640, 480));
        setBackground(Color.GRAY);
        jsonWriter = new JsonWriter(JSON_STORE);
        this.buddy = buddy;
        this.graveyard = graveyard;
        this.addButtons();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);
    }

    // Draws the game
    // modifies: g
    // effects:  draws the game onto g
    private void drawGame(Graphics g) {
        drawBuddy(g);
    }

    // Draw the tank
    // modifies: g
    // effects:  draws the tank onto g
    private void drawBuddy(Graphics g) {
        try {
            BufferedImage myPicture = ImageIO.read(new File("./data/Wolf (1).jpeg"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            add(picLabel);
        } catch (IOException e) {
            System.exit(0);
        }
    }

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
            buddy.increaseFood(5);

        }
        if (e.getSource() == saveButton) {
            this.saveBuddyAndGraveyard();
        }
//        if (e.getSource() == exitButton) {
//            System.exit(0);
//        }
        if (e.getSource() == killButton) {
            buddy.kill();
            //layout.show(cardPanel, "OP");
        }
    }

    // EFFECTS: saves the current Buddy and Graveyard to file
    private void saveBuddyAndGraveyard() {
        try {
            jsonWriter.open();
            jsonWriter.write(buddy, graveyard);
            jsonWriter.close();
            System.out.println("Saved " + buddy.getName() + " and your graveyard to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}
