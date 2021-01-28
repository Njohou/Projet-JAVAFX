DROP TABLE IF EXISTS Employe;
CREATE TABLE Employe (
idEmpl BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
nomEmpl VARCHAR(30) NOT NULL,
prenomEmpl VARCHAR(30) NOT NULL,
sexe enum('M','F','A') NOT NULL,
mailEmpl VARCHAR(255) NOT NULL,  //
motdepasse VARCHAR(255) NOT NULL,
telEmpl INT(9) NOT NULL,         //
ServiceTravail enum('Service Logistique','Comptabilité','Service Juridique','Service Financier','Ressource Humaine') NOT NULL

)
ENGINE=INNODB;




DROP TABLE IF EXISTS Chauffeur;
CREATE TABLE  Chauffeur(
idChauffeur BIGINT(20) PRIMARY KEY,
numpermis VARCHAR(15) NOT NULL,

FOREIGN KEY (idChauffeur) REFERENCES Employe(idEmpl)
)
ENGINE=INNODB;






DROP TABLE IF EXISTS Vehicule;
CREATE TABLE  Vehicule(
immatriculation VARCHAR(15) PRIMARY KEY,
modele VARCHAR(15) NOT NULL,
marque VARCHAR(15) NOT NULL,
puissance Float(2) NOT NULL,
Kilometrage  Float(2) NOT NULL,
DateDaquisition date NOT NULL,
Utilité VARCHAR(500) NOT NULL,
idFournisseur VARCHAR(20)  NOT NULL,
idAssureur VARCHAR(20) NOT NULL,
dispo enum("disponible","indisponible"),

FOREIGN KEY (idFournisseur) REFERENCES Fournisseur(idFournisseur),
FOREIGN KEY (idAssureur) REFERENCES Assureur(idAssureur)

)
ENGINE=INNODB;




DROP TABLE IF EXISTS Assureur;
CREATE TABLE  Assureur(
idAssureur VARCHAR(20) PRIMARY KEY,
nomAssureur VARCHAR(15) NOT NULL,
mailAssureur VARCHAR(40) NOT NULL,
telAssureur INT(9) NOT NULL,
type VARCHAR(15) NOT NULL
)
ENGINE=INNODB;




DROP TABLE IF EXISTS Fournisseur;
CREATE TABLE  Fournisseur(
idFournisseur VARCHAR(20) PRIMARY KEY,
nomFournisseur VARCHAR(20) NOT NULL, 
mailFournisseur VARCHAR(30) NOT NULL,
telFournisseur VARCHAR(15) NOT NULL,
)
ENGINE=INNODB;




DROP TABLE IF EXISTS Garage;
CREATE TABLE  Garage(
idGarage VARCHAR(20) PRIMARY KEY,
nomGarage VARCHAR(15),
adressegarage  VARCHAR(15),
mailgarage VARCHAR(30),
telgarage INT(9)
)
ENGINE=INNODB;



DROP TABLE IF EXISTS Reparation;
CREATE TABLE  Reparation(
idReparation BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
DebutReparation date NOT NULL,
FinReparation date NOT NULL,
type VARCHAR(500) NOT NULL,
Cout FLOAT NOT NULL,
immatriculation VARCHAR(15) NOT NULL,
idgarage VARCHAR(20) NOT NULL,

FOREIGN KEY (idgarage) REFERENCES Garage(idgarage),
FOREIGN KEY (immatriculation) REFERENCES Vehicule(immatriculation)
)
ENGINE=INNODB;





DROP TABLE IF EXISTS Conduire;
CREATE TABLE  Conduire(

idConduire INT(20) PRIMARY KEY AUTO_INCREMENT,
idChauffeur INT(20) NOT NULL, 
DateEmprunt date NOT NULL,
DateRemise date NOT NULL,
immatriculation  CHAR(4) NOT NULL,
FOREIGN KEY (idConduire) REFERENCES Employe(idEmpl),
FOREIGN KEY (immatriculation) REFERENCES vehicule(immatriculation)
)
ENGINE=INNODB;