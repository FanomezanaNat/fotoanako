# Projet individuel — Agenda

## Contexte

Vous rejoignez une équipe qui souhaite se doter d'une application de gestion d'agendas d'événements. Aujourd'hui, le suivi des événements planifiés se fait encore à la main — l'objectif est de fournir une véritable application backend pour organiser ces agendas au quotidien : ajouter des événements, retrouver un événement selon différents critères, et avoir une vue d'ensemble des événements à venir.

C'est **votre sujet**, tiré au sort. Il vous est propre : vous le développez seul(e), de A à Z.

## Votre mission

Concevoir et développer, **from scratch**, une application backend Spring Boot pour ce métier.

L'objectif de ce projet n'est pas de découvrir de nouvelles notions, mais de **mobiliser par vous-même l'ensemble de ce qui a été vu depuis le début de la formation** — aussi bien côté Java 21 (records, sealed classes, pattern matching, Optional, Stream API...) que côté Spring Boot (JPA, REST, DTOs, gestion des erreurs, tests...). C'est un projet de synthèse : à vous de choisir comment et où appliquer chaque notion sur votre domaine.

Le choix précis de l'architecture interne et des fonctionnalités exactes vous appartient — ce document ne fixe que le métier, quelques pistes de modélisation et le niveau d'exigence attendu, pas la manière de les implémenter.

## Entités principales *(à titre indicatif)*

Pour situer le métier, voici les entités que l'on retrouve typiquement dans une gestion d'agendas — libre à vous de les enrichir, de les renommer ou d'en ajouter d'autres selon vos choix de conception :

- **Agenda** — nom, propriétaire...
- **Event** — titre, date, lieu, description...

## Contraintes générales

- **Pas de front** : uniquement une API backend Spring Boot, testée
- **API documentée et testable via Swagger** (UI Swagger accessible au démarrage de l'application)
- **Durée** : 1 à 2 semaines
- **Livrables** :
  - un jar exécutable (`java -jar agenda.jar`)
  - un zip contenant l'ensemble des sources

## Attendus

Ces attendus sont **les mêmes pour tous les sujets** du tirage — seul le métier change d'un(e) élève à l'autre.

### Socle obligatoire *(bloquant — sans quoi le projet n'est pas recevable)*

- Domaine métier persisté en base (JPA/Hibernate), avec des relations entre entités
- CRUD complet sur l'entité principale
- Une fonctionnalité de **recherche** au-delà d'un simple "lister tout", sur des critères métier
- Une fonctionnalité de **catalogue/inventaire** : une vue d'ensemble avec tri et/ou agrégat
- Un jeu de données réaliste d'une centaine d'éléments au démarrage
- Une bonne séparation des couches (web / service / accès aux données), avec des DTOs dédiés
- Une gestion cohérente des erreurs métier (exception dédiée + réponse HTTP adaptée)
- Une couverture de tests complète

### Extensions attendues *(ce qui distingue un projet solide d'un projet minimal)*

- Validation des données en entrée
- Une modélisation Java 21 pertinente pour votre domaine (hiérarchie fermée avec `sealed`, pattern matching...)
- Usage de `Optional` là où c'est pertinent
- Usage de la Stream API
- Mettre de la pagination de façon cohérente
- Sécurisation cohérente avec le métier des endpoints, avec un niveau d'accès distinct par rôle

## Modalités de rendu

- Date limite : *à préciser par le formateur*
- Dépôt : jar + zip des sources, selon les modalités communiquées en formation
- Vous serez susceptible de **présenter votre projet devant la classe** et d'expliquer vos choix de conception ainsi que les objectifs que vous vous êtes fixés — préparez-vous à en parler clairement, au-delà du simple fonctionnement du code

---

*Ce sujet est le vôtre — en cas de doute sur une notion ou un choix de conception, c'est un jugement que vous êtes en mesure de faire à ce stade de la formation. N'hésitez pas à documenter vos choix si vous vous écartez d'une lecture littérale d'un point de ce document.*
