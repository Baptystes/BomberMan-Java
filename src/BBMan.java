import org.newdawn.slick.*;

import java.util.Date;



public class BBMan extends BasicGame
{
    private GameContainer gc;
    private Terrain terrain;
    private Personnage perso1, perso2;
    private Affichage affichage;

    private int tileSize, tileBorder, colonneLatterale;

    int etatDuJeu;

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

        etatDuJeu = 0;
        Son.playSoundmain();

    }

    @Override
    public void render( GameContainer gc, Graphics g ) throws SlickException{

            terrain.dessinerMap(g);
            terrain.afficherBombes(g);
            terrain.afficherBonus(g);
            perso1.afficher(g);
            perso2.afficher(g);
            affichage.interfaceJeu(g, perso1, perso2);




    }

    @Override
    public void update( GameContainer gc, int delta ) throws SlickException{

            perso1.deplacer(gc);
            perso2.deplacer(gc);
            //Date date = new Date(); date.get
            //System.out.println((date.getTimestamp());
            if (perso1.veutPoserBombe(gc) == 1)
            {
                terrain.poserBombe(perso1, perso2);
            }
            if (perso2.veutPoserBombe(gc) == 1)
            {
                terrain.poserBombe(perso2, perso1);
            }
            terrain.gestionBombes();
            terrain.gestionBonus();

            if (gc.getInput().isKeyDown(Input.KEY_ESCAPE))
            {

            }


    }
}