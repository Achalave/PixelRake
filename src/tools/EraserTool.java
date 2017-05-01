/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import pixelrake.*;
/**
 *
 * @author user
 */
public class EraserTool implements PaintTool{

    Point lastPoint;
    int amount = 50;
    
    private void erase(Point p){
        Color old = PixelRake.drawPanel.getPixel(p);
        int alpha = old.getAlpha();
        alpha -= amount;
        if(alpha < 0)
            alpha = 0;
        PixelRake.drawPanel.setPixel(p, new Color(old.getRed(), old.getBlue(), old.getGreen(),alpha));
        PixelRake.drawPanel.repaint();
    }
    
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
        lastPoint = PixelRake.drawPanel.getPointOnImage(me.getPoint());
        erase(lastPoint);
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
        if(!lastPoint.equals(PixelRake.drawPanel.getPointOnImage(me.getPoint()))){
            lastPoint = PixelRake.drawPanel.getPointOnImage(me.getPoint());
            erase(lastPoint);
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }
    
}
