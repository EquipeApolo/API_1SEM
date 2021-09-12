
import random
from util import audio
from ivy.funcoes_ivy import ivy_calculadora, ivy_gerar_senha, ivy_clima, ivy_cronometro, ivy_pesquisa, ivy_filme


class Ivy():
    nome = "Ivy"
    def __init__(self):
        self.falar("Iniciando")
        self.falar("Oi, eu sou a " + self.nome + ", Como posso te ajudar")

    def falar(self, audio_string):
        audio.talk(audio_string)

    def ouvir(self, aguardar_chamar = True, repetir_fala = True):

        voice_data = ''
        if(aguardar_chamar):
            voice_data = audio.record_audio('en-US').lower().strip()

        if (voice_data in ['ivy', 'ok', 'okay', 'hockey'] and aguardar_chamar):
            self.falar('Diga' + self.rand_tratamento() + ', Como posso te ajudar')
            voice_data = audio.record_audio()
        else:
            voice_data = audio.record_audio()

        if (voice_data != ''):
            if(repetir_fala):
                self.falar("Você disse" + voice_data)
            self.entender(voice_data)
        elif voice_data == '' and not aguardar_chamar:
            self.falar("Desculpa, eu não consegui compreender")
            voice_data = self.ouvir(False) #chama a funcao ouvir recursivamente, até compreender o que foi dito
        return  voice_data



    def entender(self, voice_data):
        voice_data = voice_data.lower()
        if 'qual é o seu nome' in voice_data or 'seu nome' in voice_data or 'qual o seu nome' in voice_data:
            self.falar('O meu nome é ' + self.nome)
        if 'pesquisar' in voice_data:
            ivy_pesquisa.pesquisar(self)
        if  voice_data in ['como está o tempo', 'como está o clima', 'clima', 'previsão do tempo']:
            ivy_clima.informar_clima(self)
        if 'como você está' in voice_data:
            self.falar('Estou bem, e você?')
        if 'senha' in voice_data or 'gerar senha' in voice_data:
            ivy_gerar_senha.gerar_senha(self)
        if 'calcular' in voice_data or 'calculadora' in voice_data:
            ivy_calculadora.calcular(self)
        if 'cronômetro' in voice_data or 'cronometrar' in voice_data:
            ivy_cronometro.cronometro_regressivo(self)
        if 'filme' in voice_data:
            ivy_filme.buscar_filme(self)
        if 'sair' in voice_data:
            self.falar('Até logo')
            exit()



    def rand_tratamento(self):
        num = random.randrange(1, 9)
        if(num == 1):
            return "meu rei"
        elif(num == 2):
            return "meu amado"
        elif(num == 3):
            return "meu senhor"
        elif(num == 4):
            return "meu lindo"
        elif(num == 5):
            return "meu fofucho"
        elif(num == 6):
            return "meu anjo"
        elif(num == 7):
            return "meu cosmos"
        elif (num == 8):
            return "meu tesouro"
        elif (num == 9):
            return "meu belo"






