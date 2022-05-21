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

        setBackground();
        addNewGameButton();
        addLoadGameButton();
        addReturnButton();
    }

    private void addNewGameButton() {
        JButton button = new JButton("新游戏");
        button.setLocation(HEIGTH/10 + 315, HEIGTH / 10 + 180);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseGame.this.dispose();
                ChooseModel chooseModel = new ChooseModel(1000,760);
                chooseModel.setVisible(true);
                System.out.println("Click StartNewGame");
            }
        });
    }

    private void addLoadGameButton() {
        JButton button = new JButton("选择存档");
        button.setLocation(HEIGTH/10 + 315, HEIGTH / 10 + 280);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseGame.this.dispose();
                new ChooseRecord(1000,760);
                System.out.println("Click ChooseRecord");
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
                ChooseGame.this.dispose();
                Menu menu = new Menu(1000,760);
                menu.setVisible(true);
                System.out.println("Click Return");
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