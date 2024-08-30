FROM eclipse-temurin:17

# devcontainer dependencies
RUN apt update && apt install -y curl unzip procps libxext6 libxrender1 libxtst6 libxi6 libfreetype6 \
    && rm -rf /var/lib/apt/lists/*

# git dependencies
RUN apt update && apt install -y openssh-client git \
    && rm -rf /var/lib/apt/lists/*

# dependencies
RUN apt update && apt install -y maven \
    && rm -rf /var/lib/apt/lists/*

RUN mkdir /opt/app

WORKDIR /opt/app