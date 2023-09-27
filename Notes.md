# Part 1: Cloud Native Fundamentals

## 1. Introduction to Cloud Native

> Objectives:
> - What cloud and cloud computing model are?
> - The Definition of Cloud Native
> - Characteristics of Cloud Native Applications
> - Culture and Practices supporting Cloud Native
> - When and why you might consider the Cloud Native approach
> - Topologies and architectures for Cloud Native Applications

- Cloud Native applications are highly ***distributed systems*** that live in the cloud and are resilient to change
- Made up by several services that communicate through a network and are deployed in a dynamic environment where
  everything keeps changing

![[01-01.png]]

### 1.1 What is Cloud Native?

> Cloud Native Applications should be specifically designed for the **cloud** and have *properties* that take advantage
 of the cloud environment and cloud computing model

#### 1.1.1 The Three Ps of Cloud Native

- What does it mean for applications to be designed for cloud?

  > *Cloud native technologies empower organizations to build and run scalable applications in modern, dynamic
  environments such as public, private, and hybrid clouds.
  > Containers, service meshes, microservices, immutable
  infrastructure, and declarative APIs exemplify this approach.
  > These techniques enable loosely coupled systems that are resilient, manageable, and observable. Combined with robust
  automation, they allow engineers to make high-impact changes frequently and predictably with minimal toil.*


- The **Three Ps** of Cloud Native:
    - ***Platforms***: CNA run on platforms based on dynamic, distributed environments: the Cloud (public, private,
      hybrid)
    - ***Properties***: CNA are designed to be scalable, loosely coupled, resilient, manageable, and observable
    - ***Practices***: Practices around CNA-automation, continuous delivery, and DevOps—include robust automation
      combined with frequent and predictable changes

- What is the Cloud Native Computing Foundation?

> The Cloud Native Computing Foundation (CNCF) is part of the Linux Foundation, and it “builds sustainable ecosystems
> and fosters communities to support the growth and health of cloud native open-source software.” The CNCF hosts many
> cloud native technologies and projects to enable cloud portability without vendor lock-in. If you want to discover the
> many projects addressing any cloud native aspect, I recommend checking out the CNCF Cloud Native Interactive Landscape

### 1.2 The Cloud and The Cloud Computing Model

- The Cloud Environment:

![[01-02.png]]
**Figure 1.2 The cloud is an IT infrastructure characterized by different computing models
and offered as a service by providers according to the degree of control consumers need.**


> 	Cloud computing is a model for enabling ubiquitous, convenient, on-demand network access to a shared pool of configurable computing resources (networks, servers, storage, applications, and services) that can be rapidly provisioned and released with minimal management effort of service provider interaction


![[01-03.png]]
**Figure 1.3 The cloud computing service models differ by the level of abstraction they provide
and who is responsible for managing which levels(the platform or the consumer).**

#### 1.2.1 Infrastructure as a Service (IaaS)

- Consumers can directly control and provision resources like servers, storage, and networks. (like virtual machines,
  software, operating systems, libraries)
- Examples: AWS Elastic Compute Cloud (EC2), Azure Virtual Machines, Google Compute Engine, Alibaba Virtual Machines,
  and Digital Ocean Droplets

#### 1.2.2 Container as a Service (CaaS)

- Consumer cannot control primitive virtualization resources -> provision and manage containers.
- Cloud Provider takes care of provisioning the underlying resources that fulfill the needs of those containers. Such
  as:
    - Starting a new virtual machine
    - Configuring networks
- Example:
    - Docker Swarm, Apache Mesos, and Kubernetes
- Kubernetes managing services: Amazon Elastic K8S Service, Azure K8S Service, Google K8s Engine.

#### 1.2.3 Platform as a Service (PaaS)

- The Platform provides infrastructure, tools, and APIs that dev can use to build and deploy applications.
- When building Java application and packaged it as JAR files, and deploy it to the Platform working according to the
  PaaS model.
- The Platform provides JVM and other required middleware, and also databases or messaging systems.
- Examples:
    - Cloud Foundry, Heroku, AWS Elastic Beanstalk, Azure App Service, Google App Engine
    - Some vendors have been converging on K8s for building a new PaaS experience for dev and ops. Example: VMware Tanzu
      Application Platform and RedHat OpenShift

#### 1.2.4 Function as a Service (FaaS)

- Faas model relies on serverless computing to let consumers focus on implementing business logic of application,
  whereas the **platform** takes care of providing servers and the rest of infrastructure
- Example: AWS Lambda, Microsoft Azure Functions, Google Cloud Functions, and Alibaba Functions Compute

#### 1.2.5 Software as a Service (SaaS)

- The Highest Abstraction Service
- Consumers access applications as a user, while the **cloud provider** manages the whole **stack** of software and
  infrastructure.
- Example: GitHub, Proton Mail, Microsoft Office 365

### 1.3 Properties of Cloud Native Applications

> How Should You design applications to take advantage of their characteristics?

- Five main properties:
    - **Scalability**
    - **Loose Coupling**
    - **Resilience**
    - **Observability**
    - **Manageability**

