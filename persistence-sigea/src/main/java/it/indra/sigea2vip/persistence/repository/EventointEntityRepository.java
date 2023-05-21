package it.indra.sigea2vip.persistence.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.indra.sigea2vip.persistence.entity.EventointEntity;

@Repository
public interface EventointEntityRepository extends JpaRepository<EventointEntity, Long> {

	Set<EventointEntity> findAllByIdEvento(long idEvento);

}
