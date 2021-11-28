-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 16-Set-2020 às 03:11
-- Versão do servidor: 10.4.14-MariaDB
-- versão do PHP: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `ferramentaria`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `administradores`
--

CREATE TABLE `administradores` (
  `codigo` int(11) NOT NULL,
  `nome` varchar(50) CHARACTER SET utf8 NOT NULL,
  `sobrenome` varchar(50) CHARACTER SET utf8 NOT NULL,
  `nascimento` date NOT NULL,
  `sexo` varchar(11) CHARACTER SET utf8 NOT NULL,
  `telefone` int(10) NOT NULL,
  `celular` int(11) NOT NULL,
  `cpf` varchar(15) CHARACTER SET utf8 NOT NULL,
  `codigo_login` int(11) NOT NULL,
  `rua` varchar(100) CHARACTER SET utf8 NOT NULL,
  `numero` int(11) NOT NULL,
  `bairro` varchar(50) CHARACTER SET utf8 NOT NULL,
  `cep` int(11) NOT NULL,
  `cidade` varchar(100) CHARACTER SET utf8 NOT NULL,
  `estado` varchar(2) CHARACTER SET utf8 NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `administradores`
--

INSERT INTO `administradores` (`codigo`, `nome`, `sobrenome`, `nascimento`, `sexo`, `telefone`, `celular`, `cpf`, `codigo_login`, `rua`, `numero`, `bairro`, `cep`, `cidade`, `estado`) VALUES
(16, 'Admin', 'Master', '0000-00-00', 'M', 1138489485, 1189485834, '10201383748', 94, 'Oculto', 1, 'Oculto', 0, 'Oculto', 'SP'),
(25, 'Lucas', 'Sergio', '2000-01-15', 'MASCULINO', 1111111111, 2147483647, '11111111111', 118, 'Rua Hondo', 222, 'Jardim Oriente', 12236100, 'S?o Jos? dos Campos', 'SP'),
(27, 'Alexandre', 'Zaramela', '1985-12-12', 'MASCULINO', 1266585456, 2147483647, '35646456465', 125, 'Rua Hondo', 23, 'Jardim Oriente', 12236100, 'S?o Jos? dos Campos', 'SP');

-- --------------------------------------------------------

--
-- Estrutura da tabela `controle`
--

CREATE TABLE `controle` (
  `codigo` int(11) NOT NULL,
  `codigo_ferramenta` int(11) NOT NULL,
  `codigo_usuario` int(11) NOT NULL,
  `data_inicial` date NOT NULL,
  `data_final` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `departamentos`
--

CREATE TABLE `departamentos` (
  `codigo` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `departamentos`
--

INSERT INTO `departamentos` (`codigo`, `nome`) VALUES
(1, 'Metrologia'),
(2, 'Megazord'),
(3, 'Ensaios'),
(4, 'Baja'),
(5, 'Oficina Hidraulica');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ferramentas`
--

CREATE TABLE `ferramentas` (
  `codigo` int(11) NOT NULL,
  `descricao` varchar(200) CHARACTER SET utf8 NOT NULL,
  `codigo_departamento` int(11) NOT NULL,
  `part_number` varchar(50) CHARACTER SET utf8 NOT NULL,
  `nome` varchar(100) CHARACTER SET utf8 NOT NULL,
  `quantidade` int(11) NOT NULL,
  `imagem` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ferramentas`
--

INSERT INTO `ferramentas` (`codigo`, `descricao`, `codigo_departamento`, `part_number`, `nome`, `quantidade`, `imagem`) VALUES
(7, 'Paqu?metro instrumento de medi??o', 1, '1254', 'Paquimetro', 5, ''),
(8, 'Alicate indicado para parte eletrica.', 2, '1253', 'Alicate de bico', 10, ''),
(9, 'Serra fita para servi?os diversos.', 2, '33424PN', 'Serra fita BOSCH', 1, '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `logins`
--

CREATE TABLE `logins` (
  `codigo` int(11) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `logins`
--

INSERT INTO `logins` (`codigo`, `nickname`, `email`, `senha`, `status`) VALUES
(94, 'AdminMaster', 'Admin@master.com', '123', 1),
(127, 'ChristianUser', 'christianuser@fatec.com', '123456', 2),
(125, 'Zara', 'zaramela@fatec.com', '123456', 1),
(124, 'KarenR', 'karen@fatec.com', '123456', 2),
(118, 'LucasGuri', 'lucas@fatec.com', '123456', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `codigo` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `sobrenome` varchar(50) NOT NULL,
  `nascimento` date NOT NULL,
  `sexo` varchar(10) NOT NULL,
  `telefone` varchar(10) NOT NULL,
  `celular` varchar(11) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `codigo_login` int(11) NOT NULL,
  `codigo_departamento` int(11) NOT NULL,
  `rua` varchar(100) NOT NULL,
  `numero` int(11) NOT NULL,
  `bairro` varchar(50) NOT NULL,
  `cep` int(11) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `estado` varchar(2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`codigo`, `nome`, `sobrenome`, `nascimento`, `sexo`, `telefone`, `celular`, `cpf`, `codigo_login`, `codigo_departamento`, `rua`, `numero`, `bairro`, `cep`, `cidade`, `estado`) VALUES
(1, 'Karen', 'Rodrigues', '2003-12-21', 'F', '1285959585', '12985955854', '43443546444', 124, 2, 'Rua Leonardo da Vinci', 235, 'Jardim Oriente', 12236200, 'S?o Jos? dos Campos', 'SP'),
(3, 'Christian', 'Queiroz', '1995-06-13', 'M', '1239488356', '12981475634', '12324254323', 127, 2, 'Rua Hondo', 153, 'Jardim Oriente', 12236100, 'S?o Jos? dos Campos', 'SP');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `administradores`
--
ALTER TABLE `administradores`
  ADD PRIMARY KEY (`codigo`);

--
-- Índices para tabela `controle`
--
ALTER TABLE `controle`
  ADD PRIMARY KEY (`codigo`);

--
-- Índices para tabela `departamentos`
--
ALTER TABLE `departamentos`
  ADD PRIMARY KEY (`codigo`);

--
-- Índices para tabela `ferramentas`
--
ALTER TABLE `ferramentas`
  ADD PRIMARY KEY (`codigo`);

--
-- Índices para tabela `logins`
--
ALTER TABLE `logins`
  ADD PRIMARY KEY (`codigo`);

--
-- Índices para tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `administradores`
--
ALTER TABLE `administradores`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de tabela `controle`
--
ALTER TABLE `controle`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `departamentos`
--
ALTER TABLE `departamentos`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `ferramentas`
--
ALTER TABLE `ferramentas`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `logins`
--
ALTER TABLE `logins`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=128;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
