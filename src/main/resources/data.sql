INSERT INTO rol (rol_id, rol_nombre)
VALUES (1, 'ADMIN'),
       (2, 'USER') ON CONFLICT (rol_id) DO
UPDATE SET rol_nombre = EXCLUDED.rol_nombre;


INSERT INTO persona(per_id, per_apellido, per_cedula, per_direccion, per_nombre, per_telefono)
VALUES (1, 'Curillo', '0106046709', 'Av. del Chofer', 'Adrian', '0981101464') ON CONFLICT (per_id) DO
UPDATE SET per_nombre = EXCLUDED.per_nombre,
    per_apellido = EXCLUDED.per_apellido;
