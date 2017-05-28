
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import javax.swing.JOptionPane;

/**
 * Created by Baptiste on 24/05/2017.
 */
public class Menu

{
    private GameContainer gc;
    private int choixEnCours;
    private Image imageBoutons;
    private Image imageBg;
    private Image imageWon;

    public Menu (GameContainer gc)

    {
        choixEnCours = 0;
        this.gc = gc;

        try {
            imageBoutons = new Image("images/menu/boutons.png");

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

    


    public void setChoix(int choix) {
        this.choixEnCours = choix;
    }


    public int gestion()
    {

        int posX = gc.getInput().getMouseX();
        int posY = gc.getInput().getMouseY();

        boolean sourisDansCadre = false;
        for (int a=0 ; a<3 ; a++)
        {
            //System.out.println(posY);
            //imageBoutons.getSubImage(0, a*84, 259, 84).draw(gc.getWidth()/2-259/2, gc.getHeight()/5*a + 100);
            if (posX>gc.getWidth()/2-259/2 && posX < (gc.getWidth()/2-259/2)+259 && posY>(gc.getHeight()/5*a + 100) && posY < (gc.getHeight()/5*a + 100+84))
            {
                choixEnCours = a;
                sourisDansCadre = true;
            }
        }

        if (gc.getInput().isKeyPressed(Input.KEY_DOWN))
        {
            choixEnCours = (choixEnCours+1)%3;
        }
        else if (gc.getInput().isKeyPressed(Input.KEY_UP))
        {
            choixEnCours--;
            if (choixEnCours<0)
                choixEnCours=2;
        }

        if ((gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && sourisDansCadre) || gc.getInput().isKeyPressed(Input.KEY_ENTER))
            return choixEnCours+1;
        else
            return 0;



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
                //setEtatDuJeu(2);
            }
        }

    }


    public void afficher()
    {

        imageBg.draw(0,0);
        for (int a=0 ; a<3 ; a++)
        {
            imageBoutons.getSubImage(0, a*84, 259, 84).draw(gc.getWidth()/2-259/2, gc.getHeight()/5*a + 100);
        }

        for (int a=0 ; a<2 ; a++)
            imageBoutons.getSubImage(259, 0, 69, 11).draw(gc.getWidth()/2-259/2 - 90 + 360*a, gc.getHeight()/5*choixEnCours +84/2 - 11/2 + 100);


        

    }
    public void iWon()
    {
        imageWon.draw(0,50);
    }


}


