/**
 * Author: Ali Rohani
 */

import javax.swing.*;
import java.awt.*;


public class TitleScene extends JPanel {

    private static final String INSTRUCTIONS = "How to play:\n"+
            "Traverse a maze using 'W,A,S,D' or 'up, left, down, right' respectively.\n" +
            "Avoid pitfalls to survive and find the exit.\n" +
            "Most importantly, have fun!";

    private JButton startButton;
    private JButton exitButton;
    private JLabel titleLabel;
    private JTextArea instructions;
    private JComboBox<Integer> dropDownMenu; // Drop-down menu declaration
    private JPanel buttonPanel;
    private static int selectedOption;

    public TitleScene() {
        startButton = new JButton("Start");
        exitButton = new JButton("Exit");
        titleLabel = setTitle();
        instructions = setInstructions();
        dropDownMenu = setDropDownMenu();
        selectedOption = 0;



        // Set the layout for the main panel
        this.setLayout(new GridLayout(4,3));

        // Create a panel for the buttons and set its layout
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center alignment for buttons
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);

        // Add components to the main panel

        this.add(new JLabel()); // Add vertical space at the top
        JLabel img = new JLabel(new ImageIcon("title.gif"), JLabel.CENTER);
        this.add(img);
        this.add(new JLabel());

        this.add(new JLabel());
        this.add(instructions); // Adding instructions
        this.add(new JLabel());

        this.add(new JLabel());
        JPanel dropDownPanel = new JPanel();
        dropDownPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dropDownPanel.add(dropDownMenu); // Adding the drop-down menu
        this.add(dropDownPanel);
        this.add(new JLabel());

        this.add(new JLabel());
        this.add(buttonPanel); // Adding the button panel
        this.add(new JLabel());

        /**
         * This is not OOP and needs to be handled in SceneSwitcher to allow the program
         * to function as expected. The gameFrame also isn't saved anywhere so unexpected
         * consquences can occur. It was higher in the constructor, moved here for
         * readability -WD

         Author: Ali
         // Add action listener to the start button
         startButton.addActionListener(new ActionListener() {
        //@Override
        public void actionPerformed(ActionEvent e) {
        mainFrame.dispose(); // Close the current frame
        JFrame gameFrame = new JFrame("Maze Game");
        gameFrame.setSize(800, 600);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.add(new GameFrame());
        gameFrame.setVisible(true);
        }
        });*/
    }

    private JComboBox<Integer> setDropDownMenu() {
        // Initialize drop-down menu
        Integer[] menuOptions = {-1,1, 2, 3, 4};
        JComboBox<Integer> reply = new JComboBox<>(menuOptions);
        reply.setMinimumSize(new Dimension(250, 30));
        reply.setMaximumSize(new Dimension(250, 30));
        reply.addActionListener(e ->{
            if(reply.getSelectedItem() != null){
                selectedOption = (int) reply.getSelectedItem();
            }

        });
        reply.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Integer && (Integer) value == -1) {
                    setText("Select number of players");
                } else {
                    setText(value.toString());
                }
                return this;
            }
        });

        return reply;
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

    public JButton getStartButton() {
        return startButton;
    }
    public JButton getExitButton() {
        return exitButton;
    }
    public int getSelectedOption() {
        return selectedOption;
    }

    @Override
    public String toString(){
        return  "This is the main menu of a maze game with instructions on how to play" +
                "and where you select the number of players playing";
    }
    
    //@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        TitleScene that = (TitleScene) obj;
        if (TitleScene.selectedOption != that.getSelectedOption()) {
            return false;
        }
        return true;
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
