
package pixelrake;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransferableColor implements Transferable{

    protected static DataFlavor colorFlavor = new DataFlavor(Color.class, "Java Color Object");
    protected final static DataFlavor[] supportedFlavors = {
    colorFlavor,                  // Transfer as a Color object
    DataFlavor.stringFlavor,      // Transfer as a String object
    };
    
    Color color;
    
    public TransferableColor(Color c){
        try {
            colorFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=java.awt.Color");
            supportedFlavors[0] = colorFlavor;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TransferableColor.class.getName()).log(Level.SEVERE, null, ex);
        }
        color = c;
    }
    
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return supportedFlavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor df) {
        if (df.equals(colorFlavor) || df.equals(DataFlavor.stringFlavor)) 
            return true;
        return false;
    }

    @Override
    public Object getTransferData(DataFlavor df) throws UnsupportedFlavorException, IOException {
        if (df.equals(colorFlavor)) return color;
        else if (df.equals(DataFlavor.stringFlavor)) return color.toString();
        else throw new UnsupportedFlavorException(df);
    }
    
}
