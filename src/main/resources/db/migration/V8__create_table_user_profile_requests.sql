CREATE TABLE user_profile_requests (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    profile_id INT NOT NULL,
    change_type VARCHAR(255) NOT NULL,
    change_status VARCHAR(255) NOT NULL,
    change_payload JSON,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (profile_id) REFERENCES user_profiles(id)
);
