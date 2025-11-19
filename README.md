# mongodb-shard-example

Exemplo de aplicação para demonstrar **sharding** (fragmentação) em MongoDB usando Java e Spring, explicando como distribuir dados entre múltiplos shards.

---

## Visão Geral

Esse projeto ilustra como configurar e usar um cluster de MongoDB fragmentado (sharded cluster), de forma a:

- Distribuir dados de uma coleção entre vários shards.
- Escalar horizontalmente a base de dados.
- Demonstrar a escolha e uso de uma **shard key** (chave de fragmentação) para particionar dados de maneira eficiente.
- Realizar operações (CRUD) em coleções fragmentadas de forma transparente para a aplicação cliente.

---

## Tecnologias / Bibliotecas

- Java  
- Spring
- MongoDB

---

## Arquitetura do Cluster Sharded

Para usar sharding no MongoDB, seu cluster deve ter:

1. **Shards**: servidores ou replicaset que armazenam os pedaços (chunks) dos dados.  
2. **Config Servers**: armazenam os metadados do cluster sharded.  
3. **mongos**: roteador que a aplicação usa para se conectar ao cluster sharded.  

Esse tipo de arquitetura é descrito na documentação oficial do MongoDB.  

---

## Escolha da Chave de Shard (Shard Key)

- A shard key determina como os documentos serão distribuídos entre os shards.
- É importante escolher uma chave com **alta cardinalidade** para garantir uma distribuição uniforme entre shards.
- Se usar **Spring Data MongoDB**, existe a anotação `@Sharded` para definir a shard key no seu modelo de entidade.
