package ui;

import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import model.Buddy;
import model.Graveyard;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.Timer;

public class BuddyMain extends JFrame {

    private static final int INTERVAL = 10;
    private static final String JSON_STORE = "./data/currentState.json";
    private Buddy currBuddy;
    private Graveyard graveyard;
    private Scanner input;
    private Scanner answer;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    boolean loaded;
}
