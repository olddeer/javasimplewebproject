-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Апр 14 2018 г., 15:07
-- Версия сервера: 10.1.29-MariaDB
-- Версия PHP: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `finaltask`
--

DELIMITER $$
--
-- Процедуры
--
CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `change_account_login` (IN `log` VARCHAR(100), IN `us_id` INT(100))  NO SQL
BEGIN
    DECLARE
        LOG2 VARCHAR(20); DECLARE isLog BOOLEAN DEFAULT 0; DECLARE log_cur CURSOR FOR
    SELECT
        users.login
    FROM
        `users`; DECLARE CONTINUE
    HANDLER FOR SQLSTATE
        '02000'
    SET
        @done = 1; OPEN log_cur; REPEAT FETCH log_cur
    INTO LOG2; IF LOG = LOG2 THEN
SET
    isLog = 1;
END IF; UNTIL @done
END REPEAT; CLOSE log_cur; IF isLog = 0 THEN
UPDATE
    users
SET
    login = LOG
WHERE
    users.user_id = us_id;
END IF;
END$$

CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `gets()` (IN `arr` VARCHAR(100))  NO SQL
BEGIN

SET @mas="i like your mum";

END$$

CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `gets2` (IN `qwe` INT(11))  NO SQL
SET @mas=5$$

CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `gets23` (IN `qw22e` INT(11))  NO SQL
SET @mas=5$$

CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `insert_trip` (`date_creation` DATETIME(6), `date_set_off` DATETIME(6), `destination` VARCHAR(10), `from` VARCHAR(10), `status_id` INTEGER(100), `dispatcher_id` INTEGER(100))  BEGIN 

 START TRANSACTION;
 INSERT INTO `trips` VALUES(NULL,date_creation,status_id,destination,date_set_off,`from`,dispatcher_id);
 IF destination = `from` THEN
 ROLLBACK;
 ELSE 
 COMMIT;
 END IF;

 END$$

CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `insert_user` (`first_name` VARCHAR(100), `last_name` VARCHAR(100), `login` VARCHAR(100), `PASSWORD` VARCHAR(100), `role_id` INTEGER(100))  NO SQL
BEGIN
DECLARE LOG2 VARCHAR(20) ;
   DECLARE log_cur CURSOR FOR
SELECT
    `users`.login
FROM
    `users` ;
    DECLARE EXIT
HANDLER FOR SQLEXCEPTION
BEGIN
    ROLLBACK
        ;
END ; DECLARE EXIT
HANDLER FOR SQLWARNING
BEGIN
    ROLLBACK
        ;
END ;

   DECLARE CONTINUE
HANDLER FOR SQLSTATE
    '02000'
SET
    @done = 1 ; 
    OPEN log_cur ; 
    REPEAT FETCH log_cur
INTO LOG2 ;
 UNTIL @done
END REPEAT ; CLOSE log_cur ;
INSERT INTO users
VALUES(
    NULL,
    login,
    PASSWORD,
    first_name,
    last_name,
    role_id
) ;

  
END$$

CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `insert_user2` (IN `first_name` VARCHAR(100), IN `last_name` VARCHAR(100), IN `login` VARCHAR(100), IN `password` VARCHAR(100), IN `role_id` INT(100))  NO SQL
BEGIN
DECLARE LOG2 VARCHAR(20) DEFAULT 0;
DECLARE  isLog INTEGER(20) DEFAULT 0;
DECLARE log_cur CURSOR FOR SELECT `users`.login FROM `users` ; 
DECLARE EXIT HANDLER FOR SQLEXCEPTION 
BEGIN
ROLLBACK; 
END; 

DECLARE EXIT HANDLER FOR SQLWARNING 
BEGIN 
ROLLBACK;
END; 

DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET @done = 1 ;
OPEN log_cur ; 
REPEAT FETCH log_cur INTO LOG2 ;
IF login=LOG2 THEN 
SET isLog=1;
END IF; 
UNTIL @done END REPEAT;
START TRANSACTION;
INSERT INTO users VALUES( NULL, login, `password`, first_name, last_name, role_id );
IF isLog=0 THEN
COMMIT;
ELSE 
ROLLBACK;
END IF;

CLOSE log_cur ; 
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Структура таблицы `autos`
--

