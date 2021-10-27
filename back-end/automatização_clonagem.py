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
with open(r"C:/Users/thale/Desktop/API/API_1SEM/endereços_github_api.txt") as f:
    for line in f:
        print("clonando endereço: ", line)
        remoteurl= line.rstrip("\n")

        nomePasta = nome_pasta(remoteurl)
        
        localfolder=f"C:/Users/thale/Desktop/API/API_1SEM/teste_clonagem/" +nomePasta

        myrepo = git.Repo.clone_from(remoteurl, localfolder, env={"GIT_SSH_COMMAND": 'ssh -i C:/Users/jenny/.ssh/id_rsa'})





