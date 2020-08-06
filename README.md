
# 1. Présentation des fichiers météo
===================================

Nous disposons de fichiers de relevés météo (Open Data) indiquant les températures
par régions ou par dates. Ces fichiers sont structurés de deux manières :
## (1) Par dates : Exemple du fichier dates/Température1997.txt
        Region JAN FEV MAR ...
        Ouest 2,8 8,4 10,3 ...
        Nord-Bassin-Parisien -0,1 7,0 9,3 ...
        Nord-Est -1,5 6,0 8,9 ...
        Centre-Est -0,9 6,9 9,8 ...
        Sud-Ouest 5,5 9,3 11,4 ...
        Sud-Est 7,6 9,7 13,0 ...
        Corse 11,0 11,6 12,5 ...
## (2) Par régions : Exemple du fichier regions/TempératureOuest.txt
        Annee JAN FEV MAR ...
        1997 2,8 8,4 10,3 ...
        1998 6,3 7,4 9,3 ...
        1999 7,7 6,7 9,2 ...
        2000 5,5 8,3 8,1 ...
        2001 6,3 6,8 9,7 ...
        2002 7,7 8,9 9,4 ...
        2003 4,7 5,7 10,6 ...
        2004 6,9 1,0 7,2 ...
        2005 7,2 4,6 8,6 ...
        2006 4,5 4,3 7,5 ...
        2007 8,2 9,1 8,3 ...
        2008 7,7 7,7 8,2 ...
        2009 3,9 5,5 8,2 ...
        2010 3,0 5,2 7,7 ...
        2011 5,7 8,3 9,1 ...
        
Travail à réaliser :
Dans HDFS, créez deux répertoires rdates et rregions. Chargez dans ces répertoires, les fichiers issus respectivement de dates/*.txt et
regions/*.txt.

# 2. Calcul de la température moyenne
===================================

## 2.1. Calcul de la température par région
On souhaite calculer la moyenne des températures par région :

        Centre-Est 11.896666666666667
        Corse 16.65277777777778
        Nord-Bassin-Parisien 11.462222222222222
        Nord-Est 11.131111111111109
        Ouest 12.142222222222221
        Sud-Est 15.234444444444446
        Sud-Ouest 13.374444444444443
        
Travail à réaliser :
### (1) Analyser l’organisation des données dans les fichiers pour choisir l’organisation adaptée (dates ou regions).
### (2) Proposer le schéma du processus Map/Reduce associé qui permet de réaliser le traitement.
### (3) Implanter le schéma proposé en Java.
## 2.2. Calcul de la température par année
On souhaite calculer la moyenne des températures par année :

        1997 13.333333333333334
        1998 12.735714285714288
        1999 13.242857142857144
        2000 13.398809523809524
        2001 13.001190476190475
        2002 13.375
        2003 13.684523809523812
        2004 12.425
        2005 12.799999999999997
        2006 13.517857142857142
        2007 13.26309523809524
        2008 12.879761904761907
        2009 13.267857142857142
        2010 12.178571428571429
        2011 13.811904761904762
** Travail à réaliser : **
### (1) Analyser l’organisation des données dans les fichiers pour choisir l’organisation adaptée (dates ou regions).
### (2) Proposer le schéma du processus Map/Reduce associé qui permet de réaliserle traitement.
### (3) Implanter le schéma proposé en Java.
## 2.3. Fichiers par année comportant des anomalies
Supposons que les fichiers comportent des anomalies (données manquantes).

        Annee JAN FEV MAR ...
        1997 2,8 10,3 ...
        1998 6,3 7,4 9,3 ...
        1999 7,7 6,7 ...

** Travail à réaliser :**
### (1) Proposer un nouveau schéma du processus Map/Reduce précédent qui supporte ces fichiers comportant des anomalies.
### (2) Prendre en compte cet élément sur la base des fichiers java des questions 2.1 et
## 2.2. Le résultat que vous devez obtenir doit correspondre à :

      1997 13.49382716049383
      1998 12.735714285714288
      1999 13.27710843373494
      2000 13.360975609756101
      2001 13.001190476190473
      2002 13.296385542168677
      2003 13.560240963855415
      2004 12.252439024390245
      2005 12.8
      2006 13.517857142857146
      2007 13.196385542168674
      2008 12.247222222222222
      2009 12.630555555555555
      2010 12.19756097560976
      2011 13.811904761904756
      
# 3. Calcul des températures moyennes, maximales et minimales par année On souhaite maintenant calculer, en plus de la température moyenne, les
températures maximales et minimales par année.

** Travail à réaliser :**
## (1) Etendre le schéma du processus Map/Reduce pour calculer, en plus des moyennes, les températures maximales et minimales.
## (2) Modifier les programmes en conséquence. Exemple de résultat souhaité avec les fichiers avec anomalies :

              1997 Moy : 13.493827160493828
              1997 Min : -1.5
              1997 Max : 24.6
              1998 Moy : 12.735714285714286
              1998 Min : 2.7
              1998 Max : 24.5
              1999 Moy : 13.277108433734943
              1999 Min : 1.7
              1999 Max : 25.3
              2000 Moy : 13.360975609756107
              2000 Min : 2.5
              2000 Max : 24.5
# 4. Calcul de la température globale On souhaite maintenant calculer la température globale qui correspond à la moyenne des températures sur toutes les années dans toutes les régions.
===================================

** Travail à réaliser : **
## (1) Proposer un nouveau schéma du processus Map/Reduce.
## (2) Implanter le schéma proposé en Java.

Exemple de résultat souhaité avec les fichiers avec anomalies :
Moyenne globale 13.036713000817661
(3) Etendre la solution pour calculer, en plus, la température maximale ainsi que
la température minimale sur l'ensemble des fichiers.
Exemple de résultat souhaité avec les fichiers avec anomalies :
Moyenne globale 13.036713000817661
Temperature Maximum 27.8
Temperature Minimum -1.5

# 5. Plus ou moins de données....
===================================

Nous allons dans cette question voir une solution permettant d'optimiser les traitementsMap/Reduce.
** Travail à réaliser :**
### (1) Selon la même logique que la question précédente, nous souhaitons désormais renvoyer uniquement les températures maximale et minimale globales.
### (2) Adapter le code précédent afin d'obtenir le résultat escompté. Observer le nombre de données échangées entre le Map et le Reduce lors de l'exécution.
Optimiser autant que possible le processus Map si ce n'est pas le cas...
### (3) Intégrer un "Combiner" afin de limiter le nombre de données échangées. Observer à nouveau le nombre de données échangées lors de l'exécution.
Constatez-vous une évolution ?
Exemple de trace d'exécution :
Sans Combiner :
    INFO mapred.JobClient: Map input records=115
    INFO mapred.JobClient: Combine input records=0
    INFO mapred.JobClient: Reduce input records=108
    INFO mapred.JobClient: Combine output records=0
    INFO mapred.JobClient: Map output records=108
    INFO mapred.JobClient: CPU time spent (ms)=3330
    Avec Combiner :
    INFO mapred.JobClient: Map input records=115
    INFO mapred.JobClient: CPU time spent (ms)=2310
    INFO mapred.JobClient: Combine input records=108
    INFO mapred.JobClient: Reduce input records=7
    INFO mapred.JobClient: Combine output records=7
    INFO mapred.JobClient: Map output records=108
    Résultat obtenu (fichiers avec anomalies) :
    Temperature Maximum 27.8
    Temperature Minimum -1.5
   
