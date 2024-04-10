package example.abhi.repository;

import example.abhi.model.Person;
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
public class PersonRepository implements CrudRepository<Person, Long> {

  Map<Long, Person> store = new HashMap<Long, Person>();

  @Override
  public <S extends Person> S save(@Valid @NotNull S person) {
    store.put(person.getId(), person);
    return person;
  }

  @Override
  public <S extends Person> S update(@Valid @NotNull S person) {
    store.put(person.getId(), person);
    return person;
  }

  @Override
  public @NonNull <S extends Person> List<S> updateAll(@Valid @NotNull Iterable<S> persons) {
    persons.forEach(s -> {store.put(s.getId(), s);});
    return StreamSupport.stream(persons.spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public @NonNull <S extends Person> List<S> saveAll(@Valid @NotNull Iterable<S> persons) {
    persons.forEach(s -> {store.put(s.getId(), s);});
    return StreamSupport.stream(persons.spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Person> findById(@NotNull Long aLong) {
    return Optional.ofNullable(store.get(aLong));
  }

  public Person findPersonById(@NotNull Long aLong) {
    return store.get(aLong);
  }

  @Override
  public boolean existsById(@NotNull Long aLong) {
    return store.containsKey(aLong);
  }

  @Override
  public @NonNull List<Person> findAll() {
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
  public void delete(@NotNull Person person) {
    deleteById(person.getId());

  }

  @Override
  public void deleteAll(@NotNull Iterable<? extends Person> entities) {

  }

  @Override
  public void deleteAll() {

  }
}

