-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Сен 21 2022 г., 08:23
-- Версия сервера: 5.7.39
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `spring`
--

-- --------------------------------------------------------

--
-- Структура таблицы `abonement`
--

CREATE TABLE `abonement` (
  `id` bigint(20) NOT NULL,
  `date_of_issue` varchar(255) DEFAULT NULL,
  `expirtion_date` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `age_limit`
--

CREATE TABLE `age_limit` (
  `id` bigint(20) NOT NULL,
  `age_limit` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `author`
--

CREATE TABLE `author` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `secondname` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `book`
--

CREATE TABLE `book` (
  `id` bigint(20) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pages` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `book_age_limit_id` bigint(20) DEFAULT NULL,
  `edition_id` bigint(20) DEFAULT NULL,
  `publisher_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `book_author`
--

CREATE TABLE `book_author` (
  `book_id` bigint(20) NOT NULL,
  `author_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `book_branch`
--

CREATE TABLE `book_branch` (
  `branch_id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `book_genre`
--

CREATE TABLE `book_genre` (
  `genre_id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `branch`
--

CREATE TABLE `branch` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `edition`
--

CREATE TABLE `edition` (
  `id` bigint(20) NOT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `employee`
--

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `secondname` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `employee_role`
--

CREATE TABLE `employee_role` (
  `employee_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `genre`
--

CREATE TABLE `genre` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `post`
--

CREATE TABLE `post` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `publisher`
--

CREATE TABLE `publisher` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `sheet`
--

CREATE TABLE `sheet` (
  `id` bigint(20) NOT NULL,
  `date_of_issue` varchar(255) DEFAULT NULL,
  `return_date` varchar(255) DEFAULT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `birth` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `passport` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `secondname` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `abonement_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `abonement`
--
ALTER TABLE `abonement`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `age_limit`
--
ALTER TABLE `age_limit`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrqh64vtkedd8xa2owfxbtgmh8` (`book_age_limit_id`),
  ADD KEY `FKg3kusjntrbh2ssj1vp4gn0vpb` (`edition_id`),
  ADD KEY `FKgtvt7p649s4x80y6f4842pnfq` (`publisher_id`);

--
-- Индексы таблицы `book_author`
--
ALTER TABLE `book_author`
  ADD KEY `FKbjqhp85wjv8vpr0beygh6jsgo` (`author_id`),
  ADD KEY `FKhwgu59n9o80xv75plf9ggj7xn` (`book_id`);

--
-- Индексы таблицы `book_branch`
--
ALTER TABLE `book_branch`
  ADD KEY `FK5e8pbsunuyfw7svnvlawi8aa6` (`book_id`),
  ADD KEY `FK1oh1m6snqougd0f09rwsqx935` (`branch_id`);

--
-- Индексы таблицы `book_genre`
--
ALTER TABLE `book_genre`
  ADD KEY `FK52evq6pdc5ypanf41bij5u218` (`book_id`),
  ADD KEY `FK8l6ops8exmjrlr89hmfow4mmo` (`genre_id`);

--
-- Индексы таблицы `branch`
--
ALTER TABLE `branch`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `edition`
--
ALTER TABLE `edition`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcm3b9d5fiw8s6co7lkw8c0lbs` (`post_id`);

--
-- Индексы таблицы `employee_role`
--
ALTER TABLE `employee_role`
  ADD KEY `FKo7rvk7ejtx29vru9cyhf7o68a` (`employee_id`);

--
-- Индексы таблицы `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `publisher`
--
ALTER TABLE `publisher`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `sheet`
--
ALTER TABLE `sheet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh63nj2osh2si9m1fj4m0bu74u` (`book_id`),
  ADD KEY `FK10rcl4f2kyf5ewi00mvprdq4q` (`employee_id`),
  ADD KEY `FKm0g3pt4p4ua3vs2x9cix06jyq` (`user_id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK85yia0mepv4v8hi99qmytvj4o` (`abonement_id`);

--
-- Индексы таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `abonement`
--
ALTER TABLE `abonement`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `age_limit`
--
ALTER TABLE `age_limit`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `author`
--
ALTER TABLE `author`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `book`
--
ALTER TABLE `book`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `branch`
--
ALTER TABLE `branch`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `edition`
--
ALTER TABLE `edition`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `employee`
--
ALTER TABLE `employee`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `genre`
--
ALTER TABLE `genre`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `post`
--
ALTER TABLE `post`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `publisher`
--
ALTER TABLE `publisher`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `sheet`
--
ALTER TABLE `sheet`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `FKg3kusjntrbh2ssj1vp4gn0vpb` FOREIGN KEY (`edition_id`) REFERENCES `edition` (`id`),
  ADD CONSTRAINT `FKgtvt7p649s4x80y6f4842pnfq` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`id`),
  ADD CONSTRAINT `FKrqh64vtkedd8xa2owfxbtgmh8` FOREIGN KEY (`book_age_limit_id`) REFERENCES `age_limit` (`id`);

--
-- Ограничения внешнего ключа таблицы `book_author`
--
ALTER TABLE `book_author`
  ADD CONSTRAINT `FKbjqhp85wjv8vpr0beygh6jsgo` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  ADD CONSTRAINT `FKhwgu59n9o80xv75plf9ggj7xn` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`);

--
-- Ограничения внешнего ключа таблицы `book_branch`
--
ALTER TABLE `book_branch`
  ADD CONSTRAINT `FK1oh1m6snqougd0f09rwsqx935` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`),
  ADD CONSTRAINT `FK5e8pbsunuyfw7svnvlawi8aa6` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`);

--
-- Ограничения внешнего ключа таблицы `book_genre`
--
ALTER TABLE `book_genre`
  ADD CONSTRAINT `FK52evq6pdc5ypanf41bij5u218` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  ADD CONSTRAINT `FK8l6ops8exmjrlr89hmfow4mmo` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`);

--
-- Ограничения внешнего ключа таблицы `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FKcm3b9d5fiw8s6co7lkw8c0lbs` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`);

--
-- Ограничения внешнего ключа таблицы `employee_role`
--
ALTER TABLE `employee_role`
  ADD CONSTRAINT `FKo7rvk7ejtx29vru9cyhf7o68a` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`);

--
-- Ограничения внешнего ключа таблицы `sheet`
--
ALTER TABLE `sheet`
  ADD CONSTRAINT `FK10rcl4f2kyf5ewi00mvprdq4q` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `FKh63nj2osh2si9m1fj4m0bu74u` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  ADD CONSTRAINT `FKm0g3pt4p4ua3vs2x9cix06jyq` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK85yia0mepv4v8hi99qmytvj4o` FOREIGN KEY (`abonement_id`) REFERENCES `abonement` (`id`);

--
-- Ограничения внешнего ключа таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
