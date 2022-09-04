package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameFrame extends JFrame implements KeyListener {
    private Background background;
    private JLabel scoreBar;
    private JCheckBox life;
    private JCheckBox life1;
    private JCheckBox life2;
    private JCheckBox life3;
    private int score;

    public JCheckBox getLife() {
        return life;
    }

    public GameFrame() {

        lifeLeftToPlay();
        scoreBar = new JLabel("Score: " + score);
        scoreBar.setBounds(0, Repo.GAME_BOUNDS_Y, Repo.GAME_BOUNDS_X, Repo.DEFAULT_SCORE_BAR_HEIGTH);
        scoreBar.setBackground(Color.green);
        scoreBar.setOpaque(true);
        this.add(scoreBar);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Repo.GAME_SIZE_X, Repo.GAME_SIZE_Y + Repo.DEFAULT_SCORE_BAR_HEIGTH);
        this.setLayout(null);
         this.setFocusable(true);
        this.addKeyListener(this);
        this.setResizable(false);
        this.setFocusable(true);
        this.setTitle("Trash Game");


        Bin player = new Bin(Repo.DEFAULT_POSITION_X, Repo.DEFAULT_POSITION_y, Repo.DEFAULT_PLAYER_WIDTH, Repo.DEFAULT_PLAYER_HEIGHT, Repo.DEFAULT_SPEED);
        background = new Background(player, Repo.GAME_SIZE_X, Repo.GAME_SIZE_Y, this);
        this.add(background);
        setFocusable(true);
        this.setVisible(true);
    }

    private void lifeLeftToPlay() {
        this.life = new JCheckBox();
        this.life1 = new JCheckBox();
        this.life2 = new JCheckBox();
        this.life3 = new JCheckBox();
//        life.setText("hold on! ! ! , last chance ");
//        life1.setText("one last life...");
//        life2.setText("two more, be careful");
//        life3.setText("three more");

        life.setBounds(0, 0, 150, 20);
        life1.setBounds(150, 0, 150, 20);
        life2.setBounds(300, 0, 150, 20);
        life3.setBounds(450, 0, 150, 20);
        life.setBackground(Color.green);
        life1.setBackground(Color.green);
        life2.setBackground(Color.green);
        life3.setBackground(Color.green);

        life.setSelected(true);
        life1.setSelected(true);
        life2.setSelected(true);
        life3.setSelected(true);
        this.add(life);
        this.add(life1);
        this.add(life2);
        this.add(life3);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        background.keyPressed(e.getKeyChar());
    }

    public void scoreCount() {
        scoreBar.setText("Score: " + score);
    }


    public void addScore() {
        this.score++;
    }

    public void minusScore() throws InterruptedException {
        if (this.score > 0) {
            this.score--;
        } else {
            if (this.life3
                    .isSelected()) {
                this.life3.setText("three more");
                this.life3.setSelected(false);

            } else if (this.life2
                    .isSelected()) {
                this.life2.setText("two more, be careful");
                this.life2.setSelected(false);
            } else if (this.life1
                    .isSelected()) {
                this.life1.setText("one last life...");
                this.life1.setSelected(false);

            } else if (this.life
                    .isSelected()) {
                // wait();
                this.life.setText("SORRY, YOU LOOSE");
                this.life.setSelected(false);

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        background.removeOrder(e.getKeyChar());
    }
}