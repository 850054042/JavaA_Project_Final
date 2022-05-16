package model;

import view.ChessboardPoint;
import controller.ClickController;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类表示棋盘上的空位置
 */
public class EmptySlotComponent extends ChessComponent {

    public EmptySlotComponent(ChessboardPoint chessboardPoint, Point location, ClickController listener, int size) {
        super(chessboardPoint, location, ChessColor.NONE, listener, size);
        this.name = '_';
    }

    @Override
    public List<ChessboardPoint> canMoveTo(ChessComponent[][] chessboard) {
        return new ArrayList<>();
    }

    @Override
    public void loadResource() throws IOException {
        //No resource!
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Highlights the model if selected.
        int origin = (super.getChessboardPoint().getX() + super.getChessboardPoint().getY() + 1) % 2;
        if(isEntered()){
            g.setColor(Color.YELLOW);
            g.fillRect(0,0,getWidth(),getHeight());
        }
        if(isCanBeMovedTo()){
            g.setColor(new java.awt.Color(155 + 100 * origin, 155 + 100 * origin, 75 + 75 * origin));
            g.fillRect(0,0,getWidth(),getHeight());
        }
    }
}
