package re.cntt4.ss14.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_histories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "message")
    private String message;

    @Column(name = "transaction_time")
    private LocalDateTime transactionTime;

    /**
     * fetch LAZY -> Lười ko lấy giá trị lên luôn
     * fetch EAGER -> Lấy giá trị lên luôn khi đối tượng vừa được nạp
     * */
    /**
     * cascade là hiệu ứng lan truyền
     * */
    @ManyToOne
    // Default One ở cuối là fetch là EAGER mặc định
    // Default Many ở cuối là fetch là LAZY mặc định
    @JoinColumn(name = "account_id")
    private Account account;

}
