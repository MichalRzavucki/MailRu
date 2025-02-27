package persistence;

import domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ConnectionInitializer;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Класс, отвечающий за удаление сотрудника из базы данных по ID
 */
public class DeleteEmployeeByIdJDBC extends ConnectionInitializer {
    private static final Logger LOGGER = LogManager.getLogger(DeleteEmployeeByIdJDBC.class);
    protected DataSource dataSource = createDataSource();

    public int delete(Employee employee) {
        try (var connection = dataSource.getConnection()) {
            String deleteQuery = "DELETE FROM Employees WHERE employee_id = ?";
            PreparedStatement ps = connection.prepareStatement(deleteQuery);
            ps.setInt(1, employee.getEmployeeId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                LOGGER.info("Сотрудник с ID {} успешно удалён из базы данных", employee.getEmployeeId());
            } else {
                LOGGER.error("Не удалось удалить сотрудника с ID {}", employee.getEmployeeId());
            }
            return rowsAffected;

        } catch (SQLException e) {
            LOGGER.error("Ошибка SQL", e);
        }
        return 0;
    }
}
