package com.larbotech.domain.api;

import com.larbotech.domain.model.Account;
import com.larbotech.domain.model.Money;

public interface GetAccountBalanceQuery {

	Money getAccountBalance(Account.AccountId accountId);

}

//application = domaine métier
// - définit des ports (interfaces) :
//    1) en in pour être solicité par le monde extérieur (par ex controller api, ...) : implémentées par l'application
//      ici, ces interfaces sont implémentées dans le packge service
//    2) en out pour soliciter le monde extérieur : save de data, call ws, ...: implémentées par others via des adapters
// les adpters en in solicitent l'application et en out sont solicités par l'application

//The Hexagon ==> the application
	//- Driver Ports ==> API offered by the application (interface Api, implémentation proposées par appli = use case)
	//- Driven Ports ==> SPI required by the application, implémentées par adapters

//Adapters ==> adapt specific technology to the application
	// - Driver Adapters ==> use the drivers ports
	// - Driven Adapters ==> implement the driven ports