package edu.miu.eaFinalProject.Controller;

import edu.miu.eaFinalProject.dto.TimeSlotDTO;
import edu.miu.eaFinalProject.service.TimeSlotService;
import edu.miu.eaFinalProject.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    @PostMapping("/timesloths")
    public ResponseEntity<?> addMemberShip(@RequestBody TimeSlotDTO timeSlotDTO){
        TimeSlotDTO addTimeSlot = timeSlotService.saveTimeSloth(timeSlotDTO);
        return new ResponseEntity<>(addTimeSlot, HttpStatus.OK);
    }

    @GetMapping("/timesloths")
    public ResponseEntity<?> getAllMemberShip(){
        List<TimeSlotDTO> timeSlotDTOList = timeSlotService.getAllTimeSlotDTO();
        return new ResponseEntity<>(timeSlotDTOList,HttpStatus.OK);
    }

    @DeleteMapping("/timesloths/{id}")
    public ResponseEntity<?> deleteTimeSloth(@PathVariable long id){
        timeSlotService.deleteTimeSlotDTO(id);
        return new ResponseEntity<>(new ResponseMessage("Delete Successfully"),HttpStatus.OK);
    }

    @GetMapping("/timesloths/{id}")
    public ResponseEntity<?> getTimeSlothById(@PathVariable long id){
        TimeSlotDTO timeSlotDTO = timeSlotService.getTimeSlotDTOByID(id);
        return new ResponseEntity<>(timeSlotDTO,HttpStatus.OK);
    }

    @PutMapping("/timesloths/{id}")
    public ResponseEntity<?> updateTimeSlothById(@RequestBody TimeSlotDTO timeSlotDTO, @PathVariable long id){
        TimeSlotDTO updateTimeSlotDTO = timeSlotService.updateTimeSlotDTO(id, timeSlotDTO);
        return new ResponseEntity<>(updateTimeSlotDTO,HttpStatus.OK);
    }


}
