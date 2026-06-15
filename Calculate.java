//imports here
import java.util.Stack;
import java.util.*;

public class Calculate{
        /*
        This class holds all the funtions to take the equation in String format and reutn in in an arraylist in reveser polish notation.
        Tokenizer grabs each number and operation and places in an array list.
        
        */
        
         public static ArrayList<String> toeknize(String equation){
            /*
            This method will tokemize all the string into an arraylist with the nubers and operation in string form.
            @param String equation
            @return ArrayList of STrings
            */
            
            ArrayList<String> listEquation = new ArrayList(); //Create and arraylist to hold our tokenized equation
            char[] charArray = equation.toCharArray(); //convert to char array
            String num = "";
            System.out.println("______________________________");
            //add each char to the arraylis
            for(int i = 0; i < charArray.length; i++){
                //If we have a . or anuther num oadd that to the sting before adding the string to the array list, so we can have mulit digit number, decimal inputs and negative inputs
                if(charArray[i] == '.' || RPNR.isNum(String.valueOf(charArray[i])) == 1){
                    num += charArray[i];
                }else if(charArray[i] == '—'){
                    num += '-';
                }else{
                        listEquation.add(num);
                        num = "";
                        listEquation.add(String.valueOf(charArray[i]));
                    }
                    
                }
                //return the tokenised equation
                System.out.println(listEquation);
                return listEquation;
            }
           
          public static boolean hasBalancedBrackets(String code) {
              /*
              Returns true or fasle if the equation has balanced brackets
              @param STring equations
              @return true or fasle
              */
                //We are just cheacking to the freqency of "(" and ")" to make sure we have equal amounts of them              
                ArrayList<Character> codeList = new ArrayList(); 
                char[] codeChars = code.toCharArray();
                
                for(char c: codeChars){
                    if(c == '(' || c == ')'){
                        codeList.add(c);
                    }
                } 
                if(Collections.frequency(codeList, '(')==Collections.frequency(codeList, ')')){
                    return true;
                }else{
                    return false;
                }
                   
            }
            
        //creats the stack and queue for the shunting yard algoritm    
        public static Stack<String> opStack = new Stack(); 
        public static Queue<String> numQueue = new LinkedList();
        
