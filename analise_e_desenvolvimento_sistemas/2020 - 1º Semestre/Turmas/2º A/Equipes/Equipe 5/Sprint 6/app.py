from tkinter import *
import time
from Regiao_norte import roda
from Regiao_centro_oeste import rodaco
from Regiao_sul import rodasul
from Regiao_sudeste import rodasud
from Regiao_nordeste2 import rodanor
from Dados_Gerais_Brasil_3 import rodage

def norteklk():
    roda("1")

def centoe():
    rodaco("1")

def sudest():
    rodasud("1")

def sull():
    rodasul("1")

def nord():
    rodanor("1")

def geral():
    rodage("1")


menu_inicial = Tk()
menu_inicial.title("Projeto integrador")


#imagem
img= PhotoImage(file="imagens/imagem1.png")


principal= Frame(menu_inicial, bg= "cyan")
principal.pack()


label_1 = Label(principal, 
            text = "Indicadores por Região",
            bg="cyan",
            font='arialblack 50')
label_1.grid(row=0, column=2)
Label(menu_inicial, bg = "cyan").pack()


#direcionar botoes
frame_button = Frame(menu_inicial, bg = "cyan")
frame_button.pack()


#botao norte
btnnt = Button(frame_button, text = "Norte",bg = "#2d8659", command= norteklk)
btnnt.grid(row = 0,column = 1)



Label_img = Label(frame_button, bg = "cyan", image=img).grid(row=1, column = 1)



#Tamanho da tela inicial
menu_inicial.geometry("500x250+1000+300")
menu_inicial.resizable(True, True)
menu_inicial.iconbitmap("imagens/icone.ico")
menu_inicial['bg'] = "cyan"
menu_inicial.state("zoomed")



#botao nordeste
btnnd = Button(frame_button, text = "Nordeste", bg = "red", command= nord)
btnnd.grid(row=1, column=2)

LabelE7= Label(principal, bg="cyan", width = 20)
LabelE7.grid(row=0,column = 4)
LabelE8= Label(frame_button, bg="cyan")
LabelE8.grid(row=0,column = 0)



#botao centro-oeste
btnco = Button(frame_button, text = "Centro-Oeste",bg = "orange", command= centoe)
btnco.grid(row = 1, column = 0)


#botaosul
btnsl = Button(frame_button, text = "sul", bg="#ac39ac", command= sull)
btnsl.grid(row = 2, column = 1)


#botaonsudeste
btnsd = Button(frame_button, text = "Sudeste",bg="yellow", command= sudest)
btnsd.grid(row = 2, column = 2)

#botaogeral
btgr = Button(principal, text = "Brasil", command=geral, font="arial 20", bg="green" )
btgr.grid(row=0, column = 5)


menu_inicial.mainloop()
