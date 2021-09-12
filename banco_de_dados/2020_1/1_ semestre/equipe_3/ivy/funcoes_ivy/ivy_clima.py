
from util import info_clima_inpe, minha_localizacao

def informar_clima(ivy):
    ivy.falar("Vou consultar o clima para você")
    location = minha_localizacao.get_localizacao_atual_by_ip()
    coordenadas = location['loc'].split(',')
    latitude = coordenadas[0]
    longitude = coordenadas[1]
    previsao_texto = info_clima_inpe.get_texto_previsao_tempo(latitude, longitude)
    ivy.falar(previsao_texto)
