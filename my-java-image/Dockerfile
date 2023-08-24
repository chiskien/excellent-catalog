#bases the new image on the official image for Ubuntu version 23.10#
FROM ubuntu:23.10

#Install the JRE using familiar bash commands#
RUN apt-get update && apt-get install -y default-jre

#Define the #
ENTRYPOINT ["java", "--version"]