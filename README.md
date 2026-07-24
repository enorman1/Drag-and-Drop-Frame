# Drag And Drop Frame

Exemple d'utilisation du **Glisser/Déposer (Drag & Drop)** en Java avec Swing.

Cette application affiche une fenêtre permettant à l'utilisateur de déposer un ou plusieurs fichiers. Les chemins complets des fichiers déposés sont ensuite récupérés et affichés dans la console.

Le projet est destiné à servir d'exemple pour comprendre la mise en œuvre du mécanisme `java.awt.dnd` avec une interface graphique Swing.

## Fonctionnalités

* Création d'une fenêtre Swing acceptant le dépôt de fichiers.
* Détection des fichiers déposés avec `DropTarget`.
* Gestion du dépôt de plusieurs fichiers simultanément.
* Vérification du type de données reçu (`javaFileListFlavor`).
* Affichage du chemin absolu des fichiers déposés.
* Chargement d'une icône depuis les ressources de l'application.

## Technologies utilisées

* Java
* Swing (`javax.swing`)
* AWT Drag & Drop (`java.awt.dnd`)

## Pré-requis

* JDK 8 ou supérieur

Le projet a été développé et testé avec :

```
OpenJDK 1.8.0_171
```

## Structure du projet

Exemple d'organisation :

```
DragAndDropFrame/
│
├── src/
│   └── DragAndDropFrame.java
│
├── resources/
│   └── DragAndDropFrame.png
│
└── README.md
```

L'image `DragAndDropFrame.png` est utilisée comme icône de la fenêtre.

## Compilation

Depuis le répertoire contenant le fichier source :

```bash
javac DragAndDropFrame.java
```

## Exécution

Lancer l'application :

```bash
java DragAndDropFrame
```

Une fenêtre apparaît. Déposez un ou plusieurs fichiers dans la fenêtre.

Exemple de sortie dans la console :

```
/home/user/Documents/exemple.txt
/home/user/Images/photo.png
```

Seuls les fichiers sont acceptés. Les dossiers déposés sont ignorés.

## Fonctionnement du glisser-déposer

L'application utilise :

* `DropTarget` pour rendre la fenêtre réceptrice d'un dépôt.
* `DropTargetListener` pour intercepter les événements :

  * entrée du curseur (`dragEnter`)
  * sortie (`dragExit`)
  * dépôt (`drop`)
* `DataFlavor.javaFileListFlavor` pour récupérer la liste des fichiers transmis.

L'opération de dépôt est limitée à une copie (`ACTION_COPY`).

## Licence

Ce projet est fourni à titre d'exemple pédagogique.
Vous êtes libre de le modifier et de le réutiliser selon vos besoins.
