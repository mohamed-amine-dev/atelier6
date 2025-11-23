# 📚 Système de Gestion des Absences - Spring Boot

## 📊 Rapport de Projet

### 🎯 Objectif du Projet

Ce projet est une **application web de gestion des absences** développée avec le framework **Spring Boot 3**. Il permet aux institutions éducatives (écoles, universités, centres de formation) de :
- Maintenir une base de données centralisée des étudiants
- Organiser les étudiants par département/classe
- Enregistrer et consulter les absences de manière efficace
- Générer des rapports d'absence par étudiant

### 📋 Contexte et Justification

**Problématique :** 
Les institutions éducatives ont besoin d'un système performant pour gérer les absences des étudiants. Les méthodes traditionnelles (cahiers papier, feuilles Excel) sont inefficaces et sujettes aux erreurs.

**Solution proposée :**
Une application web moderne, fiable et sécurisée permettant la gestion centralisée des absences avec une interface utilisateur intuitive.

---

## 🎓 Public cible

- 👨‍💼 Administrateurs d'école/université
- 👨‍🏫 Professeurs et formateurs
- 📋 Personnel administratif
- 📊 Direction pédagogique

---

## 💡 Fonctionnalités principales

### 👤 **Gestion des Étudiants**
L'application permet de :
- ✅ Ajouter des nouveaux étudiants avec leurs informations (nom, prénom, date de naissance, classe)
- ✅ Modifier les données personnelles des étudiants
- ✅ Supprimer les dossiers des étudiants (anciens étudiants)
- ✅ Afficher la liste complète avec recherche et filtrage
- ✅ Assigner automatiquement les étudiants à des départements

### 🏢 **Organisation par Département**
- ✅ Créer des départements (Informatique, Mathématiques, Sciences, etc.)
- ✅ Modifier les informations des départements
- ✅ Consulter les détails d'un département
- ✅ Voir tous les étudiants d'un département
- ✅ Supprimer des départements (si vides)

