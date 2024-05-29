CREATE TABLE device_type_inputs (
    device_type_id INT NOT NULL,
    input_device_type_id INT NOT NULL,
    PRIMARY KEY (device_type_id, input_device_type_id),
    FOREIGN KEY (device_type_id) REFERENCES device_types(id),
    FOREIGN KEY (input_device_type_id) REFERENCES device_types(id)
);

CREATE TABLE device_type_outputs (
    device_type_id INT NOT NULL,
    output_device_type_id INT NOT NULL,
    PRIMARY KEY (device_type_id, output_device_type_id),
    FOREIGN KEY (device_type_id) REFERENCES device_types(id),
    FOREIGN KEY (output_device_type_id) REFERENCES device_types(id)
);