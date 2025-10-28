mvn help:active-profiles


### Activate Profil via comand line flag 
mvn clean install -P integration-tests

# Activate profile via comand line parameter
mvn package -Denvironment=active-on-property-environment