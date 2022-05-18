//菜单主界面：开始游戏/选择风格/播放音乐/退出游戏
//据说背景有点难搞，我之后补上；音乐播放器找的代码版本有问题，再重新弄一个
package view;

import controller.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    private GameController gameController;
    public Menu(int width, int height) {
        setTitle("Menu"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;

        setSize(width, height);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        //setBackground();
        addStartGameButton();
        addLabel();
        addChangeStyleButton();
        addPlayMusicButton();
        addExitButton();
    }

    private void addLabel() {
        JLabel statusLabel = new JLabel("国际象棋");
        statusLabel.setLocation(HEIGTH / 10 + 15, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("黑体", Font.BOLD, 40));
        add(statusLabel);
    }

    private void addStartGameButton() {
        JButton button = new JButton("开始游戏");
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 180);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.this.dispose();
                new ChooseGame(360,760);
                System.out.println("Click ChooseGame");

            }
        });
    }

    private void addChangeStyleButton() {
        JButton button = new JButton("选择风格");
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 280);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);

    }

    private void addPlayMusicButton() {
        JButton button = new JButton("播放音乐");
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 380);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new MusicTest();
                System.out.println("Click PlayMusic");

            }

        });
    }

    private void addExitButton() {
        JButton button = new JButton("退出游戏");
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 480);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        button.setSize(200, 60);
        button.setVisible(true);
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click exit");
            System.exit(1);
        });
    }



//    private void setBackground(){
//        Image background = new ImageIcon("12111801.jpg").getImage();
//        setVisible(true);
//    }


}
