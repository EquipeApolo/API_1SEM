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

@app.route('/ADS')
def ads():   
    return render_template("ADS.html")

# ------ 2020_1 --------
# ------- 1ºA ----------

@app.route('/ADS/2020_1_Turma_A_Equipe_Team_Of_The_Semester')
def Turma_A_Equipe_Team_Of_The_Semester_2020_1():   
    return render_template("ADS/2020_1_Turma_A_Equipe_Team_Of_The_Semester.html")

@app.route('/ADS/2020_1_Turma_A_Equipe_Smart_Stock')
def Turma_A_Equipe_Smart_Stock_2020_1():   
    return render_template("ADS/2020_1_Turma_A_Equipe_Smart_Stock.html")


@app.route('/ADS/2020_1_Turma_A_Equipe_Psyco')
def Turma_A_Equipe_Psyco_2020_1():   
    return render_template("ADS/2020_1_Turma_A_Equipe_Psyco.html")


@app.route('/ADS/2020_1_Turma_A_Equipe_Hexágono')
def Turma_A_Equipe_Hexágono_2020_1():   
    return render_template("ADS/2020_1_Turma_A_Equipe_Hexágono.html")


@app.route('/ADS/2020_1_Turma_A_Equipe Energy Control')
def Turma_A_Equipe_Energy_Control_2020_1():   
    return render_template("ADS/2020_1_Turma_A_Equipe Energy Control.html")


@app.route('/ADS/2020_1_Turma_A_Equipe_3RAG')
def Turma_A_Equipe_3RAG_2020_1():   
    return render_template("ADS/2020_1_Turma_A_Equipe_3RAG.html")

 
#------ 1ºB ---------


@app.route('/ADS/2020_1_Turma_B_Equipe_Home_Solutions')
def Turma_B_Equipe_Home_Solutions_2020_1():   
    return render_template("ADS/2020_1_Turma_B_Equipe_Home_Solutions.html")


@app.route('/ADS/2020_1_Turma_B_Equipe_iPET')
def Turma_B_Equipe_iPET_2020_1():   
    return render_template("ADS/2020_1_Turma_B_Equipe_iPET.html")


@app.route('/ADS/2020_1_Turma_B_Equipe_JeRso')
def Turma_B_Equipe_JeRso_2020_1():   
    return render_template("ADS/2020_1_Turma_B_Equipe_JeRso.html")


@app.route('/ADS/2020_1_Turma_B_Equipe_Slim')
def Turma_B_Equipe_Slim_2020_1():   
    return render_template("ADS/2020_1_Turma_B_Equipe_Slim.html")

#-------- 2ºA-------------------------------------------------------

@app.route('/ADS/2020_1_Turma_2ºA_Equipe_1')
def Turma_2ºA_Equipe_1_2020_1():   
    return render_template("ADS/2020_1_Turma_2ºA_Equipe_1.html")


@app.route('/ADS/2020_1_Turma_2ºA_Equipe_2')
def Turma_2ºA_Equipe_2_2020_1():   
    return render_template("ADS/2020_1_Turma_2ºA_Equipe_2.html")

@app.route('/ADS/2020_1_Turma_2ºA_Equipe_3')
def Turma_2ºA_Equipe_3_2020_1():   
    return render_template("ADS/2020_1_Turma_2ºA_Equipe_3.html")


@app.route('/ADS/2020_1_Turma_2ºA_Equipe_4')
def Turma_2ºA_Equipe_4_2020_1():   
    return render_template("ADS/2020_1_Turma_2ºA_Equipe_4.html")

@app.route('/ADS/2020_1_Turma_2ºA_Equipe_5')
def Turma_2ºA_Equipe_5_2020_1():   
    return render_template("ADS/2020_1_Turma_2ºA_Equipe_5.html")


#-------------- 2ºB--------------------------------------

@app.route('/ADS/2020_1_Turma_2ºB_Equipe_1')
def Turma_2ºB_Equipe_1_2020_1():   
    return render_template("ADS/2020_1_Turma_2ºB_Equipe_1.html")


@app.route('/ADS/2020_1_Turma_2ºB_Equipe_3')
def Turma_2ºB_Equipe_3_2020_1():   
    return render_template("ADS/2020_1_Turma_2ºB_Equipe_3.html")

#-------- 3º A ------

@app.route('/ADS/2020_1_Turma_3ºA_Equipe_1')
def Turma_3ºA_Equipe_1_2020_1():   
    return render_template("ADS/2020_1_Turma_3ºA_Equipe_1.html")

