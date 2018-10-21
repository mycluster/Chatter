DROP TABLE class_accesses;
DROP TABLE usr_accesses;
DROP TABLE access_level;
DROP TABLE class_membership;
DROP TABLE class_roles;
DROP TABLE classes;
DROP TABLE notes;
DROP TABLE note_types;
DROP TABLE messages;
DROP TABLE edit_flags;
DROP TABLE usrs;
DROP TABLE activated;
DROP TABLE privs;


CREATE TABLE privs (
    p_id NUMBER(2),
    p_name VARCHAR2(50),
    CONSTRAINT privs_pk PRIMARY KEY (p_id)
);

CREATE TABLE activated (
    a_id NUMBER(1),
    a_name VARCHAR2(20),
    CONSTRAINT activated_pk PRIMARY KEY (a_id)
);

CREATE TABLE usrs (
    u_id NUMBER(9),
    username VARCHAR2(50),
    pwd VARCHAR2(50),
    f_name VARCHAR2(30),
    l_name VARCHAR2(30),
    priv NUMBER(2),
    activated NUMBER(1),
    CONSTRAINT usrs_pk PRIMARY KEY (u_id),
    CONSTRAINT usrs_fk FOREIGN KEY (priv)
        REFERENCES privs (p_id),
    CONSTRAINT usrs_fk_activated FOREIGN KEY (activated)
        REFERENCES activated (a_id)
);

CREATE TABLE edit_flags (
    f_id NUMBER(1),
    f_name VARCHAR2(20),
    CONSTRAINT edit_flags_pk PRIMARY KEY (f_id)
);

CREATE TABLE messages (
    m_id NUMBER(10),
    sender NUMBER(9),
    receiver NUMBER(9),
    message VARCHAR2(500),
    edited NUMBER(1),
    sent_at TIMESTAMP,
    CONSTRAINT messages_pk PRIMARY KEY (m_id),
    CONSTRAINT messages_sender_fk FOREIGN KEY (sender)
        REFERENCES usrs (u_id),
    CONSTRAINT messages_receiver_fk FOREIGN KEY (receiver)
        REFERENCES usrs (u_id),
    CONSTRAINT messages_edit_fk FOREIGN KEY (edited)
        REFERENCES edit_flags (f_id)
);

CREATE TABLE note_types (
    n_id NUMBER(2),
    n_name VARCHAR2(20),
    CONSTRAINT note_types_pk PRIMARY KEY (n_id)
);

CREATE TABLE notes (
    n_id NUMBER(9),
    ownr NUMBER(9),
    ty NUMBER(2),
    loc VARCHAR2(100),
    CONSTRAINT notes_pk PRIMARY KEY (n_id),
    CONSTRAINT notes_owner_fk FOREIGN KEY (ownr)
        REFERENCES usrs (u_id),
    CONSTRAINT notes_type_fk FOREIGN KEY (ty)
        REFERENCES note_types (n_id)
);

CREATE TABLE classes (
    c_id NUMBER(9),
    c_name VARCHAR(50),
    CONSTRAINT classes_pk PRIMARY KEY (c_id)
);

CREATE TABLE class_roles (
    c_id NUMBER(2),
    c_name VARCHAR2(50),
    CONSTRAINT class_roles_pk PRIMARY KEY (c_id)
);

CREATE TABLE class_membership (
    c_id NUMBER(10),
    usr NUMBER(9),
    cls NUMBER(9),
    c_role NUMBER(2),
    CONSTRAINT class_membership_pk PRIMARY KEY (c_id),
    CONSTRAINT class_membership_usr FOREIGN KEY (usr)
        REFERENCES usrs (u_id),
    CONSTRAINT class_membership_class FOREIGN KEY (cls)
        REFERENCES classes (c_id),
    CONSTRAINT class_membership_role FOREIGN KEY (c_role)
        REFERENCES class_roles (c_id)
);

CREATE TABLE access_level (
    a_id NUMBER(1),
    a_name VARCHAR2(50),
    CONSTRAINT access_level_pk PRIMARY KEY (a_id)
);

CREATE TABLE usr_accesses (
    a_id NUMBER(10),
    usr NUMBER(9),
    note NUMBER(9),
    a_level NUMBER(1),
    CONSTRAINT usr_accesses_pk PRIMARY KEY (a_id),
    CONSTRAINT usr_accesses_usr FOREIGN KEY (usr)
        REFERENCES usrs (u_id),
    CONSTRAINT usr_accesses_note FOREIGN KEY (note)
        REFERENCES notes (n_id),
    CONSTRAINT usr_accesses_level FOREIGN KEY (a_level)
        REFERENCES access_level (a_id)
);

CREATE TABLE class_accesses (
    a_id NUMBER(10),
    cls NUMBER(9),
    note NUMBER(9),
    a_level NUMBER(1),
    CONSTRAINT class_accesses_pk PRIMARY KEY (a_id),
    CONSTRAINT class_accesses_class FOREIGN KEY (cls)
        REFERENCES classes (c_id),
    CONSTRAINT class_accesses_note FOREIGN KEY (note)
        REFERENCES notes(n_id),
    CONSTRAINT class_accesses_level FOREIGN KEY (a_level)
        REFERENCES access_level (a_id)
);