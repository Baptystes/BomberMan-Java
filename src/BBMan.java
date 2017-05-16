import org.newdawn.slick.*;

public class BBMan extends BasicGame
{
    private GameContainer gc;
    private Terrain terrain;
    private int tileWidth, tileHeight, tileBorder;

    public BBMan(int tileWidth, int tileHeight, int tileBorder){
        super( "BomberBat!" );
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tileBorder = tileBorder;
    }

    @Override
    public void init( GameContainer gc ) throws SlickException{
        this.gc = gc;

        terrain = new Terrain(tileWidth, tileHeight, tileBorder);
    }

    @Override
    public void render( GameContainer gc, Graphics g ) throws SlickException{
        terrain.dessinerMap(g);
    }

    @Override
    public void update( GameContainer gc, int delta ) throws SlickException{
    }
}