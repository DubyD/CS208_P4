/**
 * @author Gus Warmington
 * simple JLabel extended object, with text that cycles through an array of Strings.
 * Controlled by a Timer.
 */

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class InstructionsLabel extends JLabel {
    private final String[] message = new String[]{"Watch out for traps!",  "Be first to escape...", "Click to navigate rooms."};
    int messageNum;
    ///Default constructor - Establishes
    public InstructionsLabel(){
        messageNum = 0; //index for message[]
        //Timer set up:
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {

            @Override
            public void run() {
                updateText();
            }
        };
            timer.scheduleAtFixedRate(tt, (500), (15 * 1000)); //initial delay: .5 seconds
                                                               //delay after first: 15 seconds
    }
    ///for use within the TimerTask's run operation
    ///Cycles to the next String in message[] and loops if at the end
    private void updateText(){
        this.setText(message[messageNum]);
        messageNum++;
        if(messageNum >= message.length){
            messageNum = 0;
        }
    }
}
