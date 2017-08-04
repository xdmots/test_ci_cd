CREATE INDEX `id_idx` on `default`(meta().id)

SELECT meta().id FROM `default`

SELECT meta().id, default_  FROM `default` default_  LIMIT 1;

CREATE PRIMARY INDEX `default-primary` ON `default` USING GSI 

SELECT * FROM `default` where meta().id  LIMIT 100;

SELECT META(default).id as _ID, META(default).cas as _CAS ,content , lable , title , userId 
FROM `default` where meta().id LIMIT 10;

