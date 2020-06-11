package com.elementary.spring.mvc.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.mercadopago.*;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Payer;


@Controller
@RequestMapping("mercadopago")
public class MercadopagoController {

    @GetMapping("")
    public String index(ModelMap map){
        map.addAttribute("welcomeMessage", "welcome");
        return "mercadopago/index";
    }

    @GetMapping("/showViewPage")
    public String passParametersWithModel(Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("spring", "mvc");
        model.addAttribute("message", "Baeldung");
        model.mergeAttributes(map);
        return "mercadopago/viewPage";
    }

    @GetMapping("/printViewPage")
    public String passParametersWithModelMap(ModelMap map) {
        map.addAttribute("welcomeMessage", "welcome");
        map.addAttribute("message", "Baeldung");
        return "mercadopago/viewPage";
    }

    @GetMapping("/goToViewPage")
    public ModelAndView passParametersWithModelAndView() {
        ModelAndView modelAndView = new ModelAndView("mercadopago/viewPage");
        modelAndView.addObject("message", "Baeldung");
        return modelAndView;
    }

    @GetMapping("/success")
    public String success(ModelMap map, @RequestParam String code) {
        //Se obtiene el codigo token de autorhizacion para realizar el pago
        map.addAttribute("code", code);

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
            System.out.println("respuesta:" + actualObj.get(""));

            map.addAttribute("respuesta", json);


        }catch(IOException e){
            System.out.println("Error en Mercadopago.success: " + e.getMessage());
        }

        return "mercadopago/success";
    }

    @GetMapping("/authorizacion")
    public String authorizacion(ModelMap map) {

        return "mercadopago/authorizacion";
    }

    @GetMapping("pago")
    public String pago(ModelMap map) {

        return "mercadopago/pago";
    }

}
