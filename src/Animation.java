/**
 * Created by Baptiste on 24/05/2017.
 */
public class Animation {

    private int nombreImages, imageEnCours;
    private long tempsImage;
    boolean doubleSens;
    int sensLecture;
    Chronometre chronometre;

    public Animation (int nombreImages, long tempsImage, boolean doubleSens)
    {
        this.nombreImages = nombreImages;
        this.tempsImage = tempsImage;
        imageEnCours = 0;
        this.doubleSens = doubleSens;
        sensLecture = 1; // 0 gauche, 1 droite
        chronometre = new Chronometre();
        chronometre.reDemarrer(tempsImage);
    }

    public void update()
    {
       if (chronometre.checkFinished() == 1)
       {
           imageEnCours += (2*sensLecture-1);
           if (imageEnCours >= nombreImages)
           {
               if (doubleSens)
               {
                   sensLecture = 0;
                   imageEnCours = nombreImages-2;
               }
               else
                   imageEnCours = 0;

           }
           else if (imageEnCours<0)
           {
               imageEnCours = 1;
               sensLecture = 1;
           }

           chronometre.reDemarrer(tempsImage);
           //System.out.println(imageEnCours + " " + nombreImages);
       }
    }

    public void reset()
    {
        imageEnCours = 0;
    }

    public int getImageEnCours (){ return imageEnCours;}
    public int getNombreImages (){ return nombreImages;}
}
