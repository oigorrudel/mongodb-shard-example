package br.xksoberbado.mongodbshardexample.repository;

import br.xksoberbado.mongodbshardexample.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PersonRepository extends MongoRepository<Person, UUID> {

    Long countByNameStartingWith(String name);
}
