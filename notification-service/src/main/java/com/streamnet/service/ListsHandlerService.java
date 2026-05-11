package com.streamnet.service;

import com.streamnet.commons.event.ListsNotificationDto;
import com.streamnet.commons.event.UpdateListsEvent;
import com.streamnet.model.Lists;

public interface ListsHandlerService {

    void handleUpdateList(UpdateListsEvent listsEvent);

    Lists getOrCreateList(ListsNotificationDto lists);
}
