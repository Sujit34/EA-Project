package edu.miu.eaFinalProject.service.Impl;

import edu.miu.eaFinalProject.domain.TimeSlot;
import edu.miu.eaFinalProject.dto.TimeSlotDTO;
import edu.miu.eaFinalProject.dto.adapter.TimeSlotAdapter;
import edu.miu.eaFinalProject.exception.ApiRequestException;
import edu.miu.eaFinalProject.repository.TimeSlotRepository;
import edu.miu.eaFinalProject.service.TimeSlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TimeSlotImpl implements TimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlothRepository;

    @Override
    public TimeSlotDTO saveTimeSloth(TimeSlotDTO timeSlotDTO) {
        log.info("timeSlotDTOIMPL = " + timeSlotDTO);
        TimeSlot timeSlot = new TimeSlot();
//        LocalDate startTime = timeSlotDTO.getStartTime();
//        LocalDate endTime = timeSlotDTO.getEndTime();
        timeSlot.setStartTime(timeSlotDTO.getStartTime());
        timeSlot.setEndTime(timeSlotDTO.getEndTime());
        timeSlot.setDay(timeSlotDTO.getDay());
        timeSlothRepository.save(timeSlot);

        return TimeSlotAdapter.getTimeSlotDTOFromTimeSlot(timeSlot);
    }

    @Override
    public List<TimeSlotDTO> getAllTimeSlotDTO() {
        List<TimeSlot> timeSlotList = timeSlothRepository.findAll();
        if (timeSlotList.isEmpty()){
            throw new ApiRequestException("Data is empty", HttpStatus.BAD_REQUEST);
        }
        return TimeSlotAdapter.getTimeSlotDTOListFromTimeSlotList(timeSlotList);
    }

    @Override
    public void deleteTimeSlotDTO(long id) {
        Optional<TimeSlot> membershipOptional = timeSlothRepository.findById(id);
        if (membershipOptional.isPresent()) {
            TimeSlot membership =  membershipOptional.get();
            timeSlothRepository.delete(membership);
        }else {
            throw new ApiRequestException("Timeslot with id = " + id + " cannot found", HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public TimeSlotDTO getTimeSlotDTOByID(long id) {
        TimeSlot timeSlot;
        Optional<TimeSlot> membershipOptional = timeSlothRepository.findById(id);
        if (membershipOptional.isPresent()) {
            timeSlot = membershipOptional.get();
        }else {
            throw new ApiRequestException("Timeslot with id = " + id + " cannot found", HttpStatus.NOT_FOUND);
        }

        return TimeSlotAdapter.getTimeSlotDTOFromTimeSlot(timeSlot);
    }

    @Override
    public TimeSlotDTO updateTimeSlotDTO(long id, TimeSlotDTO timeSlotDTO) {
        TimeSlot membership;
        Optional<TimeSlot> membershipOptional = timeSlothRepository.findById(id);
        if (membershipOptional.isPresent()) {
            membership = membershipOptional.get();
            membership.setStartTime(timeSlotDTO.getStartTime());
            membership.setEndTime(timeSlotDTO.getEndTime());
            membership.setDay(timeSlotDTO.getDay());
            timeSlothRepository.save(membership);
        }else {
            throw new ApiRequestException("Timeslot with id = " + id + " cannot updated", HttpStatus.NOT_FOUND);
        }

        return TimeSlotAdapter.getTimeSlotDTOFromTimeSlot(membership);
    }
}
