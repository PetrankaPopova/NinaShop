package diplomna.service;

import diplomna.model.service.EventAddServiceModel;

import java.util.List;

public interface EventService {
    EventAddServiceModel createEvent(EventAddServiceModel serviceModel, String username);

    List<EventAddServiceModel> findAllEvents();

    List<EventAddServiceModel> findEventByUsername(String name);

    EventAddServiceModel findById(String id);

    EventAddServiceModel editEvent(String id, EventAddServiceModel eventServiceModel);

    void deleteEvent(String id);
}
