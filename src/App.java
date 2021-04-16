import  Model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class App {
    public static void start() throws IOException {
        // repository
        Repository repository = Repository.getInstance();
        repository.reset("slang.txt");
        System.out.println(repository.randomSlang().getSlang());



    }
    public static void main(String[] args) throws IOException {
        start();

    }
}
