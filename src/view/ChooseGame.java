//选择游戏：新游戏/选择存档/返回
package view;

import controller.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseGame extends JFrame{
    private final int WIDTH;
    private final int HEIGTH;
    private GameController gameController;
    public ChooseGame(int width, int height) {
        setTitle("选择游戏");
        this.WIDTH = width;
        this.HEIGTH = height;

        setVisible(true);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        addNewGameButton();
        addLoadGameButton();
        addReturnButton();
    }

    private void addNewGameButton() {
        JButton button = new JButton("新游戏");
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 180);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseGame.this.dispose();
                ChooseModel chooseModel = new ChooseModel(360,760);
                chooseModel.setVisible(true);
                System.out.println("Click StartNewGame");
            }
        });
    }

    private void addLoadGameButton() {
        JButton button = new JButton("选择存档");
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 280);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseGame.this.dispose();
                new ChooseRecord(360,760);
                System.out.println("Click ChooseRecord");
            }
        });
    }

    private void addReturnButton() {
        JButton button = new JButton("返回");
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 380);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        button.setSize(200, 60);
        button.setVisible(true);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseGame.this.dispose();
                Menu menu = new Menu(360,760);
                menu.setVisible(true);
                System.out.println("Click Return");
            }
        });
    }
}