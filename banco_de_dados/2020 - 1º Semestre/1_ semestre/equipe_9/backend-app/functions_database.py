## Importando a biblioteca do banco de dados SQLite
import sqlite3
from app import *

## Realizando a conexão com o banco
conn = sqlite3.connect('database.db')
## Criando um cursor, utilizado para executar as funções do banco
cursor = conn.cursor()

def inserir_funcionarios():

    print("Vamos cadastrar um novo funcionário: ")
    func_nome = input("Digite o nome do funcionário: ")
    func_cargo = input("Digite o cargo do funcionário: ")
    func_salario = input("Digite o salário do funcionário: ")
    #input("Você confirma esses dados? ")

    status = str("ativo")
    cursor.execute("""
    INSERT INTO funcionarios (nome, cargo, salario, status)
    VALUES (?,?,?,?)
    """, (func_nome, func_cargo, func_salario, status))

    conn.commit()

    print('Dados inseridos com sucesso.')

    conn.close()