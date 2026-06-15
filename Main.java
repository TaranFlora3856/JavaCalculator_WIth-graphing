/**
 * @author Taran
 */
//imports here
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*; 
import static java.awt.event.KeyEvent.*; 
import java.util.*;
import java.io.OutputStream;//libraries to disable the consel
import java.io.PrintStream;

public class Main {
	
    /**
     * Describe your app here.
     *My app is a calculator tha can prfrome BEDMAS and graphing simalr to a bad Desmos
     * @param args the command line arguments
     * @return void
     */
     
    public static JLabel answerBox = new JLabel("Equation: "); //Create the Jlabel answerbox
    
    public static int length = 1200; //Define the length and height of the JFrame
    public static int width = 475;
    
    public static String equation = "";//The string that hold our equation
    public static JFrame frame = new JFrame("Calculator");//Create the J Frame
    
    public static void main(String[] args) {
        /*
        This is our Main for the Graphing calculator/
        */
        // Initializes the frame
        frame.setSize(length,width); //Set J fram size
        //Instructions
        System.out.println("Started");
        System.out.println("This is a graphing calculator");
        System.out.println("You can use it like a nomal calculator and it will use BEDMAS to return your correct asnwer.");
        System.out.println("Note: The \"x\" is mutiplication and the X is for graphing");
        System.out.println("To graphs a function just creat an equation in terms of X and the reult will be graphed on the Y axis");
        System.out.println("You must include the x sybold for evey mutiplicatin operator, so 2(X) needs 2x(X) and -X need -1xX");
        System.out.println("For negative you must use the - on the bottom left the minus sing will not work to create a negative number.");

        //Disable the consle
        
        //Lines  46,49 not wirten by me
        System.setOut(new PrintStream(new OutputStream() {
            public void write(int b) {
            }
        }));
        
        //Answer box creations
        answerBox.setBounds(0, 0, 400, 100);
        answerBox.setFont(new Font(answerBox.getFont().getName(), Font.PLAIN, 25));
        answerBox.setOpaque(true);
        answerBox.setBackground(new Color (255,255,255)); // Using a predefined color
        frame.add(answerBox);
        //Create a string list of button names/functinos
        String[] buttonNamse = {"1","2","3","0","x","/","4","5","6","+","-","√","7","8","9","^","(",")","X",".","Sin","Cos","Tan","CLR","—","Del","Equals"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "};
        int j = 0; //start incrmenent counter
        //Create an object list called buttons to hold all the button objectrs we will be useing
        NumberButtons[] buttons = new NumberButtons[30];
        //Doubl for loop array to iterate through and display and create all the buttons
        for (int r = 1; r <= 5; r++){
            for(int i = 0; i < ((length-600)/100); i+=1){
                System.out.println(i);
                if(r==5 && i == 3){
                    break;
                }
                if(i > width-60-20){i+=1000;break;}
                if(buttonNamse[i] == "="){
                    //special case for the equals button
                    buttons[i] = new NumberButtons(buttonNamse[j],i,r,20); 
                }
                //create and display the buttons
                buttons[i] = new NumberButtons(buttonNamse[j],i,r,1); 
                buttons[i].buttonDisplay(frame);
                j++;
            }
        }
        //Display stuff
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    public static void addCharacter(String toAdd){
        /*
        This functions helps us add character to our input and display it on screen.
        @param String of character to add to string
        @ returns void
        */
        equation = equation + toAdd; //Add the character to the equation string.
        //CLR the equation
        if(toAdd.equals("CLR")){
            equation = "";
            Calculate.opStack.clear();
            Calculate.numQueue.clear();
            RPNR.calcStack.clear();
        }else if(toAdd.equals("Del")){
            equation = equation.substring(0,(equation.length())-4);
        }
        String eq1 = "";
        //IF equasl is pushed then
        if(toAdd.equals("=")){
            eq1 = equation; //Strore current equation
            System.out.println("Answering");
                System.out.println("Balanced brackets.");
            if(Calculate.hasBalancedBrackets(equation)){
                //try{
                //Create a graph g and display it
                Graphics g = frame.getGraphics();
                Graph gr1=new Graph();
                gr1.setEquation(equation);
                gr1.setVisible(true);
                //Find result of the string equation by tokenizing, putting in revers polis notation, and then revers the RPN to get a real answer.
                try{
                    equation = RPNR.calcRPN(Calculate.FillemUP(Calculate.toeknize(equation)));
                }catch(Exception e){
                    equation = "";   
            }
                }else{
                    equation = "Error";
                    
                }
                        
            }
        //Below you will find some error cases and error hadneling
        if(!equation.equals("") && eq1.equals(equation)){
            System.out.println(equation);
            equation = "Error";
        }else if(equation.equals("Infinity")){
             equation = "Error";
        }
        //Update the answer box
        answerBox.setText(equation);
     }
    
}