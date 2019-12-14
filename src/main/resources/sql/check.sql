USE BEAUTY_SALOON;
/*
INSERT INTO `service` (`id_Specialization`, `service`, `cost`, `duration`) VALUES 
(1, 'evening', '30', '60'),
(1, 'daily', 25, 30),
(3, 'маникюр с долговременным покрытием без дизайна', 45, 90),
(3, 'маникюр', 30, 60);
INSERT INTO `master_service` (`id_Master`, `id_Service`) VALUES
(2, 2),
(2, 1), 
(3, 3),
(3, 2);
SELECT * FROM `beauty_master` INNER JOIN `person` ON person.id = beauty_master.id;
SELECT * FROM `master_service`;
SELECT `id`, `id_Specialization`, `service` FROM `service`, `master_service` WHERE `service`.`id` = `master_service`.`id_Service` AND `master_service`.`id_Master` = '3';

INSERT INTO `service` (`id_Specialization`, `service`, `cost`, `duration`) VALUES 
(1, 'smth', '30', '60');
SELECT @@IDENTITY;

ALTER TABLE service AUTO_INCREMENT=7;

SELECT `person`.`id`, `person`.`surname`, `person`.`name`, `person`.`email`, `person`.`passwordHash`, `person`.`phone`, `beauty_master`.`id_portfolio`, `beauty_master`.`experience`, `beauty_master`.`employment_date`, `beauty_master`.`status`
FROM `beauty_master` INNER JOIN `person` ON `beauty_master`.`id` = `person`.`id` ORDER BY `person`.`id`;

update `beauty_master`, `person` set `phone` = 222222222, `status` = 'working' where `person`.`id` = 2 AND `person`.`id` = `beauty_master`.`id`;
SELECT `person`.`id`, `person`.`surname`, `person`.`name`, `person`.`email`, `person`.`passwordHash`, `person`.`phone`, `beauty_master`.`id_portfolio`, `beauty_master`.`experience`, `beauty_master`.`employment_date`, `beauty_master`.`status`
FROM `beauty_master` INNER JOIN `person` ON `beauty_master`.`id` = `person`.`id` WHERE `person`.`id` = 2;
*/
SELECT * FROM `service`;SELECT `id`, `id_Specialization`, `service`, `cost`, `duration` FROM `service` ORDER BY `id`;
