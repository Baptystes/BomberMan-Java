import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;

import java.util.Date;



public class BBMan extends BasicGame {
    private GameContainer gc;
    private Terrain terrain;
    private Personnage perso1, perso2;
    private Interface interfaceBM;

    private int tileSize, tileBorder;

    int etatDuJeu;

    Image menu;

    Image bomberman;

    public BBMan(int tileSize, int tileBorder) {
        super("BomberBat!");
        this.tileSize = tileSize;
        this.tileBorder = tileBorder;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        this.gc = gc;
        interfaceBM = new Interface();
        terrain = new Terrain(interfaceBM, tileSize, tileBorder);

        perso1 = new Personnage(interfaceBM, terrain, 1, Color.red);
        perso1.spawn(1, 15);
        perso2 = new Personnage(interfaceBM, terrain, 2, Color.blue);
        perso2.spawn(19, 1);

        /*try {
            bomberman = new Image("images/menu/bomberman.png");

        } catch (SlickException e) {
            e.printStackTrace();
        }*/



        try {
            menu = new Image("images/menu/menu.png");

        } catch (SlickException e) {
            e.printStackTrace();
        }


        // par défaut menu

        etatDuJeu = 0;


    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        // Menu
        if (etatDuJeu == 0) {
            g.drawString("Welcome to Bomberman", 100, 50);
            //bomberman.draw(0 , 0);
            menu.draw(250, 100);

            //menu.afficher();
        }
        // partie en cours
        else if (etatDuJeu == 1) {
            terrain.dessinerMap(g);
            terrain.afficherBombes(g);
            terrain.afficherBonus(g);
            perso1.afficher(g);
            perso2.afficher(g);
        }


    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        if ((posX > 260 && posX < 525) && (posY > 325 && posY < 400)) // pour y c'est en tatonnant en gros c'est pos de de play + 30 + hauteur de exit
        {
            if (Mouse.isButtonDown(0)) {
                //etatDuJeu = 1;
                System.exit(0);
            }
        }
        if ((posX > 260 && posX < 525) && (posY > 425 && posY < 500)) // pour x c'est la différence de largeur de l'image. Pour y c'est la postion de de part d'exit -80 + la hauteur de l'image
        {
            if ((Mouse.isButtonDown(0))) {
                //System.exit(0);
                etatDuJeu = 1;
            }
        }

       /* if (etatDuJeu == 0)
        {
            // avec objet menu
           /* menu.controler();
            if (menu.choixSelectionne == 1)
            {
                etatDuJeu = 1; // Pour Jouer
            }
            else if (menu.choixSelectionne == 2)
            {
                etatDuJeu = 2; // parametre
            }*/

        if(etatDuJeu ==1)

        {
            perso1.deplacer(gc);
            perso2.deplacer(gc);
            //Date date = new Date(); date.get
            //System.out.println((date.getTimestamp());
            if (perso1.veutPoserBombe(gc) == 1) {
                terrain.poserBombe(perso1, perso2);
            }
            if (perso2.veutPoserBombe(gc) == 1) {
                terrain.poserBombe(perso2, perso1);
            }
            terrain.gestionBombes();
            terrain.gestionBonus();

            /*if (terrain.partieTerminee() == 1) {
                etatDuJeu = 0;*/
            }
        }
    }



