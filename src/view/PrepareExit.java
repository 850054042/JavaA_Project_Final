//这个是从游戏中退出的界面，要跟Menu和存档结合起来
//返回游戏，到当前游戏状态还没有做出来
//存档，还没有做出来
package view;

import controller.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrepareExit extends JFrame {
    private final int WIDTH;
    private final int HEIGTH;
    ChessGameFrame chessGameFrame;
    private GameController gameController;
    public PrepareExit(int width, int height, ChessGameFrame chessGameFrame) {
        setTitle("Exit"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.chessGameFrame = chessGameFrame;

        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        setBackground();
        addReturnGameButton();
        addRecordButton();
        addBackToMenuButton();
    }

    private void addReturnGameButton() { /** 这个要返回当前的棋盘*/
        JButton button = new JButton("返回游戏");
        button.setLocation(HEIGTH/10 + 315, HEIGTH / 10 + 180);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrepareExit.this.dispose();
                System.out.println("Click ReturnToGame");
                chessGameFrame.setVisible(true);
            }
        });
    }

    private void addRecordButton() {
        JButton button = new JButton("记录存档");
        button.setLocation(HEIGTH/10 + 315, HEIGTH / 10 + 280);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        button.setSize(200, 60);
        button.setVisible(true);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrepareExit.this.dispose();
                new NoteRecord(1000,760,chessGameFrame);
                chessGameFrame.dispose();
                System.out.println("Click SetRecord");
            }
        });
    }

    private void addBackToMenuButton() {
        JButton button = new JButton("返回主菜单");
        button.setLocation(HEIGTH/10 + 315, HEIGTH / 10 + 380);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        button.setSize(200, 60);
        button.setVisible(true);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrepareExit.this.dispose();
                Menu menu = new Menu(1000,760);
                menu.setVisible(true);
                chessGameFrame.dispose();
                System.out.println("Click Exit");
            }
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
