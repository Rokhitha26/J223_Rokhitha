package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Payroll;
import com.example.demo.service.PayrollService;

@RestController
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @PostMapping(value = "/addPayroll")
    public String addPayroll(
            @RequestParam int empId,
            @RequestParam Date payrollDate,
            @RequestParam double amount) {
        return payrollService.addPayroll(empId, payrollDate, amount);
    }

    @GetMapping(value = "/getPayrollHistoryByEmpId/{empId}")
    public List<Payroll> getPayrollHistoryByEmpId(@PathVariable int empId) {
        return payrollService.getPayrollHistoryByEmpId(empId);
    }

    @GetMapping(value = "/searchPayrollByDate")
    public List<Payroll> searchPayrollByDate(@RequestParam Date date) {
        return payrollService.searchPayrollByDate(date);
    }
}
