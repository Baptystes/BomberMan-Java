import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;

import java.util.Date;



public class BBMan extends BasicGame {
    private GameContainer gc;
    private Terrain terrain;
    private Personnage perso1, perso2;

    private Affichage affichage;

    private int tileSize, tileBorder, colonneLatterale, etatDuJeu;


    private Menu menu;
    private Option option;




    public BBMan(int tileSize, int tileBorder, int colonneLatterale){
        super( "Bomberman" );
        this.tileSize = tileSize;
        this.tileBorder = tileBorder;
        this.colonneLatterale = colonneLatterale;
    }

    @Override

    public void init( GameContainer gc) throws SlickException{
        this.gc = gc;
        affichage = new Affichage(tileSize, tileBorder, colonneLatterale);
        terrain = new Terrain(affichage);

        perso1 = new Personnage(affichage, terrain, 1);
        perso1.spawn(1,15);
        perso2 = new Personnage(affichage, terrain, 2);
        perso2.spawn(19,1);

        Son.playSoundmain();

        menu = new Menu();
        option =new Option();

    }

    @Override

    public void render(GameContainer gc, Graphics g) throws SlickException {
        // Menu
        if (menu.getEtatDuJeu() == 0)
        {
            menu.afficherImage();
        }
        // partie en cours
        else if (menu.getEtatDuJeu() == 1)
        {
            terrain.dessinerMap(g);
            terrain.afficherBombes(g);
            terrain.afficherBonus(g);
            perso1.afficher(g);
            perso2.afficher(g);

            affichage.interfaceJeu(g, perso1, perso2);
        }
        else if (menu.getEtatDuJeu() == 2)
        {
            option.saisieVie();
        }
        else if (menu.getEtatDuJeu()==3)
        {
            perso1.finMourrir(g);
            perso2.finMourrir(g);
            menu.iWon();
        }


    }

    @Override

    public void update(GameContainer gc, int delta) throws SlickException {
        menu.play();
        menu.exit();
        menu.option();
        menu.escape(gc);
        menu.end(perso1, perso2); // passer à l'écran de fin de partie


        if (menu.getEtatDuJeu() == 1)

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

        }


    }
}




