# Utiliser une image Node.js comme base
FROM node:18.15.0

# Créer le répertoire de travail dans l'image
WORKDIR /app

# Copier les fichiers package.json et package-lock.json pour installer les dépendances
COPY package*.json ./

# Installer les dépendances
RUN npm install

# Copier tous les fichiers de l'application dans le répertoire de travail
COPY . .

# Construire l'application Angular pour la production
RUN npm run build --prod

# Utiliser une image légère basée sur Nginx pour le déploiement
FROM nginx:alpine

# Copier les fichiers de l'application Angular depuis le répertoire de travail de la première étape
COPY --from=0 /app/dist/ /usr/share/nginx/html

# Exposer le port 80 pour accéder à l'application web
EXPOSE 80

# Commande de démarrage pour Nginx
CMD ["nginx", "-g", "daemon off;"]
