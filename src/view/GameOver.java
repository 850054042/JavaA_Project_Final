package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JFrame {
    private final int WIDTH = 360;
    private final int HEIGTH = 500;
    ChessGameFrame chessGameFrame;
    private int result;
    public GameOver(ChessGameFrame chessGameFrame, int result) {
        setTitle("Game Over"); //设置标题
        this.result = result;
        this.chessGameFrame = chessGameFrame;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        setBackground();
        addLabel();
        addBackToMenuButton();
    }

    private void addLabel() {
        JLabel statusLabel = new JLabel("");
        if(result == 1)
            statusLabel.setText("白方胜利");
        else
            statusLabel.setText("黑方胜利");
        statusLabel.setLocation(100, 80);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("黑体", Font.BOLD, 40));
        add(statusLabel);
    }


    private void addBackToMenuButton() {
        JButton button = new JButton("返回主菜单");
        button.setLocation(80, 280);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        button.setSize(200, 60);
        button.setVisible(true);
        add(button);

        button.addActionListener(e -> {
            GameOver.this.dispose();
            chessGameFrame.dispose();
            Menu menu = new Menu(1000,760);
            menu.setVisible(true);
            System.out.println("Click Exit");
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
