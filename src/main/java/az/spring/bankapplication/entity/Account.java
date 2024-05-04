package az.spring.bankapplication.entity;

import az.spring.bankapplication.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import static az.spring.bankapplication.enums.AccountStatus.ACTIVE;

@Table(name = "accounts")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private BigDecimal balance;

    private Long fkUserId;

    @PrePersist
    public void setStatus() {
        if (status == null) {
            status = ACTIVE;
        }
    }

}
