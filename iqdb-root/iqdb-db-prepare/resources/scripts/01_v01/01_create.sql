SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Table `SITE_USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SITE_USER` (
  `U_ID` INT NOT NULL,
  `U_NAME` VARCHAR(100) NULL,
  `U_EMAIL` VARCHAR(255) NULL,
  `U_PASSWORD` VARCHAR(45) NULL,
  `U_ROLE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`U_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TEXTUAL_ENTITY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TEXTUAL_ENTITY` (
  `TE_ID` BIGINT NOT NULL AUTO_INCREMENT,
  `TE_AUTHOR_U_ID` INT NOT NULL,
  `TE_PARENT_TE_ID` BIGINT NULL,
  `TE_CREATION_DATE` DATETIME NOT NULL,
  `TE_LAST_MODIFY_DATE` DATETIME NULL,
  `TE_SUM_MARK` INT NOT NULL DEFAULT 0,
  `TE_AVERAGE_MARK` DOUBLE NOT NULL DEFAULT 0,
  `TE_DISCRIMINATOR` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`TE_ID`),
  INDEX `TE_AUTHOR_U_FK_idx` (`TE_AUTHOR_U_ID` ASC),
  INDEX `TE_PARENT_TE_FK_idx` (`TE_PARENT_TE_ID` ASC),
  CONSTRAINT `TE_AUTHOR_U_FK`
    FOREIGN KEY (`TE_AUTHOR_U_ID`)
    REFERENCES `SITE_USER` (`U_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `TE_PARENT_TE_FK`
    FOREIGN KEY (`TE_PARENT_TE_ID`)
    REFERENCES `TEXTUAL_ENTITY` (`TE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TEXTUAL_BLOCK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TEXTUAL_BLOCK` (
  `TB_ID` BIGINT NOT NULL AUTO_INCREMENT,
  `TB_TEXT` TEXT NOT NULL,
  `TB_LANG` VARCHAR(30) NULL,
  `TB_DISCRIMINATOR` VARCHAR(20) NOT NULL,
  `TB_FROM_DATE` DATETIME NOT NULL,
  `TB_TO_DATE` DATETIME NULL,
  `TB_END_REASON` VARCHAR(20) NULL,
  `TB_PARENT_TE_ID` BIGINT NULL,
  `TB_AUTHOR_U_ID` INT NULL,
  PRIMARY KEY (`TB_ID`),
  INDEX `TE_PARENT_TE_FK_idx` (`TB_PARENT_TE_ID` ASC),
  CONSTRAINT `TB_PARENT_TE_FK`
    FOREIGN KEY (`TB_PARENT_TE_ID`)
    REFERENCES `TEXTUAL_ENTITY` (`TE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `TB_AUTHOR_U_FK`
    FOREIGN KEY (`TB_AUTHOR_U_ID`)
    REFERENCES `SITE_USER` (`U_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TAG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAG` (
  `TAG_ID` INT NOT NULL,
  `TAG_TEXT` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`TAG_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TAG_TEXTUAL_ENTITY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAG_TEXTUAL_ENTITY` (
  `TTE_TAG_ID` INT NOT NULL,
  `TTE_TE_ID` BIGINT NOT NULL,
  INDEX `TTE_TAG_FK_idx` (`TTE_TAG_ID` ASC),
  INDEX `TTE_TE_FK_idx` (`TTE_TE_ID` ASC),
  PRIMARY KEY (`TTE_TAG_ID`, `TTE_TE_ID`),
  CONSTRAINT `TTE_TAG_FK`
    FOREIGN KEY (`TTE_TAG_ID`)
    REFERENCES `TAG` (`TAG_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `TTE_TE_FK`
    FOREIGN KEY (`TTE_TE_ID`)
    REFERENCES `TEXTUAL_ENTITY` (`TE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MARK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MARK` (
  `MARK_ID` BIGINT NOT NULL,
  `MARK_PARENT_TE_ID` BIGINT NOT NULL,
  `MARK_AUTHOR_U_ID` INT NOT NULL,
  `MARK_CREATION_DATE` DATETIME NOT NULL,
  PRIMARY KEY (`MARK_ID`),
  INDEX `MARK_PARENT_TE_FK_idx` (`MARK_PARENT_TE_ID` ASC),
  INDEX `MARK_AUTHOR_U_FK_idx` (`MARK_AUTHOR_U_ID` ASC),
  CONSTRAINT `MARK_PARENT_TE_FK`
    FOREIGN KEY (`MARK_PARENT_TE_ID`)
    REFERENCES `TEXTUAL_ENTITY` (`TE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `MARK_AUTHOR_U_FK`
    FOREIGN KEY (`MARK_AUTHOR_U_ID`)
    REFERENCES `SITE_USER` (`U_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
