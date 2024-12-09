<div align="center">
    <img src="./docs/img/logo.png" alt="All My Links Logo" />
    <h1>
        All My Links
    </h1>
</div>

Welcome to the [AllMyLinks](https://allmylinks.co.uk) codebase. We're building a platform that allows you to create 
a personal page with all your links in one place. This repository contains the code of the platform.

## 🛠️ Tech stack

👨‍💻**TLDR: Java 17, Spring Boot 3.4, MariaDB, HTMX**

We're trying to keep the tech stack as simple as possible in order to move as fast as possible. And also because 
there are very a few people who's working on the project. 

The most complicated part is to build the frontend. The first version of the application was built with NextJS but
unfortunately due to unforeseen maintenance burden, we decided to switch to a more traditional approach. Currently, 
the frontend is built by using [HTMX](https://htmx.org/) as it allows to limit amount of JavaScript and rely on 
the backend technologies as much as possible. 

## 🔮 Installation and running locally

1. Install [docker](https://docs.docker.com/get-docker/) and [docker-compose](https://docs.docker.com/compose/install/).
2. Clone the repository:

```shell
$ git clone https://github.com/aabarmin/allmylinks.co.uk.git
$ cd allmylinks.co.uk
```

3. Run dependencies in docker: 

```shell
$ docker-compose up -f ./docker/docker-compose.yml
```

4. Run the application:

```shell
$ ./gradlew bootRun
```

This will run the application in development mode on [http://localhost:8080](http://localhost:8080), as well as other
necessary dependencies: MariaDB database, phpMyAdmin, and smtp4dev. 

Auto-reloading for backend and frontend is performed automatically by using Spring Boot DevTools and HTMX.

## 🧪 Testing

We use standard JUnit 5 and Spring Boot for testing. No magic here.

## 🚀 Deployment

No k8s, no AWS, no clouds, we ship `jar` files directly via ssh and it's beautiful!

## 🐞 How to report a bug? 

- 🆕 Open [a new issue](https://github.com/aabarmin/allmylinks.co.uk/issues/new) and describe the problem.

## 📢 Contributions

Contributions are welcome. If you want to have a new feature added, please [open a new issue](https://github.com/aabarmin/allmylinks.co.uk/issues/new)
and describe the feature you want to have.
