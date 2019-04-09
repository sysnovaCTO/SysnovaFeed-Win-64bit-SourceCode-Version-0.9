/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysnovaFeed;

/**
 *
 * @author root
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JTabbedPane;

public class ClosableTabbedPane extends JTabbedPane
{
  private static final long serialVersionUID = 1L;
  private TabCloseUI closeUI = new TabCloseUI(this);

  public void paint(Graphics g) {
    super.paint(g);
    this.closeUI.paint(g);
  }

  public void addTab(String title, Component component) {
    super.addTab(title + "  ", component);
  }

  public String getTabTitleAt(int index)
  {
    return super.getTitleAt(index).trim();
  }

  public boolean tabAboutToClose(int tabIndex)
  {
    return true;
  }

  private class TabCloseUI
    implements MouseListener, MouseMotionListener
  {
    private ClosableTabbedPane tabbedPane;
    private int closeX = 0; private int closeY = 0; private int meX = 0; private int meY = 0;
    private int selectedTab;
    private final int width = 8; private final int height = 8;
    private Rectangle rectangle = new Rectangle(0, 0, 8, 8);

    private TabCloseUI() {
    }
    public TabCloseUI(ClosableTabbedPane pane) {
      this.tabbedPane = pane;
      this.tabbedPane.addMouseMotionListener(this);
      this.tabbedPane.addMouseListener(this);
    }
    public void mouseEntered(MouseEvent me) {
    }
    public void mouseExited(MouseEvent me) {
    }
    public void mousePressed(MouseEvent me) {
    }
    public void mouseClicked(MouseEvent me) {
    }
    public void mouseDragged(MouseEvent me) {  } 
    public void mouseReleased(MouseEvent me) { if (closeUnderMouse(me.getX(), me.getY())) {
        boolean isToCloseTab = ClosableTabbedPane.this.tabAboutToClose(this.selectedTab);
        if ((isToCloseTab) && (this.selectedTab > -1)) {
          this.tabbedPane.removeTabAt(this.selectedTab);
        }
        this.selectedTab = this.tabbedPane.getSelectedIndex();
      } }

    public void mouseMoved(MouseEvent me)
    {
      this.meX = me.getX();
      this.meY = me.getY();
      if (mouseOverTab(this.meX, this.meY)) {
        controlCursor();
        this.tabbedPane.repaint();
      }
    }

    private void controlCursor() {
      if (this.tabbedPane.getTabCount() > 0)
        if (closeUnderMouse(this.meX, this.meY)) {
          this.tabbedPane.setCursor(new Cursor(12));
          if (this.selectedTab > -1)
            this.tabbedPane.setToolTipTextAt(this.selectedTab, "Close " + this.tabbedPane.getTitleAt(this.selectedTab));
        }
        else {
          this.tabbedPane.setCursor(new Cursor(0));
          if (this.selectedTab > -1)
            this.tabbedPane.setToolTipTextAt(this.selectedTab, "");
        }
    }

    private boolean closeUnderMouse(int x, int y) {
      this.rectangle.x = this.closeX;
      this.rectangle.y = this.closeY;
      return this.rectangle.contains(x, y);
    }

    public void paint(Graphics g)
    {
      int tabCount = this.tabbedPane.getTabCount();
      for (int j = 0; j < tabCount; j++)
        if (this.tabbedPane.getComponent(j).isShowing()) {
          int x = this.tabbedPane.getBoundsAt(j).x + this.tabbedPane.getBoundsAt(j).width - 8 - 5;
          int y = this.tabbedPane.getBoundsAt(j).y + 5;
          drawClose(g, x, y);
          break;
        }
      if (mouseOverTab(this.meX, this.meY))
        drawClose(g, this.closeX, this.closeY);
    }

    private void drawClose(Graphics g, int x, int y)
    {
      if ((this.tabbedPane != null) && (this.tabbedPane.getTabCount() > 0)) {
        Graphics2D g2 = (Graphics2D)g;
        drawColored(g2, isUnderMouse(x, y) ? Color.RED : Color.WHITE, x, y);
      }
    }

    private void drawColored(Graphics2D g2, Color color, int x, int y) {
      g2.setStroke(new BasicStroke(5.0F, 1, 1));
      g2.setColor(Color.BLACK);
      g2.drawLine(x, y, x + 8, y + 8);
      g2.drawLine(x + 8, y, x, y + 8);
      g2.setColor(color);
      g2.setStroke(new BasicStroke(3.0F, 1, 1));
      g2.drawLine(x, y, x + 8, y + 8);
      g2.drawLine(x + 8, y, x, y + 8);
    }

    private boolean isUnderMouse(int x, int y)
    {
      if ((Math.abs(x - this.meX) < 8) && (Math.abs(y - this.meY) < 8))
        return true;
      return false;
    }

    private boolean mouseOverTab(int x, int y) {
      int tabCount = this.tabbedPane.getTabCount();
      for (int j = 0; j < tabCount; j++)
        if (this.tabbedPane.getBoundsAt(j).contains(this.meX, this.meY)) {
          this.selectedTab = j;
          this.closeX = (this.tabbedPane.getBoundsAt(j).x + this.tabbedPane.getBoundsAt(j).width - 8 - 5);
          this.closeY = (this.tabbedPane.getBoundsAt(j).y + 5);
          return true;
        }
      return false;
    }
  }
}