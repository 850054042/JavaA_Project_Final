package view;


import action.Acts;
import model.*;
import controller.ClickController;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Stack;

/**
 * 这个类表示面板上的棋盘组件对象
 */
public class Chessboard extends JComponent {
    /**
     * CHESSBOARD_SIZE： 棋盘是8 * 8的
     * <br>
     * BACKGROUND_COLORS: 棋盘的两种背景颜色
     * <br>
     * chessListener：棋盘监听棋子的行动
     * <br>
     * chessboard: 表示8 * 8的棋盘
     * <br>
     * currentColor: 当前行棋方
     */
    private static final int CHESSBOARD_SIZE = 8;

    private final ChessComponent[][] chessComponents = new ChessComponent[CHESSBOARD_SIZE][CHESSBOARD_SIZE];
    private ChessColor currentColor = ChessColor.WHITE;
    //all chessComponents in this chessboard are shared only one model controller
    private final ClickController clickController = new ClickController(this);
    private final int CHESS_SIZE;
    private boolean AIundo = false;
    public static int gameMode = 0;//0 for pvp, 1 for pvc
    public static int AILevel = 0;
    public Stack<Acts> actions = new Stack<>();
    private JLabel label;
    private ChessGameFrame chessGameFrame;

    public void setChessGameFrame(ChessGameFrame chessGameFrame) {
        this.chessGameFrame = chessGameFrame;
    }

    public void setLabel(JLabel label){
        this.label = label;
    }

    public Chessboard(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        CHESS_SIZE = width / 8;
        System.out.printf("chessboard size = %d, chess size = %d\n", width, CHESS_SIZE);

        initiateEmptyChessboard();

        // FIXME: Initialize chessboard for testing only.
        initRookOnBoard(0, 0, ChessColor.BLACK);
        initRookOnBoard(0, 7, ChessColor.BLACK);
        initRookOnBoard(7, 0, ChessColor.WHITE);
        initRookOnBoard(7, 7, ChessColor.WHITE);
        initBishopOnBoard(0, 2, ChessColor.BLACK);
        initBishopOnBoard(0, 5, ChessColor.BLACK);
        initBishopOnBoard(7, 2, ChessColor.WHITE);
        initBishopOnBoard(7, 5, ChessColor.WHITE);
        initQueenOnBoard(0,3,ChessColor.BLACK);
        initQueenOnBoard(7,3,ChessColor.WHITE);
        initKnightOnBoard(0,1,ChessColor.BLACK);
        initKnightOnBoard(0,6,ChessColor.BLACK);
        initKnightOnBoard(7,1,ChessColor.WHITE);
        initKnightOnBoard(7,6,ChessColor.WHITE);
        initKingOnBoard(0,4,ChessColor.BLACK);
        initKingOnBoard(7,4,ChessColor.WHITE);
        for(int i = 0;i < 8;i++){
            initPawnOnBoard(1,i,ChessColor.BLACK);
            initPawnOnBoard(6,i,ChessColor.WHITE);
        }
    }

