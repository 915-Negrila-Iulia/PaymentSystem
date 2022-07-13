package com.example.backend.controller;

import com.example.backend.model.*;
import com.example.backend.service.IAuditService;
import com.example.backend.service.IBalanceService;
import com.example.backend.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private IBalanceService balanceService;

    @Autowired
    private IAuditService auditService;

    @Autowired
    private AuthController authController;

    @GetMapping()
    public List<Transaction> getTransactions(){
        return transactionService.getAllTransactions();
    }

    @PostMapping()
    public Transaction createTransaction(@RequestBody Transaction transactionDetails){
        transactionDetails.setStatus(StatusEnum.APPROVE);
        Transaction transaction = transactionService.saveTransaction(transactionDetails);
        Long currentUserId = Long.parseLong(this.authController.currentUser());
        Audit audit = new Audit(transaction.getId(),ObjectTypeEnum.TRANSACTION,OperationEnum.CREATE,currentUserId);
        auditService.saveAudit(audit);
        return transaction;
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<Transaction> approveTransaction(@PathVariable Long id){
        Transaction transaction = transactionService.findTransactionById(id)
                .orElseThrow(() -> new RuntimeException("Transaction with id " + id + " not found"));
        transaction.setStatus(StatusEnum.ACTIVE);
        Transaction activeTransaction = transactionService.saveTransaction(transaction);
        balanceService.updateTotalAmount(transaction.getId());
        Long currentUserId = Long.parseLong(this.authController.currentUser());
        Audit audit = new Audit(transaction.getId(),ObjectTypeEnum.TRANSACTION,OperationEnum.APPROVE,currentUserId);
        auditService.saveAudit(audit);
        return ResponseEntity.ok(activeTransaction);
    }

    @PutMapping("/deposit/{accountId}/{amount}")
    public ResponseEntity<Transaction> depositTransaction(@PathVariable Long accountId, @PathVariable Double amount){
        Transaction transaction = transactionService.depositTransaction(accountId, amount);
        balanceService.updateAvailableAmount(accountId, transaction.getId());
        Long currentUserId = Long.parseLong(this.authController.currentUser());
        Audit audit = new Audit(transaction.getId(),ObjectTypeEnum.TRANSACTION,OperationEnum.CREATE,currentUserId);
        auditService.saveAudit(audit);
        return ResponseEntity.ok(transaction);
    }

    @PutMapping("/withdrawal/{accountId}/{amount}")
    public ResponseEntity<Transaction> withdrawalTransaction(@PathVariable Long accountId, @PathVariable Double amount){
        Transaction transaction = transactionService.withdrawalTransaction(accountId, amount);
        balanceService.updateAvailableAmount(accountId, transaction.getId());
        Long currentUserId = Long.parseLong(this.authController.currentUser());
        Audit audit = new Audit(transaction.getId(),ObjectTypeEnum.TRANSACTION,OperationEnum.CREATE,currentUserId);
        auditService.saveAudit(audit);
        return ResponseEntity.ok(transaction);
    }

    @PutMapping("/transfer/{accountId}/{targetId}/{amount}")
    public ResponseEntity<Transaction> transferTransaction(@PathVariable Long accountId, @PathVariable Long targetId,
                                                           @PathVariable Double amount){
        Transaction transaction = transactionService.transferTransaction(accountId, targetId, amount);
        balanceService.updateAvailableAmount(accountId, transaction.getId());
        Long currentUserId = Long.parseLong(this.authController.currentUser());
        Audit audit = new Audit(transaction.getId(),ObjectTypeEnum.TRANSACTION,OperationEnum.CREATE,currentUserId);
        auditService.saveAudit(audit);
        return ResponseEntity.ok(transaction);
    }
}
