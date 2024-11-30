CREATE TABLE user_accounts (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    account_type VARCHAR(255) NOT NULL,
    account_password VARCHAR(255),
    account_is_active BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    unique(user_id, account_type),
    foreign key (user_id) references users(id)
);
