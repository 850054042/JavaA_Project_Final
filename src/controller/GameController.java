package controller;

import view.ChessGameFrame;
import view.Chessboard;
import view.ErrorMessage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private Chessboard chessboard;

    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public List<String> loadGameFromFile(String path) {
        try {
            List<String> chessData = Files.readAllLines(Path.of(path));
            int result = chessboard.loadGame(chessData);
            if(path.endsWith(".txt")) {
                if (result == 0) {
                    //System.out.println("loading......");
                    ChessGameFrame chessGameFrame = new ChessGameFrame(chessboard, this);
                    chessGameFrame.setVisible(true);
                    return chessData;
                } else {
                    ErrorMessage errorMessage = new ErrorMessage(result);
                    errorMessage.setVisible(true);
                    return new ArrayList<>();
                }
            }
            else{
                result = 4;
                ErrorMessage errorMessage = new ErrorMessage(result);
                errorMessage.setVisible(true);
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
