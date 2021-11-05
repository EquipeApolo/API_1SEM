from flask import Flask, render_template, request, redirect,url_for
from blueprint.email_automatico import email

app = Flask(__name__)


@app.route('/')
def index():   
    return render_template("index.html")


@app.route('/projetos')
def projetos():   
    return render_template("projetos.html")

#-----------------------------------------------------

@app.route('/ads')
def ads():   
    return render_template("ADS.html")

# ------ 2020_1 --------
# ------- 1ºA ----------

@app.route('/ads/2020_1_Turma_A_Equipe_Team_Of_The_Semester')
def Turma_A_Equipe_Team_Of_The_Semester_2020_1():   
    return render_template("ADS/2020_1_Turma_A_Equipe_Team_Of_The_Semester.html")

@app.route('/ads/2020_1_Turma_A_Equipe_Smart_Stock')
def Turma_A_Equipe_Smart_Stock_2020_1():   
    return render_template("ADS/2020_1_Turma_A_Equipe_Smart_Stock.html")


@app.route('/ads/2020_1_Turma_A_Equipe_Psyco')
def Turma_A_Equipe_Psyco_2020_1():   
    return render_template("ADS/2020_1_Turma_A_Equipe_Psyco.html")


@app.route('/ads/2020_1_Turma_A_Equipe_Hexágono')
def Turma_A_Equipe_Hexágono_2020_1():   
    return render_template("ADS/2020_1_Turma_A_Equipe_Hexágono.html")


@app.route('/ads/2020_1_Turma_A_Equipe Energy Control')
def Turma_A_Equipe_Energy_Control_2020_1():   
    return render_template("ADS/2020_1_Turma_A_Equipe Energy Control.html")


@app.route('/ads/2020_1_Turma_A_Equipe_3RAG')
def Turma_A_Equipe_3RAG_2020_1():   
    return render_template("ADS/2020_1_Turma_A_Equipe_3RAG.html")

 
#------ 1ºB ---------


@app.route('/ads/2020_1_Turma_B_Equipe_Home_Solutions')
def Turma_B_Equipe_Home_Solutions_2020_1():   
    return render_template("ADS/2020_1_Turma_B_Equipe_Home_Solutions.html")


@app.route('/ads/2020_1_Turma_B_Equipe_iPET')
def Turma_B_Equipe_iPET_2020_1():   
    return render_template("ADS/2020_1_Turma_B_Equipe_iPET.html")


@app.route('/ads/2020_1_Turma_B_Equipe_JeRso')
def Turma_B_Equipe_JeRso_2020_1():   
    return render_template("ADS/2020_1_Turma_B_Equipe_JeRso.html")


@app.route('/ads/2020_1_Turma_B_Equipe_Slim')
def Turma_B_Equipe_Slim_2020_1():   
    return render_template("ADS/2020_1_Turma_B_Equipe_Slim.html")















#--------------------------------------------------

@app.route('/bd')
def bd():   
    return render_template("BD.html")

@app.route('/dsm')
def dsm():   
    return render_template("DSM.html")


@app.route('/manav')
def manav():   
    return render_template("MANAV.html")



@app.route('/contribua', methods=["GET", "POST"])
def contribua():
    resultado = None
    
    email_p = request.args.get('email')
    nome = request.args.get('nome')

    e = email(email_p, nome)
    if e == True:
        resultado = 'Email enviado!'
        return redirect(url_for('enviado'))
    if e == False:
        resultado = 'Verfique se os campos foram colocados com informações corretas!'
    else:
        resultado = 'Verfique se os campos foram colocados com informações corretas!'
    return render_template("contribua.html", resultado=resultado)
    
@app.route('/enviado')
def enviado():   
    return 'Email enviado!'

if __name__=="__main__":
    app.run(debug=True)




