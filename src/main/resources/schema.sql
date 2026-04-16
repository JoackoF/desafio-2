-- Tabla Profesor
CREATE TABLE
    IF NOT EXISTS profesor (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               nombre VARCHAR(100) NOT NULL
    );

-- Tabla Materia
CREATE TABLE
    IF NOT EXISTS materia (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              nombre VARCHAR(100) NOT NULL,
    id_profesor BIGINT,
    CONSTRAINT fk_materia_profesor FOREIGN KEY (id_profesor) REFERENCES profesor (id)
    );

-- Tabla Alumno
CREATE TABLE
    IF NOT EXISTS alumno (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL
    );

-- Tabla Intermedia (Inscripciones)
CREATE TABLE
    IF NOT EXISTS alumno_materia (
                                     id_alumno BIGINT,
                                     id_materia BIGINT,
                                     PRIMARY KEY (id_alumno, id_materia),
    CONSTRAINT fk_alumno_materia_alumno FOREIGN KEY (id_alumno) REFERENCES alumno (id),
    CONSTRAINT fk_alumno_materia_materia FOREIGN KEY (id_materia) REFERENCES materia (id)
    );