import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Client c = new Client("127.0.0.1", 1690);
        Executioner e = new Executioner(c);
        boolean a = e.sync("2555", "Blah");
        System.out.println("a is =" + a);
        if (a) {
            e.Execute(Actions.PAUSE_PLAY_TOGGLE);
        }

    }
} 
