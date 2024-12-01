CREATE TABLE pages (
    id SERIAL PRIMARY KEY,
    profile_id INT NOT NULL,
    page_title VARCHAR(255) NOT NULL,
    page_home boolean DEFAULT FALSE,
    page_deleted boolean DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    foreign key (profile_id) references user_profiles(id)
);
