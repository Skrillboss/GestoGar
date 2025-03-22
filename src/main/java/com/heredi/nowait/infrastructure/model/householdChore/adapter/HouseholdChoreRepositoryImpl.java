package com.heredi.nowait.infrastructure.model.householdChore.adapter;

import com.heredi.nowait.application.exception.AppErrorCode;
import com.heredi.nowait.application.exception.AppException;
import com.heredi.nowait.domain.householdChore.model.householdChore;
import com.heredi.nowait.domain.householdChore.port.ItemRepository;
import com.heredi.nowait.infrastructure.model.home.entity.HomeEntity;
import com.heredi.nowait.infrastructure.model.home.jpa.HomeJPARepository;
import com.heredi.nowait.infrastructure.model.householdChore.entity.householdChoreEntity;
import com.heredi.nowait.infrastructure.model.householdChore.jpa.ItemJPARepository;
import com.heredi.nowait.infrastructure.model.householdChore.mapper.HouseholdChoreEntityMapper;
import com.heredi.nowait.infrastructure.model.queue.entity.QueueEntity;
import com.heredi.nowait.infrastructure.model.queue.jpa.QueueJPARepository;
import com.heredi.nowait.infrastructure.model.queue.mapper.QueueEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Repository
public class HouseholdChoreRepositoryImpl implements ItemRepository {

    private final HomeJPARepository homeJPARepository;

    private final ItemJPARepository itemJPARepository;

    private final QueueJPARepository queueJPARepository;

    @Autowired
    private HouseholdChoreEntityMapper householdChoreEntityMapper;

    @Autowired
    private QueueEntityMapper queueEntityMapper;

    HouseholdChoreRepositoryImpl(HomeJPARepository homeJPARepository, ItemJPARepository itemJPARepository, QueueJPARepository queueJPARepository) {
        this.homeJPARepository = homeJPARepository;
        this.itemJPARepository = itemJPARepository;
        this.queueJPARepository = queueJPARepository;
    }


    @Transactional
    @Override
    public householdChore create(Long businessId, householdChore householdChore) {
        HomeEntity homeEntity = this.homeJPARepository.findById(businessId)
                .orElseThrow(() -> new NoSuchElementException("Business not found by Id: " + businessId));

        householdChoreEntity householdChoreEntity = this.householdChoreEntityMapper.toHouseholdChoreEntity(householdChore);

        QueueEntity queueEntity = queueEntityMapper.toQueueEntity(householdChore.getQueue());

        queueEntity.setItem(householdChoreEntity);

        householdChoreEntity.setHomeEntity(homeEntity);
        householdChoreEntity.setQueue(queueEntity);

        return this.householdChoreEntityMapper.toHouseholdChore(this.itemJPARepository.save(householdChoreEntity));
    }

    @Override
    public householdChore getItemById(Long itemId){
        householdChoreEntity householdChoreEntity = this.itemJPARepository.findById(itemId)
                .orElseThrow(() -> new AppException(
                        AppErrorCode.ITEM_NOT_FOUND_BY_ID,
                        "getItemById",
                        "itemId: " + itemId,
                        HttpStatus.NOT_FOUND));

        return this.householdChoreEntityMapper.toHouseholdChore(householdChoreEntity);
    }
}
