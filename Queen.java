package Projects.FinalProject;

import java.awt.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class Queen {
    int xLoc, yLoc;
    int squareSize;
    boolean canMove;
    BufferedImage image = null;

    public Queen(int xLoc, int yLoc, int squareSize) {
        this.xLoc       = xLoc;
        this.yLoc       = yLoc;
        this.squareSize = squareSize;

        try {
            image = ImageIO.read(this.getClass().getResourceAsStream("Images/whiteQueen.png"));
        } catch(Exception e) {}
    }

    public void move(int x, int y, Chess game) {
        canMove = ((x + xLoc <= 7 && x + xLoc >= 0) &&
                    (y + yLoc <= 7 && y + yLoc >= 0));
        boolean upOrDown    = (x > 0 || x < 0);
        boolean rightOrLeft = (y > 0 || y < 0);

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

        if (game.queen.xLoc == x + xLoc && game.queen.yLoc == y + yLoc)
            return;
        
        else if (game.king.xLoc == x + xLoc && game.king.yLoc == y + yLoc)
            return;
        
        if (canMove && (upOrDown ^ rightOrLeft)) {
            xLoc += x;
            yLoc += y;
        }

        else if (Math.abs(x) == Math.abs(y) && canMove) {
            xLoc += x;
            yLoc += y;
        }
    }

    public void draw(Graphics g, Canvas c) {
        int xInPixels = (int)(squareSize * xLoc) + 72;
        int yInPixels = (int)(squareSize * yLoc) + 45;

        g.drawImage(image, xInPixels, yInPixels, c);
    }

    public void drawAtMouse(Graphics g, int xInPixels, int yInPixels, Canvas c) {
        g.drawImage(image, xInPixels - 38, yInPixels - 37, c);
    }
}
