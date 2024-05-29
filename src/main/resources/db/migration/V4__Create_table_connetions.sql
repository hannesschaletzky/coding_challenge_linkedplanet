CREATE TABLE connections (
    id SERIAL PRIMARY KEY,
    source_device_id INT NOT NULL,
    target_device_id INT NOT NULL
);
