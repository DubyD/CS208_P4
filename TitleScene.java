import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScene extends JPanel {

    private static final String INSTRUCTIONS = "Traverse a maze using 'W,A,S,D' or 'up, left, down, right' respectively.\n" +
            "Avoid pitfalls to survive and find the exit.\n" +
            "Most importantly, have fun";

    private JButton startButton;
    private JButton exitButton;
    private JLabel titleLabel;
    private JTextArea instructions;
    private JComboBox<String> dropDownMenu; // Drop-down menu declaration

    public TitleScene() {
        startButton = new JButton("Start");
        exitButton = new JButton("Exit");
        titleLabel = setTitle();
        instructions = setInstructions();

        // Initialize drop-down menu
        String[] menuOptions = {"Select an option", "1 player", "2 player", "3 player"};
        dropDownMenu = new JComboBox<>(menuOptions);

        // Add action listener to handle drop-down menu selection
        dropDownMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) dropDownMenu.getSelectedItem();
                JOptionPane.showMessageDialog(TitleScene.this, "You selected: " + selectedOption);
            }
        });

        // Set the layout for the main panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create a panel for the buttons and set its layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center alignment for buttons
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);

        // Add components to the main panel
        this.add(Box.createVerticalStrut(150)); // Add vertical space at the top
        this.add(titleLabel); // Adding title label
        this.add(instructions); // Adding instructions
        this.add(dropDownMenu); // Adding the drop-down menu
        this.add(buttonPanel); // Adding the button panel
    }

    private JLabel setTitle() {
        JLabel reply = new JLabel("Mazed and Confused");
        reply.setFont(new Font("Arial", Font.BOLD, 24)); // Set font for the title
        reply.setAlignmentX(CENTER_ALIGNMENT); // Center alignment
        return reply;
    }

    private JTextArea setInstructions() {
        JTextArea reply = new JTextArea();
        reply.setMaximumSize(new Dimension(450, 100));
        reply.setText(INSTRUCTIONS);
        reply.setEditable(false);
        reply.setWrapStyleWord(true); // Wrap text
        reply.setLineWrap(true); // Enable line wrap
        reply.setAlignmentX(CENTER_ALIGNMENT); // Center alignment
        return reply;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Mazed and Confused");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setContentPane(new TitleScene());

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
