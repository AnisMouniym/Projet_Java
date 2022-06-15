import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PresentationCase extends JPanel {

    private Morpion morpion;   
    private Case modele;    
    private JButton bouton;
    private JLabel label;

    public PresentationCase(Morpion morpion, Case modele) {

        this.morpion = morpion;
        this.modele = modele;
        bouton = new JButton();
        label = new JLabel();
        setLayout(new BorderLayout());
        add(bouton, BorderLayout.CENTER);


        bouton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evenement) {
                play();
            }

        });

    }

    public void play() {
        morpion.humanTurn(modele);
    }
    
   

    public void update() {
        Joueur player = modele.player;
        remove(bouton);
        label.setText(player.icon);
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(player.couleur);
        add(label, BorderLayout.CENTER);
        revalidate();
        repaint();

    }
    

}