/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.support;

import csheets.AppSettings;
import csheets.framework.Money;

/**
 * Utility class to get a exchange rate in Money type.
 *
 * @author Rui Freitas
 */
public class ExchangeRateConverter {

	private final static String EXCHANGERATE_POUNDTOEURO = "exchangerate.poundToEuro";
	private final static String EXCHANGERATE_DOLLARTOEURO = "exchangerate.dollarToEuro";
	private final static String EXCHANGERATE_EUROTOPOUND = "exchangerate.euroToPound";
	private final static String EXCHANGERATE_EUROTODOLLAR = "exchangerate.euroToDollar";

	/* Make sure it is an utility class */
	private ExchangeRateConverter() {
	}

	/**
	 * Exchange rate from dollar to euro.
	 *
	 * @return money
	 */
	public static Money getDollarToEuroExchangeRate() {
		return Money.euros(Double.parseDouble(AppSettings.instance().
			get(EXCHANGERATE_DOLLARTOEURO)));
	}

	/**
	 * Exchange rate from pound to euro.
	 *
	 * @return money
	 */
	public static Money getPoundToEuroExchangeRate() {
		return Money.euros(Double.parseDouble(AppSettings.instance().
			get(EXCHANGERATE_POUNDTOEURO)));
	}

	/**
	 * Exchange rate from euro to pound.
	 *
	 * @return money
	 */
	public static Money getEuroToPoundExchangeRate() {
		return Money.pounds(Double.parseDouble(AppSettings.instance().
			get(EXCHANGERATE_EUROTOPOUND)));
	}

	/**
	 * Exchange rate from euro to dollar.
	 *
	 * @return money
	 */
	public static Money getEuroToDollarExchangeRate() {
		return Money.dollars(Double.parseDouble(AppSettings.instance().
			get(EXCHANGERATE_EUROTODOLLAR)));
	}

}
