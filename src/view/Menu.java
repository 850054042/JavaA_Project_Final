//菜单主界面：开始游戏/选择风格/播放音乐/退出游戏
//据说背景有点难搞，我之后补上；音乐播放器找的代码版本有问题，再重新弄一个
package view;

import controller.GameController;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.applet.AudioClip;
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

        setBackground();
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
                new ChooseGame(1000,760);
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
