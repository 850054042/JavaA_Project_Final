package controller;


import model.ChessColor;
import model.ChessComponent;
import model.KingChessComponent;
import view.Chessboard;
import view.ChessboardPoint;
import view.MusicPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClickController {
    private final Chessboard chessboard;
    private ChessComponent first;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void onClick(ChessComponent chessComponent) throws InterruptedException {
        if (first == null) {
            if (handleFirst(chessComponent)) {
                chessComponent.setSelected(true);
                first = chessComponent;
                first.repaint();
                ChessComponent[][] chessComponents = chessboard.getChessComponents();
                for(ChessboardPoint chessboardPoint : first.canMoveTo(chessComponents)){
                    ChessComponent chessComponent1 = chessComponents[chessboardPoint.getX()][chessboardPoint.getY()];
                    chessComponent1.setCanBeMovedTo(true);
                    chessComponent1.repaint();
                }
            }
        } else {
            if (first == chessComponent) { // 再次点击取消选取
                chessComponent.setSelected(false);
                ChessComponent recordFirst = first;
                first = null;
                recordFirst.repaint();
                ChessComponent[][] chessComponents = chessboard.getChessComponents();
                for(ChessboardPoint chessboardPoint : recordFirst.canMoveTo(chessComponents)){
                    ChessComponent chessComponent1 = chessComponents[chessboardPoint.getX()][chessboardPoint.getY()];
                    chessComponent1.setCanBeMovedTo(false);
                    chessComponent1.repaint();
                }
            } else if (handleSecond(chessComponent)) {
                //repaint in swap chess method.

                ChessComponent[][] chessComponents = chessboard.getChessComponents();
                for(ChessboardPoint chessboardPoint : first.canMoveTo(chessComponents)){//高亮显示棋子可移动到的目标点
                    ChessComponent chessComponent1 = chessComponents[chessboardPoint.getX()][chessboardPoint.getY()];
                    chessComponent1.setCanBeMovedTo(false);
                    chessComponent1.repaint();
                }
                first.setHasMoved(true);
                if(first.getChessColor() == chessComponent.getChessColor()){
                    chessboard.castleSwap(first, chessComponent);//王车易位
                }
                else {
                    chessboard.swapChessComponents(first, chessComponent);//正常行棋
                }
                first.setLastMove(true);
                for(int i = 0;i < 8;i++){
                    for(int j = 0;j < 8;j++){
                        if(!first.getChessboardPoint().equals(new ChessboardPoint(i,j))){
                            chessComponents[i][j].setLastMove(false);
                        }
                    }
                }
                first.setSelected(false);
                first = null;
                chessboard.showDangerKing();
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                if(Chessboard.gameMode == 0) {//区分人人对战和人机对战
                    chessboard.swapColor();
                }
                else{
                    ChessColor AIColor = chessboard.getCurrentColor() == ChessColor.BLACK ? ChessColor.WHITE:ChessColor.BLACK;
                    switch (Chessboard.AILevel){//AI分级，算法还待完善
                        case 1://就近行棋
                            boolean hasChess = false;
                            int i1 = 0;
                            int j1 = 0;
                            for(int i = 0;i < 8;i++) {
                                for (int j = 0; j < 8; j++) {
                                    if (chessComponents[i][j].getChessColor() == AIColor && !chessComponents[i][j].canMoveTo(chessComponents).isEmpty()) {
                                        i1 = i;
                                        j1 = j;
                                        hasChess = true;
                                    }
                                    if (hasChess)
                                        break;
                                }
                                if(hasChess)
                                    break;
                            }
                            ChessComponent chess1 = chessComponents[i1][j1];
                            ChessboardPoint tP = chess1.canMoveTo(chessComponents).get(0);
                            ChessComponent tC = chessComponents[tP.getX()][tP.getY()];
                            chessboard.swapChessComponents(chess1, tC);
                            chess1.setHasMoved(true);
                            break;
                        case 2://随机行棋
                            List<ChessComponent> chessComponentList = new ArrayList<>();
                            for(int i = 0;i < 8;i++)
                                for(int j = 0;j < 8;j++)
                                    if(chessComponents[i][j].getChessColor() == AIColor && !chessComponents[i][j].canMoveTo(chessComponents).isEmpty())
                                        chessComponentList.add(chessComponents[i][j]);
                            Random r = new Random();
                            ChessComponent chess = chessComponentList.get(r.nextInt(chessComponentList.size()));
                            ChessboardPoint targetPoint = chess.canMoveTo(chessComponents).get(r.nextInt(chess.canMoveTo(chessComponents).size()));
                            ChessComponent targetChess = chessComponents[targetPoint.getX()][targetPoint.getY()];
                            if(chess.getChessColor() == targetChess.getChessColor()){
                                chessboard.castleSwap(chess, targetChess);//王车易位
                            }
                            else {
                                chessboard.swapChessComponents(chess, targetChess);//正常行棋
                            }
                            chess.setHasMoved(true);
                            break;
                        case 3://打算写贪心
                            break;
                    }
//                    MusicPlayer move = new MusicPlayer("D://文件//伴奏//Queen - Yeah.mp3");
//                    move.start();
                }
            }
        }
    }

    /**
     * @param chessComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param chessComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(ChessComponent chessComponent) {
        return first.canMoveTo(chessboard.getChessComponents()).contains(chessComponent.getChessboardPoint());
    }
}
