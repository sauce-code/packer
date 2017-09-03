# Packer

## Was ist Packer?

Packer ist ein einfaches Werkzeug zum Erstellen von Packlisten. Ziel des Programms ist es, eine individuelle Liste für jeden Reisezweck zu erzeugen. Es wurde versucht, einen Kompromiss aus Einfachheit und Komplexität zu schaffen, sodass bewusst auf einzelne Gegenstände verzichtet wurde. Die Packlsiten können in diversen Formaten exportiert werden.

Zum Ausführen des Programms wird [Java SE Runtime Environment 8](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) oder höher benötigt.

Packer wurde komplett in Java geschrieben und bietet eine JavaFX Oberfläche, die Zugriff auf alle nötigen Funktionen bietet. Der Quellcode ist in Form eines Eclipse Maven Projektes bereitgestellt, kodiert in UTF-8.

## Kompilieren

Zum Kompilieren des Programms genügt es, im Root Verzeichnis den Befehl

    mvn install
    
auszuführen. Die erzeugte ausführbare .jar ist

    target/packer-version-jar-with-dependencies.jar

## Lizenz

Packer ist unter [GNU General Public License v3.0](https://choosealicense.com/licenses/gpl-3.0/) lizensiert.
