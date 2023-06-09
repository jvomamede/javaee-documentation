## Configuration About Project 

you will need: 

- Java SE 1.8
- WildFly 9.x 

## Projeto Description
 This is project documentation about java ee, 2023 - The configuration is passed up question about java ee 8 and jakarta ee 9 and java ee 7, engloby all tools and apis that java ee especification in jrs. 
 
### Configuration JPA on Wildfly 9 

- First: Create this folder in `modules` directory: com -> mysql -> main
- Secound: Copy your driver mysql and paste in `main` folder. 
- Third: Create file with name - `module.xml`
- Fourthy: Copy this code down and paste on `module.xml`:
  ``<?xml version="1.0" encoding="UTF-8"?>
	<module xmlns="urn:jboss:module:1.3" name="com.mysql">
	    <resources>
	        <resource-root path="mysql-connector-java-8.0.30.jar"/>
	    </resources>
	    <dependencies>
	        <module name="javax.api"/>
	        <module name="javax.transaction.api"/>
	    </dependencies>
	</module>
  ``
- Fifth: in your directory of wildfly 9, inside `standalone -> configuration`, open `standalone.xml`, and at tag `datasources` create a new `datasource` wiht code:
`` <datasource jndi-name="java:jboss/datasources/MySQLDS" pool-name="MySQLDS" enabled="true" use-java-context="true">
                    <connection-url>jdbc:mysql://localhost:3306/dbjavaee</connection-url>
                    <driver>mysql</driver>
                    <security>
                        <user-name>root</user-name>
                        <password>root</password>
                    </security>
                </datasource>
                ``
- Sixth: Maybe on `standalone.xml` at tag `drivers` down `datasources`, you add a new driver with code:
`` <driver name="mysql" module="com.mysql">
                        <xa-datasource-class>com.mysql.cj.jdbc.MysqlXADataSource</xa-datasource-class>
                    </driver>`` 
                    
### Configuration JPA in your project
- First: Add all libs in `lib` folder inside `WEB-INF` directory. 
- Second: Add driver mysql in `lib` too
- Trith: Create a `persistence.xml` inside `WEB-INF -> classes -> META-INF` with code:
`` <?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
                                 http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd"
             version="2.2">
    <persistence-unit name="javaeeproject" transaction-type="JTA">
        <jta-data-source>java:/jboss/datasources/MySQLDS</jta-data-source>
        <class>model.Book</class>
        <properties>
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true"/>
        </properties>
    </persistence-unit>
</persistence>``
- Fourth: Create a class Entity
  
  
