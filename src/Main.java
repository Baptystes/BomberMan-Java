import org.newdawn.slick.*;

public class Main
{
    static private AppGameContainer app;

    public static void main( String[] args ) throws SlickException
    {
        int tileSize = 35;

        int tileBorder = 1;

        app = new AppGameContainer( new BBMan(tileSize, tileBorder) );
        app.setDisplayMode(21*(tileSize + tileBorder) , 17*(tileSize + tileBorder), false );
        app.setShowFPS(false);
        app.setTargetFrameRate(60);
        app.start();
    }
}