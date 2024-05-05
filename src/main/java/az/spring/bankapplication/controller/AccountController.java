package az.spring.bankapplication.controller;

import az.spring.bankapplication.dto.request.*;
import az.spring.bankapplication.dto.response.AccountCreateResponse;
import az.spring.bankapplication.dto.response.AccountReadResponse;
import az.spring.bankapplication.dto.response.AccountUpdateResponse;
import az.spring.bankapplication.service.accountService.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@PreAuthorize("hasAuthority('USER')")
@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountCreateService accountCreateService;

    private final AccountReadAllService accountReadAllService;

    private final AccountUpdateService accountUpdateService;

    private final AccountTransferService accountTransferService;

    private final AccountDeleteService accountDeleteService;

    @PostMapping("/save")
    public ResponseEntity<AccountCreateResponse> createAccount(AccountCreateRequest createRequest) {
        return ResponseEntity.status(CREATED).body(accountCreateService.createAccount(createRequest));
    }

    @GetMapping("/active")
    public ResponseEntity<List<AccountReadResponse>> getAllActiveAccounts(@Valid @RequestBody AccountReadActiveRequest readRequest) {
        return ResponseEntity.status(OK).body(accountReadAllService.getAllActiveAccounts(readRequest));
    }

    @GetMapping
    public ResponseEntity<List<AccountReadResponse>> getAllAccounts(@Valid @RequestBody AccountReadActiveRequest readRequest) {
        return ResponseEntity.status(OK).body(accountReadAllService.getAllAccounts(readRequest));
    }

    @PatchMapping("/{account-id}")
    public ResponseEntity<AccountUpdateResponse> updateAccount(@PathVariable(name = "account-id") Long accountId, @Valid @RequestBody AccountUpdateRequest updateRequest) {
        return ResponseEntity.status(OK).body(accountUpdateService.UpdateAccount(accountId, updateRequest));
    }

    @PostMapping("/transfer")
    public void transfer(@Valid @RequestBody TransferRequest transferRequest) {
        accountTransferService.transfer(transferRequest);
    }

    @DeleteMapping("/{account-id}")
    public void deleteAccount(@PathVariable(name = "account-id") Long accountId) {
        accountDeleteService.deleteAccount(accountId);
    }

    @DeleteMapping("/deActive/{account-id}")
    public void deActiveAccount(@PathVariable(name = "account-id") Long accountId) {
        accountDeleteService.deActiveAccount(accountId);
    }

}
