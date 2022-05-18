package controller;


import model.ChessColor;
import model.ChessComponent;
import model.KingChessComponent;
import view.Chessboard;
import view.ChessboardPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClickController {
    private final Chessboard chessboard;
    private ChessComponent first;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void onClick(ChessComponent chessComponent) {
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
                first.setSelected(false);
                first = null;
                boolean[] dangerKing = {false, false};//用于判断两方王是否处于被将军状态
                int[] posKing1 = new int[2],posKing2 = new int[2];//分别存储两个王
                boolean has1 = false;
                for(int i = 0;i < 8;i++){
                    for(int j = 0;j < 8;j++){
                        ChessComponent chess = chessComponents[i][j];
                        if(chess instanceof KingChessComponent){
                            if(has1){
                                posKing2[0] = i;
                                posKing2[1] = j;
                            }
                            else{
                                posKing1[0] = i;
                                posKing1[1] = j;
                                has1 = true;
                            }
                        }
                        int chesscolor = chess.getChessColor() == ChessColor.BLACK ? 1:0;
                        for(ChessboardPoint pos : chess.canMoveTo(chessComponents)){
                            if(chessComponents[pos.getX()][pos.getY()] instanceof KingChessComponent){
                                dangerKing[chesscolor] = true;
                                break;
                            }
                        }
                    }
                }
                if(chessComponents[posKing1[0]][posKing1[1]].getChessColor() == ChessColor.BLACK){
                    chessComponents[posKing1[0]][posKing1[1]].setCanBeMovedTo(dangerKing[0]);
                    chessComponents[posKing1[0]][posKing1[1]].repaint();
                    chessComponents[posKing2[0]][posKing2[1]].setCanBeMovedTo(dangerKing[1]);
                    chessComponents[posKing2[0]][posKing2[1]].repaint();
                }
                else{
                    chessComponents[posKing1[0]][posKing1[1]].setCanBeMovedTo(dangerKing[1]);
                    chessComponents[posKing1[0]][posKing1[1]].repaint();
                    chessComponents[posKing2[0]][posKing2[1]].setCanBeMovedTo(dangerKing[0]);
                    chessComponents[posKing2[0]][posKing2[1]].repaint();
                }//被将军时的预警
                if(Chessboard.gameMode == 0) {//区分人人对战和人机对战
                    chessboard.swapColor();
                }
                else{
                    switch (Chessboard.AILevel){//AI分级，算法还待完善
                        case 1:
                            break;
                        case 2://随机行棋
                            ChessColor AIColor = chessboard.getCurrentColor() == ChessColor.BLACK ? ChessColor.WHITE:ChessColor.BLACK;
                            List<ChessComponent> chessComponentList = new ArrayList<>();
                            for(int i = 0;i < 8;i++)
                                for(int j = 0;j < 8;j++)
                                    if(chessComponents[i][j].getChessColor() == AIColor && !chessComponents[i][j].canMoveTo(chessComponents).isEmpty())
                                        chessComponentList.add(chessComponents[i][j]);
                            Random r = new Random();
                            ChessComponent chess = chessComponentList.get(r.nextInt(chessComponentList.size()));
                            ChessboardPoint targetPoint = chess.canMoveTo(chessComponents).get(r.nextInt(chess.canMoveTo(chessComponents).size()));
                            ChessComponent targetChess = chessComponents[targetPoint.getX()][targetPoint.getY()];
                            chessboard.swapChessComponents(chess, targetChess);
                            break;
                        case 3://打算写贪心
                            break;
                    }
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
