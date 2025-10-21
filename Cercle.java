import java.awt.*;
import java.awt.event.MouseEvent;

public class Cercle extends DrawOption {
    private int x1, y1;
    private int x2, y2;

    @Override
    public void onPressed(MouseEvent m, Graphics g) {
        x1 = m.getX();
        y1 = m.getY();

    }

    public Cercle() {
        super("Cercle", "Dessine un cercle", 1);


    }

    @Override
    public void onReleased(MouseEvent m, Graphics g) {
        int x = m.getX();
        int y = m.getY();

            if (y < y1) {
                y2 = y1;
                y1 = y;
            } else {
                y2 = y;
            }
            if (x < x1) {
                x2 = x1;
                x1 = x;
            } else {
                x2 = x;
            }
            // Utiliser la couleur (globale)
            g.setColor(DrawOption.getFill_color());

            // Mode de remplissage global
            if (DrawOption.getFillMode() == 1) {
                g.fillOval((int) x1, (int) y1, (int) (x2 - x1), (int) (y2 - y1));
            } else {
                g.drawOval((int) x1, (int) y1, (int) (x2 - x1), (int) (y2 - y1));
            }
    }

}
