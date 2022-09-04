package com.company;

import javax.swing.*;
import java.awt.*;

public class GameObject extends JLabel {
    private final int speed;

    Repo repo;


    public GameObject(int x, int y, int speed) {
        this.speed = speed;
        repo = new Repo();
        this.setBounds(x, y, repo.DEFAULT_BAGS_WIDTH, repo.DEFAULT_BAGS_HEIGHT);

    }


    public void move() {
        this.setLocation(this.getX(), this.getY() + speed);
    }

}
