package com.toki.backend.auth.repository;

import com.toki.backend.auth.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByUserPk(String userPk);

    int countByUserPk(String userTag);

    Optional<RefreshToken> findOneByUserPkAndRefreshToken(String userPk, String refreshToken);

    int countByRefreshToken(String userPk);
}
