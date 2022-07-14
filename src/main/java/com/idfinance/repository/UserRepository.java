package com.idfinance.repository;

import com.idfinance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserNameAndCoinSymbol(String userName, String coinSymbol);
}
