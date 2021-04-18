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
        Repository repository = Repository.getInstance();

        System.out.println(repository.findBySlang(new Slang("BHD")).getMeanings());;
        HomeView homeView = new HomeView();


    }
    public static void main(String[] args) throws IOException {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        //JFrame.setDefaultLookAndFeelDecorated(true);
        start();

    }
}
