val arrowVersion = "1.0.1"
val kotestVersion = "5.3.0"

plugins {
    kotlin("jvm") version "1.6.21"
}

repositories {
    mavenCentral()
    maven(url = "https://dl.bintray.com/arrow-kt/arrow-kt/")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(platform("io.arrow-kt:arrow-stack:$arrowVersion"))
    implementation("io.arrow-kt:arrow-core")
    implementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")

    testImplementation(kotlin("test"))
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
