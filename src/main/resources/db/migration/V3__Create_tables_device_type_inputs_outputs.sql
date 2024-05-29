CREATE TABLE device_type_inputs (
    device_type_name VARCHAR(100) NOT NULL,
    input_device_type_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (device_type_name, input_device_type_name),
    FOREIGN KEY (device_type_name) REFERENCES device_types(name),
    FOREIGN KEY (input_device_type_name) REFERENCES device_types(name)
);

CREATE TABLE device_type_outputs (
    device_type_name VARCHAR(100) NOT NULL,
    output_device_type_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (device_type_name, output_device_type_name),
    FOREIGN KEY (device_type_name) REFERENCES device_types(name),
    FOREIGN KEY (output_device_type_name) REFERENCES device_types(name)
);