![[01-04.png]]
**Figure 1.4 The main properties of cloud native applications**

> **Cloud Native is a *methodology* for building and running applications that exhibit those properties.**

> **The Cloud is about *where*, and the Cloud Native is about *how***.

#### 1.3.1 Scalability

- CNA (Cloud Native App) is designed to **scale**.
- Support increasing workloads with additional resources.

##### Vertical Scaling

- Scaling vertically (scaling up or down)—Adding hardware resources to or removing them from the computing node, such as
  CPU or memory.
- This Approach is limited, it's not possible to keep adding hardware resources.

##### Horizontal Scaling

- Scaling Horizontally (scaling out or in)—Adding more **computing nodes** or **containers** to, or removing from the
  system
- Require application to be **scalable**.

- Traditional Systems would usually adopt **vertical scalability**.
- In the Cloud, where everything is dynamic and in constant changes, **horizontally scaling** is preferred.

![[01-05.png]]
**Figure 1.5 When you need to support increasing workloads,
the vertical scalability model will add hardware resources to the computing node,
while the horizontal scalability model will add more computing nodes.**

#### 1.3.2 Loose Coupling

- ***Loose Coupling*** is an essential property of a system where parts have as little knowledge of each other as
  possible.
- The Goal is to evolve each piece independently so that when one is changed, the others don't need to change
  accordingly.

- ***Cohesion***: Encapsulate code that change together.
- Benefits of **Modulation** :
    - **Managerial**:
    - **Product Flexibility**: The overall system should be flexible since each module is evolved independently of the
      others.
    - **Comprehensibility**: People should be able to understand and work with a module without having to study the
      whole system.

#### 1.3.3 Resilience

- A system is **resilience** if it provides its service even in the presence of faults or environmental changes.
- Resilience is *the capability* of a hardware-software network to provide and maintain an acceptable level of service
  in the face of faults and challenges to normal operation.

- When building Cloud Native Systems, our goals should be guaranteed that our apps are always available, whether there
  is a failure in the infrastructure or in our software.
- Cloud Native runs in a dynamic environment where everything keeps changing, and fault can be and will occur.
- Changes are not exceptions, they are the rule.

- Three essential concepts:
    - **Fault**: A fault is a defect that produces an incorrect internal state either in the software or the
      infrastructure.
        - Example: A method call returns a `null` value, even if its spec mandates that a non-null value is returned.
    - **Error**: An Error is discrepancy between the expected behavior of a system and the actual one.
        - Example: Due to the preceding **fault**, a `NullPointerException` is thrown.
    - **Failure**: When **fault** is triggered and results in an **error**, a failure might occur, making the system
      unresponsive and unable to behave according to its specification.
        - Example: if the `NullPointerException` is not caught, the **error** provokes a **failure**: the system
          responds to any request with a 500 response.

- **Fault** can become **errors**, may provoke **failures** => we should design application to be ***fault-tolerant***
- An essential part of **resilience** is ensuring that a failure will not cascade to other components of the system but
  stay **isolated** while it gets fixed
- The System needs to be ***self-healing*** and ***self-repairing***

> Recommended Patterns: Circuit Breakers, Retry, Timeouts, and Rate Limiters

#### 1.3.4 Observability

- ***Observability*** is a property that comes from the world of control theory.
- ***Observability*** is a measure of how well you can infer its internal state from its external outputs.
- In the software engineering context, the system is a single app or a distributed system as a whole.
- External outputs can be data like metrics, logs, and traces.

![[01-06.png]]
**Figure 1.6 Observability is about inferring the internal state of an application from its external outputs.
Manageability is about changing the internal state and outputs via external inputs.
In both cases, the application artifact is never changed.
It’s immutable.**

##### Four pillars of observability:

###### 1. Monitoring:

- Monitoring is all about measuring specific aspects of an application to get information on its overall health and
  identify failures. (Spring Boot Actuator and Integrate Prometheus with Spring to export relevant metrics about an
  application)

###### 2. Alerting / Visualization:

- Collecting data about the state of a system is useful only if it's used to take action
- When a **failure** is identified, an **alert** should be triggered, and some action should be taken to handle.
- Specific dashboards are used to visualize the data collected

###### 3. Distributed System-Tracing infrastructure

- It's not enough to track every single subsystem's behavior.
- It's essential to track data flowing through the difference subsystems.
- Spring Open Telemetry and Grafana Tempo to collect and visualize the traces.

###### 4. Log Aggregation/Analytics

- Keeping track of the main events in an application is critical to infer the software's behavior and debug if smt goes
  wrong.
- In CNA, ***logs*** should be aggregated and collected to provide a better picture of the system's behavior.
- Fluent Bit, Loki, and Grafana to collect and visualize logs

#### 1.3.5 Manageability

- **Manageability** measures how easily and effectively an external input can change the state or the output of a
  system.
- Ability to modify an application's behavior without needing to change its code
- Using a Configuration center: Spring Cloud Config Server, Kubernetes Config Map, and Secrets

