-- Tabla de posiciones (cargos en el hotel)
CREATE TABLE positions (

    id_position BIGINT UNSIGNED AUTO_INCREMENT,
    position_employee BOOLEAN DEFAULT FALSE,
    position_name VARCHAR(100) NOT NULL,
    position_description TEXT NOT NULL,

    -- CONSTRAINTS
    CONSTRAINT PK_position PRIMARY KEY (id_position),
    CONSTRAINT UQ_position_name UNIQUE (position_name),

    -- INDEXES
    INDEX idx_position_name (position_name)

);

-- Tabla de empleados
CREATE TABLE employees (

    id_employee BIGINT UNSIGNED AUTO_INCREMENT,
    id_position BIGINT UNSIGNED NOT NULL,
    employee_names VARCHAR(100) NOT NULL,
    employee_surnames VARCHAR(100) NOT NULL,
    employee_gender ENUM ('MASCULINO', 'FEMENINO', 'OTRO') NOT NULL,
    employee_birth_date DATE NOT NULL,
    employee_email VARCHAR(100) NOT NULL,
    employee_phone VARCHAR(20),
    active BOOLEAN DEFAULT TRUE,

    -- CONSTRAINTS
    CONSTRAINT PK_employee PRIMARY KEY (id_employee),
    CONSTRAINT FK_employee_position FOREIGN KEY (id_position)
    REFERENCES positions (id_position)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT UQ_employee_email UNIQUE (employee_email),
    CONSTRAINT UQ_employee_phone UNIQUE (employee_phone),

    -- INDEXES
    INDEX idx_employee_names_surnames (employee_names, employee_surnames),
    INDEX idx_employee_email (employee_email)

);

-- Tabla de clientes
CREATE TABLE clients (

    id_client BIGINT UNSIGNED AUTO_INCREMENT,
    id_position BIGINT UNSIGNED NOT NULL,
    client_names VARCHAR(100) NOT NULL,
    client_surnames VARCHAR(100) NOT NULL,
    client_email VARCHAR(100) NOT NULL,
    client_phone VARCHAR(20),

    -- CONSTRAINTS
    CONSTRAINT PK_client PRIMARY KEY (id_client),
    CONSTRAINT FK_client_position FOREIGN KEY (id_position)
    REFERENCES positions (id_position)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT UQ_client_email UNIQUE (client_email),
    CONSTRAINT UQ_client_phone UNIQUE (client_phone),

    -- INDEXES
    INDEX idx_client_names_surnames (client_names, client_surnames),
    INDEX idx_client_email (client_email)

);

-- Tabla de usuarios
CREATE TABLE users (
    id_user BIGINT UNSIGNED AUTO_INCREMENT,
    id_user_client BIGINT UNSIGNED,
    id_user_employee BIGINT UNSIGNED,
    user_type ENUM  ('CLIENT', 'EMPLOYEE') NOT NULL,
    user_username VARCHAR(200) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- CONSTRAINTS
    CONSTRAINT PK_id_user PRIMARY KEY (id_user),
    CONSTRAINT FK_id_user_client FOREIGN KEY (id_user_client)
    REFERENCES clients (id_client)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT FK_id_user_employee FOREIGN KEY (id_user_employee)
    REFERENCES employees (id_employee)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT UQ_user_username UNIQUE (user_username),

    -- INDEXES
    INDEX idx_user_type (user_type),
    INDEX idx_user_client (id_user_client),
    INDEX idx_user_employee (id_user_employee)

);

-- Tabla de cuartos
CREATE TABLE rooms (

    id_room BIGINT UNSIGNED AUTO_INCREMENT,
    room_name VARCHAR(200) NOT NULL,
    room_amenities TEXT NOT NULL,
    room_description TEXT NOT NULL,
    room_capacity INT NOT NULL,
    room_price_per_night DECIMAL(8,2) NOT NULL DEFAULT 0.00,
    room_img TEXT NOT NULL,
    room_status ENUM('DISPONIBLE', 'OCUPADO', 'MANTENIMIENTO') DEFAULT 'DISPONIBLE',

    -- CONSTRAINTS
    CONSTRAINT PK_room PRIMARY KEY (id_room),

    -- INDEXES
    INDEX idx_room_name (room_name),
    INDEX idx_room_capacity (room_capacity),
    INDEX idx_room_price_per_night (room_price_per_night),
    INDEX idx_room_status (room_status)

);


