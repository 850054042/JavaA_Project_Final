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
        setTitle("Choose Level");
        this.WIDTH = width;
        this.HEIGTH = height;

        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

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
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Coming soon!"));
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 180);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);
    }

    private void addNormalButton() {
        JButton button = new JButton("一般");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Coming soon!"));
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 280);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);
    }

    private void addHardButton() {
        JButton button = new JButton("困难");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Coming soon!"));
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 380);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);
    }


    private void addReturnButton() {
        JButton button = new JButton("返回");
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 480);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        button.setSize(200, 60);
        button.setVisible(true);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseLevel.this.dispose();
                new ChooseGame(360,760);
                System.out.println("Click Return");
            }
        });
    }

        /*private void addRestartButton() {
            JButton button = new JButton("Restart");
            button.setLocation(HEIGTH/10, HEIGTH / 10 + 360);
            button.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
            button.setSize(200, 60);
            button.setVisible(true);
            add(button);

            button.addActionListener(e -> {
                System.out.println("Click restart");
            });
        }*/
}
