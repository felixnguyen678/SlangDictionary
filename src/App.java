import Model.*;
import View.*;
import Controller.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class App {
    public static void start() throws IOException {
        HomeView homeView = new HomeView();
    }
    public static void main(String[] args) throws IOException {
        JFrame.setDefaultLookAndFeelDecorated(true);
        start();

    }
}
