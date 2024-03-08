CREATE TABLE IF NOT EXISTS Project(
	id      SERIAL UNIQUE PRIMARY KEY,
	name    varchar(65535) NOT NULL,
	"desc"    varchar(255),
	begin   date NOT NULL,
	"end"     date NOT NULL
);
CREATE TABLE IF NOT EXISTS  Task
(
	id          SERIAL PRIMARY KEY ,
	prjId       integer NOT NULL REFERENCES project(id),
	name        varchar(255) NOT NULL,
	description varchar(65535),
	"end"         date,
	isFinished  boolean NOT NULL
);