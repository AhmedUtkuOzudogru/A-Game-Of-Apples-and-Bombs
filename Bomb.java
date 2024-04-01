import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Bomb implements InteractableDrawing {
    private static final int BOMB_WIDHT = 30;
    private static final int BOMB_HEIGHT = 30;
    private int xLeft;
    private int yTop;
    private int speed;
    private GameFrame gameFrame;
        

    private Rectangle bomb;

    public Bomb( GameFrame gameFrame,int speed) {
        Random rand= new Random();
        this.speed = speed;
        this.gameFrame = gameFrame;
        this.xLeft=(int)gameFrame.getWidth();
        this.yTop= (int) rand.nextInt((int)gameFrame.getHeight());
        this.bomb= new Rectangle(xLeft, yTop, BOMB_WIDHT, BOMB_HEIGHT);
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(xLeft,yTop,BOMB_WIDHT,BOMB_HEIGHT);
        g.setColor(Color.BLACK);
        g.fillOval(xLeft, yTop, BOMB_WIDHT, BOMB_HEIGHT);
    }

    @Override
    public void interact(Ship s) {
        gameFrame.decraseLife();
    }

    @Override
    public boolean intersects(Ship s) {
        return bomb.intersects(s.getShip());
    }

    @Override
    public boolean moveLeft(int speed) {
        xLeft -= speed; 
        bomb.setLocation(xLeft, yTop); 
    

        if (xLeft + BOMB_WIDHT <= 0) {
            return false; 
        }
        return true; 
    }

}
