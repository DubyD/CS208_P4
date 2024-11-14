/**
 * Author: Ali Rohani
 */

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameEnded extends JPanel {

    private static final String WIN = "You have reached the exit gate!\nThank you for playing our game!\n would you like to play again?";
    private static final String LOSS = "Everyone has fallen to traps.\nWould you like to play again?";

    private JTextArea prompt;
    private JButton replayButton;
    private JButton exitButton;
    private JPanel buttonPanel;
    public GameEnded(RoomNode end) {
        setLayout(new GridLayout(4,3));
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        JLabel img = new JLabel();
        this.add(new JLabel());
        this.add(end); //end room in middle top, blank jlabels as padding
        this.add(img);
        System.out.println(end);
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

//        c.gridx = 0;
//        c.gridy = 1;
//        c.gridwidth = 3;
        if(end.isEmpty()){
            img.setIcon(new ImageIcon("defeat.gif"));
            prompt.setText(LOSS);
        }
        else{
            img.setIcon(new ImageIcon("victory.gif"));
            prompt.setText(WIN);
        }
        this.add(new JLabel());
        this.add(prompt);
        this.add(new JLabel());
//        c.gridwidth = 1;
//        c.gridx = 1;
//        c.gridy = 2;
        this.add(new JLabel());
        this.add(buttonPanel);
        this.add(new JLabel());
        this.revalidate();

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
