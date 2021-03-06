create table endereco (idendereco bigint not null auto_increment, bairro varchar(255), cep integer not null, idusuario bigint, numero integer not null, rua varchar(255), primary key (idendereco))
create table entrega (identrega bigint not null auto_increment, primary key (identrega))
create table pedido (idproduto bigint not null, idusuario bigint not null, idpedido bigint, primary key (idproduto, idusuario))
create table produto (idproduto bigint not null auto_increment, descricao varchar(255), nome varchar(255), quantidade integer not null, primary key (idproduto))
create table usuario (idusuario bigint not null auto_increment, cpf bigint, email varchar(255), idade integer not null, nome varchar(255), senha varchar(255), sexo varchar(255), primary key (idusuario))
alter table endereco add constraint FKm6bppibrtixh02clk8pp6d60f foreign key (idusuario) references usuario (idusuario)
