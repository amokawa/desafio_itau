@ECHO OFF

REM Run only WEB tests
set CUCUMBER_FILTER_TAGS=@web
REM set CUCUMBER_PUBLISH_TOKEN=ff682739-b75a-4923-8a4f-86b519f68a40
set CUCUMBER_PUBLISH_ENABLED=true
mvn clean test
