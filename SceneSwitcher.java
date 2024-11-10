import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceneSwitcher {
    private JFrame frame;
    private GameFrame gameScreen;
    private TitleScene menuScreen;

    public SceneSwitcher(JFrame frame) {
        this.frame = frame;
        this.gameScreen = null;
        this.menuScreen = null;
        this.showMenu();
    }

    private void showMenu(){
        this.frame.getContentPane().removeAll();
        menuScreen = new TitleScene();
        menuScreen.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGame();
            }
        });
        this.frame.setContentPane(menuScreen);

        this.frame.revalidate();

    }

    private void showGame(){
        this.frame.getContentPane().removeAll();
        gameScreen = new GameFrame();
        this.frame.setContentPane(gameScreen);


        gameScreen.getExitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMenu();
            }
        });
        this.frame.revalidate();
    }



    /**
     * Getters
     */

    public TitleScene getMenuScreen() {
        return menuScreen;
    }
    public GamePanel getGameScreen() {
        return gameScreen;
    }
    public Frame getFrame() {
        return frame;
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
                this.getMenuScreen().equals(that.getMenuScreen());
    }

}

