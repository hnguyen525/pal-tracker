package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{
    HashMap<Long,TimeEntry> map = new HashMap() ;
    long id = 0L;
    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        id = id+1;
        TimeEntry createdTimeEntry = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());

        map.put(id, createdTimeEntry);
        return createdTimeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {

        return map.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return map.values().stream().collect(Collectors.toList());

    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {

        TimeEntry updEntry = find(id);
        if (updEntry != null) {
            updEntry.setUserId(timeEntry.getUserId());
            updEntry.setDate(timeEntry.getDate());
            updEntry.setHours(timeEntry.getHours());
            updEntry.setProjectId(timeEntry.getProjectId());

        }
        return updEntry;
    }

    @Override
    public void delete(long timeEntryId) {
        if (map.containsKey(timeEntryId)) {
            map.remove(timeEntryId);
        }
    }
}
