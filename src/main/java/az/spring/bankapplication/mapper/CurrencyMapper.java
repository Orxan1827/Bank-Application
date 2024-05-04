package az.spring.bankapplication.mapper;

import az.spring.bankapplication.entity.Currency;
import az.spring.bankapplication.dto.response.CurrencyResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CurrencyMapper {

        CurrencyResponse mapCurrencyToResponse(Currency currency);

}
