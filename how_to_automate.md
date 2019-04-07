#step one 

 0 --> setup HOME variables in  **/Users/altuga/.bash_profile**
        --> export PAYARA_HOME=/Users/altuga/AppServers/payara5
            export OPEN_LIBERTY_HOME=/Users/altuga/AppServers/wlp
            export MAVEN_HOME=/Users/altuga/Applications/apache-maven-3.5.0
            export M2_HOME=/Users/altuga/Applications/apache-maven-3.5.0

 1 --> https://github.com/AdamBien/wad
 2 --> create wad.sh under /usr/local/bin


**wad.sh file content**
```
#!/bin/bash
BASEDIR=/Users/altuga/Applications/WAD/bin
java -jar ${BASEDIR}/wad.jar "$@"
```
# Prepare Payara server start and deployment scripts
- Payara 
   --> startPayara.sh
        ```
        #!/bin/bash
        echo 'Starting Payara'
        MAX_POOL_SIZE=2
        # 1 sec connection timeout
        MAX_WAIT=10
        MINIMUM=1
        $PAYARA_HOME/bin/asadmin start-domain --verbose
        ```
    --> deployPayara.sh
        ```
        wad.sh $PAYARA_HOME/glassfish/domains/domain1/autodeploy
        ```
 # Prepare 'Open Liberty' server start and deployment scripts    
- Open Liberty 
   --> startOpenliberty.sh
        ```
        #!/bin/bash
        echo 'Starting Open Liberty'
        MAX_POOL_SIZE=2
        # 1 sec connection timeout
        MAX_WAIT=10
        MINIMUM=1
        $OPEN_LIBERTY_HOME/bin/server start
        tail -f  $OPEN_LIBERTY_HOME/usr/servers/defaultServer/logs/console.log

        ```
    --> deployPayara.sh
        ```
        wad.sh $OPEN_LIBERTY_HOME/usr/servers/defaultServer/dropins/
        ```