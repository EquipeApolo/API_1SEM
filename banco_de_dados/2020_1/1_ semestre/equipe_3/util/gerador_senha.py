import random


def gerar_senha():
    lista_simbolos= ["!", "@", "#", "$", "%", "&", "*"]
    letras = ["a", "b", "c", "d","e","f","g","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"]

    gerar_palavras = True
    gerar_numeros = True
    gerar_0_9 = True
    gerar_simbolos = True
    gerar_abc = True
    qtde_abc = 5
    gerar_maiusculo = True

    senha = ""

    if gerar_simbolos:
        valor = random.randint(0, len(lista_simbolos))
        senha += lista_simbolos[valor]
        
    if gerar_numeros:
        if gerar_0_9:
            valor = random.randint(0, 9)
            senha += str(valor)

    if gerar_abc:
        for i in range(qtde_abc):
            valor = random.randint(0, len(letras))
            senha += letras[valor]

    print ("sua senha é: " + senha)
    return senha