@app.route('/ADS/2020_1_Turma_3ºA_Equipe_2')
def Turma_3ºA_Equipe_2_2020_1():   
    return render_template("ADS/2020_1_Turma_3ºA_Equipe_2.html")


@app.route('/ADS/2020_1_Turma_3ºA_Equipe_3')
def Turma_3ºA_Equipe_3_2020_1():   
    return render_template("ADS/2020_1_Turma_3ºA_Equipe_3.html")

@app.route('/ADS/2020_1_Turma_3ºA_Equipe_4')
def Turma_3ºA_Equipe_4_2020_1():   
    return render_template("ADS/2020_1_Turma_3ºA_Equipe_4.html")

@app.route('/ADS/2020_1_Turma_3ºA_Equipe_5')
def Turma_3ºA_Equipe_5_2020_1():   
    return render_template("ADS/2020_1_Turma_3ºA_Equipe_5.html")

# ------------ 5º A ---------------

@app.route('/ADS/2020_1_Turma_5ºA_Equipe_2')
def Turma_5ºA_Equipe_2_2020_1():   
    return render_template("ADS/2020_1_Turma_5ºA_Equipe_2.html")

@app.route('/ADS/2020_1_Turma_5ºA_Equipe_5')
def Turma_5ºA_Equipe_5_2020_1():   
    return render_template("ADS/2020_1_Turma_5ºA_Equipe_5.html")

@app.route('/ADS/2020_1_Turma_5ºA_Equipe_7')
def Turma_5ºA_Equipe_7_2020_1():   
    return render_template("ADS/2020_1_Turma_5ºA_Equipe_7.html")


@app.route('/ADS/2020_1_Turma_5ºA_Equipe_8')
def Turma_5ºA_Equipe_8_2020_1():   
    return render_template("ADS/2020_1_Turma_5ºA_Equipe_8.html")


#--------- 6ºA ---------------------

@app.route('/ADS/2020_1_Turma_6ºA_Equipe_1')
def Turma_6ºA_Equipe_1_2020_1():   
    return render_template("ADS/2020_1_Turma_6ºA_Equipe_1.html")


# ----------------- 2020_2 -------------------------------------

#------------ 1ºA --------------------

@app.route('/ADS/2020_2_Turma_1ºA_Equipe_1')
def Turma_1ºA_Equipe_1_2020_2():   
    return render_template("ADS/2020_2_Turma_1ºA_Equipe_1.html")

@app.route('/ADS/2020_2_Turma_1ºA_Equipe_2')
def Turma_1ºA_Equipe_2_2020_2():   
    return render_template("ADS/2020_2_Turma_1ºA_Equipe_2.html")


@app.route('/ADS/2020_2_Turma_1ºA_Equipe_3')
def Turma_1ºA_Equipe_3_2020_2():   
    return render_template("ADS/2020_2_Turma_1ºA_Equipe_3.html")


@app.route('/ADS/2020_2_Turma_1ºA_Equipe_4')
def Turma_1ºA_Equipe_4_2020_2():   
    return render_template("ADS/2020_2_Turma_1ºA_Equipe_4.html")


@app.route('/ADS/2020_2_Turma_1ºA_Equipe_5')
def Turma_1ºA_Equipe_5_2020_2():   
    return render_template("ADS/2020_2_Turma_1ºA_Equipe_5.html")

@app.route('/ADS/2020_2_Turma_1ºA_Equipe_6')
def Turma_1ºA_Equipe_6_2020_2():   
    return render_template("ADS/2020_2_Turma_1ºA_Equipe_6.html")

#--------------- 1º B --------------------------

@app.route('/ADS/2020_2_Turma_1ºB_Equipe_4')
def Turma_1ºB_Equipe_4_2020_2():   
    return render_template("ADS/2020_2_Turma_1ºB_Equipe_4.html")


#-------------- 2ºA --------------------------

@app.route('/ADS/2020_2_Turma_2ºA_Equipe_1')
def Turma_2ºA_Equipe_1_2020_2():   
    return render_template("ADS/2020_2_Turma_2ºA_Equipe_1.html")


@app.route('/ADS/2020_2_Turma_2ºA_Equipe_2')
def Turma_2ºA_Equipe_2_2020_2():   
    return render_template("ADS/2020_2_Turma_2ºA_Equipe_2.html")


@app.route('/ADS/2020_2_Turma_2ºA_Equipe_3')
def Turma_2ºA_Equipe_3_2020_2():   
    return render_template("ADS/2020_2_Turma_2ºA_Equipe_3.html")


@app.route('/ADS/2020_2_Turma_2ºA_Equipe_4')
def Turma_2ºA_Equipe_4_2020_2():   
    return render_template("ADS/2020_2_Turma_2ºA_Equipe_4.html")


