FROM openjdk:8
EXPOSE 8088
ADD target/AutomateWebAppSel-0.0.1-SNAPSHOT.war AutomateWebAppSel-0.0.1-SNAPSHOT.war
ENTRYPOINT [ "java","-jar","/AutomateWebAppSel-0.0.1-SNAPSHOT.war" ]