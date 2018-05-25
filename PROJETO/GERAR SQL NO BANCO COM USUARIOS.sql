INSERT INTO login (nome,data_nascimento, email, senha, ativo) VALUES ('Admin','2108-03-03', 'admin@pirelli.com', '$2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG', 1);
INSERT INTO grupo VALUES(1, 'ADMINISTRADOR');
select * from login_grupo;
INSERT INTO login_grupo values(1,1);
INSERT INTO grupo_permissao values(1,1);
INSERT INTO grupo_permissao values(1,2);
INSERT INTO grupo_permissao values(1,3);
INSERT INTO grupo_permissao values(1,4);
INSERT INTO grupo_permissao values(1,5);
INSERT INTO grupo_permissao values(1,6);
INSERT INTO grupo_permissao values(1,7);
INSERT INTO grupo_permissao values(1,8);
INSERT INTO grupo_permissao values(1,9);
INSERT INTO grupo_permissao values(1,10);
INSERT INTO grupo_permissao values(1,11);
INSERT INTO grupo_permissao values(1,12);
INSERT INTO grupo_permissao values(1,13);
INSERT INTO grupo_permissao values(1,14);
INSERT INTO grupo_permissao values(1,15);
INSERT INTO grupo_permissao values(1,16);
INSERT INTO grupo_permissao values(1,17);
INSERT INTO grupo_permissao values(1,18);
INSERT INTO grupo_permissao values(1,19);
INSERT INTO grupo_permissao values(1,20);
INSERT INTO grupo_permissao values(1,21);
INSERT INTO grupo_permissao values(1,22);
INSERT INTO grupo_permissao values(1,23);
INSERT INTO grupo_permissao values(1,24);
INSERT INTO grupo_permissao values(1,25);
INSERT INTO grupo_permissao values(1,26);
INSERT INTO grupo_permissao values(1,27);
INSERT INTO grupo_permissao values(1,28);
select * from grupo_permissao

SELECT * FROM PERMISSAO

INSERT INTO permissao(nome) values('ROLE_CADASTRAR_COMPUTADOR');
INSERT INTO permissao(nome) values('ROLE_CADASTRAR_FILIAL');
INSERT INTO permissao(nome) values('ROLE_CADASTRAR_GRUPO');
INSERT INTO permissao(nome) values('ROLE_CADASTRAR_IMPRESSORA');
INSERT INTO permissao(nome) values('ROLE_CADASTRAR_LOGIN');
INSERT INTO permissao(nome) values('ROLE_CADASTRAR_MAQUINA');
INSERT INTO permissao(nome) values('ROLE_CADASTRAR_MARCA');
INSERT INTO permissao(nome) values('ROLE_CADASTRAR_MODELO');
INSERT INTO permissao(nome) values('ROLE_CADASTRAR_PERMISSAO');
INSERT INTO permissao(nome) values('ROLE_CADASTRAR_PROGRAMA');
INSERT INTO permissao(nome) values('ROLE_CADASTRAR_SETOR');
INSERT INTO permissao(nome) values('ROLE_CADASTRAR_TIPO_MODELO');
INSERT INTO permissao(nome) values('ROLE_CADASTRAR_TONER');
INSERT INTO permissao(nome) values('ROLE_CADASTRAR_USUARIO');

-- PESQUISA

INSERT INTO permissao(nome) values('ROLE_PESQUISAR_COMPUTADOR');
INSERT INTO permissao(nome) values('ROLE_PESQUISAR_FILIAL');
INSERT INTO permissao(nome) values('ROLE_PESQUISAR_GRUPO');
INSERT INTO permissao(nome) values('ROLE_PESQUISAR_IMPRESSORA');
INSERT INTO permissao(nome) values('ROLE_PESQUISAR_LOGIN');
INSERT INTO permissao(nome) values('ROLE_PESQUISAR_MAQUINA');
INSERT INTO permissao(nome) values('ROLE_PESQUISAR_MARCA');
INSERT INTO permissao(nome) values('ROLE_PESQUISAR_MODELO');
INSERT INTO permissao(nome) values('ROLE_PESQUISAR_PERMISSAO');
INSERT INTO permissao(nome) values('ROLE_PESQUISAR_PROGRAMA');
INSERT INTO permissao(nome) values('ROLE_PESQUISAR_SETOR');
INSERT INTO permissao(nome) values('ROLE_PESQUISAR_TIPO_MODELO');
INSERT INTO permissao(nome) values('ROLE_PESQUISAR_TONER');
INSERT INTO permissao(nome) values('ROLE_PESQUISAR_USUARIO');

INSERT INTO permissao(nome) values('ROLE_EDITAR_COMPUTADOR');
INSERT INTO permissao(nome) values('ROLE_EDITAR_FILIAL');
INSERT INTO permissao(nome) values('ROLE_EDITAR_GRUPO');
INSERT INTO permissao(nome) values('ROLE_EDITAR_IMPRESSORA');
INSERT INTO permissao(nome) values('ROLE_EDITAR_LOGIN');
INSERT INTO permissao(nome) values('ROLE_EDITAR_MAQUINA');
INSERT INTO permissao(nome) values('ROLE_EDITAR_MARCA');
INSERT INTO permissao(nome) values('ROLE_EDITAR_MODELO');
INSERT INTO permissao(nome) values('ROLE_EDITAR_PERMISSAO');
INSERT INTO permissao(nome) values('ROLE_EDITAR_PROGRAMA');
INSERT INTO permissao(nome) values('ROLE_EDITAR_SETOR');
INSERT INTO permissao(nome) values('ROLE_EDITAR_TIPO_MODELO');
INSERT INTO permissao(nome) values('ROLE_EDITAR_TONER');
INSERT INTO permissao(nome) values('ROLE_EDITAR_USUARIO');

INSERT INTO permissao(nome) values('ROLE_MENU_CADASTRAR');
INSERT INTO permissao(nome) values('ROLE_MENU_BUSCAR');
INSERT INTO permissao(nome) values('ROLE_MENU_CONFIGURACOES');




select * FROM login_grupo;

INSERT INTO login_grupo values(1,1)