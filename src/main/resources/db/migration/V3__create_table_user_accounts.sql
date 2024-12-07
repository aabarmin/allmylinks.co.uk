CREATE TABLE user_accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    account_type VARCHAR(255) NOT NULL,
    account_password VARCHAR(255),
    account_is_active BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(user_id, account_type),
    FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB;
