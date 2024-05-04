package az.spring.bankapplication.service.accountService;

import az.spring.bankapplication.entity.Account;
import az.spring.bankapplication.exception.AccountActivationException;
import az.spring.bankapplication.exception.AccountNotFoundException;
import az.spring.bankapplication.exception.InsufficientBalanceException;
import az.spring.bankapplication.exception.SameAccountException;
import az.spring.bankapplication.dto.request.TransferRequest;
import az.spring.bankapplication.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static az.spring.bankapplication.enums.AccountStatus.INACTIVE;

@Service
@RequiredArgsConstructor
public class AccountTransferService {

    private final AccountRepository accountRepository;

    public void transfer(TransferRequest transferRequest) {
        Account fromAccount = accountRepository.findByAccountNumber(transferRequest.getFromAccountNumber()).orElseThrow(AccountNotFoundException::new);
        Account toAccount = accountRepository.findByAccountNumber(transferRequest.getToAccountNumber()).orElseThrow(AccountNotFoundException::new);

        if (fromAccount.getAccountNumber().equals(toAccount.getAccountNumber()))
            throw new SameAccountException();
        if (INACTIVE.equals(toAccount.getStatus()))
            throw new AccountActivationException();
        if (INACTIVE.equals(fromAccount.getStatus()))
            throw new AccountActivationException();
        if (Objects.equals(fromAccount.getBalance(), BigDecimal.ZERO)) {
            throw new InsufficientBalanceException();
        } else if (fromAccount.getBalance().compareTo(transferRequest.getAmount()) < 0)
            throw new InsufficientBalanceException();

        fromAccount.setBalance(fromAccount.getBalance().subtract(transferRequest.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(transferRequest.getAmount()));
        accountRepository.saveAll(List.of(toAccount, fromAccount));
    }

}
