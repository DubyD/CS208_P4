import javax.swing.*;
import java.awt.*;

public class Door{
//    private boolean isOpening; // True if the door is opening, false if closing
//    private int doorWidth; // Width of the door
//    private Thread t;
    private int direction;
    private RoomNode destination;
    private boolean exit;
    public Door() {
//        this.doorWidth = 0; // Start with the door closed
//        this.isOpening = true; // Start the door opening
//        t = new Thread(this);
//        t.start();
    }
    public Door(int direction, RoomNode destination){
        this.direction = direction; //which side of the room it is on
                                    // 0 means up, 1 means right, 2 means down, 3 means left
        this.destination = destination; // where the door leads to
        this.exit = false;
    }

    public RoomNode getDestination() {
        return destination;
    }
    public int getDirection() { return direction;}
    public void setExit() { exit = true;}
    //    public void run() {
//        while (true) {
//            try {
//                // Update the door width based on its state
//                if (isOpening) {
//                    doorWidth += 2; // Increase the width to simulate opening
//                    if (doorWidth >= 200) { // Fully open
//                        doorWidth = 200; // Set to full width
//                        isOpening = false; // Start closing
//                    }
//                } else {
//                    doorWidth -= 2; // Decrease the width to simulate closing
//                    if (doorWidth <= 0) { // Fully closed
//                        doorWidth = 0; // Set to closed
//                        isOpening = true; // Start opening
//                    }
//                }
//                Thread.sleep(50); // Sleep for a short time for smooth animation
//                repaint(); // Request a repaint
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        // Draw the door frame
//        g.setColor(new Color(100, 102, 102));
//        g.fillRect(300, 200, 200, 300); // Door frame
//        g.setColor(Color.yellow);
//        g.fillRect(300, 200, 200, 300); // Door background
//
//        // Draw the door based on its width
//        g.setColor(new Color(120, 0, 0));
//        g.fillRect(300 + (200 - doorWidth), 200, doorWidth, 300); // Draw the door
//
//        // Draw the door handle (fixed position)
//        g.setColor(Color.black);
//        g.fillRect(375, 350, 10, 10); // Handle
//    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Door Animation");
//        Door doorAnimation = new Door();
//        frame.add(doorAnimation);
//        frame.setSize(800, 600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }
}
