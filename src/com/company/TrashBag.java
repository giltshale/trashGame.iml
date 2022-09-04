package com.company;

import javax.swing.*;

public class TrashBag extends GameObject {

    public TrashBag(int x, int y, int speed) {
        super(x, y, speed);
        this.setIcon(new ImageIcon("finalBag_trueSize.png"));
    }


}
