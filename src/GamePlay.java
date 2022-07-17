import javax.swing.*;
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
    private int ballXdir = -1;
    private int ballYdir = -2;

    public GamePlay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
