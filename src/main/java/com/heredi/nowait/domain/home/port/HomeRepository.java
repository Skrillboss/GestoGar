package com.heredi.nowait.domain.home.port;

import com.heredi.nowait.domain.home.model.Home;
import org.springframework.stereotype.Service;

@Service
public interface HomeRepository {
    Home createHome(Home home);
    Home getHome(Long businessId);
    Home updateHome(Long userId, Home home);
}
