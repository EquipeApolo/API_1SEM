import git

def nome_pasta(texto):
    count = 0
    texto = texto[19:]
    for l in texto:
        if (l == "/"):
            return texto[:count]
        count = count + 1
    return ""


count = 0
with open(r"endereços_githib_api.txt") as f:
    for line in f:
        print("clonando endereço: ", line)
        remoteurl= line.rstrip("\n")

        nomePasta = nome_pasta(remoteurl)
        
        localfolder="C:/python_teste_lucas6/"+nomePasta

        myrepo = git.Repo.clone_from(remoteurl, localfolder, env={"GIT_SSH_COMMAND": 'ssh -i C:/Users/jenny/.ssh/id_rsa'})
        #myrepo.git.checkout("Equipe3-API/API_1SEM")