-- Tabla de reservas
CREATE TABLE reservations (

    id_reservation BIGINT UNSIGNED AUTO_INCREMENT,
    id_client BIGINT UNSIGNED NOT NULL,
    id_room BIGINT UNSIGNED NOT NULL,
    reservation_status ENUM('REGISTRADA','ACTIVA', 'CERRADA', 'CANCELADA') DEFAULT 'REGISTRADA',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    reservation_check_in DATETIME NOT NULL,
    reservation_check_out DATETIME NOT NULL,

    -- CONSTRAINTS
    CONSTRAINT PK_reservation PRIMARY KEY (id_reservation),
    CONSTRAINT FK_reservation_client FOREIGN KEY (id_client)
    REFERENCES clients (id_client)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT FK_reservation_room FOREIGN KEY (id_room)
    REFERENCES rooms (id_room)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

    -- INDEXES
    INDEX idx_client (id_client),
    INDEX idx_id_room (id_room)

);

-- Tabla de métodos de pago
CREATE TABLE payment_methods (

    id_payment_method BIGINT UNSIGNED AUTO_INCREMENT,
    payment_method_name VARCHAR(200) NOT NULL,
    payment_method_description TEXT,

    -- CONSTRAINTS
    CONSTRAINT PK_payment_method PRIMARY KEY (id_payment_method),
    CONSTRAINT UQ_payment_method_name UNIQUE (payment_method_name)

);

-- Tabla de pagos
CREATE TABLE payments (

    id_payment BIGINT UNSIGNED AUTO_INCREMENT,
    id_reservation BIGINT UNSIGNED NOT NULL,
    id_payment_method BIGINT UNSIGNED NOT NULL,
    payment_price DECIMAL(8,2) NOT NULL,
    payment_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- CONSTRAINTS
    CONSTRAINT PK_payment PRIMARY KEY (id_payment),
    CONSTRAINT FK_payment_reservation FOREIGN KEY (id_reservation)
    REFERENCES reservations (id_reservation)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT FK_payment_payment_method FOREIGN KEY (id_payment_method)
    REFERENCES payment_methods (id_payment_method)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

    -- INDEXES
    INDEX idx_reservation (id_reservation),
    INDEX idx_payment_method (id_payment_method),
    INDEX idx_payment_price (payment_price)

);

-- Tabla para servicios
CREATE TABLE services (

    id_service BIGINT UNSIGNED AUTO_INCREMENT,
    service_name VARCHAR(200) NOT NULL,
    service_description TEXT,

    -- CONSTRAINTS
    CONSTRAINT PK_service PRIMARY KEY (id_service),
    CONSTRAINT UQ_service_name UNIQUE (service_name)

);
-- Tabla de empleados de servicio
CREATE TABLE service_employees (

    id_service_employee BIGINT UNSIGNED AUTO_INCREMENT,
    id_employee BIGINT UNSIGNED NOT NULL,
    employee_service_status ENUM ('DISPONIBLE', 'OCUPADO') NOT NULL DEFAULT 'DISPONIBLE',

    -- CONSTRAINTS
    CONSTRAINT PK_service_employee PRIMARY KEY (id_service_employee),
    CONSTRAINT FK_service_employee_employee FOREIGN KEY (id_employee)
    REFERENCES employees (id_employee)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

    -- INDEXES
    INDEX idx_employee (id_employee),
    INDEX idx_employee_service_status (employee_service_status)

);

-- Tabla para registros de servicios
CREATE TABLE service_registrations (

    id_service_registration BIGINT UNSIGNED AUTO_INCREMENT,
    id_reservation BIGINT UNSIGNED NOT NULL,
    id_service BIGINT UNSIGNED NOT NULL,
    id_service_employee BIGINT UNSIGNED NOT NULL,
    service_registration_employee_assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- CONSTRAINTS
    CONSTRAINT PK_service_registration PRIMARY KEY (id_service_registration),
    CONSTRAINT FK_service_registration_reservation FOREIGN KEY (id_reservation)
    REFERENCES reservations (id_reservation)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT FK_service_registration_service FOREIGN KEY (id_service)
    REFERENCES services (id_service)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT FK_service_registration_service_employee FOREIGN KEY (id_service_employee)
    REFERENCES service_employees (id_service_employee)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

    -- INDEXES
    INDEX idx_reservation (id_reservation),
    INDEX idx_service (id_service),
    INDEX idx_service_employee (id_service_employee)
);

