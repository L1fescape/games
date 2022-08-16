.DEFAULT_GOAL := build

.PHONY: build
build:
	mvn install -f pom.xml

.PHONY: deps
deps:
	mkdir -p tmp
	cd tmp
	curl -O "https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar"
	java -jar BuildTools.jar --rev 1.19.1
