import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.10"
	kotlin("plugin.spring") version "1.5.10"
}

group = "com.mthaler"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework:spring-context:5.3.8")
	implementation("org.springframework:spring-jdbc:5.3.8")
	implementation("org.slf4j:slf4j-api:1.7.31")
	implementation("ch.qos.logback:logback-classic:1.2.3")
	implementation("javax.annotation:javax.annotation-api:1.3.2")
	implementation("org.apache.commons:commons-dbcp2:2.8.0")
	implementation("org.hibernate:hibernate-entitymanager:5.5.2.Final")
	implementation("org.springframework:spring-orm:5.3.8")
	implementation("org.springframework.data:spring-data-jpa:2.5.2")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation(kotlin("test"))
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
