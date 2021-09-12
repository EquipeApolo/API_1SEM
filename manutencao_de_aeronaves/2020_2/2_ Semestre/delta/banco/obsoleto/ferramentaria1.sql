-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 23-Set-2020 às 01:29
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
(1, 'Metrologia'),
(2, 'Megazord'),
(3, 'Ensaios'),
(4, 'Baja'),
(5, 'Oficina Hidráulica'),
(6, 'Simulação Eletrônica'),
(7, 'Simulação de engastação'),
(8, 'Diversos'),
(9, 'Pintura');

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
(7, 'Paquímetro é um instrumento de medição!', 1, '12543', 'Paquímetro Digital', 3, '12543.jpg'),
(8, 'Alicate indicado para parte elétrica.', 2, '1253', 'Alicate de bico laranja', 15, '1253.jpg'),
(9, 'Serra fita para serviços diversos.', 2, '33424PN', 'Serra fita BOSCH', 1, '33424PN.jpg'),
(10, 'Rebitadeira padrao', 4, '003456', 'Rebitadeira', 2, '003456.jpg'),
(11, 'Chaveta pra retirar cabeça 8', 4, '7484958', 'Chaveta 8', 20, '7484958.webp'),
(12, 'Chave de fenda universal da marca Laço para montagem ou desmontagem de parafusos', 2, 'M1', 'Chave de fenda Universal Laço', 2, 'M1.jpg'),
(13, 'Teste imagem martelo', 1, 'M2', 'Martelo1', 5, 'M2.webp'),
(14, 'Martelo testando pela segunda vez', 1, 'M3', 'Martelo 2', 2, 'M3.jpg'),
(15, 'Martelo 3 testando codigo', 2, 'M4', 'Martelo 3', 1, 'M4.jpg'),
(16, 'Papel para o lucas brincar de sei lá!', 8, 'DI0001', 'Papel cartão vermelho', 50, 'DI0001.jpg'),
(17, 'Trena para medições diversas', 3, 'ME000001', 'Trena 5m', 2, 'ME000001.jpg'),
(18, 'Serrote serra tudo', 4, 'ME11', 'Serrote Makita', 1, 'ME11.jpg'),
(19, 'Broca de furadeira ideal para furar paredes, madeiras, chapas de aço e alumínio, possui ponta preta.', 8, 'DI0002', 'Broca 10', 10, 'DI0002.jpg'),
(20, 'Broca de furadeira ideal para furar paredes, madeiras, chapas de aço e alumínio, possui ponta preta.', 8, 'DI0003', 'Broca 11', 10, 'DI0003.jpg'),
(21, 'Broca para diversas necessidades, qualidade intermediária, preferível uso em madeira.', 8, 'DI0004', 'Broca 12', 10, 'DI0004.jpg'),
(22, 'Paquímetro comum para medições precisas.', 1, 'MET0001', 'Paquímetro Comum', 1, 'MET0001.jpg'),
(23, 'Broca 13 fura tudo', 8, 'USI001', 'Broca 13', 15, 'USI001.jpg');

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
(135, 'Zara', 'zaramela@fatec.com', '123456', 1),
(134, 'KarenR', 'karen@fatec.com', '123456', 2),
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
(7, 'Karen', 'Roithmeier', '2002-12-13', 'FEMININO', '1298758473', '12984848393', '39693324862', 134, 1, 'Rua Trinidad', 16, 'Cidade Vista Verde', 12223420, 'São José dos Campos', 'SP');

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
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT de tabela `controle`
--
ALTER TABLE `controle`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `departamentos`
--
ALTER TABLE `departamentos`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `ferramentas`
--
ALTER TABLE `ferramentas`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de tabela `logins`
--
ALTER TABLE `logins`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=136;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
