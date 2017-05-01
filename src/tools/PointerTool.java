
package tools;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.TransferHandler;
import pixelrake.PixelRake;

public class PointerTool implements PaintTool{

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
        PixelRake.drawPanel.getDragHandler().exportAsDrag(PixelRake.drawPanel, me, TransferHandler.COPY);
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }
    
}
