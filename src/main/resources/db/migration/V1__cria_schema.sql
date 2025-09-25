-- Schema base
create table if not exists papel (
    id bigint generated always as identity primary key,
    nome varchar(64) not null unique
);

create table if not exists usuario (
    id bigint generated always as identity primary key,
    username varchar(100) not null unique,
    password varchar(255) not null,
    enabled boolean not null
);

create table if not exists usuario_papel (
    usuario_id bigint not null,
    papel_id bigint not null,
    primary key (usuario_id, papel_id),
    constraint fk_usuario_papel_usuario foreign key (usuario_id) references usuario(id),
    constraint fk_usuario_papel_papel foreign key (papel_id) references papel(id)
);

create table if not exists moto (
    id bigint generated always as identity primary key,
    placa varchar(20) not null,
    modelo varchar(100) not null,
    status varchar(32),
    ligada boolean not null,
    data_cadastro timestamp
);

create table if not exists vaga (
    id bigint generated always as identity primary key,
    ocupada boolean not null,
    moto_id bigint,
    constraint fk_vaga_moto foreign key (moto_id) references moto(id)
);

create table if not exists movimentacao (
    id bigint generated always as identity primary key,
    moto_id bigint not null,
    vaga_id bigint,
    tipo varchar(16) not null,
    data_hora timestamp not null,
    usuario_id bigint,
    constraint fk_mov_moto foreign key (moto_id) references moto(id),
    constraint fk_mov_vaga foreign key (vaga_id) references vaga(id),
    constraint fk_mov_usuario foreign key (usuario_id) references usuario(id)
);

create table if not exists localizacao (
    id bigint generated always as identity primary key,
    coordenada_x real not null,
    coordenada_y real not null,
    data_hora timestamp,
    moto_id bigint,
    constraint fk_loc_moto foreign key (moto_id) references moto(id)
); 