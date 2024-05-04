package az.spring.bankapplication.repository;

import az.spring.bankapplication.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
