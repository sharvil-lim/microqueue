plugins {
    id("java")
}

group = "com.sharvillimaye"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("org.projectlombok:lombok:1.18.30")
    implementation("jakarta.jms:jakarta.jms-api:3.1.0")
}

tasks.test {
    useJUnitPlatform()
}