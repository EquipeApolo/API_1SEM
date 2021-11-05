from flask import Flask, render_template, request, redirect,url_for
from blueprint.email_automatico import email_host_outlook, email

app = Flask(__name__)

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




