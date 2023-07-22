# Java

<h1>Bulletin Board System</h1>

<p>
Maven과 mySQL을 사용한 simple BBS project
</p>

<h3>개발 도구</h3>

>  ![img_61](https://github.com/Mayhem-XD/Java/assets/116787370/80653f1d-6745-40e3-8af4-fc82d7d81518) ![img_61](https://github.com/Mayhem-XD/PyCo/assets/116787370/315f7972-a2df-4f8d-aeba-b889b50d0c5d) ![Untitled-1](https://github.com/Mayhem-XD/Java/assets/116787370/076d94a4-563d-4b9e-ac52-0509d22077e6)
> ![img_61 (2)](https://github.com/Mayhem-XD/Java/assets/116787370/8406f594-fb71-4cdc-80fd-ff72268cfcab)     ![vscode](https://github.com/Mayhem-XD/Java/assets/116787370/fbda9bcc-d200-448d-bca2-d1e142d45fcd)    ![img_61 (3)](https://github.com/Mayhem-XD/Java/assets/116787370/13a06fa8-7827-4a1c-a19f-7b23c25f4a4f)
> 
<hr>
<h2>수정중</h2>

> ext jar 3개
> bcrypt
> tomcat9
> 
<hr>
<br><br>

<h4>일단 과정 수정중</h4>

> New -> Maven simple project 체크 <br>
> Group id : com.ys <br>
> Artifact id : bbs <br>
> Packaging : WAR <br>
> Name : maven bbs project <br>
> Description : Bulletin Board Service <br>
> server.xml, context.xml 수정

~~~ java

<properties>
	  <maven.compiler.target>11</maven.compiler.target>
	  <maven.compiler.source>11</maven.compiler.source>
  </properties>

<dependencies>
	<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
	<dependency>
	    <groupId>mysql</groupId>
	    <artifactId>mysql-connector-java</artifactId>
	    <version>8.0.33</version>
	</dependency>
	<!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
	<dependency>
	    <groupId>javax.servlet</groupId>
	    <artifactId>javax.servlet-api</artifactId>
	    <version>4.0.1</version>
	    <scope>provided</scope>
	</dependency>
	<!-- https://mvnrepository.com/artifact/org.mindrot/jbcrypt -->
	<dependency>
	    <groupId>org.mindrot</groupId>
	    <artifactId>jbcrypt</artifactId>
	    <version>0.4</version>
	</dependency>
	<!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
	<dependency>
	    <groupId>javax.servlet</groupId>
	    <artifactId>jstl</artifactId>
	    <version>1.2</version>
	</dependency>
  </dependencies>

~~~


<br><br>

<h5>ERROR</h5>

> ![KakaoTalk_20230718_103702797](https://github.com/Mayhem-XD/Maven-BBS/assets/116787370/d738e981-5e9a-4d58-bceb-03d241b840e3)
> register의 form tag에 enctype="multipart/form-data" 을 추가 했을때 발생<br>
> 업로드가 안되는 상황

<h6>해결 방법</h6>

> 아래 코드를 UserController에 추가

~~~ java
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 1,		// 1 MB
		maxFileSize = 1024 * 1024 * 10,				// 10 MB
		maxRequestSize = 1024 * 1024 * 10
)
~~~

> @MultipartConfig 어노테이션은 서블릿이 multipart/form-data 형식의 요청을 처리할 수 있도록 지정하는 데 사용<br>
> maxFileSize 속성은 업로드할 수 있는 파일의 최대 크기를 지정<br>
> maxRequestSize 속성은 multipart/form-data 요청의 최대 크기를 지정, 이 값보다 큰 요청은 처리 불가

<h5>../ 에러페이지/././</h5>

<hr>




