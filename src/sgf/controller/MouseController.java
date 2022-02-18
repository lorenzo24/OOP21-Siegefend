package sgf.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
/**
 * This class manages the user's mouse input.
 */
public class MouseController implements MouseListener, MouseMotionListener {

    @Override
    public void mouseDragged(final MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        // Simple way to obtain and print mouse position when clicking.
        if (e.getButton() == MouseEvent.BUTTON1) {
            System.err.println(e.getX() + ":" + e.getY());
        }
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(final MouseEvent e) {
        // TODO Auto-generated method stub
    }

}
