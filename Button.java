//imports here
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import static java.awt.event.KeyEvent.*; 
import java.util.*;

public abstract class Button extends JButton{
    /*
    *This is an abstract method that extends JButton 
    *So the Subclass can still inhert all all the properties of the button easily.
    @returns the "name if the button"
    */
    public abstract String reutrnFunction();
}