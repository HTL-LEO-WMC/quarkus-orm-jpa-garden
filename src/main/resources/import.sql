-- Runs on every start in dev and test mode (drop-and-create).
-- Table and column names are the entity names, lower case.

insert into garden (id, address) values (1, 'Flurstraße 17, 4470 Enns');
insert into garden (id, address) values (2, 'Limesstraße 12, 4060 Leonding');

insert into plot (id, width, height, garden_id) values (1, 12, 3, 1);
insert into plot (id, width, height, garden_id) values (2, 4, 4, 1);
insert into plot (id, width, height, garden_id) values (3, 6, 2, 2);

insert into vegetable (id, name, nutritionalvalue) values (1, 'Carrot', 41);
insert into vegetable (id, name, nutritionalvalue) values (2, 'Potato', 77);
insert into vegetable (id, name, nutritionalvalue) values (3, 'Pumpkin', 26);

insert into plot_vegetable (plot_id, vegetable_id) values (1, 1);
insert into plot_vegetable (plot_id, vegetable_id) values (1, 2);
insert into plot_vegetable (plot_id, vegetable_id) values (3, 3);

-- Hibernate continues from here, so new rows do not collide with the ones above.
alter sequence garden_seq restart with 100;
alter sequence plot_seq restart with 100;
alter sequence vegetable_seq restart with 100;
