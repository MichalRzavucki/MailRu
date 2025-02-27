package jdbc.persistence;

import jdbc.domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jdbc.service.ConnectionInitializer;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс, отвечающий за выбор сотрудника по ID из базы данных
 */
public class SelectEmployeeByIdJDBC extends ConnectionInitializer {
    private static final Logger LOGGER = LogManager.getLogger(SelectEmployeeByIdJDBC.class);
    protected DataSource dataSource = createDataSource();

    public Employee selectById(int employeeId) {
        try (var connection = dataSource.getConnection()) {
            String selectByIdQuery = "SELECT * FROM Employees WHERE employee_id = ?";
            PreparedStatement ps = connection.prepareStatement(selectByIdQuery);
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String position = rs.getString("position");
                double salary = rs.getDouble("salary");

                return new Employee(firstName, lastName, position, salary);
            } else {
                LOGGER.error("Сотрудник с ID {} не найден", employeeId);
            }

        } catch (SQLException e) {
            LOGGER.error("Ошибка SQL", e);
        }
        return null;
    }
}
