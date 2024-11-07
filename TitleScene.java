import javax.swing.*;

public class TitleScene {

    private static final String INSTRUCTIONS = "Traverse a maze using 'W,A,S,D' or 'up, left, down, right' respectfully.\n"+
            "Avoid pitfalls to survive and find the exit.\n"+
            "Most importantly, have fun";

    private JButton startButton;
    private JButton exitButton;
    private JLabel titleLabel;
    private JTextArea instructions;

    public TitleScene() {
        startButton = new JButton("Start");
        titleLabel = setTitle();
        instructions = setInstructions();
    }

    private JLabel setTitle() {
        JLabel title = new JLabel("Mazed and Confused");
    }

    private JLabel setInstructions() {
        JLabel instructions = new JLabel(INSTRUCTIONS);
        instructions.setText();
    }


}
