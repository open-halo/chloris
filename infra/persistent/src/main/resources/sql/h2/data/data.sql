INSERT INTO `endpoint` (id, http_method, http_url, sql_template) VALUES (1, 'GET', '/api/data_v1', 'select * from `author` where `id` = #{id}');
INSERT INTO `endpoint` (id, http_method, http_url, sql_template) VALUES (2, 'GET', '/api/data_v2', 'select email from `author` where `name` = #{name}');


INSERT INTO `author` (id, gender, name, email) VALUES (101, 'F', 'Joshua Bloch', 'joshua.bloch@example.com');
INSERT INTO `author` (id, gender, name, email) VALUES (102, 'F', 'Scott Meyers', 'scott.meyers@example.com');



