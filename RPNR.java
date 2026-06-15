//imports here
import java.util.Stack;
import java.util.*;

public class RPNR{
    /*
    This class holds a bunch of functions to reverse to Reverse polish notation and achive an answer
    */
    //Create the main stack we use to calculate our answer
    public static Stack<String> calcStack = new Stack(); 
    
    public static String calcRPN(Queue<String> RPN){
        /*
        *Main function to revese the reverse poish notation
        *@param Queue of the equation in reverse polish notation
        *@returns a String answer
        */
        while(RPN.size() > 0){
            if(isNum(RPN.peek())== 1 ){
                calcStack.add(RPN.peek()); //IF it is a number add it to the Calcstack
                RPN.remove();
            }else if(isNum(RPN.peek()) == 2){
                //Else if it is an operation pop out the last 2 numbers store them evaluate them and then add it to the calcstack
                String operation = RPN.peek(); 
                RPN.remove();
                try{////////////////////////////////////break all sin(x)+x very very bad try catech
                String num1 = calcStack.peek();
                calcStack.pop();
                try{//////////////////////////////////////////////////////////////////////////////ugly try
                String num2 = calcStack.peek();
                calcStack.pop();
                calcStack.add(eval(num1,operation,num2));
                System.out.println(calcStack);
                System.out.println(RPN);
                }catch(Exception e){}
                }catch(Exception p){}
            }else if(isNum(RPN.peek()) == 3){ 
                //Eslse if if is a sing operator grab the last number and operation evaluste it and add it to the calcstack
                String operation = RPN.peek();
                RPN.remove();
                String num1 = calcStack.peek();
                calcStack.pop();
                calcStack.add(evalSingles(num1,operation));
                System.out.println(calcStack);
                System.out.println(RPN);
            }
        }
        try{
            return calcStack.peek();
        }catch (Exception w){
            return "0";
        }
    }
    
    public static String evalSingles(String num1, String operation){
        /*
        This function evaluates a operation that takes one input on ones string number
        *@param Takees a number in string form
        *@param Takes an singe operator in stirg form
        *return evaluated number in string form
        */
        //below is a swich and case that convert the stirng to floats and prformes the dsignater operation on the, then turnsthem back into strings
        switch(operation){
            case "√":
                return Float.toString((float)Math.sqrt(Float.parseFloat(num1)));
            case "s":
                return Float.toString((float)Math.sin(Double.parseDouble(num1)*(double)Math.PI/180));
            case "c":
                double anglecos = Math.toRadians(Double.parseDouble(num1));
                double cosValue = Math.round(Math.cos(anglecos)*1000.0)/1000.0;      
                if (Math.abs(cosValue) > 1e3) {
                            cosValue = 0;
                        }
                    return String.format("%3f",cosValue);
                //return Float.toString((float)Math.cos(Double.parseDouble(num1)*(double)Math.PI/180));
            case "t":
                double angle = Math.toRadians(Double.parseDouble(num1));
                double tanValue = Math.round(Math.tan(angle)*1000.0)/1000.0;      
                if (Math.abs(tanValue) > 1e3) {
                            tanValue = 0;
                        }
                    return String.format("%6f",tanValue);
            default:
                return "Error";
        }        
    }



    public static int isNum(String character){
        /*
        *Checks to see if a string is a number
        *@param a character in string form
        *@returns an int to denote wheater it is num, double operation or singler operator
        */
        switch(character){
            case "+":
            case "-":
            case "x":
            case "/":
            case "^":
            case "(":
                return 2;
            case"√":
            case "s":
            case "c":
            case "t":
                return 3;
            default:
                try{
                    Float.parseFloat(character);
                        return 1;
                }catch(Exception e){
                    if(character.equals(".")){
                        return 1;
                    }
                    return 0;
                }
                
        }
    }
    
    public static String eval(String num1, String operation, String num2){
        /*
        This function evaluates 2 stinrg numbers with an string operation
        *@param  a number in string form 
        *@param an operator in string form
        *@param a number in strin form
        *@return the evaluated equations (num1,operation,num2)
        */
        //below we have have a swictch cas to operates each of our double operations. 
        //WE have to first turn our string into floats evalutate then then turn them back to strings.
         switch(operation){
             //Float.parseFloat(floatString);
            case "+":
                return Float.toString(Float.parseFloat(num2)+Float.parseFloat(num1));
            case "-":
                return Float.toString(Float.parseFloat(num2)-Float.parseFloat(num1));
            case "x":
                return Float.toString(Float.parseFloat(num2)*Float.parseFloat(num1));
            case "/":
                return Float.toString(Float.parseFloat(num2)/Float.parseFloat(num1));
            case "^":
                return Float.toString((float)Math.pow((int)Math.floor(Float.parseFloat(num2)),(int)Math.floor(Float.parseFloat(num1))));
            default:
                return "Error";
         }        
    }
    
}