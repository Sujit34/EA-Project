package edu.miu.eaFinalProject.Controller;

import edu.miu.eaFinalProject.dto.PlanDTO;
import edu.miu.eaFinalProject.dto.TransactionDTO;
import edu.miu.eaFinalProject.exception.ApiRequestException;
import edu.miu.eaFinalProject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("")
    public ResponseEntity<?> getAllTransactions(){
        List<TransactionDTO> transactionDTOs = null;
        try {
            transactionDTOs = transactionService.getAllTransactions();
        } catch (Exception e) {
            throw new ApiRequestException("Error getting transaction.!!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<List<TransactionDTO>>(transactionDTOs, HttpStatus.OK);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransactionById(@PathVariable long transactionId){
        TransactionDTO transactionDTO = null;
        try {
            transactionDTO = transactionService.getTransactionById(transactionId);
        } catch (NullPointerException nullPointerException) {
            throw new ApiRequestException("No transaction found with this id.!!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ApiRequestException("Error getting transaction by id.!!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<TransactionDTO>(transactionDTO, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?>createTransaction(@RequestBody TransactionDTO transactionDTO){
        try {
            transactionService.createTransaction(transactionDTO);
        } catch (Exception e) {
            throw new ApiRequestException("Error while creating transaction.!!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updateTransaction(@RequestBody TransactionDTO transactionDTO){
        try {
            transactionService.updateTransaction(transactionDTO);
        } catch (Exception e) {
            throw new ApiRequestException("Error while updating transaction.!!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("transactionId") long transactionId){
        try {
            transactionService.deleteTransaction(transactionId);
        } catch (Exception e) {
            throw new ApiRequestException("Error while deleting transaction.!!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
