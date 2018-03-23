import java.awt.Button;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class Person extends Button implements FocusListener
{
 int number;
 Color c = new Color(255,245,170);
 Person(int number,String s)
 {
  super(s);
  setBackground(c);
  this.number = number;
  c = getBackground();
  addFocusListener(this);
 }

    public void focusGained(FocusEvent e)
    {
     setBackground(Color.red);
    }
    public void focusLost(FocusEvent e)
    {
     setBackground(c);
    }
}
