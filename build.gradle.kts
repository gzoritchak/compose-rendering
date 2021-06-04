import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    id("org.jetbrains.compose") version "0.4.0"
}

group = "me.gaetan"
version = "1.0"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
    maven { url = uri("https://maven.pkg.jetbrains.space/data2viz/p/maven/public") }
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("io.data2viz.d2v:color:0.8.12")
    implementation("io.data2viz.d2v:random:0.8.12")
    implementation("io.data2viz.d2v:shape:0.8.12")
    implementation("io.data2viz.d2v:scale:0.8.12")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "compose"
            packageVersion = "1.0.0"
        }
    }
}