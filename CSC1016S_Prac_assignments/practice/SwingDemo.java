import javax.swing.*;
import java.awt.event.*;
public class SwingDemo
{
   public static final int width = 300;
   public static final int height = 300;
   
   public static void main(String[] args)
   {
      JFrame firstwindow = new JFrame();
      firstwindow.setSize(width, height);
      firstwindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      
      JButton endButton = new JButton("click to end program");
      EndingListener button = new EndingListener();
      endButton.addActionListener(button);
      firstwindow.add(endButton);
      
      firstwindow.setVisible(true);
   }
} 
class EndingListener implements ActionListener
{
   public void actionPerformed(ActionEvent e){
      System.exit(0);
   }
}