package br.xksoberbado.mongodbshardexample.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Document("persons")
public class Person {

    @Id
    @Field(targetType = FieldType.STRING)
    private UUID id;

    private String name;

    private LocalDate birthdate;
}
