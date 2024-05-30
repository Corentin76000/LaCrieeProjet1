-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 31 mai 2024 à 01:35
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bdd_criee`
--

-- --------------------------------------------------------

--
-- Structure de la table `bac`
--

CREATE TABLE `bac` (
  `id` int(11) NOT NULL,
  `idBateau` int(11) NOT NULL,
  `datePeche` date DEFAULT NULL,
  `idLot` int(11) DEFAULT NULL,
  `idBac` char(1) DEFAULT NULL,
  `idTypeBac` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `bac`
--

INSERT INTO `bac` (`id`, `idBateau`, `datePeche`, `idLot`, `idBac`, `idTypeBac`) VALUES
(1, 11, '2022-07-18', 1, 'A', 1);

-- --------------------------------------------------------

--
-- Structure de la table `bateau`
--

CREATE TABLE `bateau` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `immatriculation` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `bateau`
--

INSERT INTO `bateau` (`id`, `nom`, `immatriculation`) VALUES
(1, 'Altair', 'Ad 895511'),
(2, 'Macareux', 'Ad 584873'),
(3, 'Avel Ar Mor', 'Ad 584930'),
(4, 'Plujadur', 'Ad 627846'),
(5, 'Gwaien', 'Ad 730414'),
(6, 'L Estran', 'Ad 815532'),
(7, 'Le Petit Corse', 'Ad 584826'),
(8, 'Le Vorlen', 'Ad 614221'),
(9, 'Les Copains d Abord', 'Ad 584846'),
(10, 'Tu Pe Du', 'Ad 584871'),
(11, 'Korrigan', 'Ad 895472'),
(12, 'Ar Guevel', 'Ad 895479'),
(13, 'Broceliande', 'Ad 895519'),
(14, 'L Aventurier', 'Ad 584865'),
(15, 'L Oceanide', 'Ad 741312'),
(16, 'L Arche d alliance', 'Ad 584830'),
(17, 'Sirocco', 'Ad 715792'),
(18, 'Ondine', 'Ad 584772'),
(19, 'Chimere', 'Ad 895516');

-- --------------------------------------------------------

--
-- Structure de la table `espece`
--

CREATE TABLE `espece` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `nomScientifique` varchar(50) DEFAULT NULL,
  `nomCourt` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `espece`
--

INSERT INTO `espece` (`id`, `nom`, `nomScientifique`, `nomCourt`) VALUES
(31020, 'Turbot', 'Psetta maxima', 'TURBO'),
(31030, 'Barbue', 'Scophthalmus rhombus', 'BARBU'),
(31150, 'Plie ou carrelet', 'Pleuronectes Platessa', 'PLIE'),
(32020, 'Merlu', 'Merluccius bilinearis', 'MERLU'),
(32050, 'Cabillaud', 'Gadus Morhua Morhue', 'CABIL'),
(32130, 'Lieu Jaune de Ligne', 'Pollachius pollachius', 'LJAUL'),
(32140, 'Lieu Noir', 'Lophius Virens', 'LNOI'),
(32230, 'Lingue franche', 'Molva Molva', 'LINGU'),
(33020, 'Congre commun', 'Conger conger', 'CONGR'),
(33080, 'Saint Pierre', 'Zeus Faber', 'STPIE'),
(33090, 'Bar de Chalut', 'Dicentrarchus Labrax', 'BARCH'),
(33091, 'Bar de Ligne', 'Dicentrarchus Labrax', 'BARLI'),
(33110, 'Mérou ou cernier', 'Polyprion Americanus', 'CERNI'),
(33120, 'Mérou noir', 'Epinephelus Guaza', 'MEROU'),
(33410, 'Rouget Barbet', 'Mullus SPP', 'ROUGT'),
(33450, 'Dorade royale chalut', 'Sparus Aurata', 'DORAC'),
(33451, 'Dorade royale ligne', 'Sparus Aurata', 'DORAL'),
(33480, 'Dorade rose', 'Pagellus bogaraveo', 'DORAD'),
(33490, 'Pageot Acarne', 'Pagellus Acarne', 'PAGEO'),
(33500, 'Pageot Commun', 'Pagellus Arythrinus', 'PAGEC'),
(33580, 'Vieille', 'LabrusBergylta', 'VIEIL'),
(33730, 'Grondin gris', 'Eutrigla Gurnadus', 'GRONG'),
(33740, 'Grondin rouge', 'Aspitrigla Cuculus', 'GRONR'),
(33760, 'Baudroie', 'Lophius Piscatorus', 'BAUDR'),
(33761, 'Baudroie Maigre', 'Lophius Piscicatorius', 'BAUDM'),
(33790, 'Grondin Camard', 'Trigloporus Lastoviza', 'GRONC'),
(33800, 'Grondin Perlon', 'Trigla Lucerna', 'GRONP'),
(34150, 'Mulet', 'Mugil SPP', 'MULET'),
(35040, 'Sardine atlantique', 'Sardina Pilchardus', 'SARDI'),
(37050, 'Maquereau', 'Scomber Scombrus', 'MAQUE'),
(38150, 'Raie douce', 'Raja Montagui', 'RAIE'),
(38160, 'Raie Pastenague commune', 'Dasyatis Pastinaca', 'RAIEP'),
(42020, 'Crabe tourteau de casier', 'Cancer Pagurus', 'CRABS'),
(42021, 'Crabe tourteau de chalut', 'Cancer Pagurus', 'CRABL'),
(42040, 'Araignée de mer casier', 'Maja squinado', 'ARAIS'),
(42041, 'Araignée de mer chalut', 'Maja squinado', 'ARAIL'),
(43010, 'Homard', 'Hammarus gammorus', 'HOMAR'),
(43030, 'Langouste rouge', 'Palinurus elephas', 'LANGR'),
(44010, 'Langoustine', 'Nephrops norvegicus', 'LANGT'),
(57010, 'Seiche', 'Sepia SPP', 'SEICH'),
(57020, 'Calmar', 'Loligo SPP', 'CALAM'),
(57050, 'Poulpe', 'Octopus SPP', 'POULP');

-- --------------------------------------------------------

--
-- Structure de la table `lot`
--

CREATE TABLE `lot` (
  `id` int(11) NOT NULL,
  `idBateau` int(11) NOT NULL,
  `datePeche` date DEFAULT NULL,
  `idLot` int(11) DEFAULT NULL,
  `idEspece` int(11) NOT NULL,
  `idTaille` int(11) NOT NULL,
  `idPresentation` char(3) NOT NULL,
  `idQualite` char(1) NOT NULL,
  `poidsBrutLot` float DEFAULT NULL,
  `prixEnchere` float DEFAULT NULL,
  `dateEnchere` date DEFAULT NULL,
  `HeureDebutEnchere` datetime DEFAULT NULL,
  `prixPlancher` float DEFAULT NULL,
  `prixDepart` float DEFAULT NULL,
  `codeEtat` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `lot`
--

INSERT INTO `lot` (`id`, `idBateau`, `datePeche`, `idLot`, `idEspece`, `idTaille`, `idPresentation`, `idQualite`, `poidsBrutLot`, `prixEnchere`, `dateEnchere`, `HeureDebutEnchere`, `prixPlancher`, `prixDepart`, `codeEtat`) VALUES
(1, 11, '2022-07-18', 1, 33760, 40, 'VID', 'E', 0.1, 0.1, '2022-07-20', '2022-07-18 10:15:00', 0.1, 0.1, 'E');

-- --------------------------------------------------------

--
-- Structure de la table `peche`
--

CREATE TABLE `peche` (
  `idBateau` int(11) NOT NULL,
  `datePeche` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `peche`
--

INSERT INTO `peche` (`idBateau`, `datePeche`) VALUES
(1, '2022-07-18'),
(1, '2022-07-24'),
(1, '2022-07-25'),
(1, '2022-07-30'),
(3, '2022-07-25'),
(3, '2022-07-30'),
(3, '2022-08-25'),
(4, '2022-07-18'),
(5, '2022-08-12'),
(7, '2022-07-25'),
(7, '2022-07-30'),
(9, '2022-07-18'),
(9, '2022-08-12'),
(11, '2022-07-18'),
(11, '2022-07-20'),
(11, '2022-07-21'),
(11, '2022-07-23'),
(11, '2022-07-24'),
(11, '2022-07-25'),
(11, '2022-07-30'),
(11, '2022-08-25');

-- --------------------------------------------------------

--
-- Structure de la table `presentation`
--

CREATE TABLE `presentation` (
  `id` char(3) NOT NULL,
  `libelle` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `presentation`
--

INSERT INTO `presentation` (`id`, `libelle`) VALUES
('ENT', 'Entier'),
('ET', 'Etété'),
('VID', 'Vidé');

-- --------------------------------------------------------

--
-- Structure de la table `qualite`
--

CREATE TABLE `qualite` (
  `id` char(1) NOT NULL,
  `libelle` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `qualite`
--

INSERT INTO `qualite` (`id`, `libelle`) VALUES
('A', 'glacé'),
('B', 'déclassé'),
('E', 'extra');

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `nomRole` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`id`, `nomRole`) VALUES
(1, 'Vétérinaire'),
(2, 'Peseur-marqueur'),
(3, 'Directeur des ventes');

-- --------------------------------------------------------

--
-- Structure de la table `taille`
--

CREATE TABLE `taille` (
  `id` int(11) NOT NULL,
  `specification` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `taille`
--

INSERT INTO `taille` (`id`, `specification`) VALUES
(10, 'taille de 10'),
(15, 'taille de 15'),
(20, 'taille de 20'),
(25, 'taille de 25'),
(30, 'taille de 30'),
(35, 'taille de 35'),
(40, 'taille de 40'),
(45, 'taille de 45'),
(50, 'taille de 50'),
(55, 'taille de 55'),
(60, 'taille de 60'),
(65, 'taille de 65'),
(70, 'taille de 70'),
(75, 'taille de 75'),
(80, 'taille de 80'),
(85, 'taille de 85'),
(90, 'taille de 90'),
(95, 'taille de 95');

-- --------------------------------------------------------

--
-- Structure de la table `typebac`
--

CREATE TABLE `typebac` (
  `id` int(11) NOT NULL,
  `tare` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `typebac`
--

INSERT INTO `typebac` (`id`, `tare`) VALUES
(1, 2.5),
(2, 4);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `id` int(11) NOT NULL,
  `nomUtilisateur` varchar(50) DEFAULT NULL,
  `motdepasse` varchar(50) DEFAULT NULL,
  `idRole` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `nomUtilisateur`, `motdepasse`, `idRole`) VALUES
(1, 'utilisateur1', 'mdp', 1),
(2, 'utilisateur2', 'mdp', 2),
(3, 'utilisateur3', 'mdp', 3);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `bac`
--
ALTER TABLE `bac`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idBateau` (`idBateau`,`datePeche`,`idBac`),
  ADD KEY `fk_bac_typebac` (`idTypeBac`),
  ADD KEY `fk_bac_lot` (`idBateau`,`datePeche`,`idLot`);

