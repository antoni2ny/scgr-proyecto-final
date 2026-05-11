INSERT INTO scgr.dependencia
(id_dependencia, nombre)
VALUES(1, 'BDSW');
INSERT INTO scgr.dependencia
(id_dependencia, nombre)
VALUES(3, 'DGPE');
INSERT INTO scgr.dependencia
(id_dependencia, nombre)
VALUES(2, 'DGTIC');
INSERT INTO scgr.dependencia
(id_dependencia, nombre)
VALUES(4, 'Secretaría Administrativa');
INSERT INTO scgr.dependencia
(id_dependencia, nombre)
VALUES(5, 'Sin dependencia');


INSERT INTO scgr.estatus_reporte
(id_estatus, nombre)
VALUES(1, 'Pendiente');
INSERT INTO scgr.estatus_reporte
(id_estatus, nombre)
VALUES(2, 'En proceso');
INSERT INTO scgr.estatus_reporte
(id_estatus, nombre)
VALUES(3, 'Finalizado');
INSERT INTO scgr.estatus_reporte
(id_estatus, nombre)
VALUES(4, 'Cancelado');


INSERT INTO scgr.rol
(id_rol, nombre)
VALUES(1, 'ADMIN');
INSERT INTO scgr.rol
(id_rol, nombre)
VALUES(3, 'CONSULTA');
INSERT INTO scgr.rol
(id_rol, nombre)
VALUES(2, 'USER');


INSERT INTO scgr.tipo_reporte
(id_tipo_reporte, nombre, descripcion)
VALUES(1, 'Anual', 'Reporte anual');
INSERT INTO scgr.tipo_reporte
(id_tipo_reporte, nombre, descripcion)
VALUES(2, 'Mensual', 'Reporte que se hace mensualmente');
INSERT INTO scgr.tipo_reporte
(id_tipo_reporte, nombre, descripcion)
VALUES(3, 'Trimestral', 'Reporte trimestrales');
INSERT INTO scgr.tipo_reporte
(id_tipo_reporte, nombre, descripcion)
VALUES(4, 'Administrativo', 'Reporte relacionado con información administrativa');
INSERT INTO scgr.tipo_reporte
(id_tipo_reporte, nombre, descripcion)
VALUES(5, 'Estadístico', 'Reporte relacionado con estadísticas');
INSERT INTO scgr.tipo_reporte
(id_tipo_reporte, nombre, descripcion)
VALUES(6, 'Académico', 'Reporte relacionado con información académica');


INSERT INTO scgr.usuario
(id_usuario, numero_empleado, nombre_completo, password, id_dependencia)
VALUES(1, 123456, 'Calderón Díaz Antoni Jair', 'admin123', 1);
INSERT INTO scgr.usuario
(id_usuario, numero_empleado, nombre_completo, password, id_dependencia)
VALUES(23, 1001, 'Administrador Sistema', '$2a$10$5XOir3iSLI1JrzMIwOaC9.r0.HGka.HlMO3uDA1s3etlNF3W7bFz6', 2);
INSERT INTO scgr.usuario
(id_usuario, numero_empleado, nombre_completo, password, id_dependencia)
VALUES(24, 2001, 'Usuario Prueba', '$2a$10$5XOir3iSLI1JrzMIwOaC9.r0.HGka.HlMO3uDA1s3etlNF3W7bFz6', 2);
INSERT INTO scgr.usuario
(id_usuario, numero_empleado, nombre_completo, password, id_dependencia)
VALUES(25, 3001, 'Usuario Consulta', '$2a$10$5XOir3iSLI1JrzMIwOaC9.r0.HGka.HlMO3uDA1s3etlNF3W7bFz6', 2);


INSERT INTO scgr.usuario_rol
(id_usuario, id_rol)
VALUES(1, 1);
INSERT INTO scgr.usuario_rol
(id_usuario, id_rol)
VALUES(1, 3);
INSERT INTO scgr.usuario_rol
(id_usuario, id_rol)
VALUES(23, 1);
INSERT INTO scgr.usuario_rol
(id_usuario, id_rol)
VALUES(24, 2);
INSERT INTO scgr.usuario_rol
(id_usuario, id_rol)
VALUES(25, 3);


INSERT INTO scgr.perfil_usuario
(id_perfil, id_usuario, correo)
VALUES(1, 1, 'antoni@unam.mx');
INSERT INTO scgr.perfil_usuario
(id_perfil, id_usuario, correo)
VALUES(2, 23, 'admin@scgr.com');
INSERT INTO scgr.perfil_usuario
(id_perfil, id_usuario, correo)
VALUES(3, 24, 'usuario@scgr.com');
INSERT INTO scgr.perfil_usuario
(id_perfil, id_usuario, correo)
VALUES(4, 25, 'consulta@scgr.com');


