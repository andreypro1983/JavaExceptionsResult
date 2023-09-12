package App;

import App.model.FileHandler;
import App.model.Service;
import App.ui.Console;

public class App {
    public static void main(String[] args) {
        Console console = new Console();
        FileHandler fileHandler = new FileHandler();
        Service service = new Service(fileHandler, console);
        console.start();
    }
    
}
