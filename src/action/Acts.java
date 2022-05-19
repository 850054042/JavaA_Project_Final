package action;

import model.ChessComponent;
import view.ChessboardPoint;

public class Acts {
    private boolean isCastle = false;
    private boolean isPawnChange = false;
    private ChessComponent beEaten;
    private ChessboardPoint startingPoint;
    private ChessboardPoint targetPoint;
    private ChessboardPoint beEatenPoint;

    public ChessboardPoint getTargetPoint() {
        return targetPoint;
    }

    public void setTargetPoint(ChessboardPoint targetPoint) {
        this.targetPoint = targetPoint;
    }

    public ChessboardPoint getBeEatenPoint() {
        return beEatenPoint;
    }

    public void setBeEatenPoint(ChessboardPoint beEatenPoint) {
        this.beEatenPoint = beEatenPoint;
    }

    public boolean isCastle() {
        return isCastle;
    }

    public void setCastle(boolean castle) {
        isCastle = castle;
    }

    public boolean isPawnChange() {
        return isPawnChange;
    }

    public void setPawnChange(boolean pawnChange) {
        isPawnChange = pawnChange;
    }

    public ChessComponent getBeEaten() {
        return beEaten;
    }

    public void setBeEaten(ChessComponent beEaten) {
        this.beEaten = beEaten;
    }

    public ChessboardPoint getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(ChessboardPoint startingPoint) {
        this.startingPoint = startingPoint;
    }
}
