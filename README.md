# Ktor in Docker at now.sh
This is a sample project written with [Ktor](http://ktor.io), deployed with Docker at [now.sh](https://now.sh).

## Example
The example is a simple website which shows a "ASAP counter".
The counter will be increased each time you click the button below.

![asap_counter.png](asap_counter.png)

## Deployment
### Locally 
Since this project uses Docker heavily you can simply start Docker and run
the following command:
```
./gradlew runDockerImage
``` 
The Application will be then available at [localhost:8080](localhost:8080).

### Remote
The project is ready to use to deploy at [now.sh](https://now.sh).
Simply run 
```
now
```
And the Application will be build in the cloud 🤘