    public ChessComponent[][] getChessComponents() {
        return chessComponents;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void putChessOnBoard(ChessComponent chessComponent) {
        int row = chessComponent.getChessboardPoint().getX(), col = chessComponent.getChessboardPoint().getY();

        if (chessComponents[row][col] != null) {
            remove(chessComponents[row][col]);
        }
        add(chessComponents[row][col] = chessComponent);
    }

    public void castleSwap(ChessComponent chess1, ChessComponent chess2){//王车易位
        System.out.println("castle!");
        int row = chess1.getChessboardPoint().getX();
        int col1 = chess1.getChessboardPoint().getY();
        int col2 = chess2.getChessboardPoint().getY();
        Acts acts = new Acts();
        acts.setCastle(true);
        acts.setStartingPoint(new ChessboardPoint(row, col1));
        acts.setTargetPoint(new ChessboardPoint(row, col2));
        actions.push(acts);
        if(col1 > col2){
            ChessComponent chessSwap1 = chessComponents[row][col1 - 2];
            chess1.swapLocation(chessSwap1);
            int row1 = chess1.getChessboardPoint().getX(), co1 = chess1.getChessboardPoint().getY();
            chessComponents[row1][co1] = chess1;
            int row2 = chessSwap1.getChessboardPoint().getX(), co2 = chessSwap1.getChessboardPoint().getY();
            chessComponents[row2][co2] = chessSwap1;
            chess1.repaint();
            chessSwap1.repaint();
            ChessComponent chessSwap2 = chessComponents[row][col1 - 1];
            chess2.swapLocation(chessSwap2);
            row1 = chess2.getChessboardPoint().getX(); co1 = chess2.getChessboardPoint().getY();
            chessComponents[row1][co1] = chess2;
            row2 = chessSwap2.getChessboardPoint().getX(); co2 = chessSwap2.getChessboardPoint().getY();
            chessComponents[row2][co2] = chessSwap2;
            chess2.repaint();
            chessSwap2.repaint();
        }
        else{
            ChessComponent chessSwap1 = chessComponents[row][col1 + 2];
            chess1.swapLocation(chessSwap1);
            int row1 = chess1.getChessboardPoint().getX(), co1 = chess1.getChessboardPoint().getY();
            chessComponents[row1][co1] = chess1;
            int row2 = chessSwap1.getChessboardPoint().getX(), co2 = chessSwap1.getChessboardPoint().getY();
            chessComponents[row2][co2] = chessSwap1;
            chess1.repaint();
            chessSwap1.repaint();
            ChessComponent chessSwap2 = chessComponents[row][col1 + 1];
            chess2.swapLocation(chessSwap2);
            row1 = chess2.getChessboardPoint().getX(); co1 = chess2.getChessboardPoint().getY();
            chessComponents[row1][co1] = chess2;
            row2 = chessSwap2.getChessboardPoint().getX(); co2 = chessSwap2.getChessboardPoint().getY();
            chessComponents[row2][co2] = chessSwap2;
            chess2.repaint();
            chessSwap2.repaint();
        }
        MusicPlayer move = new MusicPlayer("D://文件//伴奏//Queen - Yeah.mp3");
        move.start();
    }

    public void swapChessComponents(ChessComponent chess1, ChessComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        boolean isChess2Empty = true;
        int result = 0;
        if(chess2 instanceof KingChessComponent){
            if(chess2.getChessColor() == ChessColor.BLACK){
                result = 1;
            }
            else {
                result = 2;
            }
        }
        Acts acts = new Acts();
        acts.setBeEaten(chess2);
        acts.setStartingPoint(chess1.getChessboardPoint());
        acts.setTargetPoint(chess2.getChessboardPoint());
        acts.setBeEatenPoint(chess2.getChessboardPoint());
        if (!(chess2 instanceof EmptySlotComponent)) {
            isChess2Empty = false;
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        chessComponents[row1][col1] = chess1;
        chessComponents[row2][col2] = chess2;
        if(chess1 instanceof PawnChessComponent){
            if(row1 == 0 || row1 == 7){
                acts.setPawnChange(true);
                if(chess1.getChessColor() == currentColor) {
                    remove(chess1);
                    PawnChangeMenu pawnChangeMenu = new PawnChangeMenu(this, chess1, clickController, CHESS_SIZE, acts);
                    pawnChangeMenu.setVisible(true);
                }
                else{
                    remove(chess1);
                    ChessComponent chess = new QueenChessComponent(chess1.getChessboardPoint(), chess1.getLocation(), chess1.getChessColor(), clickController, CHESS_SIZE);
                    add(chess);
                    chess.repaint();
                }
            }
            if(col1 != col2 && isChess2Empty){
                acts.setBeEatenPoint(new ChessboardPoint(row2,col1));
                acts.setBeEaten(chessComponents[row2][col1]);
                remove(chessComponents[row2][col1]);
                add(chessComponents[row2][col1] = new EmptySlotComponent(new ChessboardPoint(row2,col1), chessComponents[row2][col1].getLocation(),clickController,CHESS_SIZE));
                chessComponents[row2][col1].repaint();
            }
        }
        actions.push(acts);
        chess1.repaint();
        chess2.repaint();
        MusicPlayer move = new MusicPlayer("D://文件//伴奏//Queen - Yeah.mp3");
        move.start();
        if(result != 0) {
            GameOver gameOver = new GameOver(chessGameFrame, result);
            gameOver.setVisible(true);
        }
    }

    public void undo(){
        if(actions.empty()){
            JOptionPane.showMessageDialog(null,"已经没法悔棋了！","提示",JOptionPane.WARNING_MESSAGE);
            return;
        }
        Acts acts = actions.pop();
        ChessComponent chess1 = chessComponents[acts.getTargetPoint().getX()][acts.getTargetPoint().getY()];
        ChessComponent chess2 = chessComponents[acts.getStartingPoint().getX()][acts.getStartingPoint().getY()];
        chess1.setHasMoved(false);
        chess1.setLastMove(false);
        if(acts.isCastle()){
            int row = acts.getStartingPoint().getX();
            int cl1 = acts.getStartingPoint().getY();
            int cl2 = acts.getTargetPoint().getY();
            if(cl1 < cl2){
                chess1 = chessComponents[row][6];
                chess1.setHasMoved(false);
                chess2 = chessComponents[row][4];
                chess1.swapLocation(chess2);
                int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
                int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
                chessComponents[row1][col1] = chess1;
                chessComponents[row2][col2] = chess2;
                chess1.repaint();
                chess2.repaint();
                chess1 = chessComponents[row][5];
                chess2 = chessComponents[row][7];
                chess1.swapLocation(chess2);
                row1 = chess1.getChessboardPoint().getX(); col1 = chess1.getChessboardPoint().getY();
                row2 = chess2.getChessboardPoint().getX(); col2 = chess2.getChessboardPoint().getY();
                chessComponents[row1][col1] = chess1;
                chessComponents[row2][col2] = chess2;
            }
            else{
                chess1 = chessComponents[row][2];
                chess1.setHasMoved(false);
                chess2 = chessComponents[row][4];
                chess1.swapLocation(chess2);
                int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
                int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
                chessComponents[row1][col1] = chess1;
                chessComponents[row2][col2] = chess2;
                chess1.repaint();
                chess2.repaint();
                chess1 = chessComponents[row][3];
                chess2 = chessComponents[row][0];
                chess1.swapLocation(chess2);
                row1 = chess1.getChessboardPoint().getX(); col1 = chess1.getChessboardPoint().getY();
                row2 = chess2.getChessboardPoint().getX(); col2 = chess2.getChessboardPoint().getY();
                chessComponents[row1][col1] = chess1;
                chessComponents[row2][col2] = chess2;
            }
            chess1.repaint();
            chess2.repaint();
            swapColor();
            return;
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        chessComponents[row1][col1] = chess1;
        chessComponents[row2][col2] = chess2;
        if(acts.isPawnChange()){
            ChessComponent changed = chessComponents[acts.getStartingPoint().getX()][acts.getStartingPoint().getY()];
            remove(changed);
            add(changed = new PawnChessComponent(changed.getChessboardPoint(), changed.getLocation(), changed.getChessColor(), clickController, CHESS_SIZE));
            chessComponents[acts.getStartingPoint().getX()][acts.getStartingPoint().getY()] = changed;
            changed.repaint();
        }
        int row3 = acts.getBeEatenPoint().getX(), col3 = acts.getBeEatenPoint().getY();
        remove(chessComponents[row3][col3]);
        add(acts.getBeEaten());
        chessComponents[row3][col3] = acts.getBeEaten();
        chessComponents[row3][col3].setEntered(false);
        acts.getBeEaten().repaint();
        chessComponents[row3][col3].repaint();
        chess1.repaint();
        if(gameMode == 0) {
            swapColor();
        }
        else{
            if(!AIundo){
                AIundo = true;
                undo();
            }
            else{
                AIundo = false;
            }
        }
        showDangerKing();
    }

    public void showDangerKing(){
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
    }

    public String getChessboardGraph() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < 8;i++){
            for(int j = 0;j < 8;j++){
                sb.append(chessComponents[i][j].getName());
            }
            sb.append("\n");
        }
        if(currentColor == ChessColor.BLACK){
            sb.append('b');
        }else{
            sb.append('w');
        }
        return sb.toString();
    }

    public void initiateEmptyChessboard() {
        for (int i = 0; i < chessComponents.length; i++) {
            for (int j = 0; j < chessComponents[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController, CHESS_SIZE));
            }
        }
    }

