import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class InstructionsLabel extends JLabel {
    private final String[] message;
    int messageNum;
    public InstructionsLabel(){
        message = new String[]{"Watch out for traps!",  "Be first to escape...", "Click to navigate rooms."};
        messageNum = 0;
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {

            @Override
            public void run() {
                updateText();
            }
        };
            timer.scheduleAtFixedRate(tt, (500), (15 * 1000));
    }
    public void updateText(){
        this.setText(message[messageNum]);
        messageNum++;
        if(messageNum >= message.length){
            messageNum = 0;
        }
    }
}
