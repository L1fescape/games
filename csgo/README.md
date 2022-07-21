# Counter Strike: Global Offensive

## Server Commands

Use docker-compose:

```
$ docker-compose up -d
```

Or Docker:

```
$ docker build -t csgo:latest .
$ docker run -it --name csgo -p 27015:27015/udp csgo:latest
```

## Docs

- [Setting up the client mod](https://wiki.tiltedphoques.com/tilted-online/guides/getting-started)
- [Setup video walkthrough](https://www.youtube.com/watch?v=7ogIhWUxPyE)
