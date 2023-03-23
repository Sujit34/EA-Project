package edu.miu.eaFinalProject.dto.adapter;


import edu.miu.eaFinalProject.domain.TimeSlot;
import edu.miu.eaFinalProject.dto.TimeSlotDTO;
import java.util.List;

public class TimeSlotAdapter {
    public static TimeSlotDTO getTimeSlotDTOFromTimeSlot(TimeSlot timeSlot){
        if(timeSlot == null) return null;
        TimeSlotDTO timeSlotDTO = new TimeSlotDTO();

        timeSlotDTO.setId(timeSlot.getId());
        timeSlotDTO.setStartTime(timeSlot.getStartTime());
        timeSlotDTO.setEndTime(timeSlot.getEndTime());
        timeSlotDTO.setDay(timeSlot.getDay());

        return timeSlotDTO;
    }

    public static TimeSlot getTimeSlotFromTimeSlotDTO(TimeSlotDTO timeSlotDTO){
        if(timeSlotDTO == null) return null;
        TimeSlot timeSlot = new TimeSlot();

        timeSlot.setId(timeSlotDTO.getId());
        timeSlot.setStartTime(timeSlotDTO.getStartTime());
        timeSlot.setEndTime(timeSlotDTO.getEndTime());
        timeSlot.setDay(timeSlotDTO.getDay());

        return timeSlot;
    }

    public static List<TimeSlotDTO> getTimeSlotDTOListFromTimeSlotList (List<TimeSlot> timeSlots){
        if(timeSlots == null) return null;
        return timeSlots.stream().map(timeSlot -> getTimeSlotDTOFromTimeSlot(timeSlot)).toList();
    }

    public static List<TimeSlot> getTimeSlotListFromTimeSlotDTO (List<TimeSlotDTO> timeSlotDTOS){
        if(timeSlotDTOS == null) return null;
        return timeSlotDTOS.stream().map(timeSlotDTO -> getTimeSlotFromTimeSlotDTO(timeSlotDTO)).toList();
    }
}
