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
 * 这个类表示国际象棋里面的兵
 */
public class PawnChessComponent extends ChessComponent {
    /**
     * 黑兵和白兵的图片，static使得其可以被所有兵对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image PAWN_WHITE;
    private static Image PAWN_BLACK;

    /**
     * 兵棋子对象自身的图片，是上面两种中的一种
     */
    private Image pawnImage;

    /**
     * 读取加载兵棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (PAWN_WHITE == null) {
            PAWN_WHITE = ImageIO.read(new File("./images/pawn-white.png"));
        }

        if (PAWN_BLACK == null) {
            PAWN_BLACK = ImageIO.read(new File("./images/pawn-black.png"));
        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定pawnImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiatePawnImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                pawnImage = PAWN_WHITE;
            } else if (color == ChessColor.BLACK) {
                pawnImage = PAWN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        this.name = color == ChessColor.BLACK ? 'R':'r';
        initiatePawnImage(color);
    }

    /**
     * 兵棋子的移动规则
     *
     * @param chessboard 棋盘
     * @return 兵棋子移动的合法性
     */

    @Override
    public java.util.List<ChessboardPoint> canMoveTo(ChessComponent[][] chessboard) {
        int x = super.getChessboardPoint().getX();
        int y = super.getChessboardPoint().getY();
        List<ChessboardPoint> chessboardPoints = new ArrayList<>();
        ChessComponent chess = chessboard[x][y];
        if(super.getChessColor() == ChessColor.BLACK){
            if(x == 1){
                if(chessboard[2][y].getChessColor().equals(ChessColor.NONE))
                    chessboardPoints.add(new ChessboardPoint(2,y));
                if(chessboard[3][y].getChessColor().equals(ChessColor.NONE))
                    chessboardPoints.add(new ChessboardPoint(3,y));
                if(isValid(y - 1)){
                    if(chess.isOpposite(chessboard[2][y - 1]))
                        chessboardPoints.add(new ChessboardPoint(2,y - 1));
                }
                if(isValid(y + 1)){
                    if(chess.isOpposite(chessboard[2][y + 1]))
                        chessboardPoints.add(new ChessboardPoint(2,y + 1));
                }
            }
            else{
                if(isValid(x + 1)){
                    if(chessboard[x + 1][y].getChessColor().equals(ChessColor.NONE))
                        chessboardPoints.add(new ChessboardPoint(x + 1,y));
                    if(isValid(y - 1)){
                        if(chess.isOpposite(chessboard[x + 1][y - 1]))
                            chessboardPoints.add(new ChessboardPoint(x + 1,y - 1));
                    }
                    if(isValid(y + 1)){
                        if(chess.isOpposite(chessboard[x + 1][y + 1]))
                            chessboardPoints.add(new ChessboardPoint(x + 1,y + 1));
                    }
                }
            }
        }
        else{
            if(x == 6){
                if(chessboard[5][y].getChessColor().equals(ChessColor.NONE))
                    chessboardPoints.add(new ChessboardPoint(5,y));
                if(chessboard[4][y].getChessColor().equals(ChessColor.NONE))
                    chessboardPoints.add(new ChessboardPoint(4,y));
                if(isValid(y - 1)){
                    if(chess.isOpposite(chessboard[5][y - 1]))
                        chessboardPoints.add(new ChessboardPoint(5,y - 1));
                }
                if(isValid(y + 1)){
                    if(chess.isOpposite(chessboard[5][y + 1]))
                        chessboardPoints.add(new ChessboardPoint(5,y + 1));
                }
            }
            else{
                if(isValid(x - 1)){
                    if(chessboard[x - 1][y].getChessColor().equals(ChessColor.NONE))
                        chessboardPoints.add(new ChessboardPoint(x - 1,y));
                    if(isValid(y - 1)){
                        if(chess.isOpposite(chessboard[x - 1][y - 1]))
                            chessboardPoints.add(new ChessboardPoint(x - 1,y - 1));
                    }
                    if(isValid(y + 1)){
                        if(chess.isOpposite(chessboard[x - 1][y + 1]))
                            chessboardPoints.add(new ChessboardPoint(x - 1,y + 1));
                    }
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
        g.drawImage(pawnImage, 0, 0, getWidth() , getHeight(), this);
    }
}
