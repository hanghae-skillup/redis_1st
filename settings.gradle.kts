plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "redis_1st"
include("module-application")
include("module-domain")
include("module-infrastructure")
include("module-common")
