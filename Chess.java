package Projects.FinalProject;

import java.awt.*;

public class Chess {
    public boolean turn;        //true if white's turn, otherwise false
    public int squareSize = 0;
    public Pawn[] pawnList     = new Pawn[8];
    public Rook[] rookList     = new Rook[2];
    public Bishop[] bishopList = new Bishop[2];
    public Knight[] knightList = new Knight[2];
    public Queen queen;
    public King king;

    public Chess(int squareSize) {
        this.squareSize = squareSize;
        turn = true;

        //PLEASE FIX CLASSES PROBLEM!!!!
        Rook rook1 = new Rook(0, 7, squareSize);
        Rook rook2 = new Rook(7, 7, squareSize);

        queen = new Queen(3, 7, squareSize);
        king  = new King(4, 7, squareSize);

        Bishop bishop1 = new Bishop(2, 7, squareSize);
        Bishop bishop2 = new Bishop(5, 7, squareSize);

        Knight knight1 = new Knight(1, 7, squareSize);
        Knight knight2 = new Knight(6, 7, squareSize);

        Pawn pawn1 = new Pawn(0, 6, squareSize);
        Pawn pawn2 = new Pawn(1, 6, squareSize);
        Pawn pawn3 = new Pawn(2, 6, squareSize);
        Pawn pawn4 = new Pawn(3, 6, squareSize);
        Pawn pawn5 = new Pawn(4, 6, squareSize);
        Pawn pawn6 = new Pawn(5, 6, squareSize);
        Pawn pawn7 = new Pawn(6, 6, squareSize);
        Pawn pawn8 = new Pawn(7, 6, squareSize);

        pawnList[0] = pawn1;
        pawnList[1] = pawn2;
        pawnList[2] = pawn3;
        pawnList[3] = pawn4;
        pawnList[4] = pawn5;
        pawnList[5] = pawn6;
        pawnList[6] = pawn7;
        pawnList[7] = pawn8;

        rookList[0] = rook1;
        rookList[1] = rook2;

        bishopList[0] = bishop1;
        bishopList[1] = bishop2;

        knightList[0] = knight1;
        knightList[1] = knight2;
    }

    /**
     * Finds the piece at x location {@code pos1} and y location {@code pos2}
     * @param pos1 the x position of the object
     * @param pos2 the y position of the object
     */
    public String findPiece(int pos1, int pos2) {
        ///////////////////////////
        //SEARCHES THROUGH PIECES//
        ///////////////////////////

        int num = 0;
        for (Pawn p : pawnList) {
            int x = p.xLoc;
            int y = p.yLoc;
            if (x == pos1 && y == pos2) {
                return "pawn " + num;
            }

            num++;
        }

        num = 0;
        for (Bishop b : bishopList) {
            int x = b.xLoc;
            int y = b.yLoc;
            if (x == pos1 && y == pos2) {
                return "bishop " + num;
            }

            num++;
        }
        
        num = 0;
        for (Rook r : rookList) {
            int x = r.xLoc;
            int y = r.yLoc;
            if (x == pos1 && y == pos2) {
                return "rook " + num;
            }
            
            num++;
        }

        num = 0;
        for (Knight k : knightList) {
            int x = k.xLoc;
            int y = k.yLoc;
            if (x == pos1 && y == pos2) {
                return "knight " + num;
            }
            
            num++;
        }

        if (queen.xLoc == pos1 && queen.yLoc == pos2) {
            return "queen " + 1;
        }

        else if (king.xLoc == pos1 && king.yLoc == pos2) {
            return "king " + 1;
        }

        return "";
    }

    /**
     * Moves the piece at x coordinate {@code pos1} and y coordinate {@code pos2}
     * @param xAmt
     * @param yAmt
     * @param pos1
     * @param pos2
     */
    public void movePiece(int xAmt, int yAmt, int pos1, int pos2) {
        String thing = findPiece(pos1, pos2);
        String name = thing.substring(0, thing.indexOf(" "));
        int num = Integer.parseInt(thing.substring(thing.indexOf(" ") + 1, thing.length()));

        switch (name.toLowerCase()) {
            case "bishop":
                Bishop b = bishopList[num];
                b.move(xAmt, yAmt, this);
                break;
            
            case "knight":
                Knight k = knightList[num];
                k.move(xAmt, yAmt, this);
                break;

            case "rook":
                Rook r = rookList[num];
                r.move(xAmt, yAmt, this);
                break;
            
            case "pawn":
                Pawn p = pawnList[num];
                p.move(xAmt, yAmt, this);
                break;
            
            case "queen":
                queen.move(xAmt, yAmt, this);
                break;

            case "king":
                king.move(xAmt, yAmt, this);
                break;
            
            default:
                break;
        }

    }
    /**
     * Draws all the pieces on the board
     * @param g the {@code graphics} object
     * @param c the {@code canvas} object
     */
    public void draw(Graphics g, Canvas c, String piece, int num, int xPos, int yPos) {
        boolean skipP = false;
        boolean skipB = false;
        boolean skipR = false;
        boolean skipN = false;
        boolean skipQ = false;
        boolean skipK = false;
        boolean skip  = false;

        switch (piece) {
            case "pawn":
                skipP = true;
                skip  = !skip;
                break;

            case "bishop":
                skipB = true;
                skip  = !skip;
                break;
            
            case "rook":
                skipR = true;
                skip  = !skip;
                break;

            case "knight":
                skipN = true;
                skip  = !skip;
                break;

            case "queen":
                skipQ = true;
                skip  = !skip;
                break;
            
            case "king":
                skipK = true;
                skip  = !skip;
                break;

            default:
                skip = !skip;
                break;
        }

        for (int i = 0; i < pawnList.length; i++) {
            Pawn p = pawnList[i];
            if (!(i == num && skipP)) {
                p.draw(g, c);
            }

            else
                p.drawAtMouse(g, xPos, yPos, c);
        }

        for (int i = 0; i < bishopList.length; i++) {
            Bishop b = bishopList[i];
            if (!(i == num && skipB))
                b.draw(g, c);

            else
                b.drawAtMouse(g, xPos, yPos, c);
        }
        
        for (int i = 0; i < rookList.length; i++) {
            Rook r = rookList[i];
            if (!(i == num && skipR))
                r.draw(g, c);
            
            else
                r.drawAtMouse(g, xPos, yPos, c);
        }

        
        for (int i = 0; i < knightList.length; i++) {
            Knight k = knightList[i];
            if (!(i == num && skipN))
                k.draw(g, c);

            else
                k.drawAtMouse(g, xPos, yPos, c);
        }


        if (!skipQ)
            queen.draw(g, c);
        
        else
            queen.drawAtMouse(g, xPos, yPos, c);

        if (!skipK)
            king.draw(g, c);
        
        else
            king.drawAtMouse(g, xPos, yPos, c);
    }
}
