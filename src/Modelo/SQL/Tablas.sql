DROP TABLE IF EXISTS Oferta;
DROP TABLE IF EXISTS Demanda;
DROP TABLE IF EXISTS Colaboracion ;
DROP TABLE IF EXISTS Gasto ;
DROP TABLE IF EXISTS PagoCuota ;
DROP TABLE IF EXISTS Cuota ;
DROP TABLE IF EXISTS Socio ;
DROP TABLE IF EXISTS C_Persona ;
DROP TABLE IF EXISTS C_Empresa ;
DROP TABLE IF EXISTS Colaborador ;
DROP TABLE IF EXISTS ModificacionAyuda ;
DROP TABLE IF EXISTS Movimiento ;
DROP TABLE IF EXISTS Ayuda ;
DROP TABLE IF EXISTS Beneficiario ;
DROP TABLE IF EXISTS Voluntario ;
DROP TABLE IF EXISTS TipoAyuda ;
DROP TABLE IF EXISTS Familia ;
DROP TABLE IF EXISTS Persona ;
DROP TABLE IF EXISTS Sector;

CREATE TABLE Persona (
	OID INTEGER PRIMARY KEY AUTO_INCREMENT
	, NIF VARCHAR (11) NOT NULL UNIQUE
	, Nombre VARCHAR (15) NOT NULL
	, Apellidos VARCHAR (20) NOT NULL
	, FechaNacimiento DATE
	, CP VARCHAR (5)
	, TelefonoFijo VARCHAR (15)
	, TelefonoMovil VARCHAR (15)
	, Domicilio VARCHAR (50)
	, Localidad VARCHAR (15)
);

CREATE TABLE Beneficiario (
	OID INTEGER PRIMARY KEY
	, EstadoCivil VARCHAR (10)
	, Nacionalidad VARCHAR (15)
	, NivelDeEstudio VARCHAR (25)
	, Observaciones TEXT
	, Ocupacion TEXT
	, Profesion TEXT
	, SituacionEconomica TEXT
	, Vivienda VARCHAR (20)
	, ViviendaAlquiler DECIMAL
	, ViviendaObservaciones VARCHAR (100)
	, CONSTRAINT FkBeneficiario2 FOREIGN KEY (OID)
				  REFERENCES Persona (OID)
);

CREATE TABLE Familia (
	OID_Bene1 INTEGER
	, OID_Bene2 INTEGER
	, Parentesco VARCHAR (30)
	, PRIMARY KEY (OID_Bene1,OID_Bene2)
	, CONSTRAINT Fkfamilia3 FOREIGN KEY (OID_Bene1)
				  REFERENCES Persona (OID)
	, CONSTRAINT Fkfamilia4 FOREIGN KEY (OID_Bene2)
				  REFERENCES Persona (OID)
);


CREATE TABLE Voluntario (
	OID INTEGER PRIMARY KEY
	, Password VARCHAR (32)
	, CONSTRAINT FkVoluntario3 FOREIGN KEY (OID)
				  REFERENCES Persona (OID)
);

CREATE TABLE TipoAyuda (
	  OID INTEGER PRIMARY KEY AUTO_INCREMENT
	, Descripcion TEXT
	, Monetaria INTEGER
	, Titulo VARCHAR (50)
);

CREATE TABLE Ayuda (
	  OID INTEGER PRIMARY KEY AUTO_INCREMENT
	, Fecha DATE
	, Importe DECIMAL
	, Observaciones TEXT
	, TipoAyudaOID INTEGER
	, OID_Volun INTEGER
	, OID_Bene INTEGER
	, CONSTRAINT FkAyuda1 FOREIGN KEY (TipoAyudaOID)
				  REFERENCES TipoAyuda (OID)
	, CONSTRAINT FkAyuda2 FOREIGN KEY (OID_Volun)
				  REFERENCES Voluntario (OID)
	, CONSTRAINT FkAyuda3 FOREIGN KEY (OID_Bene)
				  REFERENCES Beneficiario (OID)
);

CREATE TABLE ModificacionAyuda (
	  ModificacionID INTEGER DEFAULT '1' NOT NULL PRIMARY KEY
	, AyudaOID INTEGER
	, OID_Volun INTEGER
	, Fecha DATE
	, CONSTRAINT FkModificacionAyuda FOREIGN KEY (AyudaOID)
				  REFERENCES Ayuda (OID)
	, CONSTRAINT FkModificacionAyuda2 FOREIGN KEY (OID_Volun)
				  REFERENCES Voluntario (OID)
);

CREATE TABLE Movimiento (
	OID INTEGER AUTO_INCREMENT
	, Cantidad FLOAT NOT NULL
	, Concepto TEXT NOT NULL
	, Fecha DATE NOT NULL
	, PRIMARY KEY (OID)
);


CREATE TABLE Colaborador (
	  OID INTEGER PRIMARY KEY AUTO_INCREMENT
	, Direccion VARCHAR (50) NOT NULL
	, Localidad VARCHAR (50) NOT NULL
	, Provincia VARCHAR (50) NOT NULL
	, CP VARCHAR (5) NOT NULL
	, telefonoFijo VARCHAR (15)
	, telefonoMovil VARCHAR (15)
	, Email VARCHAR(50)
);

CREATE TABLE C_Empresa (
	  OID INTEGER
	, CIF VARCHAR (9) NOT NULL UNIQUE
	, Nombre VARCHAR (50) NOT NULL
	, Direccionweb VARCHAR (256) NOT NULL
	, Fax VARCHAR (15)
	, PRIMARY KEY (OID)
	, CONSTRAINT Fk_OIDCEmpresa FOREIGN KEY (OID)
				REFERENCES Colaborador (OID)
);

