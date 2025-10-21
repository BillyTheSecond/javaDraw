# Projet JavaDraw


---

### temporaire - refactor

> Note le temps de réfléchir à une meilleure façon de code ce logiciel en utilisant des classes.
> **Objectif : **
> - Séparer le projet en classes pour alléger le fichier principal et simplifier la compréhension du code.
> - Rendre possible le dessin dans un tableau d'éléments pour pouvoir implémenter:
>   - La sauvegarde des dessins
>   - Le redimensionnement de fenêtre sans tout effacer
>   - L'ajout de fonctionalité retour arrière


- idée : Avoir une classe DrawOption qui gère les différentes options de dessin (trait, carré, rectangle, cercle, etc.)
- Chaque option hérite de DrawOption et implémente ses propres méthodes pour gérer les événements de souris (onPressed, onDragged, onReleased)
- La classe principale JavaDraw gère l'interface utilisateur et délègue les événements de souris à l'option de dessin sélectionnée