CREATE TABLE tasks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR NOT NULL,
    description VARCHAR,
    status VARCHAR,
    priority VARCHAR,
    due_date DATE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
)
