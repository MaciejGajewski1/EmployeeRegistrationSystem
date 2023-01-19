CREATE TABLE `employees` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `surename` VARCHAR(100) NOT NULL,
    `age` INT NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `phonenumber` DECIMAL(9),
    `proffesion` VARCHAR(100) NOT NULL,
    `employmentdate` DATETIME,
    PRIMARY KEY (`id`)
);