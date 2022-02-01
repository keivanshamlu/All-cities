package modules

object Modules {

    const val APP = ":app"
    const val DOMAIN = ":domain"
    const val NAVIGATION = ":navigation"
    object Data {
        private const val dir = ":data"
        const val DATA = ":$dir:data-core"

        const val DATA_LOCAL = "$dir:data-local"
    }
    object Feature {
        private const val dir = ":features"
        const val CITIES = "$dir:cities"
        const val MAP = "$dir:map"
    }
    object Utility {
        private const val dir = ":utility"
        const val BASES = "$dir:bases"
        const val BASES_ANDROID = "$dir:bases-android"
    }
    object Test {
        private const val dir = ":test"
        const val CORE = "$dir:core-test"

    }
}