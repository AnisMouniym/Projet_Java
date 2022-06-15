public class Case {
    
    public Joueur player;                                       //joueur ayant joue dans la case (null si aucun joueur n'a encore joue dans la case)
    public PresentationCase display;

    public Case(Morpion morpion) {                              //constructeur de la case
        player = null;
        display = new PresentationCase(morpion, this);

    }

    public boolean isFree() {                                    //retourne true si la case est libre
        return player == null;
    }

    public void playPermanantly(Joueur player) {                //joue définitivement sur une case
        this.player = player;
        display.update();
    }
    public void playTemporary(Joueur player) {                  //joue temporairement sur une case
        this.player = player;
    }

    public void cancel() {                                      //annule le dernier coup
        player = null;
    }
}