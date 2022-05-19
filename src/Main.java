import view.Menu;
import view.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menu = new Menu(360,760);
            menu.setVisible(true);
            //我先把加载菜单放在这里，之后是进入新游戏或存档的时候
            //直接从那里调用ChessGameFrame，这里就不用写了
            MusicPlayer musicPlayer = new MusicPlayer("D://文件//伴奏//Soledad.mp3");
            musicPlayer.start();
        });
    }
}
