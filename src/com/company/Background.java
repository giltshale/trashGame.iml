package com.company;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Background extends JLabel {


    private final Bin player;
    private final GameFrame myFrame;
    //private final Repo repo;
    private boolean gameRunning;


    Random random = new Random();

    public Background(Bin player, int x, int y, GameFrame myFrame) {
        // repo = new Repo();

        this.myFrame = myFrame;
        this.setSize(x, y);
        this.player = player;
        this.add(player);
        this.gameRunning = true;
        moveTrashBags();
        this.setFocusable(true);
    }

    private void moveTrashBags() {
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        this.initList(gameObjects);
        new Thread(() -> {
            while (true) {
                boolean trash;
                for (GameObject gameObject : gameObjects) {
                    trash = gameObject instanceof TrashBag;
                    if (this.gameRunning) {
                        gameObject.move();
                        if (CheckCollision(gameObject) && trash) {
                            this.myFrame.addScore();
                            repositionGameObject(gameObject);
                        } else if (CheckCollision(gameObject)) {
                            try {
                                // notifyAll();
                                this.myFrame.minusScore();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            repositionGameObject(gameObject);
                        } else if (CheckIfOutsideFieldBoundary(gameObject)) {
                            repositionGameObject(gameObject);
                        } else if (myFrame.getLife().getText().equals("SORRY, YOU LOOSE")) {
                            gameRunning = false;
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "You Loose,stop program and try again");
                    }
                    this.myFrame.scoreCount();
                    repaint();

                    try {
                        sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }


            }

        }).start();
    }

    public void keyPressed(char key) {
        if (gameRunning) {
            player.keyPressed(Character.toLowerCase(key));
        }
    }

    public void removeOrder(char key) {
        if (gameRunning) {
            player.removeOrder(Character.toLowerCase(key));
        }
    }

    private boolean CheckCollision(GameObject gameObjects) {
        return player.intersects(gameObjects);
    }

    private boolean CheckIfOutsideFieldBoundary(GameObject gameObject) {
        return gameObject.getY() > Repo.GAME_SIZE_Y + Repo.DEFAULT_PLAYER_HEIGHT;
    }

    private void initList(ArrayList<GameObject> gameObjects) {
        // Start with between 5 and 12 garbage gameObjects
        for (int i = 0; i < random.nextInt(8) + 5; i++) {
            addGameObjectToList(gameObjects);
        }
    }

    private void addGameObjectToList(ArrayList<GameObject> gameObjects) {
        //Each garbage bag gets a random position on the x-axis and a random speed between 6 and 10

        TrashBag bag = new TrashBag(random.nextInt(Repo.GAME_BOUNDS_X - Repo.DEFAULT_BAGS_WIDTH), (random.nextInt(Repo.GAME_SIZE_Y) + 100) * (-1), random.nextInt(6) + 4);
        gameObjects.add(bag);
        this.add(bag);
        Seeds bomb = new Seeds(random.nextInt(Repo.GAME_BOUNDS_X - Repo.DEFAULT_BAGS_WIDTH), (random.nextInt(Repo.GAME_SIZE_Y) + 100) * (-1), random.nextInt(6) + 4);
        gameObjects.add(bomb);
        this.add(bomb);

    }

    private void repositionGameObject(GameObject gameObject) {
        gameObject.setLocation(random.nextInt(Repo.GAME_SIZE_X - Repo.DEFAULT_BAGS_WIDTH), (random.nextInt(Repo.GAME_SIZE_Y) + 100) * (-1));
    }

}