### 📋 **Enregistrement des Absences**
- ✅ Enregistrer les absences avec module, date et étudiant
- ✅ Modifier une absence enregistrée
- ✅ Supprimer une absence (corrections d'erreurs)
- ✅ Consulter toutes les absences du système
- ✅ Consulter les absences d'un étudiant spécifique
- ✅ Ajouter rapidement une absence depuis la page d'un étudiant

---

## 🏗️ Architecture technique

### **Architecture MVC (Model-View-Controller)**

L'application suit le pattern MVC qui séparate les responsabilités en trois couches :

```
┌─────────────────────────────────────┐
│   PRESENTATION LAYER                │
│   (Thymeleaf Templates + Bootstrap) │
│   - Pages HTML                      │
│   - Formulaires                     │
│   - Affichage des données           │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│   APPLICATION LAYER                 │
│   (Spring Controllers)              │
│   - Traitement des requêtes HTTP    │
│   - Redirection et routage          │
│   - Validation des entrées          │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│   BUSINESS LOGIC LAYER              │
│   (Services)                        │
│   - Logique métier                  │
│   - Validations complexes           │
│   - Transactions                    │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│   PERSISTENCE LAYER                 │
│   (Repositories + JPA)              │
│   - Accès à la base de données      │
│   - Requêtes SQL                    │
│   - ORM (Hibernate)                 │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│   DATA LAYER                        │
│   (MySQL Database)                  │
│   - Tables                          │
│   - Données persistantes            │
└─────────────────────────────────────┘
```

### **Structure des composants**

```
gestionabscence/
│
├── Controllers/          → Points d'entrée HTTP
│   ├── EtudiantController
│   ├── DepartementController
│   └── AbsenceController
│
├── Services/            → Logique métier
│   ├── EtudiantServiceImpl
│   ├── DepartementServiceImpl
│   └── AbsenceServiceImpl
│
├── Repositories/        → Accès aux données
│   ├── EtudiantRepository
│   ├── DepartementRepository
│   └── AbsenceRepository
│
├── Entities/            → Modèles de données
│   ├── Etudiant
│   ├── Departement
│   └── Absence
│
├── DTOs/                → Objets de transfert
│   ├── EtudiantCreate
│   ├── EtudiantUpdate
│   ├── EtudiantResponse
│   └── AbsenceUpdate
│
└── Utils/               → Interfaces de service
    ├── EtudiantService
    ├── DepartementService
    └── AbsenceService
```

---

## 📊 Modèle de données

### **Entité Etudiant**
Représente un étudiant inscrit dans l'institution.

| Propriété | Type | Description |
|-----------|------|-------------|
| `idEtudiant` | Long | Identifiant unique (PK) |
| `nom` | String | Nom de famille |
| `prenom` | String | Prénom |
| `date` | LocalDate | Date de naissance |
| `classe` | String | Classe/niveau (ex: L1, L2, M1) |
| `departement` | Departement | Département d'affectation (FK) |
| `absences` | List<Absence> | Liste des absences |

### **Entité Departement**
Représente un département/section de l'institution.

| Propriété | Type | Description |
|-----------|------|-------------|
| `idDepartement` | Long | Identifiant unique (PK) |
| `nomDepartement` | String | Nom du département |

### **Entité Absence**
Enregistre une absence d'un étudiant.

| Propriété | Type | Description |
|-----------|------|-------------|
| `idAbsence` | Long | Identifiant unique (PK) |
| `module` | String | Module/matière (ex: Java, Mathématiques) |
| `dateAbsence` | LocalDate | Date de l'absence |
| `etudiant` | Etudiant | Étudiant absent (FK) |

### **Relations entre entités**

```
┌─────────────┐         ┌──────────────┐
│ Departement │◄────────┤   Etudiant   │
└─────────────┘ 1     N └──────────────┤
                             │ 1     N │
                        ┌────▼─────────┐
                        │   Absence    │
                        └──────────────┘
```

- **Departement → Etudiant** : Un département peut avoir plusieurs étudiants (1-N)
- **Etudiant → Absence** : Un étudiant peut avoir plusieurs absences (1-N)

---

## 🛠️ Stack technologique

| Composant | Technologie | Version | Justification |
|-----------|-------------|---------|---------------|
| **Langage** | Java | 17+ | Performant, sécurisé, portable |
| **Framework Web** | Spring Boot | 3.x | Productivité, écosystème riche |
| **Architecture Web** | Spring MVC | - | Séparation MVC claire |
| **ORM** | Hibernate/JPA | - | Mappage objet-relationnel |
| **Accès données** | Spring Data JPA | - | Repository pattern |
| **Moteur template** | Thymeleaf | 3.x | Intégration Spring, sécurité |
| **CSS Framework** | Bootstrap | 5.3 | Design responsive, moderne |
| **Base de données** | MySQL | 8.0+ | Fiabilité, performance |
| **Build** | Maven | 3.8+ | Gestion dépendances standard |
| **Utilitaires** | Lombok | - | Réduction code boilerplate |

---

## 🔄 Flux de l'application

### **Cycle de vie d'une requête**

```
1. Utilisateur accède à l'application
         │
         ▼
2. Browser envoie requête HTTP
         │
         ▼
3. Spring Router dirige vers le Controller
         │
         ▼
4. Controller valide et appelle le Service
         │
         ▼
5. Service applique la logique métier
         │
         ▼
6. Repository accède à la base de données
         │
         ▼
7. MySQL traite la requête
         │
         ▼
8. Les données sont retournées
         │
         ▼
9. Thymeleaf rend le template HTML
         │
         ▼
10. Browser affiche la page
```

---

## 📈 Cas d'usage principaux

### **Cas 1 : Enregistrer un nouvel étudiant**
1. L'administrateur clique sur "Ajouter un étudiant"
2. Remplit le formulaire (nom, prénom, date, classe)
3. Soumet le formulaire
4. Le système valide et enregistre en base de données
5. Affiche la liste mise à jour

### **Cas 2 : Enregistrer une absence**
1. L'enseignant accède à la liste des absences
2. Clique sur "Ajouter une absence"
3. Sélectionne l'étudiant et le module
4. Entre la date
5. Valide et enregistre
6. Le système confirme l'enregistrement

### **Cas 3 : Consulter les absences d'un étudiant**
1. L'administrateur accède à la liste des étudiants
2. Clique sur le bouton "Absences" d'un étudiant
3. Le système filtre et affiche les absences
4. Possibilité de modifier ou supprimer

---

## ✨ Points forts du projet

### **1. Sécurité**
- ✅ Validation des entrées côté serveur
- ✅ Protection contre les injections SQL (JPA)
- ✅ Transactions ACID garanties

### **2. Scalabilité**
- ✅ Architecture modulaire
- ✅ Base de données optimisée
- ✅ Peut supporter des milliers d'étudiants

### **3. Maintenabilité**
- ✅ Code bien organisé et documenté
- ✅ Séparation claire des responsabilités
- ✅ Facile à étendre avec nouvelles fonctionnalités

### **4. Performance**
- ✅ Requêtes SQL optimisées
- ✅ Cache Hibernate
- ✅ Lazy loading des données

### **5. Expérience utilisateur**
- ✅ Interface moderne avec Bootstrap
- ✅ Responsive design
- ✅ Navigation intuitive
- ✅ Formulaires validés et conviviaux

---

## 🚀 Déploiement et production

### **Environnement de développement**
```
Local Machine → IDE → Maven Build → Local MySQL → Browser
```

### **Environnement de production**
```
Serveur Linux → Docker Container → Production MySQL → Load Balancer → Browser
```

### **Recommandations de sécurité en production**
- ✅ Activer HTTPS/SSL
- ✅ Utiliser des variables d'environnement pour les credentials
- ✅ Mettre en place une authentification
- ✅ Configurer les rôles et permissions
- ✅ Activer les logs d'audit
- ✅ Faire des sauvegardes régulières

---

## 📈 Évolutions futures possibles

### **Court terme (v1.1)**
- 🔐 Système d'authentification et autorisation
- 📊 Tableaux de bord avec statistiques
- 📧 Notifications par email
- 🔍 Recherche avancée et filtres

### **Moyen terme (v2.0)**
- 📱 Application mobile (React Native/Flutter)
- 📊 Export rapports (PDF, Excel)
- 🔔 Système de notifications en temps réel
- 📅 Calendrier d'absences
- 🎯 Justifications d'absence

### **Long terme (v3.0)**
- 🤖 Analyse prédictive (détecter décrochage)
- 🌐 API publique pour intégrations tierces
- ☁️ Déploiement multi-cloud
- 🔐 Blockchain pour certification

---

## 📚 Ressources et documentation

- **Spring Boot Official Docs** : https://spring.io/projects/spring-boot
- **Thymeleaf Guide** : https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html
- **Bootstrap Documentation** : https://getbootstrap.com/docs/5.3/
- **MySQL Reference** : https://dev.mysql.com/doc/
- **JPA/Hibernate** : https://hibernate.org/orm/documentation/

---

## 👥 Contribution

Les contributions sont les bienvenues ! Pour contribuer :

1. 🍴 Forkez le projet
2. 🌿 Créez une branche (`git checkout -b feature/NewFeature`)
3. 📝 Committez vos changements (`git commit -m 'Add NewFeature'`)
4. 🔄 Poussez vers la branche (`git push origin feature/NewFeature`)
5. 🔃 Ouvrez une Pull Request

---

## 📄 Licence

Ce projet est sous licence MIT. Consultez le fichier `LICENSE` pour plus de détails.

---

## 📧 Support

Pour toute question, problème ou suggestion :
- 📍 **Issues GitHub** : Ouvrir une issue
- 📧 **Email** : salah.khazri@example.com
- 💬 **Discussions** : Participer aux discussions du projet

---

## 🎉 Remerciements

Merci à :
- La communauté Spring Boot
- Les contributeurs du projet
- Les utilisateurs pour leurs retours
- L'équipe pédagogique pour les spécifications

---

**Projet créé :** Novembre 2025  
**Statut :** En développement actif  
**Version actuelle :** 1.0.0  
**Dernière mise à jour :** 23 Novembre 2025

---

## 📋 Checklist d'utilisation

- [ ] Installer les prérequis (Java 17+, MySQL 8.0+, Maven 3.8+)
- [ ] Cloner le repository
- [ ] Configurer la base de données
- [ ] Installer les dépendances (`mvn clean install`)
- [ ] Démarrer l'application (`mvn spring-boot:run`)
- [ ] Accéder à http://localhost:9090/Etudiant/allEtudiant
- [ ] Tester toutes les fonctionnalités
- [ ] Consulter la documentation complète



---

## 🎯 Vue d'ensemble

Ce système permet aux administrateurs de :
- ✅ Gérer une base de données d'étudiants
- ✅ Organiser les étudiants par département
- ✅ Enregistrer et consulter les absences
- ✅ Générer des rapports d'absence par étudiant

---

## 📊 Diagramme de classes

Voici le diagramme UML représentant les relations entre les entités :

![Diagramme de classes](src/main/resources/static/css/images/gestion_absence.png)

---

## 🏗️ Architecture du projet

### Entités principales

| Entité | Description |
|--------|------------|
| **Etudiant** | Représente un étudiant avec ses informations personnelles |
| **Departement** | Représente un département regroupant plusieurs étudiants |
| **Absence** | Enregistre une absence avec le module, la date et l'étudiant concerné |

### Structure des répertoires

```
src/main/java/gestionabscence/
│
├── Controllers/
│   ├── EtudiantController.java
│   ├── DepartementController.java
│   └── AbsenceController.java
│
├── DTOs/
│   ├── EtudiantCreate.java
│   ├── EtudiantUpdate.java
│   ├── EtudiantResponse.java
│   ├── EtudiantForm.java
│   └── AbsenceUpdate.java
│
├── Entities/
│   ├── Etudiant.java
│   ├── Departement.java
│   └── Absence.java
│
├── Repositories/
│   ├── EtudiantRepository.java
│   ├── DepartementRepository.java
│   └── AbsenceRepository.java
│
├── Services/
│   ├── AbsenceServiceImpl.java
│   ├── DepartementServiceImpl.java
│   └── EtudiantServiceImpl.java
│
├── Utils/ (Interfaces de service)
│   ├── AbsenceService.java
│   ├── DepartementService.java
│   └── EtudiantService.java
│
└── GestionAbscenceApplication.java
```

### Dossier Templates (Thymeleaf)

```
src/main/resources/templates/
│
├── etudiant/
│   ├── add.html (Ajouter un étudiant)
│   ├── edit.html (Modifier un étudiant)
│   └── list.html (Liste des étudiants)
│
├── departement/
│   ├── add.html (Ajouter un département)
│   ├── edit.html (Modifier un département)
│   ├── detail.html (Détails d'un département)
│   └── list.html (Liste des départements)
│
├── absence/
│   ├── add.html (Ajouter une absence)
│   ├── edit.html (Modifier une absence)
│   └── list.html (Liste des absences)
│
└── fragments/
    ├── header.html (En-tête avec navigation)
    ├── footer.html (Pied de page)
    └── layout.html (Mise en page principale)
```

### Ressources statiques

```
src/main/resources/static/
└── css/
    ├── style.css (Feuille de style personnalisée)
    └── images/
        └── gestion_absence.png (Diagramme UML)
```

---

## 🚀 Fonctionnalités

### 👤 Gestion des étudiants
- ➕ Ajouter un nouvel étudiant
- ✏️ Modifier les informations d'un étudiant
- 🗑️ Supprimer un étudiant
- 📋 Afficher la liste complète des étudiants

### 🏢 Gestion des départements
- ➕ Créer un nouveau département
- ✏️ Modifier un département
- 🔍 Consulter les détails d'un département
- 📋 Afficher tous les départements

### 📋 Gestion des absences
- ➕ Enregistrer une nouvelle absence
- ✏️ Modifier une absence
- 🗑️ Supprimer une absence
- 📊 Consulter toutes les absences
- 🎯 Ajouter rapidement une absence depuis la liste des étudiants

---

## 🛠️ Stack technologique

| Technologie | Version | Rôle |
|------------|---------|------|
| **Java** | 17+ | Langage principal |
| **Spring Boot** | 3.x | Framework backend |
| **Spring MVC** | - | Architecture web |
| **Spring Data JPA** | - | Couche d'accès aux données |
| **Hibernate** | - | Mappage Objet-Relationnel |
| **MySQL** | 8.0+ | Base de données relationnelle |
| **Thymeleaf** | 3.x | Moteur de template côté serveur |
| **Bootstrap** | 5.3 | Framework CSS front-end |
| **Lombok** | - | Réduction du code boilerplate |
| **Maven** | 3.8+ | Gestionnaire de dépendances |

---

## 📋 Prérequis

Avant de commencer, assurez-vous d'avoir installé :
- ☕ **Java 17+** ou supérieur
- 🗄️ **MySQL 8.0+** 
- 📦 **Maven 3.8+**
- 🔧 **Git**

---

## ⚙️ Configuration de la base de données

### Créer la base de données

```sql
CREATE DATABASE gestionabsences CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### Configurer les connexions

Modifiez le fichier `application.properties` :

```properties
# Connexion MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/gestionabsences?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=votre_mot_de_passe

# Configuration Hibernate/JPA
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true

# Port du serveur
server.port=9090
server.servlet.context-path=/

# Configuration des logs
logging.level.root=INFO
logging.level.gestionabscence=DEBUG
```

**⚠️ Note importante :**
- `create-drop` : Crée les tables au démarrage et les supprime à l'arrêt (développement)
- Après le premier démarrage, changez à `validate` pour préserver vos données

---

## 🚀 Installation et démarrage

### 1️⃣ Cloner le repository

```bash
git clone https://github.com/SalahKhazri/Atelier6.git
cd Atelier6
```

### 2️⃣ Installer les dépendances Maven

```bash
mvn clean install
```

### 3️⃣ Démarrer l'application

```bash
mvn spring-boot:run
```

Ou avec votre IDE favori (Eclipse, IntelliJ IDEA, VS Code).

### 4️⃣ Accéder à l'application

Ouvrez votre navigateur et accédez à :

```
http://localhost:9090/Etudiant/allEtudiant
```

---

## 📍 Routes principales de l'application

| Route | Description |
|-------|-------------|
| `/Etudiant/allEtudiant` | Liste de tous les étudiants |
| `/Etudiant/add` | Formulaire d'ajout d'étudiant |
| `/Etudiant/edit/{id}` | Formulaire de modification d'étudiant |
| `/Etudiant/delete/{id}` | Supprimer un étudiant |
| `/Departement/allDepartement` | Liste de tous les départements |
| `/Departement/add` | Formulaire d'ajout de département |
| `/Departement/edit/{id}` | Formulaire de modification de département |
| `/Departement/delete/{id}` | Supprimer un département |
| `/Absence/all` | Liste de toutes les absences |
| `/Absence/add` | Formulaire d'ajout d'absence |
| `/Absence/edit/{id}` | Formulaire de modification d'absence |
| `/Absence/delete/{id}` | Supprimer une absence |

---

## 🎨 Interface utilisateur

L'application dispose d'une interface utilisateur moderne et responsive :

- **En-tête de navigation** : Navigation facile entre les sections
- **Tableau de bord** : Vue d'ensemble des données
- **Formulaires intuitifs** : Ajout et modification des données
- **Responsive Design** : Compatible mobile et desktop
- **Bootstrap 5** : Design professionnel et moderne

---

## 📚 Modèle de données

### Table: etudiant

```sql
CREATE TABLE etudiant (
  idEtudiant BIGINT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(50) NOT NULL,
  prenom VARCHAR(50) NOT NULL,
  date DATE,
  classe VARCHAR(50),
  departement_id BIGINT,
  FOREIGN KEY (departement_id) REFERENCES departement(id_departement)
);
```

### Table: departement

```sql
CREATE TABLE departement (
  id_departement BIGINT AUTO_INCREMENT PRIMARY KEY,
  nomDepartement VARCHAR(100) NOT NULL
);
```

### Table: absence

```sql
CREATE TABLE absence (
  idAbsence BIGINT AUTO_INCREMENT PRIMARY KEY,
  module VARCHAR(100) NOT NULL,
  date_absence DATE NOT NULL,
  id_etudiant BIGINT NOT NULL,
  FOREIGN KEY (id_etudiant) REFERENCES etudiant(idEtudiant)
);
```

---

## 🐛 Dépannage

### Erreur de connexion MySQL
```
Communications link failure
```
**Solution :** Assurez-vous que MySQL est en cours d'exécution sur le port 3306.

### Erreur de base de données non trouvée
```
Unknown database 'gestionabsences'
```
**Solution :** Créez la base de données avec la commande SQL fournie ci-dessus.

### Erreur de port déjà utilisé
```
Port 9090 already in use
```
**Solution :** Modifiez le port dans `application.properties` ou arrêtez l'application utilisant ce port.

---

## 💡 Conseils de développement

1. **Utiliser Lombok** : Réduit le code boilerplate (getters, setters, constructeurs)
2. **Respecter l'architecture MVC** : Maintient le code organisé et testable
3. **Utiliser les DTOs** : Permet de découpler l'API des entités
4. **Ajouter des validations** : Utilisez les annotations `@Valid` et `@Validated`
5. **Faire des tests unitaires** : Utilisez JUnit et Mockito

---

## 👥 Contribution

Les contributions sont les bienvenues ! Si vous souhaitez contribuer :

1. 🍴 Forkez le projet
2. 🌿 Créez une branche (`git checkout -b feature/AmazingFeature`)
3. 📝 Committez vos changements (`git commit -m 'Add AmazingFeature'`)
4. 🔄 Poussez vers la branche (`git push origin feature/AmazingFeature`)
5. 🔃 Ouvrez une Pull Request

---

## 📄 Licence

Ce projet est sous licence MIT. Consultez le fichier `LICENSE` pour plus de détails.

---

## 📧 Support et contact

Pour toute question ou problème :
- 📍 GitHub Issues : [Ouvrir une issue](https://github.com/SalahKhazri/Atelier6/issues)
- 📧 Email : salah.khazri@example.com

---

## 🎉 Remerciements

Merci à tous les contributeurs et à la communauté Spring Boot !

---

**Dernière mise à jour :** 23 Novembre 2025  
**Version :** 1.0.0
