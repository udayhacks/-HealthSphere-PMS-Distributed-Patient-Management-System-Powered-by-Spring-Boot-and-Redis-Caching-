# HealthSphere-PMS-Distributed-Patient-Management-System-Powered-by-Spring-Boot-and-Redis-Caching-
well organised system for peek use ..
# Task's & Status  :

- Create a patient servies Done !
- Docker task Completed !
-  created  Billing Serivce using Grpc Done !



# Requirements & Setups
- ## GRPC 
        ```declarative
        
        
        
        <!--GRPC -->
                <!-- https://mvnrepository.com/artifact/io.grpc/grpc-netty -->
                <dependency>
                    <groupId>io.grpc</groupId>
                    <artifactId>grpc-netty</artifactId>
                    <version>1.69.0</version>
                </dependency>
                <dependency>
                    <groupId>io.grpc</groupId>
                    <artifactId>grpc-protobuf</artifactId>
                    <version>1.69.0</version>
                </dependency>
                <dependency>
                    <groupId>io.grpc</groupId>
                    <artifactId>grpc-stub</artifactId>
                    <version>1.69.0</version>
                </dependency>
                <dependency> <!-- necessary for Java 9+ -->
                    <groupId>org.apache.tomcat</groupId>
                    <artifactId>annotations-api</artifactId>
                    <version>6.0.53</version>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>net.devh</groupId>
                    <artifactId>grpc-spring-boot-starter</artifactId>
                    <version>3.1.0.RELEASE</version>
                </dependency>
                <dependency>
                    <groupId>com.google.protobuf</groupId>
                    <artifactId>protobuf-java</artifactId>
                    <version>4.29.1</version>
                </dependency>
        ```

- ## build
        ```declarative
             <build>
                <extensions>
                    <!-- Ensure OS compatibility for protoc -->
                    <extension>
                        <groupId>kr.motd.maven</groupId>
                        <artifactId>os-maven-plugin</artifactId>
                        <version>1.7.0</version>
                    </extension>
                </extensions>
                <plugins>
                    <!-- Spring boot / maven  -->
                    <plugin>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-maven-plugin</artifactId>
                    </plugin>
        
                    <!-- PROTO -->
                    <plugin>
                        <groupId>org.xolstice.maven.plugins</groupId>
                        <artifactId>protobuf-maven-plugin</artifactId>
                        <version>0.6.1</version>
                        <configuration>
                            <protocArtifact>com.google.protobuf:protoc:3.25.5:exe:${os.detected.classifier}</protocArtifact>
                            <pluginId>grpc-java</pluginId>
                            <pluginArtifact>io.grpc:protoc-gen-grpc-java:1.68.1:exe:${os.detected.classifier}</pluginArtifact>
                        </configuration>
                        <executions>
                            <execution>
                                <goals>
                                    <goal>compile</goal>
                                    <goal>compile-custom</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>
            </build>
        ```



# kafka (event/message)
- configuration
    - image 
        ```declarative
            apache/kafka:latest
          
          
        ```
    - properties and envs
      
      ```declarative
          KAFKA_NODE_ID=1
          KAFKA_PROCESS_ROLES=broker,controller
          KAFKA_LISTENERS=PLAINTEXT://:9092,CONTROLLER://:9093,EXTERNAL://:9094
          KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092,EXTERNAL://localhost:9094
          KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT,EXTERNAL:PLAINTEXT
          KAFKA_CONTROLLER_QUORUM_VOTERS=1@kafka:9093
          KAFKA_CONTROLLER_LISTENER_NAMES=CONTROLLER
          CLUSTER_ID=MkU3OEVBNTcwNTJENDM2Qk
        ```
      - used for Notification and Analytice and some other microservice for asynchronse communication ;


# to be continued ........... (: