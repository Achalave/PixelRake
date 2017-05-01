
package tools;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;
import pixelrake.PixelRake;


public class LineTool implements PaintTool{

    Point pointOne = null;
    Point pointTwo = null;
    Point lastPointTwo = null;
    boolean dragged = false;
    
    private Set<Point> points = new HashSet<Point>();
    
    @Override
    public void reset() {
        points.clear();
        pointOne = null;
        pointTwo = null;
        dragged = false;
    }

    @Override
    public void draw(Graphics g) {
        if(pointOne != null && pointTwo != null){
            int size = PixelRake.drawPanel.getBoxSize();
            g.setColor(PixelRake.colorChooserView.getColor());
            //g.setColor(Color.RED);
            for(Point p:points){
                g.fillRect(p.x*size, p.y*size, size, size);
            }
        }
    }

    private void setPoints(){
        points.clear();
        if(pointOne.equals(pointTwo))
            return;
        //if slope is undefined
        if((pointOne.x-pointTwo.x) == 0){
            int startY = pointOne.y;
            int endY = pointTwo.y;
            if(startY > endY){
                startY = endY;
                endY = pointOne.y;
            }
            for(int y=startY; y<=endY; y++){
                points.add(new Point(pointOne.x,y));
            }
            return;
        }
        double slope = (double)(pointOne.y-pointTwo.y)/(pointOne.x-pointTwo.x);//System.out.println(slope);
        Point less = pointOne;
        Point more = pointTwo;
        if(Math.abs(slope) <= 1){
            if(less.x > pointTwo.x){
                less = pointTwo;
                more = pointOne;
            }
            for(int x=less.x; x<=more.x; x++){
                int y = (int)Math.round((slope*(x-pointOne.x))+pointOne.y);
                points.add(new Point(x,y));
            }
        }else{
            if(less.y > pointTwo.y){
                less = pointTwo;
                more = pointOne;
            }
            for(int y=less.y; y<=more.y; y++){
                int x = (int)Math.round((y-pointOne.y)/slope+pointOne.x);
                points.add(new Point(x,y));
            }
        }
        //System.out.println(points.size() + " :::: "+points);
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(me.getButton()==MouseEvent.BUTTON3){
            reset();
            PixelRake.drawPanel.repaint();
        }
        else if(pointOne == null){
            pointOne = PixelRake.drawPanel.getPointOnScreen(me.getPoint());
        }
        //Finalize
        else{
            PixelRake.drawPanel.setPixelsOnScreen(points, PixelRake.colorChooserView.getColor());
            reset();
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //Finalize
        if(pointOne != null && dragged){
            PixelRake.drawPanel.setPixelsOnScreen(points, PixelRake.colorChooserView.getColor());
            reset();
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

    @Override
    public void mouseDragged(MouseEvent me) {//System.out.println(pointOne+" "+pointTwo);
        dragged = true;
        pointTwo = PixelRake.drawPanel.getPointOnScreen(me.getPoint());
        if(pointOne != null && !pointTwo.equals(lastPointTwo)){
            lastPointTwo = pointTwo;
            setPoints();
            PixelRake.drawPanel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        pointTwo = PixelRake.drawPanel.getPointOnScreen(me.getPoint());
        if(pointOne != null && !pointTwo.equals(lastPointTwo)){
            lastPointTwo = pointTwo;
            setPoints();
            PixelRake.drawPanel.repaint();
        }
    }
    
}
