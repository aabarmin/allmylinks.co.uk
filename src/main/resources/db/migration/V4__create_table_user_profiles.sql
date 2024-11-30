CREATE TABLE user_profiles (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    profile_link VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    unique(profile_link),
    foreign key (user_id) references users(id)
);
