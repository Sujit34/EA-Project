package edu.miu.eaFinalProject.service;

import edu.miu.eaFinalProject.domain.Transaction;
import edu.miu.eaFinalProject.dto.TransactionDTO;

import java.util.Collection;
import java.util.List;

public interface TransactionService {
    public TransactionDTO createTransaction(TransactionDTO transactionDTO);
    public List<TransactionDTO> getAllTransactions();
    public TransactionDTO getTransactionById(long transactionId);
    public void deleteTransaction(long transactionId);
    public TransactionDTO updateTransaction(TransactionDTO transactionDTO);
    public Collection<TransactionDTO> getTransactionsByMember(long memberId);
}
