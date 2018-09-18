-- CRIANDO O GRUPO SISTEMA                        
INSERT INTO grupo VALUES(1, 'SISTEMA');

-- CRIAÇÃO DE USUARIO DO SISTEMA
INSERT INTO login (nome,data_nascimento, email, senha, ativo) VALUES ('Admin','2108-03-03', 'admin@pirelli.com', '$2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG', 1);

-- RELACIONANDO USUÁRIO E GRUPO NA TABELA 8
INSERT INTO login_grupo values(1,1);

-- CRIANDO AS PERMISSÕES DE CADASTRO DO SISTEMA
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
INSERT INTO permissao(nome) values('ROLE_CADASTRAR_OSCOMPUTADOR');
INSERT INTO permissao(nome) values('ROLE_CADASTRAR_OSIMPRESSORA');
INSERT INTO permissao(nome) values('ROLE_CADASTRAR_MONITOR');

-- CRIANDO AS PERMISSÕES DE PESQUISA DO SISTEMA

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
INSERT INTO permissao(nome) values('ROLE_PESQUISAR_OSCOMPUTADOR');
INSERT INTO permissao(nome) values('ROLE_PESQUISAR_OSIMPRESSORA');
INSERT INTO permissao(nome) values('ROLE_PESQUISAR_MONITOR');

-- CRIANDO AS PERMISSÕES DE EDIÇÃO DO SISTEMA

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
INSERT INTO permissao(nome) values('ROLE_EDITAR_OSCOMPUTADOR');
INSERT INTO permissao(nome) values('ROLE_EDITAR_OSIMPRESSORA');
INSERT INTO permissao(nome) values('ROLE_EDITAR_MONITOR');

-- CRIANDO AS PERMISSÕES DE MENU, SISTEMA E ADMINISTRADOR DO SISTEMA
INSERT INTO permissao(nome) values('ROLE_MENU_CADASTRAR');
INSERT INTO permissao(nome) values('ROLE_MENU_BUSCAR');
INSERT INTO permissao(nome) values('ROLE_MENU_CONFIGURACOES');
INSERT INTO permissao(nome) values('ROLE_MENU_MANUTENCAO');
INSERT INTO permissao(nome) values('ROLE_MENU_RELATORIO');
INSERT INTO permissao(nome) values('ROLE_SISTEMA');
INSERT INTO permissao(nome) values('ROLE_ADMINISTRADOR');

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
INSERT INTO grupo_permissao values(1,29);
INSERT INTO grupo_permissao values(1,30);
INSERT INTO grupo_permissao values(1,31);
INSERT INTO grupo_permissao values(1,32);
INSERT INTO grupo_permissao values(1,33);
INSERT INTO grupo_permissao values(1,34);
INSERT INTO grupo_permissao values(1,35);
INSERT INTO grupo_permissao values(1,36);
INSERT INTO grupo_permissao values(1,37);
INSERT INTO grupo_permissao values(1,38);
INSERT INTO grupo_permissao values(1,39);
INSERT INTO grupo_permissao values(1,40);
INSERT INTO grupo_permissao values(1,41);
INSERT INTO grupo_permissao values(1,42);
INSERT INTO grupo_permissao values(1,43);
INSERT INTO grupo_permissao values(1,44);
INSERT INTO grupo_permissao values(1,45);
INSERT INTO grupo_permissao values(1,46);
INSERT INTO grupo_permissao values(1,47);
INSERT INTO grupo_permissao values(1,48);
INSERT INTO grupo_permissao values(1,49);
INSERT INTO grupo_permissao values(1,50);
INSERT INTO grupo_permissao values(1,51);
INSERT INTO grupo_permissao values(1,52);
INSERT INTO grupo_permissao values(1,53);
INSERT INTO grupo_permissao values(1,54);
INSERT INTO grupo_permissao values(1,55);


select * from grupo_permissao;

SELECT * FROM PERMISSAO;













select * FROM login_grupo;

INSERT INTO login_grupo values(1,1)