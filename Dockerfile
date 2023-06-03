# Utilise l'image Docker de Moodle
FROM bitnami/moodle:latest

# Définit l'utilisateur root pour exécuter les commandes suivantes
USER root

# Installation des paquets supplémentaires pour la personnalisation
RUN apt-get update && apt-get install -y \
    locales \
    && rm -rf /var/lib/apt/lists/*

# Définit la langue par défaut sur le français
RUN echo "fr_FR.UTF-8 UTF-8" >> /etc/locale.gen && \
    locale-gen

# Définit la variable d'environnement pour la langue française
ENV LANG fr_FR.UTF-8
ENV LANGUAGE fr_FR:fr
ENV LC_ALL fr_FR.UTF-8

# Ajoute des fichiers de configuration personnalisés dans le conteneur
# COPY moodle-config.php /bitnami/moodle/config.php

# Exécute des commandes supplémentaires pour la personnalisation
# Exemple : copie des fichiers de styles personnalisés, etc.
# COPY custom-styles.css /path/to/moodle/styles/

# Change l'utilisateur pour l'exécution de l'application Moodle
USER 1001

expose 80


