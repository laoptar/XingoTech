### Java Simple Service Manager

* 新建一个Maven Project
New -> Maven Project -> maven-archetype-webapp 1.0

* Build Path
Window -> Preferences -> Java -> Installed JRES -> Java1.8
Window -> Preferences -> Server -> Runtime Environments -> Tomcat 7.0.47
项目右击 Properties -> Resource -> Text file encoding (UTF-8)
项目右击 -> Build Path -> Configure Build Path.. -> Java Build Path -> Libraries -> Add Library -> Server Runtime -> Apache Tomcat v7.0 -> Add Library -> JRE System Library -> JDK1.8 -> Finish

* 修改JRE版本
在pom.xml
```java
<build>
    <finalName>XingoTech</finalName>
    <plugins>
      <!-- 修改maven默认的JRE编译版本，1.8代表JRE编译的版本，根据自己的安装版本选择1.7或1.8 -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```
项目右击 -> Maven -> Update Project -> OK
项目右击 -> Java Build Path -> JDK1.8

之后你可以 Edit 把 jre 指定到 *Workspace default JRE* 

* 修改web.xml文件版本
右击项目 -> Properties -> Project Facets -> Dynamic Web Module -> 3.0 -> 注意下方出现 *Further configuration available...* 选项, 点击 *Further configuration available...*，Content directory改为: /src/main/webapp， 接着更改为想要的版本 3.0，保存退出

修改src/main/webapp/WEB-INF/web.xml文件,头信息版本修改为 3.0 版本的。
```java
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
</web-app>
```
项目右击 -> Maven -> Update Project

其他：WebContent 改为webapp
进入项目的盘符目录: project/.settings/org.eclipse.wst.common.component
```java
<wb-resource deploy-path="/" source-path="/WebContent" tag="defaultRootSource"/>
改为：
<wb-resource deploy-path="/" source-path="/src/main/webapp" tag="defaultRootSource"/>
```