package com.streamnet.service.impl;

import com.streamnet.commons.event.ListsNotificationDto;
import com.streamnet.commons.event.UpdateListsEvent;
import com.streamnet.model.Lists;
import com.streamnet.repository.ListsRepository;
import com.streamnet.service.ListsHandlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListsHandlerServiceImpl implements ListsHandlerService {

    private final ListsRepository listsRepository;

    @Override
    @Transactional
    public void handleUpdateList(UpdateListsEvent listsEvent) {
        listsRepository.findById(listsEvent.getId())
                .map(lists -> {
                    lists.setListName(lists.getListName());
                    return lists;
                });
    }

    @Override
    @Transactional
    public Lists getOrCreateList(ListsNotificationDto lists) {
        return listsRepository.findById(lists.getId())
                .orElseGet(() -> {
                    Lists newList = new Lists();
                    newList.setId(lists.getId());
                    newList.setListName(lists.getListName());
                    return listsRepository.save(newList);
                });
    }
}
