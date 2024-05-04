package az.spring.bankapplication.service.accountService;

import az.spring.bankapplication.entity.Account;
import az.spring.bankapplication.exception.AccountNotFoundException;
import az.spring.bankapplication.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.spring.bankapplication.enums.AccountStatus.*;

@Service
@RequiredArgsConstructor
public class AccountDeleteService {

    private final AccountRepository accountRepository;

    public void deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        accountRepository.delete(account);
    }

    public void deActiveAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        account.setStatus(INACTIVE);
        accountRepository.save(account);
    }

}
