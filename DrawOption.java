import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class DrawOption {
    // correspond à un type de dessin et sses classes
    private String name = "Label";
    private String description = "Description";
    private int active = 1; // 0 id disabled, 1 if enabled
    private static Color fill_color = Color.BLACK; // Couleur partagée par tous les composants


    public DrawOption() {

    }


    public DrawOption(String name, String description, int active) {
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public static Color getFill_color() {
        return fill_color;
    }

    public static void setFill_color(Color color) {
        fill_color = color;
    }

    public void onClicked(int x, int y) {
        System.out.println("Mouse clicked");

    }

    public void onPressed(MouseEvent m, Graphics g) {
        System.out.println("Mouse pressed");


    }

    public void onReleased(int x, int y) {
        System.out.println("Mouse released");

    }

    public void onDragged(int x, int y) {
        System.out.println("Mouse dragged");
    }


}
