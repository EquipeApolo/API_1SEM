
from util import gerador_senha

def gerar_senha(ivy):
    ivy.falar("ok, estou gerando a sua senha. Aguarde um segundo")
    senha_gerada = gerador_senha.gerar_senha()
    ivy.falar("sua senha foi gerada com sucesso. Olhe na tela para verifica-la")