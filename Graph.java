//imports here
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;


public class Graph extends JFrame {
    /*
    Graph class that extends JFrame, so we can easily place it on the JFrame.
    */
    
    public static String equation = "";

    public Graph() {
        //Set size and location
        setSize(600, 450);
        setLocation(400, 0); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //dont close the prviouse panel
        add(new GraphPanel()); // add drawing panel
    }
    
    
    public void setEquation(String equation){
        /*
        *Set the equatinos
        *@param an equation
        *@returns this.equation
        */
        this.equation = equation;
    }

    class GraphPanel extends JPanel {
        /*
        *GraphPanel extends JPanel to create a panel we can lay on the JFrame
        */
        //Lines 32,36,37 were borrowed
        protected void paintComponent(Graphics g) {
            /*
            Paint the components onto the Jpanel
            *@param Graphics g
            */
            super.paintComponent(g); //paint the component g
            drawAxis(g); //display it
        }

        private void drawAxis(Graphics g) {
            /*
            Draws the graph and creats the graphic
            *@param Graphics g
            */
            //Create the 2d graphics
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(2));
            //Create the arralits to store our equation and x and y values
            //ArrayList<String> listEquation = Calculate.toeknize(equation);
            ArrayList<String> listEquation2 = Calculate.toeknize(equation);
            ArrayList <Integer> xp = new ArrayList();
            ArrayList <Integer> yp = new ArrayList();
            //creat index swap variable
            int indexSwap = 0; //errror with theindex swap part!!!!!!!!!!!! that why no working
            //loop to iterate through each x coordiante for each point
            for(int q = -500; q <= 500; q+=1){
                //reste eveything
                ArrayList<String> listEquation = new ArrayList<>();
                Calculate.opStack.clear();
                Calculate.numQueue.clear();
                RPNR.calcStack.clear(); 
                //Swap the X's in the equation fo the "x" value on the graph q
                   if(listEquation2.contains("X")){
                            for (String token : listEquation2) {
                                if (token.equals("X")) {
                                    listEquation.add(Integer.toString(q));
                                } else {
                                    listEquation.add(token);
                                }
                            }
                }
                //listEquation.add(0,Integer.toString(q/2));
                //Collections.swap(listEquation, indexSwap, 0);
                //listEquation.remove(0);
                System.out.print("PPP:");System.out.println(listEquation);
                //Craet the ylav string holder
                String yVal = RPNR.calcRPN(Calculate.FillemUP(listEquation));
                //If the answer is not a number skip it
                if(yVal.equals("NaN")){
                    continue;
                }
                //Turn y value to an int
                int yValInt = (int)(Float.parseFloat(yVal))/10;
                System.out.println("k:");System.out.println(yValInt);
                System.out.println(q);
                //set colour to red
                g2.setColor(Color.RED);
                //Creat the individual points
                g2.fillOval((int)q+300,((getHeight() / 2)-yValInt),2,2);
                //add the point to the list
                xp.add((int)q+300);
                yp.add(((getHeight() / 2)-yValInt));
            }
            //Draw the axis
            g2.setColor(Color.BLACK);
            g2.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());   // vertical line
            g2.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);            
            g2.setColor(Color.RED);
            //Draws a connectin line between each of the points
            for(int h = 0; h < xp.size()-1; h++){
                 try{
                    g2.drawLine(xp.get(h), yp.get(h), xp.get(h+1), yp.get(h+1));            
                 }catch(Exception e){}   
            }

           // Line2D lin = new Line2D.Float(100, 100, 250, 260);
           // g2.draw(lin);
        }
    }
}