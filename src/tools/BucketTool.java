
package tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import pixelrake.PixelRake;

public class BucketTool implements PaintTool{

    private int sensitivity = 0;
    
    @Override
    public void reset() {
    }

    @Override
    public void draw(Graphics g) {
    }

    public int compareColors(Color one, Color two){
        return (one.getRed()+one.getGreen()+one.getBlue()+one.getAlpha())-(two.getRed()+two.getGreen()+two.getBlue()+two.getAlpha());
    }
    
    public void findAllAdjacentPointsOfColor(Color c, Point p, Color newCol){
        if(c.equals(newCol)){
            return;
        }
        ArrayList<ArrayList<Color>> pixels = PixelRake.drawPanel.getAllPixels();
        pixels.get(p.x).set(p.y, newCol);
        int[] addX = {1,-1,0,0};
        int[] addY = {0,0,1,-1};
        for(int i=0; i<addX.length; i++){
            Point newP = new Point(p.x+addX[i],p.y+addY[i]);
            //The point is within the bounds of the pixels
            //The color is close to what we are looking for
            if(newP.x >= 0 && newP.y >= 0 && newP.x < pixels.size() && newP.y < pixels.get(newP.x).size()
                    && pixels.get(newP.x).get(newP.y).equals(c))
                findAllAdjacentPointsOfColor(c,newP,newCol);//compareColors(pixels.get(newP.x).get(newP.y),c) <= sensitivity
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        Point p = PixelRake.drawPanel.getPointOnImage(me.getPoint());
        Color c = PixelRake.drawPanel.getPixel(p);
        findAllAdjacentPointsOfColor(c,p,PixelRake.colorChooserView.getColor());
        PixelRake.drawPanel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        mousePressed(me);
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }
    
}
