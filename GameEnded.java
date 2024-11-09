import javax.swing.*;
import java.awt.*;

public class GameEnded extends JPanel {
    public GameEnded() {
        setLayout(new BorderLayout());
        JLabel gameOverLabel = new JLabel("You finished the game", SwingConstants.CENTER);
        JTextArea instructions = new JTextArea("Instructions:\nYou have reached the end gate.\nThank you for playing our game!");
        instructions.setEditable(false);
        instructions.setOpaque(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setFocusable(false);

        add(gameOverLabel, BorderLayout.NORTH);
        add(instructions, BorderLayout.CENTER);
    }
}
