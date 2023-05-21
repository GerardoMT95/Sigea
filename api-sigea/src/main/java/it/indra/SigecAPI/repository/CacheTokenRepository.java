package it.indra.SigecAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.indra.SigecAPI.entity.CacheToken;

@Repository
@Transactional
public interface CacheTokenRepository extends JpaRepository<CacheToken, String> {

}