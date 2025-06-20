create database payroll;
use payroll;

show tables;
select*from employee_Profile;
select*From payroll;
desc payroll;
select*from users;
INSERT INTO employee_profile (first_name, last_name, address, contact_number, date_of_joining, department, gender, status) 
VALUES 
('Riya', 'Sharma', '123 Green St, Delhi', '9876543210', '2023-05-10', 'Finance', 'female', 'active'),
('Amit', 'Verma', '456 Blue Rd, Mumbai', '9823456789', '2022-11-20', 'IT', 'male', 'active'),
('Sneha', 'Patil', '789 Red Ln, Pune', '9898989898', '2024-01-15', 'Marketing', 'female', 'inactive'),
('Rahul', 'Kumar', '321 Yellow Ave, Chennai', '9845123456', '2021-08-01', 'HR', 'male', 'terminated'),
('Meera', 'Iyer', '654 Orange Blvd, Bangalore', '9812345678', '2023-09-25', 'Admin', 'female', 'active');

INSERT INTO users (u_id, first_name, last_name, password, email, role)
VALUES 
(1, 'Riya', 'Sharma', 'riya@123', 'riya.sharma@company.com', 'Employee'),
(2, 'Amit', 'Verma', 'amit@456', 'amit.verma@company.com', 'Supervisor'),
(3, 'Sneha', 'Patil', 'sneha@789', 'sneha.patil@company.com', 'Employee'),
(4, 'Rahul', 'Kumar', 'rahul@321', 'rahul.kumar@company.com', 'Employee'),
(5, 'Meera', 'Iyer', 'meera@654', 'meera.iyer@company.com', 'Admin');


INSERT INTO payroll (amount, payroll_date, emp_id) VALUES (55000.00, '2025-05-31', 2);  -- Amit Verma
INSERT INTO payroll (amount, payroll_date, emp_id) VALUES (47000.50, '2025-05-31', 3);  -- Sneha Patil
INSERT INTO payroll (amount, payroll_date, emp_id) VALUES (51000.75, '2025-06-01', 4);  -- Rahul Kumar
INSERT INTO payroll (amount, payroll_date, emp_id) VALUES (49500.00, '2025-06-15', 5);  -- Meera Iyer
INSERT INTO payroll (amount, payroll_date, emp_id) VALUES (52000.25, '2025-06-15', 2);  -- Amit Verma again
