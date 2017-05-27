/**
 * Created by Baptiste on 24/05/2017.
 */
public class Animation {

    private int nombreImages, imageEnCours;
    private long tempsImage;

    Chronometre chronometre;

    public Animation (int nombreImages, long tempsImage)
    {
        this.nombreImages = nombreImages;
        this.tempsImage = tempsImage;
        imageEnCours = 0;

        chronometre = new Chronometre();
        chronometre.reDemarrer(tempsImage);
    }

    public void update()
    {
       if (chronometre.checkFinished() == 1)
       {
           imageEnCours = (imageEnCours+1)%nombreImages;
           chronometre.reDemarrer(tempsImage);
           System.out.println(imageEnCours + " " + nombreImages);
       }
    }

    public void reset()
    {
        imageEnCours = 0;
    }

    public int getImageEnCours (){ return imageEnCours;}
    public int getNombreImages (){ return nombreImages;}
}
