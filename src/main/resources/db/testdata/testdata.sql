
INSERT INTO
   author(email, homepage, name, password)
VALUES
  ('userA@domainA.com', 'domainA.com', 'John Connor', '$2a$10$x7hh0f.7EIHhw7HaCKyzkuWM5dVDCLhmMBq.OdupIrVhU8cXt/tYC'),
  --password: secretPassword
  ('userB@domainA.com', 'domainB.com', 'Bob Carter', '$2a$10$uLQnlaUefRu2UspXBvxl2.fAblHChdxSDDmeJN3P99QJKmOZyoT.e');
  --password: secretPassword: very_strongP@ssword1

INSERT INTO project(downloads, publish_date, user_id, data, style, title) VALUES
 (5, '2023-01-01 00:00:01', SELECT id FROM author where email='userA@domainA.com', '{"backgroundColor":"#444444","width":16,"height":48,"zoom":1.75,"grid":false,"repeat":true,"positioning":"%","linears":[{"direction":64,"width":0,"height":0,"autoSize":true,"vertical":7,"horizontal":0,"visible":true,"grid":false,"lines":[{"position":0,"size":23,"color":"#999999","opacity":100,"blur":0}]},{"direction":64,"width":0,"height":0,"autoSize":true,"vertical":0,"horizontal":0,"visible":true,"grid":false,"lines":[{"position":78,"size":27,"color":"#999999","opacity":100,"blur":2}]},{"direction":63,"width":0,"height":0,"autoSize":true,"vertical":0,"horizontal":0,"visible":true,"grid":false,"lines":[{"position":38,"size":22,"color":"#9c9999","opacity":100,"blur":2}]}],"radials":[],"selected":{"type":0,"index":2},"id":null,"isLoading":true,"errors":null,"success":false}', '{"background-image": "linear-gradient(64deg, rgba(153, 153, 153, 1) 23%, transparent 23%) , linear-gradient(64deg, transparent 76%, rgba(153, 153, 153, 1) 78%, rgba(153, 153, 153, 1) 105%, transparent 107%) , linear-gradient(63deg, transparent 36%, rgba(156, 153, 153, 1) 38%, rgba(156, 153, 153, 1) 60%, transparent 62%)" , "background-position": "7px 0px, 0px 0px, 0px 0px", "background-color": "#444444", "background-size": "16px 48px", "background-repeat": "repeat"}', 'Grey stairs'),
 (5, '2022-01-01 00:00:01', SELECT id FROM author where email='userA@domainA.com', '{"backgroundColor":"#ddeeff","width":80,"height":80,"zoom":0.9,"grid":false,"repeat":true,"positioning":"%","linears":[],"radials":[{"shape":"ellipse","autoSize":true,"size":"closest-side","width":0,"height":0,"posx":50,"posy":50,"vertical":0,"horizontal":0,"visible":true,"grid":false,"rays":[{"position":98,"size":43,"color":"#000000","opacity":30,"blur":0}]},{"shape":"ellipse","autoSize":true,"size":"closest-side","width":0,"height":0,"posx":50,"posy":50,"vertical":40,"horizontal":40,"visible":true,"grid":false,"rays":[{"position":98,"size":43,"color":"#000000","opacity":30,"blur":0}]}],"selected":{"type":1,"index":1},"id":null,"isLoading":true,"errors":null,"success":false}', '{"background-image": "radial-gradient(ellipse closest-side at 50% 50%, transparent 97%, rgba(0, 0, 0, 0.3) 98%, rgba(0, 0, 0, 0.3) 140%, transparent 141%) , radial-gradient(ellipse closest-side at 50% 50%, transparent 97%, rgba(0, 0, 0, 0.3) 98%, rgba(0, 0, 0, 0.3) 140%, transparent 141%)" , "background-position": "0px 0px , 40px 40px" , "background-color": "#ddeeff", "background-size": "80px 80px", "background-repeat": "repeat"}', 'Shippo'),
 (5, '2021-01-01 00:00:01', SELECT id FROM author where email='userB@domainA.com', '{"backgroundColor":"#026873","width":80,"height":80,"zoom":0.9,"grid":false,"repeat":true,"positioning":"%","linears":[{"direction":90,"width":13,"height":13,"autoSize":false,"vertical":0,"horizontal":0,"visible":true,"grid":false,"lines":[{"position":0,"size":50,"color":"#ffffff","opacity":7,"blur":0}]},{"direction":90,"width":29,"height":29,"autoSize":false,"vertical":0,"horizontal":0,"visible":true,"grid":false,"lines":[{"position":0,"size":50,"color":"#ffffff","opacity":13,"blur":0}]},{"direction":90,"width":37,"height":37,"autoSize":false,"vertical":0,"horizontal":0,"visible":true,"grid":false,"lines":[{"position":0,"size":50,"color":"#ffffff","opacity":17,"blur":0}]},{"direction":90,"width":53,"height":53,"autoSize":false,"vertical":0,"horizontal":0,"visible":true,"grid":false,"lines":[{"position":0,"size":50,"color":"#ffffff","opacity":20,"blur":0}]}],"radials":[],"selected":{"type":0,"index":3},"id":null,"isLoading":true,"errors":null,"success":false}', '{"background-image": "linear-gradient(90deg, rgba(255, 255, 255, 0.07) 50%, transparent 50%) , linear-gradient(90deg, rgba(255, 255, 255, 0.13) 50%, transparent 50%) , linear-gradient(90deg, rgba(255, 255, 255, 0.17) 50%, transparent 50%) , linear-gradient(90deg, rgba(255, 255, 255, 0.2) 50%, transparent 50%)" , "background-position": "0px 0px, 0px 0px, 0px 0px, 0px 0px", "background-color": "#026873", "background-size": "13px 13px, 29px 29px, 37px 37px, 53px 53px", "background-repeat": "repeat"}', 'Cicada Stripes');