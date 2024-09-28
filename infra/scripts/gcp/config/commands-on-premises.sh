gcloud config configurations create hackyeah
gcloud config set account XX@gmail.com
gcloud config set core/project black-skyline-436915-s1
gcloud config set compute/zone us-central1-f
gcloud config set compute/region us-central1

gcloud compute ssh --project=black-skyline-436915-s1 --zone=us-central1-f hackyeahinstance
docker push us-central1-docker.pkg.dev/black-skyline-436915-s1/md-gar/safety-navigator/app:v2