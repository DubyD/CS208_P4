/**
 * Author: Ali Rohani
 */

import javax.swing.*;
import java.awt.*;

public class GameEnded extends JPanel {

    private static final String INSTRUCTIONS =  "Instructions:\nYou have reached the exit gate."+
                                                "\nThank you for playing our game!";
    private JTextArea prompt;
    private JButton replayButton;
    private JButton exitButton;
    private JPanel buttonPanel;

    public GameEnded() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        prompt = new JTextArea(INSTRUCTIONS);

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

        this.setLayout(new BorderLayout(10, BoxLayout.Y_AXIS));
        this.add(prompt, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.NORTH);
    }

    public JButton getReplayButton() {
        return replayButton;
    }
    public JButton getExitButton() {
        return exitButton;
    }
    @Override
    public String toString(){
        return "Game over Screen";
    }

}
