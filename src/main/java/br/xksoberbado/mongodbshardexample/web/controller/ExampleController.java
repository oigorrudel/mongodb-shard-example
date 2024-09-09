package br.xksoberbado.mongodbshardexample.web.controller;

import br.xksoberbado.mongodbshardexample.repository.PersonRepository;
import br.xksoberbado.mongodbshardexample.repository.ShardedPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
public class ExampleController {

    private final PersonRepository personRepository;
    private final ShardedPersonRepository shardedPersonRepository;

    @GetMapping("persons")
    public Long get(final String name) {
        return personRepository.countByNameStartingWith(name);
    }

    @GetMapping("sharded-persons")
    public Long get2(final String name) {
        return shardedPersonRepository.countByNameStartingWith(name);
    }
}
