-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 18, 2018 at 12:54 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `crop_watch`
--

-- --------------------------------------------------------

--
-- Table structure for table `complain`
--

CREATE TABLE `complain` (
  `c_id` int(255) NOT NULL,
  `c_title` varchar(50) NOT NULL,
  `c_body` varchar(500) NOT NULL,
  `date_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `c_uid` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `complain`
--

INSERT INTO `complain` (`c_id`, `c_title`, `c_body`, `date_time`, `c_uid`) VALUES
(1, 'abc', 'sdshd', '2018-10-16 07:29:53', 1),
(2, 'abc', 'xyz', '2018-10-16 12:55:29', 1),
(3, 'abc', 'ghbbb', '2018-10-16 12:56:04', 1);

-- --------------------------------------------------------

--
-- Table structure for table `data`
--

CREATE TABLE `data` (
  `d_id` int(255) NOT NULL,
  `temperature_data` float NOT NULL,
  `humidity_data` float NOT NULL,
  `moisture_data` float NOT NULL,
  `date_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `u_id` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `data`
--

INSERT INTO `data` (`d_id`, `temperature_data`, `humidity_data`, `moisture_data`, `date_time`, `u_id`) VALUES
(1, 1, 1, 1, '2018-10-16 07:29:04', 1);

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `f_id` int(255) NOT NULL,
  `f_title` varchar(50) NOT NULL,
  `f_body` varchar(500) NOT NULL,
  `date_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `f_uid` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`f_id`, `f_title`, `f_body`, `date_time`, `f_uid`) VALUES
(1, 'a', 'dhfjf', '2018-10-16 12:25:39', 1),
(2, 'a', 'dhfjf', '2018-10-16 12:25:41', 1),
(3, 'a', 'dhfjf', '2018-10-16 12:26:55', 1),
(4, 'a', 'dhfjf', '2018-10-16 12:26:58', 1),
(5, 'a', 'dhfjf', '2018-10-16 12:33:31', 1),
(6, 'a', 'dhfjf', '2018-10-16 12:33:34', 1),
(7, '1', '1', '2018-10-16 12:36:27', 1),
(8, '1', '1', '2018-10-16 12:49:37', 1),
(9, 'bdnd', 'hshsj', '2018-10-16 12:51:42', 1),
(10, 'bdnd', 'hshsj', '2018-10-16 12:51:45', 1),
(11, 'bdnd', 'hshsjkfjvj', '2018-10-16 12:52:12', 1),
(12, 'bdnd', 'hshsjkfjvj', '2018-10-16 12:52:15', 1),
(13, '1', '1', '2018-10-16 12:53:18', 1),
(14, 'bdnd', 'hshsjkfjvj', '2018-10-16 12:53:30', 1),
(15, 'bdnd', 'hshsjkfjvj', '2018-10-16 12:53:45', 1),
(16, 'bdnd', 'j', '2018-10-16 12:53:57', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `u_id` int(255) NOT NULL,
  `u_name` varchar(100) NOT NULL,
  `u_email` varchar(50) NOT NULL,
  `u_pass` varchar(50) NOT NULL,
  `u_mobileno` varchar(13) NOT NULL,
  `u_address` varchar(500) NOT NULL,
  `u_role` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`u_id`, `u_name`, `u_email`, `u_pass`, `u_mobileno`, `u_address`, `u_role`) VALUES
(1, 'abc', 'kirtandudhatra.28@gmail.com', 'kirtan@123', '8141192497', 'nadiad', 1),
(2, 'admin', 'admin@admin.com', 'admin@123', '9898989898', 'nadiad', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `complain`
--
ALTER TABLE `complain`
  ADD PRIMARY KEY (`c_id`),
  ADD KEY `complain_fk` (`c_uid`);

--
-- Indexes for table `data`
--
ALTER TABLE `data`
  ADD PRIMARY KEY (`d_id`),
  ADD KEY `data_fk` (`u_id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`f_id`),
  ADD KEY `feedback_fk` (`f_uid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`u_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `complain`
--
ALTER TABLE `complain`
  MODIFY `c_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `data`
--
ALTER TABLE `data`
  MODIFY `d_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `f_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `u_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `complain`
--
ALTER TABLE `complain`
  ADD CONSTRAINT `complain_fk` FOREIGN KEY (`c_uid`) REFERENCES `users` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `data`
--
ALTER TABLE `data`
  ADD CONSTRAINT `data_fk` FOREIGN KEY (`u_id`) REFERENCES `users` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_fk` FOREIGN KEY (`f_uid`) REFERENCES `users` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
