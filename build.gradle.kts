plugins {
    id 'java'
    id 'application'
    id 'org.openjfx.javafxplugin' version '0.0.13'
    id 'org.beryx.jlink' version '2.25.0'
}

group 'com.client'
version '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

ext {
    junitVersion = '5.9.1'
}

sourceCompatibility = '19'
targetCompatibility = '19'

tasks.withType(JavaCompile) {
    options.encoding = 'UTF-8'
}

application {
    mainModule = 'com.client'
    mainClass = 'com.client.ApplicationLauncher'
}

javafx {
    version = '19'
    modules = ['javafx.controls', 'javafx.fxml']
}

dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
    implementation("com.squareup.okhttp:okhttp:2.7.5")
}

test {
    useJUnitPlatform()
}

jlink {
    imageZip = project.file("${buildDir}/distributions/app-${javafx.platform.classifier}.zip")
    options = ['--strip-debug', '--compress', '2', '--no-header-files', '--no-man-pages']
    launcher {
        name = 'app'
    }
}

jlinkZip {
    group = 'distribution'
}