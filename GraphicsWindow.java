package Projects.FinalProject;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.event.*;

public class GraphicsWindow extends JFrame implements ActionListener {
    final int delay;
    Timer t;

    JButton button;
    JPanel topPanel, canvasPanel;
    JTextField info, squarePlace;
    Canvas canvas;
    Chess game;

    int numSquaresAcross, squareSize, width, height, x, y, num, dist1, dist2;
    String piece;

    boolean running;
    public boolean clicked;

    public GraphicsWindow() {
        super("Game Runner");
        width  = 800;
        height = 800;
        delay  = 25;
        numSquaresAcross = 8;
        squareSize       = 80;
        
        x = 0;
        y = 0;

        game   = new Chess(squareSize);
        canvas = new Canvas(width, height, game, numSquaresAcross);

        canvas.addMouseListener(new MouseAdapter() {
            // public void mouseClicked(MouseEvent e) {
            //     System.out.println("Mouse Clicked: (" +e.getX() + ", " + e.getY() + ")");
            //     String place = getSquare(e.getX(), e.getY());
            //     System.out.println(place);

            //     if (!clicked) {
            //         clicked = true;
                   
            //         x = Integer.parseInt(place.substring(0, 1));
            //         y = Integer.parseInt(place.substring(2, 3));
                   
            //         String thing = game.findPiece(x, Math.abs(y - 8));
            //         int space    = thing.indexOf(" ");

            //         piece = thing.substring(0, space);
            //         num   = Integer.parseInt(thing.substring(space + 1, thing.length()));
                    
            //         canvas.piece = piece;
            //         canvas.num   = num;
            //     }
                
            //     else {
            //         clicked = false;
                   
            //         int newX = Integer.parseInt(place.substring(0, 1));
            //         int newY = Integer.parseInt(place.substring(2, 3));

            //         int row1 = x;
            //         int row2 = Math.abs(y - 8);
            //         int row3 = newX;
            //         int row4 = Math.abs(newY - 8);
                   
            //         int dist1 = row3 - row1;
            //         int dist2 = row4 - row2;

            //         canvas.piece = "";
            //         canvas.num   = 0;

            //         try {
            //             game.movePiece(dist1, dist2, row1, row2);
            //         } catch (Exception j) {System.out.println("Error");}
            //     }
            // }

            public void mouseReleased(MouseEvent e) {
                clicked = false;

                String place = getSquare(e.getX(), e.getY());

                int newX = Integer.parseInt(place.substring(0, 1));
                int newY = Integer.parseInt(place.substring(2, 3));

                int row1 = x;
                int row2 = Math.abs(y - 8);
                int row3 = newX;
                int row4 = Math.abs(newY - 8);
                
                dist1 = row3 - row1;
                dist2 = row4 - row2;

                canvas.piece = "";
                canvas.num   = 0;

                try {
                    game.movePiece(dist1, dist2, row1, row2);
                } catch (Exception j) {}
            }
        });

        canvas.addMouseMotionListener(new MouseInputAdapter() {
            public void mouseDragged(MouseEvent e) {
                int xPos = e.getX();
                int yPos = e.getY();

                canvas.xPos = xPos;
                canvas.yPos = yPos;
                
                if (clicked)
                    return;
                
                clicked = true;
                String place = getSquare(e.getX(), e.getY());

                x = Integer.parseInt(place.substring(0, 1));
                y = Integer.parseInt(place.substring(2, 3));

                String thing = game.findPiece(x, Math.abs(y - 8));
                int space    = thing.indexOf(" ");

                piece = thing.substring(0, space);
                num   = Integer.parseInt(thing.substring(space + 1, thing.length()));
                
                canvas.piece = piece;
                canvas.num   = num;
            }
        });

        this.setLayout(new BorderLayout());

        button = new JButton("button");
        button.addActionListener(this);
        button.setActionCommand("activate");

        topPanel = new JPanel();
        topPanel.setBackground(Color.BLACK);
        topPanel.add(button);

        info = new JTextField(20);
        squarePlace = new JTextField(20);
        topPanel.add(info);
        topPanel.add(squarePlace);
        
        this.add(topPanel, BorderLayout.NORTH);
        this.add(canvas, BorderLayout.CENTER);

        running = false;
        clicked   = false;

        this.setPreferredSize(new Dimension(width, height));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Starts the timer to listen to the window
     */
    public void startTimer() {
        running = true;
        t = new Timer(delay, this);
        t.setActionCommand("timerFired");
        t.start();
    }

    /**
     * Turns the x and y coordinates into the square on the board
     * @param xInPixels the x coordinate
     * @param yInPixels the y coordinate
     * @return a {@code String} containing the row (x) and column (y)
     */
    public String getSquare(int xInPixels, int yInPixels) {
        int x = (xInPixels - 70) / squareSize;
        int y = Math.abs(((yInPixels - 51) / squareSize) - 8);
        return "" + x + " " + y;
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("timerFired")) {
            canvas.repaint();
        }
    }
}
