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
import model.Exceptions.EmptyNameException;

import java.io.IOException;
import java.util.Scanner;

// Buddy application
public class BuddyApp {
    private Buddy currBuddy;
    private Scanner input;
    private Screen screen;
    private WindowBasedTextGUI endGui;

    public BuddyApp() throws EmptyNameException, IOException, InterruptedException {
        runBuddy();
    }

    private void runBuddy() throws IOException, EmptyNameException, InterruptedException {
        createCurrBuddy();
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        beginTicks();
    }

    private void beginTicks() throws IOException, InterruptedException {
        while (currBuddy.isLiving() || endGui.getActiveWindow() != null) {
            tick();
            Thread.sleep(1000L / Buddy.TICKS_PER_SECOND);
        }

        System.exit(0);
    }

    private void tick() throws IOException {
        handleUserInput();

        currBuddy.updateStats();

        screen.setCursorPosition(new TerminalPosition(0, 0));
        screen.clear();
        printScreen();
        screen.refresh();

        screen.setCursorPosition(new TerminalPosition(screen.getTerminalSize().getColumns() - 1, 0));
    }

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
    }

    private void printScreen() {
        if (!currBuddy.isLiving()) {
            if (endGui == null) {
                drawEndScreen();
            }

            return;
        }

        drawStatus();
        //drawBuddy();
    }

    private void createCurrBuddy() throws EmptyNameException {
        System.out.println("Let's make a new Buddy!");
        input = new Scanner(System.in);
        String newName = input.next();
        currBuddy = new Buddy(newName);
    }

    private void drawEndScreen() {
        endGui = new MultiWindowTextGUI(screen);

        new MessageDialogBuilder()
                .setTitle("Your Buddy died!")
                .setText("You can view your buddy in the graveyard")
                .addButton(MessageDialogButton.Close)
                .build()
                .showDialog(endGui);
    }

    private void drawStatus() {
        drawHealth();
        drawFood();
        drawEnergy();
        drawHappiness();
    }

    private void drawHealth() {
        TextGraphics healthText = screen.newTextGraphics();
        String healthString = "Health: " + Integer.toString(currBuddy.getHealth());
        healthText.setForegroundColor(TextColor.ANSI.GREEN);
        healthText.putString(1, 1, healthString);
    }

    private void drawFood() {
        TextGraphics foodText = screen.newTextGraphics();
        String foodString = "Food: " + Integer.toString(currBuddy.getFood());
        foodText.setForegroundColor(TextColor.ANSI.GREEN);
        foodText.putString(68, 1, foodString);
    }

    private void drawEnergy() {
        TextGraphics energyText = screen.newTextGraphics();
        String energyString = "Energy: " + Integer.toString(currBuddy.getEnergy());
        energyText.setForegroundColor(TextColor.ANSI.GREEN);
        energyText.putString(1, 22, energyString);
    }

    private void drawHappiness() {
        TextGraphics happinessText = screen.newTextGraphics();
        String happinessString = "Happiness: " + Integer.toString(currBuddy.getHappiness());
        happinessText.setForegroundColor(TextColor.ANSI.GREEN);
        happinessText.putString(63, 22, happinessString);
    }

    private void printHappyBuddy() {
        System.out.println("    __________");
        System.out.println("   / ^    ^   | ");
        System.out.println("  /  0    0   | ");
        System.out.println("  |     U     |");
        System.out.println("  ------------");
        System.out.println("  ||         ||");
        System.out.println("  —           —");
    }

    private void printSadBuddy() {
        System.out.println("    __________");
        System.out.println("   / ,    ,   | ");
        System.out.println("  /  0    0   | ");
        System.out.println("  |     ^     |");
        System.out.println("  ------------");
        System.out.println("  ||         ||");
        System.out.println("  —           —");
    }

    private void printNormBuddy() {
        System.out.println("    __________");
        System.out.println("   / —    —   | ");
        System.out.println("  /  0    0   | ");
        System.out.println("  |     -     |");
        System.out.println("  ------------");
        System.out.println("  ||         ||");
        System.out.println("  —           —");
    }

    private void printCurrStats(Buddy b) {
        System.out.println("Current statistics for " + b.getName());
        System.out.println("Health: " + b.getHealth());
        System.out.println("Food: " + b.getFood());
        System.out.println("Energy: " + b.getEnergy());
        System.out.println("Happiness: " + b.getHappiness());
    }
}


