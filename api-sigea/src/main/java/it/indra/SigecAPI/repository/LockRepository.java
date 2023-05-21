package it.indra.SigecAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.indra.SigecAPI.entity.Lock;

public interface LockRepository extends JpaRepository<Lock, Long> {
	Optional<Lock> findOneByItemIdAndTableName(Long itemId, String tableName);
}
