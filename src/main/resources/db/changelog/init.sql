--liquibase formatted sql
--changeset TestUsers_sql:1

CREATE SCHEMA task_tracker;

CREATE TABLE task_tracker.task (
  id  SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  due_date DATE NOT NULL,
  status VARCHAR(255)
);

CREATE TABLE task_tracker.category_task (
  id  SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description TEXT NOT NULL
);

ALTER TABLE task_tracker.task
ADD COLUMN category_id INT,
ADD CONSTRAINT fk_task_category
FOREIGN KEY (category_id)
REFERENCES task_tracker.category_task (id)
ON DELETE SET NULL;


ALTER TABLE task_tracker.task ADD COLUMN planned_time_in_minutes INTEGER;
ALTER TABLE task_tracker.task ADD COLUMN elapsed_time_in_minutes INTEGER;

ALTER TABLE task_tracker.category_task ADD COLUMN planned_time_in_minutes INTEGER;
ALTER TABLE task_tracker.category_task ADD COLUMN elapsed_time_in_minutes INTEGER;

CREATE TABLE task_tracker.customer (
  id  SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);

ALTER TABLE task_tracker.category_task
ADD COLUMN customer_id INT,
ADD CONSTRAINT fk_category_customer
FOREIGN KEY (customer_id)
REFERENCES task_tracker.customer (id)
ON DELETE SET NULL;

ALTER TABLE task_tracker.category_task ADD COLUMN  due_date DATE NOT NULL;
ALTER TABLE task_tracker.category_task ADD COLUMN  execution_date DATE NOT NULL;
ALTER TABLE task_tracker.task ADD COLUMN  execution_date DATE NOT NULL;


ALTER TABLE task_tracker.category_task  ADD COLUMN  customer_code INTEGER NOT NULL;
ALTER TABLE task_tracker.task  ADD COLUMN  category_code INTEGER NOT NULL;

ALTER TABLE task_tracker.task  ADD COLUMN  customer_code INTEGER NOT NULL;

ALTER TABLE task_tracker.task
ADD COLUMN customer_id INT,
ADD CONSTRAINT fk_task_customer
FOREIGN KEY (customer_id)
REFERENCES task_tracker.customer (id)
ON DELETE SET NULL;

