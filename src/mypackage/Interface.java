package mypackage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Interface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// Méthode jFrame
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Fenêtre
	public Interface() {
		
        // Définition paramètres fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(800, 500, 800, 400);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
        setTitle("La Criee - Interface utilisateur");
		
        
		// Titre
		JLabel lblNewLabel = new JLabel("Sélectionnez une option parmi les suivantes:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(175, 11, 500, 50);
		contentPane.add(lblNewLabel);
		
		
		// Bouton
		JButton btnAjouter = new JButton("Créer un bac");
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setBackground(new Color(138, 43, 226));
		btnAjouter.setBounds(300, 80, 210, 50);
		contentPane.add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cas_4.main(null);
				dispose();
			}
		});
		
		
		// Bouton
		JButton btnDemenagement = new JButton("Afficher liste des lots par vente");
		btnDemenagement.setForeground(Color.WHITE);
		btnDemenagement.setBackground(new Color(138, 43, 226));
		btnDemenagement.setBounds(300, 150, 210, 50);
		contentPane.add(btnDemenagement);
		btnDemenagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cas_8.main(null);
				dispose();
			}
		});
		
		
		// Bouton
		JButton btnSupprimer = new JButton("Modifier le poids d'un bac");
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setBackground(new Color(138, 43, 226));
		btnSupprimer.setBounds(300, 220, 210, 50);
		contentPane.add(btnSupprimer);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cas_12.main(null);
				dispose();
			}
		});
		
		
		// Bouton
		JButton btnDeconnexion = new JButton("Déconnexion");
		btnDeconnexion.setForeground(Color.WHITE);
		btnDeconnexion.setBackground(new Color(255, 0, 0));
		btnDeconnexion.setBounds(300, 300, 210, 50);
		contentPane.add(btnDeconnexion);
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Authentification.main(null);
				dispose();
			}
		});
	}
}
