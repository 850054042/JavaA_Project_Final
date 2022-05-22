/**这个是存档，如果空存档则直接覆盖，有存档要提示一下*/
package view;

import controller.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class NoteRecord extends JFrame{
    private final int WIDTH;
    private final int HEIGTH;
    private GameController gameController;
    private ChessGameFrame chessGameFrame;
    private Chessboard chessboard;
    public NoteRecord(int width, int height, ChessGameFrame chessGameFrame) {
        setTitle("记录存档"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        chessboard = chessGameFrame.getChessboard();
        this.chessGameFrame = chessGameFrame;

        setVisible(true);
        setSize(width, height);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        setBackground();
        addRecordOneButton();
        addRecordTwoButton();
        addRecordThreeButton();
        addReturnButton();
    }

    private void addRecordOneButton() {
        JButton button = new JButton("存档一");
        button.setLocation(HEIGTH/10 + 315, HEIGTH / 10 + 180);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            NoteRecord.this.dispose();
            Menu menu = new Menu(1000,760);
            menu.setVisible(true);
            //System.out.println("Click ChooseRecordOne");
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("Save1.txt"));
                writer.write(chessboard.getChessboardGraph());
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            chessGameFrame.dispose();
        });
    }

    private void addRecordTwoButton() {
        JButton button = new JButton("存档二");
        button.setLocation(HEIGTH/10 + 315, HEIGTH / 10 + 280);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            NoteRecord.this.dispose();
            Menu menu = new Menu(1000,760);
            menu.setVisible(true);
            //System.out.println("Click ChooseRecordTwo");
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("Save2.txt"));
                writer.write(chessboard.getChessboardGraph());
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            chessGameFrame.dispose();
        });
    }

    private void addRecordThreeButton() {
        JButton button = new JButton("存档三");
        button.setLocation(HEIGTH/10 + 315, HEIGTH / 10 + 380);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            NoteRecord.this.dispose();
            Menu menu = new Menu(1000,760);
            menu.setVisible(true);
            //System.out.println("Click ChooseRecordThree");
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("Save3.txt"));
                writer.write(chessboard.getChessboardGraph());
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            chessGameFrame.dispose();
        });
    }

    private void addReturnButton() {
        JButton button = new JButton("返回");
        button.setLocation(HEIGTH/10 + 315, HEIGTH / 10 + 480);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        button.setSize(200, 60);
        button.setVisible(true);
        add(button);

        button.addActionListener(e -> {
            NoteRecord.this.dispose();
            PrepareExit prepareExit = new PrepareExit(1000,760,chessGameFrame);
            prepareExit.setVisible(true);
            //System.out.println("Click Return");
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