### 1.4 Culture and Practices Supporting Cloud Native

![[01-07.png]]

#### 1.4.1 Automation

#### 1.4.2 Continuous Delivery

#### 1.4.3 DevOps

### 1.5 Is Cloud Best option ?

### 1.6 Cloud Native Topologies

![[01-09.png]]
**Figure 1.9 The main cloud native computing models are containers (managed by orchestrators) and serverless.**

#### 1.6.1 Containers

- When you join a team, you might develop a new feature and then test it in a quality assurance

## 2. Cloud Native Patterns and Technologies

> Objectives:
> - Understanding development principles for cloud native applications
> - Building Cloud Native application with Spring Boot
> - Containerizing Applications with Docker and BuildPacks
> - Deploying application to the cloud with K8s
> - Introducing patterns and technologies used in the books



![[02-01.png]]

### 2.1 Cloud Native Development Principles: 12 Factors and Beyond

> Characteristics:
> - Suitable to be developed on cloud platforms
> - Scalable by design
> - Portable across systems
> - Enable of continuous deployment and agility

#### 2.1.1 One codebase, One Application

- One codebase for each application
- Shared code should be tracked in its own codebase as a **library** that can be included as a **dependency** or a
  service can be run in a standalone mode act as a **backing services** for other applications.
- A deployment is running instance of the application. Any configuration should be outside the application codebase

#### 2.1.2 API First

- Cloud Native Systems are made up of different services that communicate through APIs
- **APIs first** approach helps fit into distributed systems and favors the distribution of work across a different team
- Designing a contract up front, integration with other systems will be more robust and testable

#### 2.1.3 Dependency Management

- All Dependencies should be declared explicitly in a **manifest** and be available for the dependency manager to
  download from **central repository** (Maven, Gradle)

#### 2.1.4 Design, Build, Release and Run

- **Design Stage**: Technologies, dependencies, and tools needed by a specific application feature are decided
- **Build Stage**: The codebase is compiled and packaged together with its dependencies as an *immutable artifact*
  called a **build**
- **Release Stage**: The **build** is combined with a specific configuration for the deployment. Each release is
  immutable and be uniquely identified. Each release should be stored in a central repo for easy access
- **Run Stage**: The Application runs in the execution environment from a specific release.

#### 2.1.5 Configuration, Credentials,and Code

- Configuration is everything likely to change between deployments.
- Should be able to change configuration without modifying the code, building application again
- Configs might include resources to handle backing services like a database or messaging system, credential to access
  third-party APIs, and feature flags.
- Should not keep configuration code the same codebase as application.
- By that, you can deploy to many environments without changing the code.

#### 2.1.6 Logs

- Application should log to the standard output, treating logs as events emitted in a sequence ordered of time.

#### 2.1.7 Disposability

-

#### 2.1.8 Backing Services

#### 2.1.9 Environment Parity

#### 2.1.10 Administrative Processes

#### 2.1.11 Port Binding

#### 2.1.12 Stateless Processes

#### 2.1.13 Concurrency

#### 2.1.14 Telemetry

#### 2.1.15 Authentication and Authorization

### 2.3 Containerizing Applications with Docker

> Docker is an open-source platform that "provides the ability to package and run an application in a loosely isolated
> environment called a container"

#### 2.3.1 Introducing Docker: Images and Containers

- When you install the Docker platform on your computer, you get the ***Docker Engine*** package characterized by a
  *client/server* architecture.
- The ***Docker Server*** contains the ***Docker daemon***, a background process responsible for creating and managing
  Docker objects like images, containers, volumes, and networks.
- The Docker server runs on a machine called ***Docker host***

- **Docker Daemon** exposes API can be used to send instruction, such as run a container or create a volume.

- A **container image** (image) is a lightweight executable package that includes everything needed to run the
  application inside.
- Docker Image format is the most used one for creating container images, standardized by the OCI project
- OCI Images can be created from scratch by defining instruction in a `Dockerfile`, a text-based contains all
  instructions, steps to generate the image.
-

#### 2.3.2 Running a Spring Application as a container

- `maven: spring-boot: build-image`
- ` docker run --rm --name catalog-service -p 8080:8080 catalog-service:0.0.1-SNAPSHOT`
    - `docker run`: Runs a container from an image
    - `--rm`: Removes the container after its execution completes
    - `--name catalog-service`: name of the container
    - `-p 8080:8080`: Exposes service outside the container through port 8080
    - ` catalog-service:0.0.1-SNAPSHOT`: Name and version of the image to run

### 2.4 Managing Containers with Kubernetes

> Kubernetes (K8S) is an open-source system for automating the deployment, scaling, and management of containerized
> applications

- When working with containers in Docker, the deployment target is machine, other scenarios it might be a Virtual
  Machine.
  => It's about deploying to the specific machine
- However, when it comes to deploying containers without downtime, scaling them by leveraging the cloud's elasticity

![[02-09.png]]

### 2.5 Polar Bookshop: A Cloud Native Application

#### Overall Architecture

![[02-14.png]]

#### Web and Interactions

