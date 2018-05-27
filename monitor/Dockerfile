FROM maven as build
MAINTAINER Roman Bratschun <rbratschun@gmail.com>

COPY ./src /app
COPY pom.xml /app
RUN mkdir /app/target && touch /app/target/monitor.jar
WORKDIR /app
RUN mvn clean package

FROM ubuntu:16.04

RUN set -x && \
apt-get update && apt-get install -y net-tools curl software-properties-common && \
apt-get update && \ 
rm -rf /var/lib/apt/lists/*

# Install Java
RUN \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install -y oracle-java8-installer && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /var/cache/oracle-jdk8-installer

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# Define default command.
CMD ["bash"]

# Copy Configuration file
RUN set -x mkdir /monitor
# COPY --from=build /app/target/monitor.jar /monitor/monitor.jar
COPY monitor.jar /monitor/monitor.jar
COPY entrypoint.sh /entrypoint.sh

RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]

