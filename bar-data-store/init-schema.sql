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

CREATE TABLE bar_events
(
    bar_id    BIGINT NOT NULL,
    events_id BIGINT NOT NULL
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

CREATE TABLE event
(
    id        BIGINT NOT NULL,
    name      VARCHAR(255),
    image_url VARCHAR(255),
    bar_id    BIGINT,
    CONSTRAINT pk_event PRIMARY KEY (id)
);

CREATE TABLE service
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255),
    icon_url VARCHAR(255),
    CONSTRAINT pk_service PRIMARY KEY (id)
);

ALTER TABLE bar_events
    ADD CONSTRAINT uc_bar_events_events UNIQUE (events_id);

ALTER TABLE bar
    ADD CONSTRAINT FK_BAR_ON_BAR_TYPE FOREIGN KEY (bar_type_id) REFERENCES bar_type (id);

ALTER TABLE event
    ADD CONSTRAINT FK_EVENT_ON_BAR FOREIGN KEY (bar_id) REFERENCES bar (id);

ALTER TABLE bar_events
    ADD CONSTRAINT fk_bareve_on_bar FOREIGN KEY (bar_id) REFERENCES bar (id);

ALTER TABLE bar_events
    ADD CONSTRAINT fk_bareve_on_event FOREIGN KEY (events_id) REFERENCES event (id);

ALTER TABLE bar_services
    ADD CONSTRAINT fk_barser_on_bar FOREIGN KEY (bars_id) REFERENCES bar (id);

ALTER TABLE bar_services
    ADD CONSTRAINT fk_barser_on_service FOREIGN KEY (services_id) REFERENCES service (id);