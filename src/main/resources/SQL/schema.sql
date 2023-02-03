DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL,
    `manager_id` BIGINT,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `proffesion` VARCHAR(100) NOT NULL,
    `employment_date` DATE,
    `salary` DECIMAL(6,2) NOT NULL,
    `employment_type` VARCHAR(23),
    `department_id` BIGINT,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_department`
    FOREIGN KEY (`department_id`) REFERENCES `department`(`id`)
);

DROP TABLE IF EXISTS `personal_data`;
CREATE TABLE `personal_data` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(25) NOT NULL,
    `surename` VARCHAR(40) NOT NULL,
    `birth_day` DATE NOT NULL,
    `pesel` BIGINT(11) NOT NULL,
    PRIMARY KEY(`id`),
    CONSTRAINT `fk_employee`
    FOREIGN KEY (`id`) REFERENCES `employee`(`id`)
);

DROP TABLE IF EXISTS `contact_details`;
CREATE TABLE `contact_details` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `phone_number` VARCHAR(15) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `street` VARCHAR(50) NOT NULL,
    `postal_code` VARCHAR(6) NOT NULL,
    `city` VARCHAR(30) NOT NULL,
    PRIMARY KEY(`id`),
    CONSTRAINT `fk1_employee`
    FOREIGN KEY (`id`) REFERENCES `employee`(`id`)
);

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL,
    `uuid` VARCHAR(36) NOT NULL,
    PRIMARY KEY(`id`)
);

DROP TABLE IF EXISTS `employee_projects`;
CREATE TABLE `employee_projects` (
    `employee_id` BIGINT NOT NULL,
    `project_id` BIGINT NOT NULL,
    PRIMARY KEY(`employee_id`, project_id),
    CONSTRAINT `fk_project_employee_employee_id`
    FOREIGN KEY (`employee_id`) REFERENCES `employee`(`id`),
    CONSTRAINT `fk_project_employee_project_id`
    FOREIGN KEY (`project_id`) REFERENCES `project`(`id`)
);

DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `image_data` MEDIUMBLOB,
    PRIMARY KEY(`id`),
    CONSTRAINT `fk2_employee`
    FOREIGN KEY (`id`) REFERENCES `employee`(`id`)
);

ALTER TABLE `department` ADD
CONSTRAINT `fk3_employee`
FOREIGN KEY (`manager_id`) REFERENCES `employee`(`id`)
ON DELETE SET NULL;