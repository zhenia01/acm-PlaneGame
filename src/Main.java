import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.util.Random;

public class Main extends GraphicsProgram {

    private Plane plane;
    private House[] houses = new House[15];
    private Shot shot = null;
    private int count = houses.length;

    public void init() {
        setSize(1600, 1200);
        plane = new Plane();

        getGCanvas().addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                plane.setLocation(e.getX() - plane.getWidth() / 2.0, plane.getY());
            }
        });

        for (int i = 0; i < houses.length; i++) {
            Random rand = new Random();

            int w = rand.nextInt(200) + 100;
            int h = rand.nextInt((getHeight()- 200) / houses.length) + (getHeight()- 200) / houses.length;

            int x = rand.nextInt(getWidth() - w) + 5;
            int y = i * ((getHeight()- 200) / houses.length);
            houses[i] = new House();
            houses[i].setSize(w, h);
            add(houses[i], x, y);
        }

        add(plane, getWidth() / 2.0, getHeight() - 75);

    }

    public void run() {

        Rules rules = new Rules();

        double speed = rules.setSpeed();
        rules.begin();

        waitForClick();


        getGCanvas().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (shot == null) {
                    shot = new Shot();
                    add(shot, plane.getX() + 20, plane.getY());
                    shot.sendToBack();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        while (plane.getY() > -plane.getHeight() && !isWinner()) {
            plane.move(0, speed);
            moveShot();
            checkTarget();
            checkPos();
            pause(0.01);
        }

        rules.end(count);
        exit();
    }


    private void moveShot() {
        if (shot != null) {
            shot.move(0, -2);
        }
    }

    private void checkPos() {
        if (shot != null) {

            if (shot.getY() <= -shot.getHeight()) {
                remove(shot);
                shot = null;
            }
        }
    }

    private boolean isWinner() {
        return count == 0;
    }

    private void checkTarget() {
        if (shot != null) {
            GObject obj = getElementAt(shot.getX() + shot.getWidth() / 2.0, shot.getY() -  1);
            if (obj != null) {
                count--;
                remove(obj);
                remove(shot);
                shot = null;
            }
        }
    }

}
