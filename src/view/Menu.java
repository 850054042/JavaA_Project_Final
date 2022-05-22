//菜单主界面：开始游戏/选择风格/播放音乐/退出游戏
//据说背景有点难搞，我之后补上；音乐播放器找的代码版本有问题，再重新弄一个
package view;

import controller.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    private JLabel label;
    private GameController gameController;
    JButton button1 = new JButton("开始游戏");
    JButton button2 = new JButton("切换风格");
    JButton button3 = new JButton("退出游戏");
    public Menu(int width, int height) {
        setTitle("Menu"); //设置标题
        this.label = new JLabel();
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
        addExitButton();
    }

    private void addLabel() {
        JLabel statusLabel = new JLabel("国际象棋");
        statusLabel.setLocation(HEIGTH / 10 + 330, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("黑体", Font.BOLD, 40));
        add(statusLabel);
    }

    private void addStartGameButton() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.this.dispose();
                new ChooseGame(1000,760);
                System.out.println("Click ChooseGame");
            }
        });
    }

    private void addChangeStyleButton() {
        button2.addActionListener(e -> {
            System.out.println("mouseListener");
            path = resetPath(path);
            setBackground();
            repaint();
        });
    }

    private void addExitButton() {
        button3.addActionListener(e -> {
            System.out.println("Click exit");
            System.exit(1);
        });
    }

    private String resetPath(String oldPath){
        if (oldPath.equals("./images/2.png")) return "./images/1.jpeg";
        else return "./images/2.png";
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        setLocationRelativeTo(null);
        setSize(1000,760);
        button1.setLocation(HEIGTH/10 + 315, HEIGTH / 10 + 180);
        button1.setSize(200, 60);
        button1.setFont(new Font("黑体", Font.BOLD, 20));
        add(button1);

        button2.setLocation(HEIGTH/10 + 315, HEIGTH / 10 + 280);
        button2.setSize(200, 60);
        button2.setFont(new Font("黑体", Font.BOLD, 20));
        add(button2);

        button3.setLocation(HEIGTH/10 + 315, HEIGTH / 10 + 380);
        button3.setFont(new Font("黑体", Font.BOLD, 20));
        button3.setSize(200, 60);
        button3.setVisible(true);
        add(button3);
    }

    public static String path = "./images/2.png";
    public static ImageIcon icon1;

    private void setBackground(){
        System.out.println("setBackground");
        JPanel imPanel=(JPanel) this.getContentPane();//注意内容面板必须强转为JPanel才可以实现下面的设置透明
        imPanel.setOpaque(false);//将内容面板设为透明
        icon1=new ImageIcon(path);//背景图
        label.setIcon(icon1);//往一个标签中加入图片
        label.setBounds(0, 0, WIDTH, HEIGTH);//设置标签位置大小，记得大小要和窗口一样大
        icon1.setImage(icon1.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));//图片自适应窗口大小
        getLayeredPane().add(label,Integer.MIN_VALUE);
    }
}
