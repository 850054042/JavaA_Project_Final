package view;

import javax.swing.*;
import java.awt.*;

public class ErrorMessage extends JFrame {
    private final int WIDTH = 360;
    private final int HEIGTH = 500;
    private int result;
    public ErrorMessage(int result) {
        setTitle("Game Over"); //设置标题
        this.result = result;

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
        String str = String.format("错误编码：10%d",result);
        statusLabel.setText(str);
        statusLabel.setLocation(80, 80);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("黑体", Font.BOLD, 25));
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
            ErrorMessage.this.dispose();
            Menu menu = new Menu(1000,760);
            menu.setVisible(true);
            //System.out.println("Click Exit");
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
