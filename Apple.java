import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Apple implements InteractableDrawing {
    private static final int APPLE_WIDHT = 30;
    private static final int APPLE_HEIGHT = 30;


    private int xLeft;
    private int yTop;
    private int speed;
    private Rectangle apple;
    private GameFrame gameFrame;


    public Apple(GameFrame gameFrame,int speed){
        Random rand= new Random();
        this.gameFrame=gameFrame;
        this.speed=speed;
        this.xLeft=970;
        this.yTop= (int) rand.nextInt(970);
        this.apple= new Rectangle(xLeft, yTop, APPLE_WIDHT, APPLE_HEIGHT);

  
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(xLeft,yTop,APPLE_WIDHT,APPLE_HEIGHT);
        g.setColor(Color.RED);
        g.fillOval(xLeft, yTop, APPLE_WIDHT, APPLE_HEIGHT);

    }

    @Override
    public void interact(Ship s) {
        gameFrame.increaseScore();
        
    }

    @Override
    public boolean intersects(Ship s) {
        return apple.intersects(s.getShip());
    }

    @Override
    public boolean moveLeft(int speed) {
        xLeft -= speed; 
        apple.setLocation(xLeft, yTop); 
    

        if (xLeft + APPLE_WIDHT <= 0) {
            return false; 
        }
        return true; 
    }
    

}
