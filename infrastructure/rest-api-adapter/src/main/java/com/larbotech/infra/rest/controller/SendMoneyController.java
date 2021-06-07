package com.larbotech.infra.rest.controller;

import com.larbotech.infra.rest.annotation.RestAdapter;
import com.larbotech.domain.api.SendMoneyCommand;
import com.larbotech.domain.api.SendMoneyUseCase;
import com.larbotech.domain.model.Account;
import com.larbotech.domain.model.Money;
import com.larbotech.infra.rest.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class SendMoneyController  extends AbstractController{

	private final AccountService accountService;

	//@Autowired
	//private SendMoneyUseCase sendMoneyUseCase;

	/*public void validate(SendMoneyCommand sendMoneyCommand){

	}*/

	@PostMapping(path = "/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
	void sendMoney(
			@PathVariable("sourceAccountId") String sourceAccountId,
			@PathVariable("targetAccountId") String targetAccountId,
			@PathVariable("amount") Long amount) {

		SendMoneyCommand command = new SendMoneyCommand(
				 Account.AccountId.ofString(sourceAccountId),
				 Account.AccountId.ofString(targetAccountId),
				Money.of(amount));

		accountService.sendMoney(command);
	}

}
