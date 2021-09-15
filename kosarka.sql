/*
SQLyog Community
MySQL - 10.4.8-MariaDB : Database - kosarka
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `clankluba` */

CREATE TABLE `clankluba` (
  `idClana` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `prezime` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `datumRodjenja` date NOT NULL,
  `jmbg` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idClana`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `igrac` */

CREATE TABLE `igrac` (
  `idClana` bigint(20) NOT NULL,
  `brojNaDresu` int(11) NOT NULL,
  `visina` double NOT NULL,
  `sifraTima` bigint(20) NOT NULL,
  PRIMARY KEY (`idClana`),
  KEY `sifraTima` (`sifraTima`),
  CONSTRAINT `igrac_ibfk_1` FOREIGN KEY (`idClana`) REFERENCES `clankluba` (`idClana`),
  CONSTRAINT `igrac_ibfk_2` FOREIGN KEY (`sifraTima`) REFERENCES `tim` (`sifraTima`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `kategorija` */

CREATE TABLE `kategorija` (
  `sifraKategorije` bigint(20) NOT NULL AUTO_INCREMENT,
  `nazivKategorije` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`sifraKategorije`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `tim` */

CREATE TABLE `tim` (
  `sifraTima` bigint(20) NOT NULL AUTO_INCREMENT,
  `nazivTima` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `idClana` bigint(20) NOT NULL,
  `sifraKategorije` bigint(20) NOT NULL,
  PRIMARY KEY (`sifraTima`),
  KEY `idClana` (`idClana`),
  KEY `sifraKategorije` (`sifraKategorije`),
  CONSTRAINT `tim_ibfk_1` FOREIGN KEY (`idClana`) REFERENCES `trener` (`idClana`),
  CONSTRAINT `tim_ibfk_2` FOREIGN KEY (`sifraKategorije`) REFERENCES `kategorija` (`sifraKategorije`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `trener` */

CREATE TABLE `trener` (
  `idClana` bigint(20) NOT NULL,
  `brojLicence` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idClana`),
  CONSTRAINT `trener_ibfk_1` FOREIGN KEY (`idClana`) REFERENCES `clankluba` (`idClana`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
