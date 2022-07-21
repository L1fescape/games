# Counter Strike: Global Offensive

## Server Commands

Use docker-compose:

```
$ docker-compose up -d
```

Or Docker:

```
$ docker build -t csgo:latest .
$ docker run -it --name csgo -p 27015:27015/udp -e STEAM_API_KEY= -e STEAM_GAMESERVER_TOKEN= csgo:latest
```

## Links

- [Steam API Key](https://steamcommunity.com/dev/apikey)
- [Steam Gameserver Token](https://steamcommunity.com/dev/managegameservers)