CREATE TABLE `autos` (
  `auto_id` int(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `auto_type` int(100) NOT NULL,
  `auto_status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `autos`
--

INSERT INTO `autos` (`auto_id`, `name`, `auto_type`, `auto_status`) VALUES
(3, 'Ferari', 3, 3),
(4, 'Mercedes sprinter', 2, 3),
(5, 'Lada', 3, 2),
(6, 'GAZ', 2, 3),
(7, 'KAMAZ', 1, 1),
(8, 'BMW', 3, 3),
(9, 'Porche', 3, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `auto_status`
--

CREATE TABLE `auto_status` (
  `id` int(100) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `auto_status`
--

INSERT INTO `auto_status` (`id`, `name`) VALUES
(1, 'Ready'),
(2, 'Busy'),
(3, 'Fallen out');

-- --------------------------------------------------------

--
-- Структура таблицы `auto_types`
--

CREATE TABLE `auto_types` (
  `auto_type_id` int(100) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `auto_types`
--

INSERT INTO `auto_types` (`auto_type_id`, `name`) VALUES
(1, 'Truck'),
(2, 'Van'),
(3, 'Sedan');

-- --------------------------------------------------------

--
-- Структура таблицы `broken_autos`
--

CREATE TABLE `broken_autos` (
  `id` int(11) NOT NULL,
  `auto_id` int(11) NOT NULL,
  `date_of_broken` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `broken_autos`
--

INSERT INTO `broken_autos` (`id`, `auto_id`, `date_of_broken`) VALUES
(1, 3, '2018-02-01'),
(2, 5, '2018-02-01'),
(3, 8, '2018-01-03'),
(4, 4, '2018-02-01'),
(5, 6, '2018-01-09');

-- --------------------------------------------------------

--
-- Структура таблицы `completed_requests`
--

CREATE TABLE `completed_requests` (
  `id` int(100) NOT NULL,
  `request_id` int(100) NOT NULL,
  `auto_id` int(100) NOT NULL,
  `date_completed` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `completed_requests`
--

INSERT INTO `completed_requests` (`id`, `request_id`, `auto_id`, `date_completed`) VALUES
(12, 30, 3, '2018-01-31 00:00:00'),
(13, 39, 7, '2018-01-03 00:00:00'),
(14, 40, 3, '2018-01-02 00:00:00'),
(15, 31, 8, '2018-01-03 00:00:00'),
(16, 41, 6, '2018-01-09 00:00:00'),
(17, 42, 9, '2018-01-30 00:00:00');

-- --------------------------------------------------------

--
-- Структура таблицы `requests`
--

CREATE TABLE `requests` (
  `request_id` int(100) NOT NULL,
  `date_creation` datetime NOT NULL,
  `trip_id` int(100) NOT NULL,
  `auto_type_id` int(100) NOT NULL,
  `driver_id` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `requests`
--

INSERT INTO `requests` (`request_id`, `date_creation`, `trip_id`, `auto_type_id`, `driver_id`) VALUES
(30, '2018-01-31 00:00:00', 29, 3, 1),
(31, '2018-02-01 00:00:00', 28, 3, 1),
(32, '2018-02-01 00:00:00', 13, 3, 1),
(33, '2018-02-01 00:00:00', 30, 1, 1),
(35, '2018-02-01 00:00:00', 31, 2, 1),
(37, '2018-02-01 00:00:00', 32, 1, 1),
(38, '2018-02-01 00:00:00', 30, 1, 1),
(39, '2018-02-01 00:00:00', 33, 1, 1),
(40, '2018-02-01 00:00:00', 34, 3, 1),
(41, '2018-03-08 00:00:00', 35, 2, 1),
(42, '2018-03-29 00:00:00', 42, 3, 1),
(43, '2018-04-02 00:00:00', 43, 2, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `roles`
--

CREATE TABLE `roles` (
  `role_id` int(100) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `roles`
--

INSERT INTO `roles` (`role_id`, `name`) VALUES
(1, 'Administrator'),
(2, 'Driver'),
(3, 'Dispatcher');

-- --------------------------------------------------------

--
-- Структура таблицы `trips`
--

CREATE TABLE `trips` (
  `trip_id` int(100) NOT NULL,
  `date_creation` datetime NOT NULL,
  `status_id` int(100) NOT NULL,
  `destination` varchar(100) NOT NULL,
  `date_set_off` datetime NOT NULL,
  `from` varchar(100) NOT NULL,
  `dispatcher_id` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `trips`
--

INSERT INTO `trips` (`trip_id`, `date_creation`, `status_id`, `destination`, `date_set_off`, `from`, `dispatcher_id`) VALUES
(8, '2018-02-28 00:00:00', 1, 'Kiev', '2018-01-01 00:00:00', 'Poltava', 2),
(13, '2018-01-30 00:00:00', 4, 'Kharkov', '2018-01-31 00:00:00', 'Kiev', 2),
(28, '2018-01-31 00:00:00', 1, 'Uganda', '2018-01-31 00:00:00', 'Kiev', 2),
(29, '2018-01-31 00:00:00', 1, 'Harvard', '2018-01-01 00:00:00', 'London', 2),
(30, '2018-02-01 00:00:00', 4, 'Poland', '2018-01-02 00:00:00', 'England', 2),
(31, '2018-02-01 00:00:00', 4, 'Kharkov', '2018-01-02 00:00:00', 'Poltava', 2),
(32, '2018-02-01 00:00:00', 4, 'Hungary', '2018-01-03 00:00:00', 'Austria', 2),
(33, '2018-02-01 00:00:00', 1, 'Uganda', '2018-01-02 00:00:00', 'Kiev', 2),
(34, '2018-02-01 00:00:00', 1, 'Kiev', '2018-01-02 00:00:00', 'Kharkov', 2),
(35, '2018-03-08 00:00:00', 1, 'Poltava', '2018-01-09 00:00:00', 'KIev', 2),
(38, '2018-03-21 00:00:00', 1, 'Kyiv', '2018-03-22 00:00:00', 'Moscow', 4),
(41, '2009-03-02 00:00:00', 1, 'Poltava', '2010-04-03 00:00:00', 'Kyiv', 1),
(42, '2018-03-29 00:00:00', 1, 'Paris', '2018-01-30 00:00:00', 'Kharkov', 4),
(43, '2018-04-02 00:00:00', 4, 'Back', '2018-01-03 00:00:00', 'To', 4);

-- --------------------------------------------------------

--
-- Структура таблицы `trip_status`
--

CREATE TABLE `trip_status` (
  `status_id` int(100) NOT NULL,
  `type` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `trip_status`
--

INSERT INTO `trip_status` (`status_id`, `type`) VALUES
(1, 'Accept'),
(2, 'Reject'),
(3, 'Open'),
(4, 'In progress');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `user_id` int(100) NOT NULL,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `role_id` int(100) NOT NULL,
  `ban` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`user_id`, `login`, `password`, `first_name`, `last_name`, `role_id`, `ban`) VALUES
(1, 'olddeer', 'qwe', 'Alex', 'Sut', 2, 1),
(2, 'uganda', 'qwerr', 'Denis', 'Pogreb', 3, 1),
(3, 'petrov', 'qwe', 'Jack', 'Petrov', 2, 1),
(4, 'obama', 'qwer', 'Ivan', 'Ivanov', 3, 1),
(14, 'petrovvv', 'qwe', 'Alex', 'sut', 1, 1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `autos`
--
ALTER TABLE `autos`
  ADD PRIMARY KEY (`auto_id`),
  ADD KEY `auto_type` (`auto_type`),
  ADD KEY `auto_status` (`auto_status`);

--
-- Индексы таблицы `auto_status`
--
ALTER TABLE `auto_status`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `auto_types`
--
ALTER TABLE `auto_types`
  ADD PRIMARY KEY (`auto_type_id`);

--
-- Индексы таблицы `broken_autos`
--
ALTER TABLE `broken_autos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `auto_id` (`auto_id`);

--
-- Индексы таблицы `completed_requests`
--
ALTER TABLE `completed_requests`
  ADD PRIMARY KEY (`id`),
  ADD KEY `auto_id` (`auto_id`),
  ADD KEY `requset_id` (`request_id`);

--
-- Индексы таблицы `requests`
--
ALTER TABLE `requests`
  ADD PRIMARY KEY (`request_id`),
  ADD KEY `trip_id` (`trip_id`,`auto_type_id`,`driver_id`),
  ADD KEY `driver_id` (`driver_id`),
  ADD KEY `auto_type_id` (`auto_type_id`);

--
-- Индексы таблицы `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Индексы таблицы `trips`
--
ALTER TABLE `trips`
  ADD PRIMARY KEY (`trip_id`),
  ADD KEY `status_id` (`status_id`),
  ADD KEY `dispatcher_id` (`dispatcher_id`);

--
-- Индексы таблицы `trip_status`
--
ALTER TABLE `trip_status`
  ADD PRIMARY KEY (`status_id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `autos`
--
ALTER TABLE `autos`
  MODIFY `auto_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT для таблицы `auto_status`
--
ALTER TABLE `auto_status`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `auto_types`
--
ALTER TABLE `auto_types`
  MODIFY `auto_type_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `broken_autos`
--
ALTER TABLE `broken_autos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `completed_requests`
--
ALTER TABLE `completed_requests`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT для таблицы `requests`
--
ALTER TABLE `requests`
  MODIFY `request_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT для таблицы `trips`
--
ALTER TABLE `trips`
  MODIFY `trip_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `autos`
--
ALTER TABLE `autos`
  ADD CONSTRAINT `autos_ibfk_1` FOREIGN KEY (`auto_type`) REFERENCES `auto_types` (`auto_type_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `autos_ibfk_2` FOREIGN KEY (`auto_status`) REFERENCES `auto_status` (`id`);

--
-- Ограничения внешнего ключа таблицы `broken_autos`
--
ALTER TABLE `broken_autos`
  ADD CONSTRAINT `broken_autos_ibfk_1` FOREIGN KEY (`auto_id`) REFERENCES `autos` (`auto_id`);

--
-- Ограничения внешнего ключа таблицы `completed_requests`
--
ALTER TABLE `completed_requests`
  ADD CONSTRAINT `completed_requests_ibfk_1` FOREIGN KEY (`auto_id`) REFERENCES `autos` (`auto_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `completed_requests_ibfk_2` FOREIGN KEY (`request_id`) REFERENCES `requests` (`request_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `requests`
--
ALTER TABLE `requests`
  ADD CONSTRAINT `requests_ibfk_4` FOREIGN KEY (`trip_id`) REFERENCES `trips` (`trip_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `requests_ibfk_6` FOREIGN KEY (`driver_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `requests_ibfk_7` FOREIGN KEY (`auto_type_id`) REFERENCES `auto_types` (`auto_type_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `trips`
--
ALTER TABLE `trips`
  ADD CONSTRAINT `trips_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `trip_status` (`status_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `trips_ibfk_2` FOREIGN KEY (`dispatcher_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
