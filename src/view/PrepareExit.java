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
    private GameController gameController;
    public PrepareExit(int width, int height) {
        setTitle("Exit"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;

        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        addReturnGameButton();
        addLoadButton();
        addChangeStyleButton();
        addBackToMenuButton();
    }

    private void addReturnGameButton() { /** 这个要返回当前的棋盘*/
        JButton button = new JButton("返回游戏");
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 180);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrepareExit.this.dispose();
                new ChooseGame(360,760);
                System.out.println("Click ReturnToGame");
            }
        });
    }

    private void addLoadButton() {
        JButton button = new JButton("加载存档");
        button.setLocation(HEIGTH / 10, HEIGTH / 10 + 280);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click Load");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            gameController.loadGameFromFile(path);
        });
    }

    private void addChangeStyleButton() {
        JButton button = new JButton("改变风格");
        button.setLocation(HEIGTH / 10, HEIGTH / 10 + 380);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);

    }

    private void addBackToMenuButton() {  /** 这里可以用if else，如果没有操作则直接返回主菜单 */
        JButton button = new JButton("回到主菜单");
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 480);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        button.setSize(200, 60);
        button.setVisible(true);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrepareExit.this.dispose();
                new NoteRecord(360,760);
                System.out.println("Click BackToMenu");
            }
        });
    }



//    private void setBackground(){
//        Image background = new ImageIcon("12111801.jpg").getImage();
//        setVisible(true);
//    }


}
