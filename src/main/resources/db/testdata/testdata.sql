INSERT INTO
   author(email, home_page, name, password)
VALUES
  ('userA@domainA.com', 'domainA.com', 'John Connor', 'secretPassword'),
  ('userB@domainA.com', 'domainB.com', 'Bob Carter', 'very_strongP@ssword1');

INSERT INTO project(downloads, publish_date, user_id, data, style, title) VALUES
 (5, '2023-01-01 00:00:01', SELECT id FROM author where email='userA@domainA.com', 'test data', 'test style', 'pattern A'),
 (5, '2022-01-01 00:00:01', SELECT id FROM author where email='userA@domainA.com', 'test data', 'test style', 'pattern B'),
 (5, '2021-01-01 00:00:01', SELECT id FROM author where email='userB@domainA.com', 'test data', 'test style', 'pattern C');