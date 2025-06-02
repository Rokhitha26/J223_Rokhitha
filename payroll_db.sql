show databases;
create database angularProject;

use angularProject;
CREATE TABLE Users (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(50) UNIQUE NOT NULL,
    PasswordHash VARCHAR(255) NOT NULL,
    Role ENUM('ADMIN', 'HR_MANAGER', 'PAYROLL_PROCESSOR', 'EMPLOYEE', 'MANAGER') NOT NULL,
    Email VARCHAR(100),
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT UNIQUE,  -- links to Users
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Gender ENUM('MALE', 'FEMALE', 'OTHER'),
    DOB DATE,
    Department VARCHAR(50),
    Designation VARCHAR(50),
    DateOfJoining DATE,
    ContactNumber VARCHAR(15),
    Address TEXT,
    Status ENUM('ACTIVE', 'INACTIVE', 'TERMINATED'),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);
CREATE TABLE Payroll (
    PayrollID INT PRIMARY KEY AUTO_INCREMENT,
    EmployeeID INT,
    BaseSalary DECIMAL(10, 2),
    Bonuses DECIMAL(10, 2),
    Deductions DECIMAL(10, 2),
    NetSalary DECIMAL(10, 2),
    PayMonth DATE,  -- typically stored as 1st of month
    Status ENUM('PENDING', 'PROCESSED', 'VERIFIED'),
    ProcessedBy INT,  -- UserID of payroll processor
    ProcessedDate TIMESTAMP,
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID),
    FOREIGN KEY (ProcessedBy) REFERENCES Users(UserID)
);
CREATE TABLE LeaveRequests (
    LeaveID INT PRIMARY KEY AUTO_INCREMENT,
    EmployeeID INT,
    StartDate DATE,
    EndDate DATE,
    LeaveType VARCHAR(50),
    Reason TEXT,
    Status ENUM('PENDING', 'APPROVED', 'REJECTED'),
    RequestedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ApprovedBy INT,  -- UserID of Manager/Supervisor
    ApprovedDate TIMESTAMP,
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID),
    FOREIGN KEY (ApprovedBy) REFERENCES Users(UserID)
);
CREATE TABLE Notifications (
    NotificationID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    Message TEXT,
    Type ENUM('SYSTEM', 'PAYROLL', 'LEAVE', 'GENERAL'),
    IsRead BOOLEAN DEFAULT FALSE,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);
CREATE TABLE AuditTrail (
    LogID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    Action VARCHAR(100),
    Description TEXT,
    Timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);
