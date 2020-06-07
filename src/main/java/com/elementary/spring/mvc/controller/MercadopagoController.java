package com.elementary.spring.mvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("mercadopago")
public class MercadopagoController {

    @GetMapping("boton")
    public String boton(ModelMap map){
        map.addAttribute("welcomeMessage", "welcome");
        return "mercadopago/boton";
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

        map.addAttribute("code", code);

        try{
            String newtoken = "";
            String uri = "https://api.mercadopago.com/oauth/token";
            String access_token = "TEST-2207797945420831-122010-06933213e1f9eda6452bffee4786b2bd__LC_LA__-214222883";
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(uri);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("client_secret", access_token));
            params.add(new BasicNameValuePair("grant_type", code));
            params.add(new BasicNameValuePair("code", code));
            params.add(new BasicNameValuePair("redirect_uri", uri));

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");


            httpPost.setEntity(new UrlEncodedFormEntity(params));

            CloseableHttpResponse response = client.execute(httpPost);

            System.out.println("respuesta Mercadopago: "+ response.getStatusLine().getStatusCode());
            client.close();


            System.out.println("Credenciales:" + newtoken);

        }catch(IOException e){

        }

        return "mercadopago/success";
    }

    @GetMapping("/authorizacion")
    public String authorizacion(ModelMap map) {

        return "mercadopago/authorizacion";
    }

}
