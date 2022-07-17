# Skyrim Together

## Server Commands

Use docker-compose:

```
$ docker-compose up -d
```

Or Docker:

```
$ docker run -it --name skyrim -p 10578:10578/udp -v ${PWD}/config:/home/server/config -v ${PWD}/data:/home/server/Data -v ${PWD}/logs:/home/server/logs tiltedphoques/st-reborn-server:latest
```

## Docs

- [Setting up the client mod](https://wiki.tiltedphoques.com/tilted-online/guides/getting-started)
- [Setup video walkthrough](https://www.youtube.com/watch?v=7ogIhWUxPyE)