--
-- Index pour la table `bateau`
--
ALTER TABLE `bateau`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `espece`
--
ALTER TABLE `espece`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `lot`
--
ALTER TABLE `lot`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idBateau` (`idBateau`,`datePeche`,`idLot`),
  ADD KEY `fk_lot_espece` (`idEspece`),
  ADD KEY `fk_lot_taille` (`idTaille`),
  ADD KEY `fk_lot_presentation` (`idPresentation`),
  ADD KEY `fk_lot_qualite` (`idQualite`);

--
-- Index pour la table `peche`
--
ALTER TABLE `peche`
  ADD PRIMARY KEY (`idBateau`,`datePeche`);

--
-- Index pour la table `presentation`
--
ALTER TABLE `presentation`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `qualite`
--
ALTER TABLE `qualite`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `taille`
--
ALTER TABLE `taille`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `typebac`
--
ALTER TABLE `typebac`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_utilisateurs_roles` (`idRole`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `bac`
--
ALTER TABLE `bac`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `bateau`
--
ALTER TABLE `bateau`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `espece`
--
ALTER TABLE `espece`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57051;

--
-- AUTO_INCREMENT pour la table `lot`
--
ALTER TABLE `lot`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `taille`
--
ALTER TABLE `taille`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=96;

--
-- AUTO_INCREMENT pour la table `typebac`
--
ALTER TABLE `typebac`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bac`
--
ALTER TABLE `bac`
  ADD CONSTRAINT `fk_bac_lot` FOREIGN KEY (`idBateau`,`datePeche`,`idLot`) REFERENCES `lot` (`idBateau`, `datePeche`, `idLot`),
  ADD CONSTRAINT `fk_bac_typebac` FOREIGN KEY (`idTypeBac`) REFERENCES `typebac` (`id`);

--
-- Contraintes pour la table `lot`
--
ALTER TABLE `lot`
  ADD CONSTRAINT `fk_lot_espece` FOREIGN KEY (`idEspece`) REFERENCES `espece` (`id`),
  ADD CONSTRAINT `fk_lot_peche` FOREIGN KEY (`idBateau`,`datePeche`) REFERENCES `peche` (`idBateau`, `datePeche`),
  ADD CONSTRAINT `fk_lot_presentation` FOREIGN KEY (`idPresentation`) REFERENCES `presentation` (`id`),
  ADD CONSTRAINT `fk_lot_qualite` FOREIGN KEY (`idQualite`) REFERENCES `qualite` (`id`),
  ADD CONSTRAINT `fk_lot_taille` FOREIGN KEY (`idTaille`) REFERENCES `taille` (`id`);

--
-- Contraintes pour la table `peche`
--
ALTER TABLE `peche`
  ADD CONSTRAINT `peche_ibfk_1` FOREIGN KEY (`idBateau`) REFERENCES `bateau` (`id`);

--
-- Contraintes pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD CONSTRAINT `fk_utilisateurs_roles` FOREIGN KEY (`idRole`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