        public static Queue<String> FillemUP(ArrayList<String> equation){
                    /*
                    Main Shunting Yard algorithm to fill and manage the stack and queue form the arrayList.
                    */
            
            try{
                //eval sqrts first
                for(int j = 0; j <= equation.size()-1; j++){
                     if(equation.get(j).equals("√")){
                        float numSqrt = Float.parseFloat(equation.get(j+1));
                        equation.remove(j);
                        equation.remove(j);
                        equation.add(j,Double.toString(Math.sqrt(numSqrt)));
                    }
                }
            }catch(Exception e){}
            
            for(String c: equation){
                //Here we iterate through and if operator add to opstack else add to numstack
                //To follow besmas rules if the operator has a lower priority then we must Epty out the opstack until we can add out operation so it follow besdams
                switch (c){
                    case "+":
                    case "-":
                    case "x":
                    case "/":
                    case "^":
                    case "(":
                    case "√":
                    case "s":
                    case "c":
                    case "t":
                        System.out.print(c);System.out.println("<-- c");
                        try{
                            if(BEDMAScheck(c,opStack.peek()) == 1){ //1 is true
                                opStack.push(c); //add to opstack
                                System.out.print(c);System.out.println("<-- c1-try");

                            }else if (BEDMAScheck(c,opStack.peek()) == 2){ //2 is false
                                //little odd but we shall see
                                System.out.print(c);System.out.println("<-- c2-try");
                                while(opStack.size() > 0){
                                   String holder = opStack.peek();
                                   System.out.print("h:");System.out.println(holder);
                                   System.out.print("c:");System.out.println(c);
                                   opStack.pop();
                                   if(holder.equals("(") == false){
                                        numQueue.add(holder);
                                        //System.out.print("opStack67:");
                                        //System.out.println(opStack);
                                        //System.out.print("numQueue:");
                                        //System.out.println(numQueue);
                                        //break;//weird asf
                                    }
                                    if(holder.equals("(")){ ////////////////////////////////////////////////////////////
                                        opStack.add(c);
                                        break;
                                    }
                                  if(BEDMAScheck(c,opStack.peek()) == 1){
                                      opStack.push(c);
                                      break;
                                   }
                                }
                            }else{
                                System.out.print(c);System.out.println("else triggered on nedmas opstack");
                            }
                        }catch (Exception e){
                            //Same code as before but to operate on rest of the equatino after the first section
                            try{
                                //System.out.println("CATCH EXCETIPON");
                                if(BEDMAScheck(c,"-") == 1){ //1 is true
                                    //System.out.println("12345");
                                    opStack.push(c);
                                }else if (BEDMAScheck(c,"-") == 2){ //2 is false
                                    System.out.println("6,7");
                                    //little odd but we shall see
                                    for(int i = 0; i < opStack.size()-1; i++){
                                      String holder = opStack.peek();
                                      opStack.pop();
                                      if(holder.equals("(") == false){
                                            numQueue.add(holder);
                                            System.out.print("opStack:");
                                            System.out.println(opStack);
                                            System.out.print("numQueue:");
                                            System.out.println(numQueue);
                                        }
                                      //numQueue.add(holder);
                                      System.out.println("-----------");
                                      if(BEDMAScheck(c,opStack.peek()) == 1){
                                        opStack.push(c);
                                        break;
                                      }
                                    }
                                }else{
                                    System.out.print(c);System.out.println("else triggered on bedmas opstack catch");
                            
                                }
                                }catch (Exception w){
                                    Main.equation = "Error";
                            }
                            
                        }
                        break;
                    case ")":
                        try{
                        BEDMAScheck(c,opStack.peek());
                        }catch(Exception e){}
                        break;
                    
                    default:
                       //System.out.println("h:");
                        //System.out.println(c);
                        if(c.equals(".") || c.equals("√") || c.equals("s")){
                            //System.out.println("llgjhfhgjhgfdhsdfbvaergbdytfng");
                            numQueue.add(c);
                            break;
                        }else{
                            try{
                                if(Float.parseFloat(c) > -1000000 || c.equals("(")){
                                    numQueue.add(c);
                                }
                                break;
                            }catch(Exception e){
                                System.out.println(".");
                            }
                            break;
                        }
                }
                if(numQueue.size() < opStack.size()){
                    Main.equation = "Error";
                }
                
            }
            System.out.print("opStack:");
            System.out.println(opStack);
            System.out.print("numQueue:");
            System.out.println(numQueue);
            while(opStack.size() > 0){
                numQueue.add(opStack.peek());
                opStack.pop();
            }
            System.out.println("Post-fix:");
            System.out.println(numQueue);
            return numQueue;
        }
    public static int BEDMAScheck(String op, String preOp){
        /*
        *Check for correct order of operations and weather or not you have priority or not.
        *@params String operations
        *@param String perviouse operation
        *@return and int denting is it is a valid oepration, invalid or singl operation
        */
        //Brackets,Expontnets,/x,+-
        ArrayList<String> BEDMAS = new ArrayList(List.of("X","(","s","t","c","√","^","/","x","-","+",")"));
        if(BEDMAS.indexOf(op) <= BEDMAS.indexOf(preOp)){
            return 1;
        }else if(op.equals("+") && BEDMAS.indexOf(preOp) == BEDMAS.indexOf("-")){
            return 1;
        }else if(op.equals("x") && BEDMAS.indexOf(preOp) == BEDMAS.indexOf("/")){
            return 1;
        }else if(op.equals(")")){
            System.out.println("{-----------------------------}");
            for(int i = 0; i <= opStack.size()-1; i++){
                System.out.println("{-----------------------------}");
                System.out.println(opStack);
                try{
                    if(opStack.peek().equals("(") == false){
                        numQueue.add(opStack.peek());
                    }
                    opStack.pop();
                    if(opStack.peek().equals("(")){
                        opStack.pop();
                        break;
                    }
                }catch (Exception e){
                    System.out.println("eee");
                }
            }
            return 3;
        }else if(op.equals(")")) {
            while (!opStack.isEmpty() && !opStack.peek().equals("(")) {
                numQueue.add(opStack.pop());
            }
            // pop the "(" itself
            if (!opStack.isEmpty() && opStack.peek().equals("(")) {
                opStack.pop();
            }
            return 3; // means we handled a closing bracket
        }else{
            return 2;
        }
    }
}