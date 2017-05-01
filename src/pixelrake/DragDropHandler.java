/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pixelrake;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 *
 * @author user
 */
public class DragDropHandler extends TransferHandler{
    String mimeType = DataFlavor.javaJVMLocalObjectMimeType + ";class=java.awt.Color";
    DataFlavor colorFlavor;
    
    //Setup the color flavor
    public DragDropHandler(){
        try {
            colorFlavor = new DataFlavor(mimeType);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DragDropHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Iterate through all of the flavors of the dropped object and check for color flavor
    protected boolean hasColorFlavor(DataFlavor[] flavors) {
        if (colorFlavor == null) {
             return false;
        }

        for (int i = 0; i < flavors.length; i++) {
            if (colorFlavor.equals(flavors[i])) {
                return true;
            }
        }
        return false;
    }
    
    //Something has been droped onto this DrawPanel
    @Override
    public boolean importData(JComponent c, Transferable t) {
        if (hasColorFlavor(t.getTransferDataFlavors())) {
            try {
                Color col = (Color)t.getTransferData(colorFlavor);
                //do import changes to c
                ((DrawPanel)c).dropPixel(col);
                return true;
            } catch (UnsupportedFlavorException ufe) {
                Logger.getLogger(DragDropHandler.class.getName()).log(Level.SEVERE, null, ufe);
            } catch (IOException ioe) {
                Logger.getLogger(DragDropHandler.class.getName()).log(Level.SEVERE, null, ioe);
            }
        }
        return false;
    }
    
    //Return true if the dragged item can be dropped on this DrawPanel
    @Override
    public boolean canImport(JComponent c, DataFlavor[] flavors) {
        return hasColorFlavor(flavors);
    }
    
    @Override
    protected Transferable createTransferable(JComponent c) {
        Color color = ((DrawPanel)c).getPixel(((DrawPanel)c).getMousePixelLocation());
        
        return new TransferableColor(color);
    }
    
    @Override
    public int getSourceActions(JComponent c) {
        return COPY;
    }
}
