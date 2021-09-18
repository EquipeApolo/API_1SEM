######################
# Captura de voz Ivy#
######################

# Importar biblioteca
import speech_recognition as sr
import playsound
import os
import random
from gtts import gTTS

# Reconhecedor de Aúdio
r = sr.Recognizer()


def record_audio(ask=False):
    with sr.Microphone() as source:
        if ask:
            ivy_speak(ask)
        audio = r.listen(source)
        voice_data = ''

        try:
            voice_data = r.recognize_google(audio, language="pt-BR")
            print(voice_data)
        except sr.UnknownValueError:
            ivy_speak('Desculpe não consegui entender')
        except sr.RequestError:
            ivy_speak('Desculpe, o reconhecimento de voz não está funcionando')
        return voice_data


def ivy_speak(audio_string):
    tts = gTTS(text=audio_string, lang='pt-BR')
    r = random.randint(1, 1000000)
    audio_file = 'audio-' + str(r) + '.mp3'
    tts.save(audio_file)
    playsound.playsound(audio_file)
    print(audio_string)
    os.remove(audio_file)


def respond(voice_data):
        if 'calculadora' in voice_data:

            ivy_speak('Informe o primeiro numero')
            num1 = float(record_audio())
            ivy_speak('Informe o segundo numero')
            num2 = float(record_audio())
            ivy_speak('Ok, agora me diga qual operação deseja realizar? somar, subtrair, mutiplicar ou dividir?')
            operacao = record_audio()

            if 'somar' in operacao:
                total = num1 + num2
                ivy_speak('O resultado é: '+str(total))

            if 'subtrair' in operacao:
                total = num1 - num2
                ivy_speak('O resultado é: '+str(total))

            if 'multiplicar' in operacao:
                total = num1 * num2
                ivy_speak('O resultado é: '+str(total))

            if 'dividir' in operacao:
                total = num1 / num2
                ivy_speak('O resultado é: '+str(total))


ivy_speak('O que posso ajudar?')
voice_data = record_audio()
respond(voice_data)
