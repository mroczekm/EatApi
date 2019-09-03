package com.eatapi;

import com.eatapi.model.Order;
import com.eatapi.model.OrderDetails;
import com.eatapi.model.User;
import com.eatapi.repository.OrderDetailsRepository;
import com.eatapi.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;

@Component
public class EmailServiceImpl {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    UserResource userResource;
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    public void sendSimpleMessage(
            String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public String getTextNewOrder(Order order) {
        return "Co zamawiamy? \n" + order.getName() + "\n\nOpis: \n" + order.getDescription() + "\n\n " + "Pozdrawiam\n" + order.getUser().getFirstName() + " " + order.getUser().getLastName();
    }

    public String getText(Order order, OrderDetails orderDetails) {
        DecimalFormat df = new DecimalFormat("###0.00");
        return "Zmiana statusu zamówienia na: " + order.getStatus().toUpperCase() + "\n\n" +
                "TWOJE ZAMÓWIENIE\n" +
                "Danie: " + orderDetails.getDish() + "\n" +
                "Uwagi: " + orderDetails.getDescription() + "\n" +
                "Cena: " + df.format(orderDetails.getPrice()) + " zł\n" +
                "Dodatkowo: " + df.format(orderDetails.getExtra()) + " zł\n" +
                "ŁĄCZNIE: " + df.format(orderDetails.getPrice()+orderDetails.getExtra())  + "\n\n" +
                "OPIS ZAMÓWIENIA: \n" + order.getDescription() + "\n\n " +
                "Pozdrawiam\n" + order.getUser().getFirstName() + " " + order.getUser().getLastName();
    }


    public void sendMails(Order order) {

        List<User> users = userResource.getAll();
        List<OrderDetails> orderDetails = orderDetailsRepository.findByOrderId(order.getId());
        if (order.getStatus().equals("Otwarte")) {
            for (User user : users) {
                sendSimpleMessage(user.getUsername(), "Zamawiamy jedzenie", getTextNewOrder(order));
            }
        } else if (order.getStatus().equals("Zamówione")) {
            for (OrderDetails orderDetail : orderDetails) {
                sendSimpleMessage(orderDetail.getUser().getUsername(), "Jedzenie zostało zamówione", getText(order, orderDetail));
            }
        } else if (order.getStatus().equals("Do odbioru")) {
            for (OrderDetails orderDetail : orderDetails) {
                sendSimpleMessage(orderDetail.getUser().getUsername(), "Jedzenie do odbioru", getText(order, orderDetail));
            }
        }
    }
}