# See active profiles
mvn help:active-profiles

# Profil via comand line flag 
mvn clean install -P integration-tests

# Activate profile via comand line parameter
mvn package -Denvironment=active-on-property-environment