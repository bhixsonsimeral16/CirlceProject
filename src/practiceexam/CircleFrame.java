/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceexam;

import java.awt.Container;
import javax.swing.JFrame;

/**
 *
 * @author Brian
 */
public class CircleFrame extends JFrame {
    public static final int FRAME_WIDTH = 768;
    public static final int FRAME_HEIGHT = 768;
    public static final String TITLE = "Circles";
    
    /**
     * set the pixel size of the window
     * set the title
     * getContentPane is from JFrame (check the api if you would like to know more)
     * Create a CirclePanel, add it to the pane, make it visible
     */
    public CircleFrame(){
        this.setSize( FRAME_WIDTH, FRAME_HEIGHT );
        this.setTitle( TITLE );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        
        Container pane = this.getContentPane();
        CirclePanel circlePanel = new CirclePanel();
        pane.add( circlePanel );
        this.setVisible( true  );
        
    }

    /**
     * @param args the command line arguments
     * Main method creates the CiricleFrame that initializes the whole program
     */
    public static void main(String[] args) {
        CircleFrame circleFrame = new CircleFrame();
    }
    
}
