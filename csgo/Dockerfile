FROM ubuntu:18.04

WORKDIR /opt/csgo

EXPOSE 27015
EXPOSE 27015/udp

ARG USERNAME=steam
ARG USER_UID=1000
ARG USER_GID=$USER_UID
ARG STEAM_DIR=/home/$USERNAME
ARG CSGO_ROOT=$STEAM_DIR/games/csgo
ARG CSGO_DIR=$CSGO_ROOT/csgo
ARG LOCALE=en_US.UTF-8

ARG steam_user=anonymous
ARG steam_password=

ENV DEBIAN_FRONTEND noninteractive

# env setup
RUN dpkg --add-architecture i386
RUN apt-get update
# env tools
RUN apt-get install -y \
  apt-utils \
  build-essential \
  git \
  unzip \
  # envsubstr
  gettext-base \
  # steam
  curl \
  net-tools \
  netcat \
  lib32gcc1 \
  # https://github.com/ValveSoftware/steam-for-linux/issues/7036
  libsdl2-2.0-0:i386 \
  # sourcemod
  lib32stdc++6 \
  # admin
  tmux \
  vim

RUN apt-get install -y locales
RUN locale-gen $LOCALE
RUN update-locale LANG=$LOCALE

RUN groupadd --gid $USER_GID $USERNAME
RUN useradd -s /bin/bash --uid $USER_UID --gid $USER_GID -m $USERNAME

# fix permissions
RUN chown -R $USERNAME:$USERNAME $STEAM_DIR

USER $USERNAME

# install steam
RUN cd $STEAM_DIR && \
  curl -sqL "https://steamcdn-a.akamaihd.net/client/installer/steamcmd_linux.tar.gz" | tar zxvf -

# fix steam
RUN mkdir -p $STEAM_DIR/.steam
RUN ln -s $STEAM_DIR/linux32 $STEAM_DIR/.steam/sdk32

# install cs
RUN mkdir -p $CSGO_ROOT
RUN $STEAM_DIR/steamcmd.sh \
		+login anonymous \
		+force_install_dir $CSGO_ROOT \
		+app_update 740 validate \
		+quit

# copy src
COPY . .

RUN make mods

ENTRYPOINT [ "sh", "/opt/csgo/docker-entrypoint.sh" ]
