
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import javax.swing.JOptionPane;

/**
 * Created by Baptiste on 24/05/2017.
 */
public class Menu

{
    private int etatDuJeu  ;
    private int choix  ;
    private Image imageMenu;
    private Image imageBg;
    private Image imageWon;

    public Menu ( )

    {
        etatDuJeu=0;


        try {
            imageMenu = new Image("images/menu/menu.png");

        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            imageBg = new Image("images/menu/bomberman.png");

        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            imageWon = new Image("images/menu/winBomberman.jpg");

        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public int getEtatDuJeu() {
        return etatDuJeu;
    }

    public void setEtatDuJeu(int etatDuJeu) {
        this.etatDuJeu = etatDuJeu;
    }

    public int getChoix()
    {
        return choix;
    }

    public void setChoix(int choix) {
        this.choix = choix;
    }
    public void choisir (GameContainer gc)
    {
        if (Mouse.isButtonDown(0) || gc.getInput().isKeyDown(Input.KEY_ENTER))
        {
            setChoix(1);
        }
    }
    public void play( )
    {
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        if ((posX > 260 && posX < 525) && (posY > 375 && posY < 455))
        {
            if (Mouse.isButtonDown(0))
            {
                setEtatDuJeu(1);

            }
        }


    }
    public void exit()
    {
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        if ((posX > 260 && posX < 525) && (posY > 275 && posY < 355))
        {
            if (Mouse.isButtonDown(0))
            {

                System.exit(0);
            }
        }

    }
    public void option()
    {

        int posX = Mouse.getX();
        int posY = Mouse.getY();
        if ((posX > 260 && posX < 525) && (posY > 165 && posY < 250))
        {
            if (Mouse.isButtonDown(0))
            {
                setEtatDuJeu(2);
            }
        }

    }
    public void escape(GameContainer gc)
    {
        if (gc.getInput().isKeyDown(Input.KEY_ESCAPE))
        {
            setEtatDuJeu(0);
        }
    }
    public void end(Personnage perso1, Personnage perso2)
    {
        if (perso1.getNbVies() ==0 || perso2.getNbVies() ==0)
        {

            setEtatDuJeu(3);
        }
    }

    public void afficherImage()
    {

        imageBg.draw(0,0);

        imageMenu.draw(250, 150);
        

    }
    public void iWon()
    {


        imageWon.draw(0,50);
    }


}


