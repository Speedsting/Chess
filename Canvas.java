package Projects.FinalProject;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel{
    Font myFont;
    int numSquaresAcross;
    Chess game;
    BufferedImage image = null;
    public String piece;
    public int num, xPos, yPos;

    public Canvas(int width, int height, Chess game, int numSquaresAcross) {
        this.myFont           = new Font("Verdana", Font.BOLD, 16);
        this.game             = game;
        this.numSquaresAcross = numSquaresAcross;
        piece = "";
        num   = 0;
        xPos  = 0;
        yPos  = 0;

        try {
            image = ImageIO.read(this.getClass().getResourceAsStream("Images/chessboard.png"));
        } catch(Exception e) {}

        setPreferredSize(new Dimension(width, height));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(image, 25, 0, this);
        game.draw(g, this, piece, num, xPos, yPos);
        g.setFont(myFont);
    }
}
