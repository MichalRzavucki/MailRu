package jdbc;

import jdbc.domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jdbc.service.EmployeeService;

public class JdbcConnector {
    private static final Logger LOGGER = LogManager.getLogger(JdbcConnector.class);

    public static void main(String[] args) {
        // Выбор всех сотрудников из БД
        LOGGER.info("Выбор всех сотрудников из БД:");
        new EmployeeService().getAllEmployees();

        // Выбор одного сотрудника из БД по ID
        LOGGER.info("Выбор одного сотрудника из БД по ID:");
        new EmployeeService().getEmployeeById(1);

        // Добавление нового сотрудника в БД
        LOGGER.info("Добавление нового сотрудника в БД:");
        new EmployeeService().addNewEmployee(new Employee("Sarah", "Johnson", "Engineer", 65000.00));

        // Обновление информации о сотруднике в БД по ID
        LOGGER.info("Обновление информации о сотруднике в БД по ID:");
        new EmployeeService().updateEmployeeById(new Employee(1, "Senior Manager", 55000.00));

        // Удаление сотрудника из БД по ID
        LOGGER.info("Удаление сотрудника из БД по ID:");
        new EmployeeService().deleteEmployeeById(new Employee(1));
    }
}
