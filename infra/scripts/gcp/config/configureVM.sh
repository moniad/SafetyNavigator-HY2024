# install docker
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc
echo   "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
$(. /etc/os-release && echo "$VERSION_CODENAME") stable" |   sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
sudo docker run hello-world
sudo service docker status

gcloud auth configure-docker us-central1-docker.pkg.dev
sudo usermod -aG docker $USER
sudo systemctl restart docker
newgrp docker
docker pull us-central1-docker.pkg.dev/black-skyline-436915-s1/hy2024-registry/app:latest
docker image ls

# install certificates
sudo apt-get update
sudo apt-get install certbot
sudo apt-get install python3-certbot-nginx
sudo certbot certonly --standalone -d smartbike.website
#Certificate is saved at: /etc/letsencrypt/live/smartbike.website/fullchain.pem
#Key is saved at:         /etc/letsencrypt/live/smartbike.website/privkey.pem
EKHEMMMM put here NGINX config update: /etc/nginx/sites-enabled/default
#DONE it didn't require any changes afair: /etc/nginx/nginx.conf
sudo nginx -t
sudo service nginx restart

sudo apt install net-tools

#frontend
# installs nvm (Node Version Manager)
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.0/install.sh | bash
# download and install Node.js (you may need to restart the terminal)
nvm install 20
# verifies the right Node.js version is in the environment
node -v # should print `v20.17.0`
# verifies the right npm version is in the environment
npm -v # should print `10.8.2`

EKHEEM run in frontend-server/: npm init
npm install express