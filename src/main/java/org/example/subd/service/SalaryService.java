package org.example.subd.service;


import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.example.subd.model.Budget;
import org.example.subd.model.Employee;
import org.example.subd.model.Salary;
import org.example.subd.model.dto.salary.SalaryDTO;
import org.example.subd.model.dto.salary.SalaryUpdateDTO;
import org.example.subd.model.mapper.SalaryMapper;
import org.example.subd.repository.*;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalaryService {
    private final SalaryRepo salaryRepo;
    private final SalaryMapper salaryMapper;
    private final BudgetRepo budgetRepo;
    private final EmployeeRepo employeeRepo;
    private final ProductionRepo productionRepo;
    private final SaleRepo saleRepo;
    private final PurchaseRepo purchaseRepo;


    public void calculateAllSalary(int year, int month) {

        //Считываем какой месяц/год
        LocalDate fromDate = LocalDate.of(year, month, 1);
        LocalDate toDate = fromDate.withDayOfMonth(fromDate.lengthOfMonth());

        if(!salaryRepo.findAllBySalaryDateBetween(fromDate, toDate)){
            return;
        }

        Budget budget = budgetRepo.findAll().stream()
                .findFirst().orElseThrow(()-> new RuntimeException("Бюджет не найден"));

        BigDecimal bonusPercent = budget.getBonus();
        List<Employee> employeeList = employeeRepo.findAll();

        for(Employee employee: employeeList) {
            //Скипнуть если зп уже рассчитана на сегодня
            /*boolean alreadyCalculated = salaryRepo.existsByEmployeeAndSalaryDate(employee, LocalDate.now());
            if(alreadyCalculated) continue;*/

            //Найти дату последней выплаты ЗП
            Optional<Salary> lastSalaryOpt = salaryRepo
                    .findTopByEmployeeAndIsIssuedOrderBySalaryDateDesc(employee, true);

            LocalDate lastIssued = lastSalaryOpt
                    .map(Salary::getSalaryDate)
                    .orElse(LocalDate.of(2000, 1, 1));


            //Подсчет активности сотрудника
            int purchaseCount = purchaseRepo.countByEmployeeAndCreatedAtBetween(employee, lastIssued, toDate);
            int productionCount = productionRepo.countByEmployeeAndCreatedAtBetween(employee, lastIssued,toDate);
            int saleCount = saleRepo.countByEmployeeAndCreatedAtBetween(employee, lastIssued,toDate);


            int totalActivity = productionCount + saleCount + purchaseCount;

            //Расчет бонуса
            BigDecimal baseSalary = employee.getSalary();
            BigDecimal bonus = baseSalary
                    .multiply(bonusPercent)
                    .multiply(BigDecimal.valueOf(totalActivity))
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

            //Штраф, если сотрудник не проявлял активности
            BigDecimal fine = totalActivity == 0 ? BigDecimal.valueOf(500) : BigDecimal.ZERO;

            //Общая сумма к выплате
            BigDecimal total = baseSalary.add(bonus).subtract(fine);

            //Создание записи о зп
            Salary salary = new Salary();
            salary.setSalaryDate(fromDate);
            salary.setSalary(total);
            salary.setEmployee(employee);
            salary.setBonus(bonus);
            salary.setFine(fine);
            salary.setIsIssued(false); // пока не выдана
            salary.setPurchaseCount(purchaseCount);
            salary.setProductionCount(productionCount);
            salary.setSaceCount(saleCount);

            salaryRepo.save(salary);

        }
    }


    //Выплата всех невыданных зарплат
    public String issueCalculatedSalaries(){

        Budget budget = budgetRepo.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("Бюджет не найден"));
        List<Salary> toIssue = salaryRepo.findAllByIsIssuedFalse();

        for(Salary salary : toIssue){
            BigDecimal total = salary.getSalary();
            if(budget.getAmount().compareTo(total) < 0){
                return "Недостаточно средств";
            }

            //Списываем сумму из бюджета
            budget.setAmount(budget.getAmount().subtract(total));

            //Отмечаем что зп выдана
            salary.setIsIssued(true);
            salaryRepo.save(salary);
        }
        budgetRepo.save(budget);
        return "OK";
    }



 /*   public List<SalaryDTO> getPending(){
        List<Salary> salaries = salaryRepo.findAllByIsIssuedFalse();
        return salaryMapper.toDtoList(salaries);
    }*/


    public List<SalaryDTO> getAll(int year, int month){
//        LocalDate fromDate = LocalDate.of(year, month, 1);
//        LocalDate toDate = fromDate.withDayOfMonth(fromDate.lengthOfMonth());
//        List<Salary> salaries = salaryRepo.findAllBySalaryDateBetweenOrderBySalary(fromDate, toDate);
//        return salaryMapper.toDtoList(salaries);
        List<Salary> all = salaryRepo.findAll();
        return all.stream()
                .filter(s -> s.getSalaryDate().getMonthValue() == month)
                .filter(s -> s.getSalaryDate().getYear() == year)
                .map(salaryMapper::toDto)
                .collect(Collectors.toList());
    }


    public SalaryDTO getById(UUID id){
        SalaryDTO dto =
                salaryMapper.toDto(salaryRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Расчитанная зп не " +
                        "найдена")));
        return dto;
    }


    @Transactional
    public void update(SalaryUpdateDTO dto){
        Salary s = salaryRepo.findById(dto.getId()).orElseThrow(()-> new EntityNotFoundException("Не найден"));
        if(dto.getBonus() != null){
            s.setBonus(dto.getBonus());
        }

        if(dto.getFine() != null){
            s.setFine(dto.getFine());
        }

        salaryRepo.save(s);
    }
}
