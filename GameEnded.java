/**
 * Author: Ali Rohani
 */

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameEnded extends JPanel {

    private static final String YOUWON = "You have reached the exit gate.\nThank you for playing our game!\n would you like to play again?";
    private static final String YOULOST = "You have fallen to traps.\nWould you like to play again?";

    private JTextArea prompt;
    private JButton replayButton;
    private JButton exitButton;
    private JPanel buttonPanel;

    public GameEnded() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        prompt = new JTextArea();
        prompt.setEditable(false);
        prompt.setLineWrap(true);
        prompt.setWrapStyleWord(true);
        prompt.setFocusable(false);

        replayButton = new JButton("Replay");
        exitButton = new JButton("Exit");

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center alignment for buttons
        buttonPanel.add(replayButton);
        buttonPanel.add(exitButton);


        this.add(prompt, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.NORTH);
    }

    public JButton getReplayButton() {
        return replayButton;
    }
    public JButton getExitButton() {
        return exitButton;
    }
    public void setPrompt(boolean didYouWin){
        if(didYouWin){
            prompt.setText(YOUWON);
        }else{
            prompt.setText(YOULOST);
        }
    }

    @Override
    public String toString(){
        return "Game over";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GameEnded gameEnded = (GameEnded) o;
        return Objects.equals(prompt, gameEnded.prompt) &&
               Objects.equals(replayButton, gameEnded.replayButton) &&
               Objects.equals(exitButton, gameEnded.exitButton) &&
               Objects.equals(buttonPanel, gameEnded.buttonPanel);
    }
}
