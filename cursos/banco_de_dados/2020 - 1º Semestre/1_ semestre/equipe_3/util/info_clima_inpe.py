from xml.dom import minidom
from urllib import request
import xmltodict, json


def get_informacoes_clima_7_dias(latitude, longitude):
    endpoint_lat_lng = "http://servicos.cptec.inpe.br/XML/cidade/7dias/" + latitude + "/" + longitude + "/previsaoLatLon.xml"
    response = request.urlopen(endpoint_lat_lng)
    data = json.dumps(xmltodict.parse(response))
    return json.loads(data)

def get_texto_previsao_tempo(latitude, longitude):
    clima = get_informacoes_clima_7_dias(latitude, longitude)
    previsao_hoje = clima['cidade']['previsao'][0]
    previsao_texto = "Hoje o tempo está " + get_descricao_tempo_by_sigla(previsao_hoje['tempo'])
    previsao_texto += ", a temperatura máxima é de " + previsao_hoje['maxima'] + ' graus'
    previsao_texto += ", a minima é de " + previsao_hoje['minima'] + " graus celsius"
    previsao_texto += ", essa é a previsão para " + clima['cidade']['nome']
    previsao_texto += ", informações do INPE"
    return previsao_texto

def get_descricao_tempo_by_sigla(sigla):
    return siglas_tempo[sigla]


siglas_tempo = {
    "ec": "Encoberto com Chuvas Isoladas",
    "ci": "Chuvas Isoladas",
    "c": "Chuva",
    "in": "Instável",
    "pp": "Poss. de Pancadas de Chuva",
    "cm": "Chuva pela Manhã",
    "cn": "Chuva a Noite",
    "pt": "Pancadas de Chuva a Tarde",
    "pm": "Pancadas de Chuva pela Manhã",
    "np": "Nublado e Pancadas de Chuva",
    "pc": "Pancadas de Chuva",
    "pn": "Parcialmente Nublado",
    "cv": "Chuvisco",
    "ch": "Chuvoso",
    "t": "Tempestade",
    "ps": "Predomínio de Sol",
    "e": "Encoberto",
    "n": "Nublado",
    "cl": "Céu Claro",
    "nv": "Nevoeiro",
    "g": "Geada",
    "ne": "Neve",
    "nd": "Não Definido",
    "pnt": "Pancadas de Chuva a Noite",
    "psc": "Possibilidade de Chuva",
    "pcm": "Possibilidade de Chuva pela Manhã",
    "pct": "Possibilidade de Chuva a Tarde",
    "pcn": "Possibilidade de Chuva a Noite",
    "npt": "Nublado com Pancadas a Tarde",
    "npn": "Nublado com Pancadas a Noite",
    "ncn": "Nublado com Poss. de Chuva a Noite",
    "nct": "Nublado com Poss. de Chuva a Tarde",
    "ncm": "Nubl. c/ Poss. de Chuva pela Manhã",
    "npm": "Nublado com Pancadas pela Manhã",
    "npp": "Nublado com Possibilidade de Chuva",
    "vn": "Variação de Nebulosidade",
    "ct": "Chuva a Tarde",
    "ppn": "Poss. de Panc. de Chuva a Noite",
    "ppt": "Poss. de Panc. de Chuva a Tarde",
    "ppm": "Poss. de Panc. de Chuva pela Manhã"
}

