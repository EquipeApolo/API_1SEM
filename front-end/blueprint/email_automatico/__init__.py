def email_host_outlook(adm, senha, destinatario, subject, corpo):
    """
    -> Faz o envio de um email, usando o metodo SMTP!
    :param adm: Administrador, email de que vai enviar
    :param senha: Senha do administrador
    :param destinatario: Email de quem vai receber!
    :param subject: Assunto do email
    :param corpo: Conteudo do email 
    """


    import smtplib
    from email.mime.multipart import MIMEMultipart
    from email.mime.text import MIMEText

    try:
      # startar o servidar SMTP
        if adm.find('gmail') >= 0:
            host = "smtp.gmail.com"
            port = "587"
        else:
        # startar o servidar SMTP
            host = "smtp.office365.com"
            port = "587"
        
        login = f"{adm}"
        senha = f"{senha}"

        server = smtplib.SMTP(host, port)

        server.ehlo()
        server.starttls()
        server.login(login,senha)
    except TypeError:
        return False
    except AttributeError:
        return False
    except smtplib.SMTPAuthenticationError:
        erro = 'ERRO! Por favor desative o recurso de segurança do seu email!'
        return erro
        # construir o email tipo MINE
    else:
        corpo1 = f"{corpo}"
        email_msg = MIMEMultipart()
        email_msg['From'] = login
        email_msg['To'] = destinatario
        email_msg['Subject'] = f"{subject}"
        email_msg.attach(MIMEText(corpo1, 'html'))

        # enviar o email tipo MIME no servidor SMTP
        server.sendmail(email_msg['From'], email_msg['To'], email_msg.as_string())

        server.quit()
        return True

#-----------------------------------------------------
def ler(art):
    with open(f'{art}', 'r+') as file:
        return file.read()

				
def email(email_p, nome):
    try:
        email = 'equipe3api2021@outlook.com'
        ddd = ler('.arq.txt')
        corpo = f"""A pessoa {nome}, com o email: {email_p} está interessada em contribuir no 			projeto."""

        d = 'equipe3api2021@outlook.com'
        
    except AttributeError:
        return False

    else:
        if email_p != None:
            e = email_host_outlook(email, ddd, d, 'Pedido de colaboração', corpo)
        else:
            return False
        return True
    


