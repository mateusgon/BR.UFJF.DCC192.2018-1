/*Usuário: dcc094
Senha: dcc094*/

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
    comentario varchar (100),
    fk_codigoRepositorio integer,
    fk_codigoPessoa integer,
    foreign key (fk_codigoRepositorio) references repositorio (codigoRepositorio),
    foreign key (fk_codigoPessoa) references pessoa (codigoPessoa)
)

create table commits_modificacoes(
    codigoModificacao integer PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    diff varchar (10000),
    fk_codigoCommits varchar (100),
    foreign key (fk_codigoCommits) references commits (codigoCommits)
)