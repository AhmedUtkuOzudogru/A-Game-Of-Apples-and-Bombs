import java.awt.*;
public class Ship implements InteractableDrawing{
    private static final int SHIP_WIDHT = 80;
    private static final int SHIP_HEIGHT = 30;
    private int xLeft;
    private int yTop;

    private Rectangle ship;
    private String name;

    public Ship(String name){
        this.name = name;
        this.ship = new Rectangle (SHIP_WIDHT, SHIP_HEIGHT);

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor (Color.yellow);
        g2d.fillRect (xLeft, yTop, SHIP_WIDHT, SHIP_HEIGHT);
        g2d.setColor (Color.BLACK);
        Font shipNameFont = new Font ("Times New Roman", Font.BOLD, 16);
        g2d.setFont (shipNameFont);
        g2d.drawString (this.name ,ship.x + 10, ship.y + 20);
    }

    @Override
    public void interact(Ship s) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public boolean intersects(Ship s) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean moveLeft(int speed) {
        // TODO Auto-generated method stub
        return false;
    }

    public void moveTo(int x, int y) {
        xLeft=x;
        yTop=y;
        ship.setLocation(xLeft,yTop);
    }

    public Rectangle getShip() {
        return this.ship;
    }

}