    public void swapColor() {
        currentColor = currentColor == ChessColor.BLACK ? ChessColor.WHITE : ChessColor.BLACK;
        if(currentColor == ChessColor.BLACK) {
            label.setText("黑方回合");
        }
        else{
            label.setText("白方回合");
        }
    }

    private void initRookOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new RookChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initBishopOnBoard(int row, int col, ChessColor color){
        ChessComponent chessComponent = new BishopChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initQueenOnBoard(int row, int col, ChessColor color){
        ChessComponent chessComponent = new QueenChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initKnightOnBoard(int row, int col, ChessColor color){
        ChessComponent chessComponent = new KnightChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initKingOnBoard(int row, int col, ChessColor color){
        ChessComponent chessComponent = new KingChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initPawnOnBoard(int row, int col, ChessColor color){
        ChessComponent chessComponent = new PawnChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }


    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }

    public void loadGame(List<String> chessData) {
        for(int i = 0;i < 8;i++){
            for(int j = 0;j < 8;j++){
                char c = chessData.get(i).charAt(j);
                int row = i, col = j;
                ChessColor color = Character.isUpperCase(c) ? ChessColor.BLACK:ChessColor.WHITE;
                switch (c){
                    case 'R':
                    case 'r':
                        chessComponents[i][j] = new RookChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
                        break;
                    case 'N':
                    case 'n':
                        chessComponents[i][j] = new KnightChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
                        break;
                    case 'B':
                    case 'b':
                        chessComponents[i][j] = new BishopChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
                        break;
                    case 'Q':
                    case 'q':
                        chessComponents[i][j] = new QueenChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
                        break;
                    case 'K':
                    case 'k':
                        chessComponents[i][j] = new KingChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
                        break;
                    case 'P':
                    case 'p':
                        chessComponents[i][j] = new PawnChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
                        break;
                    default:
                        chessComponents[i][j] = new EmptySlotComponent(new ChessboardPoint(row, col), calculatePoint(row, col), clickController, CHESS_SIZE);
                        break;
                }
                chessComponents[i][j].setName(c);
                chessComponents[i][j].setVisible(true);
                putChessOnBoard(chessComponents[i][j]);
            }
        }
        currentColor = chessData.get(8).charAt(0) == 'w' ? ChessColor.WHITE:ChessColor.BLACK;
    }
}
