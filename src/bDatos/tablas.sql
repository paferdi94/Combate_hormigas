-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 10-07-2016 a las 12:46:03
-- Versión del servidor: 5.5.42
-- Versión de PHP: 7.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de datos: `Hormigas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hormigas`
--

CREATE TABLE `hormigas` (
  `id` int(11) NOT NULL,
  `tipo` varchar(10) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `vida` double DEFAULT NULL,
  `fuerza` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `hormigas`
--
ALTER TABLE `hormigas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `hormigas`
--
ALTER TABLE `hormigas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;