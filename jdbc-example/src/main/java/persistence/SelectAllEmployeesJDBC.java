package persistence;

import domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ConnectionInitializer;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс, отвечающий за выбор всех сотрудников из базы данных
 */
public class SelectAllEmployeesJDBC extends ConnectionInitializer {
    private static final Logger LOGGER = LogManager.getLogger(SelectAllEmployeesJDBC.class);
    protected DataSource dataSource = createDataSource();

    public List<Employee> select() {
        List<Employee> employees = new ArrayList<>();

        try (var connection = dataSource.getConnection()) {
            String selectQuery = "SELECT * FROM Employees";
            PreparedStatement ps = connection.prepareStatement(selectQuery);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int employeeId = rs.getInt("employee_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String position = rs.getString("position");
                double salary = rs.getDouble("salary");
                employees.add(new Employee(employeeId, firstName, lastName, position, salary));
            }
            return employees;

        } catch (SQLException e) {
            LOGGER.error("Ошибка SQL", e);
        }
        return Collections.emptyList();
    }
}
