package az.spring.bankapplication.accountServiceTest;

import az.spring.bankapplication.dto.request.AccountUpdateRequest;
import az.spring.bankapplication.dto.response.AccountUpdateResponse;
import az.spring.bankapplication.entity.Account;
import az.spring.bankapplication.mapper.AccountMapper;
import az.spring.bankapplication.repository.AccountRepository;
import az.spring.bankapplication.service.accountService.AccountUpdateService;
import az.spring.bankapplication.util.AccountUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;

import static az.spring.bankapplication.enums.AccountStatus.INACTIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class AccountUpdateServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountUpdateService accountUpdateService;

    @Test
    public void testUpdateAccount_whenUserExistById_shouldReturnSuccess() {
        Long accountId = 1L;
        Account account = AccountUtil.activeAccount();
        Account updatedAccount = AccountUtil.activeAccount();
        updatedAccount.setStatus(INACTIVE);
        Account savedAccount = AccountUtil.activeAccount();
        savedAccount.setStatus(INACTIVE);
        AccountUpdateResponse expectResponse = AccountUtil.accountUpdateResponse();
        AccountUpdateRequest request = AccountUtil.updateRequest();

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(accountMapper.updateAccountFromUpdateRequest(request, account)).thenReturn(updatedAccount);
        when(accountRepository.save(updatedAccount)).thenReturn(savedAccount);
        when(accountMapper.mapAccountToUpdateResponse(savedAccount)).thenReturn(expectResponse);

        AccountUpdateResponse actual = accountUpdateService.updateAccount(accountId, request);
        assertNotNull(actual);
        assertEquals(actual, expectResponse);
        assertEquals(actual.getId(), expectResponse.getId());

        verify(accountRepository, times(1)).findById(accountId);
        verify(accountMapper, times(1)).updateAccountFromUpdateRequest(request, account);
        verify(accountRepository, times(1)).save(updatedAccount);
        verify(accountMapper, times(1)).mapAccountToUpdateResponse(savedAccount);

    }
}
