package br.xksoberbado.mongodbshardexample.repository;

import br.xksoberbado.mongodbshardexample.model.ShardedPerson;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ShardedPersonRepository extends MongoRepository<ShardedPerson, UUID> {

    Long countByNameStartingWith(String name);
}
