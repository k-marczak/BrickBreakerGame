import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

    // Starting values of the game
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8;

    // pedal and ball

    private int playerX = 310;
    private int ballPosX = 100;
    private int ballPosY = 100;
    private int ballXdir = -3;
    private int ballYdir = -5;

    private MapGenerator map;


    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

        map = new MapGenerator(3, 7);


    }


    public void paint(Graphics g) {
        requestFocus(true);
        g.setColor(Color.white);
        g.fillRect(1, 1, 692, 592);
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(682, 0, 3, 592);


        // palet
        g.setColor(Color.blue);
        g.fillRect(playerX, 550, 100, 8);

        // Ball
        g.setColor(Color.green);
        g.fillOval(ballPosX, ballPosY, 20, 20);

        map.draw((Graphics2D) g);

        g.dispose();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        timer.start();

        if (new Rectangle(ballPosX, ballPosY, 20, 30).intersects(new Rectangle(playerX, 550, 100, 8))) {
            ballYdir = -ballYdir;
        }

        for (int i = 0; i < map.map.length; i++) {
            for (int j = 0; j < map.map[0].length; j++) {
                if (map.map[i][j] > 0) {
                    int brickX = j * map.brickWidth + 80;
                    int brickY = i * map.brickHeight + 50;
                    int brickWidth = map.brickWidth;
                    int brickHeight = map.brickHeight;

                    Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                    Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
                    Rectangle brickRect = rect;

                    if (ballRect.intersects(brickRect)) {
                        map.setbrickValue(0, i, j);
                        totalBricks--;
                        score += 5;


                        if (ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width) {
                            ballXdir = -ballXdir;
                        } else {
                            ballYdir = -ballYdir;
                        }
                    }
                }
            }
        }

        ballPosX += ballXdir;
        ballPosY += ballYdir;

        if (ballPosX <= 0) {
            ballXdir = -ballXdir;
        }

        if (ballPosX > 670) {
            ballXdir = -ballXdir;
        }

        if (ballPosY < 0) {
            ballYdir = -ballYdir;
        }


        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else
                moveLeft();
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX > 690) {
                playerX = 690;
            } else
                moveRight();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void moveRight() {
        play = true;
        playerX = playerX + 30;
    }

    public void moveLeft() {
        play = true;
        playerX = playerX - 30;
    }
}
