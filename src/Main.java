import org.newdawn.slick.*;

public class Main
{
    static private AppGameContainer app;

    public static void main( String[] args ) throws SlickException
    {
        app = new AppGameContainer( new BBMan() );
        app.setDisplayMode( 640, 480, false );
        app.setShowFPS(false);
        app.setTargetFrameRate(60);
        app.start();
    }
}