

from util import audio
from time import sleep

def cronometro_regressivo(ivy):
    ivy.falar('Vou cronometrar sua tarefa!')
    ivy.falar("Quanto tempo irá dedicar a sua tarefa ?")
    t = int(ivy.ouvir(False))
    for tempo in range(t, 0, -1):
        if sleep(t) == sleep(8):
            ivy.falar("Foco no trabalho!")
        if sleep(t) == sleep(5):
            ivy.falar("não mexa no whatsapp!")
        if sleep(t) == sleep(2):
            ivy.falar("quase lá")
        break
    ivy.falar("terminou!")


