package persistence;

import domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ConnectionInitializer;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Класс, отвечающий за обновление информации о сотруднике в базе данных по ID
 */
public class UpdateEmployeeByIdJDBC extends ConnectionInitializer {
    private static final Logger LOGGER = LogManager.getLogger(UpdateEmployeeByIdJDBC.class);
    protected DataSource dataSource = createDataSource();

    public int update(Employee employee) {
        try (var connection = dataSource.getConnection()) {
            String updateQuery = "UPDATE Employees SET position = ?, salary = ? WHERE employee_id = ?";
            PreparedStatement ps = connection.prepareStatement(updateQuery);
            ps.setString(1, employee.getPosition());
            ps.setDouble(2, employee.getSalary());
            ps.setInt(3, employee.getEmployeeId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                LOGGER.info("Данные сотрудника с ID {} успешно обновлены", employee.getEmployeeId());
            } else {
                LOGGER.error("Не удалось обновить данные сотрудника с ID {}", employee.getEmployeeId());
            }

            return rowsAffected;

        } catch (SQLException e) {
            LOGGER.error("Ошибка SQL", e);
        }
        return 0;
    }
}
