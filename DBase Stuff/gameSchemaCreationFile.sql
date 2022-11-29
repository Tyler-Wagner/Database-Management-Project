CREATE DATABASE `gameschema` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `building` (
  `buildingID` int(11) NOT NULL AUTO_INCREMENT,
  `streetAddress` varchar(50) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `country` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`buildingID`),
  UNIQUE KEY `buildingID_UNIQUE` (`buildingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `game` (
  `gameID` int(11) NOT NULL AUTO_INCREMENT,
  `gameName` varchar(50) DEFAULT NULL,
  `gameSystem` varchar(20) DEFAULT NULL,
  `releaseDate` date DEFAULT NULL,
  PRIMARY KEY (`gameID`),
  UNIQUE KEY `gameID_UNIQUE` (`gameID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `shipping` (
  `shipmentID` int(11) NOT NULL AUTO_INCREMENT,
  `shipDate` date DEFAULT NULL,
  `receivedDate` date DEFAULT NULL,
  PRIMARY KEY (`shipmentID`),
  UNIQUE KEY `shipmentID_UNIQUE` (`shipmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `shippinginfo` (
  `shippingInfoID` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `videogameID` int(11) NOT NULL,
  `shipmentWeight` int(11) DEFAULT NULL,
  PRIMARY KEY (`shippingInfoID`),
  UNIQUE KEY `shippingInfoID_UNIQUE` (`shippingInfoID`),
  KEY `gameID_idx` (`videogameID`),
  CONSTRAINT `shippingInfoID` FOREIGN KEY (`shippingInfoID`) REFERENCES `shipping` (`shipmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `videogameID` FOREIGN KEY (`videogameID`) REFERENCES `game` (`gameID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `store` (
  `gameStoreID` int(11) NOT NULL AUTO_INCREMENT,
  `bID` int(11) NOT NULL,
  `gameStoreName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`gameStoreID`),
  UNIQUE KEY `gameStoreID_UNIQUE` (`gameStoreID`),
  KEY `buildingID_idx` (`bID`),
  CONSTRAINT `bID` FOREIGN KEY (`bID`) REFERENCES `building` (`buildingID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `iis` (
  `shopID` int(11) NOT NULL,
  `gID` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `aisle` varchar(3) DEFAULT NULL COMMENT 'aisle a1 - z10',
  `price` double DEFAULT NULL,
  PRIMARY KEY (`shopID`),
  UNIQUE KEY `shopID_UNIQUE` (`shopID`),
  KEY `gID_idx` (`gID`),
  CONSTRAINT `gID` FOREIGN KEY (`gID`) REFERENCES `game` (`gameID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `shopID` FOREIGN KEY (`shopID`) REFERENCES `store` (`gameStoreID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `warehouse` (
  `warehouseID` int(11) NOT NULL AUTO_INCREMENT,
  `buildingID` int(11) NOT NULL,
  `companyName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`warehouseID`),
  UNIQUE KEY `warehouseID_UNIQUE` (`warehouseID`),
  KEY `buildingID_idx` (`buildingID`),
  CONSTRAINT `buildingID` FOREIGN KEY (`buildingID`) REFERENCES `building` (`buildingID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `storage` (
  `storageID` int(11) NOT NULL AUTO_INCREMENT,
  `warehouseID` int(11) NOT NULL,
  `gameID` int(11) NOT NULL,
  `QTT` int(11) DEFAULT '0',
  `sectionID` varchar(1) DEFAULT NULL COMMENT 'Sections A-Z',
  `stackID` int(11) DEFAULT NULL COMMENT 'stack 1-10',
  PRIMARY KEY (`storageID`),
  UNIQUE KEY `storageID_UNIQUE` (`storageID`),
  KEY `gameID_idx` (`gameID`),
  KEY `warehouseID_idx` (`warehouseID`),
  CONSTRAINT `gameID` FOREIGN KEY (`gameID`) REFERENCES `game` (`gameID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `warehouseID` FOREIGN KEY (`warehouseID`) REFERENCES `warehouse` (`warehouseID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


####################################################### DATA #########################################################################

#Data for game
insert into game(gameID, gameName, gameSystem, releaseDate) 
values	(00111, 'Pokemon Sword', 'Nintendo Switch', '11-19-2021'),
		(00112, 'Super Mario Odyssey', 'Nintendo Switch', '10-27-2017'),
        (00113, 'Splatoon 2', 'Nintendo Switch', '07-21-2017'),
        (00114, 'Gotham Knights', 'PC', '10-21-2022'),
		(00115, 'Sims 4', 'PC', '09-02-2014'),
        (00116, 'NBA 2K23', 'PlayStation 5', '09-08-2022'),
        (00117, 'Returnal', 'PlayStation 5', '04-20-2021'),
        (00118, 'Just Dance 2022', 'PlayStation 5', '11-04-2021'),
        (00119, 'Evil West', 'PlayStation 5', '11-22-2022'),
        (00120, 'Call of Duty: Modern Warfare', 'Xbox One', '11-05-2007'),
        (00121, 'Grand Theft Auto V', 'Xbox One', '09-17-2013'),
        (00122, 'Minecraft', 'Xbox One', '11-18-2014'),
        (00123, 'Mortal Kombat 11', 'Xbox One', '04-23-2019');
        
#Data for building
insert into building(buildingID, streetaddress, city, state, country) 
values	(01, '646 Romines Mill Road', 'Dallas', 'TX', 'USA'), #10 Stores
		(02, '3364 Southern Avenue', 'Saint Charles', 'MO', 'USA'),
        (03, '2508 Frederick Street', 'Sacramento', 'CA', 'USA'),
        (04, '4137 Reserve St', 'Toronto', 'Ontario', 'Canada'),
		(05, '1182 Miguel Hidalgo', ' Nogales', 'Sonora', 'Mexico'),
        (06, '2144 Wilkinson Street', 'Nashville', 'TN', 'USA'), 
		(07, '4430 Mulberry Avenue', 'Corpus Christi', 'TX', 'USA'),
        (08, '2772 University Hill Road', 'Springfield', 'IL', 'USA'), 
		(09, '1080 Wildrose Lane', 'Westland', 'MI', 'USA'),
        (10, '2781 Point Street', 'Des Plaines', 'IL', 'USA'),
        (11, '2131 Parkview Drive', 'Houston', 'TX', 'USA'), #5 warehouses
        (12, '1639 Arthur Avenue', 'Marseilles', 'IL', 'USA'), 
		(13, '2581 Cambridge Court', 'Springdale', 'AR', 'USA'), 
        (14, '3834 Fancher Drive', 'Baja California', 'Aguascalientes', 'Mexico'), 
		(15, '4866 Sussex Court', 'Quebec', 'Quebec City', 'Canada'); 
        
#Data for shipping
insert into shipping(shipmentID, shipDate, receievedDate)
values	(20265, '09-06-2022', '09-11-2022'),
		(20266, '09-08-2022', '09-15-2022'),
        (20267, '09-23-2022', '09-30-2022'),
        (20268, '10-04-2022', '10-10-2022'),
		(20269, '10-09-2022', '10-17-2022'),
        (20270, '10-12-2022', '10-20-2022'),
		(20271, '10-16-2022', '10-23-2022'),
        (20272, '10-20-2022', '10-28-2022'),
        (20273, '10-22-2022', '10-30-2022'),
		(20274, '10-26-2022', '11-05-2022');
        
#Data for store
insert into store(bID, gameStoreName) 
values	(01, 'Store1'),
		(02, 'Store2'),
        (03, 'Store3'),
        (04, 'Store4'),
		(05, 'Store5'),
        (06, 'Store6'),
		(07, 'Store7'),
        (08, 'Store8'),
        (09, 'Store9'),
		(10, 'Store10');
        
#Data for warehouse
insert into warehouse(bID, companyName) 
values	(11, 'company1'),
		(12, 'company2'),
        (13, 'company3'),
        (14, 'company4'),
		(15, 'company5');

#Data for shippingInfo
insert into shippingInfo(shippingInfoID, quantity, videoGameID, shipmentWeight)
values	(20265, 100, 00111, 25),
		(20266, 200, 00112, 50),
        (20267, 250, 00113, 55),
        (20268, 50, 00114, 10),
		(20269, 150, 00115, 20),
        (20270, 300, 00116, 50),
		(20271, 200, 00119, 45),
        (20272, 150, 00120, 30),
        (20273, 175, 00121, 40),
		(20274, 100, 00122, 25);
        
#Data for iis
insert into iis(shopID, gID, quantity, aisle, price)
values	(01, 00111, 5, 1, 50), #Shop 1's inventory
		(01, 00112, 3, 1, 37),
		(01, 00113, 8, 1, 25),
		(01, 00114, 4, 2, 47),
		(01, 00115, 2, 2, 59),
		(01, 00116, 8, 3, 25),
        (01, 00117, 5, 3, 50),
		(01, 00118, 3, 3, 37),
		(01, 00119, 8, 4, 25),
		(01, 00120, 4, 4, 50),
		(01, 00121, 2, 4, 37),
		(01, 00122, 3, 4, 49),
        (01, 00123, 9, 4, 25),
        
        (02, 00111, 5, 1, 50), #Shop 2's inventory, etc. 
		(02, 00112, 3, 1, 37),
		(02, 00113, 8, 1, 25),
		(02, 00114, 4, 2, 47),
		(02, 00115, 2, 2, 59),
		(02, 00116, 8, 3, 25),
        (02, 00117, 5, 3, 50),
		(02, 00118, 3, 3, 37),
		(02, 00119, 8, 3, 25),
		(02, 00120, 4, 4, 50),
		(02, 00121, 2, 4, 37),
		(02, 00122, 3, 4, 49),
        (02, 00123, 9, 4, 25);
        
#Data for storage
insert into storage(warehouseID, gameID, QTT, sectionID, stackID)
values	(11, 00111, 300, 1, 1), #warehouse 1's inventory
		(11, 00112, 450, 1, 2), 
		(11, 00113, 350, 1, 3),
		(11, 00114, 400, 2, 4),
		(11, 00115, 360, 2, 5),
        (11, 00116, 600, 3, 6), 
		(11, 00117, 246, 3, 7),
		(11, 00118, 550, 3, 8),
		(11, 00119, 440, 3, 9),
		(11, 00120, 360, 4, 10), 
		(11, 00121, 250, 4, 11),
		(11, 00122, 180, 4, 12),
		(11, 00123, 500, 4, 13),
		
        (12, 00111, 280, 1, 1), #warehouse 2's inventory, etc. 
		(12, 00112, 344, 1, 2), 
		(12, 00113, 250, 1, 3),
		(12, 00114, 450, 2, 4),
		(12, 00115, 105, 2, 5),
        (12, 00116, 700, 3, 6), 
		(12, 00117, 200, 3, 7),
		(12, 00118, 500, 3, 8),
		(12, 00119, 490, 3, 9),
		(12, 00120, 380, 4, 10), 
		(12, 00121, 222, 4, 11),
		(12, 00122, 150, 4, 12),
		(12, 00123, 600, 4, 13);
        

        