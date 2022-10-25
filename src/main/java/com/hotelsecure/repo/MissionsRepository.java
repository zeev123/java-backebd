package com.hotelsecure.repo;

import com.hotelsecure.tables.Missions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionsRepository extends JpaRepository<Missions,Long> {
}
