package Projects.FinalProject;

import java.awt.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class Rook {
    int xLoc, yLoc;
    int squareSize;
    boolean canMove;
    BufferedImage image = null;

    public Rook(int xLoc, int yLoc, int squareSize) {
        this.xLoc       = xLoc;
        this.yLoc       = yLoc;
        this.squareSize = squareSize;
        
        try {
            image = ImageIO.read(this.getClass().getResourceAsStream("Images/whiteRook.png"));
        } catch(Exception e) {}
    }

    public void move(int x, int y, Chess game) {
        canMove = ((x + xLoc <= 7 && x + xLoc >= 0) &&
                    (y + yLoc <= 7 && y + yLoc >= 0));
        
        //Checks for pieces in the way of the rook
        for (Bishop b : game.bishopList) {
            if (b.xLoc == x + xLoc) {
                if (b.yLoc == y + yLoc)
                    return;
                else if (b.yLoc > yLoc && b.yLoc <= y + yLoc)
                    return;
                else if (b.yLoc < yLoc && b.yLoc >= y + yLoc)
                    return;
            }
        }

        for (Knight k : game.knightList) {
            if (k.xLoc == x + xLoc) {
                if (k.yLoc == y + yLoc)
                    return;
                else if (k.yLoc > yLoc && k.yLoc <= y + yLoc)
                    return;
                else if (k.yLoc < yLoc && k.yLoc >= y + yLoc)
                    return;
            }
        }

        for (Pawn p : game.pawnList) {
            if (p.xLoc == x + xLoc) {
                if (p.yLoc == y + yLoc)
                    return;
                else if (p.yLoc > yLoc && p.yLoc <= y + yLoc)
                    return;
                else if (p.yLoc < yLoc && p.yLoc >= y + yLoc)
                    return;
            }
        }

        for (Rook r : game.rookList) {
            if (r.xLoc == x + xLoc) {
                if (r.yLoc == y + yLoc)
                    return;
                else if (r.yLoc > yLoc && r.yLoc <= y + yLoc)
                    return;
                else if (r.yLoc < yLoc && r.yLoc >= y + yLoc)
                    return;
            }
        }

        if (game.queen.xLoc == x + xLoc) {
            if (game.queen.yLoc == y + yLoc)
                return;
            else if (game.queen.yLoc > yLoc && game.queen.yLoc <= y + yLoc)
                return;
            else if (game.queen.yLoc < yLoc && game.queen.yLoc >= y + yLoc)
                return;
        }
        
        else if (game.king.xLoc == x + xLoc) {
            if (game.king.yLoc == y + yLoc)
                return;
            else if (game.king.yLoc > yLoc && game.king.yLoc <= y + yLoc)
                return;
            else if (game.king.yLoc < yLoc && game.king.yLoc >= y + yLoc)
                return;
        }
        
        boolean upOrDown    = (x > 0 || x < 0);
        boolean rightOrLeft = (y > 0 || y < 0);

        if (canMove && (upOrDown ^ rightOrLeft)) {
            xLoc += x;
            yLoc += y;
        }
    }

    public void draw(Graphics g, Canvas c) {
        int xInPixels = (int)(squareSize * xLoc) + 80;
        int yInPixels = (int)(squareSize * yLoc) + 48;

        g.drawImage(image, xInPixels, yInPixels, c);
    }

    public void drawAtMouse(Graphics g, int xInPixels, int yInPixels, Canvas c) {
        g.drawImage(image, xInPixels - 31, yInPixels - 33, c);
    }
}
