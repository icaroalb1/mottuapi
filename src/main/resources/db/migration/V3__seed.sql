-- Motos e Vagas iniciais
insert into moto (placa, modelo, status, ligada, data_cadastro) values
 ('ABC1D23', 'Honda CG 160', 'DISPONIVEL', false, CURRENT_TIMESTAMP),
 ('EFG4H56', 'Yamaha Fazer 250', 'DISPONIVEL', false, CURRENT_TIMESTAMP),
 ('IJK7L89', 'Honda Biz 125', 'EM_MANUTENCAO', false, CURRENT_TIMESTAMP);

insert into vaga (ocupada, moto_id) values (false, null);
insert into vaga (ocupada, moto_id) values (false, null);
insert into vaga (ocupada, moto_id) values (false, null); 