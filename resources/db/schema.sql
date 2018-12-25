drop table if exists blogger;
drop table if exists blog;

create table blogger (
	id int(11) not null auto_increment,
	username varchar(20) unique not null,
	password varchar(20) not null,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	email varchar(30) not null,
    primary key(id)
);

create table blog (
    id int(11) not null auto_increment,
    message varchar(140) not null,
	created_at timestamp not null,
    primary key(id),
);
