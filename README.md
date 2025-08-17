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
│                 └── DataFrameUtils.scala # Fonctions utilitaires
│    └── ressources
│       └── application.config # Fichier de configuration d'environnement
│
│── build.sbt # Fichier de configuration SBT
│── README.md # Documentation du projet
│── gitignore # Fichiers et dossiers à ignorer
 </pre>

## Contributions

`src/main/scala/Main.scala`

1- Initialisation de la SparkSession

![Spark](https://kuikopsimages.s3.eu-north-1.amazonaws.com/spark1.png)

2- Chargment du fichier `sale_data.csv` à partir d'une bucket S3 dans un dataframe Spark

![bucket](https://kuikopsimages.s3.eu-north-1.amazonaws.com/aws-s3-1.png)

3- Nettoyer les données en supprimant les valeurs nulles puis charger les données dans un nouveau dataframe

![clean-data](https://kuikopsimages.s3.eu-north-1.amazonaws.com/clean.png)

4- Calcule du total sales (chiffre d’affaires) par catégorie de produit puis charger les données dans un nouveau dataframe

![totale-sales](https://kuikopsimages.s3.eu-north-1.amazonaws.com/totale-sales.png)

5- Calcule des 5 Top produits par ventes puis charger les données dans un nouveau dataframe

![top-produits](https://kuikopsimages.s3.eu-north-1.amazonaws.com/top-produits.png)

6- Calcule des totales des ventes par mois puis charger les données dans un nouveau dataframe

![top-produits](https://kuikopsimages.s3.eu-north-1.amazonaws.com/sales-monthly.png)

7- Uploader les datasets (cleanedDF, topProducts, salesByMonth) dans des fichiers csv (cleaned_data.csv, topProducts.csv, salesByMonth.csv) sur une bucket s3

![upload](https://kuikopsimages.s3.eu-north-1.amazonaws.com/upload.png)

`src/main/scala/utils/DataFrameUtils.scala`

1- Checker les valeurs nulls dans un dataset

![check-nulls](https://kuikopsimages.s3.eu-north-1.amazonaws.com/check-nulls.png)

`src/main/resources/application.config`

1- Variables de configurations AWS

![AWS](https://kuikopsimages.s3.eu-north-1.amazonaws.com/config.png)

2- Chargement des variables de configurations AWS

![VAR-AWS](https://kuikopsimages.s3.eu-north-1.amazonaws.com/var-config.png)

`src/build.sbt`

1- Ajout des packages

![sbt](https://kuikopsimages.s3.eu-north-1.amazonaws.com/sbt.png)
