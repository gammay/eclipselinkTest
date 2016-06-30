SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `ELTestDB` ;
CREATE SCHEMA IF NOT EXISTS `ELTestDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `ELTestDB` ;

-- -----------------------------------------------------
-- Table `ELTestDB`.`Tenant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ELTestDB`.`Tenant` ;

CREATE TABLE IF NOT EXISTS `ELTestDB`.`Tenant` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `CreatedDateTime` DATETIME NULL,
  `UpdatedDateTime` DATETIME NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ELTestDB`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ELTestDB`.`User` ;

CREATE TABLE IF NOT EXISTS `ELTestDB`.`User` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL,
  `CompanyName` VARCHAR(45) NULL,
  `CreatedDateTime` DATETIME NULL,
  `UpdatedDateTime` DATETIME NULL,
  `Tenant` INT NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `fk_User_Tenant`
    FOREIGN KEY (`Tenant`)
    REFERENCES `ELTestDB`.`Tenant` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_User_Tenant_idx` ON `ELTestDB`.`User` (`Tenant` ASC);


-- -----------------------------------------------------
-- Table `ELTestDB`.`UserAddress`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ELTestDB`.`UserAddress` ;

CREATE TABLE IF NOT EXISTS `ELTestDB`.`UserAddress` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `AddressLine1` VARCHAR(45) NULL,
  `AddressLine2` VARCHAR(45) NULL,
  `CreatedDateTime` DATETIME NULL,
  `UpdatedDateTime` DATETIME NULL,
  `User` INT NULL,
  `Tenant` INT NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `fk_UserAddress_Tenant`
    FOREIGN KEY (`Tenant`)
    REFERENCES `ELTestDB`.`Tenant` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserAddress_User`
    FOREIGN KEY (`User`)
    REFERENCES `ELTestDB`.`User` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_UserAddress_Tenant_idx` ON `ELTestDB`.`UserAddress` (`Tenant` ASC);

CREATE INDEX `fk_UserAddress_User_idx` ON `ELTestDB`.`UserAddress` (`User` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `ELTestDB`.`Tenant`
-- -----------------------------------------------------
START TRANSACTION;
USE `ELTestDB`;
INSERT INTO `ELTestDB`.`Tenant` (`Id`, `Name`, `CreatedDateTime`, `UpdatedDateTime`) VALUES (1, 'tenant1', NULL, NULL);
INSERT INTO `ELTestDB`.`Tenant` (`Id`, `Name`, `CreatedDateTime`, `UpdatedDateTime`) VALUES (2, 'tenant2', NULL, NULL);
INSERT INTO `ELTestDB`.`Tenant` (`Id`, `Name`, `CreatedDateTime`, `UpdatedDateTime`) VALUES (3, 'tenant3', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ELTestDB`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `ELTestDB`;
INSERT INTO `ELTestDB`.`User` (`Id`, `Name`, `CompanyName`, `CreatedDateTime`, `UpdatedDateTime`, `Tenant`) VALUES (1, 'user1', 'xyz', '2016-06-29 11:24:21', NULL, 1);

COMMIT;

