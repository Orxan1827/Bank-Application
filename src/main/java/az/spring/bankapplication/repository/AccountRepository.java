package az.spring.bankapplication.repository;

import az.spring.bankapplication.entity.Account;
import az.spring.bankapplication.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByFkUserIdAndStatus(Long fkUserId, AccountStatus status);

//    @Query("""
//              select new az.spring.bankapplication.wrapper.AccountWrapper(a.id,a.accountNumber,a.status,a.balance,a.fkUserId) from Account a where a.status = 'ACTIVE'
//            """)
//    List<AccountWrapper> findByBookStatusA();

    Optional<Account> findByAccountNumber(String accountNumber);

}