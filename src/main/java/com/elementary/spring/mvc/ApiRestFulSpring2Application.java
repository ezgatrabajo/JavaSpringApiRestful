package com.elementary.spring.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mercadopago.*;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Payer;

@SpringBootApplication
public class ApiRestFulSpring2Application {

	public static void main(String[] args) throws MPException, MPConfException {

		SpringApplication.run(ApiRestFulSpring2Application.class, args);
	}

}
