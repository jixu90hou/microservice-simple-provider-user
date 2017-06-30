DROP TABLE person IF EXISTS;
CREATE TABLE person (
  id bigint generated by default AS identity,
  username varchar(30),
  name varchar(20),
  age int(3),
  balance decimal(10,2),
  primary key(id)
)