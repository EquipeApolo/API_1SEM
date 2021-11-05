from flask import Flask, render_template, request, redirect,url_for
from blueprint.email_automatico import email

app = Flask(__name__)


@app.route('/')
def index():   
    return render_template("index.html")


@app.route('/projetos')
def projetos():   
    return render_template("projetos.html")


@app.route('/ads')
def ads():   
    return render_template("ADS.html")

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




