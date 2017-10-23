import java.awt.*;
import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.*;
import java.util.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.*;
import javax.swing.undo.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

class PaintFrame extends JFrame
{
    private JPanel toolPanel;
    private JPanel finalPanel;
    private DrawingPanel d;
    private ToolBox toolbox;
    public PaintFrame()
    {
        setSize(800, 700);
        finalPanel = new JPanel();
        finalPanel.setLayout(new BorderLayout());
        toolPanel = new JPanel();
        d = new DrawingPanel();
        toolbox = new ToolBox();
        JButton bt = new JButton("Clear");
        bt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                d.clear();
            }
        });
        finalPanel.add(bt, BorderLayout.SOUTH);
        finalPanel.add(toolbox, BorderLayout.WEST);
        finalPanel.add(d,BorderLayout.EAST);
        add(finalPanel);
        pack();
    }
}
