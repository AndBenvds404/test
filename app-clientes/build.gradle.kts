plugins {
    id("java")
    id("io.quarkus") version "3.11.1"

}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


val quarkusVersion = "3.11.1"
dependencies {
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:${quarkusVersion}"))

    implementation("io.quarkus:quarkus-arc") //CDI: Arc
    implementation("io.quarkus:quarkus-resteasy-reactive") //JAX-RS: RESTEasy
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson") //JAX-RS-Json: Jackson

    implementation("io.quarkus:quarkus-hibernate-orm-panache") //JPA Hibernate+ repo
    implementation("io.quarkus:quarkus-jdbc-postgresql") //Driver JDBC: PostgreSQL

    implementation("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    //--rest client
    implementation("io.quarkus:quarkus-rest-client-reactive")
    implementation("io.quarkus:quarkus-rest-client-reactive-jackson")

    implementation("io.smallrye.reactive:smallrye-mutiny-vertx-consul-client")


}

tasks.test {
    useJUnitPlatform()
}