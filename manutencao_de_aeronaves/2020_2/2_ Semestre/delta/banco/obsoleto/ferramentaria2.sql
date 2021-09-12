-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 06-Out-2020 às 21:00
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
  `nome` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `sobrenome` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `nascimento` date NOT NULL,
  `sexo` varchar(11) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `telefone` varchar(10) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `celular` varchar(11) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `cpf` varchar(15) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `codigo_login` int(11) NOT NULL,
  `rua` varchar(100) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `numero` int(11) NOT NULL,
  `bairro` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `cep` int(11) NOT NULL,
  `cidade` varchar(100) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `estado` varchar(2) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `administradores`
--

INSERT INTO `administradores` (`codigo`, `nome`, `sobrenome`, `nascimento`, `sexo`, `telefone`, `celular`, `cpf`, `codigo_login`, `rua`, `numero`, `bairro`, `cep`, `cidade`, `estado`) VALUES
(31, 'Alexandre', 'Zaramela', '1985-09-09', 'MASCULINO', '1239456812', '12981374539', '85869574958', 135, 'Rua Arturus', 45, 'Jardim Satélite', 12230200, 'São José dos Campos', 'SP'),
(1, 'Christian', 'Queiroz', '1995-06-13', 'MASCULINO', '1239316430', '12981475168', '39693324862', 1, 'Rua Hondo', 481, 'Jardim Oriente', 12236100, 'São José dos Campos', 'SP');

-- --------------------------------------------------------

--
-- Estrutura da tabela `controle`
--

