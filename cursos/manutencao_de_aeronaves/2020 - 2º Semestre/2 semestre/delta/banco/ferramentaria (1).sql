-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 22-Nov-2020 às 03:42
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
  `numeral` int(11) NOT NULL,
  `nome` varchar(100) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  `quantidade` int(11) NOT NULL,
  `imagem` varchar(50) NOT NULL,
  `data_imagem` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ferramentas`
--

INSERT INTO `ferramentas` (`codigo`, `descricao`, `codigo_departamento`, `part_number`, `numeral`, `nome`, `quantidade`, `imagem`, `data_imagem`) VALUES
(45, 'Relógio comparador convencional', 14, 'MEC1', 1, 'Relógio Comparador', 1, 'MEC1-21112020194401.jpg', '21112020194401'),
(46, 'Kit completo Relógio Apalpador', 14, 'MEC2', 2, 'Kit Relógio Apalpador', 1, 'MEC2-21112020143231.jpg', '21112020143231'),
(47, 'Conjunto com um dos kits faltando uma ponta.', 14, 'MEC3', 3, 'Conjunto de pontas para tupia', 2, 'MEC3-21112020193246.jpg', '21112020193246'),
(48, 'Micrômetro Mitutoyo comprado na Cordilheira dos Andes', 14, 'MEC4', 4, 'Micrômetro 0-50mm Mitutoyo', 1, 'MEC4-21112020193301.jpg', '21112020193301'),
(49, 'Tesoura para corte de fibras', 15, 'MCO1', 1, 'Tesoura', 2, 'MCO1-21112020201048.jpg', '21112020201048'),
(50, 'Espatula para manipulação de resina', 15, 'MCO2', 2, 'Espatula', 3, 'MCO2-21112020201148.jpg', '21112020201148'),
(51, 'Pistola para aplicação de silicone', 15, 'MCO3', 3, 'Pistola de Silicone', 1, 'MCO3-21112020201227.jpg', '21112020201227'),
(52, 'Pincel para manipulação e aplicação de resina', 15, 'MCO4', 4, 'Pincel', 4, 'MCO4-21112020201257.jpg', '21112020201257'),
(53, 'Medidor de temperatura infravermelho', 16, 'ELE1', 1, 'Medidor de temperatura', 2, 'ELE1-21112020201352.jpg', '21112020201352'),
(54, 'Impressora de pequenas etiquetas', 16, 'ELE2', 2, 'Impressora Brother', 1, 'ELE2-21112020201418.jpg', '21112020201418'),
(55, 'Anemômetro WingSpeed measure', 16, 'ELE3', 3, 'Anemômetro', 1, 'ELE3-21112020201458.jpg', '21112020201458'),
(56, 'Carece de descrição', 16, 'ELE4', 4, 'Suporte laser BOSCH BM1', 1, 'ELE4-21112020201543.jpg', '21112020201543'),
(57, 'Carregador de baterias automotivas e etc.', 17, 'MIS1', 1, 'Carregador de bateria 12V', 1, 'MIS1-21112020201730.jpg', '21112020201730'),
(58, 'Politriz convencional', 17, 'MIS2', 2, 'Politriz', 1, 'MIS2-21112020201937.jpg', '21112020201937'),
(59, 'Plaina convencional da MAKITA', 17, 'MIS3', 3, 'Plaina Makita', 1, 'MIS3-21112020202054.jpg', '21112020202054'),
(60, 'Serra elétrica convencional da MAKITA', 17, 'MIS4', 4, 'Serra elétrica MAKITA', 1, 'MIS4-21112020202123.jpg', '21112020202123'),
(61, 'Relógio de Zeramento de ferramenta', 14, 'MEC5', 5, 'Relógio de Zeramento de ferramenta', 1, 'MEC5-21112020193321.jpg', '21112020193321'),
(62, 'Refratrômetro Portátil', 14, 'MEC6', 6, 'Refratrômetro Portátil', 1, 'MEC6-21112020194225.jpg', '21112020194225'),
(63, 'Relógio Comparador 3D', 14, 'MEC7', 7, 'Relógio Comparador 3D', 1, 'MEC7-21112020194242.jpg', '21112020194242'),
(64, 'Desandador Tipo T M3-M8', 14, 'MEC8', 8, 'Desandador Tipo T M3-M8', 1, 'MEC8-21112020194259.jpg', '21112020194259'),
(65, 'Pente de Rosca', 14, 'MEC9', 9, 'Pente de Rosca', 1, 'MEC9-21112020194316.jpg', '21112020194316'),
(66, 'Esquadro de luz 160x100mm', 14, 'MEC10', 10, 'Esquadro de luz 160x100mm', 1, 'MEC10-21112020194333.jpg', '21112020194333'),
(67, 'Esquadro de luz 63x40mm', 14, 'MEC11', 11, 'Esquadro de luz 63x40mm', 1, 'MEC11-21112020194426.jpg', '21112020194426'),
(68, 'Esquadro de luz 100x63mm', 14, 'MEC12', 12, 'Esquadro de luz 100x63mm', 1, 'MEC12-21112020194509.jpg', '21112020194509'),
(69, 'Esquadro de luz 300x200mm', 14, 'MEC13', 13, 'Esquadro de luz 300x200mm', 1, 'MEC13-21112020194525.jpg', '21112020194525'),
(70, 'Base Magnética', 14, 'MEC14', 14, 'Base Magnética', 1, 'MEC14-21112020194545.jpg', '21112020194545'),
(71, 'Medidor de PH', 14, 'MEC15', 15, 'Medidor de PH', 1, 'MEC15-21112020194605.jpg', '21112020194605'),
(72, 'Gravador Elétrico', 14, 'MEC16', 16, 'Gravador Elétrico', 1, 'MEC16-21112020194624.jpg', '21112020194624'),
(73, 'Goniômetro de Precisão', 14, 'MEC17', 17, 'Goniômetro de Precisão', 1, 'MEC17-21112020194651.jpg', '21112020194651'),
(74, 'Desandador 265mm', 14, 'MEC18', 18, 'Desandador 265mm', 1, 'MEC18-21112020194710.jpg', '21112020194710'),
(75, 'Desandador 220mm', 14, 'MEC19', 19, 'Desandador 220mm', 1, 'MEC19-21112020194743.jpg', '21112020194743'),
(76, 'Trena', 14, 'MEC20', 20, 'Trena', 1, 'MEC20-21112020194803.jpg', '21112020194803'),
(77, 'Macho M8', 14, 'MEC21', 21, 'Macho M8', 2, 'MEC21-21112020194907.jpg', '21112020194907'),
(78, 'Macho M5', 14, 'MEC22', 22, 'Macho M5', 2, 'MEC22-21112020194936.jpg', '21112020194936'),
(79, 'Macho M4', 14, 'MEC23', 23, 'Macho M4', 1, 'MEC23-21112020195009.jpg', '21112020195009'),
(80, 'Macho M3', 14, 'MEC24', 24, 'Macho M3', 1, 'MEC24-21112020195321.jpg', '21112020195321'),
(81, 'Macho M6', 14, 'MEC25', 25, 'Macho M6', 1, 'MEC25-21112020195354.jpg', '21112020195354'),
(82, 'Macho M10', 14, 'MEC26', 26, 'Macho M10', 3, 'MEC26-21112020195425.jpg', '21112020195425'),
(83, 'Macho M16', 14, 'MEC27', 27, 'Macho M16', 2, 'MEC27-21112020195451.jpg', '21112020195451'),
(84, 'Macho 1/4 UNF ', 14, 'MEC28', 28, 'Macho 1/4 UNF ', 7, 'MEC28-21112020195539.jpg', '21112020195539'),
(85, 'Macho 5/16 UNF', 14, 'MEC29', 29, 'Macho 5/16 UNF', 4, 'MEC29-21112020195728.jpg', '21112020195728'),
(86, 'Macho 3/8 UNF', 14, 'MEC30', 30, 'Macho 3/8 UNF', 2, 'MEC30-21112020195633.jpg', '21112020195633'),
(87, 'Macho 1/8 UNC', 14, 'MEC31', 31, 'Macho 1/8 UNC', 3, 'MEC31-21112020195829.jpg', '21112020195829'),
(88, 'Macho 8/36 UNF', 14, 'MEC32', 32, 'Macho 8/36 UNF', 2, 'MEC32-21112020195911.jpg', '21112020195911'),
(89, 'Macho 10/32 UNF', 14, 'MEC33', 33, 'Macho 10/32 UNF', 3, 'MEC33-21112020200030.jpg', '21112020200030'),
(90, 'Macho 3/8 NPT', 14, 'MEC34', 34, 'Macho 3/8 NPT', 1, 'MEC34-21112020200524.jpg', '21112020200524'),
(91, 'Macho 1/4 NPT', 14, 'MEC35', 35, 'Macho 1/4 NPT', 1, 'MEC35-21112020200555.jpg', '21112020200555'),
(92, 'Macho 1/8 NPT', 14, 'MEC36', 36, 'Macho 1/8 NPT', 1, 'MEC36-21112020200626.jpg', '21112020200626');

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
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;

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
