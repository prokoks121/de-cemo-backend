CREATE TABLE bar
(
    id                   BIGINT NOT NULL,
    name                 VARCHAR(255),
    address              VARCHAR(255),
    work_time            JSON,
    latitude             VARCHAR(255),
    longitude            VARCHAR(255),
    phone_number         VARCHAR(255),
    main_picture_url     VARCHAR(255),
    gallery_picture_urls JSON,
    bar_type_id          BIGINT,
    CONSTRAINT pk_bar PRIMARY KEY (id)
);

CREATE TABLE bar_services
(
    bars_id     BIGINT NOT NULL,
    services_id BIGINT NOT NULL
);

CREATE TABLE bar_type
(
    id   BIGINT NOT NULL,
    type VARCHAR(255),
    CONSTRAINT pk_bartype PRIMARY KEY (id)
);

CREATE TABLE service
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255),
    icon_url VARCHAR(255),
    CONSTRAINT pk_service PRIMARY KEY (id)
);

ALTER TABLE bar
    ADD CONSTRAINT FK_BAR_ON_BAR_TYPE FOREIGN KEY (bar_type_id) REFERENCES bar_type (id);

ALTER TABLE bar_services
    ADD CONSTRAINT fk_barser_on_bar FOREIGN KEY (bars_id) REFERENCES bar (id);

ALTER TABLE bar_services
    ADD CONSTRAINT fk_barser_on_service FOREIGN KEY (services_id) REFERENCES service (id);