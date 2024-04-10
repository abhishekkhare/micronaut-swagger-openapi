package example.abhi.repository;

import example.abhi.model.Employee;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.annotation.Repository;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Repository
public class EmployeeRepository implements CrudRepository<Employee, Long> {

  Map<Long, Employee> store = new HashMap<Long, Employee>();

  @Override
  public <S extends Employee> S save(@Valid @NotNull S employee) {
    store.put(employee.getId(), employee);
    return employee;
  }

  @Override
  public <S extends Employee> S update(@Valid @NotNull S employee) {
    store.put(employee.getId(), employee);
    return employee;
  }

  @Override
  public @NonNull <S extends Employee> List<S> updateAll(@Valid @NotNull Iterable<S> employees) {
    employees.forEach(s -> {store.put(s.getId(), s);});
    return StreamSupport.stream(employees.spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public @NonNull <S extends Employee> List<S> saveAll(@Valid @NotNull Iterable<S> persons) {
    persons.forEach(s -> {store.put(s.getId(), s);});
    return StreamSupport.stream(persons.spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Employee> findById(@NotNull Long aLong) {
    return Optional.ofNullable(store.get(aLong));
  }

  public Employee findEmployeeById(@NotNull Long aLong) {
    return store.get(aLong);
  }

  @Override
  public boolean existsById(@NotNull Long aLong) {
    return store.containsKey(aLong);
  }

  @Override
  public @NonNull List<Employee> findAll() {
    return store.values().stream().toList();
  }

  @Override
  public long count() {
    return store.size();
  }

  @Override
  public void deleteById(@NotNull Long aLong) {
    store.remove(aLong);
  }

  @Override
  public void delete(@NotNull Employee person) {
    deleteById(person.getId());

  }

  @Override
  public void deleteAll(@NotNull Iterable<? extends Employee> entities) {

  }

  @Override
  public void deleteAll() {

  }
}

