set -e

./docker-build.sh

docker run -it \
           --rm \
           -v `pwd`/..:/mnt \
           -v `pwd`/../dot-m2:/root/.m2 \
           -w /mnt \
           ews-java-api/build \
           bash -c 'mvn clean install'