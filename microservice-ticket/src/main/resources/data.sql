-- Insert Ticket data
INSERT INTO ticket (id, customerID, roundID, seatID) VALUES (1, 1, 1, 1);
INSERT INTO ticket (id, customerID, roundID, seatID) VALUES (2, 1, 1, 2);
INSERT INTO ticket (id, customerID, roundID, seatID) VALUES (3, 2, 1, 3);
INSERT INTO ticket (id, customerID, roundID, seatID) VALUES (4, 2, 2, 5);


-- [
--     {
--         "id": 1,
--         "customerID": 1,
--         "roundID": 1,
--         "seatID": 1
--     },
--     {
--         "id": 2,
--         "customerID": 1,
--         "roundID": 1,
--         "seatID": 2
--     },
--     {
--         "id": 3,
--         "customerID": 2,
--         "roundID": 1,
--         "seatID": 3
--     },
--     {
--         "id": 4,
--         "customerID": 2,
--         "roundID": 2,
--         "seatID": 5
--     }
-- ]