- RESTful Web Services that interact synchronously over HTTP, both in a ***synchronous*** ***blocking way*** (traditional servlets) and ***asynchronous, non-blocking way*** (reactive programming)
- Spring MVC
- Spring WebFlux

> - When building cloud native applications, the services should be designed loosely coupled
> - Data should be kept consistent in distributed context

=> Event-driven programming

#### Data

#### Configuration

####    

# Part 2: Cloud Native Development

## 3. Getting Started with Cloud Native Development

> Objectives:
> - Bootstrapping a cloud native project
> - Working with embedded servers and Tomcat
> - Building a RESTful application with Spring MVC
> - Testing a RESTful application with Spring Test
> - Automating the build and tests with GitHub actions

### 3.2 Working with Embedded Servers

- Aspects of Cloud Native Applications:
    1. Entirely ***Self-contained***, with no external dependencies other than the runtime
    2. Being ***packed*** as standard, executable artifacts

- Two Factors Applied:
    - Port Binding
    - Concurrency

- Jargons:
    - ***Web Server***:A server that handles HTTP requests coming from a client and replies with HTTP responses, such as
      Apache HTTPD
    - ***Servlet Container***: A component, part of a web server, that provides execution context for web applications
      using the Java Servlet API. Tomcat is an example
    - ***Application Server***: A server that provides a complete execution environment (Jarkatar EE) for different
      types of applications and supports several protocols, such as WildFly

#### 3.2.1 Executable JARs and embedded servers

#### 3.2.2 Thread-per-request model

- The ***request/response*** pattern commonly used in a web app to establish synchronous interactions over HTTP.
- In a web app running in a Servlet Container like Tomcat, requests are processed based on a model called
  ***thread-per-request***
    - For each request, the application dedicates a thread exclusively to handling that specific request. The thread
      will not be used for anything else until a response is returned to a client.
    - The thread will block and wait until the operations are completed.
        - Handling I/O
        - Read/Write Data from Database

> This type of processing is called ***synchronous*** and ***blocking***


![[03-05.png]]

### 3.3 Building API with Spring MVC

- Entities (Model): An ***entity*** represents the noun in a domain, such as ***"book"***
- Service: A ***service*** defines the use cases for the domain. For example, ***"Adding a book to the catalog"***
- Repository: A ***repository*** is an abstraction to let the domain layer access data independently of its source

### 3.5 Deployment Pipeline: Build and Test

- ***Commit Stage***: After a dev commits new code to the mainline, this stage goes through build, unit tests,
  integration tests, static code analysis, and packaging. At the end of the stage, an executable application artifact is
  published to an artifact repository. It is a release candidate.
- ***Acceptance Stage***
- ***Production Stage***

#### 3.5.2 Implementing the commit stage with GitHub Actions

- GitHub Actions is a platform build into GitHub that lets you automate software workflows directly from your code
  repositories.
- A ***workflow*** is an automated process

### 3.5 Summary

- Each Cloud Native App should be tracked in its own codebase, and all its dependencies should be declared in a manifest
  using tools like Gradle or Maven
- Cloud Native Application doesn't depend on servers being injected into the environment. Instead, they use an embedded
  server and are self-contained.
- Tomcat

## 4. Externalized Configuration Management

> Objectives:
> - Configuring Spring with properties and profiles
> - Applying external configuration with Spring Boot
> - Implementing a configuration server with Spring Cloud Config Server
> - Configuration applications with Spring Cloud Config Client

- When we deploy application to different environments, do we need different Tomcat Configuration for each of these
  environments?

- Traditional Apps were usually packaged as a ***bundle***,
  including source code and a series of configuration files containing data for different environments,
  with the appropriate config being selected through a flag at runtime
  => Need to make a new application build every time needed to update the configuration data for a specific environment

- ***Configuration*** is defined as everything likely to change between deployments like credentials, resource handles,
  and URLs to backing services (database)
- A key aspect of Cloud Native App is that the application artifact will stay immutable across environments.

![[04-01.png]]
**Figure 4.1 Each release you deploy is a combination of build and configuration,
which is different for each environment.**

#### Configuration Strategies

##### Property Files

- These files act as specifications of what config data the application
  supports (`application.yml` , `application.properties`)
- Useful for defining sensible default values, mainly oriented to the development environment

##### Environment Variables

- Great for **portability** because EV is supported by any OS
- Most Programming Languages allow accessing to the EV. `System.getenv()` Java
- Useful for defining configuration data that depends on the infrastructure and the platform where the app is deployed
  such as active profiles, hostnames, service names, and port numbers

##### Configuration Services

- Provides Config data persistence, auditing, and accountability
- Allow secret management by using encryption or dedicated secret vault
- Useful for defining configuration data specific to the app, such as connection pools, credentials, feature flags,
  thread pools, and URLs to third-party services

### 4.1 Configuration in Spring: Properties and Profiles

- ***Configuration*** can have different meanings depend on the context.
- Two keys aspect of a Spring application environment is ***properties*** and ***profiles***

![[04-02.png]]

#### 4.1.1 Properties: Key/Value pairs for Config

