package com.example.demo.repo;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Payroll;
import com.example.demo.model.employeeProfile;

import jakarta.transaction.Transactional;

@Repository
public interface PayrollRepo extends JpaRepository<Payroll, Integer> {

    List<Payroll> findByEmployee(employeeProfile employee);

    List<Payroll> findByPayrollDate(Date date);

    @Query("DELETE FROM Payroll p WHERE p.employee.id = :empId")
    void deleteByEmployeeId(int empId);
}
