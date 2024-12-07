CREATE TABLE page_blocks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    page_id INT NOT NULL,
    block_type VARCHAR(255) NOT NULL,
    block_order INT NOT NULL,
    block_deleted BOOLEAN DEFAULT FALSE,
    block_props JSON,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (page_id) REFERENCES pages(id)
) ENGINE=InnoDB;
