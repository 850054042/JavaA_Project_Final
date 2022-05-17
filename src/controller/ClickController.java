package controller;


import model.ChessColor;
import model.ChessComponent;
import model.KingChessComponent;
import view.Chessboard;
import view.ChessboardPoint;

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
                for(ChessboardPoint chessboardPoint : first.canMoveTo(chessComponents)){
                    ChessComponent chessComponent1 = chessComponents[chessboardPoint.getX()][chessboardPoint.getY()];
                    chessComponent1.setCanBeMovedTo(false);
                    chessComponent1.repaint();
                }
                chessboard.swapChessComponents(first, chessComponent);
                chessboard.swapColor();
                first.setSelected(false);
                first = null;
                boolean[] dangerKing = {false, false};
                int[] posKing1 = new int[2],posKing2 = new int[2];
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
        return chessComponent.getChessColor() != chessboard.getCurrentColor() &&
                first.canMoveTo(chessboard.getChessComponents()).contains(chessComponent.getChessboardPoint());
    }
}
