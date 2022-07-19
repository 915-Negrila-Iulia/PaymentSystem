package internship.paymentSystem.backend.services.interfaces;

import internship.paymentSystem.backend.models.Balance;

import java.util.List;

public interface IBalanceService {

    Balance saveBalance(Balance balance);

    Balance getCurrentBalance(Long id);

    List<Balance> getAllBalances();

    List<Balance> getAllBalancesByAccountId(Long id);

    void updateAvailableAmount(Long accountId, Long transactionId);

    void updateTotalAmount(Long transactionId);

    Balance createInitialBalance(Long accountId);

    void cancelAmountChanges(Long transactionId);

}