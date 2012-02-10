import java.awt.*;
import javax.swing.UIManager.*;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main {

  public static void main (String[] args) {

  // define frame, its size and make it visible
  try {
      for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
          if ("Nimbus".equals(info.getName())) {
              UIManager.setLookAndFeel(info.getClassName());
              break;
          }
      }
  } catch (Exception e) {
      // If Nimbus is not available, you can set the GUI to another look and feel.
  }	  
	  
  CirSim myFrame = new CirSim();
  myFrame.init();
  myFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
  myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  myFrame.setVisible(true);
  } // end main method
} // end class
