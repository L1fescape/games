.DEFAULT_GOAL := start

SHELL ?= /bin/bash
STEAM_DIR ?= /home/steam
CSGO_ROOT ?= ${STEAM_DIR}/games/csgo
CSGO_DIR ?= ${CSGO_ROOT}/csgo
ADDONS_DIR ?= ${CSGO_DIR}/addons
CFG_DIR ?= ${CSGO_DIR}/cfg
CSGO_PORT ?= 27015
STEAM_GAMESERVER_TOKEN ?= ""
STEAM_WORKSHOP_ID ?= ""
STEAM_WORKSHOP_START_MAP ?= ""
TICK_RATE ?= 128
GAME_TYPE ?= 0
GAME_MODE ?= 1
DEFAULT_MAP ?= de_mirage
MAX_PLAYERS ?= 16
MAP_GROUP ?= mg_active

ARGS = \
	-game csgo \
	-usercon \
	-strictportbind \
	-ip 0.0.0.0 \
	-port ${CSGO_PORT} \
	-tickrate ${TICK_RATE} \
	+sv_setsteamaccount ${STEAM_GAMESERVER_TOKEN} \
	+host_workshop_collection ${STEAM_WORKSHOP_ID} \
	+workshop_start_map ${STEAM_WORKSHOP_START_MAP} \
	+game_type ${GAME_TYPE} \
	+game_mode ${GAME_MODE} \
	+servercfgfile server.cfg \
	-maxplayers_override ${MAX_PLAYERS} \
	-authkey ${STEAM_API_KEY} \
	-nobreakpad

.PHONY: start
start: config
	${CSGO_ROOT}/srcds_run $(ARGS)

.PHONY: config
config:
	mkdir -p ${CFG_DIR}
	# server configs
	envsubst < cfg/server.cfg > ${CFG_DIR}/server.cfg
	envsubst < cfg/aliases.cfg > ${CFG_DIR}/aliases.cfg
	envsubst < cfg/motd.txt > ${CSGO_DIR}/motd.txt
	# gamemode configs
	cp gamemodes/competitive/competitive.cfg ${CFG_DIR}/competitive.cfg
	cp gamemodes/practice/practice.cfg ${CFG_DIR}/practice.cfg
	cp gamemodes/surf/surf.cfg ${CFG_DIR}/surf.cfg

.PHONY: update
update:
	@mkdir -p ${CSGO_ROOT}
	${STEAM_DIR}/steamcmd.sh \
		+login anonymous \
		+force_install_dir ${CSGO_ROOT} \
		+app_update 740 validate \
		+quit

.PHONY: mods
mods:
	# metamod
	@cd ${CSGO_DIR} && \
		curl -sqL "https://mms.alliedmods.net/mmsdrop/1.11/mmsource-1.11.0-git1148-linux.tar.gz" | tar zxf -
  # sourcemod
	@cd ${CSGO_DIR} && \
		curl -sqL "https://sm.alliedmods.net/smdrop/1.11/sourcemod-1.11.0-git6906-linux.tar.gz" | tar zxf -
	# prophunt
	@cd ${CSGO_DIR} && \
		curl -o prophunt.zip -sqL "https://github.com/olavim/sm-PropHunt/archive/refs/heads/master.zip" && \
		unzip prophunt.zip -d tmp && \
		mv tmp/sm-PropHunt-master/*/* . && \
		rm -rf tmp

.PHONY: clean
clean:
	@rm -rf $(ADDONS_DIR)
