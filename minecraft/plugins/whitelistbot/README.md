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