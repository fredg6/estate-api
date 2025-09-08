# Estate API

Ce projet a été généré avec [Spring Initializr](https://start.spring.io/) (Spring Boot 3.5.4 / Java 21)

## Cloner le dépôt du projet
> git clone https://github.com/fredg6/estate-api

## Installation de Java

* Télécharger le JDK 21 par exemple [ici](https://adoptium.net/fr/temurin/releases), puis l'installer
* Créer la variable d'environnement système suivante :
  
|    Nom    |                   Valeur                   |
|:---------:|:------------------------------------------:|
| JAVA_HOME | <chemin_vers_le_répertoire_d_installation> |

* Ajouter à la variable d'environnement ```Path``` la valeur ```<chemin_vers_le_répertoire_d_installation>\bin``` (exemple sous Windows)

## Installation de la base de données

* Pour installer le SGBD MySql en version 8.0.43, suivre ce [guide OpenClassrooms très complet :)](https://openclassrooms.com/fr/courses/6971126-implementez-vos-bases-de-donnees-relationnelles-avec-sql/7152681-installez-le-sgbd-mysql)
* Créer la base de données en exécutant dans le client en ligne de commande MySql (exemple sous Windows) :
> mysql> CREATE DATABASE <nom_de_la_base>;\
> mysql> USE DATABASE <nom_de_la_base>;\
> mysql> source <chemin_vers_le_projet>\src\main\resources\sql\script.sql

**En cas d'erreur sur la commande ```source```, placer le fichier à la racine du lecteur par exemple**
* Créer les variables d'environnement système suivantes :
  
 |        Nom        |                    Valeur                    |
 |:-----------------:|:--------------------------------------------:|
 |   DATABASE_URL    | jdbc:mysql://localhost:3306/<nom_de_la_base> |
 | DATABASE_USERNAME |             root _(par défaut)_              |
 | DATABASE_PASSWORD |                <mot_de_passe>                |

## Stockage des images

Le stockage des images des offres de location s'effectue sur la plateforme [Cloudinary](https://cloudinary.com/)
Il est nécessaire de [créer un compte](https://cloudinary.com/users/register_free) ainsi que la variable d'environnement système suivante pour le bon fonctionnement de l'application :

|        Nom        |                           Valeur                           |
|:-----------------:|:----------------------------------------------------------:|
|  CLOUDINARY_URL   | cloudinary://<your_api_key>:<your_api_secret>@<cloud_name> |

Après connexion au compte créé, les composantes de l'URL ci-dessus sont visibles en cliquant en haut à gauche sur **Home** puis à droite sur le bouton **Go to API Keys**

## Démarrage de l'application

Dans le répertoire du projet, exécuter la commande :
> ./mvnw spring-boot:run

L'application est documentée et testable via Swagger à l'adresse http://localhost:9000/swagger-ui.html 