-- Création de la table flags
CREATE TABLE flags (
                       flag_id SERIAL PRIMARY KEY,
                       location_id INTEGER,
                       user_id INTEGER NOT NULL,
                       comment_id INTEGER,
                       reason TEXT NOT NULL,
                       reviewed_by INTEGER,
                       status VARCHAR(100) DEFAULT 'pending'
);

-- Insertion d'exemples de flags
INSERT INTO flags (location_id, user_id, comment_id, reason, reviewed_by, status)
VALUES (1, 1, NULL, 'Contenu inapproprié dans le lieu', NULL, 'pending');

INSERT INTO flags (location_id, user_id, comment_id, reason, reviewed_by, status)
VALUES (NULL, 2, 1, 'Commentaire insultant', NULL, 'pending');
