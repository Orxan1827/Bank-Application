package az.spring.bankapplication.service.accountService;

import az.spring.bankapplication.mapper.AccountMapper;
import az.spring.bankapplication.dto.request.AccountReadActiveRequest;
import az.spring.bankapplication.dto.request.AccountReadRequest;
import az.spring.bankapplication.dto.response.AccountReadResponse;
import az.spring.bankapplication.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountReadAllService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    public List<AccountReadResponse> getAllActiveAccounts(AccountReadActiveRequest readRequest) {
        return accountRepository.findAllByFkUserIdAndStatus(readRequest.getFkUserId(), readRequest.getStatus())
                .stream().map(accountMapper::mapAccountToReadResponse)
                .toList();
    }

    public List<AccountReadResponse> getAllAccounts(AccountReadRequest readRequest) {
       return accountRepository.findAll().stream()
                .filter(account -> account.getFkUserId().equals(readRequest.getFkUserId()))
                .map(accountMapper::mapAccountToReadResponse)
                .toList();
    }

}
