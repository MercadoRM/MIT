# Python program to read
import re
import sqlite3 
import os

if __name__ == "__main__":
  data_folder = "Prueba/"
  base_folder = "Salida/"
  ####Conexion a base d datos
  #conn = sqlite3.connect("gamehouse.db")

  queries = [ "INSERT INTO sjug_juego (titulo,descripcion,lanzamiento) VALUES (?,?,?)",
###OBTENER ID DEL JUEGO
"SELECT id_juego FROM sjug_juego WHERE titulo = ?",#Obtener id_juego

####### INSERTAR GENERO
"SELECT id_genero FROM sjug_genero WHERE nombre LIKE ?",#Obtener id de cada genero que tiene el juego
"INSERT INTO sjug_generos_asociados (juego,genero) VALUES (?,?)", #Mandar ids

####### INSERTAR PLATAFORMA
"SELECT id_plataforma FROM sjug_plataforma WHERE nombre = ?",
"INSERT INTO sjug_plataformas_asociados (juego,plataforma) VALUES (?,?)",

####### INSERTAR COMPANIA
"SELECT id_compania FROM sjug_compania WHERE nombre = ?",
"INSERT INTO sjug_compania_asociados (juego,compania) VALUES (?,?)",

####### INSERTAR IMAGEN
"INSERT INTO sjug_imagen (referencia,alt,juego) VALUES (?,?,?)"
]
 
  for filename in os.listdir(data_folder): #Open each file
    conn = sqlite3.connect("gamehouse.db")
    document_name = data_folder + filename
    document = open(document_name, encoding = "utf-8")
    #document = open(document_name, encoding = "utf-8")    
    games = document.read() #Take the document as a simple string
    document.close()
    cr = conn.cursor()
    while games != "":
      if(games == "\n"):
        break
      ### [titulo,compañia,año,genero,plataforma,descripcion,imagen, resto_del_texto]
      splited = games.split("|",7)
      games = splited[7] ##Resto cadena
      generos = list(set(splited[3].split(",")))
      generos = [genero.strip() for genero in generos] ##Remove whitespaces
      generos = [genero for genero in generos if genero] ## Remove empty elem.
      plataformas = list(set(splited[4].split(",")))
      #### INSERT DE JUEGOS
      t = (splited[0],splited[6],splited[2]) #titu,desc,anio
      cr.execute(queries[0],t)
      ### OBTENER ID JUEGO
      cr.execute(queries[1],(splited[0],))
      id_juego = cr.fetchone()[0] #Return a row
      print("\n\n********INSERTANDO JUEGO********\n\n")
      print("Id del juego:",id_juego)
      print("Juego:"+splited[0])
      ### OBTENER ID DE LOS GENEROS
      id_generos = []
      print("Generos:",generos)
      for genero in generos:
        cr.execute(queries[2],("%"+genero+"%",))
        id_generos.append(cr.fetchone()[0])
      print("Id de generos:",id_generos)
      ### INSERTAR GENEROS DEL JUEGO
      for idg in id_generos:
       print("Id del genero a insertar:",tipo)
       t = (id_juego,tipo)
       cr.execute(queries[3],t)
    cr.close()
    conn.commit()    
    conn.close()  