#### 4.1.2 Profiles: Feature flags and configuration groups

- Profiles are logical groups of ***beans*** that are loaded into the Spring context only if the specified profile is
  active.

##### Using Profile as Feature flags

- First use case of profile: loading groups of beans only if a specified profile is active.

##### Using Profile as Configuration Groups

### 4.2 Externalized Configuration: One build, multiple configurations

> Externalized configuration allows you to configure your application depending on where it’s deployed while
> consistently using the same immutable build for your application code. The critical aspect is that you don’t change
> your
> application after you build and package it. If any configuration change is needed (for example, different credentials
> or
> database handles), it’s done from the outside.

![[04-06.png]]

### 4.3 Centralized Configuration Management with Spring Cloud Config Server

##### Issues with Environment Variables

- Configuration Data is as important as the application code => ***Where should the config data be stored ?***
- EV do not provide granular access control features => ***How can you control access to configuration data***
- Config data will evolve and require changes.

##### Centralized Configuration is build around two components:

- A ***data store*** for config data, providing persistence, versioning, and possibly access control
- A ***server*** sitting on top of the data store to manage config data and ***serve*** it to multiple applications

> Imagine having many applications deployed in different environments. A configuration server could manage configuration
> data for all of them from a centralized place, and that configuration data might be stored in different ways. For
> example, you could use a dedicated Git repository for storing non-sensitive data and use HashiCorp Vault to store your
> secrets. No matter how the data is stored, a configuration server will deliver it to different applications through a
> unified interface


![[04-07.png]]
***Figure 4.7 A centralized configuration server manages external properties for many applications across all
environments.***

## 5. Persisting and Managing Data in the Cloud

> Objectives:
> - Understanding databases in a cloud native system
> - Implementing data persistence with Spring Data JDBC
> - Testing data persistence with Spring Boot and TestContainers
> - Managing databases in production using Flyway

##### Challenges With Data Services

- ***Scalability***: CNA can scale in and out dynamically. Data Services
- ***Resilience***:
- ***Performance***
- ***Compliance***:

##### Categories of Data Services

### 5.2 Data Persistence with Spring Data JDBC

![[05-03.png]]

- ***Data Driver***: Components that provide integration with a specific database (through connection factories)
    - JDBC (synchronous, imperative, blocking )
    - R2DBC  (reactive, asynchronous, non-blocking)
- ***Entities***: Domain Objects that are persisted in a DB.
- ***Repositories***: Abstraction used for data storage and retrieval

## 6. Containerizing Spring Boot

> ***Objectives:
> - Working with container image with Spring Boot
> - Packaging Spring Boot applications as container images
> - Managing Spring Boot containers with Docker Compose
> - Automate image build with GitHub Actions***

### 6.1 Working with Container image on Docker

> ***Docker Engine*** has a client/server architecture.
> ***Docker CLI*** is the client used to interact with the ***Docker server***.
> ***Docker Server*** is responsible for managing all ***Docker Resources*** (images, containers, networks) through
***Docker Daemon***


![[06-01.png]]

#### 6.1.1Understanding Container's Images

> - ***Container Images*** are the product of executing an ordered sequence of instructions, each resulting as a
    ***layer***
> - Each image is made up of several layers; each represents a modification produced by the corresponding instructions.

- Images can be created from scratch or starting with a base image.
- Example:
    1. ***Use Ubuntu as a base image***
    2. ***Install Java Runtime Environment***
    3. ***Run the `java --version` command***

![[06-02.png]]
***Figure 6.2 Container images are composed of an ordered sequence of read-only layers. The first one represents the
base image; the others represent modifications applied on top of it.***

- All layers are read-only
- To change something, apply a new layer on top of it (executing new instruction)

> ***copy-on-write***: a copy of the original item is created in the upper layer, and changes are applied to the copy
> rather than to the original item.

#### 6.1.2 Creating Images with Dockerfiles

> ***Dockerfile***: a script that acts as a recipe containing all the steps to *build* the desire image and a list
> sequence of instructions.

- Each Instruction starts with a command from a Docker-specific syntax -> Can pass familiar shell command as arguments
  to the instruction, depending on which Linux Distribution as a base image
- `INSTRUCTION arguments`

```Dockerfile
FROM ubuntu:22.04 

RUN apt-get update && apt-get install -y default-jre

ENTRYPOINT ["java", "--version"] 
```

1️⃣: Bases new image on the official image for Ubuntu with version 22.04
2️⃣: Installs the JRE using familiar bash commands
3️⃣: Defines the execution entry point for the running container

- Docker is configured to use Docker Hub to find and download images.
- Docker Hub is a ***registry*** that can be used for free (specific limit rate)
- Unlike VM, Containers are meant to run tasks, not operating systems.
    - If dockerfiles doesn't have ***entry point***, the containers will not run as an executable
    - The `java --version` is an ***entry point*** of the executing container.

**Table 6.1 The most common instructions used in a Dockerfile for building container images**

