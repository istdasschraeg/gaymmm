import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Bohrer extends Actor
{
    private Display anzeige;
    private int Eisen;
    private int X;

    public void activate()
    {
        if(gesteinVorhanden()){
            X = ((Gestein)getOneIntersectingObject(Gestein.class)).getWassergehalt();
            Greenfoot.delay(10);
            nachricht("Gestein untersucht!  Du erhälst " + X +"Eisen");
            Eisen += X;
            removeTouching(Gestein.class);
            nachricht("Du hast "  + Eisen + " Eisen");
        }
    }

    public void ShowEisen()
    {
        nachricht("Du hast " + Eisen + " Eisen");
    }

    /**
     * Der Bohrer gibt durch einen Wahrheitswert (true oder false )zurück, ob sich auf seiner Position ein Objekt der Klasse Gestein befindet.
     * Eine entsprechende Meldung erscheint auch auf dem Display.
     */
    private boolean gesteinVorhanden()
    {
        if(getOneIntersectingObject(Gestein.class)!=null)
        {
            nachricht("Gestein gefunden!");
            return true;

        }

        return false;
    }


    /**
     * Der Bohrer ermittelt den Wassergehalt des Gesteins auf seiner Position und gibt diesen auf dem Display aus.
     * Sollte kein Objekt der Klasse Gestein vorhanden sein, dann erscheint eine entsprechende Meldung auf dem Display.
     */
    private void analysiereGestein()
    {
        if(gesteinVorhanden())
        {
            nachricht("Gestein untersucht! Wassergehalt ist " + ((Gestein)getOneIntersectingObject(Gestein.class)).getWassergehalt()+"%.");
            Greenfoot.delay(1);
            removeTouching(Gestein.class);
        }
        else 
        {
            nachricht("Hier ist kein Gestein");
        }
    }

    private void nachricht(String pText)
    {
        if(anzeige!=null)
        {
            anzeige.anzeigen(pText);
            Greenfoot.delay(1);
            anzeige.loeschen();
        }
    }

    private void displayAusschalten()
    {
        getWorld().removeObject(anzeige);

    }

    protected void addedToWorld(World world)
    {

        setImage("images/rover.png");
        world = getWorld();
        anzeige = new Display();
        world.addObject(anzeige, 7, 0);
        if(getY()==0)
        {
            setLocation(getX(),1);
        }
        anzeige.anzeigen("Ich bin bereit");

    }

    class Display extends Actor
    {
        GreenfootImage bild; 

        public Display()
        {
            bild = getImage();
        }

        public void act() 
        {

        }  

        public void anzeigen(String pText)
        {
            loeschen();
            bild = new GreenfootImage(pText, 30, null, null); 
            getImage().drawImage(bild, 8,0);

        }

        public void loeschen()
        {
            getImage().clear();
            setImage("images/nachricht.png");
        }

    }
}