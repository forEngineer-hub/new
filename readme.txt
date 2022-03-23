
1.在项目build path的source中点击add folder 名字叫test

2.junit的jar包要手动添加，点击报错的文件，fix project setup ——》 add junit4 to build path

3. @SpringBootTest报错的话，把scope注释
     <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <!-- <scope>test</scope> -->
     </dependency>