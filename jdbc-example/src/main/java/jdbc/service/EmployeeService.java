package jdbc.service;

import jdbc.domain.Employee;
import jdbc.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс, отвечающий за сервисные функции для SelectAllEmployeesService
 */
public class EmployeeService {
    private static final Logger LOGGER = LogManager.getLogger(EmployeeService.class);

    public void getAllEmployees() {
        for (Employee employee : new SelectAllEmployeesJDBC().select()) {
            LOGGER.info("Employee ID: {}, Name: {} {}, Position: {}, Salary: {}", employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(), employee.getPosition(), employee.getSalary());
        }
    }

    public void getEmployeeById(int employeeId) {
        Employee employee = new SelectEmployeeByIdJDBC().selectById(employeeId);
        LOGGER.info("Employee ID: {}, Name: {} {}, Position: {}, Salary: {}", employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(), employee.getPosition(), employee.getSalary());
    }

    public void addNewEmployee(Employee employee) {
        int employeesAdded = new InsertEmployeeJDBC().insert(employee);
        LOGGER.info(employeesAdded);
    }

    public void updateEmployeeById(Employee employee) {
        int employeesUpdated = new UpdateEmployeeByIdJDBC().update(employee);
        LOGGER.info(employeesUpdated);
    }

    public void deleteEmployeeById(Employee employee) {
        int employeesDeleted = new DeleteEmployeeByIdJDBC().delete(employee);
        LOGGER.info(employeesDeleted);
    }
}
