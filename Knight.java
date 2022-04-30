package Projects.FinalProject;

import java.awt.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class Knight {
    int xLoc, yLoc;
    int squareSize;
    boolean canMove;
    BufferedImage image = null;

    public Knight(int xLoc, int yLoc, int squareSize) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.squareSize = squareSize;

        try {
            image = ImageIO.read(this.getClass().getResourceAsStream("Images/whiteKnight.png"));
        } catch(Exception e) {}
    }

    public void move(int x, int y, Chess game) {
        canMove = ((x + xLoc <= 7 && x + xLoc >= 0) && 
                    (y + yLoc <= 7 && y + yLoc >= 0));
        
        for (Bishop b : game.bishopList) {
            if (b.xLoc == x + xLoc && b.yLoc == y + yLoc)
                return;
        }

        for (Knight k : game.knightList) {
            if (k.xLoc == x + xLoc && k.yLoc == y + yLoc)
                return;
        }

        for (Pawn p : game.pawnList) {
            if (p.xLoc == x + xLoc && p.yLoc == y + yLoc)
                return;
        }

        for (Rook r : game.rookList) {
            if (r.xLoc == x + xLoc && r.yLoc == y + yLoc)
                return;
        }

        if (game.queen.xLoc == x + xLoc && game.queen.yLoc == y + yLoc)
            return;
        
        else if (game.king.xLoc == x + xLoc && game.king.yLoc == y + yLoc)
            return;
        
        if (canMove) {
            if (Math.abs(x) == 2 && Math.abs(y) == 1) {
                xLoc += x;
                yLoc += y;
            }

            if (Math.abs(x) == 1 && Math.abs(y) == 2) {
                xLoc += x;
                yLoc += y;
            }
        }
    }

    public void draw(Graphics g, Canvas c) {
        int xInPixels = (int)(squareSize * xLoc) + 71;
        int yInPixels = (int)(squareSize * yLoc) + 45;

        g.drawImage(image, xInPixels, yInPixels, c);
    }

    public void drawAtMouse(Graphics g, int xInPixels, int yInPixels, Canvas c) {
        g.drawImage(image, xInPixels - 36, yInPixels - 37, c);
    }
}
