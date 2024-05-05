package az.spring.bankapplication.accountServiceTest;

import az.spring.bankapplication.dto.request.AccountCreateRequest;
import az.spring.bankapplication.dto.response.AccountCreateResponse;
import az.spring.bankapplication.entity.Account;
import az.spring.bankapplication.entity.User;
import az.spring.bankapplication.mapper.AccountMapper;
import az.spring.bankapplication.repository.AccountRepository;
import az.spring.bankapplication.repository.UserRepository;
import az.spring.bankapplication.service.accountService.AccountCreateService;
import az.spring.bankapplication.util.AccountUtil;
import az.spring.bankapplication.util.UserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.math.BigDecimal;
import java.util.Optional;

import static az.spring.bankapplication.enums.AccountStatus.ACTIVE;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class AccountCreateServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountCreateService accountCreateService;

    @Test
    public void testCreateAccount_whenCreateRequest_shouldReturnCreateResponse() {
        AccountCreateResponse expect = AccountUtil.accountCreateResponse();
        AccountCreateRequest request = AccountUtil.accountCreateRequest();
        Account account = AccountUtil.activeAccount();
        User user = UserUtil.user();

        when(userRepository.findById(request.getFkUserId())).thenReturn(of(user));
        when(accountMapper.mapCreateRequestToAccount(request)).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(account);
        when(accountMapper.mapAccountToCreateResponse(account)).thenReturn(expect);

        AccountCreateResponse actual = accountCreateService.createAccount(request);

        assertNotNull(actual);
        assertEquals(actual, expect);
        assertEquals(actual.getId(), expect.getId());

        verify(userRepository, times(1)).findById(request.getFkUserId());
        verify(accountMapper, times(1)).mapCreateRequestToAccount(request);
        verify(accountRepository, times(1)).save(account);
        verify(accountMapper, times(1)).mapAccountToCreateResponse(account);
    }

}
