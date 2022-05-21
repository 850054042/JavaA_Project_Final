//在新游戏中选择难度：简单/一般/困难/返回
package view;

import controller.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseLevel extends JFrame {
    private final int WIDTH;
    private final int HEIGTH;
    private GameController gameController;
    public ChooseLevel(int width, int height) {
        setTitle("选择难度");
        this.WIDTH = width;
        this.HEIGTH = height;

        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        setBackground();
        addLabel();
        addEasyButton();
        addNormalButton();
        addHardButton();
        addReturnButton();
    }

    private void addLabel() {
        JLabel statusLabel = new JLabel("选择难度");
        statusLabel.setLocation(HEIGTH / 10 + 20, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("黑体", Font.BOLD, 40));
        add(statusLabel);
    }

    private void addEasyButton() {
        JButton button = new JButton("简单");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseLevel.this.dispose();
                ChessGameFrame chessGameFrame = new ChessGameFrame(1000,760);
                chessGameFrame.setVisible(true);
                System.out.println("Easy Mode!");
                Chessboard.AILevel = 1;
            }
        });
        button.setLocation(HEIGTH/10 + 315, HEIGTH / 10 + 180);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);
    }

    private void addNormalButton() {
        JButton button = new JButton("一般");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseLevel.this.dispose();
                ChessGameFrame chessGameFrame = new ChessGameFrame(1000,760);
                chessGameFrame.setVisible(true);
                System.out.println("Normal Mode!");
                Chessboard.AILevel = 2;
            }
        });
        button.setLocation(HEIGTH/10 + 315, HEIGTH / 10 + 280);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);
    }

    private void addHardButton() {
        JButton button = new JButton("困难");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseLevel.this.dispose();
                ChessGameFrame chessGameFrame = new ChessGameFrame(1000,760);
                chessGameFrame.setVisible(true);
                System.out.println("Hard Mode!");
                Chessboard.AILevel = 3;
            }
        });
        button.setLocation(HEIGTH/10 + 315, HEIGTH / 10 + 380);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);
    }


    private void addReturnButton() {
        JButton button = new JButton("返回");
        button.setLocation(HEIGTH/10 + 315, HEIGTH / 10 + 480);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        button.setSize(200, 60);
        button.setVisible(true);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseLevel.this.dispose();
                new ChooseModel(360,760);
                System.out.println("Click Return");
            }
        });
    }

    private void setBackground(){
        JPanel imPanel=(JPanel) this.getContentPane();//注意内容面板必须强转为JPanel才可以实现下面的设置透明
        imPanel.setOpaque(false);//将内容面板设为透明
        ImageIcon icon=new ImageIcon("./images/1530971282b420d77bdfb6444d854f952fe31f0d1e.jpeg");//背景图
        JLabel label = new JLabel(icon);//往一个标签中加入图片
        label.setBounds(0, 0, WIDTH, HEIGTH);//设置标签位置大小，记得大小要和窗口一样大
        icon.setImage(icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));//图片自适应窗口大小
        getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
    }
}
