CREATE TABLE pages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    profile_id INT NOT NULL,
    page_title VARCHAR(255) NOT NULL,
    page_home BOOLEAN DEFAULT FALSE,
    page_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (profile_id) REFERENCES user_profiles(id)
) ENGINE=InnoDB;
