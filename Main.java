import javax.swing.*;

public class Main {
    //testing testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Mazed and Confused");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setContentPane(new TitleScene());
            frame.setPreferredSize(frame.getSize());

            new SceneSwitcher(frame);
//            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
