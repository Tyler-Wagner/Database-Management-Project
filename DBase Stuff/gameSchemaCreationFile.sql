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
