import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.lang.Integer.parseInt
import java.lang.System.getenv
import java.net.URI

plugins {
	id("org.springframework.boot") version "2.2.5.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	id("io.franzbecker.gradle-lombok") version "3.2.0"
	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
}

group = "com.archesky"
version = "0.0.${parseInt(getenv().getOrDefault("BUILD_NUM", "1"))}-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

val developmentOnly by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
}

repositories {
	jcenter()
	maven {
		url = URI("http://pynguins.com:8081/nexus/content/repositories/releases")
	}
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.graphql-java-kickstart:graphql-spring-boot-starter:7.1.0")
	implementation("com.graphql-java-kickstart:graphql-kickstart-spring-boot-starter-tools:7.1.0")
	implementation("com.arangodb:arangodb-java-driver:6.6.0")
	implementation("com.arangodb:arangodb-spring-data:3.2.3")
	implementation("com.archesky.auth.library:archesky-auth-library:1.4.0")
	runtimeOnly("com.graphql-java-kickstart:altair-spring-boot-starter:7.1.0")
	runtimeOnly("com.graphql-java-kickstart:graphiql-spring-boot-starter:7.1.0")
	runtimeOnly("com.graphql-java-kickstart:voyager-spring-boot-starter:7.1.0")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("com.graphql-java-kickstart:graphql-spring-boot-test:7.1.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
