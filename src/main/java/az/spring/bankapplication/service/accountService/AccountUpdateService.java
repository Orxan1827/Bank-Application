package az.spring.bankapplication.service.accountService;

import az.spring.bankapplication.entity.Account;
import az.spring.bankapplication.exception.AccountNotFoundException;
import az.spring.bankapplication.mapper.AccountMapper;
import az.spring.bankapplication.dto.request.AccountUpdateRequest;
import az.spring.bankapplication.dto.response.AccountUpdateResponse;
import az.spring.bankapplication.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountUpdateService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    public AccountUpdateResponse UpdateAccount(Long accountId, AccountUpdateRequest updateRequest) {
        Account account = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        Account updatedAccount = accountMapper.updateAccountFromUpdateRequest(updateRequest, account);
        Account savedAccount = accountRepository.save(updatedAccount);
        return accountMapper.mapAccountToUpdateResponse(savedAccount);
    }

}
