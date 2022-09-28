INSERT INTO tb_sector (name) VALUES ('Games');
INSERT INTO tb_sector (name) VALUES ('Fitness');

INSERT INTO tb_role (name, sector_id) VALUES ('Técnico', 1);
INSERT INTO tb_role (name, sector_id) VALUES ('Vendedor', 1);

INSERT INTO tb_role (name, sector_id) VALUES ('Consultor', 2);
INSERT INTO tb_role (name, sector_id) VALUES ('Modelo', 2);

INSERT INTO tb_worker (name, cpf, sector_id, role_id) VALUES ('Inácio', 01205236585, 1, 1);
INSERT INTO tb_worker (name, cpf, sector_id, role_id) VALUES ('Rogério', 90090090090, 1, 2);
INSERT INTO tb_worker (name, cpf, sector_id, role_id) VALUES ('Roberta', 88080250050, 2, 3);
INSERT INTO tb_worker (name, cpf, sector_id, role_id) VALUES ('Maria', 60060060060, 2, 4);