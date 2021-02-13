package com.elementary.spring.mvc.rest;

import com.elementary.spring.mvc.exception.CategoriaCustomNotFoundException;
import com.elementary.spring.mvc.model.Authorizationcode;
import com.elementary.spring.mvc.model.Categoria;
import com.elementary.spring.mvc.repository.AuthorizationcodeRepository;
import com.elementary.spring.mvc.repository.CategoriaRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Card;
import com.mercadopago.resources.Customer;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.customer.DefaultAddress;
import com.mercadopago.resources.datastructures.customer.Identification;
import com.mercadopago.resources.datastructures.customer.Phone;
import com.mercadopago.resources.datastructures.customer.card.PaymentMethod;
import com.mercadopago.resources.datastructures.payment.Address;
import com.mercadopago.resources.datastructures.payment.Payer;
import com.mercadopago.resources.datastructures.preference.Item;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/v1/mercadopago")
@CrossOrigin
public class MercadopagoRestController {

    @Autowired
    private AuthorizationcodeRepository repoAuthorizationcode;



    @PostMapping("procesar_pago")
    public void procesar_pago(HttpServletRequest request) {
        try {
            String description = request.getParameter("description");
            String token = request.getParameter("token");
            float transaction_amount = Float.parseFloat(request.getParameter("transaction_amount"));
            System.out.println("Procesar Pago: " + description);
            System.out.println("Procesar Pago: " + token);

            MercadoPago.SDK.setAccessToken("TEST-2207797945420831-122010-06933213e1f9eda6452bffee4786b2bd__LC_LA__-214222883");
            MercadoPago.SDK.configure("TEST-2207797945420831-122010-06933213e1f9eda6452bffee4786b2bd__LC_LA__-214222883");

            Payment payment = new Payment();
            payment.setTransactionAmount(transaction_amount)
                    .setToken(token)
                    .setDescription("Gorgeous Leather Shirt")
                    .setInstallments(1)
                    .setPaymentMethodId("visa")
                    .setPayer(new Payer()
                            .setEmail("test@test.com"));
            payment.save();
            System.out.println(payment.getStatus());


        } catch (MPException e) {
            System.out.println(e.getStatusCode());

        }
    }


    @PostMapping("procesar_pago_efectivo")
    public void procesar_pago_efectivo(HttpServletRequest request) {
        try {
            String description = request.getParameter("description");
            String token = request.getParameter("token");
            float transaction_amount = Float.parseFloat(request.getParameter("transaction_amount"));
            System.out.println("Procesar Pago: " + description);
            System.out.println("Procesar Pago: " + token);

            MercadoPago.SDK.setAccessToken("TEST-2207797945420831-122010-06933213e1f9eda6452bffee4786b2bd__LC_LA__-214222883");
            MercadoPago.SDK.configure("TEST-2207797945420831-122010-06933213e1f9eda6452bffee4786b2bd__LC_LA__-214222883");
            Payment payment = new Payment();
            Payer p = new Payer();
            p.setEmail("test_user_54740899@testuser.com");

            payment.setTransactionAmount(100f)
                    .setDescription("Título del producto")
                    .setPaymentMethodId("rapipago")
                    .setPayer(p);

            payment.save();
        } catch (MPException e) {
            System.out.println(e.getStatusCode());

        }
    }

    @PostMapping("notifications")
    public void notification_ipn(HttpServletRequest request){
        try {

            String token = request.getParameter("token");
            System.out.println("Notification ipn: " + token);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }





    @PostMapping("card/add")
    public void card_add() {
        try {
            MercadoPago.SDK.configure("TEST-2207797945420831-122010-06933213e1f9eda6452bffee4786b2bd__LC_LA__-214222883");

            Customer customer = new Customer();
            customer.setEmail("prudence_auer@gmail.com");

            customer.save();

            Card card = new Card();
            card.setToken("9b2d63e00d66a8c721607214cedaecda");
            card.setCustomerId(customer.getId());
            card.save();
        } catch (MPException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/success")
    public void success(ModelMap map, @RequestParam String code) {

	    //Se obtiene el codigo token de autorhizacion para realizar el pago
        map.addAttribute("code", code);

        Authorizationcode ac = new Authorizationcode();
        ac.setCode(code);
        repoAuthorizationcode.save(ac);

        try{
            System.out.println("mp.success:" + code);
            String newtoken = "";
            String uri = "https://api.mercadopago.com/oauth/token";
            String access_token = "TEST-2207797945420831-122010-06933213e1f9eda6452bffee4786b2bd__LC_LA__-214222883";

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(uri);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("client_secret", access_token));
            params.add(new BasicNameValuePair("grant_type", "authorization_code"));
            params.add(new BasicNameValuePair("code", code));
            params.add(new BasicNameValuePair("redirect_uri", "https://127.0.0.1:8050/mercadopago/success"));

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");

            httpPost.setEntity(new UrlEncodedFormEntity(params));

            CloseableHttpResponse response = client.execute(httpPost);

            System.out.println("respuesta Mercadopago: "+ response.getStatusLine().getStatusCode());
            client.close();

            String json = IOUtils.toString(response.getEntity().getContent());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(json);

            //Pasar datos al front
            System.out.println("respuesta:" +json);
            System.out.println("respuesta:" + actualObj.get("access_token"));


        }catch(IOException e){
            System.out.println("Error en Mercadopago.success: " + e.getMessage());
        }


    }

	@PostMapping("customers/add/")
	@ResponseStatus(HttpStatus.CREATED)
	public String customersAdd(){
		try{
			MercadoPago.SDK.setAccessToken("TEST-2207797945420831-122010-06933213e1f9eda6452bffee4786b2bd__LC_LA__-214222883");
			MercadoPago.SDK.setClientSecret("lZVQBryGrJ3wzcuFLBrxsWuETU4sm1IEss");
			MercadoPago.SDK.setClientId("2207797945420831");


			Customer c = new Customer();
			c.setEmail("ezequiel@gmail.com");
			c.setFirstName("eze");
			c.setLastName("gal");
			c.setPhone(new Phone().setAreaCode("011").setNumber("1138083642"));

			Identification i = new Identification();
			i.setType("DNI");
			i.setNumber("331313131");


			c.setIdentification(i);
			DefaultAddress a = new DefaultAddress();
			a.setStreetName("llavallol");
			a.setZipCode("1419");
			a.setStreetNumber("123");
			c.setAddress(a);
			c.save();

            System.out.println("customerAdd: "+ c. toString());

            return c.getId();


		}catch(Exception e){
			System.out.println("Error en customers_add: " + e.getMessage());
			return null;
		}

	}







}
