package az.spring.bankapplication.util;

import az.spring.bankapplication.dto.request.AccountCreateRequest;
import az.spring.bankapplication.dto.request.AccountReadActiveRequest;
import az.spring.bankapplication.dto.request.TransferRequest;
import az.spring.bankapplication.dto.response.AccountCreateResponse;
import az.spring.bankapplication.dto.response.AccountReadResponse;
import az.spring.bankapplication.entity.Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static az.spring.bankapplication.enums.AccountStatus.ACTIVE;

public class AccountUtil {

    private AccountUtil() {

    }

    public static AccountCreateResponse accountCreateResponse() {
        return AccountCreateResponse.builder()
                .id(1L)
                .balance(BigDecimal.ZERO)
                .accountNumber("12345")
                .status(ACTIVE)
                .fkUserId(1L)
                .build();
    }

    public static AccountCreateRequest accountCreateRequest() {
        return AccountCreateRequest.builder()
                .balance(BigDecimal.ZERO)
                .accountNumber("12345")
                .fkUserId(1L)
                .build();
    }

    public static List<AccountReadResponse> accountReadResponses() {
        List<AccountReadResponse> expectResponseList = new ArrayList<>();
        expectResponseList.add(AccountReadResponse.builder().id(1L).status(ACTIVE).fkUserId(1L).build());
        expectResponseList.add(AccountReadResponse.builder().id(2L).status(ACTIVE).fkUserId(1L).build());
        return expectResponseList;
    }

    public static AccountReadActiveRequest accountReadActiveRequest() {
        return AccountReadActiveRequest.builder()
                .fkUserId(1L)
                .build();
    }

    public static List<Account> activeAccountList() {
        List<Account> accountList = new ArrayList<>();
        accountList.add(Account.builder().id(1L).status(ACTIVE).build());
        accountList.add(Account.builder().id(2L).status(ACTIVE).build());
        return accountList;
    }

    public static List<Account> accountList() {
        List<Account> accountList = new ArrayList<>();
        accountList.add(Account.builder().id(1L).fkUserId(1L).build());
        accountList.add(Account.builder().id(2L).fkUserId(1L).build());
        return accountList;
    }

    public static Account activeAccount() {
        return Account.builder()
                .id(1L)
                .balance(BigDecimal.ZERO)
                .accountNumber("12345")
                .status(ACTIVE)
                .fkUserId(1L)
                .build();
    }

    public static Account fromAccount() {
        return Account.builder()
                .id(1L)
                .balance(BigDecimal.TEN)
                .accountNumber("12345")
                .status(ACTIVE)
                .fkUserId(1L)
                .build();
    }

    public static Account toAccount() {
        return Account.builder()
                .id(1L)
                .balance(BigDecimal.ONE)
                .accountNumber("12346")
                .status(ACTIVE)
                .fkUserId(2L)
                .build();
    }

    public static TransferRequest transferRequest() {
        return TransferRequest.builder()
                .fromAccountNumber("12345")
                .toAccountNumber("12346")
                .amount(BigDecimal.ONE)
                .build();
    }

}
