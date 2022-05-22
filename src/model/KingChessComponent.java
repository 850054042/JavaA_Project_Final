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
 * 这个类表示国际象棋里面的王
 */
public class KingChessComponent extends ChessComponent {
    /**
     * 黑王和白王的图片，static使得其可以被所有王对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image KING_WHITE;
    private static Image KING_BLACK;

    /**
     * 王棋子对象自身的图片，是上面两种中的一种
     */
    private Image kingImage;

    /**
     * 读取加载王棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (KING_WHITE == null) {
            KING_WHITE = ImageIO.read(new File("./images/king-white.png"));
        }

        if (KING_BLACK == null) {
            KING_BLACK = ImageIO.read(new File("./images/king-black.png"));
        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定kingImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateKingImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                kingImage = KING_WHITE;
            } else if (color == ChessColor.BLACK) {
                kingImage = KING_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KingChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        this.name = color == ChessColor.BLACK ? 'K':'k';
        initiateKingImage(color);
    }

    /**
     * 王棋子的移动规则
     *
     * @param chessboard 棋盘
     * @return 王棋子移动的合法性
     */

    @Override
    public java.util.List<ChessboardPoint> canMoveTo(ChessComponent[][] chessboard) {
        int x = super.getChessboardPoint().getX();
        int y = super.getChessboardPoint().getY();
        List<ChessboardPoint> chessboardPoints = new ArrayList<>();
        ChessComponent chess = chessboard[x][y];
        for(int i = x - 1;i <= x + 1;i++)
            for(int j = y - 1;j <= y + 1;j++)
                if((i != x || j != y) && isValid(i) && isValid(j))
                    if(chess.isOpposite(chessboard[i][j]) || chessboard[i][j].getChessColor().equals(ChessColor.NONE))
                        chessboardPoints.add(new ChessboardPoint(i,j));//基础走法
        boolean castleLeft = true, castleRight = true;
        if(this.isHasMoved()){
            castleLeft = false;
            castleRight = false;
        }//王车易位时王不能动过
        if(chessboard[x][0] instanceof RookChessComponent) {
            if (chessboard[x][0].isHasMoved())
                castleLeft = false;//左侧判断，左车不能动过
            else{
                for(int i = 0;i <= 4;i++) {
                    if(i != 0 && i != 4)
                        if (!(chessboard[x][i] instanceof EmptySlotComponent))
                            castleLeft = false;//中间非空不能易位
                    if(i >= 2) {
                        for (int j = 0; j < 8; j++) {
                            for (int k = 0; k < 8; k++) {
                                if(!(chessboard[j][k] instanceof KingChessComponent)) {
                                    if (chessboard[j][k].getChessColor() != chessColor
                                            && chessboard[j][k].canMoveTo(chessboard).contains(new ChessboardPoint(x, i))) {
                                        castleLeft = false;//王经过的格子都不能受威胁
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else{
            castleLeft = false;
        }
        if(chessboard[x][7] instanceof RookChessComponent) {
            if (chessboard[x][7].isHasMoved())
                castleRight = false;//右侧判断，右车不能动过
            else{
                for(int i = 4;i <= 7;i++) {
                    if(i != 7 && i != 4)
                        if (!(chessboard[x][i] instanceof EmptySlotComponent))
                            castleRight = false;//中间非空不能易位
                    if(i <= 6) {
                        for (int j = 0; j < 8; j++) {
                            for (int k = 0; k < 8; k++) {
                                if(!(chessboard[j][k] instanceof KingChessComponent)) {
                                    if (chessboard[j][k].getChessColor() != chessColor
                                            && chessboard[j][k].canMoveTo(chessboard).contains(new ChessboardPoint(x, i))) {
                                        castleRight = false;//王经过的格子都不能受威胁
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else{
            castleRight = false;
        }
        if(castleLeft)
            chessboardPoints.add(new ChessboardPoint(x,0));
        if(castleRight)
            chessboardPoints.add(new ChessboardPoint(x,7));
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
            g.setColor(Color.RED);
            g.fillRect(0,0,getWidth(),getHeight());
        }
        g.drawImage(kingImage, 0, 0, getWidth() , getHeight(), this);
    }
}
