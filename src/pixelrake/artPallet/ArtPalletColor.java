
package pixelrake.artPallet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;

public class ArtPalletColor {
    
    Color color;
    int size = 30;
    Area area;
    
    public ArtPalletColor(Color c, int x, int y){
        color = c;
        area = new Area(new Ellipse2D.Double(x-size/2, y-size/2, size, size));
        Area temp = new Area();
        for (PathIterator pi = area.getPathIterator(null); !pi.isDone(); pi.next()) {
            double[] coords = new double[6];
            int type = pi.currentSegment(coords);
            int newSize = (int)(Math.random()*(size-10))+10;
            int xMod = (coords[0]>x)?1:-1;
            int yMod = (coords[1]>y)?1:-1;
            if(type != 4)
                temp.add(new Area(new Ellipse2D.Double(coords[0]-xMod*newSize/2, coords[1]-yMod*newSize/2, newSize, newSize)));
        }
        area.add(temp);
        AffineTransform af = new AffineTransform();
        af.translate(-(area.getBounds().x+area.getBounds().getWidth()/2), -(area.getBounds().y+area.getBounds().getHeight()/2));
        area.transform(af);
        af = new AffineTransform();
        af.rotate(Math.random()*(Math.PI*2));
        area.transform(af);
        af = new AffineTransform();
        af.translate(x, y);
        area.transform(af);
    }
    
    public ArtPalletColor(Color c, Area a){
        area = a;
        color = c;
    }
    
    public Color getColor(){
        return color;
    }
    
    public void draw(Graphics g){
        g.setColor(color);
        ((Graphics2D)g).fill(area);
//        g.setColor(Color.RED);
//        ((Graphics2D)g).draw(area.getBounds());
    }
    
    public void setLoctation(int x, int y){
        AffineTransform af = new AffineTransform();
        af.translate(x-area.getBounds().getX(), y-area.getBounds().getY());
        area.transform(af);
    }
    
    public int getX(){
        return (int)area.getBounds().getX();
    }
    
    public int getY(){
        return (int)area.getBounds().getY();
    }
    
    public boolean intersectsBasicBounds(Area a){
        return a.getBounds().intersects(area.getBounds());
    }
    
    public boolean intersectsPoint(Point p){
        return area.contains(p);
    }
    
    public Area getArea(){
        return area;
    }
    
    public Area getBounds(){
        return area;
    }
}
