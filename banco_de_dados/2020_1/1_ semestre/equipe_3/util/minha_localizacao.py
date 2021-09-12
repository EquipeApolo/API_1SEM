import json
import requests

def get_localizacao_atual_by_ip():
    r = requests.get("http://ipinfo.io/json")
    data = json.loads(r.text)
    return data