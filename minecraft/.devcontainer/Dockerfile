FROM eclipse-temurin:17-jdk

EXPOSE 25565
EXPOSE 8123
EXPOSE 3000

RUN apt update
RUN apt install -y \
  git \
  tmux \
  build-essential \
  maven

# Install ngrok
RUN curl -s https://ngrok-agent.s3.amazonaws.com/ngrok.asc | tee /etc/apt/trusted.gpg.d/ngrok.asc >/dev/null && echo "deb https://ngrok-agent.s3.amazonaws.com buster main" | tee /etc/apt/sources.list.d/ngrok.list
RUN apt update
RUN apt install ngrok

WORKDIR /workspace