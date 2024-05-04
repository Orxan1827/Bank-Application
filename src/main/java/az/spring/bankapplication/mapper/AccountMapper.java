package az.spring.bankapplication.mapper;

import az.spring.bankapplication.entity.Account;
import az.spring.bankapplication.dto.request.AccountCreateRequest;
import az.spring.bankapplication.dto.request.AccountUpdateRequest;
import az.spring.bankapplication.dto.response.AccountCreateResponse;
import az.spring.bankapplication.dto.response.AccountReadResponse;
import az.spring.bankapplication.dto.response.AccountUpdateResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    Account mapCreateRequestToAccount(AccountCreateRequest createRequest);

    AccountCreateResponse mapAccountToCreateResponse(Account account);

    AccountReadResponse mapAccountToReadResponse(Account account);

    AccountUpdateResponse mapAccountToUpdateResponse(Account account);

    @Mapping(target = "id", ignore = true)
    Account updateAccountFromUpdateRequest(AccountUpdateRequest updateRequest, @MappingTarget Account account);


}
