/**
 * Author: Ali Rohani
 */

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameEnded extends JPanel {

    private static final String WIN = "You have reached the exit gate.\nThank you for playing our game!\n would you like to play again?";
    private static final String LOSS = "Everyone has fallen to traps.\nWould you like to play again?";

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
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center alignment for buttons
        buttonPanel.add(replayButton);
        buttonPanel.add(exitButton);
        RoomNode displayRoom = new RoomNode(-1,-1);

        this.add(prompt, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.NORTH);

        this.add(displayRoom);
    }

    public JButton getReplayButton() {
        return replayButton;
    }
    public JButton getExitButton() {
        return exitButton;
    }
    public void setPrompt(boolean didYouWin){
        if(didYouWin){
            prompt.setText(WIN);
        }else{
            prompt.setText(LOSS);
        }
    }

    @Override
    public String toString(){
        return "Game over screen, shows if and who has won.";
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
