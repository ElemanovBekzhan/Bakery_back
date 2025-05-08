package org.example.subd.repository;

import org.example.subd.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PositionRepo extends JpaRepository<Position, UUID> {
}
