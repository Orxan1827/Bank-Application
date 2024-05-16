package az.spring.bankapplication.mapper;

import az.spring.bankapplication.entity.User;
import az.spring.bankapplication.dto.request.UserRegisterRequest;
import az.spring.bankapplication.dto.response.UserReadResponse;
import az.spring.bankapplication.dto.response.UserRegisterResponse;
import az.spring.bankapplication.dto.request.UserUpdateRequest;
import az.spring.bankapplication.dto.response.UserUpdateResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User mapRegisterRequestToUser(UserRegisterRequest registerRequest);

    UserRegisterResponse mapUserToRegisterResponse(User user);

    UserUpdateResponse mapUserToUpdatedResponse(User user);

    UserReadResponse mapUserToReadResponse(User user);

    User updateUSerFromUpdateRequest(UserUpdateRequest updateRequest, @MappingTarget User user);

}
