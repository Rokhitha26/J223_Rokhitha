show databases;
create database angularproject;

use angularproject;

create table users (
    userid int primary key auto_increment,
    username varchar(50) unique not null,
    passwordhash varchar(255) not null,
    role enum('admin', 'hr_manager', 'payroll_processor', 'employee', 'manager') not null,
    email varchar(100),
    createdat timestamp default current_timestamp
);

create table employees (
    employeeid int primary key auto_increment,
    userid int unique,
    firstname varchar(50),
    lastname varchar(50),
    gender enum('male', 'female', 'other'),
    dob date,
    department varchar(50),
    designation varchar(50),
    dateofjoining date,
    contactnumber varchar(15),
    address text,
    status enum('active', 'inactive', 'terminated'),
    foreign key (userid) references users(userid)
);

create table payroll (
    payrollid int primary key auto_increment,
    employeeid int,
    basesalary decimal(10, 2),
    bonuses decimal(10, 2),
    deductions decimal(10, 2),
    netsalary decimal(10, 2),
    paymonth date,
    status enum('pending', 'processed', 'verified'),
    processedby int,
    processeddate timestamp,
    foreign key (employeeid) references employees(employeeid),
    foreign key (processedby) references users(userid)
);

create table leaverequests (
    leaveid int primary key auto_increment,
    employeeid int,
    startdate date,
    enddate date,
    leavetype varchar(50),
    reason text,
    status enum('pending', 'approved', 'rejected'),
    requesteddate timestamp default current_timestamp,
    approvedby int,
    approveddate timestamp,
    foreign key (employeeid) references employees(employeeid),
    foreign key (approvedby) references users(userid)
);

create table notifications (
    notificationid int primary key auto_increment,
    userid int,
    message text,
    type enum('system', 'payroll', 'leave', 'general'),
    isread boolean default false,
    createdat timestamp default current_timestamp,
    foreign key (userid) references users(userid)
);

create table audittrail (
    logid int primary key auto_increment,
    userid int,
    action varchar(100),
    description text,
    timestamp timestamp default current_timestamp,
    foreign key (userid) references users(userid)
);

insert into users (username, passwordhash, role, email) values
('rossgeller', 'hashedpass1', 'employee', 'ross@itcorp.com'),
('rachelgreen', 'hashedpass2', 'employee', 'rachel@itcorp.com'),
('monicageller', 'hashedpass3', 'hr_manager', 'monica@itcorp.com'),
('chandlerbing', 'hashedpass4', 'payroll_processor', 'chandler@itcorp.com'),
('joeytribbiani', 'hashedpass5', 'manager', 'joey@itcorp.com'),
('phoebebuffay', 'hashedpass6', 'admin', 'phoebe@itcorp.com');

insert into employees (userid, firstname, lastname, gender, dob, department, designation, dateofjoining, contactnumber, address, status) values
(1, 'ross', 'geller', 'male', '1985-10-18', 'software development', 'backend developer', '2010-06-15', '555-101-2020', '100 tech park', 'active'),
(2, 'rachel', 'green', 'female', '1986-05-05', 'ui/ux design', 'ui designer', '2012-09-10', '555-303-4040', '101 design blvd', 'active'),
(3, 'monica', 'geller', 'female', '1985-03-22', 'human resources', 'hr manager', '2009-02-01', '555-505-6060', '102 admin st', 'active'),
(4, 'chandler', 'bing', 'male', '1985-04-08', 'payroll', 'payroll specialist', '2011-08-15', '555-707-8080', '103 finance ave', 'active'),
(5, 'joey', 'tribbiani', 'male', '1986-01-09', 'project management', 'project manager', '2013-03-23', '555-909-0000', '104 pm road', 'active'),
(6, 'phoebe', 'buffay', 'female', '1985-02-16', 'it administration', 'system administrator', '2008-12-01', '555-111-2222', '105 it lane', 'active');

insert into payroll (employeeid, basesalary, bonuses, deductions, netsalary, paymonth, status, processedby, processeddate) values
(1, 90000.00, 5000.00, 2000.00, 93000.00, '2025-05-01', 'verified', 4, now()),
(2, 75000.00, 3500.00, 1500.00, 77000.00, '2025-05-01', 'verified', 4, now()),
(3, 85000.00, 4000.00, 1800.00, 87200.00, '2025-05-01', 'verified', 4, now()),
(4, 70000.00, 3000.00, 1600.00, 71400.00, '2025-05-01', 'verified', 4, now()),
(5, 80000.00, 4500.00, 1700.00, 82800.00, '2025-05-01', 'verified', 4, now()),
(6, 78000.00, 3200.00, 1400.00, 79800.00, '2025-05-01', 'verified', 4, now());

insert into leaverequests (employeeid, startdate, enddate, leavetype, reason, status, requesteddate, approvedby, approveddate) values
(1, '2025-06-10', '2025-06-20', 'vacation', 'family trip to mountains', 'approved', now(), 5, now()),
(2, '2025-06-15', '2025-06-18', 'sick leave', 'medical reasons', 'pending', now(), null, null),
(3, '2025-07-01', '2025-07-05', 'vacation', 'relaxation and recharge', 'approved', now(), 5, now()),
(4, '2025-05-25', '2025-05-27', 'personal leave', 'urgent personal work', 'rejected', now(), 5, now()),
(5, '2025-08-10', '2025-08-15', 'vacation', 'traveling abroad', 'approved', now(), 6, now()),
(6, '2025-06-20', '2025-06-22', 'sick leave', 'recovering from illness', 'pending', now(), null, null);

insert into notifications (userid, message, type, isread, createdat) values
(1, 'your payroll for may 2025 has been processed.', 'payroll', false, now()),
(2, 'leave request is pending approval.', 'leave', false, now()),
(3, 'payroll policy updated by hr manager.', 'system', false, now()),
(4, 'new payroll tasks assigned.', 'payroll', false, now()),
(5, 'your leave request was approved.', 'leave', true, now()),
(6, 'system maintenance scheduled for sunday night.', 'system', false, now());

insert into audittrail (userid, action, description, timestamp) values
(6, 'user login', 'admin phoebe logged in.', now()),
(4, 'processed payroll', 'chandler processed may 2025 payroll.', now()),
(3, 'approved leave', 'monica approved ross'' leave request.', now()),
(5, 'submitted leave', 'joey submitted a vacation leave request.', now()),
(2, 'updated profile', 'rachel updated her contact information.', now()),
(1, 'viewed payslip', 'ross viewed his may 2025 payslip.', now());
