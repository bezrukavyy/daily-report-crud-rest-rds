CREATE TABLE `daily` (
  `date` date NOT NULL,
  `location` varchar(100) NOT NULL,
  `new_cases` int(11) DEFAULT NULL,
  `new_deaths` int(11) DEFAULT NULL,
  `total_cases` int(11) DEFAULT NULL,
  `total_deaths` int(11) DEFAULT NULL,
  `weekly_cases` decimal(10,0) DEFAULT NULL,
  `weekly_deaths` decimal(10,0) DEFAULT NULL,
  `biweekly_cases` decimal(10,0) DEFAULT NULL,
  `biweekly_deaths` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`date`,`location`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1