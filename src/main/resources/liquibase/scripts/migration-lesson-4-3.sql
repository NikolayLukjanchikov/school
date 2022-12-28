--liquibase formatted sql

--changeSet: Nikolay_L:1
CREATE INDEX student_name_index ON student (name)

--changeSet: Nikolay_L:2
CREATE INDEX name_and_color_index ON faculty (name, color)

