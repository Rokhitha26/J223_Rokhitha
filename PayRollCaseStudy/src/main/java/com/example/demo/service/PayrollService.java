package com.example.demo.service;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Payroll;
import com.example.demo.model.employeeProfile;
import com.example.demo.repo.AdminRepo;
import com.example.demo.repo.PayrollRepo;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepo payrollRepo;

    @Autowired
    private AdminRepo adminRepo;

    public String addPayroll(int empId, Date payrollDate, double amount) {
        employeeProfile emp = adminRepo.findById(empId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Payroll payroll = new Payroll();
        payroll.setEmployee(emp);
        payroll.setPayrollDate(payrollDate);
        payroll.setAmount(amount);
        payrollRepo.save(payroll);

        return "Payroll added successfully";
    }

    public List<Payroll> getPayrollHistoryByEmpId(int empId) {
        employeeProfile emp = adminRepo.findById(empId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return payrollRepo.findByEmployee(emp);
    }

    public List<Payroll> searchPayrollByDate(Date date) {
        List<Payroll> list = payrollRepo.findByPayrollDate(date);
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No payroll records found for date: " + date);
        }
        return list;
    }
}
