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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Cas_8 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBox;
    private JTable table;
    static Connection con;

    // Méthode jFrame
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Cas_8 frame = new Cas_8();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public Cas_8() {

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
        setTitle("La Criee - Afficher la liste des lots pour vérifier la vente du jour");


        // Titre
        JLabel lblTitre = new JLabel("Liste des lots pour la vente du:");
        lblTitre.setBounds(176, 10, 240, 19);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitre);

        // Liste déroulante des lots pour la date de vente
        comboBox = new JComboBox<>(getVenteData());
        comboBox.setBounds(415, 7, 90, 25);
        contentPane.add(comboBox);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayLotData();
            }
        });


        // Tableau des données des lots
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(5, 47, 774, 303);
        contentPane.add(scrollPane);
        table = new JTable();
        scrollPane.setViewportView(table);
        displayLotData();


        // Bouton Retour
        JButton btnNewButton = new JButton("Retour");
        btnNewButton.setBounds(659, 0, 120, 25);
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(new Color(150, 50, 225));
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Interface.main(null);
            }
        });
    }


    // Méthode de récupération des dates d'enchères disponibles
    private String[] getVenteData() {
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT dateEnchere FROM lot");

            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateDuJour = today.format(formatter);

            // Construction de la liste des dates
            List<String> dates = new ArrayList<>();
            dates.add(dateDuJour);
            while (resultSet.next()) {
                String dateEnchere = resultSet.getString("dateEnchere");
                if (!dateEnchere.equals(dateDuJour)) {
                    dates.add(dateEnchere);
                }
            }

            // Conversion de la liste en tableau
            return dates.toArray(new String[0]);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des dates des enchères.", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }


    // Méthode d'obtention de la date de la liste déroulante
    private String getSelectedDate() {
        String selectedDate = (String) comboBox.getSelectedItem();
        return selectedDate;
    }


    // Méthode d'affichage des données des lots
    private void displayLotData() {
        try (Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT lot.idLot, bateau.nom AS nomBateau, lot.datePeche, espece.nom AS nomEspece, taille.specification, presentation.libelle AS libellePresentation, qualite.libelle AS libelleQualite, lot.poidsBrutLot " +
                        		"FROM lot " +
                        		"INNER JOIN bateau ON lot.idBateau = bateau.id " +
                                "INNER JOIN espece ON lot.idEspece = espece.id " +
                                "INNER JOIN taille ON lot.idTaille = taille.id " +
                                "INNER JOIN presentation ON lot.idPresentation = presentation.id " +
                                "INNER JOIN qualite ON lot.idQualite = qualite.id " +
                                "WHERE lot.dateEnchere = '" + getSelectedDate() + "'")) {

            // Création des colonnes
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("N° Lot");
            model.addColumn("Bateau");
            model.addColumn("Date de pêche");
            model.addColumn("Nom espèce");
            model.addColumn("Spécification");
            model.addColumn("Présentation");
            model.addColumn("Qualité");
            model.addColumn("Poids Brut Lot");

            // Remplissage avec les données
            while (resultSet.next()) {
                model.addRow(new Object[] { 
                		resultSet.getInt("idLot"), 
                		resultSet.getString("nomBateau"),
                        resultSet.getDate("datePeche"), 
                        resultSet.getString("nomEspece"),
                        resultSet.getString("specification"), 
                        resultSet.getString("libellePresentation"),
                        resultSet.getString("libelleQualite"), 
                        resultSet.getFloat("poidsBrutLot") });
            }

            table.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des données lots.", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}