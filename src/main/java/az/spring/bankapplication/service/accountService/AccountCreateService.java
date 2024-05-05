package az.spring.bankapplication.service.accountService;

import az.spring.bankapplication.entity.Account;
import az.spring.bankapplication.entity.User;
import az.spring.bankapplication.exception.UserNotFoundException;
import az.spring.bankapplication.mapper.AccountMapper;
import az.spring.bankapplication.dto.request.AccountCreateRequest;
import az.spring.bankapplication.dto.response.AccountCreateResponse;
import az.spring.bankapplication.repository.AccountRepository;
import az.spring.bankapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCreateService {

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private final AccountMapper accountMapper;

    public AccountCreateResponse createAccount(AccountCreateRequest createRequest) {
        User user = userRepository.findById(createRequest.getFkUserId()).orElseThrow(UserNotFoundException::new);
        Account account = accountMapper.mapCreateRequestToAccount(createRequest);
        account.setFkUserId(user.getId());
        Account savedAccount = accountRepository.save(account);
        return accountMapper.mapAccountToCreateResponse(savedAccount);
    }

}
