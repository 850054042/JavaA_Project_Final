//悔棋还没有做出来
package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private int WIDTH;
    private int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    private Chessboard chessboard;

    public ChessGameFrame(int width, int height) {
        setTitle("Battle!"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        setBackground();
        addChessboard();
        addLabel();
        addHelloButton();
        addRetractButton();
        addRestartButton();
        addReturnButton();
    }

    public ChessGameFrame(Chessboard chessboard){
        setTitle("Battle!"); //设置标题
        this.WIDTH = 1000;
        this.HEIGTH = 760;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        setBackground();
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        this.chessboard = chessboard;
        gameController = new GameController(chessboard);
        add(chessboard);
        chessboard.setChessGameFrame(this);
        addLabel();
        addHelloButton();
        addRetractButton();
        addRestartButton();
        addReturnButton();
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    private void addChessboard() {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        this.chessboard = chessboard;
        add(chessboard);
        chessboard.setChessGameFrame(this);
    }

    private void addLabel() {
        JLabel statusLabel = new JLabel("白方回合");
        statusLabel.setLocation(HEIGTH - 20, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("宋体", Font.BOLD, 20));
        chessboard.setLabel(statusLabel);
        add(statusLabel);
    }

    private void addHelloButton() {
        JButton button = new JButton("打个招呼吧！");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "很高兴见到你!"));
        button.setLocation(HEIGTH - 25, HEIGTH / 10 + 280);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);
    }

    private void addRetractButton() {
        JButton button = new JButton("悔棋");
        button.setLocation(HEIGTH - 25, HEIGTH / 10 + 360);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            chessboard.undo();
        });
    }

    private void addRestartButton() {
        JButton button = new JButton("重新开始");
        button.setLocation(HEIGTH - 25, HEIGTH / 10 + 440);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click Restart");
            ChessGameFrame.this.dispose();
            ChessGameFrame anotherchessgameframe = new ChessGameFrame(1000,760);
            anotherchessgameframe.setVisible(true);
        });
    }

    private void addReturnButton() {
        JButton button = new JButton("退出比赛");
        button.setLocation(HEIGTH - 25, HEIGTH / 10 + 520);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click Return");
            ChessGameFrame.this.setVisible(false);
            PrepareExit prepareExit  = new PrepareExit(1000,760, this);
            prepareExit.setVisible(true);
        });
    }

    private void setBackground(){
        JPanel imPanel=(JPanel) this.getContentPane();//注意内容面板必须强转为JPanel才可以实现下面的设置透明
        imPanel.setOpaque(false);//将内容面板设为透明
        ImageIcon icon=Menu.icon1;//背景图
        JLabel label = new JLabel(icon);//往一个标签中加入图片
        label.setBounds(0, 0, WIDTH, HEIGTH);//设置标签位置大小，记得大小要和窗口一样大
        icon.setImage(icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));//图片自适应窗口大小
        getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
    }
}
