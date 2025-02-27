package persistence;

import domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ConnectionInitializer;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Класс, отвечающий за добавление нового сотрудника в базу данных
 */
public class InsertEmployeeJDBC extends ConnectionInitializer {
    private static final Logger LOGGER = LogManager.getLogger(InsertEmployeeJDBC.class);
    protected DataSource dataSource = createDataSource();

    public int insert(Employee employee) {
        try (var connection = dataSource.getConnection()) {
            String insertQuery = "INSERT INTO Employees (employee_id, first_name, last_name, position, salary) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(insertQuery);
            ps.setInt(1, 6);
            ps.setString(2, employee.getFirstName());
            ps.setString(3, employee.getLastName());
            ps.setString(4, employee.getPosition());
            ps.setDouble(5, employee.getSalary());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                LOGGER.info("Новый сотрудник успешно добавлен в базу данных");
            } else {
                LOGGER.error("Не удалось добавить нового сотрудника");
            }

            return rowsAffected;

        } catch (SQLException e) {
            LOGGER.error("Ошибка SQL", e);
        }
        return 0;
    }
}
