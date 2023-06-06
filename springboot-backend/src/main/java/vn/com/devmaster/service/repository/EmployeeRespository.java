package vn.com.devmaster.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.devmaster.service.model.Employee;

@Repository
public interface EmployeeRespository extends JpaRepository<Employee, Long> {

}
