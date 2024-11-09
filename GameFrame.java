import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GameFrame  extends JPanel implements ActionListener {
    Timer mainTimer;
    Player player;

    public GameFrame() {
        setFocusable(true);
        player = new Player(100, 100);
        addKeyListener(new KeyAdapt(player));
        mainTimer = new Timer(10, this);
        mainTimer.start();
    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("Maze is running",100,100);
        player.draw(g2d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        player.update();
    }
}
