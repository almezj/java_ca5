-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 28, 2023 at 12:12 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `java_ca5`
--

-- --------------------------------------------------------

--
-- Table structure for table `players`
--

CREATE TABLE `players` (
  `player_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  `points` int(11) NOT NULL,
  `birth_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `players`
--

INSERT INTO `players` (`player_id`, `first_name`, `last_name`, `country`, `points`, `birth_date`) VALUES
(1, 'Novak', 'Djokovic', 'SRB', 7070, '1987-05-22'),
(2, 'Carlos', 'Alcaraz', 'ESP', 6480, '2003-05-05'),
(3, 'Stefanos', 'Tsitsipas', 'GRE', 5940, '1998-08-12'),
(4, 'Casper', 'Ruud', 'NOR', 5515, '1998-12-22'),
(5, 'Andrey', 'Rublev', 'RUS', 3860, '1997-10-20'),
(6, 'Rafael', 'Nadal', 'ESP', 3815, '1986-06-03'),
(7, 'Taylor', 'Fritz', 'USA', 3660, '1997-10-28'),
(8, 'Daniil', 'Medvedev', 'RUS', 3805, '1996-02-11'),
(9, 'Felix', 'Auger-Aliassime', 'CAN', 3200, '2000-08-08'),
(10, 'Holger', 'Rune', 'NOR', 3161, '1997-06-20'),
(11, 'Hubert', 'Hurkacz', 'POL', 2995, '1997-02-11'),
(12, 'Jannik', 'Sinner', 'ITA', 2745, '2001-08-16'),
(13, 'Cameron', 'Norrie', 'GBR', 2615, '1995-08-23'),
(14, 'Karen', 'Khachanov', 'RUS', 2470, '1996-05-21'),
(15, 'Frances', 'Tiafoe', 'USA', 2350, '1998-01-20'),
(16, 'Alexander', 'Zverev', 'GER', 2320, '1997-04-20'),
(17, 'Pablo Carenno', 'Busta', 'ESP', 2285, '1991-07-12'),
(18, 'Lorenzo', 'Musetti', 'ITA', 1855, '2002-03-03'),
(19, 'Nick', 'Kyrgios', 'AUS', 1825, '1995-04-27'),
(21, 'Josef', 'Zemlicka', 'CZE', 10000, '2001-12-22');

-- --------------------------------------------------------

--
-- Table structure for table `racquet_specifications`
--

CREATE TABLE `racquet_specifications` (
  `spec_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `head_size` float NOT NULL,
  `weight` float NOT NULL,
  `string_pattern` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `racquet_specifications`
--

INSERT INTO `racquet_specifications` (`spec_id`, `player_id`, `brand`, `model`, `head_size`, `weight`, `string_pattern`) VALUES
(1, 1, 'Head', 'PT 346.1 Pro Stock', 95, 354, '18x19'),
(2, 2, 'Babolat', 'Pure Aero VS 2020', 98, 320, '16x20'),
(3, 3, 'Wilson', 'Blade 98 v4', 98, 335, '18x20'),
(4, 4, 'Yonex', 'Ezone DR 100+', 100, 330, '16x19'),
(5, 5, 'Head', 'Gravity Pro', 100, 358, '18x20'),
(6, 6, 'Babolat', 'AeroPro Drive Original', 100, 340, '16x19'),
(7, 7, 'Head', 'IG Radical MP', 98, 315, '18x20'),
(8, 8, 'Technifibre', 'T Fight Dynacore 95', 95, 354, '18x19'),
(9, 9, 'Babolat', 'Pure Aero VS', 98, 330, '16x20'),
(10, 10, 'Babolat', 'Pure Aero VS 2020', 98, 337, '16x20'),
(11, 11, 'Yonex', 'Vcore Pro', 97, 323, '16x19'),
(12, 12, 'Head', 'Speed MP', 100, 325, '16x19'),
(13, 13, 'Babolat', 'Pure Control', 98, 323, '16x19'),
(14, 14, 'Wilson', 'Blade 98', 98, 347, '18x20'),
(15, 15, 'Yonex', 'Vcore Pro 97', 97, 326, '16x19'),
(16, 16, 'Head', 'Head Graphene 360+ Gravity Pro', 100, 332, '18x20'),
(17, 17, 'Wilson', 'Burn', 98, 346, '18x20'),
(18, 18, 'Head', 'Boom Pro', 98, 323, '16x19'),
(19, 19, 'Yonex', 'Xi 98', 98, 343, '16x19');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `players`
--
ALTER TABLE `players`
  ADD PRIMARY KEY (`player_id`);

--
-- Indexes for table `racquet_specifications`
--
ALTER TABLE `racquet_specifications`
  ADD PRIMARY KEY (`spec_id`),
  ADD KEY `player_id` (`player_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `players`
--
ALTER TABLE `players`
  MODIFY `player_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `racquet_specifications`
--
ALTER TABLE `racquet_specifications`
  MODIFY `spec_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `racquet_specifications`
--
ALTER TABLE `racquet_specifications`
  ADD CONSTRAINT `racquet_specifications_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `players` (`player_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
