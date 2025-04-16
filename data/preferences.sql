-- Table for Unit Groups
--CREATE TABLE unit_groups (
--    id INT AUTO_INCREMENT PRIMARY KEY,
--    code VARCHAR(10) NOT NULL UNIQUE,
--    description VARCHAR(50)
--);

INSERT INTO unit_groups (code, description) VALUES
('us', 'US customary units'),
('uk', 'UK imperial units'),
('metric', 'Metric system'),
('base', 'Scientific measurement units');

-- Table for Languages
--CREATE TABLE languages (
--    id INT AUTO_INCREMENT PRIMARY KEY,
--    code VARCHAR(5) NOT NULL UNIQUE,
--    name VARCHAR(50) NOT NULL
--);

INSERT INTO languages (code, name) VALUES
('ar', 'Arabic'),
('bg', 'Bulgarian'),
('cs', 'Czech'),
('da', 'Danish'),
('de', 'German'),
('el', 'Greek'),
('en', 'English'),
('es', 'Spanish'),
('fa', 'Farsi'),
('fi', 'Finnish'),
('fr', 'French'),
('he', 'Hebrew'),
('hu', 'Hungarian'),
('it', 'Italian'),
('ja', 'Japanese'),
('ko', 'Korean'),
('nl', 'Dutch'),
('pl', 'Polish'),
('pt', 'Portuguese'),
('ru', 'Russian'),
('sk', 'Slovakian'),
('sr', 'Serbian'),
('sv', 'Swedish'),
('tr', 'Turkish'),
('uk', 'Ukrainian'),
('vi', 'Vietnamese'),
('zh', 'Chinese');
