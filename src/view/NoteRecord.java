/**这个是存档，如果空存档则直接覆盖，有存档要提示一下*/
package view;

import controller.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteRecord extends JFrame{
    private final int WIDTH;
    private final int HEIGTH;
    private GameController gameController;
    public NoteRecord(int width, int height) {
        setTitle("记录存档"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;

        setVisible(true);
        setSize(width, height);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addRecordOneButton();
        addRecordTwoButton();
        addRecordThreeButton();
        addReturnButton();
    }

    private void addRecordOneButton() {
        JButton button = new JButton("存档一");
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 180);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NoteRecord.this.dispose();
                //new ChooseRecord(360,760);
                System.out.println("Click ChooseRecordOne");
            }
        });
    }

    private void addRecordTwoButton() {
        JButton button = new JButton("存档二");
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 280);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NoteRecord.this.dispose();
                //new ChooseRecord(360,760);
                System.out.println("Click ChooseRecordTwo");
            }
        });
    }

    private void addRecordThreeButton() {
        JButton button = new JButton("存档三");
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 380);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NoteRecord.this.dispose();
                //new ChooseRecord(360,760);
                System.out.println("Click ChooseRecordThree");
            }
        });
    }

    private void addReturnButton() {
        JButton button = new JButton("返回");
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 480);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        button.setSize(200, 60);
        button.setVisible(true);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NoteRecord.this.dispose();
                PrepareExit prepareExit = new PrepareExit(1000,760);
                prepareExit.setVisible(true);
                System.out.println("Click Return");
            }
        });
    }
}
