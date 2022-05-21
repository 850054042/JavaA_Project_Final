import view.Menu;
import view.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menu = new Menu(1000,760);
            menu.setVisible(true);
            MusicPlayer musicPlayer = new MusicPlayer("D://文件//伴奏//Soledad.mp3");
            musicPlayer.start();
        });
    }
}
