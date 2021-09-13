import speech_recognition as sr
import playsound
import os
import random
from gtts import gTTS

def record_audio(language = 'pt-BR'):
    r = sr.Recognizer()
    with sr.Microphone() as source:
        audio = r.listen(source)
        voice_data = ''
        try:
            voice_data = r.recognize_google(audio, language=language)
            print(voice_data)
        except sr.UnknownValueError:
            return ""
        except sr.RequestError:
            return ""
        return voice_data

def talk(audio_string):
    tts = gTTS(text=audio_string, lang='pt-BR')
    r = random.randint(1, 100000)
    arquivo_texto = 'audio' + str(r) + '.mp3'
    tts.save(arquivo_texto)
    playsound.playsound(arquivo_texto)
    os.remove(arquivo_texto)