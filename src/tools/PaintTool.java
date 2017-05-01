
package tools;

import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface PaintTool extends MouseListener, MouseMotionListener{
    
    public abstract void reset();
    public abstract void draw(Graphics g);
    
}
