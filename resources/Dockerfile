FROM centos
ENV JAVA_VERSION 8u31
ENV BUILD_VERSION b13
# Upgrading system
RUN yum -y upgrade
RUN yum -y install wget
# Downloading & Config Java 8
RUN wget --no-cookies --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/8u144-b01/090f390dda5b47b9b721c7dfaa008135/jdk-8u144-linux-x64.rpm" -O /tmp/jdk-8-linux-x64.rpm
RUN yum -y install /tmp/jdk-8-linux-x64.rpm
RUN alternatives --install /usr/bin/java jar /usr/java/latest/bin/java 200000
RUN alternatives --install /usr/bin/javaws javaws /usr/java/latest/bin/javaws 200000
RUN alternatives --install /usr/bin/javac javac /usr/java/latest/bin/javac 200000
EXPOSE 8080
#install Spring Boot artifact
VOLUME /tmp
ADD customer-service-2.0.0.M2.jar customer-service-2.0.0.jar
RUN sh -c 'touch /customer-service-2.0.0.M2.jar'
ENTRYPOINT ["/usr/java/latest/bin/java","-Djava.security.egd=file:/dev/./urandom","-jar","/customer-service-2.0.0.jar"]