package com.heredi.nowait.application.model.item.mapper;

import com.heredi.nowait.application.model.item.dto.in.ItemRequestDTO;
import com.heredi.nowait.application.model.item.dto.out.HouseholdChoresResponseDTO;
import com.heredi.nowait.application.model.queue.mapper.QueueMapper;
import com.heredi.nowait.domain.householdChore.model.householdChore;
import com.heredi.nowait.domain.householdChore.model.householdChore.ItemStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    private final QueueMapper queueMapper;

    public ItemMapper(QueueMapper queueMapper){
        this.queueMapper = queueMapper;
    }

    // Convierte de Item a ItemDTO
    public HouseholdChoresResponseDTO toItemResponseDTO(householdChore householdChore) {
        if (householdChore == null) {
            return null;
        }

        HouseholdChoresResponseDTO dto = new HouseholdChoresResponseDTO();
        dto.setId(householdChore.getId().toString());
        dto.setName(householdChore.getName());
        dto.setDescription(householdChore.getDescription());
        dto.setBusinessId(householdChore.getHome().getId().toString());
        dto.setBusinessName(householdChore.getHome().getName());
        dto.setRating(householdChore.getRating());
        dto.setMainImagePath(householdChore.getMainImagePath());
        dto.setSecondaryImagePath(householdChore.getSecondaryImagePath());
        dto.setStatus(householdChore.getStatus().name());
        dto.setQueueResponseDTO(queueMapper.toQueueResponseDTO(householdChore.getQueue()));


        return dto;
    }

    public List<HouseholdChoresResponseDTO> toItemsDTO(List<householdChore> householdChores){
        if(householdChores == null){
            return null;
        }

        return householdChores.stream()
                .map(this::toItemResponseDTO)
                .collect(Collectors.toList());
    }

    // Convierte de ItemDTO a Item
    public householdChore toItem(ItemRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        householdChore householdChore = new householdChore();
        householdChore.setName(dto.getName());
        householdChore.setDescription(dto.getDescription());
        householdChore.setRating(0.0);
        householdChore.setMainImagePath(dto.getMainImagePath());
        householdChore.setSecondaryImagePath(dto.getSecondaryImagePath());
        householdChore.setStatus(ItemStatus.valueOf(dto.getStatus()));
        householdChore.setQueue(queueMapper.toQueue(dto.getQueueRequestDTO()));

        return householdChore;
    }

    public List<householdChore> toItems(List<ItemRequestDTO> itemsDTO){
        if(itemsDTO ==  null){
            return null;
        }

        return itemsDTO.stream()
                .map(this::toItem)
                .collect(Collectors.toList());
    }
}
