# Use the official Tomcat image as the base image
FROM tomcat:9.0-jdk17

# Set ARG for WAR file location
ARG WAR_FILE=target/backend-0.0.1-SNAPSHOT.war

# Remove the default webapps to avoid conflicts
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file to the Tomcat webapps directory
COPY ${WAR_FILE} /usr/local/tomcat/webapps/ROOT.war

# Expose the Tomcat port (default is 8080)
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
# Use the official Tomcat image as the base image
FROM tomcat:9.0-jdk17

# Set ARG for WAR file location
ARG WAR_FILE=target/tour-booking-service.war

# Remove the default webapps to avoid conflicts
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file to the Tomcat webapps directory
COPY ${WAR_FILE} /usr/local/tomcat/webapps/ROOT.war

# Expose the Tomcat port (default is 8080)
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
