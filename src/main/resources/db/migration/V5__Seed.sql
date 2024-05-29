-- seed device types and their inputs and outputs
INSERT INTO device_types (name) VALUES ('Mikrofon');
INSERT INTO device_types (name) VALUES ('Kamera');
INSERT INTO device_types (name) VALUES ('Video-Mischer');
INSERT INTO device_types (name) VALUES ('Audio-Mischer');
INSERT INTO device_types (name) VALUES ('Encoder');
INSERT INTO device_types (name) VALUES ('Sender-Satellit');
INSERT INTO device_types (name) VALUES ('Sender-TV');
INSERT INTO device_types (name) VALUES ('Sender-Internet');
INSERT INTO device_type_inputs (device_type_name, input_device_type_name) VALUES ('Video-Mischer', 'Kamera');
INSERT INTO device_type_inputs (device_type_name, input_device_type_name) VALUES ('Audio-Mischer', 'Mikrofon');
INSERT INTO device_type_inputs (device_type_name, input_device_type_name) VALUES ('Encoder', 'Video-Mischer');
INSERT INTO device_type_inputs (device_type_name, input_device_type_name) VALUES ('Encoder', 'Audio-Mischer');
INSERT INTO device_type_inputs (device_type_name, input_device_type_name) VALUES ('Sender-Satellit', 'Encoder');
INSERT INTO device_type_inputs (device_type_name, input_device_type_name) VALUES ('Sender-TV', 'Encoder');
INSERT INTO device_type_inputs (device_type_name, input_device_type_name) VALUES ('Sender-Internet', 'Encoder');
INSERT INTO device_type_outputs (device_type_name, output_device_type_name) VALUES ('Mikrofon', 'Audio-Mischer');
INSERT INTO device_type_outputs (device_type_name, output_device_type_name) VALUES ('Kamera', 'Video-Mischer');
INSERT INTO device_type_outputs (device_type_name, output_device_type_name) VALUES ('Video-Mischer', 'Encoder');
INSERT INTO device_type_outputs (device_type_name, output_device_type_name) VALUES ('Audio-Mischer', 'Encoder');
INSERT INTO device_type_outputs (device_type_name, output_device_type_name) VALUES ('Encoder', 'Sender-Satellit');
INSERT INTO device_type_outputs (device_type_name, output_device_type_name) VALUES ('Encoder', 'Sender-TV');
INSERT INTO device_type_outputs (device_type_name, output_device_type_name) VALUES ('Encoder', 'Sender-Internet');

-- seed the inventory
INSERT INTO devices (name, device_type_name) VALUES ('Stand Mikro groß', 'Mikrofon');
INSERT INTO devices (name, device_type_name) VALUES ('Stand Mikro klein', 'Mikrofon');
INSERT INTO devices (name, device_type_name) VALUES ('Schall 30db', 'Mikrofon');
INSERT INTO devices (name, device_type_name) VALUES ('Schall 50db', 'Mikrofon');
INSERT INTO devices (name, device_type_name) VALUES ('Mobiles Mikro rot', 'Mikrofon');
INSERT INTO devices (name, device_type_name) VALUES ('Mobiles Mikro blau', 'Mikrofon');

INSERT INTO devices (name, device_type_name) VALUES ('Hauptfeld', 'Kamera');
INSERT INTO devices (name, device_type_name) VALUES ('Hintertor 1', 'Kamera');
INSERT INTO devices (name, device_type_name) VALUES ('Hintertor 2', 'Kamera');
INSERT INTO devices (name, device_type_name) VALUES ('Zeitlupe LO', 'Kamera');
INSERT INTO devices (name, device_type_name) VALUES ('Zeitlupe RO', 'Kamera');

INSERT INTO devices (name, device_type_name) VALUES ('Video-Mischer Regie 1', 'Video-Mischer');
INSERT INTO devices (name, device_type_name) VALUES ('Audio-Mischer Regie 1', 'Audio-Mischer');
INSERT INTO devices (name, device_type_name) VALUES ('Video-Mischer Regie 2', 'Video-Mischer');
INSERT INTO devices (name, device_type_name) VALUES ('Audio-Mischer Regie 2', 'Audio-Mischer');

INSERT INTO devices (name, device_type_name) VALUES ('Encoder', 'Encoder');

INSERT INTO devices (name, device_type_name) VALUES ('Satellit-USA', 'Sender-Satellit');
INSERT INTO devices (name, device_type_name) VALUES ('Satellit-USA-Ersatz', 'Sender-Satellit');
INSERT INTO devices (name, device_type_name) VALUES ('TV-Sky', 'Sender-TV');
INSERT INTO devices (name, device_type_name) VALUES ('TV-BBC', 'Sender-TV');
INSERT INTO devices (name, device_type_name) VALUES ('Internet-DAZN', 'Sender-Internet');
INSERT INTO devices (name, device_type_name) VALUES ('Internet-Sky', 'Sender-Internet');
INSERT INTO devices (name, device_type_name) VALUES ('Internet-BBC', 'Sender-Internet');




