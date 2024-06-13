ALTER TABLE doctors ADD active TINYINT(1);
UPDATE doctors SET active = 1;
