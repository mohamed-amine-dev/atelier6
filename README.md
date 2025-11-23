# 📘 Application de Gestion d'Absences – Spring Boot

Ce projet est une application web développée avec **Spring Boot**, permettant de gérer les **étudiants**, leurs **départements**, ainsi que leurs **absences**.  
Il utilise **Thymeleaf** pour les vues et suit une architecture MVC propre : *Controllers → Services → Repositories → Entities*.

---


## 📘 Diagramme de classes

Voici le diagramme UML représentant les relations entre les entités :

![Diagramme de classes](src/main/resources/static/css/images/gestion_abscence.png)

---

## 📦 Architecture du projet

- **Etudiant** : gère les informations d’un étudiant
- **Departement** : regroupe les étudiants par département
- **Abscence** : stocke les absences avec module, date et étudiant associé

---

## 🏗️ Structure du projet

```
src/main/java/gestionabscence
│
├── Controllers
│ ├── AbscenceController
│ ├── DepartementController
│ └── EtudiantController
│
├── DTOs
│ ├── AbscenceUpdate
│ ├── EtudiantCreate
│ ├── EtudiantForm
│ ├── EtudiantResponse
│ └── EtudiantUpdate
│
├── Entities
│ ├── Abscence
│ ├── Departement
│ └── Etudiant
│
├── Repositories
│ ├── AbscenceRepo
│ ├── DepartementRepo
│ └── EtudiantRepo
│
├── Services
│ ├── AbscenceServiceImpl
│ ├── DepartementServiceImpl
│ └── EtudiantServiceImpl
│
├── Utils
│ ├── AbscenceService
│ ├── DepartementService
│ └── EtudiantService
│
└── GestionAbscenceApplication

```

### Dossier Templates

```
src/main/resources/templates
│
├── abscence
│ ├── add.html
│ ├── edit.html
│ └── list.html
│
├── departement
│ ├── add.html
│ ├── detail.html
│ ├── edit.html
│ └── list.html
│
├── etudiant
│ ├── add.html
│ ├── edit.html
│ └── list.html
│
└── fragments
├── header.html
├── footer.html
└── layout.html
```

### Ressources statiques
```
src/main/resources/static
└── style.css
```

---

## 🚀 Fonctionnalités

### ✔️ Gestion des étudiants
- Ajouter un étudiant
- Modifier un étudiant
- Supprimer un étudiant
- Lister tous les étudiants

### ✔️ Gestion des départements
- Ajouter un département
- Modifier un département
- Voir les étudiants d’un département
- Lister tous les départements

### ✔️ Gestion des absences
- Ajouter une absence
- Modifier une absence
- Supprimer une absence
- Lister toutes les absences
- Ajouter directement une absence depuis la liste des étudiants  
  *(le système récupère automatiquement l’ID de l’étudiant)*

---

## 🛠️ Technologies utilisées

| Technologie | Description |
|------------|-------------|
| **Java 17+** | Langage principal |
| **Spring Boot 3** | Framework backend |
| **Spring MVC** | Architecture web |
| **Spring Data JPA** | Couche repository |
| **MySQL** | Base de données |
| **Hibernate** | ORM |
| **Thymeleaf** | Moteur de template |
| **Bootstrap 5** | Style front-end |
| **Maven** | Gestion des dépendances |

---

## ⚙️ Configuration de la base de données

Dans `application.properties` :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestionabsences
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=create // et après modifier en none pour ne perte pas vos données
spring.jpa.show-sql=true
```
# Lancer l'application
## Cloner le projet
```
git clone https://github.com/SalahKhazri/Atelier6.git
```
## Installer les dépendances
```
mvn clean install
```

## Démarrer l'application
```
mvn spring-boot:run
```

## Accéder à l'application

```
http://localhost:9090/Etudiant/allEtudiant
```

## Aperçu des pages
```
/Etudiant/allEtudiant : Liste des étudiants

/Departement/allDepartement : Liste des départements

/Abscence/all : Liste des absences

/abscences/add : Ajouter une absence à un étudiant

```
