import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceneSwitcher {
    private JFrame frame;
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
        menuScreen.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(menuScreen.getSelectedOption() > 0){
                    showGame(menuScreen.getSelectedOption());
                }
            }
        });
        this.frame.setContentPane(menuScreen);

        this.frame.revalidate();

    }

    private void showGame(int players){
        this.frame.getContentPane().removeAll();
        gameScreen = new GameScene(players);
        this.frame.setContentPane(gameScreen);


        gameScreen.getExitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMenu();
            }
        });
        this.frame.revalidate();
    }

    private void finishScreen(){
        frame.getContentPane().removeAll();
        finishScreen = new GameEnded();
        frame.setContentPane(finishScreen);
        /**
        finishScreen.getPlayAgainButton().addActionListener(new ActionListener() {
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
         */
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

