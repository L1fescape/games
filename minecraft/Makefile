.DEFAULT_GOAL := start

ROOT_DIR ?= ${HOME}/minecraft
BACKUP_DIR ?= ${ROOT_DIR}/backups
DATA_DIR ?= ${ROOT_DIR}/data
MODS_DIR ?= ${DATA_DIR}/plugins
BACKUP_FILENAME ?= backup-$(shell date +%Y-%m-%d-%H:%M).tar.gz

.PHONY: start
start:
	docker-compose up -d

.PHONY: backup
backup:
	mkdir -p ${BACKUP_DIR}
	tar cfz ${BACKUP_DIR}/${BACKUP_FILENAME} ${DATA_DIR}

.PHONY: restore
restore:
	tar xzf ${BACKUP_DIR}/arg ${DATA_DIR}

.PHONY: mods
mods:
	mkdir -p ${MODS_DIR}
	curl -o ${MODS_DIR}/Dynmap.jar \
		-L https://dynmap.us/releases/Dynmap-3.4-beta-5-spigot.jar
	curl -o ${MODS_DIR}/worldedit.jar \
		-L https://media.forgecdn.net/files/3028/434/worldedit-bukkit-7.2.12.jar
	curl -o ${MODS_DIR}/Multiverse-Core.jar \
		-L https://mediafiles.forgecdn.net/files/3462/546/Multiverse-Core-4.3.1.jar
	curl -o ${MODS_DIR}/Multiverse-Portals.jar \
		-L https://mediafiles.forgecdn.net/files/3113/114/Multiverse-Portals-4.2.1.jar
	curl -o ${MODS_DIR}/Multiverse-Inventories.jar \
		-L https://mediafiles.forgecdn.net/files/3687/469/Multiverse-Inventories-4.2.3.jar

.PHONY: rcon
rcon:
	docker exec -i mc.server rcon-cli
