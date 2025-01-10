/*初始化*/
INSERT INTO team_nav.nav_role(id, role_name, update_time)
SELECT '1', '管理员', now() WHERE NOT EXISTS (SELECT 1 FROM team_nav.nav_role WHERE id='1');

INSERT INTO team_nav.nav_role_user(ID, role_id, user_id)
SELECT '1', '1', '1' WHERE NOT EXISTS (SELECT 1 FROM team_nav.nav_role_user WHERE id='1');
