-- Papeis
insert into papel (nome) values ('ROLE_ADMIN');
insert into papel (nome) values ('ROLE_OPERADOR');

-- Senha bcrypt para 123456 (BCrypt $2a$10$hJb...)
insert into usuario (username, password, enabled) values ('admin', '$2a$10$w3i6p9zF1Xq7sQx1mJkQzOAI3H3Jm0yH7xj9yX4o8j6J5m0Qm3YdS', true);
insert into usuario (username, password, enabled) values ('oper', '$2a$10$w3i6p9zF1Xq7sQx1mJkQzOAI3H3Jm0yH7xj9yX4o8j6J5m0Qm3YdS', true);

-- Vínculos
insert into usuario_papel (usuario_id, papel_id)
select u.id, p.id from usuario u join papel p on p.nome='ROLE_ADMIN' where u.username='admin';
insert into usuario_papel (usuario_id, papel_id)
select u.id, p.id from usuario u join papel p on p.nome='ROLE_OPERADOR' where u.username='oper'; 