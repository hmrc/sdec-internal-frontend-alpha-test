# sdec-internal-frontend-test

SDEC Internal Frontend UI journey tests.

## Pre-requisites
If running locally, you will need:
1. Authentication services
2. An instance of sdec-internal-frontend

### Services

Start Mongo Docker container as follows:

```bash
docker run --rm -d -p 27017:27017 --name mongo percona/percona-server-mongodb:6.0
```

Start the following services:
 - AUTH
 - AUTH_LogIN_API
 - AUTH_LOGIN_sTUB

```bash
sm2 --start SDEC_INTERNAL_FRONTEND
```

## Tests

```bash
sbt clean -Dbrowser="<browser>" -Denvironment="local" test testReport
```

## Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