| Instruction  | Description                                                                                                                                                                                                                           | Example                                                |
|--------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------|
| `FROM`       | Defines the base image for the subsequent instructions. It must be the first instruction in a Dockerfile.                                                                                                                             | `FROM ubuntu:22.04`                                    |
| `LABEL`      | Adds metadata to the image, following a key/value format. Multiple `LABEL` instructions can be defined.                                                                                                                               | `LABEL version="1.2.1"`                                |
| `ARG`        | Defines a variable that user can pass at build time. Multiple `ARG` instructions can be defined                                                                                                                                       | `ARG JAR_FILE`                                         |
| `RUN`        | Executes the commands passed as arguments in a new layer on top of the existing ones. Multiple `RUN` instructions can be defined.                                                                                                     | `RUN apt-get update && apt-get install -y default-jre` |
| `COPY`       | Copies file or dir from the host filesystems to the one inside the container.                                                                                                                                                         | `COPY app-0.0.1-SNAPSHOT.jar app.jar`                  |
| `USER`       | Defines user that will run all the subsequent instructions and the images itself (as a container).                                                                                                                                    | `USER sheldon`                                         |
| `ENTRYPOINT` | Defines program to execute when the image is run as a container. Only the last `ENTRYPOINT` instruction in a Dockerfile is considered                                                                                                 | `ENTRYPOINT ["/bin/bash"]`                             |
| `CMD`        | Specifies defaults for an executing container. If the `ENTRYPOINT` instruction is defined, they are passed as arguments. If not, it should also contain an executable. Only the last `CMD` instruction in a Dockerfile is considered. | `CMD ["sleep", "10"]`                                  |

- `docker build` to build image base on specification in Dockerfile.

![[06-04.png]]
***Figure 6.4 Images are built starting from Dockerfiles. Each instruction in a Dockerfile results in an ordered
sequence of layers in the image.***

