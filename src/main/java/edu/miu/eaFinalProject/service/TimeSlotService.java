package edu.miu.eaFinalProject.service;

import edu.miu.eaFinalProject.dto.TimeSlotDTO;

import java.util.List;

public interface TimeSlotService {
    TimeSlotDTO saveTimeSloth(TimeSlotDTO timeSlotDTO);

    List<TimeSlotDTO> getAllTimeSlotDTO();

    void deleteTimeSlotDTO(long id);

    TimeSlotDTO getTimeSlotDTOByID(long id);

    TimeSlotDTO updateTimeSlotDTO(long id, TimeSlotDTO timeSlotDTO);


}
