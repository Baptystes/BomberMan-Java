import org.newdawn.slick.*;

public class Main
{
    static private AppGameContainer app;

    public static void main( String[] args ) throws SlickException
    {
        int tileSize = 35;
        int tileBorder = 1;
        int colonneLateralle = 200;

        app = new AppGameContainer( new BBMan(tileSize, tileBorder, colonneLateralle));
        app.setDisplayMode(21*(tileSize + tileBorder) + 2*colonneLateralle, 17*(tileSize + tileBorder), false );
        app.setShowFPS(false);
        app.setTargetFrameRate(60);
        app.start();
    }
}