-- Tabla para logs de registros de servicio
CREATE TABLE service_registration_logs (

    id_service_registration_log BIGINT UNSIGNED AUTO_INCREMENT,
    id_service_registration BIGINT UNSIGNED NOT NULL,
    service_registration_log_status ENUM ('PENDIENTE', 'EN_PROGRESO', 'COMPLETADO', 'CANCELADO') NOT NULL DEFAULT 'PENDIENTE',
    service_registration_log_status_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- CONSTRAINT
    CONSTRAINT PK_service_registration_log PRIMARY KEY (id_service_registration_log),
    CONSTRAINT FK_service_registration_log_service_registration FOREIGN KEY (id_service_registration)
    REFERENCES service_registrations (id_service_registration)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

    -- INDEXES
    INDEX idx_service_registration (id_service_registration),
    INDEX idx_service_registration_log_status (service_registration_log_status)

);

-- Reseñas de clientes
CREATE TABLE reviews (
    id_review BIGINT UNSIGNED AUTO_INCREMENT,
    id_reservation BIGINT UNSIGNED NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    review_rating INT NOT NULL,
    review_comment TEXT,

    -- CONSTRAINTS
    CONSTRAINT PK_review PRIMARY KEY (id_review),
    CONSTRAINT FK_review_reservation FOREIGN KEY (id_reservation)
    REFERENCES reservations (id_reservation)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT CHK_review_rating CHECK (review_rating BETWEEN 1 AND 10),

    -- INDEXES
    INDEX idx_reservation (id_reservation),
    INDEX idx_review_rating (review_rating)
);

-- Tabla de subscripcion a noticias
CREATE TABLE news_subscriptions (

    id_news_subscription BIGINT UNSIGNED AUTO_INCREMENT,
    news_subscription_email VARCHAR(100) NOT NULL,
    subscribed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- CONSTRAINTS
    CONSTRAINT PK_news_subscription PRIMARY KEY (id_news_subscription),
    CONSTRAINT UQ_news_subscription_email UNIQUE (news_subscription_email)

);

-- Solicitudes del formulario de contacto
CREATE TABLE contact_services (

    id_contact_service BIGINT UNSIGNED AUTO_INCREMENT,
    id_client BIGINT UNSIGNED NOT NULL,
    contact_service_type ENUM ('RECLAMO_RESERVAS', 'RECLAMO_EMPLEADO_SERVICIOS', 'RECLAMO_SERVICIOS', 'OTROS') NOT NULL,
    contact_service_reclaim TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- CONSTRAINTS
    CONSTRAINT PK_contact_service PRIMARY KEY (id_contact_service),
    CONSTRAINT FK_contact_service_client FOREIGN KEY (id_client)
    REFERENCES clients (id_client)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

    -- INDEXES
    INDEX idx_client (id_client),
    INDEX idx_contact_service_type (contact_service_type)

);

-- Tabla de registros de servicios de contacto
CREATE TABLE contact_service_registrations(

    id_contact_service_registration BIGINT UNSIGNED AUTO_INCREMENT,
    id_contact_service BIGINT UNSIGNED NOT NULL,
    id_employee BIGINT UNSIGNED NOT NULL,
    opened_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    closed_at TIMESTAMP ,

    -- CONSTRAINTS
    CONSTRAINT PK_contact_service_registration PRIMARY KEY (id_contact_service_registration),
    CONSTRAINT FK_contact_service_registration_contact_service FOREIGN KEY (id_contact_service)
    REFERENCES contact_services (id_contact_service)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT FK_contact_service_registration_employee FOREIGN KEY (id_employee)
    REFERENCES employees (id_employee)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

    -- INDEXES
    INDEX idx_employee (id_employee),
    INDEX idx_contact_service (id_contact_service)

);
