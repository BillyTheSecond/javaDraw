import java.awt.*;
import java.awt.event.MouseEvent;

public class Trait extends DrawOption {
    private int x1, y1;
    private int x2, y2;

    @Override
    public void onPressed(MouseEvent m, Graphics g) {
        x1 = m.getX();
        y1 = m.getY();

    }

    public Trait() {
        super("Trait", "Dessine un trait", 1);

    }

    @Override
    public void onReleased(MouseEvent m, Graphics g) {
        this.x2 = m.getX();
        this.y2 = m.getY();
        // Utiliser la couleur partagée
        g.setColor(DrawOption.getFill_color());
        g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }

}
