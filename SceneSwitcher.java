/**
 * @author Will Duby
 * @author Gus Warmington
 * @author Ali
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceneSwitcher {
    private final JFrame frame;
    private GameScene gameScreen;
    private TitleScene menuScreen;
    private GameEnded finishScreen;

    public SceneSwitcher(JFrame frame) {
        this.frame = frame;
        this.gameScreen = null;
        this.menuScreen = null;
        this.finishScreen = null;
        this.showMenu();
    }

    private void showMenu(){
        this.frame.getContentPane().removeAll();
        menuScreen = new TitleScene();
        menuScreen.getExitButton().addActionListener(e ->{
            System.exit(0);
        });
        menuScreen.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(menuScreen.getSelectedOption() > 0){
                    showGame(menuScreen.getSelectedOption());
                }
            }
        });
        menuScreen.setOpaque(true);
        this.frame.setContentPane(menuScreen);

        this.frame.revalidate();

    }

    private void showGame(int players){
        this.frame.getContentPane().removeAll();
        gameScreen = new GameScene(players, this);


        gameScreen.getExitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMenu();
                //finishScreen(gameScreen.maze.getEndNode()); -> needs to be tied to game finishing
            }
        });
        gameScreen.setVisible(true);
        gameScreen.setOpaque(true);
        this.frame.setContentPane(gameScreen);
        this.frame.pack();
        this.frame.setVisible(true);

        gameScreen.takeTurn();
        gameScreen.requestFocusInWindow();


    }

    public void finishScreen(RoomNode winners){
        frame.getContentPane().removeAll();
        finishScreen = new GameEnded(winners);
        frame.setContentPane(finishScreen);

        finishScreen.getReplayButton().addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                showMenu();
            }
        });
        finishScreen.getExitButton().addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.revalidate();
    }



    /**
     * Getters
     */

    public TitleScene getMenuScreen() {
        return menuScreen;
    }
    public GameScene getGameScreen() {
        return gameScreen;
    }
    public Frame getFrame() {
        return frame;
    }
    public GameEnded getFinishedScreen() {
        return finishScreen;
    }

    @Override
    public String toString() {
        return "SceneSwitcher is an abstract concept used as the brain of the program."+
                " This deals with bridging the gap between gui, and backend.";
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SceneSwitcher that = (SceneSwitcher) obj;
        return this.getFrame().equals(that.getFrame()) &&
                this.getGameScreen().equals(that.getGameScreen()) &&
                this.getMenuScreen().equals(that.getMenuScreen()) &&
                this.getFinishedScreen().equals(that.getFinishedScreen());
    }

}

