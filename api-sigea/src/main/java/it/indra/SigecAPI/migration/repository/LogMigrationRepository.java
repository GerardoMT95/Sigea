package it.indra.SigecAPI.migration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.indra.SigecAPI.migration.entity.LogMigration;

@Repository
@Transactional
public interface LogMigrationRepository extends JpaRepository<LogMigration, Long>{


	LogMigration findAllByEventoIdSigea(Long eventoId);

	LogMigration findAllByEventoIdVipAndEventoIdSigea(Long idVip, Long idSigea);

}
