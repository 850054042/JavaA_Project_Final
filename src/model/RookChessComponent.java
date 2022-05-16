package model;

import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类表示国际象棋里面的车
 */
public class RookChessComponent extends ChessComponent {
    /**
     * 黑车和白车的图片，static使得其可以被所有车对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image ROOK_WHITE;
    private static Image ROOK_BLACK;

    /**
     * 车棋子对象自身的图片，是上面两种中的一种
     */
    private Image rookImage;

    /**
     * 读取加载车棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (ROOK_WHITE == null) {
            ROOK_WHITE = ImageIO.read(new File("./images/rook-white.png"));
        }

        if (ROOK_BLACK == null) {
            ROOK_BLACK = ImageIO.read(new File("./images/rook-black.png"));
        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateRookImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                rookImage = ROOK_WHITE;
            } else if (color == ChessColor.BLACK) {
                rookImage = ROOK_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RookChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        this.name = color == ChessColor.BLACK ? 'R':'r';
        initiateRookImage(color);
    }

    /**
     * 车棋子的移动规则
     *
     * @param chessboard 棋盘
     * @return 车棋子移动的合法性
     */

    @Override
    public java.util.List<ChessboardPoint> canMoveTo(ChessComponent[][] chessboard) {
        int x = super.getChessboardPoint().getX();
        int y = super.getChessboardPoint().getY();
        List<ChessboardPoint> chessboardPoints = new ArrayList<>();
        ChessComponent chess = chessboard[x][y];
        for(int i = 2;i <= 8;i += 2) {
            for (int j = 1; j < 8; j++) {
                int sinAngle = (int) Math.round(Math.sin(i * 0.7854) * 1.4);
                int cosAngle = (int) Math.round(Math.cos(i * 0.7854) * 1.4);
                int targetX = x + j * cosAngle;
                int targetY = y + j * sinAngle;
                if (isValid(targetX) && isValid(targetY)) {
                    if (chessboard[targetX][targetY].name != '_') {
                        if (chess.isOpposite(chessboard[targetX][targetY])) {
                            chessboardPoints.add(new ChessboardPoint(targetX, targetY));
                        }
                        break;
                    } else {
                        chessboardPoints.add(new ChessboardPoint(targetX, targetY));
                    }
                } else {
                    break;
                }
            }
        }
        return chessboardPoints;
    }

    /**
     * 注意这个方法，每当窗体受到了形状的变化，或者是通知要进行绘图的时候，就会调用这个方法进行画图。
     *
     * @param g 可以类比于画笔
     */

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        // Highlights the model if selected.
        if(isEntered()){
            g.setColor(new java.awt.Color(255, 255, 150));
            g.fillRect(0,0,getWidth(),getHeight());
        }
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
        if(isCanBeMovedTo()){
            g.setColor(Color.GREEN);
            g.fillRect(0,0,getWidth(),getHeight());
        }
        g.drawImage(rookImage, 0, 0, getWidth() , getHeight(), this);
    }
}
