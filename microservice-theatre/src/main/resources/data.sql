-- Insert Theatre data
INSERT INTO theatre (id) VALUES (1);
INSERT INTO theatre (id) VALUES (2);

-- Insert Seat data for Theatre 1
INSERT INTO seat (id, row, column, vip, avaliable, theatre_id) VALUES (1, 'A', 1, true, true, 1);
INSERT INTO seat (id, row, column, vip, avaliable, theatre_id) VALUES (2, 'A', 2, true, false, 1);
INSERT INTO seat (id, row, column, vip, avaliable, theatre_id) VALUES (3, 'B', 1, false, true, 1);
INSERT INTO seat (id, row, column, vip, avaliable, theatre_id) VALUES (4, 'B', 2, false, true, 1);

-- Insert Seat data for Theatre 2
INSERT INTO seat (id, row, column, vip, avaliable, theatre_id) VALUES (5, 'A', 1, true, true, 2);
INSERT INTO seat (id, row, column, vip, avaliable, theatre_id) VALUES (6, 'A', 2, false, false, 2);
INSERT INTO seat (id, row, column, vip, avaliable, theatre_id) VALUES (7, 'B', 1, false, true, 2);
INSERT INTO seat (id, row, column, vip, avaliable, theatre_id) VALUES (8, 'B', 2, true, true, 2);

-- TheatreDTO

-- [
--     {
--         "id": 1,
--         "seats": [
--             {
--                 "id": 1,
--                 "row": "A",
--                 "column": 1,
--                 "vip": true,
--                 "avaliable": true
--             },
--             {
--                 "id": 2,
--                 "row": "A",
--                 "column": 2,
--                 "vip": true,
--                 "avaliable": false
--             },
--             {
--                 "id": 3,
--                 "row": "B",
--                 "column": 1,
--                 "vip": false,
--                 "avaliable": true
--             },
--             {
--                 "id": 4,
--                 "row": "B",
--                 "column": 2,
--                 "vip": false,
--                 "avaliable": true
--             }
--         ]
--     },
--     {
--         "id": 2,
--         "seats": [
--             {
--                 "id": 5,
--                 "row": "A",
--                 "column": 1,
--                 "vip": true,
--                 "avaliable": true
--             },
--             {
--                 "id": 6,
--                 "row": "A",
--                 "column": 2,
--                 "vip": false,
--                 "avaliable": false
--             },
--             {
--                 "id": 7,
--                 "row": "B",
--                 "column": 1,
--                 "vip": false,
--                 "avaliable": true
--             },
--             {
--                 "id": 8,
--                 "row": "B",
--                 "column": 2,
--                 "vip": true,
--                 "avaliable": true
--             }
--         ]
--     }
-- ]

-- JSON SeatDTO
-- [
--     {
--         "id": 1,
--         "row": "A",
--         "column": 1,
--         "vip": true,
--         "avaliable": true,
--         "theatre": {
--             "id": 1
--         }
--     },
--     {
--         "id": 2,
--         "row": "A",
--         "column": 2,
--         "vip": true,
--         "avaliable": false,
--         "theatre": {
--             "id": 1
--         }
--     },
--     {
--         "id": 3,
--         "row": "B",
--         "column": 1,
--         "vip": false,
--         "avaliable": true,
--         "theatre": {
--             "id": 1
--         }
--     },
--     {
--         "id": 4,
--         "row": "B",
--         "column": 2,
--         "vip": false,
--         "avaliable": true,
--         "theatre": {
--             "id": 1
--         }
--     }
-- ]


