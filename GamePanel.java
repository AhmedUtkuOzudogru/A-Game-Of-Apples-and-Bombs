import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class GamePanel extends JComponent {
    protected Ship ship;
    protected ArrayList<InteractableDrawing> objects;
    protected Timer timer;
    protected int speed;
    protected GameFrame gameFrame;
    protected String name;

    public GamePanel(String name, int speed, GameFrame GameFrame) {
        this.name = name;
        ship = new Ship(name);
        TimerListener timerListener = new TimerListener();
        timer = new Timer(100, timerListener);
        timer.start();
        this.speed = speed;
        this.gameFrame = GameFrame;
        setLayout(new BorderLayout());
        objects = new ArrayList<>();
        MouseMove mouseListener = new MouseMove();
        this.addMouseMotionListener(mouseListener);
        gameFrame.add(this);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.ship.draw(g2d);

        for (InteractableDrawing obj : objects) {
            obj.draw(g2d);
        }
    }

    public void moveGameObjects() {
        for (int i = 0; i < this.objects.size(); i++) {
            InteractableDrawing obj = this.objects.get(i);
            if (!obj.moveLeft(speed)) {
                this.objects.remove(obj);
            }
        }

    }

    public void addGameObjects() {
        Random rand = new Random();
        int operator = (int) rand.nextInt(3);
        switch (operator) {
            case 1:
                objects.add(new Apple(gameFrame, speed));
                break;
            case 2:
                objects.add(new Bomb(gameFrame, speed));
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    public class MouseMove implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            ship.moveTo(x, y);

            for (int i = 0; i < objects.size(); i++) {
                InteractableDrawing obj = objects.get(i);
                if (obj.intersects(ship)) {
                    obj.interact(ship);
                    objects.remove(obj);
                }
            }
            repaint();
            if (gameFrame.isFinished()) {
                int option = JOptionPane.showConfirmDialog(gameFrame, "Game Over\n Score" + gameFrame.getScore());
                if (option == JOptionPane.YES_OPTION) {
                    gameFrame.restart();
                    objects.clear();
                    repaint();
                    

                } else if (option == JOptionPane.NO_OPTION) {
                    gameFrame.dispose();

                } else if (option == JOptionPane.CANCEL_OPTION) {
                    gameFrame.dispose();
                }
            }
        }

    }

    public class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            moveGameObjects();
            addGameObjects();
            repaint();
        }

    }

}
