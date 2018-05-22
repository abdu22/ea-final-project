-- Roles
INSERT INTO `ea_final_project`.`role` (`id`, `name`) VALUES ('1', 'ADMIN');
INSERT INTO `ea_final_project`.`role` (`id`, `name`) VALUES ('2', 'DEVELOPER');
INSERT INTO `ea_final_project`.`role` (`id`, `name`) VALUES ('3', 'PROJECT_MANAGER');

-- Users
INSERT INTO `ea_final_project`.`user` (`id`, `city`, `country`, `email`, `enabled`, `name`,`password`) VALUES ('1', 'Ho Chi Minh', 'Vietnam', 'hoainguyen@mum.edu', 1, 'Hoai Nguyen','$2a$10$.mxbiC0qCmeHuhrL9HSdGuuEUaKkaWCvoPqiCe/X9sUCOuTlFh5/6');
INSERT INTO `ea_final_project`.`user` (`id`, `city`, `country`, `email`, `enabled`, `name`,`password`) VALUES ('2', 'Fairfield', 'US', 'btasefa@mum.edu', 1, 'Beakal Asefa','$2a$10$.mxbiC0qCmeHuhrL9HSdGuuEUaKkaWCvoPqiCe/X9sUCOuTlFh5/6');
INSERT INTO `ea_final_project`.`user` (`id`, `city`, `country`, `email`, `enabled`, `name`,`password`) VALUES ('3', 'New York', 'US', 'test.dev@mum.edu', 1, 'Developer','$2a$10$.mxbiC0qCmeHuhrL9HSdGuuEUaKkaWCvoPqiCe/X9sUCOuTlFh5/6');

--User roles
INSERT INTO `ea_final_project`.`user_role` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `ea_final_project`.`user_role` (`user_id`, `role_id`) VALUES ('2', '3');
INSERT INTO `ea_final_project`.`user_role` (`user_id`, `role_id`) VALUES ('3', '2');

-- Projects
INSERT INTO `ea_final_project`.`project` (`id`, `description`, `end_date`, `location`, `name`, `start_date`, `status`) VALUES ('1','Test Desc','2018-05-25','Fairfield, Iowa','Enterprise Architecture Final Project', '2018-05-22','STARTED');

-- Project assignment
INSERT INTO `ea_final_project`.`user_project` (`user_id`, `project_id`) VALUES ('3', '1');




