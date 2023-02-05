package ru.ibra.task_tracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ibra.task_tracker.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}
