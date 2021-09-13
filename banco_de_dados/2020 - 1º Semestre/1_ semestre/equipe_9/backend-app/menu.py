def cadastro_funcionario():
    print("Cadastro de funcionario")

def pesquisa_funcionario():
    print("Pesquisa de funcionario")

def habilitar_sarah():
    print("Habilitar SARAH")

def desabilitar_sarah():
    print("Habilitar SARAH")

## Menu Inicial com as tarefas iniciais
def menu_inicial():
    print('_' * 42)
    print('Olá, sou a SARAH, sua assistente de RH!')
    print('_' * 42)
    print('Diga, o que você deseja fazer?')
    print('1 - Cadastrar funcionarios')
    print('2 - Pesquisar funcionario')
    print('3 - Habilitar SARAH')
    print('4 - Desabilitar SARAH')

menu_inicial()

opcao = int(input("Diga o numero de uma opção: "))

## Opções do menu, chamando as funções correspondentes

if opcao == 1:
    cadastro_funcionario()
if opcao == 2:
    pesquisa_funcionario()
if opcao == 3:
    habilitar_sarah()
if opcao == 4:
    desabilitar_sarah()










