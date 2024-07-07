## API Gestion des films

### Description

Ce projet consiste en une API RESTful pour la gestion des détails de films, permettant d'ajouter et de récupérer des films avec leurs acteurs associés.

### Fonctionnalités
- **Récupération de films par ID** : Permet de récupérer les détails d'un film spécifique via son ID.
- **Ajout de nouveaux films** : Permet d'ajouter un nouveau film s'il n'existe pas déjà dans la base de données.


### Technologies Utilisées
- **Java 17** 
- **Spring Boot 3.3.1** 
- **Spring Data JPA** 
- **H2 Database** 
- **JUnit 5** 
- **Mockito** 
- **MapStruct** 
- **Lombok** 
- **Maven** 
- **Spring Test** : Pour les tests d'intégration.
- **Postman** : Pour tester les API de manière interactive.

### Structure du projet
- `src/main/java` : Code source principal.
- `src/test/java` : Code des tests.
- `postman` : Contient la collection Postman pour tester les API.

### Prérequis
- **JDK 17** ou supérieur.
- **Maven** pour la gestion des dépendances et la construction du projet.

### Installation et Lancement
1. Clonez le dépôt :
    ```bash
    git clone https://github.com/islimani/film-api.git
    ```
2. Accédez au répertoire du projet :
    ```bash
    cd api
    ```
3. Construisez le projet avec Maven :
    ```bash
    mvn clean install
    ```
4. Démarrez l'application :
    ```bash
    mvn spring-boot:run
   ```

## Profils de Développement et de Production

### Profil `dev`
Le profil `dev` est utilisé pour le développement et les tests locaux. Il est configuré pour utiliser une base de données H2 en mémoire, ce qui permet une configuration légère et rapide. Ce profil active également des fonctionnalités de débogage et des logs plus détaillés pour faciliter le développement.

**Configuration :**
- Base de données H2 en mémoire.
- Logs en mode `debug` pour plus de détails.
- Accès à la console H2 pour vérifier les données.
- Chargement des fichiers `application-dev.yml`.

**Utilisation :**
Pour lancer l'application avec le profil `dev` :
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Profil `prod`
Le profil `prod` est destiné à la production et utilise une configuration plus robuste et sécurisée. Il est généralement configuré pour utiliser une base de données persistante comme MySQL ou PostgreSQL et désactive les fonctionnalités de débogage pour des raisons de sécurité et de performance.

**Configuration :**
- Base de données persistante (Dans notre test, on utilise toujours du H2).
- Logs en mode info pour des informations générales.
- Optimisations pour la performance.
- Chargement des fichiers application-prod.yml.

**Utilisation :**
Pour lancer l'application avec le profil `prod` :
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

## Endpoints
### GET /api/film/{id}
- **Description** : Récupère les détails d'un film par son ID.
- **Réponse** :
    ```json
    {
        "id": 1,
        "titre": "Inception",
        "description": "A mind-bending thriller",
        "acteurs": [
            {
                "id": 1,
                "nom": "DiCaprio",
                "prenom": "Leonardo"
            }
        ]
    }
    ```

### POST /api/film
- **Description** : Ajoute un nouveau film.
- **Corps de la requête** :
    ```json
    {
        "titre": "Inception",
        "description": "A mind-bending thriller",
        "acteurs": [
            {
                "nom": "DiCaprio",
                "prenom": "Leonardo"
            }
        ]
    }
    ```
- **Réponse** :
    ```json
    {
        "id": 1,
        "titre": "Inception",
        "description": "A mind-bending thriller",
        "acteurs": [
            {
                "id": 1,
                "nom": "DiCaprio",
                "prenom": "Leonardo"
            }
        ]
    }

## Accès à la Base de Données H2
### Configuration de H2
L'application utilise une base de données H2 en mémoire pour le stockage des données. H2 est une base de données légère et rapide qui est idéale pour les tests et les applications simples.

### Accéder à la Console Web H2
1. Assurez-vous que l'application est en cours d'exécution.
2. Accédez à la console H2 via votre navigateur à l'adresse suivante :
    ```
    http://localhost:8080/h2-console
    ```
3. Utilisez les informations de connexion suivantes :
   - **JDBC URL** : `jdbc:h2:mem:devdb/proddev` : selon le profil séléctionné
   - **Nom d'utilisateur** : `sa`
   - **Mot de passe** : (à récupérer depuis les fichiers de paramètrage .yml)

4. Cliquez sur "Connect" pour accéder à la console. Vous pourrez alors interroger et gérer la base de données directement via l'interface web.



## Utilisation de la Collection Postman

Une collection Postman est fournie pour valider les API du projet. Pour l'utiliser :

1. Ouvrez Postman.
2. Importez la collection en allant dans `File > Import` et sélectionnez `postman/FilmAPI.postman_collection.json`.
3. Exécutez les tests pour vérifier les API.
   Nom du Projet
   API de gestion des films






