package ui;

import model.Buddy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BuddyPanel extends JPanel {
    Buddy buddy;
//    JButton feedButton = new JButton("Feed");
//    JButton killButton = new JButton("Kill");
//    JButton saveButton = new JButton("Save");
//    JButton exitButton = new JButton("Exit");

    // Constructs a game panel
    // effects:  sets size and background colour of panel,
    //           updates this with the game to be displayed
    public BuddyPanel(Buddy buddy) {
        setPreferredSize(new Dimension(640, 480));
        setBackground(Color.GRAY);
        this.buddy = buddy;
//        this.add(feedButton);
//        this.add(killButton);
//        this.add(saveButton);
//        this.add(exitButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);

//        if (game.isOver()) {
//            gameOver(g);
//        }
    }

    // Draws the game
    // modifies: g
    // effects:  draws the game onto g
    private void drawGame(Graphics g) {
        drawBuddy(g);
        //drawInvaders(g);
        //drawMissiles(g);
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

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == feedButton) {
//            buddy.increaseFood(5);
//        } else if (e.getSource() == saveButton) {
//
//        }
//    }
}
