//imports here
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import static java.awt.event.KeyEvent.*;
import java.util.*;

public class NumberButtons extends Button{
    /*
    *NumberButtons class extends buttons, and allows us to creat button with atributes and their own event listeners.
    * Calculator-style buttons with their own attributes and event listeners.
    */
    private String me;
    private int interationx;
    private int interationy;
    private int buttonWidth = 60;
    private int type;
    public NumberButtons(String me, int interationx, int interationy, int type){
       /*
       @param button "name" (me)
       @param x location (interationx)
       @param y locaiontion (interationy)
       @param sizing (type)
       */
        this.me = me; //name/id
        this.interationx = interationx; //xpos
        this.interationy = interationy; //ypos
        this.type = type; //sizing

    }
    public String reutrnFunction(){
        /*
        *Returns the functions operation or number
        @returns this.me
        */
        return this.me;
    }
    /*
     *Display the button and set sizes
     *@param J frame frame to display on
     */
    public void buttonDisplay(JFrame frame){
        System.out.println("printed to da screen");
        //JFrame frame = new JFrame("Calculator");
        JButton button1 = new JButton(me); //create button
        int longer = 0;
        //increase the length of equals button
        if(this.me == "Equals"){
            longer = 200;
        }
        //set button bounds
        button1.setBounds(interationx*(buttonWidth+5)+5, 70*interationy+50, buttonWidth+longer, buttonWidth);
        button1.setBackground(new Color(210,210,255));
        frame.add(button1); //add the button to the frame
        //create action listenter for each button
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //below we have some special casses for = and trig functinos
               if(NumberButtons.this.me.equals("Equals")){
                   NumberButtons.this.me = "=";
               }else if(NumberButtons.this.me.equals("Sin")){
                   NumberButtons.this.me = "s(";
               }else if(NumberButtons.this.me.equals("Cos")){
                   NumberButtons.this.me = "c(";
               }else if(NumberButtons.this.me.equals("Tan")){
                   NumberButtons.this.me = "t(";
               }
               
               System.out.println(NumberButtons.this.me);
               Main.addCharacter(NumberButtons.this.me); //add the buttons id to the string with the addcharacter method in main
               
            }
        });
    }
    
   
}