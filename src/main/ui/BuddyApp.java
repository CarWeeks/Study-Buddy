package ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.Buddy;
import model.Graveyard;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Buddy application runs the current Buddy and Graveyard
public class BuddyApp {
    private static final String JSON_STORE = "./data/currentState.json";
    private Buddy currBuddy;
    private Graveyard graveyard;
    private Scanner input;
    private Scanner answer;
    private Screen screen;
    private WindowBasedTextGUI endGui;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: starts running the program
    public BuddyApp() throws IOException, InterruptedException {
        this.graveyard = new Graveyard();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        checkLoad();
        printOpening();
        runBuddy();
    }

    private void printOpening() {
        System.out.println("\u001B[35mWelcome to the Study Buddy Application!\u001B[0m");
        System.out.println("In this application you will be able to name your Buddy and help care for it.");
        System.out.println("Your Buddy will get hungry fast, so be sure to feed it regularly!");
        System.out.println("While your Buddy is alive you will be able to enter the following commands:");
        System.out.println("\u001B[33m"
                + "f: to feed your Buddy\nk: to kill your Buddy\ng: to view your graveyard in the console\ns: to save"
                + "your current Buddy and graveyard"
                + "\u001B[0m");
        System.out.println("Enjoy, and don't let your Buddy die!");
    }

    // EFFECTS: begins the program by starting the screen and begins ticking
    private void runBuddy() throws IOException, InterruptedException {
        createCurrBuddy();
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        beginTicks();
    }

    // ticks the Buddy until the window is inactive or the Buddy dies
    private void beginTicks() throws IOException, InterruptedException {
        while (currBuddy.isLiving() || endGui.getActiveWindow() != null) {
            tick();
            Thread.sleep(1000L / Buddy.TICKS_PER_SECOND);
        }

        System.exit(0);
    }

    // MODIFIES: this
    // EFFECTS: updates the Buddy's statistics based on what they currently are and refreshes the output
    private void tick() throws IOException, InterruptedException {
        handleUserInput();

        this.currBuddy.updateStats();

        this.screen.setCursorPosition(new TerminalPosition(0, 0));
        this.screen.clear();
        printScreen();
        this.screen.refresh();

        this.screen.setCursorPosition(new TerminalPosition(screen.getTerminalSize().getColumns() - 1, 0));
    }

    // MODIFIES: this
    // EFFECTS: changes the buddy or prints the graveyard according to the key pressed
    private void handleUserInput() throws IOException {
        KeyStroke stroke = screen.pollInput();

        if (stroke == null) {
            return;
        }

        if (stroke.getKeyType() != KeyType.Character) {
            return;
        }

        if (stroke.getCharacter() == 'f') {
            currBuddy.increaseFood(5);
        }

        if (stroke.getCharacter() == 'k') {
            currBuddy.kill();
        }

        if (stroke.getCharacter() == 'g') {
            printGraveyard();
        }

        if (stroke.getCharacter() == 's') {
            saveBuddyAndGraveyard();
        }
    }

    // EFFECTS: prints each Buddy's name in the graveyard and how long they lived
    private void printGraveyard() {
        System.out.println("\u001B[31mBuddy Graveyard:\u001B[0m");
        for (int i = 0; i < this.graveyard.getLength(); i++) {
            Buddy b = this.graveyard.getBuddy(i);
            System.out.println("Buddy: " + b.getName() + "\n\tTime alive: "
                    + Long.toString(b.getTimeAlive()) + " seconds");
        }
    }

    // EFFECTS: prints the screen
    private void printScreen() throws IOException, InterruptedException {
        if (!currBuddy.isLiving()) {
            if (endGui == null) {
                drawEndScreen();
                endGui = null;
                buryBuddy();
                runBuddy();
            }

            return;
        }

        drawStatus();
        drawBuddy();
        drawBuddyName();
    }

    // MODIFIES: this
    // EFFECTS: asks user if they want to add their Buddy to the graveyard, adds Buddy is yes
    private void buryBuddy() {
        System.out.println("Would you like to bury your Buddy in the graveyard?");
        String result = "";

        while (!(result.equals("y") || result.equals("n"))) {
            System.out.println("Enter one of the following to proceed");
            System.out.println("\u001B[33m" + "y for yes");
            System.out.println("n for no" + "\u001B[0m");
            answer = new Scanner(System.in);
            result = answer.next();
            result = result.toLowerCase();
        }

        if (result.equals("y")) {
            this.graveyard.addBuddy(currBuddy);
        }
    }

