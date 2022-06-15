import javax.swing.JFrame;
public class ApplicationMorpion {
	public static void main(String[] args) {
		/*
		 * on cree un morpion (true si on veut que l'ordinateur commence, false
		 * pour que l'humain commence)
		 */

		Morpion morpion = new Morpion(false);
		
		/*
		 * on cree une fenetre intitulee "Morpion"
		 */

		JFrame screen = new JFrame("Morpion");

		/*
		 * on definit sa largeur et sa hauteur en pixels
		 */

		screen.setSize(380, 400);

		/*
		 * cette methode permet de centrer la fenetre a l'ecran
		 */

		screen.setLocationRelativeTo(null);

		/*
		 * cette methode permet de quitter l'application quand l'utilisateur
		 * ferme la screen
		 */

		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * on definit la presentation du morpion en tant que contenu de la
		 * fenetre
		 */
			screen.setContentPane(morpion.display);
		/*
		 * on rend la fenetre visible a l'ecran, l'utilisateur peut maintenant
		 * jouer
		 */
			screen.setVisible(true);
		}

		
	
}
	