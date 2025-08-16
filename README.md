# TP Scala – Data Processing with Spark

## 📌 Description

Ce projet est un **TP de Big Data en Scala** dont l’objectif est de traiter un jeu de données de ventes (`sale_data.csv`) à l’aide d’**Apache Spark**.  
Il met en pratique les concepts de **data cleaning**, **agrégation** et **analyse de données** en Scala.

## 🎯 Objectifs du projet

Le programme Scala doit :

1. Lire le dataset `sale_data.csv` et le charger dans un **DataFrame** ou **Dataset**.
2. Nettoyer les données en supprimant les valeurs **nulles** ou manquantes.
3. Calculer le **chiffre d’affaires total par catégorie de produit**.
4. Identifier les **5 produits avec le chiffre d’affaires le plus élevé**.
5. Calculer le **chiffre d’affaires moyen par mois**.
6. Sauvegarder les résultats sous forme de fichiers **CSV** :
   - `cleaned_data.csv` : données nettoyées
   - `topProducts.csv` : top 5 des produits les plus rentables
   - `salesByMonth.csv` : ventes moyennes par mois

## 🛠️ Technologies utilisées

- **Scala**
- **Apache Spark** (SQL & DataFrame API)
- **sbt** pour la gestion des dépendances et la compilation

## 📂 Structure du projet
