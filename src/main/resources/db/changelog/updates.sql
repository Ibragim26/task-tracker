--liquibase formatted sql

--changeset updates_by_Ibra:15
ALTER TABLE task_tracker.task ADD COLUMN  prior_task VARCHAR;
ALTER TABLE task_tracker.category_task ADD COLUMN  prior_category VARCHAR;
ALTER TABLE task_tracker.category_task ADD COLUMN status VARCHAR(255);
ALTER TABLE task_tracker.category_task ADD COLUMN create_date DATE;
ALTER TABLE task_tracker.category_task ADD COLUMN update_date DATE;
ALTER TABLE task_tracker.task ADD COLUMN create_date DATE;
ALTER TABLE task_tracker.task ADD COLUMN update_date DATE;
