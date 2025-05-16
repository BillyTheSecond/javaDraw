package TP1;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class main extends JFrame {
    int x, y;

    // states and settings
    int mouseMode = 0;
    int step = 0; // etape du tracé
    int fillMode = 0; // mode de remplissage (0: pas de remplissage, 1: remplissage)

    double x1 = -1; // coordonnées du tracé (trait, rect, cercle)
    double y1 = -1;
    double x2 = -1;
    double y2 = -1;
    // code hexa couleur

    // tableau de couleurs
    Color[] colors = { Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.MAGENTA,
            Color.CYAN };

    Color fill_color = colors[0]; // couleur par défaut = Première couleur du tableau

    // 0 = curseur classique
    // 1 = tracé de trait
    // 2 = tracé de rectangle

    public main() {
        addMouseListener(new Souris(this));
        setSize(400, 600);
        setVisible(true);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(d.width / 2 - this.getWidth() / 2, d.height / 2 - this.getHeight() / 2);
    }

    public void paint(Graphics g) {
        super.paint(g);
        // g.drawLine(x, y, x + 100, y + 100);

        // carré
        // g.drawRect(x, y, 50, 50);

        // cercle
        // g.drawOval(x + 100, y + 100, 50, 50);

        // texte
        // g.drawString("texte", x, y);

        afficherMenu(g);
    }

    public void afficherMenu(Graphics g) {
        // afficher des boutons trait, reclangle, cercle en bas de la fenêtre
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, getHeight() - 50, getWidth(), 50);
        g.setColor(Color.BLACK);
        g.drawString("Trait", 10, getHeight() - 20);
        g.drawString("Rectangle", 100, getHeight() - 20);
        g.drawString("Cercle", 200, getHeight() - 20);
        g.drawString("Fill Mode", 300, getHeight() - 20);
        g.drawString("Effacer", 400, getHeight() - 20);

        // menu de choix des couleurs avec couleur de remlissage et couleur de contour
        // dans le même
        int nbColors = colors.length;

        // parcourir le tableau des couleurs
        for (int i = 0; i < nbColors; i++) {
            g.setColor(colors[i]);
            g.fillRect(i * (getWidth() / nbColors), getHeight() - 100, getWidth() / nbColors, 50);
        }

    }

    public void handleTrait(int x, int y) {
        if (step == 0) {
            x1 = x;
            y1 = y;
            step++;
        } else if (step == 1) {
            x2 = x;
            y2 = y;
            step = 0;
            Graphics g = getGraphics();
            // set line color
            g.setColor(fill_color);
            g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
            // this.mouseMode = 0; // revenir au mode classique
        }
    }

    public void handleRectangle(int x, int y) {
        if (step == 0) {
            x1 = x;
            y1 = y;
            step++;
        } else if (step == 1) {
            // si la coordonnée du 2nd point est au dessus du 1er point
            // on inverse les coordonnées
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
            Graphics g = getGraphics();
            // set fill color
            g.setColor(fill_color);

            // valeur absolue
            int largeur = (int) Math.abs(x2 - x1);
            int hauteur = (int) Math.abs(y2 - y1);  

            if (this.fillMode == 1) {
                g.fillRect((int) x1, (int) y1, largeur, hauteur);
            } else {
                g.drawRect((int) x1, (int) y1, largeur, hauteur);

            }
            step = 0;
            // this.mouseMode = 0; // revenir au mode classique
        }
    }

    public void handleCercle(int x, int y) {
        if (step == 0) {
            x1 = x;
            y1 = y;
            step++;
        } else if (step == 1) {
            // si la coordonnée du 2nd point est au dessus du 1er point
            // on inverse les coordonnées
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
            step = 0;
            Graphics g = getGraphics();
            g.setColor(fill_color);
            if (this.fillMode == 1) {
                g.fillOval((int) x1, (int) y1, (int) (x2 - x1), (int) (y2 - y1));
            } else
                g.drawOval((int) x1, (int) y1, (int) (x2 - x1), (int) (y2 - y1));
            // this.mouseMode = 0; // revenir au mode classique
        }
    }

    public void setSelectedColor(int x) {
        // reçoit la position du clic de la souris et set la couleur cliquée
        int colorCaseWidth = getWidth() / colors.length;

        // trouver le numéro de la couleur cliquée
        int colorIndex = x / colorCaseWidth;
        // TODO: Vérifier comment se fait l'arondissement.
        // Il faudrait qu'il se fasse toujours vers le bas.
        // set la couleur de remplissage
        fill_color = colors[colorIndex];
        System.out.println("La couleur est définir sur " + fill_color);

        // définir la couleur de remplissage pour tous les tracés
        Graphics g = getGraphics();
        g.setColor(fill_color);

    }

    public void affect(int x, int y) {
        this.x = x;
        this.y = y;
        repaint();
    }

    public static void main(String[] args) {
        main a = new main();
    }

    class Souris extends MouseAdapter {
        main d;

        Souris(main a) {
            d = a;
        }

        public void mouseClicked(MouseEvent m) {
            int x = m.getX();
            int y = m.getY();
            System.out.println("Clic détecté");

            // d.affect(x, y);

            // Si on clique dans la zone de sélection de couleur
            if (y <= getHeight() - 50 && y >= getHeight() - 100) {
                d.setSelectedColor(x);
                System.out.println("Clic sur la zone de couleur");
            }
            // Si on clique sur le bouton "Trait"
            else if (x >= 0 && x <= 50 && y >= d.getHeight() - 50 && y <= d.getHeight()) {
                d.mouseMode = 1;
                d.step = 0;
                // d.affect(x, y);
                System.out.println("Clic sur le bouton TRAIT");

            }
            // Si on clique sur le bouton "Rectangle"
            else if (x >= 100 && x <= 200 && y >= d.getHeight() - 50 && y <= d.getHeight()) {
                d.mouseMode = 2;
                d.step = 0;
                System.out.println("Clic sur le bouton RECTANGLE");
            }
            // Si on clique sur le bouton "Cercle"
            else if (x >= 200 && x <= 300 && y >= d.getHeight() - 50 && y <= d.getHeight()) {
                d.mouseMode = 3;
                d.step = 0;
                System.out.println("Clic sur le bouton CERCLE");
            }
            // si on clique sur fill mode
            else if (x >= 300 && x <= 400 && y >= d.getHeight() - 50 && y <= d.getHeight()) {
                d.fillMode = (d.fillMode + 1) % 2; // toggle fill mode
                d.step = 0;
                System.out.println("Clic sur le bouton FILL MODE");
            }
            // Si on clique sur le bouton "Effacer"
            else if (x >= 400 && x <= 500 && y >= d.getHeight() - 50 && y <= d.getHeight()) {
                d.mouseMode = 0;
                d.step = 0;
                System.out.println("Clic sur le bouton EFFACER");
                Graphics g = d.getGraphics();
                g.clearRect(0, 0, d.getWidth(), d.getHeight());
                d.repaint();
            }

            // si on clique sur le zone de tracé

            // si on est en mode tracé de trait
            else if (d.mouseMode == 1) {
                // g.drawLine(x, y, x + 100, y + 100);
                d.handleTrait(x, y);
            } else if (d.mouseMode == 2) {
                // g.drawRect(x, y, 50, 50);
                d.handleRectangle(x, y);
            } else if (d.mouseMode == 3) {
                // g.drawOval(x + 100, y + 100, 50, 50);
                d.handleCercle(x, y);
            }

            System.out.println("Etape du tracé : " + d.step);

        }
    }
}
