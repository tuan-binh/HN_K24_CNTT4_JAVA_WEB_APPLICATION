package re.cntt4.ss14.repository.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import re.cntt4.ss14.model.Account;
import re.cntt4.ss14.model.TransactionHistory;
import re.cntt4.ss14.repository.ITransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements ITransactionRepository {
    private final SessionFactory sessionFactory;

    @Override
    public void bankingMoney(Long senderId, Long receiveId, Double amount) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        // 1. Tìm người gửi dựa vào id và người nhận
        Account sender = session.get(Account.class, senderId);
        Account receive = session.get(Account.class, receiveId);

        // 2. Kiểm tra người gửi có đủ tiền hay không
        if (sender.getBalance() < amount) {
            System.err.println("Không đủ tiền");

            tx.rollback();
        } else {
            // 3. Cập nhật tiền từng tài khoản (người nhận +) (người gửi -)
            // Trừ tiền tài khoản người gửi
            sender.setBalance(sender.getBalance() - amount);
            // Công tiền tài khoản người nhận
            receive.setBalance(receive.getBalance() + amount);

            // Lưu lại thông khi thay đổi
            // Dùng merge hay persist hay remove là mọi người sẽ phải sử dụng Transaction thì mới lưu
            session.merge(sender);
            session.merge(receive);

            // Tạo lịch sử giao dịch
            TransactionHistory transactionHistory = new TransactionHistory();
            transactionHistory.setAccount(sender);
            transactionHistory.setAmount(amount);
            transactionHistory.setMessage("Chuyển tiền");
            transactionHistory.setTransactionTime(LocalDateTime.now());
            // Lưu lại lịch sử chuyển tiền
            session.persist(transactionHistory);

            tx.commit();
        }

        // 4. Sử dụng transaction
        // 1. Mở transaction thông qua beginTransaction
        // 2. Nếu thành công -> commit
        // 3. Nếu thất bại -> rollback
    }

    @Override
    public void rechangeMoney(Long receiveId, Double money) {

    }

    @Override
    public List<TransactionHistory> getHistories() {
        Session session = sessionFactory.openSession();
        // Sử dụng HQL
        List<TransactionHistory> histories =
                session.createQuery(
                        "FROM TransactionHistory",
                        TransactionHistory.class
                ).list();
        session.close();
        return histories;
    }
}
