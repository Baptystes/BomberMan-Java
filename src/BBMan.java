import org.newdawn.slick.*;

public class BBMan extends BasicGame
{
    private GameContainer gc;

    public BBMan(){
        super( "GameWindow" );
    }

    @Override
    public void init( GameContainer gc ) throws SlickException{
        this.gc = gc;
    }

    @Override
    public void render( GameContainer gc, Graphics g ) throws SlickException{
    }

    @Override
    public void update( GameContainer gc, int delta ) throws SlickException{
    }
}