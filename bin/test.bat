@ECHO OFF

REM Filter by tags
REM set CUCUMBER_FILTER_TAGS=@api

REM Set the environment properties for Cucumber reports service
set CUCUMBER_PUBLISH_TOKEN=134b4160-dfb8-4026-a5f1-236e9428fbb6
set CUCUMBER_PUBLISH_ENABLED=true

REM Run all the tests
mvn clean test