CREATE TABLE C_Persona (
	  OID INTEGER
	, DNI VARCHAR (9) NOT NULL UNIQUE
	, Nombre VARCHAR (50) NOT NULL
	, Apellidos VARCHAR (256) NOT NULL
	, FechaNacimiento DATE NOT NULL
	, Sexo CHAR
	, PRIMARY KEY (OID)
	, CONSTRAINT Fk_OIDCPersona FOREIGN KEY (OID)
				REFERENCES Colaborador (OID)
);

CREATE TABLE Socio (
	  OID INTEGER
	, Usuario VARCHAR (50) NOT NULL UNIQUE
	, Contrasena VARCHAR (50)  NOT NULL
	, PRIMARY KEY (OID)
	, CONSTRAINT Fk_OIDSocio FOREIGN KEY (OID)
				REFERENCES C_Persona (OID)
);

CREATE TABLE Cuota (
	  OID INTEGER
	, OIDSocio INTEGER
	, Cantidad FLOAT NOT NULL
	, IntervalosPagos DATE NOT NULL
	, FechaInicio DATE NOT NULL
	, FechaFin DATE
	, FechaUltimoPago DATE
	, PRIMARY KEY (OID)
	, CONSTRAINT Fk_OIDCuota FOREIGN KEY (OIDSocio)
				  REFERENCES Socio (OID)
);

CREATE TABLE PagoCuota (
	OID INTEGER
	,OIDVoluntario INTEGER
	,OIDSocio INTEGER
	, PRIMARY KEY (OID)
	, CONSTRAINT Fk_OIDPagoCuota FOREIGN KEY (OIDSocio)
			REFERENCES Socio (OID)
	, CONSTRAINT Fk_OIDPagoCuota2 FOREIGN KEY (OID)
			REFERENCES Movimiento (OID)
	, CONSTRAINT Fk_OIDPagoCuota3 FOREIGN KEY (OIDVoluntario)
			REFERENCES Voluntario (OID)
);

CREATE TABLE Gasto (
	OID INTEGER
	, OIDAyuda INTEGER
	, PRIMARY KEY (OID)
	, CONSTRAINT Fk_OIDGasto FOREIGN KEY (OID)
			REFERENCES Movimiento (OID)
	, CONSTRAINT Fk_OIDGasto2 FOREIGN KEY (OIDAyuda)
			REFERENCES Ayuda (OID)
);

CREATE TABLE Colaboracion (
	OID INTEGER
	,OIDVoluntario INTEGER
	,OIDColaborador INTEGER
	, PRIMARY KEY (OID)
	, CONSTRAINT Fk_OIDColaboracion FOREIGN KEY (OID)
			REFERENCES Movimiento (OID)
	, CONSTRAINT Fk_OIDColaboracion2 FOREIGN KEY (OIDVoluntario)
			REFERENCES Voluntario (OID)
	, CONSTRAINT Fk_OIDColaboracion3 FOREIGN KEY (OIDColaborador)
			REFERENCES Colaborador (OID)
);

CREATE TABLE Sector (
	OID INTEGER
	, Descripccion VARCHAR (100)  NOT NULL
	, PRIMARY KEY (OID)
);

CREATE TABLE Oferta (
	OID INTEGER
	, OIDSector INTEGER
	, OIDEmpresa INTEGER
	, OIDBeneficiario INTEGER
	, OIDVoluntario INTEGER
	, CualificacionRequerida VARCHAR (50)
	, DescripcionOferta VARCHAR (100) NOT NULL
	, DuracionContrato INTEGER
	, Fecha DATE NOT NULL
	, PlazasOfertadas INTEGER
	, TipoContrato VARCHAR (50)
	, CONSTRAINT Fk_OIDOferta FOREIGN KEY (OIDSector)
			REFERENCES Sector (OID)
	, CONSTRAINT Fk_OIDOferta2 FOREIGN KEY (OIDEmpresa)
			REFERENCES C_Empresa (OID)
	, CONSTRAINT Fk_OIDOferta3 FOREIGN KEY (OIDBeneficiario)
			REFERENCES Beneficiario (OID)
	, CONSTRAINT Fk_OIDOferta4 FOREIGN KEY (OIDVoluntario)
			REFERENCES Voluntario (OID)

);

CREATE TABLE Demanda (
	OID INTEGER
	, OIDSector INTEGER
	, OIDBeneficiario INTEGER
	, OIDVoluntario INTEGER
	, DescripcionVidaLaboral VARCHAR (100)
	, Fecha DATE NOT NULL
	, CONSTRAINT Fk_OIDDemanda FOREIGN KEY (OIDSector)
			REFERENCES Sector (OID)
	, CONSTRAINT Fk_OIDDemanda2 FOREIGN KEY (OIDBeneficiario)
			REFERENCES Beneficiario (OID)
	, CONSTRAINT Fk_OIDDemanda3 FOREIGN KEY (OIDVoluntario)
			REFERENCES Voluntario (OID)
);


INSERT INTO Persona (`OID`, `NIF`, `Nombre`, `Apellidos`, `FechaNacimiento`, `CP`, `TelefonoFijo`, `TelefonoMovil`, `Domicilio`, `Localidad`) VALUES (1, '12345678A', 'admin', 'admin', '1990-04-03', 11321, 666999666, 999666999, 'C/Falsa 123', 'Granada');
INSERT INTO Voluntario (`OID`, `Password`) VALUES (1, 'cd15bf6a6c78f04336aa85a722ceb148');
