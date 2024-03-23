INSERT INTO sys_user (entity_key, lock_version, create_by, create_time, del_flag, update_by, update_time,
                      object_comment, object_id,
                      named_space, avatar, email, password, real_name, status)
VALUES ('1', 0, '0', '2023-12-24 15:22:09.470187', 0, '0', '2023-12-24 15:22:09.470218', null, 'ADMIN', 'default', null,
        null, '$2a$10$.9WyxGockLYnsd.GrlXT7OdHmXnuu/U.8EgbGXmLO0Jn5DePqGy/2', 'ADMIN', null);

-- screen
INSERT INTO public.sys_screen (entity_key, create_by, create_time, lock_version, update_by, update_time, object_comment,
                               del_flag, named_space, object_id, active_schema_version)
VALUES ('0', 'ADMIN', '2024-01-16 16:24:02.000000', 1, 'ADMIN', '2024-01-16 16:24:06.000000', null, 0, 'DEFAULT',
        'LOGIN', 0);
INSERT INTO public.sys_screen (entity_key, create_by, create_time, lock_version, update_by, update_time, object_comment,
                               del_flag, named_space, object_id, active_schema_version)
VALUES ('1', 'ADMIN', '2024-01-16 16:24:02.000000', 1, 'ADMIN', '2024-01-16 16:24:06.000000', null, 0, 'DEFAULT',
        'SCREEN', 0);
INSERT INTO public.sys_screen (entity_key, create_by, create_time, lock_version, update_by, update_time, object_comment,
                               del_flag, named_space, object_id, active_schema_version)
VALUES ('2', 'ADMIN', '2024-01-16 16:24:02.000000', 1, 'ADMIN', '2024-01-16 16:24:06.000000', null, 0, 'DEFAULT',
        'MENU', 0);

-- MENU
INSERT INTO sys_menu (entity_key, lock_version, create_by, create_time, del_flag, update_by, update_time,
                      object_comment, object_id,
                      named_space, hide_in_menu, icon, menu_type, order_no, path, perms, status)
VALUES ('0', 0, '1', '2023-12-25 16:03:30.000000', 0, '1', '2023-12-25 16:03:35.000000', null, 'ROOT', 'DEFAULT',
        null,
        null, 'DIR', 1, '/', 'menu', 'ACTIVE');

INSERT INTO sys_menu (entity_key, lock_version, create_by, create_time, del_flag, update_by, update_time,
                      object_comment, object_id,
                      named_space,
                      hide_in_menu, icon, menu_type, order_no, parent_entity_key, path, perms, status)
VALUES ('1', 0, '1', '2023-12-25 15:53:34.000000', 0, '1', '2023-12-25 15:53:41.000000', null, 'dashboard', 'default',
        null, null, 'MENU', 1, '0', '/dashboard', 'menu.dashboard', 'ACTIVE');

-- system
INSERT INTO sys_menu (entity_key, lock_version, create_by, create_time, del_flag, update_by, update_time,
                      object_comment, object_id,
                      named_space, hide_in_menu, icon, menu_type, order_no, parent_entity_key, path, perms, status)
VALUES ('2', 0, '1', '2023-12-25 16:03:30.000000', 0, '1', '2023-12-25 16:03:35.000000', null, 'SYSTEM', 'default',
        null,
        null, 'DIR', 1, '0', '/system', 'menu.system', 'ACTIVE');

INSERT INTO sys_menu (entity_key, lock_version, create_by, create_time, del_flag, update_by, update_time,
                      object_comment, object_id,
                      named_space, hide_in_menu, icon, menu_type, order_no, parent_entity_key, path, perms, status,
                      screen_key)
VALUES ('3', 0, '1', '2023-12-25 16:03:30.000000', 0, '1', '2023-12-25 16:03:35.000000', null, 'MENU', 'default', null,
        null, 'MENU', 1, '2', '/system/menu', 'menu.system.menu', 'ACTIVE', '2');

INSERT INTO sys_menu (entity_key, lock_version, create_by, create_time, del_flag, update_by, update_time,
                      object_comment, object_id,
                      named_space, hide_in_menu, icon, menu_type, order_no, parent_entity_key, path, perms, status,
                      screen_key)
VALUES ('4', 0, '1', '2023-12-25 16:03:30.000000', 0, '1', '2023-12-25 16:03:35.000000', null, 'SCREEN', 'default',
        null,
        null, 'MENU', 2, '2', '/system/screen', 'menu.system.screen', 'ACTIVE', '1');


-- security
INSERT INTO sys_menu (entity_key, lock_version, create_by, create_time, del_flag, update_by, update_time,
                      object_comment, object_id,
                      named_space, hide_in_menu, icon, menu_type, order_no, parent_entity_key, path, perms, status)
VALUES ('5', 0, '1', '2023-12-25 16:03:30.000000', 0, '1', '2023-12-25 16:03:35.000000', null, 'SECURITY', 'default',
        null,
        null, 'DIR', 1, '0', '/security', 'menu.security', 'ACTIVE');

INSERT INTO sys_menu (entity_key, lock_version, create_by, create_time, del_flag, update_by, update_time,
                      object_comment, object_id,
                      named_space, hide_in_menu, icon, menu_type, order_no, parent_entity_key, path, perms, status)
VALUES ('6', 0, '1', '2023-12-25 16:03:30.000000', 0, '1', '2023-12-25 16:03:35.000000', null, 'USER', 'default', null,
        null, 'MENU', 1, '5', '/security/user', 'menu.security.user', 'ACTIVE');

INSERT INTO sys_menu (entity_key, lock_version, create_by, create_time, del_flag, update_by, update_time,
                      object_comment, object_id,
                      named_space, hide_in_menu, icon, menu_type, order_no, parent_entity_key, path, perms, status)
VALUES ('7', 0, '1', '2023-12-25 16:03:30.000000', 0, '1', '2023-12-25 16:03:35.000000', null, 'USER_GROUP', 'default',
        null,
        null, 'MENU', 1, '5', '/security/usergroup', 'menu.security.usergroup', 'ACTIVE');