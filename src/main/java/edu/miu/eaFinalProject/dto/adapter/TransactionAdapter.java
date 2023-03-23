package edu.miu.eaFinalProject.dto.adapter;


import edu.miu.eaFinalProject.domain.Transaction;
import edu.miu.eaFinalProject.dto.TransactionDTO;
import java.util.List;

public class TransactionAdapter {
    public static TransactionDTO getTransactionDTOFromTransaction(Transaction transaction) {
        if(transaction == null)
            return null;
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setDateTime(transaction.getDateTime());
        transactionDTO.setMemberDTO(MemberAdapter.getMemberDTOFromMember(transaction.getMember()));
        transactionDTO.setLocationDTO(LocationAdapter.getLocationDTOFromLocation(transaction.getLocation()));
        transactionDTO.setMembershipDTO(MembershipAdapter.getMembershipDTOFromMembership(transaction.getMembership()));
        return transactionDTO;
    }

    public static Transaction getTransactionFromTransactionDTO(TransactionDTO transactionDTO) {
        if(transactionDTO == null) return null;
        Transaction transaction = new Transaction();

        transaction.setId(transactionDTO.getId());
        transaction.setDateTime(transactionDTO.getDateTime());
        transaction.setMember(MemberAdapter.getMemberFromMemberDTO(transactionDTO.getMemberDTO()));
        transaction.setLocation(LocationAdapter.getLocationFromLocationDTO(transactionDTO.getLocationDTO()));
        transaction.setMembership(MembershipAdapter.getMembershipFromMembershipDTO(transactionDTO.getMembershipDTO()));

        return transaction;
    }

    public static List<TransactionDTO> getTransactionDTOListFromTransactionList(List<Transaction> transactions) {
        if(transactions == null) return null;
        return transactions.stream().map(transaction -> getTransactionDTOFromTransaction(transaction)).toList();
    }

    public static List<Transaction> getTransactionListFromTransactionDTOList(List<TransactionDTO> transactionDTOS) {
        if(transactionDTOS == null) return null;
        return transactionDTOS.stream().map(transactionDTO -> getTransactionFromTransactionDTO(transactionDTO)).toList();
    }
}
