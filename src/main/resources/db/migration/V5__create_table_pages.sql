CREATE TABLE pages (
    id SERIAL PRIMARY KEY,
    profile_id INT NOT NULL,
    page_title VARCHAR(255) NOT NULL,
    page_home boolean DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    foreign key (profile_id) references user_profiles(id)
);
