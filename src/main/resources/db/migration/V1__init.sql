CREATE TABLE posts (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(250) NOT NULL UNIQUE,
    content TEXT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    likes INTEGER NOT NULL DEFAULT 0
);

INSERT INTO
    posts (title, content, created, likes)
VALUES
    (
        'First Posts',
        'This is content of the first post',
        CURRENT_TIMESTAMP,
        10
    ),
    (
        'Second Posts',
        'This is content of the second post',
        CURRENT_TIMESTAMP,
        3
    );