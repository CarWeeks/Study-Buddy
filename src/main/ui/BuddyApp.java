package ui;

import model.Buddy;
import model.Exceptions.EmptyNameException;
import java.util.Scanner;

// Buddy application
public class BuddyApp {
    private Buddy currBuddy;
    private Scanner input;

    public BuddyApp() {
        runBuddy();
    }

    private void runBuddy() {
        try {
            createCurrBuddy();
            printHappyBuddy();
            printNormBuddy();
            printSadBuddy();
            printCurrStats(currBuddy);


            while (currBuddy.isLiving()) {

            }
        } catch (EmptyNameException e) {
            System.out.println("Sorry your Buddy needs a name!");
            runBuddy();
        }
    }

    private void createCurrBuddy() throws EmptyNameException {
        System.out.println("Let's make a new Buddy!");
        input = new Scanner(System.in);
        String newName = input.next();
        currBuddy = new Buddy(newName);
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
