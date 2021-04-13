import  Model.*;
public class App {
    public static void start(){
        // repository
        Repository repository = Repository.getInstance();
        repository.import_data("slang.txt");

    }
    public static void main(String[] args) {
        start();
    }
}
