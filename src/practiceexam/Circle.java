/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceexam;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.BasicStroke;

/**
 *
 * @author Brian
 */
public class Circle implements Colorable {
    
    private Color color;          // color of the circle
    private Color cColor;         // color of the outline
    private final double x;       // x coord of the origin of the circle
    private final double y;       // y coord of the origin of the circle
    private final double radius;  // radius of the circle
    
    public Circle(double x, double y, double radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = Color.RED;    //default the fill color to RED if not set
        this.cColor = Color.BLACK; //default the outline color to BLACK if not set
    }
    
    //getter for fill color
    public Color getColor(){
        return this.color;
    }
    
    //setter for fill color
    public void setColor(Color c){
        this.color = c;
    }
    
    //getter for outline color
    public Color getCColor(){
        return this.cColor;
    }
    
    //setter for outline color
    public void setCColor(Color c){
        this.cColor = c;
    }
    
    /**
     * Does this circle intersect with Circle c
     * @param c another circle object
     * @return true if the two circles intersect
     * Determine the distance between the two origins
     * If the distance is less than the sum of the radii then they intersect
     */
    public boolean intersects(Circle c){
        //Distance between the two origins
        double dist = Math.sqrt(Math.pow((c.x - this.x), 2) + Math.pow((c.y - this.y), 2));
        //Is the Distance less than the sum of the radii
        boolean intersect = dist < (this.radius + c.radius);
        return intersect;
    }
    
    /**
     * Draw this circle on our CirclePanel
     * @param g2D allows us to draw shapes by modifying certain aspects (color, width, position...)
     * @param transform transforms the Ellipse2D object into a shape object
     */
    public void draw(Graphics2D g2D, AffineTransform transform){
        //Helper with teh Ellipse2D.Double method
        //Allows us to use the origin rather than the top left point to specify the circle
        double ulx = this.x - this.radius;
        double uly = this.y - this.radius;
        double d = this.radius * 2;
        //creates the circles
        Ellipse2D ellipse = new Ellipse2D.Double(ulx, uly, d, d);
        Shape shape = transform.createTransformedShape(ellipse); //transforms the Ellipse2D into a shape object
        g2D.setColor(this.color);                                //set the color
        g2D.fill(shape);                                         //draw a filled circle
        g2D.setStroke(new BasicStroke((float) this.radius*8));   //set the width of the line that will be drawn
        g2D.setColor(this.cColor);                               //set the color of the outline
        g2D.draw(shape);                                         //drawn the outline of a circle
    }   
}
