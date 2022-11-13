.DEFAULT_GOAL := build

REV := 1.19.2

.PHONY: build
build:
	mvn install -f pom.xml

.PHONY: deps
deps:
	mkdir -p deps
	cd deps && curl -O "https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar"
	cd deps && java -jar BuildTools.jar --rev ${REV}
	cp deps/Spigot/Spigot-API/target/spigot-api-${REV}-*SNAPSHOT.jar libs/spigot-api-${REV}.jar
