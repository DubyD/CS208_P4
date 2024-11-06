import javax.swing.*;
import java.awt.*;

public class door extends JPanel implements Runnable {
    private boolean flag;
    private Thread t;

    public door() {
        // Start the animation thread
        t = new Thread(this);
        flag = true;
        t.start();
    }

    public void run() {
        while (true) {
            try {
                // Toggle the flag to open/close the door
                flag = !flag;
                Thread.sleep(1000); // Sleep for 1 second
                repaint(); // Request a repaint
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[] x = {300, 400, 500};
        int[] y = {300, 200, 300};

        // Draw the door frame
        g.drawPolygon(x, y, 3);
        g.setColor(new Color(100, 102, 102));
        g.fillPolygon(x, y, 3);
        g.drawRect(300, 300, 200, 100);
        g.setColor(Color.yellow);
        g.fillRect(300, 300, 200, 100);

        // Draw the door based on the flag
        if (flag) {
            g.drawRect(375, 350, 50, 50);
            g.setColor(new Color(120, 0, 0));
            g.fillRect(375, 350, 50, 50);
            g.setColor(Color.black);
            g.fillRect(375, 350, 50, 50);
            g.setColor(new Color(120, 0, 0));

            // Draw the door opening
            int[] x1 = {375, 390, 390, 375, 375};
            int[] y1 = {350, 360, 390, 400, 350};
            g.fillPolygon(x1, y1, 5);
            int[] x2 = {425, 410, 410, 425, 425};
            int[] y2 = {350, 360, 390, 400, 350};
            g.fillPolygon(x2, y2, 5);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Door Animation");
        door doorAnimation = new door();
        frame.add(doorAnimation);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}