package edu.miu.eaFinalProject.service.Impl;

import edu.miu.eaFinalProject.domain.Transaction;
import edu.miu.eaFinalProject.dto.TransactionDTO;
import edu.miu.eaFinalProject.dto.adapter.TransactionAdapter;
import edu.miu.eaFinalProject.repository.TransactionRepository;
import edu.miu.eaFinalProject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction = TransactionAdapter.getTransactionFromTransactionDTO(transactionDTO);
        transactionRepository.save(transaction);
        return TransactionAdapter.getTransactionDTOFromTransaction(transaction);
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return TransactionAdapter.getTransactionDTOListFromTransactionList(transactions);
    }

    @Override
    public TransactionDTO getTransactionById(long transactionId) {

        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NotFoundException("Transaction not found with id: " + transactionId));

        return TransactionAdapter.getTransactionDTOFromTransaction(transaction);
    }

    @Override
    public void deleteTransaction(long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
        .orElseThrow(() -> new NotFoundException(transactionId+ " was not found"));
        transactionRepository.delete(transaction);

    }

   @Override
   public TransactionDTO  updateTransaction( TransactionDTO transactionDTO) {
       Transaction transaction;
       Optional<Transaction> transactionOptional = transactionRepository.findById(transactionDTO.getId());
           if(transactionOptional.isPresent()){
               transaction=transactionOptional.get();
               transaction = TransactionAdapter.getTransactionFromTransactionDTO(transactionDTO);
               transactionRepository.save(transaction);
           } else{
               throw new NotFoundException("Transaction not found with id: " + transactionDTO.getId());
           }
           return TransactionAdapter.getTransactionDTOFromTransaction(transaction);

   }

    @Override
    public Collection<TransactionDTO> getTransactionsByMember(long memberId) {
        Collection<Transaction> transactions = transactionRepository.findAllByMember_Id(memberId);
        Collection<TransactionDTO> transactionDTOS = new ArrayList<>();
        for(Transaction t: transactions) {
            transactionDTOS.add(TransactionAdapter.getTransactionDTOFromTransaction(t));
        }
        return transactionDTOS;
    }
}
