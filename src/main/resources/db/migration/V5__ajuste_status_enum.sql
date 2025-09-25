-- Corrige valor antigo do enum para o literal correto existente em StatusMoto
update moto set status = 'EM_MANUTENCAO' where status = 'MANUTENCAO'; 