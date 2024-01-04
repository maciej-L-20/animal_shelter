package bada_shelter.SpringApplication;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDatabaseConnection() {
        System.setProperty("oracle.net.tns_admin",
                System.getProperty("user.dir") + File.separator + "wallet");
        try (Connection connection = dataSource.getConnection()) {
            assertTrue(connection.isValid(5)); // Sprawdzamy, czy połączenie jest ważne przez 5 sekund
        } catch (SQLException e) {
            e.printStackTrace();
            // Rzuć wyjątkiem, jeśli połączenie nie powiedzie się
            assertTrue(false, "Database connection failed");
        }
    }
}
