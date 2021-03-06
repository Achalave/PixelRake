/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pixelrake;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import pixelrake.artPallet.ArtPallet;

/**
 *
 * @author user
 */
public class PixelRake extends javax.swing.JFrame {

    public static boolean showPixelLines = true;
    public static boolean showImageSizeLines = true;
    
    public static JFrame colorChooserFrame = new JFrame("Color Chooser");
    public static JFrame toolBoxFrame = new JFrame("Tool Box");
    public static ArtPallet artPallet = new ArtPallet();
    
    public static JColorChooser colorChooserView = new JColorChooser();
    public static ToolPanel toolPanelView = new ToolPanel();
    
    
    public static DrawPanel drawPanel= null;
    
    private File lastFile = null;
    
    public PixelRake() {
        super("Pixel Rake");
        initComponents();
        this.setAlwaysOnTop(false);
        colorChooserFrame.add(colorChooserView);
        colorChooserView.setDragEnabled(true);
        colorChooserFrame.setAlwaysOnTop(true);
        colorChooserFrame.pack();
        
        toolBoxFrame.add(toolPanelView);
        toolBoxFrame.setAlwaysOnTop(true);
        toolBoxFrame.pack();
        
        artPallet.setAlwaysOnTop(true);
        
        addNewTab("Businessman.png");
        try {
            drawPanel.loadImage("Businessman.png");
        } catch (IOException ex) {
            Logger.getLogger(PixelRake.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public static Point getPixelOnImage(Point p){
        return drawPanel.getPointOnImage(p);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabPanel = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        newImage = new javax.swing.JMenu();
        save = new javax.swing.JMenuItem();
        load = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        zoom = new javax.swing.JMenuItem();
        clear = new javax.swing.JMenuItem();
        backgroundColor = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        pixelLineCheckbox = new javax.swing.JCheckBoxMenuItem();
        imageSizeLineCheckbox = new javax.swing.JCheckBoxMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        colorPalette = new javax.swing.JMenuItem();
        toolBox = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabPanel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabPanelStateChanged(evt);
            }
        });

        jMenu1.setText("File");

        newImage.setText("New");
        jMenu1.add(newImage);

        save.setText("Save");
        jMenu1.add(save);

        load.setText("Load");
        load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadActionPerformed(evt);
            }
        });
        jMenu1.add(load);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        zoom.setText("Zoom");
        zoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomActionPerformed(evt);
            }
        });
        jMenu2.add(zoom);

        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jMenu2.add(clear);

        backgroundColor.setText("Background Color");
        backgroundColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backgroundColorActionPerformed(evt);
            }
        });
        jMenu2.add(backgroundColor);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Show");

        pixelLineCheckbox.setSelected(true);
        pixelLineCheckbox.setText("Pixel Lines");
        pixelLineCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pixelLineCheckboxActionPerformed(evt);
            }
        });
        jMenu3.add(pixelLineCheckbox);

        imageSizeLineCheckbox.setSelected(true);
        imageSizeLineCheckbox.setText("Image Size Lines");
        imageSizeLineCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imageSizeLineCheckboxActionPerformed(evt);
            }
        });
        jMenu3.add(imageSizeLineCheckbox);
        jMenu3.add(jSeparator1);

        colorPalette.setText("Color Chooser");
        colorPalette.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorPaletteActionPerformed(evt);
            }
        });
        jMenu3.add(colorPalette);

        toolBox.setText("Tool Box");
        toolBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBoxActionPerformed(evt);
            }
        });
        jMenu3.add(toolBox);

        jMenuItem1.setText("Art Pallet");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, tabPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 921, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, tabPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 946, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void zoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomActionPerformed
        if(drawPanel == null)
            return;
        int size;
        try{
            String s = JOptionPane.showInputDialog(this, "Set zoom:", drawPanel.getBoxSize());
            if(s == null)
                return;
            size = Integer.parseInt(s);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "You must enter a non-decimal numaric value.");
            return;
        }
        if(size <= 0){
            JOptionPane.showMessageDialog(this, "You must enter a numaric value greater than zero.");
            return;
        }
        drawPanel.setBoxSize(size);
    }//GEN-LAST:event_zoomActionPerformed

    private void pixelLineCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pixelLineCheckboxActionPerformed
        showPixelLines = pixelLineCheckbox.getState();
    }//GEN-LAST:event_pixelLineCheckboxActionPerformed

    private void imageSizeLineCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imageSizeLineCheckboxActionPerformed
        showImageSizeLines = imageSizeLineCheckbox.getState();
    }//GEN-LAST:event_imageSizeLineCheckboxActionPerformed

    private void loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadActionPerformed
        JFileChooser jfc = new JFileChooser();
        FileFilter f = new FileFilter(){

            @Override
            public boolean accept(File file) {
                if(file.isDirectory())
                    return true;
                Image i;
                try{
                    i = ImageIO.read(file);
                }catch(Exception e){
                    return false;
                }
                return (i != null);
            }

            @Override
            public String getDescription() {
                return "Images";
            }
            
        };
        jfc.setFileFilter(f);
        if(lastFile != null){
            jfc.setCurrentDirectory(lastFile);
        }
        int val = jfc.showOpenDialog(this);
        if(val == JFileChooser.APPROVE_OPTION){
            try {
                File file = jfc.getSelectedFile();
                addNewTab(file.getName());
                tabPanel.setSelectedIndex(tabPanel.getTabCount()-1);
                drawPanel.loadImage(file.getPath());
                lastFile = file;
            } catch (IOException ex) {
                Logger.getLogger(PixelRake.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_loadActionPerformed

    private void addNewTab(String s){
        tabPanel.add(s, new DrawPanel());
        tabPanel.setTabComponentAt(tabPanel.getTabCount()-1,new CloseableTab(tabPanel));
        artPallet.addNewTab(s);
    }
    
    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        if(drawPanel == null)
            return;
        drawPanel.clear();
    }//GEN-LAST:event_clearActionPerformed

    private void colorPaletteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorPaletteActionPerformed
        colorChooserFrame.setVisible(true);
    }//GEN-LAST:event_colorPaletteActionPerformed

    private void toolBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBoxActionPerformed
        toolBoxFrame.setVisible(true);
    }//GEN-LAST:event_toolBoxActionPerformed

    private void tabPanelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabPanelStateChanged
        drawPanel = (DrawPanel)tabPanel.getSelectedComponent();
    }//GEN-LAST:event_tabPanelStateChanged

    private void backgroundColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backgroundColorActionPerformed
        if(drawPanel == null)
            return;
        Color newColor = JColorChooser.showDialog(this,"Choose Background Color",drawPanel.getBackground());
        if(newColor != null)
            drawPanel.setBackground(newColor);
    }//GEN-LAST:event_backgroundColorActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        artPallet.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PixelRake.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PixelRake.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PixelRake.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PixelRake.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new PixelRake().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem backgroundColor;
    private javax.swing.JMenuItem clear;
    private javax.swing.JMenuItem colorPalette;
    private javax.swing.JCheckBoxMenuItem imageSizeLineCheckbox;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem load;
    private javax.swing.JMenu newImage;
    private javax.swing.JCheckBoxMenuItem pixelLineCheckbox;
    private javax.swing.JMenuItem save;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JMenuItem toolBox;
    private javax.swing.JMenuItem zoom;
    // End of variables declaration//GEN-END:variables
}
