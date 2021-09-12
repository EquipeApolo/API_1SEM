import requests

def buscar_filme(ivy):
    url = "https://r4u.herokuapp.com/getFilme/3" #filmes preferidos
    informacao_retornada = requests.get(url)
    informacao = informacao_retornada.json() 
    print(informacao['filme'])
    ivy.falar('O filme encontrado é ' + informacao['filme'])