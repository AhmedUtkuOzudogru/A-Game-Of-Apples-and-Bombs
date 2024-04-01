import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;

public class GameFrame extends JFrame {
    protected String name;
    protected int speed, score, life;

    public GameFrame(String name, int speed) {
        super("Life: 3 - Score: 0");


        this.name = name;
        this.speed = speed;
        this.score = 0;
        this.life = 3;
        constructFrame();
       
    }
    public void constructFrame(){
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLUE);
        setSize(1000, 1000);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent gamePanel = new GamePanel(this.name, this.speed, this);
        add(gamePanel);

        setVisible(true);
    }
    public void updateTitle() {
        setTitle("Life: " + getLife() + " Score: " + getScore());
    }

    public int getScore() {
        return score;
    }

    public int getLife() {
        return life;
    }

    public void increaseScore() {
       this.score++;
       updateTitle();
    }
    public void decraseLife(){
        this.life--;
        updateTitle();
    }
    public boolean isFinished(){
        return life<=0;
    }
    public void restart(){
        this.life=3;
        this.score=0;
        updateTitle();
    }

}