- `docker build -t my-java-image:1.0.0 .`  (don't forget the dot at the end)
    - `docker build`:  Command to build a Docker Image
    - `-t my-java-image:1.0.0`: tag/name of the image and the version of it
    - `.`: Searches for the dockerfile within the folder

#### 6.1.3 Publishing Images on GitHub Registry

> A Container registry is what a Maven repo is to Java libraries.

- Container Images follow common naming convention:  `<container_registry>/<namespace>/<name>[:<tag>]`
    - `container_registry`: the hostname for the container registry where the image is stored. When using Docker Hub,
      the hostname is ***docker.io***, with GitHub Container Registry, the hostname is ***ghcr.io***.
    - `namespace`: When using Docker Hub or GitHub Container Registry, the ***namespace*** will be the
      ***Docker/GitHub*** username in lowercase.
    - `name:tag`: The image represents the repo that contains all the version of your image.
    - Example:ghcr.io/thomasvitale/<image_name>

### 6.2 Packaging Spring Boot App as a Container Images

## 7. Kubernetes Fundamentals for Spring Boot

> Objectives:
> - Moving from Docker to K8S
> - Deploying Spring Boot Applications on K8S
> - Understanding service discovery and load balancing
> - Building scalable and disposable applications
> - Establishing a local K8S dev workflow
> - Validation K8s manifests with GitHub Actions

### 7.0 Kubernetes Revision

> K8s is an open-source system for automating the deployment, scaling, and management of containerized applications.

- When it comes to deploying containers without downtime, scaling them by leveraging the cloud's elasticity, or
  connecting them across different hosts, we will need more than container engine.
- Instead, deploying to **one** specific machine, now deploying to a **cluster** of machine.

### 7.1 Moving from Docker to K8s

- With ***Docker***, we deploy containers to an individual machine,

![[07-01.png]]
**Figure 7.1 Docker clients interact with a Docker daemon that can only manage resources on the machine where it is
installed, called the Docker host. Applications are deployed as containers to the Docker host.**

- With ***Kubernetes***, we deploy containers to a cluster of machine

![[07-02.png]]
**Figure 7.2 Kubernetes clients interact with the Control Plane, which manages containerized applications in a cluster
consisting of one or more nodes. Applications are deployed as Pods to the nodes of a cluster.**

##### Components

- ***Cluster***: A set of **nodes** running containerized applications. It hosts the ***Control Plane*** and comprises 1
  or more worker nodes.
- ***Control Plane***: The Cluster component exposing the API and interfaces to define, deploy, and manage the life
  cycle of Pods. It comprises all the essential elements that implement the typical features of an orchestrator, like
  cluster management, scheduling, and health monitoring
- ***Worker Nodes***: Physical or Virtual Machines providing capacity such as CPU, memory, network, and storage so that
  containers can run and connect to a network
- ***Pod***: The Smallest deployable unit wrapping an application container.

##### Create a K8s cluster:

```shell
minikube start --cpus 2 --memory 4g --driver docker --profile excellent 
```

### 7.2 K8s Deployments for Spring Boot

> A Spring Boot application on K8s is still packaged as a container, but it runs in a **Pod** controlled by a *
*Deployment Object**.

#### 7.2.1 From Containers to Pods

> **Pods** are the smallest units in K8s. When moving from Docker to K8, we are switching from managing containers to
> managing pods.

- A **Pod** usually comprised of one container: the application instance. (Just the same as docker container)
- However, **Pod** also contains more than one container, such as *helper container*.
    - ***Linkerd*** (service mesh): adds its own container (a ***sidecar***) to **Pods** to perform operations such as
      intercepting HTTP traffic and encrypting it to guarantee secure communication between all **Pods** via mTLS (mutual Transport Layer Security).

![[07-03.png]]
**Figure 7.3 Pods are the smallest deployable units in Kubernetes.
They run at least one primary container (the
application) and might run optional helper containers for additional features like logging, monitoring, or security.**

#### 7.2.2 Controlling Pods with Deployments

> How to scale five replicas running?

> A **Deployment** is an object that manages the life cycle of a stateless, replicated application. Each replica is
> represented by a **Pod**.
> The replicas are distributed among the nodes of a cluster for better resilience.

- Deployment helps manage Pod, deploy applications, roll out updates without downtime, roll back to a previous version
  in case of errors, and pause and resume upgrades.

- Use `ReplicaSet` object to manage replicas, ensure there's always the desired number of Pods up and running in cluster

![[07-04.png]]
**Figure 7.4 A Deployment manages replicated applications with `ReplicaSets` and Pods in your cluster. A `ReplicaSet`
ensures the desired number of Pods is always up and running. A Pod runs a containerized application.**

- **Deployment** provides a convenient abstraction to declare what we want to archive (**the desire state**).
- Ansible and Puppet are **imperative**
- We can tell K8s what we want, and the **orchestrator** will figure out how to achieve the desired results and keep it
  consistent: **the declarative configuration**

#### 7.2.3 Creating a Deployment for Spring Boot Application

- To create **Deployment**, we describe an object's desired state in a ***manifest*** file (YAML format).
- Using ***declarative configuration***: declare what we want instead of how to achieve it. `apply` them to the cluster.
- K8s automatically reconcile the actual state in the cluster with the desired state in the ***manifest***.

##### A Kubernetes Manifest files:

- `apiVersion`: Defines the versioned schema of the specific object representation.
    - Core resources such as Pods, Services follow a versioned schema composed of only a version number (v1)
    - Replicas and Deployment follows a versioned schema consisting of a group and a version number (apps/v1)
- `kind`: the type of K8s object you want to create, such as Pod, ReplicaSet, Deployment, or Service.
- `metadata`: provides detail about the object want to create, including `name` and `labels`
- `spec`: a section specific to each object type and is used to declare the **desired configuration**

![[07-05.png]]
**Figure 7.5 A Kubernetes manifest is typically composed of four main sections:`apiVersion`,`kind`,`metadata`,
and`spec`.**

### 7.3 Service Discovery and Load Balancing

> How to interact with other services from other pods?
> How can Spring Boot App be exposed and to be used by other Pods in the cluster?
> How can expose it outside the cluster?

#### 7.3.1 Understanding Service Discovery and Load Balancing

- When one service needs to communicate with another, it must be provided with information about the location (such as
  IP Address, DNS name)

![[07-06.png]]
**Figure 7.6 If there were only one Beta App instance, the interprocess communication between Alpha App and Beta App
would be based on a DNS name resolving to the IP address of Beta App.**

- Alpha App is *upstream* and Beta app is *downstream*

- In the cloud, prefer to have multiple instances of service running and each has its own IP Address.
- Unlike physical machines or long-running virtual machine, a service instance will not live long in the cloud
- Applications are **disposable**.
  => Using IP Addresses for interprocess communication in the cloud is not an option

- DNS Record, relying on round-robin name solutions.

- ***Service Discovery***:
    - Keep track of all the service instances running and store that in4 in a ***service registry***.
        - Whenever a new instance is created, an entry should be added to the registry.
        - Removed from the registry when it shut down.
    - When an application needs to call a backing service, it performs a *look-up* in the registry to determine which IP
      address to contact.
    - If multiple instances are available, a **load-balancing** strategy is applied to distribute the workload across
      them.

#### 7.3.2 Client-side service discovery and load balancing

- Client-side service discovery requires applications to register themselves with a service registry upon startup and
  unregister when shutting down.
- Whenever they need to call a backing service, they ask the service registry for an IP Address. If multiple instances
  are available, the registry will return the list of IP addresses.

![[07-07.png]]
**Figure 7.7 The interprocess communication between Alpha App and Beta App is based on the IP address of the specific
instance to call, chosen from a list of IP addresses returned upon lookup in the service registry.**

- Popular Spring projects: Spring Cloud Netflix Eureka, Spring Cloud Consul, Spring Cloud Zookeeper Discovery, and
  Spring Cloud Alibaba Nacos.

- All the solution has is that the application has complete control over the ***load-balancing*** strategy
- ***Hedging***: Sending the same request to multiple instances to increase the chance one responds correctly within a
  specific time limit

##### Drawbacks:

- More responsibility to developers.
- If a system consists of different languages => have to handle the client part of each of them in a different way

#### 7.3.3 Server-side Service Discovery and Load-Balancing

- Automatically register and deregister app instances and rely on a **load-balancer** component to **route** any
  incoming requests to one of the available instances according to a specific strategy

![[07-08.png]]
**Figure 7.8
The interprocess communication between Alpha App and Beta App is based on a DNS name
that gets resolved to one of the instance IP addresses by a load-balancer component.
The platform handles the service registration process transparently.**

- The K8s Implementation of this service discovery pattern is based on **Service** object.

> A ***Service*** is "an abstract way to expose an application running on a set of **Pods** as a network service"
> A ***Service*** object is an abstraction targeting a set of Pods and defining access policy

1. When an application needs to interact with Pods exposed by Service object, it can use the Service name instead of
   calling the Pod directly
2. The Service name is then resolved to the IP Address of the Service itself by a local DNS server running in the K8s
   Control Plane
3. After resolving the Service name to its IP Address, K8s relies on a proxy (**kube-proxy**), which intercepts the
   connection to the Service object and forwards the request to one of the Pods targeted by the Service.
4. The proxy knows all the replicas available and adopts a load-balancing strategy depending on the type of Service and
   the proxy configuration

![[07-09.png]]
**Figure 7.9 In Kubernetes,
the interprocess communication between Alpha App and Beta App happens through a Service object.
Any request arriving at the Service is intercepted by a proxy
that forwards it to one of the replicas targeted by the Service based on a specific load-balancing strategy.**

#### 7.3.4 Exposing Spring Boot applications with Kubernetes Services

- There are different types of Services, depending on **access policy**
    - `NodePort`
    - `ClusterIP`
    - `ExternalName`
    - `LoadBalancer`

- The default a most common type is called `ClusterIP`
- `ClusterIP`: exposes a set of Pods to the cluster.
    - The `selector` label used to match all the Pods that should be targeted and exposed by the Service
    - The `network` protocol used by the service.
    - The `port` on which the **Service** is listening.
    - The `targetPort`: the port exposed by the targeted Pods to which the Service will forward requests.

![[07-10.png]]
**Figure 7.10 shows the relationship between a ClusterIP Service and a set of target Pods running applications exposed
on port 8080. The name of the Service must be a valid DNS name, since it will be used by other Pods as a hostname to
access the targeted Pods.**

##### CREATING A SERVICE OBJECT FROM A MANIFEST

```shell
kubectl apply -f k8s/service.yml
```

- The command will be processed by the K8s Control Plane, which will create and maintain the Service object in the
  cluster.

### 7.4 Scalability and Disposability

- Deploying multiple instances can ensure the **availability** of system.
- When the workload is high, the system can be distributed across replicas (like cdn)
- Instance got faulty, it will be removed, and a new one be created.

> This continuous and dynamic scaling of app instances requires stateless and disposable application

#### How does it mean to be Disposable:

##### 7.4.1 Ensuring Disposability: Fast Startup

- Traditional Applications deployed on application server take lots of time to start.
- Cloud Native App should be optimized for **fast startup** because:
    - Applications in Cloud Native are **disposable**
    - Applications in Cloud Native are **frequently created, destroyed, and scaled**
    - The quicker the startup, the sooner a new app instance is ready to accept connection

##### 7.4.2 Ensuring Disposability: Graceful shutdown

- Whenever the application is shutdown, it must happen **gracefully** without clients experiencing downtime or errors.
- **Gracefully** shutdown means that the application stops accepting new requests, complete those still in progress, and
  closes any open resources (database connections)


- By default, Spring Boot stops the server immediately after receiving a termination signal (SIGTERM)

- After enabling support for **graceful shutdown** -> update the **Deployment** manifest accordingly.
- When the **Pod** has to be terminated, K8s sends a `SIGTERM` signal. Spring Boot will intercept that signal and start
  shutting down gracefully.
- By default, K8s sends a `SIGKILL` signal to force the Pod's termination. Since the Spring Boot grace period is lower
  than the K8s, the app is in control of when it will terminate.
- When K8s sends the `SIGTERM` signal to Pod

# Part 3: Cloud Native-Distributed Systems

## 8. Reactive Spring: Resilience and Scalability

> Objectives:
> - Understanding Reactive Programming with Reactor and Spring
> - Building reactive servers with Spring WebFlux and Spring Data R2DBC
> - Building reactive clients with WebClient
> - Improving resilience for applications with Reactor
> - Testing Reactive Applications with Spring and TestContainers

- In the ***thread-per-request*** model, each request is bound to a thread exclusively allocated to its processing.
- Reactive Applications operate asynchronously and in a non-blocking way

## 9. API Gateway and Circuit Breakers

> Objectives:
> - Implementing edge services with Spring Cloud Gateway and Reactive Spring
> - Configuring circuit breakers with Spring Cloud Circuit Breaker and Resilience4J
> - Defining rate limiters with Spring Cloud Gateway and Redis
> - Managing distributed sessions with Spring Session Data Redis
> - Routing Application traffic with K8s Ingress


