/*Usuário: dcc094
Senha: dcc094*/

drop table commits
drop table pessoa
drop table repositorio

create table repositorio(
    codigoRepositorio integer PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nome varchar (100),
    url varchar (100)
)

create table pessoa(
    codigoPessoa integer PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nome varchar (100),
    email varchar (100),
    fk_codigoRepositorio integer,
    foreign key (fk_codigoRepositorio) references repositorio (codigoRepositorio)
) 

create table commits(
    codigoCommits varchar (100) primary key,
    comentario varchar (10000),
    fk_codigoRepositorio integer,
    fk_codigoPessoa integer,
    foreign key (fk_codigoRepositorio) references repositorio (codigoRepositorio),
    foreign key (fk_codigoPessoa) references pessoa (codigoPessoa)
)
