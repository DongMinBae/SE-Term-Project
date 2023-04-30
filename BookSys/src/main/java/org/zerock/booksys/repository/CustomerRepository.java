package org.zerock.booksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.booksys.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
