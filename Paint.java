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

class Paint
{
    public static void main(String Ar[])
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                PaintFrame frame = new PaintFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setTitle("Paint it all out");
            }
        });
    }
}
