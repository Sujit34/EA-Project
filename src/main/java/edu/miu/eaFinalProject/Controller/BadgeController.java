package edu.miu.eaFinalProject.Controller;

import edu.miu.eaFinalProject.dto.BadgeDTO;
import edu.miu.eaFinalProject.exception.ApiRequestException;
import edu.miu.eaFinalProject.service.BadgeService;
import edu.miu.eaFinalProject.service.Impl.BadgeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/badge")
public class BadgeController {
    @Autowired

    private BadgeService badgeService;
    @GetMapping("/{id}")
public ResponseEntity<?> getBadge(@PathVariable long id ){
        BadgeDTO badgeDTO= badgeService.getBadgeById(id);
        return new ResponseEntity<>(badgeDTO, HttpStatus.OK);

}
@PostMapping("/add")
public ResponseEntity<?> createBadge(@RequestBody BadgeDTO badgeDTO){
    badgeService.createBadge(badgeDTO);
        return new ResponseEntity<>(badgeDTO, HttpStatus.OK);
}
@PutMapping("/update")
public ResponseEntity<?>updateBadge(@PathVariable long badgeId,@RequestBody BadgeDTO badgeDTO){
        badgeService.updateBadge(badgeId,badgeDTO);
        return new ResponseEntity<>(badgeDTO,HttpStatus.OK);

}
@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletBadge(@PathVariable long id ){
        BadgeDTO badgeDTO = badgeService.deleteBadgeById(id);
        return new ResponseEntity<>(badgeDTO,HttpStatus.OK);
}

    @PostMapping("/scanBadge")
    public ResponseEntity<?> scanBadge(@RequestParam String badgeId, @RequestParam long planId, @RequestParam long locationId) {
        String response ="";
        try {
            response = badgeService.scanBadge(badgeId, planId, locationId);
        } catch(Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }


}
