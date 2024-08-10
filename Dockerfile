FROM eclipse-temurin:17-alpine

# devcontainer dependencies
RUN apk update && apk add --no-cache curl unzip libxext libxrender libxtst libxi freetype procps gcompat \
    && rm -rf /var/cache/apk/* /tmp/* /usr/share/man

# ssh client
RUN apk update && apk add --no-cache openssh-client \
    && rm -rf /var/cache/apk/* /tmp/* /usr/share/man

# dependencies
RUN apk update && apk add --no-cache maven

RUN mkdir /opt/app

WORKDIR /opt/app