CREATE TABLE `controle` (
  `codigo` int(11) NOT NULL,
  `codigo_ferramenta` int(11) NOT NULL,
  `codigo_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `departamentos`
--

CREATE TABLE `departamentos` (
  `codigo` int(11) NOT NULL,
  `nome` varchar(100) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `departamentos`
--

INSERT INTO `departamentos` (`codigo`, `nome`) VALUES
(14, 'Mecânica'),
(15, 'Material Composto'),
(16, 'Elétrica e Eletrônica'),
(17, 'Miscellaneous');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ferramentas`
--

CREATE TABLE `ferramentas` (
  `codigo` int(11) NOT NULL,
  `descricao` varchar(200) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `codigo_departamento` int(11) NOT NULL,
  `part_number` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `nome` varchar(100) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `quantidade` int(11) NOT NULL,
  `imagem` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ferramentas`
--

INSERT INTO `ferramentas` (`codigo`, `descricao`, `codigo_departamento`, `part_number`, `nome`, `quantidade`, `imagem`) VALUES
(45, 'Relógio comparador convencional', 14, 'MEC1', 'Relógio Comparador', 1, 'MEC1.jpg'),
(46, 'Kit completo Relógio Apalpador', 14, 'MEC2', 'Kit Relógio Apalpador', 1, 'MEC2.jpg'),
(47, 'Conjunto com um dos kits faltando uma ponta.', 14, 'MEC3', 'Conjunto de pontas para tupia', 2, 'MEC3.jpg'),
(48, 'Micrômetro Mitutoyo comprado na Cordilheira dos Andes', 14, 'MEC4', 'Micrômetro 0-50mm Mitutoyo', 1, 'MEC4.jpg'),
(49, 'Tesoura para corte de fibras', 15, 'MCO1', 'Tesoura', 2, 'vazio.jpg'),
(50, 'Espatula para manipulação de resina', 15, 'MCO2', 'Espatula', 3, 'vazio.jpg'),
(51, 'Pistola para aplicação de silicone', 15, 'MCO3', 'Pistola de Silicone', 1, 'MCO3.jpg'),
(52, 'Pincel para manipulação e aplicação de resina', 15, 'MCO4', 'Pincel', 4, 'MCO4.jpg'),
(53, 'Medidor de temperatura infravermelho', 16, 'ELE1', 'Medidor de temperatura', 1, 'ELE1.jpg'),
(54, 'Impressora de pequenas etiquetas', 16, 'ELE2', 'Impressora Brother', 1, 'vazio.jpg'),
(55, 'Anemômetro WingSpeed measure', 16, 'ELE3', 'Anemômetro', 1, 'ELE3.jpg'),
(56, 'Carece de descrição', 16, 'ELE4', 'Suporte laser BOSCH BM1', 1, 'vazio.jpg'),
(57, 'Carregador de baterias automotivas e etc.', 17, 'MIS1', 'Carregador de bateria 12V', 1, 'MIS1.jpg'),
(58, 'Politriz convencional', 17, 'MIS2', 'Politriz', 1, 'vazio.jpg'),
(59, 'Plaina convencional da MAKITA', 17, 'MIS3', 'Plaina Makita', 1, 'MIS3.jpg'),
(60, 'Serra elétrica convencional da MAKITA', 17, 'MIS4', 'Serra elétrica MAKITA', 1, 'vazio.jpg');

-- --------------------------------------------------------

--
-- Estrutura da tabela `logins`
--

CREATE TABLE `logins` (
  `codigo` int(11) NOT NULL,
  `nickname` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `email` varchar(100) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `senha` varchar(100) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `logins`
--

INSERT INTO `logins` (`codigo`, `nickname`, `email`, `senha`, `status`) VALUES
(142, 'RafaelFatec', 'rafael@composto.com', '123456', 2),
(143, 'Willian', 'willian@citroen.com', '123456', 2),
(141, 'Renato', 'renato@mecanica.com', '123456', 2),
(144, 'Alemao', 'alemao@nay.com', '123456', 2),
(135, 'Zara', 'zaramela@fatec.com', '123456', 1),
(1, 'ChristianQ', 'christian@fatec.com', '123456', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `codigo` int(11) NOT NULL,
  `nome` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `sobrenome` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `nascimento` date NOT NULL,
  `sexo` varchar(10) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `telefone` varchar(10) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `celular` varchar(11) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `cpf` varchar(15) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `codigo_login` int(11) NOT NULL,
  `codigo_departamento` int(11) NOT NULL,
  `rua` varchar(100) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `numero` int(11) NOT NULL,
  `bairro` varchar(50) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `cep` int(11) NOT NULL,
  `cidade` varchar(100) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `estado` varchar(2) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`codigo`, `nome`, `sobrenome`, `nascimento`, `sexo`, `telefone`, `celular`, `cpf`, `codigo_login`, `codigo_departamento`, `rua`, `numero`, `bairro`, `cep`, `cidade`, `estado`) VALUES
(15, 'Alemão', 'Embraer', '1990-10-10', 'MASCULINO', '1239445566', '12988665544', '44444444444', 144, 17, 'Rua Valdemir de Oliveira', 40, 'Conjunto Residencial Trinta e Um de Março', 12237200, 'São José dos Campos', 'SP'),
(13, 'Rafael', 'Fatec', '1988-01-01', 'MASCULINO', '1239444433', '12981755885', '22222222222', 142, 15, 'Rua das Rogerias', 10, 'Jardim das Indústrias', 12240100, 'São José dos Campos', 'SP'),
(14, 'Willian', 'Citroen', '1989-10-22', 'MASCULINO', '1239211221', '12987478559', '33333333333', 143, 16, 'Rua Francisco Paes', 20, 'Centro', 12210100, 'São José dos Campos', 'SP'),
(12, 'Renato', 'Pinto', '1980-12-12', 'MASCULINO', '1233333333', '12981281221', '11111111111', 141, 14, 'Rua Maricá', 12, 'Jardim Satélite', 12230100, 'São José dos Campos', 'SP');

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
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de tabela `controle`
--
ALTER TABLE `controle`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `departamentos`
--
ALTER TABLE `departamentos`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de tabela `ferramentas`
--
ALTER TABLE `ferramentas`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT de tabela `logins`
--
ALTER TABLE `logins`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=145;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
