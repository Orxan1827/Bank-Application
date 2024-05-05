package az.spring.bankapplication.accountServiceTest;

import az.spring.bankapplication.dto.request.AccountReadActiveRequest;
import az.spring.bankapplication.dto.response.AccountReadResponse;
import az.spring.bankapplication.entity.Account;
import az.spring.bankapplication.mapper.AccountMapper;
import az.spring.bankapplication.repository.AccountRepository;
import az.spring.bankapplication.service.accountService.AccountReadAllService;
import az.spring.bankapplication.util.AccountUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.ArrayList;
import java.util.List;

import static az.spring.bankapplication.enums.AccountStatus.ACTIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class AccountReadAllServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountReadAllService accountReadAllService;

    @Test
    public void testGetAllActiveAccounts_whenReadActiveRequest_shouldReturnAccountReadResponse() {
        AccountReadActiveRequest request = AccountUtil.accountReadActiveRequest();
        List<AccountReadResponse> expectResponseList = AccountUtil.accountReadResponses();
        List<Account> accountList = AccountUtil.activeAccountList();

        when(accountRepository.findAllByFkUserIdAndStatus(request.getFkUserId(), ACTIVE)).thenReturn(accountList);
        when(accountMapper.mapAccountToReadResponse(any(Account.class))).thenReturn(expectResponseList.get(0), expectResponseList.get(1));

        List<AccountReadResponse> actualResponseList = accountReadAllService.getAllActiveAccounts(request);

        assertNotNull(actualResponseList);
        assertEquals(expectResponseList.size(), actualResponseList.size());
        assertEquals(expectResponseList.get(0), actualResponseList.get(0));

        verify(accountRepository, times(1)).findAllByFkUserIdAndStatus(request.getFkUserId(), ACTIVE);
        verify(accountMapper, times(2)).mapAccountToReadResponse(any(Account.class));
    }

    @Test
    public void testGetAllAccounts_whenReadRequest_shouldReturnAccountReadResponse() {
        AccountReadActiveRequest request = AccountUtil.accountReadActiveRequest();
        List<AccountReadResponse> expectResponseList = AccountUtil.accountReadResponses();
        List<Account> accountList = AccountUtil.accountList();

        when(accountRepository.findAll()).thenReturn(accountList);
        when(accountMapper.mapAccountToReadResponse(any(Account.class))).thenReturn(expectResponseList.get(0), expectResponseList.get(1));

        List<AccountReadResponse> actualResponseList = accountReadAllService.getAllAccounts(request);

        assertNotNull(actualResponseList);
        assertEquals(expectResponseList.size(), actualResponseList.size());
        assertEquals(expectResponseList.get(0), actualResponseList.get(0));

        verify(accountRepository, times(1)).findAll();
        verify(accountMapper, times(2)).mapAccountToReadResponse(any(Account.class));
    }

}
