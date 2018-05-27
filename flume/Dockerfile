FROM ubuntu:16.04
MAINTAINER Roman Bratschun <rbratschun@gmail.com>

RUN set -x && \
echo "######## Tools & Java 8" && \
apt-get update && apt-get install -y net-tools curl software-properties-common && \
apt-get update && \ 
rm -rf /var/lib/apt/lists/*

# Install Java.
RUN \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install -y oracle-java8-installer && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /var/cache/oracle-jdk8-installer

# Define working directory.
WORKDIR /data

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# Define default command.
CMD ["bash"]

RUN set -x && \
echo "######## Flume" && \
mkdir /flume && \
curl -fsSL http://archive.apache.org/dist/flume/1.8.0/apache-flume-1.8.0-bin.tar.gz | tar xvfz - -C /flume --strip-components=1

EXPOSE 18000
EXPOSE 18001

COPY flume.conf /flume/conf/flume.conf
COPY flume-env.sh /flume/conf/flume-env.sh

COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]

