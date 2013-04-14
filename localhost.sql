-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 14, 2013 at 09:10 AM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `surveyme`
--
CREATE DATABASE `surveyme` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `surveyme`;

-- --------------------------------------------------------

--
-- Table structure for table `surveyheader`
--

CREATE TABLE IF NOT EXISTS `surveyheader` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `desc` varchar(40) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `surveyheader`
--


-- --------------------------------------------------------

--
-- Table structure for table `surveyquestions`
--

CREATE TABLE IF NOT EXISTS `surveyquestions` (
  `id` int(10) NOT NULL,
  `qno` int(10) NOT NULL,
  `qtext` varchar(40) NOT NULL,
  `resp_type` int(20) NOT NULL,
  `resp_code_1` int(5) DEFAULT NULL,
  `resp_text_1` int(40) DEFAULT NULL,
  `resp_code_2` int(5) DEFAULT NULL,
  `resp_text_2` int(40) DEFAULT NULL,
  `resp_code_3` int(5) DEFAULT NULL,
  `resp_text_3` int(40) DEFAULT NULL,
  `resp_code_4` int(5) DEFAULT NULL,
  `resp_text_4` int(40) DEFAULT NULL,
  `resp_code_5` int(5) DEFAULT NULL,
  `resp_text_5` int(40) DEFAULT NULL,
  `resp_code_6` int(5) DEFAULT NULL,
  `resp_text_6` int(40) DEFAULT NULL,
  `resp_code_7` int(5) DEFAULT NULL,
  `resp_text_7` int(40) DEFAULT NULL,
  `resp_code_8` int(5) DEFAULT NULL,
  `resp_text_8` int(40) DEFAULT NULL,
  PRIMARY KEY (`id`,`qno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `surveyquestions`
--


-- --------------------------------------------------------

--
-- Table structure for table `surveyresponse`
--

CREATE TABLE IF NOT EXISTS `surveyresponse` (
  `user_id` varchar(20) NOT NULL,
  `survey_id` int(20) NOT NULL,
  `qno` int(10) NOT NULL,
  `resp_code` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `surveyresponse`
--


--
-- Constraints for dumped tables
--

--
-- Constraints for table `surveyquestions`
--
ALTER TABLE `surveyquestions`
  ADD CONSTRAINT `surveyquestions_ibfk_1` FOREIGN KEY (`id`) REFERENCES `surveyheader` (`id`);
