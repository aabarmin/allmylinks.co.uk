CREATE TABLE files (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    file_storage VARCHAR(255) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    foreign key (user_id) references users(id)
);
