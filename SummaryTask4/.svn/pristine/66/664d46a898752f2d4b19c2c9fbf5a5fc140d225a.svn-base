-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Фев 09 2018 г., 00:09
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
(6, 'GAZ', 2, 1),
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
(4, 4, '2018-02-01');

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
(15, 31, 8, '2018-01-03 00:00:00');

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
(40, '2018-02-01 00:00:00', 34, 3, 1);

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
(8, '2018-01-28 00:00:00', 1, 'Kiev', '2018-01-01 00:00:00', 'Poltava', 2),
(13, '2018-01-30 00:00:00', 4, 'Kharkov', '2018-01-31 00:00:00', 'Kiev', 2),
(28, '2018-01-31 00:00:00', 1, 'Uganda', '2018-01-31 00:00:00', 'Kiev', 2),
(29, '2018-01-31 00:00:00', 1, 'Harvard', '2018-01-01 00:00:00', 'London', 2),
(30, '2018-02-01 00:00:00', 4, 'Poland', '2018-01-02 00:00:00', 'England', 2),
(31, '2018-02-01 00:00:00', 4, 'Kharkov', '2018-01-02 00:00:00', 'Poltava', 2),
(32, '2018-02-01 00:00:00', 4, 'Hungary', '2018-01-03 00:00:00', 'Austria', 2),
(33, '2018-02-01 00:00:00', 1, 'Uganda', '2018-01-02 00:00:00', 'Kiev', 2),
(34, '2018-02-01 00:00:00', 1, 'Kiev', '2018-01-02 00:00:00', 'Kharkov', 2);

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
  `role_id` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`user_id`, `login`, `password`, `first_name`, `last_name`, `role_id`) VALUES
(1, 'olddeer', 'qwe', 'Alex', 'Sut', 2),
(2, 'lolipop', 'qwerr', 'Denis', 'Pogreb', 3),
(3, 'petrov', 'qwe', 'Jack', 'Petrov', 2),
(4, 'obama', 'qwer', 'Ivan', 'Ivanov', 3);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `completed_requests`
--
ALTER TABLE `completed_requests`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT для таблицы `requests`
--
ALTER TABLE `requests`
  MODIFY `request_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT для таблицы `trips`
--
ALTER TABLE `trips`
  MODIFY `trip_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `autos`
--
ALTER TABLE `autos`
  ADD CONSTRAINT `autos_ibfk_1` FOREIGN KEY (`auto_type`) REFERENCES `auto_types` (`auto_type_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `autos_ibfk_2` FOREIGN KEY (`auto_status`) REFERENCES `auto_status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

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