INSERT INTO Users (Username, PasswordHash, Role, Email) VALUES
('rossgeller', 'hashedpass1', 'EMPLOYEE', 'ross@itcorp.com'),
('rachelgreen', 'hashedpass2', 'EMPLOYEE', 'rachel@itcorp.com'),
('monicageller', 'hashedpass3', 'HR_MANAGER', 'monica@itcorp.com'),
('chandlerbing', 'hashedpass4', 'PAYROLL_PROCESSOR', 'chandler@itcorp.com'),
('joeytribbiani', 'hashedpass5', 'MANAGER', 'joey@itcorp.com'),
('phoebebuffay', 'hashedpass6', 'ADMIN', 'phoebe@itcorp.com');
INSERT INTO Employees (UserID, FirstName, LastName, Gender, DOB, Department, Designation, DateOfJoining, ContactNumber, Address, Status) VALUES
(1, 'Ross', 'Geller', 'MALE', '1985-10-18', 'Software Development', 'Backend Developer', '2010-06-15', '555-101-2020', '100 Tech Park', 'ACTIVE'),
(2, 'Rachel', 'Green', 'FEMALE', '1986-05-05', 'UI/UX Design', 'UI Designer', '2012-09-10', '555-303-4040', '101 Design Blvd', 'ACTIVE'),
(3, 'Monica', 'Geller', 'FEMALE', '1985-03-22', 'Human Resources', 'HR Manager', '2009-02-01', '555-505-6060', '102 Admin St', 'ACTIVE'),
(4, 'Chandler', 'Bing', 'MALE', '1985-04-08', 'Payroll', 'Payroll Specialist', '2011-08-15', '555-707-8080', '103 Finance Ave', 'ACTIVE'),
(5, 'Joey', 'Tribbiani', 'MALE', '1986-01-09', 'Project Management', 'Project Manager', '2013-03-23', '555-909-0000', '104 PM Road', 'ACTIVE'),
(6, 'Phoebe', 'Buffay', 'FEMALE', '1985-02-16', 'IT Administration', 'System Administrator', '2008-12-01', '555-111-2222', '105 IT Lane', 'ACTIVE');
INSERT INTO Payroll (EmployeeID, BaseSalary, Bonuses, Deductions, NetSalary, PayMonth, Status, ProcessedBy, ProcessedDate) VALUES
(1, 90000.00, 5000.00, 2000.00, 93000.00, '2025-05-01', 'VERIFIED', 4, NOW()),
(2, 75000.00, 3500.00, 1500.00, 77000.00, '2025-05-01', 'VERIFIED', 4, NOW()),
(3, 85000.00, 4000.00, 1800.00, 87200.00, '2025-05-01', 'VERIFIED', 4, NOW()),
(4, 70000.00, 3000.00, 1600.00, 71400.00, '2025-05-01', 'VERIFIED', 4, NOW()),
(5, 80000.00, 4500.00, 1700.00, 82800.00, '2025-05-01', 'VERIFIED', 4, NOW()),
(6, 78000.00, 3200.00, 1400.00, 79800.00, '2025-05-01', 'VERIFIED', 4, NOW());
INSERT INTO LeaveRequests (EmployeeID, StartDate, EndDate, LeaveType, Reason, Status, RequestedDate, ApprovedBy, ApprovedDate) VALUES
(1, '2025-06-10', '2025-06-20', 'Vacation', 'Family trip to mountains', 'APPROVED', NOW(), 5, NOW()),
(2, '2025-06-15', '2025-06-18', 'Sick Leave', 'Medical reasons', 'PENDING', NOW(), NULL, NULL),
(3, '2025-07-01', '2025-07-05', 'Vacation', 'Relaxation and recharge', 'APPROVED', NOW(), 5, NOW()),
(4, '2025-05-25', '2025-05-27', 'Personal Leave', 'Urgent personal work', 'REJECTED', NOW(), 5, NOW()),
(5, '2025-08-10', '2025-08-15', 'Vacation', 'Traveling abroad', 'APPROVED', NOW(), 6, NOW()),
(6, '2025-06-20', '2025-06-22', 'Sick Leave', 'Recovering from illness', 'PENDING', NOW(), NULL, NULL);
INSERT INTO Notifications (UserID, Message, Type, IsRead, CreatedAt) VALUES
(1, 'Your payroll for May 2025 has been processed.', 'PAYROLL', FALSE, NOW()),
(2, 'Leave request is pending approval.', 'LEAVE', FALSE, NOW()),
(3, 'Payroll policy updated by HR Manager.', 'SYSTEM', FALSE, NOW()),
(4, 'New payroll tasks assigned.', 'PAYROLL', FALSE, NOW()),
(5, 'Your leave request was approved.', 'LEAVE', TRUE, NOW()),
(6, 'System maintenance scheduled for Sunday night.', 'SYSTEM', FALSE, NOW());
INSERT INTO AuditTrail (UserID, Action, Description, Timestamp) VALUES
(6, 'User Login', 'Admin Phoebe logged in.', NOW()),
(4, 'Processed Payroll', 'Chandler processed May 2025 payroll.', NOW()),
(3, 'Approved Leave', 'Monica approved Ross\' leave request.', NOW()),
(5, 'Submitted Leave', 'Joey submitted a vacation leave request.', NOW()),
(2, 'Updated Profile', 'Rachel updated her contact information.', NOW()),
(1, 'Viewed Payslip', 'Ross viewed his May 2025 payslip.', NOW());
