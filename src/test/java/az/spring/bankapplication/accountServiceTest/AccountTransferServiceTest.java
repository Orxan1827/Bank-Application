package az.spring.bankapplication.accountServiceTest;

import az.spring.bankapplication.dto.request.TransferRequest;
import az.spring.bankapplication.entity.Account;
import az.spring.bankapplication.exception.AccountActivationException;
import az.spring.bankapplication.exception.InsufficientBalanceException;
import az.spring.bankapplication.exception.SameAccountException;
import az.spring.bankapplication.repository.AccountRepository;
import az.spring.bankapplication.service.accountService.AccountTransferService;
import az.spring.bankapplication.util.AccountUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static az.spring.bankapplication.enums.AccountStatus.INACTIVE;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class AccountTransferServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountTransferService accountTransferService;

    @Test
    public void testAccountTransferService_whenAllConditionsSuccess() {
        Account fromAccount = AccountUtil.fromAccount();
        Account toAccount = AccountUtil.toAccount();
        TransferRequest request = AccountUtil.transferRequest();

        when(accountRepository.findByAccountNumber(fromAccount.getAccountNumber())).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber(toAccount.getAccountNumber())).thenReturn(Optional.of(toAccount));
        when(accountRepository.saveAll(List.of(toAccount, fromAccount))).thenReturn(List.of(toAccount, fromAccount));

        accountTransferService.transfer(request);

        verify(accountRepository, times(1)).findByAccountNumber(fromAccount.getAccountNumber());
        verify(accountRepository, times(1)).findByAccountNumber(toAccount.getAccountNumber());
        verify(accountRepository, times(1)).saveAll(List.of(toAccount, fromAccount));
    }

    @Test
    public void testAccountTransferService_whenAccountsIsSame_shouldReturnSameAccountException() {
        Account fromAccount = AccountUtil.fromAccount();
        Account toAccount = AccountUtil.toAccount();
        toAccount.setAccountNumber("12345");
        TransferRequest request = AccountUtil.transferRequest();
        request.setToAccountNumber("12345");
        when(accountRepository.findByAccountNumber(fromAccount.getAccountNumber())).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber(toAccount.getAccountNumber())).thenReturn(Optional.of(toAccount));
        when(accountRepository.saveAll(List.of(toAccount, fromAccount))).thenReturn(List.of(toAccount, fromAccount));

        Assertions.assertThrows(SameAccountException.class, () -> accountTransferService.transfer(request));

        verify(accountRepository, times(2)).findByAccountNumber(fromAccount.getAccountNumber());
        verify(accountRepository, times(2)).findByAccountNumber(toAccount.getAccountNumber());
        verify(accountRepository, never()).saveAll(List.of(toAccount, fromAccount));
    }

    @Test
    public void testAccountTransferService_whenAccountBalanceInsufficient_shouldReturnInsufficientBalanceException() {
        Account fromAccount = AccountUtil.fromAccount();
        fromAccount.setBalance(new BigDecimal("0.4"));
        Account toAccount = AccountUtil.toAccount();
        TransferRequest request = AccountUtil.transferRequest();

        when(accountRepository.findByAccountNumber(fromAccount.getAccountNumber())).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber(toAccount.getAccountNumber())).thenReturn(Optional.of(toAccount));
        when(accountRepository.saveAll(List.of(toAccount, fromAccount))).thenReturn(List.of(toAccount, fromAccount));

        Assertions.assertThrows(InsufficientBalanceException.class, () -> accountTransferService.transfer(request));

        verify(accountRepository, times(1)).findByAccountNumber(fromAccount.getAccountNumber());
        verify(accountRepository, times(1)).findByAccountNumber(toAccount.getAccountNumber());
        verify(accountRepository, never()).saveAll(List.of(toAccount, fromAccount));
    }

    @Test
    public void testAccountTransferService_whenAccountBalanceIsZero_shouldReturn() {
        Account fromAccount = AccountUtil.fromAccount();
        fromAccount.setBalance(BigDecimal.ZERO);
        Account toAccount = AccountUtil.toAccount();
        TransferRequest request = AccountUtil.transferRequest();

        when(accountRepository.findByAccountNumber(fromAccount.getAccountNumber())).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber(toAccount.getAccountNumber())).thenReturn(Optional.of(toAccount));
        when(accountRepository.saveAll(List.of(toAccount, fromAccount))).thenReturn(List.of(toAccount, fromAccount));

        Assertions.assertThrows(InsufficientBalanceException.class, () -> accountTransferService.transfer(request));

        verify(accountRepository, times(1)).findByAccountNumber(fromAccount.getAccountNumber());
        verify(accountRepository, times(1)).findByAccountNumber(toAccount.getAccountNumber());
        verify(accountRepository, never()).saveAll(List.of(toAccount, fromAccount));
    }

    @Test
    public void testAccountTransferService_whenToAccountIsInactive_shouldReturnAccountActivationException() {
        Account fromAccount = AccountUtil.fromAccount();
        Account toAccount = AccountUtil.toAccount();
        toAccount.setStatus(INACTIVE);
        TransferRequest request = AccountUtil.transferRequest();

        when(accountRepository.findByAccountNumber(fromAccount.getAccountNumber())).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber(toAccount.getAccountNumber())).thenReturn(Optional.of(toAccount));
        when(accountRepository.saveAll(List.of(toAccount, fromAccount))).thenReturn(List.of(toAccount, fromAccount));

        Assertions.assertThrows(AccountActivationException.class, () -> accountTransferService.transfer(request));

        verify(accountRepository, times(1)).findByAccountNumber(fromAccount.getAccountNumber());
        verify(accountRepository, times(1)).findByAccountNumber(toAccount.getAccountNumber());
        verify(accountRepository, never()).saveAll(List.of(toAccount, fromAccount));
    }

    @Test
    public void testAccountTransferService_whenFromAccountIsInactive_shouldReturnAccountActivationException() {
        Account fromAccount = AccountUtil.fromAccount();
        fromAccount.setStatus(INACTIVE);
        Account toAccount = AccountUtil.toAccount();
        TransferRequest request = AccountUtil.transferRequest();

        when(accountRepository.findByAccountNumber(fromAccount.getAccountNumber())).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber(toAccount.getAccountNumber())).thenReturn(Optional.of(toAccount));
        when(accountRepository.saveAll(List.of(toAccount, fromAccount))).thenReturn(List.of(toAccount, fromAccount));

        Assertions.assertThrows(AccountActivationException.class, () -> accountTransferService.transfer(request));

        verify(accountRepository, times(1)).findByAccountNumber(fromAccount.getAccountNumber());
        verify(accountRepository, times(1)).findByAccountNumber(toAccount.getAccountNumber());
        verify(accountRepository, never()).saveAll(List.of(toAccount, fromAccount));
    }

}
