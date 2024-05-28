// DatabaseConfig.kt

import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

object DatabaseConfig {
    fun init() {
        val url = "jdbc:postgresql://localhost:5432/postgres"
        val user = "hannes"
        val password = "secret"

        // Initialize Flyway
        val flyway = Flyway.configure()
            .dataSource(url, user, password)
            .load()

        // Start the migration
        flyway.migrate()

        // Connect to the database using Exposed or any other library
        Database.connect(url, driver = "org.postgresql.Driver", user = user, password = password)
    }
}
