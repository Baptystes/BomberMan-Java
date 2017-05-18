import org.newdawn.slick.*;

import java.util.Date;



public class BBMan extends BasicGame
{
    private GameContainer gc;
    private Terrain terrain;
    private Personnage perso1, perso2;


    private int tileSize, tileBorder;

    public BBMan(int tileSize, int tileBorder){
        super( "BomberBat!" );
        this.tileSize = tileSize;

        this.tileBorder = tileBorder;
    }

    @Override
    public void init( GameContainer gc ) throws SlickException{
        this.gc = gc;

        terrain = new Terrain(tileSize, tileBorder);

        perso1 = new Personnage(terrain, 1, Color.red);
        perso1.spawn(1,15);
        perso2 = new Personnage(terrain, 2, Color.blue);
        perso2.spawn(19,1);

    }

    @Override
    public void render( GameContainer gc, Graphics g ) throws SlickException{
        terrain.dessinerMap(g);
        terrain.afficherBombes(g);
        perso1.afficher(g);
        perso2.afficher(g);






    }

    @Override
    public void update( GameContainer gc, int delta ) throws SlickException{
        perso1.deplacer(gc);
        perso2.deplacer(gc);
        //Date date = new Date(); date.get
        //System.out.println((date.getTimestamp());
        if (perso1.veutPoserBombe(gc) == 1)
        {
            terrain.poserBombe(perso1);
        }
        if (perso2.veutPoserBombe(gc) == 1)
        {
            terrain.poserBombe(perso2);
        }
        terrain.gestionBombes();
    }
}