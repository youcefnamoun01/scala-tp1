# TP Scala – Data Processing with Spark

Ce projet est un **TP en Scala** dont l’objectif est de traiter un jeu de données de ventes (`sale_data.csv`) à l’aide du langage de programmation **Scala** et du framework **Apache Spark**.

## Travail demandé

Un programme Scala qui doit :

1. Lire le dataset `sale_data.csv` et le charger dans un **DataFrame** ou **Dataset**.
2. Nettoyer les données en supprimant les valeurs **nulles** ou manquantes.
3. Calculer le **chiffre d’affaires total par catégorie de produit**.
4. Identifier les **5 produits avec le chiffre d’affaires le plus élevé**.
5. Calculer le **chiffre d’affaires moyen par mois**.
6. Sauvegarder les résultats sous forme de fichiers **CSV** :
   - `cleaned_data.csv` : données nettoyées
   - `topProducts.csv` : top 5 des produits les plus rentables
   - `salesByMonth.csv` : ventes moyennes par mois

## Outils utilisés

- **Scala** : langage principal du projet
- **VS Code** : éditeur de code
- **Metals** : extension VS Code pour créer et gérer des projets Scala avec toutes les dépendances
- **sbt** : outil de build et gestion des dépendances
- **Apache Spark** (`spark-core`, `spark-sql`) : traitement et analyse des données
- **Hadoop** : support pour les systèmes de fichiers distribués
- **AWS (hadoop-aws)** : compatibilité avec le stockage S3
- **Typesafe Config** : gestion des fichiers de configuration (`application.conf`)

## Structure du projet

<pre>TP-Scala/
│
│── src/
│ └── main/scala/
│              └── Main.scala # Code principal
│              └── utils
│                 └── utils # Fonctions utilitaires
│    └── ressources
│       └── application.config # Fichier de configuration d'environnement
│
│── build.sbt # Fichier de configuration SBT
│── README.md # Documentation du projet
│── gitignore# Fichier pour ignorer la configuration

 </pre>
