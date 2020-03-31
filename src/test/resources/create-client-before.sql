delete from cashbox;
delete from client;

insert into client
(
    id,
    contract,
    mail,
    name,
    nonresident,
    vat
) values
    (1,'1H','some@some.some','ИП КакоеТоТам Т.Д.', true,'987654321')
    ,
    (2,'2Е','some@some.some','ИП ЕщеКакоеТо Т.Д.', true,'893746184')
    ,
    (3,'3К','some@some.some','ИП ИЕщеКакоеТо Т.Д.', true,'678954321')
    ,
    (4,'4Н','some@some.some','ИП НуИПоследнее Т.Д.', true,'123456789');

insert into cashbox
(
 id,
 address,
 date_create,
 date_enter,
 name_model,
 serial_number,
 skno,
 client_id,
 master_id
) values
(1,'Какойто адрес 1', null,null,'ОКА МК',null,true,1,null),
(1,'Какойто адрес 1', null,null,'ОКА МК',null,true,1,null),
(2,'Какойто адрес 2', null,null,'Титан',null,true,2,null),
(2,'Какойто адрес 2', null,null,'Титан',null,true,2,null),
(3,'Какойто адрес 3', null,null,'ВМ8119',null,true,3,null),
(3,'Какойто адрес 3', null,null,'ВМ8119',null,true,3,null),
(4,'Какойто адрес 4', null,null,'Миника',null,true,4,null),
(4,'Какойто адрес 4', null,null,'Миника',null,true,4,null),
(4,'Какойто адрес 4', null,null,'Миника',null,true,4,null);