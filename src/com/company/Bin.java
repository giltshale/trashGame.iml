package com.company;
import javax.swing.*;
import java.util.HashSet;
import java.util.Set;


public class Bin extends JLabel {
    private final int speed;
    private final int BORDER_X;
    private final int BORDER_Y;
    private final Set<Character> movementSet;

    public Bin(int x, int y, int width, int height, int speed) {
        Repo repo = new Repo();

        BORDER_X = repo.GAME_BOUNDS_X;
        BORDER_Y = repo.GAME_BOUNDS_Y;
        this.setBounds(x, y, width, height);
        this.setIcon(new ImageIcon("finalBin_trueSize.png"));


        this.speed = speed;
        movementSet = new HashSet<>();
    }

    public void keyPressed(char key) {
        if (!(key == 'a' || key == 'w' || key == 's' || key == 'd')) { // if invalid input, return
            return;
        }
        movementSet.add(key);
        for (char order : movementSet) {
            switch (order) {
                case 'a': {
                    if (this.getX() - speed < 0) {
                        this.setLocation(0, this.getY());
                    } else setLocation(this.getX() - speed, this.getY());
                }
                break;
                case 'd': {
                    if (this.getX() + this.getWidth() + speed > BORDER_X) {
                        setLocation(BORDER_X - this.getWidth(), this.getY());
                    } else setLocation(this.getX() + this.speed, this.getY());
                }
                break;
                case 'w': {
                    if (this.getY() - speed < 0) {
                        setLocation(this.getX(), 0);
                    } else setLocation(this.getX(), this.getY() - this.speed);
                }
                break;
                case 's': {
                    if (this.getY() + this.getHeight() + speed > BORDER_Y) {
                        setLocation(this.getX(), BORDER_Y - this.getHeight());
                    } else setLocation(this.getX(), this.getY() + this.speed);
                }
                break;
                default:
            }
        }
    }

    // copy of Shape.intersects
    public boolean intersects(GameObject r) {
        int tw = this.getWidth();
        int th = this.getHeight();
        int rw = r.getWidth();
        int rh = r.getHeight();
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        int tx = this.getX();
        int ty = this.getY();
        int rx = r.getX();
        int ry = r.getY();
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

    public void removeOrder(char key){
        movementSet.remove(key);
    }

}
