
package tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import pixelrake.DrawPanel;
import pixelrake.PixelRake;

public class PenTool implements PaintTool{

    Point lastPoint;
    
    @Override
    public void reset() {
    }

    @Override
    public void draw(Graphics g) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        DrawPanel dp = PixelRake.drawPanel;
        //dp.setPixel(dp.getPointOnImage(me.getPoint()),);
        lastPoint = dp.getPointOnImage(me.getPoint());
        dp.setPixel(lastPoint, PixelRake.colorChooserView.getColor());
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
        if(!lastPoint.equals(me.getPoint())){
            DrawPanel dp = PixelRake.drawPanel;
            lastPoint = dp.getPointOnImage(me.getPoint());
            dp.setPixelOnScreen(lastPoint, PixelRake.colorChooserView.getColor());
            
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }
    
}
