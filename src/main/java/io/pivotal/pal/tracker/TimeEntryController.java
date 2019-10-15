package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    TimeEntryRepository repo;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.repo = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        TimeEntry myEntry = repo.create(timeEntry);
        return new ResponseEntity(myEntry, HttpStatus.CREATED);

    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry myEntry = repo.find(timeEntryId);
        ResponseEntity<TimeEntry> myList;
        if (myEntry != null) {
         myList = new ResponseEntity(myEntry,HttpStatus.OK);
         }
        else {
            myList = new ResponseEntity(myEntry,HttpStatus.NOT_FOUND);

        }
        return myList;
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        ResponseEntity<List<TimeEntry>> myList;
        myList = new ResponseEntity(repo.list(),HttpStatus.OK);
        return myList;
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntry) {
        TimeEntry timeEntry1 = repo.update(timeEntryId, timeEntry);
        ResponseEntity myEntity;
        if (timeEntry1 != null) {
           myEntity = new ResponseEntity(timeEntry1, HttpStatus.OK);
        } else {
            myEntity = new ResponseEntity(timeEntry1,HttpStatus.NOT_FOUND);
        }
        return myEntity;
    }


    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        repo.delete(timeEntryId);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }
}
