package example.abhi.controller;

import example.abhi.model.Message;
import example.abhi.model.Person;
import example.abhi.repository.PersonRepository;
import javax.inject.Inject;
import javax.validation.Valid;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;


@Validated
@Controller("/persons")
public class PersonController {
  @Inject
  private PersonRepository personRepository;

  @Post("/person")
  public HttpResponse<?> savePerson(@Body @Valid Person person) {
    this.personRepository.save(person);
    return HttpResponse.status(HttpStatus.CREATED).body(new Message(HttpStatus.CREATED.getCode(),"Saved successfully !"));
  }

  @Post("/persons")
  public HttpResponse<?> savePersons(@Body @Valid Iterable<Person> persons) {
    this.personRepository.saveAll(persons);
    return HttpResponse.status(HttpStatus.CREATED).body(new Message(HttpStatus.CREATED.getCode(),"Saved successfully !"));
  }

  @Get("/persons")
  public HttpResponse<?> getPersons() {
    return HttpResponse.status(HttpStatus.OK).body(this.personRepository.findAll());
  }
  @Get("/{id}")
  public HttpResponse<?> getPerson(long id) {
    return HttpResponse.status(HttpStatus.OK).body(this.personRepository.findPersonById(id));
  }
}

