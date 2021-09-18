
import webbrowser


def pesquisar(ivy):
    ivy.falar('O que você quer pesquisar?')
    pesquisar = ivy.ouvir(False)
    url = 'https://google.com/search?q=' + pesquisar
    webbrowser.get().open(url)
    ivy.falar('Aqui está o que eu encontrei sobre ' + pesquisar)