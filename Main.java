import javax.swing.*;

public class Main {
    //testing testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame1 = new JFrame("Mazed and Confused");
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.setSize(800, 600);
            frame1.setContentPane(new TitleScene());

            new SceneSwitcher(frame1);
            frame1.setLocationRelativeTo(null);
            frame1.setVisible(true);
        });
        JFrame frame2 = new JFrame("Maze Game");
        frame2.setSize(800, 600);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setResizable(false);
        frame2.add(new GameFrame());
        frame2.setVisible(true);
    }
}
