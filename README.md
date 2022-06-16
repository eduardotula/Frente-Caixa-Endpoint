# Quarkus Pet Clinic API

This project uses Quarkus, the Supersonic Subatomic Java Framework.



## Developing the application

- Run development database locally with Docker:



- Run your application in dev mode that enables live coding using:

```
./mvnw quarkus:dev
```

## Packaging and running the application

- Run database locally with Docker:



## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or you can use Docker to build the native executable using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your binary: `./target/quarkus-petclinic-api-1.0.1-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image-guide .
