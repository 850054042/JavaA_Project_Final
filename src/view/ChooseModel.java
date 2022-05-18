//玩家VS玩家/玩家VS电脑，可以从前者进入对战界面
package view;

import controller.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseModel extends JFrame {
    private final int WIDTH;
    private final int HEIGTH;
    private GameController gameController;

    public ChooseModel(int width, int height) {
        setTitle("选择模式");
        this.WIDTH = width;
        this.HEIGTH = height;

        setVisible(true);
        setSize(width, height);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        addHumanBattleButton();
        addComputerBattleButton();
        addReturnButton();
    }

    private void addHumanBattleButton() {
        JButton button = new JButton("玩家VS玩家");
        button.setLocation(HEIGTH / 10, HEIGTH / 10 + 180);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseModel.this.dispose();
                ChessGameFrame chessGameFrame = new ChessGameFrame(1000,760);
                chessGameFrame.setVisible(true);
                System.out.println("Click Battle!");
            }
        });
    }

    private void addComputerBattleButton() {
        JButton button = new JButton("玩家VS电脑");
        button.setLocation(HEIGTH / 10, HEIGTH / 10 + 280);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseModel.this.dispose();
                ChooseLevel chooseLevel = new ChooseLevel(360,760);
                chooseLevel.setVisible(true);
                System.out.println("Click ChooseLevel");
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
                ChooseModel.this.dispose();
                ChooseGame chooseGame = new ChooseGame(360,760);
                chooseGame.setVisible(true);
                System.out.println("Click Return");
            }
        });
    }
}
