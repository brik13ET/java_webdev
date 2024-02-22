CREATE TABLE IF NOT EXISTS Project(
	id      bigint PRIMARY KEY,
	name    varchar(65535) NOT NULL,
	"desc"    varchar(255),
	begin   date NOT NULL,
	"end"     date NOT NULL
);
CREATE TABLE IF NOT EXISTS  Task
(
	id          bigint,
	prjId       bigint NOT NULL REFERENCES Project(id),
	name        varchar(255) NOT NULL,
	description varchar(65535),
	"end"         date,
	isFinished  boolean NOT NULL,
	PRIMARY KEY ( id, prjId )
);

CREATE SEQUENCE IF NOT EXISTS Project_ID INCREMENT BY 1 OWNED BY Project.id;
CREATE SEQUENCE IF NOT EXISTS Task_ID INCREMENT BY 1 OWNED BY Task.id;