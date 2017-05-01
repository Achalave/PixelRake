
package pixelrake;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CloseableTab extends JPanel{
    private final JTabbedPane pane;
    public CloseableTab(final JTabbedPane p){
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pane = p;
        if (pane == null) {
            throw new NullPointerException("TabbedPane is null");
        }
        setOpaque(false);
        JLabel label = new JLabel() {
            public String getText() {
                int i = pane.indexOfTabComponent(CloseableTab.this);
                if (i != -1) {
                    return pane.getTitleAt(i);
                }
                return null;
            }
        };
        add(label);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        final JButton button = new JButton();
        button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close.png")));
        button.setContentAreaFilled(false);
        int size = 17;
        button.setPreferredSize(new Dimension(size, size));
        button.setToolTipText("close this image");
        button.setRolloverEnabled(true);
        button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                int i = pane.indexOfTabComponent(CloseableTab.this);
                if (i != -1) {
                    pane.remove(i);
                }
            }
            
        });
        add(button);
        
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        
    }
}
