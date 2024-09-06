INSERT INTO theatre (id) VALUES (1);
INSERT INTO theatre (id) VALUES (2);
INSERT INTO theatre (id) VALUES (3);

-- Theatre 1 seats
INSERT INTO seat (id, seat_row, seat_column, vip, available, theatre_id) VALUES (1, 'A', 1, false, true, 1);
-- INSERT INTO seat (id, row, column, vip, available, theatre_id) VALUES (2, 'A', 2, false, true, 1);
-- More seats from row A to E
-- INSERT INTO seat (id, row, column, vip, available, theatre_id) VALUES (21, 'F', 1, true, true, 1);  -- VIP seats
-- INSERT INTO seat (id, row, column, vip, available, theatre_id) VALUES (22, 'F', 2, true, true, 1);

-- Theatre 2 seats
-- INSERT INTO seat (id, row, column, vip, available, theatre_id) VALUES (23, 'A', 1, false, true, 2);
-- INSERT INTO seat (id, row, column, vip, available, theatre_id) VALUES (24, 'F', 1, true, true, 2);  -- VIP seat in Theatre 2

-- Theatre 3 seats
-- INSERT INTO seat (id, row, column, vip, available, theatre_id) VALUES (25, 'F', 1, true, true, 3);  -- VIP seat in Theatre 3
