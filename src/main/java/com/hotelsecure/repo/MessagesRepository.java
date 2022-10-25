package com.hotelsecure.repo;

import com.hotelsecure.tables.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
}
