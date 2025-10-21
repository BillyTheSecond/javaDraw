import java.awt.*;
import java.awt.event.MouseEvent;

public class Rectangle extends DrawOption {
    private int x1, y1;
    private int x2, y2;

    @Override
    public void onPressed(MouseEvent m, Graphics g) {
        x1 = m.getX();
        y1 = m.getY();

    }

    public Rectangle() {
        super("Rectangle", "Dessine un rectangle", 1);


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

        // Utiliser la couleur partagée
        g.setColor(DrawOption.getFill_color());

        int largeur = Math.abs(x2 - x1);
        int hauteur = Math.abs(y2 - y1);

        if (DrawOption.getFillMode() == 1) {
            g.fillRect(x1, y1, largeur, hauteur);
        } else {
            g.drawRect(x1, y1, largeur, hauteur);
        }
    }

}
