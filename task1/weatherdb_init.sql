create user zemuvier with password 'test';
grant all privileges on database weather to zemuvier;
grant all privileges on database postgres to zemuvier;
alter user zemuvier CREATEDB;
