package view;

import action.Acts;
import controller.ClickController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PawnChangeMenu extends JFrame {
    private final int WIDTH = 360;
    private final int HEIGTH = 760;
    private Acts acts;
    public PawnChangeMenu(Chessboard chessboard, ChessComponent chess, ClickController listener, int size, Acts acts) {
        setTitle("PawnChangeMenu"); //设置标题

        setSize(WIDTH,HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        this.acts = acts;

        addLabel();
        addQueenButton(chessboard,chess,listener,size);
        addKnightButton(chessboard,chess,listener,size);
        addRookButton(chessboard,chess,listener,size);
        addBishopButton(chessboard,chess,listener,size);
    }

    private void addLabel() {
        JLabel statusLabel = new JLabel("兵の升变");
        statusLabel.setLocation(HEIGTH / 10 + 15, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("黑体", Font.BOLD, 40));
        add(statusLabel);
    }

    private void addQueenButton(Chessboard chessboard,ChessComponent chess1, ClickController listener, int size) {
        JButton button = new JButton("变身为后");
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 180);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PawnChangeMenu.this.dispose();
                System.out.println("Click ChooseQueen");
                ChessComponent chess = new QueenChessComponent(chess1.getChessboardPoint(), chess1.getLocation(), chess1.getChessColor(), listener, size);
                chessboard.add(chess);
                chess.repaint();
            }
        });
    }

    private void addKnightButton(Chessboard chessboard,ChessComponent chess1, ClickController listener, int size) {
        JButton button = new JButton("变身为马");
        button.setLocation(HEIGTH/10, HEIGTH / 10 + 280);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PawnChangeMenu.this.dispose();
                System.out.println("Click ChooseKnight");
                ChessComponent chess = new KnightChessComponent(chess1.getChessboardPoint(), chess1.getLocation(), chess1.getChessColor(), listener, size);
                chessboard.add(chess);
                chess.repaint();
            }
        });
    }

    private void addRookButton(Chessboard chessboard,ChessComponent chess1, ClickController listener, int size) {
        JButton button = new JButton("变身为车");
        button.setLocation(HEIGTH / 10, HEIGTH / 10 + 380);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PawnChangeMenu.this.dispose();
                System.out.println("Click ChooseRook");
                ChessComponent chess = new RookChessComponent(chess1.getChessboardPoint(), chess1.getLocation(), chess1.getChessColor(), listener, size);
                chessboard.add(chess);
                chess.repaint();
            }
        });
    }

    private void addBishopButton(Chessboard chessboard,ChessComponent chess1, ClickController listener, int size) {
        JButton button = new JButton("变身为象");
        button.setLocation(HEIGTH / 10, HEIGTH / 10 + 480);
        button.setSize(200, 60);
        button.setFont(new Font("黑体", Font.BOLD, 20));
        add(button);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PawnChangeMenu.this.dispose();
                System.out.println("Click ChooseBishop");
                ChessComponent chess = new BishopChessComponent(chess1.getChessboardPoint(), chess1.getLocation(), chess1.getChessColor(), listener, size);
                chessboard.add(chess);
                chess.repaint();
            }
        });
    }
}