    // REQUIRES: inputted name must not contain spaces
    // MODIFIES: this
    // EFFECTS: creates a new Buddy object based on user input
    private void createCurrBuddy() {
        System.out.println("Let's make a new Buddy!\nEnter your new Buddy's name:");
        this.input = new Scanner(System.in);
        String newName = input.next();
        this.currBuddy = new Buddy(newName);
    }

    private void checkLoad() {
        System.out.println("Would you like to load in your saved Buddy and graveyard?");
        String result = "";

        while (!(result.equals("y") || result.equals("n"))) {
            System.out.println("Enter one of the following to proceed");
            System.out.println("\u001B[33m" + "y for yes");
            System.out.println("n for no" + "\u001B[0m");
            answer = new Scanner(System.in);
            result = answer.next();
            result = result.toLowerCase();
        }

        if (result.equals("y")) {
            loadBuddyAndGraveyard();
        }
    }

    // MODIFIES: this
    // EFFECTS: prints end screen once Buddy dies to tell user their Buddy died
    private void drawEndScreen() {
        this.endGui = new MultiWindowTextGUI(screen);

        new MessageDialogBuilder()
                .setTitle("Your Buddy died!")
                .setText("Go back to the console to decide to bury your Buddy in the graveyard.")
                .addButton(MessageDialogButton.Close)
                .build()
                .showDialog(endGui);
    }

    // EFFECTS: prints current Buddy and current stats
    private void drawStatus() {
        drawHealth();
        drawFood();
        drawEnergy();
        drawHappiness();
    }

    // EFFECTS: prints current Buddy's health in top left of screen
    private void drawHealth() {
        TextGraphics healthText = screen.newTextGraphics();
        String healthString = "Health: " + Integer.toString(currBuddy.getHealth());
        healthText.setForegroundColor(TextColor.ANSI.GREEN);
        healthText.putString(1, 1, healthString);
    }

    // EFFECTS: prints current Buddy's food in top right of screen
    private void drawFood() {
        TextGraphics foodText = screen.newTextGraphics();
        String foodString = "Food: " + Integer.toString(currBuddy.getFood());
        foodText.setForegroundColor(TextColor.ANSI.GREEN);
        foodText.putString(68, 1, foodString);
    }

    // EFFECTS: prints current Buddy's energy in bottom left of screen
    private void drawEnergy() {
        TextGraphics energyText = screen.newTextGraphics();
        String energyString = "Energy: " + Integer.toString(currBuddy.getEnergy());
        energyText.setForegroundColor(TextColor.ANSI.GREEN);
        energyText.putString(1, 22, energyString);
    }

    // EFFECTS: prints current Buddy's happiness in bottom right of screen
    private void drawHappiness() {
        TextGraphics happinessText = screen.newTextGraphics();
        String happinessString = "Happiness: " + Integer.toString(currBuddy.getHappiness());
        happinessText.setForegroundColor(TextColor.ANSI.GREEN);
        happinessText.putString(63, 22, happinessString);
    }

    // MODIFIES: this
    // EFFECTS: prints picture of your Buddy in the middle of the screen in green
    private void drawBuddy() {
        TextGraphics bodyText = this.screen.newTextGraphics();
        bodyText.setForegroundColor(TextColor.ANSI.GREEN);
        bodyText.putString(30, 7, "     _________");
        bodyText.putString(30, 8, "    /         |");
        if (this.currBuddy.getHappiness() > Buddy.MAX_BAR / 2) {
            bodyText.putString(30, 9, "   / ^    ^   |");
        } else {
            bodyText.putString(30, 9, "   / '    '   |");
        }
        if (this.currBuddy.getFood() % 50 == 0) {
            bodyText.putString(30, 10, "  /  -    -   |");
        } else {
            bodyText.putString(30, 10, "  /  0    0   |");
        }
        if (this.currBuddy.getHappiness() > Buddy.MAX_BAR / 2) {
            bodyText.putString(30, 11, "  |     U     |");
        } else {
            bodyText.putString(30, 11, "  |     ^     |");
        }
        bodyText.putString(30, 12, "  ------------");
        bodyText.putString(30, 13, "  ||         ||");
        bodyText.putString(30, 14, "  —           —");
    }

    // EFFECTS: draws Buddy's name below Buddy's picture on the screen in blue
    private void drawBuddyName() {
        TextGraphics nameText = this.screen.newTextGraphics();
        nameText.setForegroundColor(TextColor.ANSI.BLUE);
        nameText.putString(36, 17, this.currBuddy.getName());
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
        }
    }
}


