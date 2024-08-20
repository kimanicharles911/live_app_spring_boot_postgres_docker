* To start docker desktop on my machine:
```
sudo sysctl -w kernel.apparmor_restrict_unprivileged_userns=0 && systemctl --user restart docker-desktop
```

* To start the application via docker as in first commit of this repo:
```
docker compose up -d java_db
```

* To avoid error that occurs when trying to create an image run:
```
mvn clean package -DskipTests
```