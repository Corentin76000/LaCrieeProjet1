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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Cas_4 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBox;
    private JComboBox<String> comboBoxPoidsBac;
    private JTextField textFieldNomBac;
    private Map<String, String> typeBacMap;
    static Connection con;

	// Méthode jFrame
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	Cas_4 frame = new Cas_4();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public Cas_4() {
    	
        // Connexion à la base de données
        con = Connexion.getConnection();

    	
        // Définition paramètres fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(800, 500, 800, 400);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("La Criee - Créer les bacs de poissons et les données associées pour un lot");


        // Titre
        JLabel lblTitre1 = new JLabel("Créer le bac pour le lot suivant:");
        lblTitre1.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitre1.setBounds(35, 15, 284, 32);
        contentPane.add(lblTitre1);
 
        // Liste déroulante pour sélectionner le lot
        comboBox = new JComboBox<>(getLotListData());
        comboBox.setBounds(316, 18, 180, 25);
        contentPane.add(comboBox);        


        // Titre
        JLabel lblTitre2 = new JLabel("Poids du bac (tare):");
        lblTitre2.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitre2.setBounds(140, 72, 170, 32);
        contentPane.add(lblTitre2);
        
        // Liste déroulante pour sélectionner le type de bac
        comboBoxPoidsBac = new JComboBox<>(getTypeBacData());
        comboBoxPoidsBac.setBounds(316, 78, 180, 25);
        contentPane.add(comboBoxPoidsBac);


        // Titre
        JLabel lblTitre3 = new JLabel("Nom du bac:");
        lblTitre3.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitre3.setBounds(199, 108, 111, 32);
        contentPane.add(lblTitre3);

        // Zone de texte pour saisir le nom du bac
        textFieldNomBac = new JTextField();
        textFieldNomBac.setBounds(316, 114, 180, 25);
        contentPane.add(textFieldNomBac);
        textFieldNomBac.setColumns(10);


        // Bouton Créer
        JButton btnNewButton2 = new JButton("Créer");
        btnNewButton2.setBounds(659, 325, 120, 25);
        btnNewButton2.setForeground(Color.WHITE);
        btnNewButton2.setBackground(new Color(150, 50, 225));
        contentPane.add(btnNewButton2);
        btnNewButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer les données sélectionnées dans la liste déroulante des lots
                String selectedLot = (String) comboBox.getSelectedItem();

                // Récupérer les données sélectionnées dans la liste déroulante du poids du bac
                String selectedTypeBac = (String) comboBoxPoidsBac.getSelectedItem();
                String selectedBacId = typeBacMap.get(selectedTypeBac); // Récupérer l'id correspondant au tare

                // Récupérer le nom du bac saisi dans la zone de texte
                String bacName = textFieldNomBac.getText();

                // Vérifier si toutes les données nécessaires sont fournies
                if (selectedLot != null && !selectedLot.equals("Choisir un lot") &&
                		selectedTypeBac != null && !selectedTypeBac .equals("Choisir le poids du bac") && !bacName.isEmpty()) {
                    int option = JOptionPane.showConfirmDialog(null, "Êtes vous sûr de la création ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        try {
                            // Récupérer les données du lot sélectionné
                            Statement statement = con.createStatement();
                            ResultSet resultSet = statement.executeQuery("SELECT idBateau, datePeche FROM lot WHERE idLot = '" + selectedLot + "'");
                            if (resultSet.next()) {
                                String idBateauValue = resultSet.getString("idBateau");
                                String datePecheValue = resultSet.getString("datePeche");

                                statement.executeUpdate("INSERT INTO bac (idBateau, datePeche, idLot, idBac, idTypeBac) VALUES ('" + idBateauValue + "', '" + datePecheValue + "', '" + selectedLot + "', '" + bacName + "', " + selectedBacId + ")");

                                JOptionPane.showMessageDialog(null, "Création enregistrée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Erreur lors de la création du bac.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        // Bouton Retour
        JButton btnNewButton1 = new JButton("Retour");
        btnNewButton1.setBounds(659, 0, 120, 25);
        btnNewButton1.setForeground(Color.WHITE);
        btnNewButton1.setBackground(new Color(150, 50, 225));
        contentPane.add(btnNewButton1);
        btnNewButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Interface.main(null);
            }
        });
    }

    
    // Méthode pour obtenir les lots dans la liste déroulante
    private String[] getLotListData() {
        List<String> dataList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT idLot FROM lot")) {

            dataList.add("Choisir un lot");

            while (resultSet.next()) {
                dataList.add(resultSet.getString("idLot"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new String[]{"Erreur lors de la récupération des données lots."};
        }

        return dataList.toArray(new String[0]);
    }

    
    
    // Méthode pour obtenir typebac dans la liste déroulante
    private String[] getTypeBacData() {
        List<String> dataList = new ArrayList<>();
        typeBacMap = new HashMap<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id, tare FROM typebac")) {

            dataList.add("Choisir le poids du bac");

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String tare = resultSet.getString("tare");
                dataList.add(tare);
                typeBacMap.put(tare, id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new String[]{"Erreur lors de la récupération des données typebac."};
        }

        return dataList.toArray(new String[0]);
    }
}