CREATE TABLE IF NOT EXISTS Employees (
    employee_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    position VARCHAR(50),
    salary DECIMAL(10, 2)
);

TRUNCATE TABLE Employees;

INSERT INTO Employees (employee_id, first_name, last_name, position, salary)
VALUES
(1, 'John', 'Doe', 'Manager', 50000.00),
(2, 'Jane', 'Smith', 'Developer', 60000.00),
(3, 'Alice', 'Johnson', 'Salesperson', 45000.00),
(4, 'Bob', 'Brown', 'Analyst', 55000.00),
(5, 'Emily', 'Davis', 'Designer', 48000.00);