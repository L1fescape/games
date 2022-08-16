# Whitelist Bot

> Use Slack or Discord to whitelist players

## Install

- Install openjdk
- Build spigot

## Building Spigot
- Grab the latest [Spigot BuildTools.jar](https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar) ([more info](https://www.spigotmc.org/wiki/buildtools/))
- Run `java -jar BuildTools.jar --rev 1.19.1`
- Copy the build artifact to `libs/`

## Build the Plugin

```
$ make build
```

or if `make` is not installed:

```
$ mvn install -f pom.xml
```

## Configuration within Slack

Create a new slack bot with the app manifest in `slack_app_manifest.yml`.

Copy the `SLACK_BOT_TOKEN` and `SLACK_SIGNING_SECRET` and enter the values in `../docker-compose.yml`.

Note: the domain hosting the minecraft server and bot will need to support https since Slack requires https for all command endpoints.

wip

## Local development
```
$ ngrok http minecraft:3000
```

wip