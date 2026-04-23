package re.cntt4.ss14.repository;

import re.cntt4.ss14.model.TransactionHistory;

import java.util.List;

public interface ITransactionRepository {

    // Chuyển tiền
    void bankingMoney(Long senderId,Long receiveId,Double amount);
    // Nạp / Rút tiền
    void rechangeMoney(Long receiveId, Double money);

    List<TransactionHistory> getHistories();

}
