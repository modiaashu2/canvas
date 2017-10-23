import java.awt.*;
import java.awt.GraphicsEnvironment;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;
import java.awt.geom.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.*;
import javax.swing.undo.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

class DrawingPanel extends JComponent
{
    private ArrayList< ArrayList<Point2D> > pt;
    private ArrayList<Point2D> pt2;
    private ArrayList< Point2D > tmp;
    private Image img;
    private Graphics2D g2;
    private int a, b, c, d;
    public Point2D find(Point2D p)
    {
        for(Point2D r : pt2)
        if(r == p)
        return r;
        return null;
    }
    public DrawingPanel()
    {
        setSize(800, 800);
        pt2 = new ArrayList<>();
        pt = new ArrayList<>();
        tmp = null;

        addMouseListener(new MouseAdapter(){
            /*public void mouseClicked(MouseEvent e)
            {
                System.out.println(ToolBox.getSelected());
                tmp.add(e.getPoint());
                pt.add(tmp);
                repaint();
            }*/
            public void mousePressed(MouseEvent e)
            {
                //System.out.println(ToolBox.getSelected());
                a = e.getX();
                b = e.getY();
                g2.drawLine(a, b, a, b);
                if(ToolBox.getSelected() == 1)
                {
                    Rectangle2D k = new Rectangle2D.Double(a - 5, b - 5, 10, 10);
                    g2.fill(k);
                }
                else if(ToolBox.getSelected() == 2)
                {
                    Ellipse2D k = new Ellipse2D.Double();
                    double radius = 5;
                    k.setFrameFromCenter(a, b, a + radius, b + radius);
                    g2.fill(k);
            //        k.setFrameFromCenter(c, d, c + radius, d + radius);
            //        g2.fill(k);
                }
                /*tmp = new ArrayList<>();
                tmp.add(e.getPoint());
                pt.add(tmp);
                repaint();*/
                repaint();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e)
            {
                //System.out.println(ToolBox.getSelected());
                //tmp.add(e.getPoint());
                c = e.getX();
                d = e.getY();
                if(g2 != null)
                {
                    if(ToolBox.getSelected() == 1)
                    {
                        g2.drawLine(a, b, a, b);
                        g2.drawLine(c, d, c, d);
                        Ellipse2D k = new Ellipse2D.Double();
                        double radius = 5;
                        k.setFrameFromCenter(a, b, a + radius, b + radius);
                //        g2.fill(k);
                        k.setFrameFromCenter(c, d, c + radius, d + radius);
                //        g2.fill(k);
                        g2.drawLine(a, b, c, d);
                        for(int i = 0; i < 5; i++)
                        {
                            g2.drawLine(a + i + 1, b + i + 1, c + i + 1, d + i + 1);
                            g2.drawLine(a + i + 1, b + i + 1, c + i + 1, d - i - 1);
                            g2.drawLine(a + i + 1, b + i + 1, c - i - 1, d + i + 1);
                            g2.drawLine(a + i + 1, b + i + 1, c - i - 1, d - i - 1);
                        }
                        for(int i = 0; i < 5; i++)
                        {
                            g2.drawLine(a + i + 1, b - i - 1, c + i + 1, d + i + 1);
                            g2.drawLine(a + i + 1, b - i - 1, c + i + 1, d - i - 1);
                            g2.drawLine(a + i + 1, b - i - 1, c - i - 1, d + i + 1);
                            g2.drawLine(a + i + 1, b - i - 1, c - i - 1, d - i - 1);
                        }
                        for(int i = 0; i < 5; i++)
                        {
                            g2.drawLine(a - i - 1, b + i + 1, c + i + 1, d + i + 1);
                            g2.drawLine(a - i - 1, b + i + 1, c + i + 1, d - i - 1);
                            g2.drawLine(a - i - 1, b + i + 1, c - i - 1, d + i + 1);
                            g2.drawLine(a - i - 1, b + i + 1, c - i - 1, d - i - 1);
                        }
                        for(int i = 0; i < 5; i++)
                        {
                            g2.drawLine(a - i - 1, b - i - 1, c + i + 1, d + i + 1);
                            g2.drawLine(a - i - 1, b - i - 1, c + i + 1, d - i - 1);
                            g2.drawLine(a - i - 1, b - i - 1, c - i - 1, d + i + 1);
                            g2.drawLine(a - i - 1, b - i - 1, c - i - 1, d - i - 1);
                        }
                    }
                    else if(ToolBox.getSelected() == 2)
                    {
                        g2.setStroke(new BasicStroke(10));
                        g2.drawLine(a, b, c, d);
                        g2.setStroke(new BasicStroke());
                    }
                    else
                        g2.drawLine(a, b, c, d);
                }
                repaint();
                a = c;
                b = d;
            }
        });
    }

    public void clear(){
        g2.setPaint(Color.white);
		g2.fillRect(0, 0, getSize().width, getSize().height);
		g2.setPaint(Color.black);
        g2.setColor(Color.BLUE);
		repaint();
	}

    public Dimension getPreferredSize()
    {
        return new Dimension(700, 700);
    }
    public void paintComponent(Graphics g)
    {
        if(img == null)
        {
            img = createImage(getWidth(), getHeight());
            g2 = (Graphics2D)img.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(img, 0, 0, null);
        /*g2 = (Graphics2D)g;
        g2.setColor(new Color(255, 255, 255));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        g2.setColor(Color.BLACK);
        if(ToolBox.getSelected() == 1)
        {
            for(ArrayList<Point2D> p : pt)
            {
                for(Point2D x : p)
                {
                    if(find(x) == null)
                    {
                        pt2.add(x);
                    }
                }
            }
            for(Point2D p : pt2)
            {
                g2.drawLine((int)p.getX(), (int)p.getY(), (int)p.getX(), (int)p.getY());
            }
        }
        else
        for(ArrayList<Point2D> p : pt)
        {
            for(int i = 0; i < p.size() - 1; i++)
            {
                g2.drawLine((int)p.get(i).getX(), (int)p.get(i).getY(), (int)p.get(i + 1).getX(), (int)p.get(i + 1).getY());
            }
            if(p.size() == 1)
            g2.drawLine((int)p.get(0).getX(), (int)p.get(0).getY(), (int)p.get(0).getX(), (int)p.get(0).getY());
        }
        */
    }
}
