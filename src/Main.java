import view.ChessGameFrame;
import view.Menu;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menu = new Menu(360,760);
            menu.setVisible(true);
            //我先把加载菜单放在这里，之后是进入新游戏或存档的时候
            //直接从那里调用ChessGameFrame，这里就不用写了
            ChessGameFrame mainFrame = new ChessGameFrame(1000, 760);
            mainFrame.setVisible(true);
        });
    }
}
