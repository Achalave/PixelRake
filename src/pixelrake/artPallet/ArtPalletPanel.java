/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pixelrake.artPallet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.Area;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.TransferHandler;
import pixelrake.DragDropHandler;
import pixelrake.TransferableColor;

/**
 *
 * @author user
 */
public class ArtPalletPanel extends javax.swing.JPanel {

    ArrayList<ArtPalletColor> colors = new ArrayList<ArtPalletColor>();
    Color transferColor;
    TransferHandler th = new DDHandler();
    ArtPalletColor clickedColor;
    Point clickedPoint;
    
    Sponge sponge = new Sponge(0,0);
    boolean dragSponge = false;
    
    Rag rag = new Rag(sponge.getBounds().width+10,0);
    boolean dragRag = false;
    
    public ArtPalletPanel() {
        initComponents();
        this.setTransferHandler(th);
    }

    public ArrayList<ArtPalletColor> getColors(){
        return colors;
    }
    
    public Color getTransferColor(){
        return transferColor;
    }
    
    public void dropColor(Color col){
        Point m = MouseInfo.getPointerInfo().getLocation();
        Point s = this.getLocationOnScreen();
        colors.add(new ArtPalletColor(col,m.x-s.x,m.y-s.y));
        checkIntersections();
        repaint();
    }
    
    private void doIntersection(ArtPalletColor one, ArtPalletColor two){
        //Separate into three areas
        Area a1 = one.getArea();
        Area a2 = two.getArea();
        Area a3 = (Area)a1.clone();
        a3.intersect(a2);
        a2.exclusiveOr(a3);
        a1.exclusiveOr(a3);
        //Now do color combination
        Color c1 = one.getColor();
        Color c2 = two.getColor();
        if(a3.getBounds().getWidth()+a3.getBounds().getHeight() > 15)
            colors.add(new ArtPalletColor(new Color((c1.getRed()+c2.getRed())/2,(c1.getGreen()+c2.getGreen())/2,(c1.getBlue()+c2.getBlue())/2,(c1.getAlpha()+c2.getAlpha())/2),a3));
    }
    
    private void checkIntersections(){
        for(int i=0; i<colors.size(); i++){
            ArtPalletColor col = colors.get(i);
            for(int k=i+1; k<colors.size(); k++){
                if(col.intersectsBasicBounds(colors.get(k).getArea())){
                    if(col.getColor().equals(colors.get(k).getColor())){
                        Area add = colors.get(k).getArea();
                        colors.remove(k);
                        col.getArea().add(add);
                    }else{
                        Area one = (Area)col.getArea().clone();
                        one.intersect(colors.get(k).getArea());
                        if(one.getBounds().getHeight() > 0 && one.getBounds().getWidth() > 0){
                            doIntersection(col,colors.get(k));
                            checkIntersections();
                            return;
                        }
                    }
                }
            }
        }
    } 
    
    
    private void applySponge(){
        for(int i=0; i<colors.size(); i++){
            Area temp = colors.get(i).getArea();
            Area sA = (Area)new Area(sponge.getBounds()).clone();
            sA.intersect(temp);
            if(sA.getBounds().getHeight() > 0 && sA.getBounds().getWidth() > 0){
                Color old = colors.get(i).getColor();
                Color newC = new Color(old.getRed(),old.getGreen(),old.getBlue(),255/2);
                colors.add(new ArtPalletColor(newC, sA));
                temp.subtract(sA);
                break;
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(ArtPalletColor c:colors)
            c.draw(g);
        sponge.draw(g);
        rag.draw(g);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clearButton = new javax.swing.JButton();

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
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });

        clearButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/trash.png"))); // NOI18N
        clearButton.setToolTipText("Clear the screen");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(0, 296, Short.MAX_VALUE)
                .add(clearButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(0, 391, Short.MAX_VALUE)
                .add(clearButton))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        
    }//GEN-LAST:event_formMouseClicked

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if(evt.isAltDown()|| evt.getButton() == 3){
            transferColor = clickedColor.getColor();
            this.getTransferHandler().exportAsDrag(this, evt, TransferHandler.COPY);
        }
        else if(dragSponge){
            sponge.setLocation(evt.getPoint().x-clickedPoint.x, evt.getPoint().y-clickedPoint.y);
            applySponge();
            checkIntersections();
            repaint();
        }
        else if(dragRag){
            rag.setLocation(evt.getPoint().x-clickedPoint.x, evt.getPoint().y-clickedPoint.y);
            for(int i=0; i<colors.size(); i++){
                ArtPalletColor p = colors.get(i);
                if(p.getArea().getBounds().intersects(rag.getBounds())){
                    p.getArea().subtract(new Area(rag.getBounds()));
                    if(p.getArea().getBounds().getHeight() == 0 && p.getArea().getBounds().getWidth() == 0){
                        colors.remove(i);
                        i++;
                    }
                }
            }
            repaint();
        }
        else if(clickedColor != null && clickedPoint != null){
            clickedColor.setLoctation(evt.getPoint().x-clickedPoint.x, evt.getPoint().y-clickedPoint.y);
            repaint();
        }
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        //Drage the sponge
        if(sponge.getBounds().contains(evt.getPoint())){
            dragSponge = true;
            clickedPoint = new Point(evt.getPoint().x-sponge.getBounds().x,evt.getPoint().y-sponge.getBounds().y);
        }
        //Drag the rag
        else if(rag.getBounds().contains(evt.getPoint())){
            dragRag = true;
            clickedPoint = new Point(evt.getPoint().x-rag.getBounds().x,evt.getPoint().y-rag.getBounds().y);
        }
        //Drag a color
        else{
            for(int i=0; i<colors.size(); i++){
                if(colors.get(i).intersectsPoint(evt.getPoint())){
                    clickedColor = colors.get(i);
                    clickedPoint = new Point(evt.getPoint().x-colors.get(i).getX(),evt.getPoint().y-colors.get(i).getY());
                }
            }
        }
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        checkIntersections();
        if(clickedColor != null && clickedPoint != null){
            repaint();
        }else if(dragSponge){
            sponge.setLocation(0, 0);
            repaint();
        }else if(dragRag){
            rag.setLocation(sponge.getBounds().width+10, 0);
            repaint();
        }
        clickedColor = null;
        clickedPoint = null;
        dragSponge = false;
        dragRag = false;
    }//GEN-LAST:event_formMouseReleased

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        colors.clear();
        repaint();
    }//GEN-LAST:event_clearButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    // End of variables declaration//GEN-END:variables
}




class DDHandler extends TransferHandler{
    String mimeType = DataFlavor.javaJVMLocalObjectMimeType + ";class=java.awt.Color";
    DataFlavor colorFlavor;
    
    //Setup the color flavor
    public DDHandler(){
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
                ((ArtPalletPanel)c).dropColor(col);
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
        return new TransferableColor(((ArtPalletPanel)c).getTransferColor());
    }
    
    @Override
    public int getSourceActions(JComponent c) {
        return COPY;
    }
}
