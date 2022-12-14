package internship.paymentSystem.backend.controllers;

import internship.paymentSystem.backend.DTOs.BaseObjectDto;
import internship.paymentSystem.backend.DTOs.CurrentUserDto;
import internship.paymentSystem.backend.models.Account;
import internship.paymentSystem.backend.models.AccountHistory;
import internship.paymentSystem.backend.models.Person;
import internship.paymentSystem.backend.models.User;
import internship.paymentSystem.backend.models.enums.StatusEnum;
import internship.paymentSystem.backend.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    IAccountService accountService;

    @GetMapping()
    public List<Account> getAccounts(){
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Optional<Account> GetAccountById(@PathVariable Long id){
        return accountService.findAccountById(id);
    }

    @GetMapping("/history")
    public List<AccountHistory> getHistoryOfAccounts() {
        return accountService.getHistoryOfAccounts();
    }

    @GetMapping("/history/user/{currentUserId}")
    public List<AccountHistory> getAccountsHistoryOfUser(@PathVariable Long currentUserId){
        return accountService.getAccountsHistoryOfUser(currentUserId);
    }

    @GetMapping("/history/{accountId}")
    public List<AccountHistory> getHistoryOfAccount(@PathVariable Long accountId){
        return accountService.getHistoryByAccountId(accountId);
    }

    @GetMapping("/status/{filterStatus}")
    public List<Account> getAccountsByStatus(@PathVariable StatusEnum filterStatus){
        return accountService.getAccountsByStatus(filterStatus);
    }

    @GetMapping("/valid")
    public Set<Account> getValidAccounts(){
        return accountService.getValidAccounts();
    }

    @GetMapping("/by-iban/{iban}")
    public Account getAccountByIban(@PathVariable String iban){
        return accountService.getAccountByIban(iban);
    }

    @GetMapping("/user/{currentUserId}")
    public List<Account> getAccountsOfUser(@PathVariable Long currentUserId){
        return accountService.getAccountsOfUser(currentUserId);
    }

    @PostMapping("/{currentUserId}")
    public Account createAccount(@RequestBody Account accountDetails, @PathVariable Long currentUserId){
        return accountService.createAccount(accountDetails,currentUserId);
    }

    @PutMapping()
    public ResponseEntity<Account> updateAccount(@RequestBody BaseObjectDto<Account> accountDto){
        Account updatedAccount = accountService.updateAccount(accountDto.getCurrentUserDto().getObjectId(),
                accountDto.getCurrentUserDto().getCurrentUserId(), accountDto.getObject());
        return ResponseEntity.ok(updatedAccount);
    }

    @PutMapping("/approve")
    public ResponseEntity<?> approveAccount(@RequestBody CurrentUserDto currentUserDto){
        try{
            Account activeAccount = accountService.approveAccount(currentUserDto.getObjectId(),
                    currentUserDto.getCurrentUserId());
            return ResponseEntity.ok(activeAccount);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/reject")
    public ResponseEntity<?> rejectAccount(@RequestBody CurrentUserDto currentUserDto){
        try{
            Account rejectedAccount = accountService.rejectAccount(currentUserDto.getObjectId(),
                    currentUserDto.getCurrentUserId());
            return ResponseEntity.ok(rejectedAccount);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/delete")
    public ResponseEntity<Account> deleteAccount(@RequestBody CurrentUserDto currentUserDto){
        Account deletedAccount = accountService.deleteAccount(currentUserDto.getObjectId(),
                currentUserDto.getCurrentUserId());
        return ResponseEntity.ok(deletedAccount);
    }
}
