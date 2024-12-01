CREATE TABLE page_blocks (
    id SERIAL PRIMARY KEY,
    page_id INT NOT NULL,
    block_type VARCHAR(255) NOT NULL,
    block_order INT NOT NULL,
    block_deleted BOOLEAN DEFAULT FALSE,
    block_props JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    foreign key (page_id) references pages(id)
);