INSERT INTO scgr.reporte
(id_reporte, nombre, descripcion, fecha_creacion, id_usuario, id_estatus, id_tipo_reporte)
VALUES(4, 'Reporte mensual de asistencia', 'Generado por el sistema', '2025-11-17 23:47:31.000', 1, 2, 2);
INSERT INTO scgr.reporte
(id_reporte, nombre, descripcion, fecha_creacion, id_usuario, id_estatus, id_tipo_reporte)
VALUES(5, 'Reporte actualizado', 'Descripción modificada', '2025-11-17 23:55:17.000', 1, 3, 1);
INSERT INTO scgr.reporte
(id_reporte, nombre, descripcion, fecha_creacion, id_usuario, id_estatus, id_tipo_reporte)
VALUES(6, 'Reporte mensual de asistencia', 'Generado por el sistema', '2025-11-17 23:55:45.000', 1, 2, 2);
INSERT INTO scgr.reporte
(id_reporte, nombre, descripcion, fecha_creacion, id_usuario, id_estatus, id_tipo_reporte)
VALUES(7, 'Reporte mensual de asistencia', 'Generado por el sistema', '2025-11-17 23:55:56.000', 1, 2, 2);
INSERT INTO scgr.reporte
(id_reporte, nombre, descripcion, fecha_creacion, id_usuario, id_estatus, id_tipo_reporte)
VALUES(8, 'Reporte de Asistencia Mensual', 'Resumen general de asistencia de personal administrativo y académico.', '2025-11-17 23:57:30.000', 1, 1, 2);
INSERT INTO scgr.reporte
(id_reporte, nombre, descripcion, fecha_creacion, id_usuario, id_estatus, id_tipo_reporte)
VALUES(9, 'Prueba', 'Reporte de pruebas', '2025-12-04 23:22:37.000', 1, 2, 6);
INSERT INTO scgr.reporte
(id_reporte, nombre, descripcion, fecha_creacion, id_usuario, id_estatus, id_tipo_reporte)
VALUES(12, 'reporte prueba2', '', '2026-03-30 18:08:38.000', 1, 2, 2);
INSERT INTO scgr.reporte
(id_reporte, nombre, descripcion, fecha_creacion, id_usuario, id_estatus, id_tipo_reporte)
VALUES(13, 'reporte prueba2', '', '2026-03-30 18:09:04.000', 23, 2, 2);
INSERT INTO scgr.reporte
(id_reporte, nombre, descripcion, fecha_creacion, id_usuario, id_estatus, id_tipo_reporte)
VALUES(14, 'reporte prueba23', 'descripción nueva', '2026-03-30 18:46:50.000', 25, 1, 4);
INSERT INTO scgr.reporte
(id_reporte, nombre, descripcion, fecha_creacion, id_usuario, id_estatus, id_tipo_reporte)
VALUES(16, 'reporte prueba244', 'prueba', '2026-03-30 19:15:00.000', 1, 3, 4);
INSERT INTO scgr.reporte
(id_reporte, nombre, descripcion, fecha_creacion, id_usuario, id_estatus, id_tipo_reporte)
VALUES(25, 'Reporte de Asistencia Mensual', 'Reporte de trabajadores con asistencia', '2026-05-10 19:28:20.000', 24, 2, 6);
INSERT INTO scgr.reporte
(id_reporte, nombre, descripcion, fecha_creacion, id_usuario, id_estatus, id_tipo_reporte)
VALUES(28, 'Reporte general', 'Resumen general de bono de personal administrativo y académico', '2026-05-10 19:40:36.000', 23, 1, 4);


INSERT INTO scgr.bitacora
(id_bitacora, fecha_hora, accion, tabla, id_registro, id_usuario)
VALUES(1, '2025-12-04 23:23:42.000', 'alta', 'reporte', 1, 1);
INSERT INTO scgr.bitacora
(id_bitacora, fecha_hora, accion, tabla, id_registro, id_usuario)
VALUES(2, '2025-12-04 23:24:31.000', 'baja', 'reporte', 1, 1);
INSERT INTO scgr.bitacora
(id_bitacora, fecha_hora, accion, tabla, id_registro, id_usuario)
VALUES(3, '2025-12-04 23:24:58.000', 'modificacion', 'laquesea', 2, 1);
INSERT INTO scgr.bitacora
(id_bitacora, fecha_hora, accion, tabla, id_registro, id_usuario)
VALUES(4, '2026-03-10 19:56:38.000', 'alta', 'reporte', 30, 24);
INSERT INTO scgr.bitacora
(id_bitacora, fecha_hora, accion, tabla, id_registro, id_usuario)
VALUES(5, '2026-03-10 19:57:41.000', 'baja', 'reporte', 30, 24);
INSERT INTO scgr.bitacora
(id_bitacora, fecha_hora, accion, tabla, id_registro, id_usuario)
VALUES(6, '2026-04-10 20:23:39.000', 'modificacion', 'reporte', 25, 24);
INSERT INTO scgr.bitacora
(id_bitacora, fecha_hora, accion, tabla, id_registro, id_usuario)
VALUES(7, '2026-04-10 21:54:32.000', 'alta', 'reporte', 31, 23);
INSERT INTO scgr.bitacora
(id_bitacora, fecha_hora, accion, tabla, id_registro, id_usuario)
VALUES(8, '2026-05-10 21:54:36.000', 'baja', 'reporte', 31, 23);
INSERT INTO scgr.bitacora
(id_bitacora, fecha_hora, accion, tabla, id_registro, id_usuario)
VALUES(9, '2026-05-10 22:32:12.000', 'modificacion', 'reporte', 25, 23);
INSERT INTO scgr.bitacora
(id_bitacora, fecha_hora, accion, tabla, id_registro, id_usuario)
VALUES(10, '2026-05-10 22:32:56.000', 'modificacion', 'reporte', 28, 23);




