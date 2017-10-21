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
            B.add(new JButton("i"));
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
