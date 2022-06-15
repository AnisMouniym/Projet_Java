import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Morpion {
    private static Random RANDOM = new Random();                        //random pour les IA
    private Case[][] grille;                                            //grille de morpion
    private Joueur human;                                               //joueur humain
    private Joueur IA;                                                  //ordinateur
    private int playedCellCount;                                        //nombre de cases jouées
    public PresentationMorpion display;
    public PresentationCase newCase;
	public JFrame screen;


    public void Option() {
    	Object[] option = {'X', 'O'};
    	int x = JOptionPane.showOptionDialog(null, "Choisissez votre Symbole", "", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE,null, option, option[0]);
    	if (x==0) {
    		human = new Joueur("X",Color.blue);
            IA = new Joueur("O",Color.red);
    	} else if (x==1){
    		IA = new Joueur("X", Color.blue);
            human = new Joueur("O", Color.red);
    	} else System.exit(0);
    }
    
    public void replay( ) {
		Object[] replay= {"Oui","Non"};
		int x = JOptionPane.showOptionDialog(null, "Rejouer ?", "", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, replay, replay[0]);
		if (x==0) {
	    	JOptionPane.showMessageDialog(null,"Je rigole ça marche pas haha","La tu rejoue tkt",JOptionPane.INFORMATION_MESSAGE,null);

		} else if (x==1){
			System.exit(0);
		} else {
			System.exit(0);
		}
}
    
    public void endGame() {
    	
    	JOptionPane.showMessageDialog(null,end(this.human),"GAME OVER",JOptionPane.INFORMATION_MESSAGE,null);
    	
    	
    }
    
    
    public Morpion(boolean iaStart) {           	 //constructeur du morpion
        grille = new Case[3][3];

        for (int x = 0; x < 3; x++) {                                   

            for (int y = 0; y < 3; y++) {

                grille[x][y] = new Case(this);
            }
        }
    	Option();
        playedCellCount = 0;
        display = new PresentationMorpion(this);

        if (iaStart) {
            iaPlay();
        }
    }

    public Case getCell(int x, int y) {
        return grille[x][y];
    }

    public void humanTurn(Case playedCell) {  
        if (!gameOver()) {
            playedCell.playPermanantly(human);
            playedCellCount++;
            if (!gameOver()) {
                iaPlay();
            } 
        } 
    }

    public boolean gameOver() {                                    //retourne true si la partie est finie
    	return getWinner() != null || playedCellCount == 9; 
       
    }
    
    

    private void iaPlay() {                                         //L'IA joue une case aléatoire
        Case betterCell = getBetterCell();
        betterCell.playPermanantly(IA);
        playedCellCount++;
        if (gameOver()) {
            endGame();
            replay();
        }

    }
    
    private int score(Joueur player) {                              //retourne le score d'un joueur
        Joueur winner = getWinner();
        int score;
        if (winner == player) {
            score = 1;
        } else if (winner == null) {
            score = 0;
        } else {
            score = -1;
        }
        return score;
    }
    
    private String end(Joueur player) {                              //retourne le score d'un joueur
        Joueur winner = getWinner();
        String result;
        if (winner == player) {										//Même si il n'est pas possible de gagner 
            result = "TU AS GAGNE ! \n BIEN JOUER BG";
        } else if (winner == null) {
            result = "MATCH NUL ! ON EN REFAIT UNE ?";
        } else {
            result = "LOOSER..." + "\n VA TE PENDRE MEC";
        }
        return result;
    }
    
    private Joueur getOpps(Joueur player) {                //retourne l'adversaire d'un joueur
        Joueur opponent;
        if (player == IA) {
            opponent = human;
        } else {
            opponent = IA;
        }
        return opponent;

    }
    
    private Case getBetterCell() {                                  //retourne la meilleure case pour le joueur
        Case checkedCell;
        List<Case> bestCell = new LinkedList<Case>();
        int evaluationbestCell = Integer.MIN_VALUE;
        int evaluationCaseTestee;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                checkedCell = grille[x][y];
                if (checkedCell.isFree()) {
                    checkedCell.playTemporary(IA);
                    playedCellCount++;
                    evaluationCaseTestee = locationValue(IA);
                    if (evaluationCaseTestee > evaluationbestCell) {
                        bestCell.clear();
                        bestCell.add(checkedCell);
                        evaluationbestCell = evaluationCaseTestee;
                    } else if (evaluationCaseTestee == evaluationbestCell) {
                        bestCell.add(checkedCell);
                    }
                    checkedCell.cancel();
                    playedCellCount--;
                }
            }
        }
        Case betterCell = bestCell.get(RANDOM.nextInt(bestCell
                .size()));

        return betterCell;
    }
    
    
    private int locationValue(Joueur player) {                                    
        int locationValue;
        if (gameOver()) {
            locationValue = score(player);
        } else {
            Joueur opponent = getOpps(player);
            Case checkedCell;
            int locationValueCell;
            int locationValueOpps = Integer.MIN_VALUE;
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    checkedCell = grille[x][y];
                    if (checkedCell.isFree()) {
                        checkedCell.playTemporary(opponent);
                        playedCellCount++;
                        locationValueCell = locationValue(opponent);
                        checkedCell.cancel();
                        playedCellCount--;
                        if (locationValueCell > locationValueOpps) {
                            locationValueOpps = locationValueCell;
                        }
                    }
                }
            }
            locationValue = -locationValueOpps;
        }
        return locationValue;
    }
    
    private Joueur getWinner() {                                    //retourne le vainqueur
        Joueur player;
        for (int x = 0; x < 3; x++) { 
            player = grille[x][0].player;
            if (player != null && grille[x][1].player == player
                    && grille[x][2].player == player) {    
            	return player;
            }
        } 
        for (int y = 0; y < 3; y++) {
            player = grille[0][y].player;
            if (player != null && grille[1][y].player == player
                    && grille[2][y].player == player) {
            	return player;
            }
        }
        player = grille[0][0].player;
        if (player != null && grille[1][1].player == player
                && grille[2][2].player == player) {
        	return player;
        }
        player = grille[0][2].player;
        if (player != null && grille[1][1].player == player
                && grille[2][0].player == player) {
        	return player;
        }
        return null;
    }
}