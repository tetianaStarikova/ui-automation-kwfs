# Prerequisites
- Java IDE (IntelliJ Idea, Eclipse etc.)
- Java jdk and jre

# How to run tests
Tests are executed via JUnitRunner but cucumber.filter.tags should be specified in junit-platform.properties at first. (for example "not @ignore" will run all except test marked with @ignore tag)
Other option - run tests from feature files. You can run whole feature or specific scenario with "Run" button which is located next to it

Target environment should be specified in CucumberHooks.class (DEV, QA or PROD although the last one should be used carefully and only with approval within TA team)

# CI configuration of tests
CI configuration of tests can be found in [.circleci/config.yml](https://github.com/KWRI/ui-automation-kwfs/blob/master/.circleci/config.yml).

Tests on CI are being executed within Jobs.Build.Steps node -run option. Commands are located [here](https://github.com/KWRI/ui-automation-kwfs/blob/master/.circleci/config.yml#L59-L62)
# CI Builds
Latest executions of tests can be found in [CircleCI](https://app.circleci.com/pipelines/github/KWRI/ui-automation-kwfs). If you see 404 after navigating there it means that your git account doesn't have read access to repository since CircleCI inherits permissions from gitHub.

# Triggering CI with API
Below you can find postman export of API call which needs to be invoked to trigger build. 3 things needs replacement:
Below you can find postman export of API call which need to be invoked to trigger build. 4 things needs replacement:
1. $CIRCLE_CI_TOKEN - used as username parameter of basic auth, can be acquired in CI environment.
2. ENVIRONMENT parameter determine on which environment tests will be executed (make sure that you have specified it in lowercase).
3. TestTag - specify tag which you want to run on CI (could be multiple tags).
4. ApiTrigger - set to this param to true to run automation job on_demand
```
curl --location --request POST 'https://app.circleci.com/pipelines/github/KWRI/ui-automation-kwfs/pipeline' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--header 'x-attribution-login: string' \
--header 'x-attribution-actor-id: string' \
--header 'Authorization: Basic $CIRCLE_CI_TOKEN' \
--data-raw '{
  "branch": "master",
  "parameters": {
        "ENVIRONMENT": "[qa or prod]",
        "TestTag": "{TestTag}",
        "ApiTrigger": true
  }
}'
```
# Scheduled executions
This repository has following scheduled events:
1. Nightly executions - Regression scope every night of working week;
# Test steps
Each test step should have description of its functionality in case if definition of step itself doesn't provide enough information. Also, you can refer to [StepsLibrary](StepsLibrary.md).

# UI repositories under test
1. ui-financial-statement
