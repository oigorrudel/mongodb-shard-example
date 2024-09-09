package br.xksoberbado.mongodbshardexample.runner;

import br.xksoberbado.mongodbshardexample.model.Person;
import br.xksoberbado.mongodbshardexample.model.ShardedPerson;
import br.xksoberbado.mongodbshardexample.repository.PersonRepository;
import br.xksoberbado.mongodbshardexample.repository.ShardedPersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExampleRunner {

    private static final int MAX = 100_000;
    private static final String[] NAMES = new String[]{
        "João", "Maria", "Pedro", "Mateus", "Henrique", "Igor", "Nicoli", "Patricia", "Joana", "Joaquina"
    };

    private static EasyRandom EASY_RANDOM;

    static {
        final var parameters = new EasyRandomParameters();
        parameters.randomize(String.class, () -> pickName());

        EASY_RANDOM = new EasyRandom(parameters);
    }

    private final MongoTemplate template;
    private final PersonRepository personRepository;
    private final ShardedPersonRepository shardedPersonRepository;
    private final AsyncService asyncService;

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            if (personRepository.count() > 0) {
                return;
            }

            setupDB();

            IntStream.rangeClosed(1, 10)
                .forEach(n -> {
                    log.info("Creating {}", n);
                    asyncService.run(this::createPersons);
                    asyncService.run(this::createShardedPersons);
                });
        };
    }

    private void setupDB() {
        final var adminDB = template.getMongoDatabaseFactory().getMongoDatabase("admin");

        adminDB.runCommand(new Document("enableSharding", "shard-example"));

        final var shardCmd = new Document("shardCollection", "shard-example.shardedPersons")
            .append("key", new Document("name", 1));

        adminDB.runCommand(shardCmd);
    }

    private void createPersons() {
        final var persons = EASY_RANDOM.objects(Person.class, MAX).toList();

        personRepository.insert(persons);

        log.info("Created 100k persons.");
    }

    private void createShardedPersons() {
        final var shardedPersons = EASY_RANDOM.objects(ShardedPerson.class, MAX).toList();

        shardedPersonRepository.insert(shardedPersons);

        log.info("Created 100k shardedPersons.");
    }

    private static String pickName() {
        return NAMES[EASY_RANDOM.nextInt(0, NAMES.length)];
    }
}
