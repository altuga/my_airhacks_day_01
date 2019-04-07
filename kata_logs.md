#checkout
git checkout -f 866c591e4ab6c840cf4e168874a43c59af6769d7

#curl history

curl -i -H"Content-type: application/json" -d'{"hugo":"d","proxyClass":"u"}' http://localhost:8080/hello/resources/ping



curl -i -H"Content-type: application/json" -d'{"hugo":"d","proxyClass":"u"}' http://localhost:9080/hello/resources/ping

#----------------------#

**Target condition**
Open Liberty üzerinde default veritabanını tanımlaman lazım : 
the server cannot locate the java:comp/DefaultDataSource data source for the prod persistence unit because 
it has encountered the following exception: javax.naming.NameNotFoundException: javax.naming.NameNotFoundException: java:comp/DefaultDataSource.

 **Obstracle 1** 
 - admin panelinde erişmem lazım <-- yanlış varsayım.
    **Obstracle 1.1** **BOOM**
    -  admin paneline erişmek için SSL sorununu çözmek lazım

        [WARNING ] CWPKI0809W: There is a failure loading the defaultKeyStore keystore. 
        If an SSL configuration references the defaultKeyStore keystore, then the SSL configuration will fail to initialize.

        createSSLCertificate , hangi dizinde ? 

        /Users/altuga/AppServers/wlp/usr/servers/defaultServer/resources/security/key.p12


        --> işe yaradı **boom** --> $OPEN_LIBERTY_HOME/bin/securityUtility createSSLCertificate --server=defaultServer --password=adminpwd --validity=365 --subject=CN=mycompany,O=myOrg,C=myCountry
     **Obstracle 1.2**  
        - admin url yi bulamadım - adres bulamadım  
         --> işlemedi  --> https://localhost:9443/adminCenter/   
         --> 
         Server.xml içerisnde aşağıdakileri ekledim
         <feature>adminCenter-1.0</feature>
         <feature>websocket-1.1</feature>      

         ama https://localhost:9443/adminCenter deyince olmadı 
         https://localhost:9443/ de işlemedi **fail**  
         

         --> installUtility i yüklemek lazım ama wlp içerisinde installUtility yok. installUtility sayesinde openLiberty ye herşeyi yükleyebiliyorsun sanırım.

             --> https://www.ibm.com/support/knowledgecenter/en/SSAW57_liberty/com.ibm.websphere.wlp.nd.multiplatform.doc/ae/twlp_ui_setup.html#twlp_ui_setup__uiinstall adresinde dedildiğine göre 
             
             > bin/installUtility install adminCenter-1.0
              installUtility bulunamıyor.
              



 **Obstracle 2**  Yeni bir yol.
 - Direk server.xml içerisine direkt datasource tanımlayabiliyoruz.
    --> https://blog.sebastian-daschner.com/entries/openliberty-with-postgres

        Derby veritabanı ile çalışmak istiyorum.
        --> *sh /Users/altuga/AppServers/db-derby-10.14.2.0-bin/bin/startNetworkServer* çalıştırdım.

        --> derby nazlı çıktı username ve pass istedi- sallamasyon bilgileri girdim
        ```
        <properties.derby.client databaseName="sample" createDatabase="create" 
                             serverName="localhost" portNumber="1527" user="sa" password="sa"/>
        ```

        --> curl -i -H"Content-type: application/json" -d'{"hugo":"d","proxyClass":"u"}' http://localhost:9080/hello/resources/ping
            HTTP/1.1 204 No Content
            X-Powered-By: Servlet/4.0
Date: Sun, 07 Apr 2019 15:44:36 GMT
Content-Length: 0
Content-Language: en-US     

**Obstracle 3** **BOOM**
 Detected JSESSIONID with invalid length; expected length of 23, found 24, setting: ca454404b9442e5cb3f5c673 to null. 
   --> ghost modunda - cookie tutmayarak çalışırsam bu hata ile karşılaşmadım.
    --> cevap : https://developer.ibm.com/answers/questions/302724/why-do-i-see-jsessionid-with-invalid-length-expect/ 
   --> http://localhost:9080/hello/resources/ping/sample
     
  

 **Obstracle 4** **BOOM**
 No message body writer has been found for class
   --> Ping.java tepesine @XmlRootElement ekledim ama bana mısın demedi **fail**
   --> new Response diyerek deneme yapmak istiyorum. **fail**
   -->  @Produces(MediaType.APPLICATION_JSON) ekledim ve oldu **boom**
    ```
    @GET
    @Produces(MediaType.APPLICATION_JSON)  --> bu kısmı ekledim ve düzeldi.
    @Path("sample")
    public Ping sample() {
        return new Ping("m", "p");
    }
    ```

 






