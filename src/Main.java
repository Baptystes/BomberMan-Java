import org.newdawn.slick.*;

public class Main
{
    static private AppGameContainer app;

    public static void main( String[] args ) throws SlickException
    {
        int tileWidth = 35;
        int tileHeight = tileWidth;
        int tileBorder = 1;

        app = new AppGameContainer( new BBMan(tileWidth, tileHeight, tileBorder) );
        app.setDisplayMode(21*(tileWidth + tileBorder) , 17*(tileHeight + tileBorder), false );
        app.setShowFPS(false);
        app.setTargetFrameRate(60);
        app.start();
    }
}