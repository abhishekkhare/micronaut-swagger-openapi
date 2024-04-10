package example.abhi.controller;

import example.abhi.model.Employee;
import example.abhi.model.Message;
import example.abhi.repository.EmployeeRepository;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import javax.inject.Inject;
import javax.validation.Valid;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import java.util.List;

@Validated
@Controller
public class EmployeeController {
  @Inject
  private EmployeeRepository employeeRepository;
  @Get("/employees")
  List<Employee> all() {
    return employeeRepository.findAll();
  }
  @Post("/employee")
  public HttpResponse<?> newEmployee(@Body @Valid Employee employee) {
    this.employeeRepository.save(employee);
    return HttpResponse.status(HttpStatus.CREATED).body(new Message(HttpStatus.CREATED.getCode(),"Saved successfully !"));
  }

  @Get("/employee/{id}")
  public HttpResponse<?> getPerson(long id) {
    return HttpResponse.status(HttpStatus.OK).body(this.employeeRepository.findEmployeeById(id));
  }
@Put("/employees/{id}")
  Employee replaceEmployee(@Body @Valid Employee newEmployee,  Long id) {
    return employeeRepository.findById(id).map(employee -> {
          employee.setName(newEmployee.getName());
          employee.setRole(newEmployee.getRole());
          return employeeRepository.save(employee);
        })
        .orElseGet(() -> {
          newEmployee.setId(id);
          return employeeRepository.save(newEmployee);
        });
  }
  @Delete("/employees/{id}")
  void deleteEmployee(Long id) {
    employeeRepository.deleteById(id);
  }
}
