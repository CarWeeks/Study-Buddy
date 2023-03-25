package ui;

import model.Buddy;
import model.Graveyard;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;

public class BuddyMain extends JFrame implements ActionListener {

    private static final int INTERVAL = 100;
    private static final String JSON_STORE = "./data/currentState.json";
    private StatsPanel sp;
    private BuddyPanel bp;
    private OptionsPanel op;
    private PickBuddyPanel pbp;
    private Buddy currBuddy;
    private Graveyard graveyard;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    JButton feedButton = new JButton("Feed");
    JButton killButton = new JButton("Kill");
    JButton saveButton = new JButton("Save");
    JButton exitButton1 = new JButton("Exit");
    JButton exitButton2 = new JButton("Exit");
    JButton newBuddyButton = new JButton("Choose a buddy");
    JButton createBuddy = new JButton("Create Buddy");
    JButton loadBuddy = new JButton("Load previous save");
    TextField newBuddyName = new TextField("Enter your new Buddy's name: ");


    public BuddyMain() {
        super("Buddy");
        this.setPreferredSize(new Dimension(1000, 550));
        currBuddy = new Buddy("Bob");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        this.graveyard = new Graveyard();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.sp = new StatsPanel(currBuddy);
        this.bp = new BuddyPanel(currBuddy);
        bp.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 3));
        this.op = new OptionsPanel();
        this.pbp = new PickBuddyPanel();
        add(sp, BorderLayout.SOUTH);
        add(bp, FlowLayout.LEFT);
        add(op);
        //add(pbp);
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
        this.addButtons();
        this.addTextFields();

    }

    private void addTextFields() {
        pbp.add(newBuddyName);
    }

    private void addButtons() {
        bp.add(feedButton, BoxLayout.X_AXIS);
        feedButton.addActionListener(this);
        bp.add(killButton, BoxLayout.X_AXIS);
        killButton.addActionListener(this);
        bp.add(saveButton, BoxLayout.X_AXIS);
        saveButton.addActionListener(this);
        bp.add(exitButton1, BoxLayout.X_AXIS);
        exitButton1.addActionListener(this);

        op.add(newBuddyButton);
        newBuddyButton.addActionListener(this);
        op.add(exitButton2);
        exitButton2.addActionListener(this);

        pbp.add(newBuddyButton);
        newBuddyButton.addActionListener(this);
        pbp.add(createBuddy);
        createBuddy.addActionListener(this);
        pbp.add(loadBuddy);
        loadBuddy.addActionListener(this);
    }

    // REQUIRES: inputted name must not contain spaces
    // MODIFIES: this
    // EFFECTS: creates a new Buddy object based on user input
    private void createCurrBuddy(String buddyName) {
        this.currBuddy = new Buddy(buddyName);
    }

    private void printOpening() {
        System.out.println("\u001B[35mWelcome to the Study Buddy Application!\u001B[0m");
        System.out.println("In this application you will be able to name your Buddy and help care for it.");
        System.out.println("Your Buddy will get hungry fast, so be sure to feed it regularly!");
        System.out.println("While your Buddy is alive you will be able to enter the following commands:");
        System.out.println("\u001B[33m"
                + "f: to feed your Buddy\nk: to kill your Buddy\ng: to view your graveyard in the console\ns: to save"
                + " your current Buddy and graveyard"
                + "\u001B[0m");
        System.out.println("Enjoy, and don't let your Buddy die!");
    }

    // EFFECTS: saves the current Buddy and Graveyard to file
    private void saveBuddyAndGraveyard() {
        try {
            jsonWriter.open();
            jsonWriter.write(currBuddy, graveyard);
            jsonWriter.close();
            System.out.println("Saved " + currBuddy.getName() + " and your graveyard to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
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

    // Set up timer
    // modifies: none
    // effects:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!currBuddy.isLiving()) {
                    op.repaint();
                } else {
                    currBuddy.updateStats();
                    bp.repaint();
                    sp.update();
                }
                //op.repaint();
                //pbp.repaint();
            }
        });

        t.start();
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == feedButton) {
            currBuddy.increaseFood(5);
        }
        if (e.getSource() == saveButton) {
            this.saveBuddyAndGraveyard();
        }
        if (e.getSource() == exitButton1 || e.getSource() == exitButton2) {
            System.exit(0);
        }
        if (e.getSource() == killButton) {
            currBuddy.kill();
        }
        if (e.getSource() == newBuddyButton) {
            pbp.setVisible(true);
        }
        if (e.getSource() == createBuddy) {
            createCurrBuddy(newBuddyName.getText());
            pbp.setVisible(false);
            bp.setVisible(true);
            sp.setVisible(true);
        }
        if (e.getSource() == loadBuddy) {
            loadBuddyAndGraveyard();
            pbp.setVisible(false);
            bp.setVisible(true);
            sp.setVisible(true);
            op.setVisible(false);
        }
    }
}