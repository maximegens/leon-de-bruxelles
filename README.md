Application Android Léon de Bruxelles
==========================
_Store Location : Léon de Bruxelles_

![leon](https://raw.githubusercontent.com/maximegens/location-leon-de-bruxelles/master/res/drawable-hdpi/ic_leon.png)
 
> **Application pour téléphone et tablette Android permettant de connaitre le restaurant "Léon de Bruxelles" le plus proche de sa position ainsi que d'obtenir plusieurs informations sur le restaurant sélectionné comme par exemple les horaires d'ouverture, l’existence d'un parking ou d'un accès handicapé.**

> **Télécharger l'application :**
> [Léon de Bruxelles App](https://github.com/maximegens/location-leon-de-bruxelles/blob/master/bin/LeonDeBruxelles.apk)

**Développeur :** [Maxime Gens](https://github.com/maximegens) - Etudiant en Master Informatique spécialité E-Services à l'université de Lille 1

**Capture d'écran :**

Rendu sur Tablette

![Rendu sur Tablette](https://raw.githubusercontent.com/maximegens/location-leon-de-bruxelles/master/documents/tab_leon.png)

Rendu sur Smartphone

![Rendu sur telephoen 1](https://raw.githubusercontent.com/maximegens/location-leon-de-bruxelles/master/documents/tel_list.png) --- 
![Rendu sur telephoen 1](https://raw.githubusercontent.com/maximegens/location-leon-de-bruxelles/master/documents/tel_detail.png)

**Fonctionnalités :**
* Parcourir la liste des restaurants
* Voir les informations d'un restaurant (photo du restaurant, horaires d'ouverture ...)
* Possibilité d'appeler directement le restaurant
* Affichage de l'emplacement du restaurant sur une map
* Itinéraire vers le restaurant depuis sa position
* Activation du GPS pour affiner la géolocalisation
* Mode OnLine OffLine des données.
* Partage des informations sur le restaurants.
* Lien sur le site internet "Léon de Bruxelles"

**Appareils supportés** 

Smartphone et tablette Android (Interface différente sur chacun) - A partir de la version 1.6 d'Android (API 4)

**Technologies utilisées**
* Bibliothèque de support v7 - appcompat_v7 : pour la compatibilité avec les devices inférieur à l'API 11 
* Bibliothèque de service Google - google_play_service_lib
* Bibliothèque GSON de Google
* Fragments : FragmentManager - FragmentTransaction (Utilisé pour l'affichage différent en fonction du device (tablette ou smartphone)
* Géolocalisation de l'utilisateur
* Communication avec les paramètres du device pour activer le GPS
* Génération de l'affichage par les folders "layout" "layout-land" etc
* ActionBar dynamique (principalement pour le bouton "Share")
* Intégration de Google Map V2
* Adapter Personnalisé
* AsyncTacks pour le téléchargement online et local des données.

 
