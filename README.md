# PhotoNum 

PhotoNum est une application Java qui fait appel à Oracle pour gerer un service de gestion de Photo.
Ce petit ReadMe vous sera utile pour profiter de notre application ;) 


## Installation :
Vous devez procéder en deux étapes.

### Partie Oracle :

Pour créer la base avec toutes les contraintes :
1) Connectez vous sur votre compte SQLplus.
2) Récuperez tout les scripts préalablement téléchargés à partir du dossier Sql_Script et dézippés.
3) Lancez chaque script avec la commande `start nom_fichier `

4) Création de la base de données: start create_tables 
5) Compilation de nos triggers: start create_triggers
6) Peuplement de la base: start insert_tables

Pour tester nos triggers: 
1) start drop_triggers
2) start delete_tables 
3) start create_triggers
4) start test_insert_tables
5) Vous trouverez dans le script test_insert_tables un teste pour chaque trigger

Pour tester les scénarios sur Mysql
1) Ouvrir deux terminaux
2) Se connecter à la base sur les deux terminaux
3) si la base est peuplé on continue sinon on suit les étapes suivantes: start drop_triggers, start delete_tables, start create_triggers, start insert_tables
4) Ouvrir le script scénarios.sql puis lancer chaque écriture ou lecture dans le terminal associé

## Utilisation 
### Partie JDBC : 

1) Ouvrir le projet sous Eclipse.
2) Modifiez le BD.properties avec vos coordonnées.
3) Lancez PhotoNum sous le package Main
4) Profitez !

lors de problèmes juridiques,consultation des informations de stock, commandes, clients.
     
Pour tester les scénarios sur JDBC
1) Excuter la classe scénarios.java sur un terminal 1
2) Choisir un scénario parmi 4
3) Choisir scénario terminal 1
4) Excuter la classe Scénarios.java sur un terminal 2
4) Choisir le même scénario (comme 2)
5) Choisir scénario terminal 2
6) Appuyer sur entrer pour passer les étapes entre deux terminaux en suivant le scénario


## Réalisé par :
ANZID Samya
BENABBOU Halima
BENBRAHIM Yassine
BENZAOUI Mohamed Tahar
LAKHYARI Mohammed Said
