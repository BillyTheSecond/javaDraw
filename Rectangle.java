import java.awt.*;
import java.awt.event.MouseEvent;

public class Rectangle extends DrawOption {
    private int x1, y1;
    private int x2, y2;

    @Override
    public void onPressed(MouseEvent m, Graphics g) {
        // Initialiser les coordonnées du premier point
        x1 = m.getX();
        y1 = m.getY();
    }

    public Rectangle() {
        super("Rectangle", "Dessine un rectangle", 1);


    }

    @Override
    public void onReleased(MouseEvent m, Graphics g) {
        // Récupérer les coordonnées du second point
        x2 = m.getX();
        y2 = m.getY();

        // Calculer les coordonnées du rectangle
        int startX = Math.min(x1, x2);
        int startY = Math.min(y1, y2);
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);

        // Définir la couleur de remplissage
        g.setColor(DrawOption.getFill_color());

        // Dessiner le rectangle en fonction du mode de remplissage
        if (DrawOption.getFillMode() == 1) {
            g.fillRect(startX, startY, width, height);
        } else {
            g.drawRect(startX, startY, width, height);
        }
    }

}
