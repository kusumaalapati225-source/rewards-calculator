CREATE TABLE customers(
                          customer_id BIGINT PRIMARY KEY,
                          customer_name VARCHAR(100)
);

CREATE TABLE transactions(
                             transaction_id BIGINT PRIMARY KEY,
                             customer_id BIGINT,
                             amount DECIMAL(10,2),
                             transaction_date DATE
);