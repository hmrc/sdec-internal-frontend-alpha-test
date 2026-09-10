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
 - AUTH_LOGIN_API
 - AUTH_LOGIN_STUB

```bash
sm2 --start SDEC_INTERNAL_FRONTEND_ALPHA_TEST
```

## Tests
To run all tests:
```
sbt clean run
```

Make sure that the following JVM Options are added to the run configuration's VM Option sections:
```
-Dbrowser=chrome -Denvironment=local -Dbrowser.option.headless=false -Dbrowser.usePreviousVersion=true
```
Then, you can run these tests in IntelliJ.

Run tests as follows:

* Argument `<browser>` must be `chrome`, `edge`, or `firefox`.
* Argument `<environment>` must be `local`, `dev`, `qa` or `staging`.

```bash
sbt clean -Dbrowser="<browser>" -Denvironment="<environment>" test testReport
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
