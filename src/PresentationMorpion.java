
import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PresentationMorpion extends JPanel {

	public PresentationMorpion(Morpion modele) {
		setLayout(new GridLayout(3, 3));

		Case caseMorpion;

		for (int y = 0; y < 3; y++) {

			for (int x = 0; x < 3; x++) {

				caseMorpion = modele.getCell(x, y);
				add(caseMorpion.display);

			}

		}

	}
	

}