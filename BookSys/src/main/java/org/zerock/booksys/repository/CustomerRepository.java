package org.zerock.booksys.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.booksys.domain.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select c from Customer c where c.cId = :cid and c.social = false")
    Optional<Customer> getWithRoles(String cid);

}
