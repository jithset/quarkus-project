# multiple-datasource Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## point to remember

* generation of database is in the last

```properties
quarkus.hibernate-orm.packages=io.github.jithset.domains.server1

quarkus.datasource."server2".db-kind = postgresql
quarkus.datasource."server2".username = partner_user
quarkus.datasource."server2".password = partner_pass
quarkus.datasource."server2".jdbc.url = jdbc:postgresql://localhost:5433/partner_db


# order is important
quarkus.hibernate-orm.database.generation=drop-and-create
quarkus.hibernate-orm.log.sql=true
```
* reactive and jdbc cannot be in same project 