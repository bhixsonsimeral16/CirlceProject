/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceexam;

import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brian
 */
public class CirclePanel extends JPanel {
    public static final Color BG_COLOR = Color.WHITE; //Background color
    private final double MAX_RADIUS = 0.25;           //maximum radius of the circles (experiment based on you window size)
    private final double MIN_RADIUS = 0.001;          //minimum radius of the circles
    private final int LIMIT = 10000;                  //number of times it tries to find a new space before it gives up (a higher number will result in a fulelr picture)
    
    /**
     * Constructor for CirclePanel
     */
    public CirclePanel(){
        this.setBackground(BG_COLOR);
    }
    
    /**
     * I believe this method is mentioned in JPanel
     * @param g a graphics object (look at the api if you want to learn about them)
     * 
     * All the math and logic is done in this method.
     */
    @Override
    public void paintComponent(Graphics g){
        //Comes from the spitfire project
        super.paintComponent(g);
        System.out.println("yes");
        //Need a Graphics2D object so that we can use their methods
        Graphics2D g2D = (Graphics2D) g;
        
        //Width and height of the window in x, y coordinates
        int w = this.getWidth();
        int h = this.getHeight();
        
        //AffineTransform will move the window so that the bottom left is (0,0) and the top right is (1,1)
        //not sure on this
        AffineTransform scale = new AffineTransform();
        scale.setToScale(w / 2, h / 2);

        AffineTransform translate = new AffineTransform();
        translate.setToTranslation(w / 2, h / 2);
        
        //We concatenate the transforms so that they do not override one another
        AffineTransform transform = new AffineTransform();
        transform.concatenate(translate);
        transform.concatenate(scale);
        
        //This list is used to store all of the Circles that end up being drawn
        List<Circle> shapes = new ArrayList<>();
        
        //List of colors to reference for fillinf the circles
        List<Color> fillColors = new ArrayList<>();
        fillColors.add(new Color(17,192,250));
        fillColors.add(new Color(191,17,250));
        fillColors.add(new Color(250,75,17));
        fillColors.add(new Color(76,250,17));
        
        //List of colors to reference to draw the outlines
        List<Color> outColors = new ArrayList<>();
        outColors.add(new Color(0,8,255));
        outColors.add(new Color(255,0,135));
        outColors.add(new Color(255,248,0));
        outColors.add(new Color(0,255,120));
        
        //currentRadius is the radius that the circles will be drawn with
        double currentRadius = MAX_RADIUS;

        //When currentRadius goes below MIN_RADIUS we will stop drawing
        while (currentRadius >= MIN_RADIUS) {
            
            //count will keep track of the number of coordinates that we have tried to draw at
            int count = 0;
            
            //when we have tried to draw more times than LIMIT, we will reduce the radius of the circles that we draw
            while (count <= LIMIT) {
                //Does the circle that we are creating intersect any other circle that we have drawn
                boolean intersect = false;
                
                //Random x and y coordinates between -1 and 1 (the bounds of the picture)
                double randX = (Math.random() * 2) - 1;
                double randY = (Math.random() * 2) - 1;
                //create the circle with these specifications
                Circle circle = new Circle(randX, randY, currentRadius);
                
                //set the colors of the circle randomly
                circle.setColor(fillColors.get(((int) Math.floor(Math.random() * (int) fillColors.size()))));
                circle.setCColor(outColors.get(((int) Math.floor(Math.random() * (int) outColors.size()))));
                
                //loop through the list of circles
                for (Circle shape : shapes) {
                    //if our new circle intersects with any of the previous circles set intersect to true and break
                    if (circle.intersects(shape)) {
                        intersect = true;
                        break;
                    }
                }
                //if the circle intersected any of our previous circles then add 1 to count
                if(intersect){
                    count++;
                }
                //if the circle did not intersect any of the previous circles then add it to the list and draw it
                else{
                    shapes.add(circle);
                    circle.draw(g2D, transform);
                }
            }
            //If we have expended all of our tries then reduce the radius of the circle and try again
            currentRadius-=0.001;
        }
    }
}
