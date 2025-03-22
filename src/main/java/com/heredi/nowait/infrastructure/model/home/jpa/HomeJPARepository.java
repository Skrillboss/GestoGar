package com.heredi.nowait.infrastructure.model.home.jpa;

import com.heredi.nowait.infrastructure.model.home.entity.HomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeJPARepository extends JpaRepository<HomeEntity, Long> {
}
