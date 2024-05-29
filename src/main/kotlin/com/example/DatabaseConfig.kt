import org.flywaydb.core.Flyway

object DatabaseConfig {
    fun init() {
        val url = "jdbc:postgresql://localhost:5432/postgres"
        val user = "hannes"
        val password = "secret"

        val flyway = Flyway.configure()
            .dataSource(url, user, password)
            .load()

        flyway.migrate()
    }
}
