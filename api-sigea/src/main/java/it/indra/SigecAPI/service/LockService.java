package it.indra.SigecAPI.service;

import java.sql.Timestamp;
import java.util.Optional;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.indra.SigecAPI.entity.Lock;
import it.indra.SigecAPI.exception.LockedResourceException;
import it.indra.SigecAPI.repository.LockRepository;

@Service
public class LockService {
	
	@Autowired
	private LockRepository lockRepository;
	
	synchronized public void lockRisorsa(Long itemId, Long ownerId, Class<?> entityClass) throws Exception, LockedResourceException{
		String tableName = entityClass.getAnnotation(Table.class).name();
		Lock lock = null;
		
		Optional<Lock> optionalLock = lockRepository.findOneByItemIdAndTableName(itemId, tableName);
		if (optionalLock.isPresent()) {
			lock = optionalLock.get();
			if (lock.getOwnerId().longValue() != ownerId.longValue()) {
				Timestamp currentTime = new Timestamp(System.currentTimeMillis());
				long diffmin = currentTime.getTime() - lock.getDataLock().getTime();
				System.out.println(diffmin/ (60 * 1000));
				if (diffmin/ (60 * 1000) < 1) {
					throw new LockedResourceException("Attendere: risorsa occupata da un altro utente");
				}else{
					lock.setOwnerId(ownerId);
				}
			}
		}else {
			lock = new Lock();
			lock.setItemId(itemId);
			lock.setOwnerId(ownerId);
			lock.setTableName(tableName);
		}
		lock.setDataLock(null);
		lockRepository.save(lock);
	}

}
