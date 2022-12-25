CREATE TABLE car (
                     id SERIAL PRIMARY KEY,
                     brand TEXT NOT NULL,
                     model TEXT NOT NULL,
                     price REAL CHECK (price >= 0)
);

CREATE TABLE people (
                       id SERIAL PRIMARY KEY,
                       name TEXT NOT NULL,
                       age INTEGER,
                       driverLicense BOOLEAN,
                       car_id INT REFERENCES car (id)
)

