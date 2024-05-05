package az.spring.bankapplication.accountServiceTest;

import az.spring.bankapplication.entity.Account;
import az.spring.bankapplication.exception.AccountNotFoundException;
import az.spring.bankapplication.repository.AccountRepository;
import az.spring.bankapplication.service.accountService.AccountDeleteService;
import az.spring.bankapplication.util.AccountUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class AccountDeleteServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountDeleteService accountDeleteService;

    @Test
    public void testDeleteAccount_whenAccountExists() {
        Long accountId = 1L;
        Account account = AccountUtil.activeAccount();

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        accountDeleteService.deleteAccount(accountId);

        verify(accountRepository, times(1)).findById(accountId);
        verify(accountRepository, times(1)).delete(account);
    }

    @Test
    public void testDeleteAccount_whenAccountDoesNotExists() {
        Long accountId = 1L;
        Account account = AccountUtil.activeAccount();

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        Assertions.assertThrows(AccountNotFoundException.class, () -> accountDeleteService.deleteAccount(accountId));

        verify(accountRepository, times(1)).findById(accountId);
        verify(accountRepository, never()).delete(account);
    }

    @Test
    public void testDeActiveAccount_whenAccountExists() {
        Long accountId = 1L;
        Account account = AccountUtil.activeAccount();

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(accountRepository.save(account)).thenReturn(account);

        accountDeleteService.deActiveAccount(accountId);

        verify(accountRepository, times(1)).findById(accountId);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testDeActiveAccount_whenAccountDoesNotExists() {
        Long accountId = 1L;
        Account account = AccountUtil.activeAccount();

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        Assertions.assertThrows(AccountNotFoundException.class, () -> accountDeleteService.deleteAccount(accountId));

        verify(accountRepository, times(1)).findById(accountId);
        verify(accountRepository, never()).save(account);
    }

}
