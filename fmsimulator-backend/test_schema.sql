DROP SCHEMA IF EXISTS `test`;
CREATE SCHEMA `test`;
USE `test`;

CREATE TABLE `Country`(
    `country_id` varchar(3) NOT NULL,
    `country_name` varchar(255) NOT NULL DEFAULT "",
    PRIMARY KEY(`country_id`)
);

CREATE TABLE `League`(
	`league_id` varchar(255) NOT NULL,
    `league_name` varchar(255) NOT NULL DEFAULT "",
    `league_capacity` int NOT NULL DEFAULT 0,
    `league_relegation` int NOT NULL DEFAULT 0,
    `league_parent_league_id` varchar(255) NOT NULL DEFAULT "-",
    `league_round` int NOT NULL DEFAULT 1,
    `league_cicle` int NOT NULL DEFAULT 1,
    PRIMARY KEY(`league_id`)
);

CREATE TABLE `Team`(
    `team_id` varchar(255) NOT NULL,
    `team_country_id` varchar(3) NOT NULL DEFAULT "ALL",
    `team_name` varchar(30) NOT NULL DEFAULT "",
    `team_fullname` varchar(255) DEFAULT "",
    `team_preferred_jersey` varchar(5) NOT NULL DEFAULT "light",
    `team_formation` varchar(10) DEFAULT "4-3-3",
    `team_type` enum('club', 'national') NOT NULL DEFAULT 'club', 
    PRIMARY KEY(`team_id`),
    FOREIGN KEY(`team_country_id`) REFERENCES `Country` (`country_id`)
);

CREATE TABLE `National`(
    `national_team_id` varchar(255) NOT NULL,
    PRIMARY KEY(`national_team_id`),
    FOREIGN KEY(`national_team_id`) REFERENCES `Team`(`team_id`)
);

CREATE TABLE `Club`(
    `club_team_id` varchar(255) NOT NULL,
    `club_rating` int NOT NULL DEFAULT 1,
    `club_money` double NOT NULL DEFAULT 0.0,
    `club_league_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY(`club_team_id`),
    FOREIGN KEY(`club_team_id`) REFERENCES `Team`(`team_id`),
    FOREIGN KEY(`club_league_id`) REFERENCES `League` (`league_id`)
);

CREATE TABLE `Player`(
	`player_id` int NOT NULL,
    `player_name` varchar(255) DEFAULT "",
    `player_lastname` varchar(255) NOT NULL,
    `player_country_id` varchar(3) NOT NULL DEFAULT "ALL",
    `player_positions` varchar(255) NOT NULL DEFAULT "",
    `player_rating` int NOT NULL DEFAULT 50,
    `player_potential` int NOT NULL DEFAULT 0,
    `player_birth_year` int NOT NULL DEFAULT 1980,
    `player_height` int NOT NULL DEFAULT 0,
    `player_stamina` int NOT NULL DEFAULT 100,
    `player_contract_club_id` varchar(255) DEFAULT NULL,
    `player_contract_salary` double DEFAULT NULL,
    `player_contract_sign_date` int DEFAULT NULL,
    `player_contract_expire_date` int DEFAULT NULL,
    `player_contract_role` varchar(3) DEFAULT NULL,
    `player_contract_number` int DEFAULT NULL,
    `player_arrangement_national_id` varchar(255) DEFAULT NULL,
    `player_arrangement_role` varchar(3) DEFAULT NULL,
    `player_arrangement_number` int DEFAULT NULL,
    PRIMARY KEY(`player_id`),
    FOREIGN KEY(`player_country_id`) REFERENCES `Country` (`country_id`),
    FOREIGN KEY(`player_contract_club_id`) REFERENCES `Club` (`club_team_id`),
    FOREIGN KEY(`player_arrangement_national_id`) REFERENCES `National` (`national_team_id`)
);
/*

SELECT 
    t.id, 
    t.name, 
    t.country_id, 
    c.rating, 
    c.budget, 
    c.salary_budget
FROM Team t
JOIN Club c ON t.id = c.team_id;

*/