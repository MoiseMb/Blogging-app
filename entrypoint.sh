#!/bin/bash

# Démarrer Apache
service apache2 start

# Attendre que MySQL soit prêt
while ! mysqladmin ping -h"mariadb" --silent; do
    sleep 1
done

# Importer la base de données Moodle
mysql -h mariadb -u root -p${MYSQL_ROOT_PASSWORD} -e "CREATE DATABASE IF NOT EXISTS ${MYSQL_DATABASE} DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci"
mysql -h mariadb -u root -p${MYSQL_ROOT_PASSWORD} ${MYSQL_DATABASE} < /var/www/html/moodle/moodle/install.xml

# Mettre à jour les informations de configuration de Moodle
php /var/www/html/moodle/admin/cli/install_database.php --agree-license --fullname="Moodle" --shortname="Moodle" --adminpass=${MOODLE_ADMIN_PASSWORD} --adminemail=admin@example.com

# Arrêter Apache
service apache2 stop

# Exécuter la commande fournie en argument
exec "$@"
