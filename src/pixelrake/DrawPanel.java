/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pixelrake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

/**
 *
 * @author user
 */
public class DrawPanel extends javax.swing.JPanel {

    //Settings
    
    Dimension imageSize = null;
    
    ArrayList<ArrayList<Color>> pixels = new ArrayList<ArrayList<Color>>();
    int boxSize = 20;
    boolean mousePressed = false;
    Point mouse = new Point(0,0);
    Point leftCorner = new Point(0,0);
    DragDropHandler ddh = new DragDropHandler();
    
    public DrawPanel() {
        initComponents();
        setBackground(Color.WHITE);
        this.setTransferHandler(ddh);
    }
    
    public int getBoxSize() {
        return boxSize;
    }

    public ArrayList<ArrayList<Color>> getAllPixels(){
        return pixels;
    }
    
    public DragDropHandler getDragHandler(){
        return ddh;
    }
    
    public void setBoxSize(int boxSize) {
        this.boxSize = boxSize;
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //Paint pixels
        for(int x=0; x<pixels.size(); x++){
            for(int y=0; y<pixels.get(x).size(); y++){
                if(pixels.get(x).get(y) != null){
                    g.setColor(pixels.get(x).get(y));
                    g.fillRect((x-leftCorner.x)*boxSize, (y-leftCorner.y)*boxSize, boxSize, boxSize);
                }
            }
        }
        
        PixelRake.toolPanelView.getCurrentPaintTool().draw(g);
        
        if(PixelRake.showPixelLines && boxSize > 4){
            g.setColor(Color.BLACK);
            for(int i=0; i<getWidth(); i+=boxSize){
                for(int k=0; k<getHeight(); k+=boxSize){
                    g.drawRect(i, k, boxSize, boxSize);
                }
            }
        }
        if(imageSize != null && PixelRake.showImageSizeLines){
            g.setColor(Color.RED);
            g.drawRect(-leftCorner.x*boxSize, -leftCorner.y*boxSize, (int)imageSize.getWidth()*boxSize, (int)imageSize.getHeight()*boxSize);
        }
        
    }
    
    public void loadImage(String path) throws IOException{
        final File file = new File(path);
        final BufferedImage image = ImageIO.read(file);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                final int clr = image.getRGB(x, y);
                final int red = (clr & 0x00ff0000) >> 16;
                final int green = (clr & 0x0000ff00) >> 8;
                final int blue = clr & 0x000000ff;
                int alpha = (clr>>24) & 0xff;
                Color c = new Color(red,green,blue,alpha);
                try{
                    pixels.get(x).add(c);
                }catch(IndexOutOfBoundsException e){
                    pixels.add(new ArrayList<Color>());
                    pixels.get(x).add(c);
                }
            }
        }
        imageSize = new Dimension(image.getWidth(), image.getHeight());
        repaint();
    }
        
    public void clear(){
        pixels.clear();
        repaint();
    }
    
    //Returns the image pixel of the mouse
    public Point getMousePixelLocation(){
        return new Point((mouse.x+leftCorner.x*boxSize)/boxSize, (mouse.y+leftCorner.y*boxSize)/boxSize);
    }
    
    //Returns the point of the image at point p on the screen
    public Point getPointOnImage(Point p){
        return new Point((p.x+leftCorner.x*boxSize)/boxSize, (p.y+leftCorner.y*boxSize)/boxSize);
    }
    
    //Returns the point on screen at point p scaled to box size (Not relative to the screen)
    public Point getPointOnScreen(Point p){
        return new Point((p.x)/boxSize, (p.y)/boxSize);
    }
    
    //Returns the pixel at Point p on the Image (Not relative to the screen)
    public Color getPixel(Point p){
        return pixels.get(p.x).get(p.y);
    }
    //Sets the pixel p on the image (Not relative to the screen)
    public void setPixel(Point p, Color c){
        pixels.get(p.x).set(p.y,c);
        repaint();
    }
    
    //Sets the pixels at Point p on the Image (Relative to the screen)
    public void setPixelsOnScreen(Set<Point> pnts, Color col){
        for(Point p:pnts){
            pixels.get(p.x+leftCorner.x).set(p.y+leftCorner.y,col);
        }
        repaint();
    }
    
    public void setPixelOnScreen(Point p, Color col){
        setPixel(new Point(p.x+leftCorner.x,p.y+leftCorner.y),col);
    }
    
    //For drag drop
    public void dropPixel(Color col){
        Point m = MouseInfo.getPointerInfo().getLocation();
        Point onScreen = this.getLocationOnScreen();
        setPixel(getPointOnImage(new Point(m.x-onScreen.x, m.y-onScreen.y)),col);
        repaint();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(900, 900));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 770, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 582, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        ((MouseListener)(PixelRake.toolPanelView.getCurrentPaintTool())).mousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        ((MouseMotionListener)(PixelRake.toolPanelView.getCurrentPaintTool())).mouseMoved(evt);
        mouse.setLocation(evt.getPoint());
    }//GEN-LAST:event_formMouseMoved

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        //System.out.println("LEFT CORNER: "+leftCorner);
        if(evt.getKeyCode() == KeyEvent.VK_LEFT){
            if(leftCorner.x > 0)
                leftCorner.x--;
        }else if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
            leftCorner.x++;
        }else if(evt.getKeyCode() == KeyEvent.VK_UP){
            if(leftCorner.y > 0)
                leftCorner.y--;
        }else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            leftCorner.y++;
        }
        repaint();
    }//GEN-LAST:event_formKeyPressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        mouse.setLocation(evt.getPoint());
        ((MouseMotionListener)(PixelRake.toolPanelView.getCurrentPaintTool())).mouseDragged(evt);
    }//GEN-LAST:event_formMouseDragged

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        requestFocus();
        ((MouseListener)(PixelRake.toolPanelView.getCurrentPaintTool())).mouseEntered(evt);
    }//GEN-LAST:event_formMouseEntered

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        ((MouseListener)(PixelRake.toolPanelView.getCurrentPaintTool())).mouseReleased(evt);
    }//GEN-LAST:event_formMouseReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        ((MouseListener)(PixelRake.toolPanelView.getCurrentPaintTool())).mouseClicked(evt);
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
