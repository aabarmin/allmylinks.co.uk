CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    task_type VARCHAR(255) NOT NULL,
    task_data BYTEA NOT NULL,
    task_status VARCHAR(255) NOT NULL,
    execution_attempts INT,
    last_run_at TIMESTAMP,
    execution_exception TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);
