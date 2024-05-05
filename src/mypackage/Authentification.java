package mypackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Authentification extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField identifiant;
    private JTextField mdp;
    static Connection con;

	// Méthode jFrame
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Authentification frame = new Authentification();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Authentification() {
    	
        // Connexion à la base de données
        con = Connexion.getConnection();

    	
        // Définition paramètres fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(800, 500, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("Connexion à l'interface utilisateur");

        
        // Titre
        JLabel lblNewLabel_1 = new JLabel("Veuillez vous authentifier:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1.setBounds(105, 30, 237, 22);
        contentPane.add(lblNewLabel_1);

        
        // Titre
        JLabel lblNewLabel = new JLabel("Identifiant:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(80, 93, 100, 23);
        contentPane.add(lblNewLabel);

        // Champs à remplir
        identifiant = new JTextField();
        identifiant.setBounds(173, 93, 199, 20);
        contentPane.add(identifiant);
        identifiant.setColumns(10);


        // Titre
        JLabel lblMotDePasse = new JLabel("Mot de passe:");
        lblMotDePasse.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMotDePasse.setBounds(63, 136, 90, 15);
        contentPane.add(lblMotDePasse);

        // Champs à remplir
        mdp = new JPasswordField();
        mdp.setBounds(173, 134, 199, 20);
        contentPane.add(mdp);
        mdp.setColumns(10);

        
        // Boutton connexion
        JButton btnNewButton = new JButton("Connexion");
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(new Color(138, 43, 226));
        btnNewButton.setBounds(173, 172, 114, 23);
        contentPane.add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String NomUtilisateur = identifiant.getText();
                String Mdp = mdp.getText();
                try {
                    Statement statement = con.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT nomUtilisateur, motdepasse FROM utilisateurs WHERE nomUtilisateur='" + NomUtilisateur + "' AND motdepasse='" + Mdp + "'");
                    if (resultSet.next()) {
                        Interface.main(null);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Identifiants incorrects. Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        identifiant.setText("");
                        mdp.setText("");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        // Boutton quitter
        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.setForeground(Color.WHITE);
        btnQuitter.setBackground(new Color(255, 0, 0));
        btnQuitter.setBounds(294, 172, 76, 23);
        contentPane.add(btnQuitter);
        btnQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

}