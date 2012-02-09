import java.awt.*;

import javax.swing.JFrame;

public class Main {

  public static void main (String[] args) {

  // define frame, its size and make it visible
  CirSim myFrame = new CirSim();
  myFrame.init();
  myFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
  myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  myFrame.setVisible(true);
  } // end main method
} // end class
