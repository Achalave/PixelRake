
package pixelrake.artPallet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Sponge {
    
    Image image;
    int x, y;
    
    public Sponge(int x, int y){
        this.x = x;
        this.y = y;
        try {
            image = ImageIO.read(Sponge.class.getResource("/icons/sponge.png"));
        } catch (IOException ex) {
            Logger.getLogger(Sponge.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void draw(Graphics g){
        g.drawImage(image, x, y, null);
    }
    
    
    
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x,y,image.getWidth(null),image.getHeight(null));
    }
    
    
}