#------------ 2ºB ------------------

@app.route('/ADS/2020_2_Turma_2ºB_Equipe_1')
def Turma_2ºB_Equipe_1_2020_2():   
    return render_template("ADS/2020_2_Turma_2ºB_Equipe_1.html")


@app.route('/ADS/2020_2_Turma_2ºB_Equipe_2')
def Turma_2ºB_Equipe_2_2020_2():   
    return render_template("ADS/2020_2_Turma_2ºB_Equipe_2.html")


@app.route('/ADS/2020_2_Turma_2ºB_Equipe_3')
def Turma_2ºB_Equipe_3_2020_2():   
    return render_template("ADS/2020_2_Turma_2ºB_Equipe_3.html")


@app.route('/ADS/2020_2_Turma_2ºB_Equipe_4')
def Turma_2ºB_Equipe_4_2020_2():   
    return render_template("ADS/2020_2_Turma_2ºB_Equipe_4.html")


@app.route('/ADS/2020_2_Turma_2ºB_Equipe_5')
def Turma_2ºB_Equipe_5_2020_2():   
    return render_template("ADS/2020_2_Turma_2ºB_Equipe_5.html")


#-------------- 3ºA -------------------

@app.route('/ADS/2020_2_Turma_3ºA_Equipe_1')
def Turma_3ºA_Equipe_1_2020_2():   
    return render_template("ADS/2020_2_Turma_3ºA_Equipe_1.html")


@app.route('/ADS/2020_2_Turma_3ºA_Equipe_2')
def Turma_3ºA_Equipe_2_2020_2():   
    return render_template("ADS/2020_2_Turma_3ºA_Equipe_2.html")


@app.route('/ADS/2020_2_Turma_3ºA_Equipe_3')
def Turma_3ºA_Equipe_3_2020_2():   
    return render_template("ADS/2020_2_Turma_3ºA_Equipe_3.html")

@app.route('/ADS/2020_2_Turma_3ºA_Equipe_4')
def Turma_3ºA_Equipe_4_2020_2():   
    return render_template("ADS/2020_2_Turma_3ºA_Equipe_4.html")

#------------ 3ºB ----------------------

@app.route('/ADS/2020_2_Turma_3ºB_Equipe_1')
def Turma_3ºB_Equipe_1_2020_2():   
    return render_template("ADS/2020_2_Turma_3ºB_Equipe_1.html")


@app.route('/ADS/2020_2_Turma_3ºB_Equipe_4')
def Turma_3ºB_Equipe_4_2020_2():   
    return render_template("ADS/2020_2_Turma_3ºB_Equipe_4.html")

#------------- 5ºA ------------------------

@app.route('/ADS/2020_2_Turma_5ºA_Equipe_1')
def Turma_5ºA_Equipe_1_2020_2():   
    return render_template("ADS/2020_2_Turma_5ºA_Equipe_1.html")


@app.route('/ADS/2020_2_Turma_5ºA_Equipe_2')
def Turma_5ºA_Equipe_2_2020_2():   
    return render_template("ADS/2020_2_Turma_5ºA_Equipe_2.html")


@app.route('/ADS/2020_2_Turma_5ºA_Equipe_3')
def Turma_5ºA_Equipe_3_2020_2():   
    return render_template("ADS/2020_2_Turma_5ºA_Equipe_3.html")


@app.route('/ADS/2020_2_Turma_5ºA_Equipe_6')
def Turma_5ºA_Equipe_6_2020_2():   
    return render_template("ADS/2020_2_Turma_5ºA_Equipe_6.html")

#-------------- 6ºA ---------------------------

@app.route('/ADS/2020_2_Turma_6ºA_Equipe_2')
def Turma_6ºA_Equipe_2_2020_2():   
    return render_template("ADS/2020_2_Turma_6ºA_Equipe_2.html")


@app.route('/ADS/2020_2_Turma_6ºA_Equipe_3')
def Turma_6ºA_Equipe_3_2020_2():   
    return render_template("ADS/2020_2_Turma_6ºA_Equipe_3.html")


@app.route('/ADS/2020_2_Turma_6ºA_Equipe_4')
def Turma_6ºA_Equipe_4_2020_2():   
    return render_template("ADS/2020_2_Turma_6ºA_Equipe_4.html")



















#--------------------------------------------------

@app.route('/BD')
def bd():   
    return render_template("BD.html")

@app.route('/DSM')
def dsm():   
    return render_template("DSM.html")


@app.route('/MANAV')
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




