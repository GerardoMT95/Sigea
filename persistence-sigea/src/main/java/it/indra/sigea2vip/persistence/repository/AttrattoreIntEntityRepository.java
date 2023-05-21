package it.indra.sigea2vip.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.indra.sigea2vip.persistence.entity.AttrattoreIntEntity;


public interface AttrattoreIntEntityRepository extends JpaRepository<AttrattoreIntEntity, Long> {

	AttrattoreIntEntity findAllByidAttrattoreAndIdLingua(long idattrattore, String string);



}
