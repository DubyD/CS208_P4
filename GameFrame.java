import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JPanel implements ActionListener {
    Timer mainTimer;
    Player player;
    List<Wall> walls;

    public GameFrame() {
        setFocusable(true);
        player = new Player(100, 100);
        walls = new ArrayList<>();

        // Create walls
        walls.add(new Wall(200, 150, 10, 300)); // Example wall
        walls.add(new Wall(300, 200, 300, 10)); // Example wall

        addKeyListener(new KeyAdapt(player));
        mainTimer = new Timer(10, this);
        mainTimer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("Maze is running", 100, 100);

        // Draw walls
        for (Wall wall : walls) {
            wall.draw(g2d);
        }

        player.draw(g2d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        player.update(walls); // Pass walls for collision detection
    }
}

