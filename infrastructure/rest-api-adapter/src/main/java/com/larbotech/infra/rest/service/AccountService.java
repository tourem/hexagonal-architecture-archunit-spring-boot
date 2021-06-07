package com.larbotech.infra.rest.service;

import com.larbotech.domain.api.SendMoneyCommand;
import com.larbotech.domain.api.SendMoneyUseCase;
import com.larbotech.infra.rest.annotation.RestAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RestAdapter
@RequiredArgsConstructor
@Service
public class AccountService {

    private final SendMoneyUseCase sendMoneyUseCase;

        public boolean sendMoney(SendMoneyCommand command) {
            return sendMoneyUseCase.sendMoney(command);
        }
}
