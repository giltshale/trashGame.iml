package com.company;

import javax.swing.*;

public class Seeds extends GameObject {

    public Seeds(int x, int y, int speed) {
        super(x, y, speed);
        this.setIcon(new ImageIcon("bomb.png"));
    }

}

