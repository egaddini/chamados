INSERT INTO user (active, creation_dt, email, first_name, last_name, password, phone, role, timestamp)
VALUES
(1, '2023-09-09 18:12:20.685729', 'teste@gmail.com', 'João', 'Silva', '$2a$10$7Dd9Jg/zNDuM1KqKGkR.ZOaghKvujUk2RVbj3QBIXe6CiS6J3yEk.', '1111111111', 'ADMIN', '0'),
(1, '2023-09-09 18:15:30.685729', 'usuario2@gmail.com', 'Maria', 'Santos', '$2a$10$7Dd9Jg/zNDuM1KqKGkR.ZOaghKvujUk2RVbj3QBIXe6CiS6J3yEk.', '2222222222', 'SOLVER', '0'),
(1, '2023-09-09 18:18:40.685729', 'usuario3@gmail.com', 'Pedro', 'Ferreira', '$2a$10$7Dd9Jg/zNDuM1KqKGkR.ZOaghKvujUk2RVbj3QBIXe6CiS6J3yEk.', '3333333333', 'USER', '0'),
(1, '2023-09-09 18:20:50.685729', 'teste2@gmail.com', 'Mariana', 'Oliveira', '$2a$10$7Dd9Jg/zNDuM1KqKGkR.ZOaghKvujUk2RVbj3QBIXe6CiS6J3yEk.', '4444444444', 'ADMIN', '0'),
(1, '2023-09-09 18:23:00.685729', 'teste3@gmail.com', 'Rafael', 'Ramos', '$2a$10$7Dd9Jg/zNDuM1KqKGkR.ZOaghKvujUk2RVbj3QBIXe6CiS6J3yEk.', '5555555555', 'SOLVER', '0'),
(1, '2023-09-09 18:25:10.685729', 'usuario6@gmail.com', 'Lúcia', 'Sousa', '$2a$10$7Dd9Jg/zNDuM1KqKGkR.ZOaghKvujUk2RVbj3QBIXe6CiS6J3yEk.', '6666666666', 'USER', '0'),
(1, '2023-09-09 18:27:20.685729', 'usuario7@gmail.com', 'Gustavo', 'Alves', '$2a$10$7Dd9Jg/zNDuM1KqKGkR.ZOaghKvujUk2RVbj3QBIXe6CiS6J3yEk.', '7777777777', 'SOLVER', '0'),
(1, '2023-09-09 18:29:30.685729', 'usuario8@gmail.com', 'Isabela', 'Machado', '$2a$10$7Dd9Jg/zNDuM1KqKGkR.ZOaghKvujUk2RVbj3QBIXe6CiS6J3yEk.', '8888888888', 'USER', '0'),
(1, '2023-09-09 18:31:40.685729', 'usuario9@gmail.com', 'Diego', 'Carvalho', '$2a$10$7Dd9Jg/zNDuM1KqKGkR.ZOaghKvujUk2RVbj3QBIXe6CiS6J3yEk.', '9999999999', 'SOLVER', '0'),
(1, '2023-09-09 18:33:50.685729', 'usuario10@gmail.com', 'Camila', 'Pereira', '$2a$10$7Dd9Jg/zNDuM1KqKGkR.ZOaghKvujUk2RVbj3QBIXe6CiS6J3yEk.', '1010101010', 'USER', '0');

INSERT INTO call_sector(acronym, name)
VALUES
    ('TEC', 'Tecnologia'),
    ('RH', 'Recursos Humanos'),
    ('FIN', 'Financeiro'),
    ('SUP', 'Suporte'),
    ('VND', 'Vendas'),
    ('ADM', 'Administração'),
    ('MKT', 'Marketing'),
    ('LOG', 'Logística'),
    ('PRO', 'Produção'),
    ('QC', 'Controle de Qualidade'),
    ('CLI', 'Atendimento ao Cliente'),
    ('PRJ', 'Projetos'),
    ('COM', 'Compras'),
    ('ENG', 'Engenharia'),
    ('EDU', 'Educação'),
    ('SAU', 'Saúde'),
    ('ART', 'Arte'),
    ('ESP', 'Esportes'),
    ('ALI', 'Alimentos'),
    ('TRA', 'Transporte'),
    ('EST', 'Estratégia'),
    ('MED', 'Médico'),
    ('JUR', 'Jurídico'),
    ('SER', 'Serviços'),
    ('TEA', 'Ensino Técnico'),
    ('REL', 'Relações Públicas'),
    ('DES', 'Design'),
    ('TEL', 'Telecomunicações'),
    ('AGR', 'Agricultura'),
    ('GOV', 'Governo');

INSERT INTO CALL_STATUS (description, name, notify, weight)
VALUES
    ('Aberto', 'Aberto', 0, 1),
    ('Em Triagem', 'Triagem', 0, 2),
    ('Em Andamento', 'Andamento', 0, 3),
    ('Aguardando Informações', 'Aguardando Info', 0, 4),
    ('Aguardando Aprovação', 'Aguardando Aprovação', 0, 5),
    ('Em Espera', 'Em Espera', 0, 6),
    ('Pendente', 'Pendente', 0, 7),
    ('Resolvido com Falha', 'Falha', 0, 8),
    ('Cancelado', 'Cancelado', 0, 9),
    ('Finalizado', 'Finalizado', 0, 10);

INSERT INTO call_priority (name, weight)
VALUES
    ('Muito Baixa', 1),
    ('Baixa', 2),
    ('Média Baixa', 3),
    ('Média', 4),
    ('Média Alta', 5),
    ('Alta', 6),
    ('Muito Alta', 7),
    ('Crítica', 8),
    ('Urgente', 9),
    ('Emergência', 10);

INSERT INTO Call_category (description, title, priority_id, sector_id)
VALUES
    ('Problemas técnicos relacionados ao software da empresa', 'Problemas de Software', 4, 1),
    ('Solicitação de férias ou licenças', 'Solicitação de Férias', 2, 2),
    ('Solicitação de reembolso de despesas', 'Solicitação de Reembolso', 3, 3),
    ('Problemas técnicos relacionados ao hardware da empresa', 'Problemas de Hardware', 4, 4),
    ('Perguntas sobre produtos ou serviços da empresa', 'Dúvidas de Vendas', 5, 5),
    ('Solicitação de documentos ou informações administrativas', 'Solicitação de Documentos', 6, 6),
    ('Solicitações relacionadas a campanhas publicitárias e promoções', 'Solicitação de Marketing', 7, 7),
    ('Problemas relacionados à entrega e logística de produtos', 'Problemas de Logística', 8, 8),
    ('Problemas ou solicitações relacionadas à linha de produção', 'Problemas de Produção', 9, 9),
    ('Inspeção de qualidade de produtos ou serviços', 'Inspeção de Qualidade', 10, 10);