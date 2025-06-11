DROP SCHEMA IF EXISTS `db_fmsim`;
CREATE SCHEMA `db_fmsim`;
USE `db_fmsim`;

CREATE TABLE `Country`(
    `country_id` varchar(3) NOT NULL,
    `country_name` varchar(255) NOT NULL DEFAULT "",
    PRIMARY KEY(`country_id`)
);

CREATE TABLE `Season`(
    `season_id` int AUTO_INCREMENT NOT NULL,
    `season_year` int NOT NULL,
    PRIMARY KEY(`season_id`)
);

CREATE TABLE `League`(
	`league_id` varchar(255) NOT NULL,
    `league_name` varchar(255) NOT NULL DEFAULT "",
    `league_capacity` int NOT NULL DEFAULT 0,
    `league_relegation` int NOT NULL DEFAULT 0,
    `league_parent_league_id` varchar(255) NOT NULL DEFAULT "-",
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
    `club_salary_budget` double NOT NULL DEFAULT 0.0,
    `club_league_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY(`club_team_id`),
    FOREIGN KEY(`club_team_id`) REFERENCES `Team`(`team_id`),
    FOREIGN KEY(`club_league_id`) REFERENCES `League` (`league_id`)
);

CREATE TABLE `Contract`(
    `contract_id` int AUTO_INCREMENT NOT NULL,
    `contract_club_id` varchar(255) NOT NULL, 
    `contract_signDate` int NOT NULL DEFAULT 0,
    `contract_expireDate` int NOT NULL DEFAULT 0,
    `contract_salary` double NOT NULL DEFAULT 0.0,
    `contract_number` int NOT NULL DEFAULT 0,
    `contract_role` varchar(3) NOT NULL DEFAULT 'sub',
    PRIMARY KEY(`contract_id`),
    FOREIGN KEY(`contract_club_id`) REFERENCES `Club`(`club_team_id`)
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
    `player_contract_id` int DEFAULT NULL,
/*`player_national_id` varchar(255) DEFAULT NULL,  TO BE CHANGED TO player_arrangement_id AND Arrangement TABLE is missing*/
    PRIMARY KEY(`player_id`),
    FOREIGN KEY(`player_country_id`) REFERENCES `Country` (`country_id`),
    FOREIGN KEY(`player_contract_id`) REFERENCES `Contract` (`contract_id`)
);

CREATE TABLE `Tenure`(
    `tenure_id` int AUTO_INCREMENT NOT NULL,
    `tenure_player_id` int NOT NULL,
    `tenure_team_id` varchar(255) NOT NULL,
    `tenure_start` int NOT NULL,
    `tenure_end` int NOT NULL,
    `tenure_salary` double NOT NULL DEFAULT 1.0,
    `tenure_goals` int NOT NULL DEFAULT 0,
    PRIMARY KEY (`tenure_id`),
    FOREIGN KEY (`tenure_player_id`) REFERENCES `Player`(`player_id`),
    FOREIGN KEY (`tenure_team_id`) REFERENCES `Team`(`team_id`)
);

CREATE TABLE `LeagueSeason`(
    `leagueseason_id` int AUTO_INCREMENT NOT NULL,
    `leagueseason_league_id` varchar(255) NOT NULL,
    `leagueseason_season_id` int NOT NULL,
    `league_round` int NOT NULL DEFAULT 1,
    `league_cycle` int NOT NULL DEFAULT 1,
    PRIMARY KEY(`leagueseason_id`),
    FOREIGN KEY(`leagueseason_league_id`) REFERENCES `League`(`league_id`),
    FOREIGN KEY(`leagueseason_season_id`) REFERENCES `Season`(`season_id`),
    UNIQUE(`leagueseason_league_id`, `leagueseason_season_id`)
);

CREATE TABLE `LeagueParticipant`(
    `leagueparticipant_leagueseason_id` int AUTO_INCREMENT NOT NULL,
    `leagueparticipant_team_id` varchar(255) NOT NULL,
    `leagueparticipant_wins` int NOT NULL DEFAULT 0,
    `leagueparticipant_draws` int NOT NULL DEFAULT 0,
    `leagueparticipant_losses` int NOT NULL DEFAULT 0,
    `leagueparticipant_goals_scored` int NOT NULL DEFAULT 0,
    `leagueparticipant_goals_conceded` int NOT NULL DEFAULT 0,
    PRIMARY KEY(`leagueparticipant_leagueseason_id`,`leagueparticipant_team_id`),
    FOREIGN KEY(`leagueparticipant_leagueseason_id`) REFERENCES `LeagueSeason`(`leagueseason_id`),
    FOREIGN KEY(`leagueparticipant_team_id`) REFERENCES `Team`(`team_id`)
);

CREATE TABLE `Match`(
    `match_id` int AUTO_INCREMENT NOT NULL,
    `match_leagueseason_id` int NOT NULL,
    `match_host_id` varchar(255) DEFAULT NULL,
    `match_guest_id` varchar(255) DEFAULT NULL,
    `match_host_goals` int DEFAULT -1,
    `match_guest_goals` int DEFAULT -1,
    `match_is_neutral` boolean DEFAULT FALSE,
    `match_round` int NOT NULL,
    PRIMARY KEY (`match_id`),
    FOREIGN KEY (`match_leagueseason_id`) REFERENCES `LeagueSeason`(`leagueseason_id`),
    FOREIGN KEY (`match_host_id`) REFERENCES `Team`(`team_id`),
    FOREIGN KEY (`match_guest_id`) REFERENCES `Team`(`team_id`)
);

INSERT INTO Country (country_id, country_name) VALUES ('ALG', 'Algeria');
INSERT INTO Country (country_id, country_name) VALUES ('ANG', 'Angola');
INSERT INTO Country (country_id, country_name) VALUES ('BEN', 'Benin');
INSERT INTO Country (country_id, country_name) VALUES ('BOT', 'Botswana');
INSERT INTO Country (country_id, country_name) VALUES ('BFA', 'Burkina Faso');
INSERT INTO Country (country_id, country_name) VALUES ('BUR', 'Burundi');
INSERT INTO Country (country_id, country_name) VALUES ('CPV', 'Cabo Verde');
INSERT INTO Country (country_id, country_name) VALUES ('CMR', 'Cameroon');
INSERT INTO Country (country_id, country_name) VALUES ('CAR', 'Central African Republic');
INSERT INTO Country (country_id, country_name) VALUES ('CHA', 'Chad');
INSERT INTO Country (country_id, country_name) VALUES ('KOM', 'Comoros');
INSERT INTO Country (country_id, country_name) VALUES ('CNG', 'Congo');
INSERT INTO Country (country_id, country_name) VALUES ('DRC', 'Democratic Republic of the Congo');
INSERT INTO Country (country_id, country_name) VALUES ('DJI', 'Djibouti');
INSERT INTO Country (country_id, country_name) VALUES ('EGY', 'Egypt');
INSERT INTO Country (country_id, country_name) VALUES ('EGU', 'Equatorial Guinea');
INSERT INTO Country (country_id, country_name) VALUES ('ERI', 'Eritrea');
INSERT INTO Country (country_id, country_name) VALUES ('ESW', 'Eswatini');
INSERT INTO Country (country_id, country_name) VALUES ('ETY', 'Ethiopia');
INSERT INTO Country (country_id, country_name) VALUES ('GAB', 'Gabon');
INSERT INTO Country (country_id, country_name) VALUES ('GAM', 'Gambia');
INSERT INTO Country (country_id, country_name) VALUES ('GHA', 'Ghana');
INSERT INTO Country (country_id, country_name) VALUES ('GUI', 'Guinea');
INSERT INTO Country (country_id, country_name) VALUES ('GUB', 'Guinea-Bissau');
INSERT INTO Country (country_id, country_name) VALUES ('CIV', 'Ivory Coast');
INSERT INTO Country (country_id, country_name) VALUES ('KEN', 'Kenya');
INSERT INTO Country (country_id, country_name) VALUES ('LES', 'Lesotho');
INSERT INTO Country (country_id, country_name) VALUES ('LBR', 'Liberia');
INSERT INTO Country (country_id, country_name) VALUES ('LBY', 'Libya');
INSERT INTO Country (country_id, country_name) VALUES ('MDG', 'Madagascar');
INSERT INTO Country (country_id, country_name) VALUES ('MLW', 'Malawi');
INSERT INTO Country (country_id, country_name) VALUES ('MLI', 'Mali');
INSERT INTO Country (country_id, country_name) VALUES ('MTQ', 'Martinique');
INSERT INTO Country (country_id, country_name) VALUES ('MAU', 'Mauritania');
INSERT INTO Country (country_id, country_name) VALUES ('MUS', 'Mauritius');
INSERT INTO Country (country_id, country_name) VALUES ('MOR', 'Morocco');
INSERT INTO Country (country_id, country_name) VALUES ('MOZ', 'Mozambique');
INSERT INTO Country (country_id, country_name) VALUES ('NMB', 'Namibia');
INSERT INTO Country (country_id, country_name) VALUES ('NGE', 'Niger');
INSERT INTO Country (country_id, country_name) VALUES ('NGR', 'Nigeria');
INSERT INTO Country (country_id, country_name) VALUES ('RWA', 'Rwanda');
INSERT INTO Country (country_id, country_name) VALUES ('STP', 'Sao Tome and Principe');
INSERT INTO Country (country_id, country_name) VALUES ('SEN', 'Senegal');
INSERT INTO Country (country_id, country_name) VALUES ('SEY', 'Seychelles');
INSERT INTO Country (country_id, country_name) VALUES ('SRL', 'Sierra Leone');
INSERT INTO Country (country_id, country_name) VALUES ('SOM', 'Somalia');
INSERT INTO Country (country_id, country_name) VALUES ('RSA', 'South Africa');
INSERT INTO Country (country_id, country_name) VALUES ('SSD', 'South Sudan');
INSERT INTO Country (country_id, country_name) VALUES ('SUD', 'Sudan');
INSERT INTO Country (country_id, country_name) VALUES ('TAN', 'Tanzania');
INSERT INTO Country (country_id, country_name) VALUES ('TOG', 'Togo');
INSERT INTO Country (country_id, country_name) VALUES ('TUN', 'Tunisia');
INSERT INTO Country (country_id, country_name) VALUES ('UGA', 'Uganda');
INSERT INTO Country (country_id, country_name) VALUES ('ZAM', 'Zambia');
INSERT INTO Country (country_id, country_name) VALUES ('ZBW', 'Zimbabwe');
INSERT INTO Country (country_id, country_name) VALUES ('ARM', 'Armenia');
INSERT INTO Country (country_id, country_name) VALUES ('AZE', 'Azerbaijan');
INSERT INTO Country (country_id, country_name) VALUES ('BHR', 'Bahrain');
INSERT INTO Country (country_id, country_name) VALUES ('BNG', 'Bangladesh');
INSERT INTO Country (country_id, country_name) VALUES ('CHN', 'China');
INSERT INTO Country (country_id, country_name) VALUES ('GEO', 'Georgia');
INSERT INTO Country (country_id, country_name) VALUES ('IND', 'India');
INSERT INTO Country (country_id, country_name) VALUES ('IDN', 'Indonesia');
INSERT INTO Country (country_id, country_name) VALUES ('IRN', 'Iran');
INSERT INTO Country (country_id, country_name) VALUES ('IRQ', 'Iraq');
INSERT INTO Country (country_id, country_name) VALUES ('ISR', 'Israel');
INSERT INTO Country (country_id, country_name) VALUES ('JPN', 'Japan');
INSERT INTO Country (country_id, country_name) VALUES ('JOR', 'Jordan');
INSERT INTO Country (country_id, country_name) VALUES ('KAZ', 'Kazakhstan');
INSERT INTO Country (country_id, country_name) VALUES ('KYR', 'Kyrgyzstan');
INSERT INTO Country (country_id, country_name) VALUES ('LAO', 'Laos');
INSERT INTO Country (country_id, country_name) VALUES ('LBN', 'Lebanon');
INSERT INTO Country (country_id, country_name) VALUES ('MAL', 'Malaysia');
INSERT INTO Country (country_id, country_name) VALUES ('MNG', 'Mongolia');
INSERT INTO Country (country_id, country_name) VALUES ('MYA', 'Myanmar');
INSERT INTO Country (country_id, country_name) VALUES ('NEP', 'Nepal');
INSERT INTO Country (country_id, country_name) VALUES ('PRK', 'North Korea');
INSERT INTO Country (country_id, country_name) VALUES ('OMN', 'Oman');
INSERT INTO Country (country_id, country_name) VALUES ('PAK', 'Pakistan');
INSERT INTO Country (country_id, country_name) VALUES ('PAL', 'Palestine');
INSERT INTO Country (country_id, country_name) VALUES ('PHI', 'Philippines');
INSERT INTO Country (country_id, country_name) VALUES ('QAT', 'Qatar');
INSERT INTO Country (country_id, country_name) VALUES ('KSA', 'Saudi Arabia');
INSERT INTO Country (country_id, country_name) VALUES ('SGP', 'Singapore');
INSERT INTO Country (country_id, country_name) VALUES ('KOR', 'South Korea');
INSERT INTO Country (country_id, country_name) VALUES ('SYR', 'Syria');
INSERT INTO Country (country_id, country_name) VALUES ('TWN', 'Taiwan');
INSERT INTO Country (country_id, country_name) VALUES ('TAJ', 'Tajikistan');
INSERT INTO Country (country_id, country_name) VALUES ('THA', 'Thailand');
INSERT INTO Country (country_id, country_name) VALUES ('TKM', 'Turkmenistan');
INSERT INTO Country (country_id, country_name) VALUES ('UAE', 'United Arab Emirates');
INSERT INTO Country (country_id, country_name) VALUES ('UZB', 'Uzbekistan');
INSERT INTO Country (country_id, country_name) VALUES ('VIE', 'Vietnam');
INSERT INTO Country (country_id, country_name) VALUES ('YEM', 'Yemen');
INSERT INTO Country (country_id, country_name) VALUES ('ALL', 'World');
INSERT INTO Country (country_id, country_name) VALUES ('ALB', 'Albania');
INSERT INTO Country (country_id, country_name) VALUES ('AND', 'Andorra');
INSERT INTO Country (country_id, country_name) VALUES ('AUT', 'Austria');
INSERT INTO Country (country_id, country_name) VALUES ('BLR', 'Belarus');
INSERT INTO Country (country_id, country_name) VALUES ('BEL', 'Belgium');
INSERT INTO Country (country_id, country_name) VALUES ('BIH', 'Bosnia and Herzegovina');
INSERT INTO Country (country_id, country_name) VALUES ('BUL', 'Bulgaria');
INSERT INTO Country (country_id, country_name) VALUES ('CRO', 'Croatia');
INSERT INTO Country (country_id, country_name) VALUES ('CYP', 'Cyprus');
INSERT INTO Country (country_id, country_name) VALUES ('CZE', 'Czech Republic');
INSERT INTO Country (country_id, country_name) VALUES ('DEN', 'Denmark');
INSERT INTO Country (country_id, country_name) VALUES ('ENG', 'England');
INSERT INTO Country (country_id, country_name) VALUES ('EST', 'Estonia');
INSERT INTO Country (country_id, country_name) VALUES ('FRI', 'Faroe Islands');
INSERT INTO Country (country_id, country_name) VALUES ('FIN', 'Finland');
INSERT INTO Country (country_id, country_name) VALUES ('FRA', 'France');
INSERT INTO Country (country_id, country_name) VALUES ('GER', 'Germany');
INSERT INTO Country (country_id, country_name) VALUES ('GRE', 'Greece');
INSERT INTO Country (country_id, country_name) VALUES ('HUN', 'Hungary');
INSERT INTO Country (country_id, country_name) VALUES ('ISL', 'Iceland');
INSERT INTO Country (country_id, country_name) VALUES ('IRL', 'Ireland');
INSERT INTO Country (country_id, country_name) VALUES ('ITA', 'Italy');
INSERT INTO Country (country_id, country_name) VALUES ('LAT', 'Latvia');
INSERT INTO Country (country_id, country_name) VALUES ('LIE', 'Liechtenstein');
INSERT INTO Country (country_id, country_name) VALUES ('LTU', 'Lithuania');
INSERT INTO Country (country_id, country_name) VALUES ('LUX', 'Luxembourg');
INSERT INTO Country (country_id, country_name) VALUES ('MLT', 'Malta');
INSERT INTO Country (country_id, country_name) VALUES ('MDA', 'Moldova');
INSERT INTO Country (country_id, country_name) VALUES ('MON', 'Monaco');
INSERT INTO Country (country_id, country_name) VALUES ('MNE', 'Montenegro');
INSERT INTO Country (country_id, country_name) VALUES ('NED', 'Netherlands');
INSERT INTO Country (country_id, country_name) VALUES ('MKD', 'North Macedonia');
INSERT INTO Country (country_id, country_name) VALUES ('NIR', 'Northern Ireland');
INSERT INTO Country (country_id, country_name) VALUES ('NOR', 'Norway');
INSERT INTO Country (country_id, country_name) VALUES ('POL', 'Poland');
INSERT INTO Country (country_id, country_name) VALUES ('POR', 'Portugal');
INSERT INTO Country (country_id, country_name) VALUES ('ROU', 'Romania');
INSERT INTO Country (country_id, country_name) VALUES ('RUS', 'Russia');
INSERT INTO Country (country_id, country_name) VALUES ('SMR', 'San Marino');
INSERT INTO Country (country_id, country_name) VALUES ('SCO', 'Scotland');
INSERT INTO Country (country_id, country_name) VALUES ('SRB', 'Serbia');
INSERT INTO Country (country_id, country_name) VALUES ('SVK', 'Slovakia');
INSERT INTO Country (country_id, country_name) VALUES ('SVN', 'Slovenia');
INSERT INTO Country (country_id, country_name) VALUES ('ESP', 'Spain');
INSERT INTO Country (country_id, country_name) VALUES ('SWE', 'Sweden');
INSERT INTO Country (country_id, country_name) VALUES ('SUI', 'Switzerland');
INSERT INTO Country (country_id, country_name) VALUES ('TUR', 'Turkey');
INSERT INTO Country (country_id, country_name) VALUES ('UKR', 'Ukraine');
INSERT INTO Country (country_id, country_name) VALUES ('VAT', 'Vatican');
INSERT INTO Country (country_id, country_name) VALUES ('WAL', 'Wales');
INSERT INTO Country (country_id, country_name) VALUES ('BHM', 'Bahamas');
INSERT INTO Country (country_id, country_name) VALUES ('CAN', 'Canada');
INSERT INTO Country (country_id, country_name) VALUES ('CRC', 'Costa Rica');
INSERT INTO Country (country_id, country_name) VALUES ('CUB', 'Cuba');
INSERT INTO Country (country_id, country_name) VALUES ('CUR', 'Curacao');
INSERT INTO Country (country_id, country_name) VALUES ('DOM', 'Dominican Republic');
INSERT INTO Country (country_id, country_name) VALUES ('SLV', 'El Salvador');
INSERT INTO Country (country_id, country_name) VALUES ('HAI', 'Haiti');
INSERT INTO Country (country_id, country_name) VALUES ('HON', 'Honduras');
INSERT INTO Country (country_id, country_name) VALUES ('JAM', 'Jamaica');
INSERT INTO Country (country_id, country_name) VALUES ('MEX', 'Mexico');
INSERT INTO Country (country_id, country_name) VALUES ('NCR', 'Nicaragua');
INSERT INTO Country (country_id, country_name) VALUES ('PAN', 'Panama');
INSERT INTO Country (country_id, country_name) VALUES ('PUE', 'Puerto Rico');
INSERT INTO Country (country_id, country_name) VALUES ('TRI', 'Trinidad and Tobago');
INSERT INTO Country (country_id, country_name) VALUES ('USA', 'United States');
INSERT INTO Country (country_id, country_name) VALUES ('AUS', 'Australia');
INSERT INTO Country (country_id, country_name) VALUES ('FJI', 'Fiji');
INSERT INTO Country (country_id, country_name) VALUES ('GUA', 'Guam');
INSERT INTO Country (country_id, country_name) VALUES ('NZE', 'New Zealand');
INSERT INTO Country (country_id, country_name) VALUES ('SAM', 'Samoa');
INSERT INTO Country (country_id, country_name) VALUES ('ARG', 'Argentina');
INSERT INTO Country (country_id, country_name) VALUES ('BOL', 'Bolivia');
INSERT INTO Country (country_id, country_name) VALUES ('BRA', 'Brazil');
INSERT INTO Country (country_id, country_name) VALUES ('CHI', 'Chile');
INSERT INTO Country (country_id, country_name) VALUES ('COL', 'Colombia');
INSERT INTO Country (country_id, country_name) VALUES ('ECU', 'Ecuador');
INSERT INTO Country (country_id, country_name) VALUES ('GUY', 'Guyana');
INSERT INTO Country (country_id, country_name) VALUES ('PAR', 'Paraguay');
INSERT INTO Country (country_id, country_name) VALUES ('PER', 'Peru');
INSERT INTO Country (country_id, country_name) VALUES ('SUR', 'Suriname');
INSERT INTO Country (country_id, country_name) VALUES ('URU', 'Uruguay');
INSERT INTO Country (country_id, country_name) VALUES ('VEN', 'Venezuela');

INSERT INTO League (league_id, league_name, league_capacity, league_relegation)
VALUES
('premierleague', 'Premier League', 14, 2),
('seriea', 'Serie A', 12, 2),
('laliga1', 'La Liga 1', 12, 2),
('bundesliga1', 'Bundesliga 1', 12, 2),
('ligue1', 'Ligue 1', 10, 1),
('belneta', 'Belnet A', 12, 2),
('primeira1', 'Primeira 1', 8, 1),
('rolexaleague', 'Rolex A League', 10, 1),
('russianleague1', 'Russian League 1', 10, 1),
('turkishleague1', 'Turkish League 1', 8, 1),
('scandinavianileague', 'Scandinavian I League', 12, 2),
('leaguealpha', 'League Alpha', 8, 1),
('premiershipa', 'Premiership A', 8, 1),
('eastsuperleague1', 'East Super League 1', 8, 1),
('adriaticleague1', 'Adriatic League 1', 12, 2),
('blacksealeague1', 'Black Sea League 1', 10, 1),
('slovencko1', 'Slovencko 1', 10, 1),
('premiyeraliga1', 'Premiyera Liga 1', 8, 1),
('balticaa', 'Baltica A', 8, 1),
('liges1', 'Liges 1', 8, 1),
('brasileiraoseriea', 'Brasileirao Serie A', 10, 1),
('argentineprimera', 'Argentine Primera', 8, 1),
('ligue2', 'Ligue 2', 8, 0),
('serieb', 'Serie B', 8, 0),
('bundesliga2', 'Bundesliga 2', 8, 0),
('laliga2', 'La Liga 2', 8, 0);

INSERT INTO Season (season_id) VALUES (2025);

INSERT INTO LeagueSeason (leagueseason_league_id, leagueseason_season_id)
VALUES
  ('premierleague', 1),
  ('seriea', 1),
  ('laliga1', 1),
  ('bundesliga1', 1),
  ('ligue1', 1),
  ('belneta', 1),
  ('primeira1', 1),
  ('rolexaleague', 1),
  ('russianleague1', 1),
  ('turkishleague1', 1),
  ('scandinavianileague', 1),
  ('leaguealpha', 1),
  ('premiershipa', 1),
  ('eastsuperleague1', 1),
  ('adriaticleague1', 1),
  ('blacksealeague1', 1),
  ('slovencko1', 1),
  ('premiyeraliga1', 1),
  ('balticaa', 1),
  ('liges1', 1),
  ('brasileiraoseriea', 1),
  ('argentineprimera', 1),
  ('ligue2', 1),
  ('serieb', 1),
  ('bundesliga2', 1),
  ('laliga2', 1);

  