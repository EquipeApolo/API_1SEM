import sys
import os
import inspect
import speech_recognition as sr
import sqlite3
from subprocess import call
from time import sleep

r = sr.Recognizer()
r.pause_threshold = 1
with sr.Microphone() as source:
    r.adjust_for_ambient_noise(source, duration=1)

def inserir_funcionarios():

    ## Realizando a conexão com o banco
    conn = sqlite3.connect(os.path.join(os.path.dirname(os.path.abspath(inspect.stack()[0][1])), 'database.db'))
    ## Criando um cursor, utilizado para executar as funções do banco
    cursor = conn.cursor()

    print('Diga o nome do funcionário que deseja cadastrar.')
    func_nome = listenSpeech()

    print('Diga um cargo listado a baixo em que deseja cadastrar o funcionário.')
    cursor.execute(""" SELECT * FROM cargos ORDER BY 1; """)

    for row in cursor.fetchall():
        print("{0} - {1}".format(row[0], row[1]))
    func_cargo = listenSpeech()

    print('Diga o salário do funcionário.')
    func_salario = listenSpeech()

    confirmacao = ""
    while confirmacao != "sim" and confirmacao != "não":

        print("Nome: {0};\nCargo: {1};\nSalário: {2}\n".format(func_nome, func_cargo, func_salario))
        print('Você confirma esses dados? Sim, ou não?')
        confirmacao = listenSpeech()

        if confirmacao == "sim":

            cursor.execute("""
            INSERT INTO funcionarios (funcionarios_nome, funcionarios_cargo, funcionarios_salario, funcionarios_status)
            VALUES (?,?,?,?)
            """, (func_nome, func_cargo, func_salario, "ativo"))

            conn.commit()
            print('Dados inseridos com sucesso.')
        elif confirmacao == "não":
            print('Dados não inseridos!')
        else:
            print('Por favor, diga apenas sim, ou não.\n')


    conn.close()
    return


def inserir_cargo():

    ## Realizando a conexão com o banco
    conn = sqlite3.connect(os.path.join(os.path.dirname(os.path.abspath(inspect.stack()[0][1])), 'database.db'))
    ## Criando um cursor, utilizado para executar as funções do banco
    cursor = conn.cursor()

    ##print("Vamos cadastrar um novo cargo: ")

    print('Diga o nome do cargo.')
    cargo_nome = listenSpeech()

    confirmacao = ""
    while confirmacao != "sim" and confirmacao != "não":
        
        print("Cargo: {0}\n".format(cargo_nome))
        print('Você confirma esses dados? Sim, ou não?')
        confirmacao = listenSpeech()

        if confirmacao == "sim":
            cursor.execute(""" INSERT INTO cargos (cargos_nome) VALUES (?) """, (cargo_nome,))
            conn.commit()
            print('Dados inseridos com sucesso.')
        elif confirmacao == "não":
            print('Dados não inseridos!')
        else:
            print('Por favor, diga apenas sim, ou não.\n')
    
    conn.close()
    return

def functionSwitcher(argument):

    if str(argument).lower() == "cadastrar funcionário":
        inserir_funcionarios()
    elif str(argument).lower() == "cadastrar cargo":
        inserir_cargo()
    elif str(argument).lower() == "desabilitar sara":
        sys.exit()
    else:
        print("Função Não Implementada\n")

def main():

    print('MENU PRINCIPAL SARAH')
    print('_' * 42)
    print('Olá, sou a SARAH, sua assistente de RH!')
    print('_' * 42)
    print('Diga, o que você deseja fazer?')
    print('1 - Cadastrar funcionário')
    print('2 - Pesquisar funcionário')
    print('3 - Cadastrar Cargo')
    print('4 - Desabilitar SARAH\n')
    print('Diga o que deseja fazer.')
    command = listenSpeech()

    functionSwitcher(command)

def listenSpeech():

    with sr.Microphone() as source:
        print('Escutando...\n')
        audio = r.listen(source)
        print('Processando...\n')
    try:
        command = r.recognize_google(audio, language='pt-BR')
        print('Você disse: {0}\n'.format(command))
    # loop back to continue to listen for commands if unrecognizable speech
    # is received
    except sr.UnknownValueError:
        print('Não entendi o que você disse, repita por favor.')
        print('Escutando...\n')
        command = listenSpeech()
    
    clear()
    return command

def clear():

    sleep(2)
    if os.name == 'nt': 
        _ = os.system('cls') 
   
    else: 
        _ = os.system('clear')

while True:

    main()
pass
