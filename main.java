import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


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
    // 3 = tracé de cercle
    // 4 = usage du crayon

    public main() {
        addMouseListener(new Souris(this));
        addMouseMotionListener(new Souris(this));
        setSize(400, 600);
        setVisible(true);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(d.width / 2 - this.getWidth() / 2, d.height / 2 - this.getHeight() / 2);
    }

    // setteurs et getteurs

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMouseMode() {
        return this.mouseMode;
    }

    public void setMouseMode(int mouseMode) {
        this.mouseMode = mouseMode;
    }

    public int getStep() {
        return this.step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getFillMode() {
        return this.fillMode;
    }

    public void setFillMode(int fillMode) {
        this.fillMode = fillMode;
    }

    public double getX1() {
        return this.x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return this.y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return this.x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return this.y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public Color[] getColors() {
        return this.colors;
    }

    public void setColors(Color[] colors) {
        this.colors = colors;
    }

    public Color getFill_color() {
        return this.fill_color;
    }

    public void setFill_color(Color fill_color) {
        this.fill_color = fill_color;
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
        g.drawString("Crayon", 500, getHeight() - 20);

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


    // crayon
    public void handleCrayon(int x,int y) {
        System.out.println("crayonnn");
        Graphics g = getGraphics();
        if (g == null) return;
        g.setColor(fill_color);
        
        // initial point for the crayon stroke
        if (this.step == 0) {
            this.x = x;
            this.y = y;
            this.step = 1;
            g.fillOval(x - 1, y - 1, 3, 3); // petit point initial
        } else {
            g.drawLine(this.x, this.y, x, y);
            this.x = x;
            this.y = y;
        }
        g.dispose();
        // release pen
        

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

        public void mousePressedOrReleased(MouseEvent m) {

            int x = m.getX();
            int y = m.getY();
            System.out.println("Clic détecté");

            // d.affect(x, y);

            // si on clique sur le zone de tracé

            // si on est en mode tracé de trait
            if (d.mouseMode == 1) {
                // g.drawLine(x, y, x + 100, y + 100);
                d.handleTrait(x, y);
            } else if (d.mouseMode == 2) {
                // g.drawRect(x, y, 50, 50);
                d.handleRectangle(x, y);
            } else if (d.mouseMode == 3) {
                // g.drawOval(x + 100, y + 100, 50, 50);
                d.handleCercle(x, y);
            }

            // System.out.println("Etape du tracé : " + d.step);

        }

        // gestion des clicks simples sur les boutons de navigation
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
                System.out.println("Fill mode value:" + getFillMode());
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
            // Si on clique sur le bouton "Crayon"
            else if (x >= 500 && x <= 600 && y >= d.getHeight() - 50 && y <= d.getHeight()) {
                d.mouseMode = 4;
                d.step = 0;
                System.out.println("Clic sur le bouton CRAYON");
            }


        }

        // Gestion des clicks pour les dessins (pas les clicks sur les boutons)
        public void mousePressed(MouseEvent m) {
            System.out.println("Mouse clicked");
            mousePressedOrReleased(m);
        }

        public void mouseReleased(MouseEvent m) {
            System.out.println("Mouse released");
            mousePressedOrReleased(m);
            // relacher le stylo entre 2 tracés
            if (d.mouseMode == 4) {
                // lever le crayon : remettre l'etat a 0 pour demarrer un nouveau trait au prochain press
                d.step = 0;
                System.out.println("Stylo relache");
            }


        }


        public void mouseDragged(MouseEvent m) {
            // System.out.println("draggued");
            if (getMouseMode() == 4) {
                handleCrayon(m.getX(),m.getY());

            }
        }

    }
}
