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

        setBackground();
        addHumanBattleButton();
        addComputerBattleButton();
        addReturnButton();
    }

    private void addHumanBattleButton() {
        JButton button = new JButton("玩家VS玩家");
        button.setLocation(HEIGTH / 10 + 315, HEIGTH / 10 + 180);
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
                Chessboard.gameMode = 0;
            }
        });
    }

    private void addComputerBattleButton() {
        JButton button = new JButton("玩家VS电脑");
        button.setLocation(HEIGTH / 10 + 315, HEIGTH / 10 + 280);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseModel.this.dispose();
                ChooseLevel chooseLevel = new ChooseLevel(1000,760);
                chooseLevel.setVisible(true);
                System.out.println("Click ChooseLevel");
                Chessboard.gameMode = 1;
            }
        });
    }

    private void addReturnButton() {
        JButton button = new JButton("返回");
        button.setLocation(HEIGTH/10 + 315, HEIGTH / 10 + 380);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        button.setSize(200, 60);
        button.setVisible(true);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseModel.this.dispose();
                ChooseGame chooseGame = new ChooseGame(1000,760);
                chooseGame.setVisible(true);
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
