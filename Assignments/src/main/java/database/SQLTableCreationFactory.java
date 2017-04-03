package database;

import static database.Constants.Tables.*;

public class SQLTableCreationFactory {

    public String getCreateSQLforTable(String table) {
        switch (table) {
            case USER:
                return "CREATE TABLE IF NOT EXISTS `bank`.`user` (" + "`id` INT NOT NULL AUTO_INCREMENT,"
                        + "`username` VARCHAR(200) NOT NULL," + "`password` VARCHAR(64) NOT NULL," + "PRIMARY KEY (`id`),"
                        + "UNIQUE INDEX `username_UNIQUE` (`username` ASC)," + "UNIQUE INDEX `id_UNIQUE` (`id` ASC))"
                        + "ENGINE = InnoDB";
            case CLIENT:
                return "CREATE TABLE IF NOT EXISTS `bank`.`client` (" + "`id` INT NOT NULL AUTO_INCREMENT, "
                        + "`firstName` VARCHAR(200) NOT NULL, " + "`lastName` VARCHAR(200) NOT NULL, "
                        + "`CNP` VARCHAR(13) NOT NULL, " + "`idCard` VARCHAR(16) NOT NULL, "
                        + "`address` VARCHAR(200) NOT NULL, " + " PRIMARY KEY (`id`)) " + "ENGINE = InnoDB";
            case ACCOUNT:
                return "CREATE TABLE IF NOT EXISTS `bank`.`account` (" + " `id` INT NOT NULL AUTO_INCREMENT,"
                        + "`type` VARCHAR(45) NOT NULL," + " `money` FLOAT NULL," + "`dateOfCreation` DATE NULL,"
                        + "`Accountcol` VARCHAR(45) NULL," + " PRIMARY KEY (`id`))" + "ENGINE = InnoDB";
            case ROLE:
                return "CREATE TABLE IF NOT EXISTS `bank`.`role` (" + "`id` INT NOT NULL AUTO_INCREMENT,"
                        + " `role` VARCHAR(100) NOT NULL," + "PRIMARY KEY (`id`))" + "ENGINE = InnoDB";
            case RIGHT:
                return "CREATE TABLE IF NOT EXISTS `bank`.`right` (" + "`id` INT NOT NULL AUTO_INCREMENT,"
                        + "`right` VARCHAR(100) NULL," + "PRIMARY KEY (`id`))" + "ENGINE = InnoDB";
            case ROLE_RIGHT:
                return "CREATE TABLE IF NOT EXISTS `bank`.`role_right` ( " + " `id` INT NOT NULL AUTO_INCREMENT,"
                        + " `role_id` INT NOT NULL," + " `right_id` INT NOT NULL," + " PRIMARY KEY (`id`),"
                        + " INDEX `ID_role_idx` (`role_id` ASC)," + " INDEX `ID_right_idx` (`right_id` ASC),"
                        + " CONSTRAINT `ID_role`" + " FOREIGN KEY (`role_id`)" + " REFERENCES `bank`.`role` (`id`)"
                        + " ON DELETE CASCADE" + " ON UPDATE CASCADE," + " CONSTRAINT `ID_right`"
                        + "  FOREIGN KEY (`right_id`)" + "  REFERENCES `bank`.`right` (`id`)" + " ON DELETE CASCADE"
                        + "  ON UPDATE CASCADE)" + "ENGINE = InnoDB";
            case USER_ROLE:
                return "CREATE TABLE IF NOT EXISTS `bank`.`user_role` ( " + "`id` INT NOT NULL AUTO_INCREMENT,"
                        + "`user_id` INT NOT NULL," + " `role_id` INT NOT NULL," + " PRIMARY KEY (`id`),"
                        + " INDEX `user_id_idx` (`user_id` ASC)," + " INDEX `role_id_idx` (`role_id` ASC),"
                        + " CONSTRAINT `user_id`" + "   FOREIGN KEY (`user_id`)" + "  REFERENCES `bank`.`user` (`id`)"
                        + "   ON DELETE CASCADE" + "  ON UPDATE CASCADE," + " CONSTRAINT `role_id`"
                        + "  FOREIGN KEY (`role_id`)" + "  REFERENCES `bank`.`role` (`id`)" + " ON DELETE CASCADE"
                        + "  ON UPDATE CASCADE)" + "ENGINE = InnoDB";
            case CLIENT_ACCOUNT:
                return "CREATE TABLE IF NOT EXISTS `bank`.`client_account` (" + " `idclient_account` INT NOT NULL,"
                        + " `idclient` INT NULL," + " `idaccount` INT NULL," + " PRIMARY KEY (`idclient_account`),"
                        + " INDEX `idClient_idx` (`idclient` ASC)," + " INDEX `idAccount_idx` (`idaccount` ASC),"
                        + "CONSTRAINT `idClient`" + "  FOREIGN KEY (`idclient`)" + "  REFERENCES `bank`.`client` (`id`)"
                        + "  ON DELETE CASCADE" + "  ON UPDATE CASCADE," + " CONSTRAINT `idAccount`"
                        + "  FOREIGN KEY (`idaccount`)" + " REFERENCES `bank`.`account` (`id`)" + " ON DELETE CASCADE"
                        + " ON UPDATE CASCADE)" + "ENGINE = InnoDB";

            default:
                return "";
        }
    }
}
