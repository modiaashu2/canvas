import java.awt.*;
import java.awt.GraphicsEnvironment;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.*;
import javax.swing.undo.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;
import javax.swing.border.*;
import java.util.*;
import java.awt.font.TextAttribute;
import java.awt.image.*;

class ToolBox extends JComponent
{
    private ArrayList<JButton> B;
    private static int ct;
    public ToolBox()
    {
        B = new ArrayList<>();
        setSize(50, 500);
        setLayout(new GridLayout(8, 2));
        for(int i = 0; i < 16; i++)
        {
            ImageIcon imag = new ImageIcon(getClass().getResource("resources//paint.png"));
            Image img = imag.getImage();
            BufferedImage resizedImg = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g4 = resizedImg.createGraphics();
            g4.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g4.drawImage(img, 0, 0, 40, 40, null);
            g4.dispose();
            imag = new ImageIcon(resizedImg);
            B.add(new JButton("", imag));
            B.get(i).setPreferredSize(new Dimension(40, 40));
            add(B.get(i));
            final int z = i;
            B.get(i).addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    ct = z;
                }
            });
        }
    }
    public static int getSelected()
    {
        return ct;
    }
    public Dimension getPreferredSize()
    {
        return new Dimension(100, 500);
    }
}
