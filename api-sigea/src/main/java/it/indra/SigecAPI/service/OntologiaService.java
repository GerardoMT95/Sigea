package it.indra.SigecAPI.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.indra.SigeaCommons.model.MezzoModel;
import it.indra.SigeaCommons.model.ProdottoModel;
import it.indra.SigeaCommons.model.TipologiaAttivitaModel;
import it.indra.SigeaCommons.model.TipologiaMIBACTModel;
import it.indra.SigeaCommons.model.ValoreAttrattivitaTuristicaModel;
import it.indra.SigecAPI.entity.Mezzo;
import it.indra.SigecAPI.entity.Prodotto;
import it.indra.SigecAPI.entity.TipologiaAttivita;
import it.indra.SigecAPI.entity.TipologiaMIBACT;
import it.indra.SigecAPI.entity.ValoreAttrattivitaTuristica;
import it.indra.SigecAPI.mapper.MezzoMapper;
import it.indra.SigecAPI.mapper.ProdottoMapper;
import it.indra.SigecAPI.mapper.TipologiaAttivitaMapper;
import it.indra.SigecAPI.mapper.TipologiaMIBACTMapper;
import it.indra.SigecAPI.mapper.ValoreAttrattivitaTuristicaMapper;
import it.indra.SigecAPI.repository.MezzoRepository;
import it.indra.SigecAPI.repository.ProdottoRepository;
import it.indra.SigecAPI.repository.TipologiaAttivitaRepository;
import it.indra.SigecAPI.repository.TipologiaMIBACTRepository;
import it.indra.SigecAPI.repository.ValoreAttrattivitaRepository;

@Service
public class OntologiaService {
	
	@Autowired
	MezzoRepository mezzoRepository;
	
	@Autowired
	ProdottoRepository prodottoRepository;
	
	@Autowired
	TipologiaAttivitaRepository attivitaRepository;
	
	@Autowired
	TipologiaMIBACTRepository mibactRepository;
	
	@Autowired
	ValoreAttrattivitaRepository valoreRepository;
	
	@Autowired
	MezzoMapper mezzoMapper;
	
	@Autowired
	ProdottoMapper prodottoMapper;
	
	@Autowired
	TipologiaAttivitaMapper attivitaMapper;
	
	@Autowired
	TipologiaMIBACTMapper mibactMapper;
	
	@Autowired
	ValoreAttrattivitaTuristicaMapper valoreMapper;
	
	public Set<MezzoModel> getMezzi(){
		List<Mezzo> mezzi = mezzoRepository.findAll();
		Set<Mezzo> targetSet = new HashSet<>(mezzi);
		Set<MezzoModel> mezziModel = mezzoMapper.entityToDtoSet(targetSet);
		return mezziModel;
	}
	
	public Set<ProdottoModel> getProdotti(){
		List<Prodotto> prodotti = prodottoRepository.findAll();
		Set<Prodotto> targetSet = new HashSet<>(prodotti);
		Set<ProdottoModel> prodottiModel = prodottoMapper.entityToDtoSet(targetSet);
		return prodottiModel;
	}
	
	public Set<TipologiaAttivitaModel> getTipologieAttivita(){
		List<TipologiaAttivita> tipologie = attivitaRepository.findAll();
		Set<TipologiaAttivita> targetSet = new HashSet<>(tipologie);
		Set<TipologiaAttivitaModel> tipologieModel = attivitaMapper.entityToDtoSet(targetSet);
		return tipologieModel;
	}
	
	public Set<TipologiaMIBACTModel> getTipologieMIBACT(){
		List<TipologiaMIBACT> tipologie = mibactRepository.findAll();
		Set<TipologiaMIBACT> targetSet = new HashSet<>(tipologie);
		Set<TipologiaMIBACTModel> tipologieModel = mibactMapper.entityToDtoSet(targetSet);
		return tipologieModel;
	}
	
	public Set<ValoreAttrattivitaTuristicaModel> getValoriAttrattivita(){
		List<ValoreAttrattivitaTuristica> valori = valoreRepository.findAll();
		Set<ValoreAttrattivitaTuristica> targetSet = new HashSet<>(valori);
		Set<ValoreAttrattivitaTuristicaModel> valoriModel = valoreMapper.entityToDtoSet(targetSet);
		return valoriModel;
	}

}
