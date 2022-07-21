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

# metamod
RUN cd $CSGO_DIR && \
		curl -sqL "https://mms.alliedmods.net/mmsdrop/1.10/mmsource-1.10.7-git971-linux.tar.gz" | tar zxf - 
# sourcemod
RUN cd $CSGO_DIR && \
  curl -sqL "https://sm.alliedmods.net/smdrop/1.10/sourcemod-1.10.0-git6459-linux.tar.gz" | tar zxf -

# copy src
COPY . .

RUN make build

ENTRYPOINT [ "sh", "/opt/csgo/docker-entrypoint.sh" ]
