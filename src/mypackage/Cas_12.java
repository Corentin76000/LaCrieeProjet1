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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;


public class Cas_12 extends JFrame {

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
                	Cas_12 frame = new Cas_12();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public Cas_12() {
    	
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
        setTitle("La Criee - Modifier le poids des bacs pour un lot donné du jour");

        
        // Titre
        JLabel lblTitre = new JLabel("Liste des lots pour la date du:");
        lblTitre.setBounds(176, 10, 240, 19);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitre);

        // Liste déroulante des lots pour date de pêche
        comboBox = new JComboBox<>(getDateData());
        comboBox.setBounds(415, 7, 90, 25);
        contentPane.add(comboBox);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayLotData();
            }
        });

        
        // Tableau des données des bacs
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(5, 47, 774, 267);
        contentPane.add(scrollPane);
        table = new JTable();
        scrollPane.setViewportView(table);
        displayLotData();        
        
        
        // Bouton Modifier
        JButton btnNewButton2 = new JButton("Modifier");
        btnNewButton2.setBounds(659, 325, 120, 25);
        btnNewButton2.setForeground(Color.WHITE);
        btnNewButton2.setBackground(new Color(150, 50, 225));
        contentPane.add(btnNewButton2);
        btnNewButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer la ligne sélectionnée
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(Cas_12.this, "Veuillez sélectionner le bac à modifier.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Récupérer l'ID du lot sélectionné
                int idLot = (int) table.getValueAt(selectedRow, 0); // Supposant que la première colonne contient l'ID

                // Afficher une boîte de dialogue avec une JComboBox pour sélectionner le nouveau tare
                JComboBox<String> tareComboBox = new JComboBox<>(getTypeBacTare());
                tareComboBox.setSelectedItem(table.getValueAt(selectedRow, 1)); // Supposant que la deuxième colonne contient le tare
                int option = JOptionPane.showConfirmDialog(Cas_12.this, tareComboBox, "Sélectionnez le nouveau poids:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String newTare = (String) tareComboBox.getSelectedItem();
                    // écrire le code pour récupérer l'id correspondant au tare de la table typebac

                    try {
                        // Requête pour récupérer l'ID du tare sélectionné
                        Statement statement = con.createStatement();
                        ResultSet idResultSet = statement.executeQuery("SELECT id FROM typebac WHERE tare = '" + newTare + "'");
                        
                        // Vérification s'il y a un résultat (devrait y avoir au plus un résultat car le tare est censé être unique)
                        if (idResultSet.next()) {
                            int newTypeBacId = idResultSet.getInt("id");

                            try {
                                // Mettre à jour le tare dans la base de données
                            	Statement statementTare = con.createStatement();
                            	int rowsAffected = statementTare.executeUpdate("UPDATE bac SET idTypeBac = " + newTypeBacId + " WHERE id = " + idLot);
                            	
                                if (rowsAffected > 0) {
                                    JOptionPane.showMessageDialog(Cas_12.this, "Modification enregistrée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                                    displayLotData(); // Actualiser tableau
                                } else {
                                    JOptionPane.showMessageDialog(Cas_12.this, "Erreur lors de la modification du tare.", "Erreur", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(Cas_12.this, "Erreur lors de la modification du tare.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(Cas_12.this, "Impossible de trouver l'ID correspondant au tare sélectionné.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(Cas_12.this, "Erreur lors de la modification du tare.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        
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

    
    // Méthode de récupération des dates de pêches disponibles
    private String[] getDateData() {
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT datePeche FROM bac");

            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateDuJour = today.format(formatter);

            // Construction de la liste des dates
            List<String> dates = new ArrayList<>();
            dates.add(dateDuJour);
            while (resultSet.next()) {
                String dateEnchere = resultSet.getString("datePeche");
                if (!dateEnchere.equals(dateDuJour)) {
                    dates.add(dateEnchere);
                }
            }

            // Conversion de la liste en tableau
            return dates.toArray(new String[0]);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des données.", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    

    // Méthode d'affichage des données des lots
    private void displayLotData() {
        try (Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT bac.id, typebac.tare " +
                        "FROM bac " +
                        "INNER JOIN typebac ON bac.idTypeBac = typebac.id " +
                        "WHERE bac.datePeche = '" + getSelectedDate() + "'")) {

            // Création des colonnes
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("N° Bac");
            model.addColumn("Type de bac");

            // Remplissage avec les données
            while (resultSet.next()) {
                model.addRow(new Object[] { 
                        resultSet.getInt("id"), 
                        resultSet.getString("tare")
                });
            }

            table.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des données.", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    
    // Méthode d'obtention de la date de pêche de la liste déroulante
    private String getSelectedDate() {
        String selectedDate = (String) comboBox.getSelectedItem();
        return selectedDate;
    }
    
    
    // Méthode pour obtenir les valeurs typebac dans la liste déroulante
    private String[] getTypeBacTare() {
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT tare FROM typebac");

            List<String> tares = new ArrayList<>();
            while (resultSet.next()) {
                tares.add(resultSet.getString("tare"));
            }

            return tares.toArray(new String[0]);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des données de tare.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return new String[0];
    }
}