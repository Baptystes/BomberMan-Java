import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * Created by Baptiste on 24/05/2017.
 */
public class Menu

{
    private int etatDuJeu  ;
    private int choix  ;
    private Image imageMenu;
    private Image imageBg;

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
        
        if ((posX > 260 && posX < 525) && (posY > 425 && posY < 500))
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
        if ((posX > 260 && posX < 525) && (posY > 325 && posY < 400))
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
        if ((posX > 260 && posX < 525) && (posY > 225 && posY < 300))
        {
            if (Mouse.isButtonDown(0))
            {
                setEtatDuJeu(2);
            }
        }

    }

    public void afficherImage(Graphics g)
    {
        imageBg.draw(0,0);

        imageMenu.draw(250, 150);
        

    }
}
