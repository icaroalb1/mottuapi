-- Unicidade de placa
create unique index if not exists ux_moto_placa on moto(placa);

-- Indices úteis
create index if not exists ix_mov_moto on movimentacao(moto_id);
create index if not exists ix_mov_vaga on movimentacao(vaga_id);
create index if not exists ix_loc_moto on localizacao(moto_id); 