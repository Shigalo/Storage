package by.bsuir.shigalo7.Services;

import by.bsuir.shigalo7.Entities.Stock;
import by.bsuir.shigalo7.Entities.Subscribe;
import by.bsuir.shigalo7.Entities.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSendingService /*implements EmailService*/ {

    @Autowired
    public JavaMailSender emailSender;
    @Autowired
    SubscribeService subscribeService;

    public void sendMessage(String to, String subject, String text) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendMessages(Stock stock) {
        try {
            Warehouse warehouse = stock.getWarehouse();
            List<Subscribe> subscribeList = subscribeService.findByWarehouse(warehouse);

            int min = Math.max(1, stock.getReorder_level() - stock.getQuantity());

            String subject = "Оповещение о необходимость заказа";
            String text = "В отслеживаемом вами складе по адресу " + stock.getWarehouse().getAddress() +
                    " требуется пополенение продукции!\n" +
                    "Информация о продукции:\n" +
                    "Наименование: " + stock.getProduct().getName() + "\n" +
                    "Тип: " + stock.getProduct().getType() + "\n\n" +
                    "Оставшийся запас продукции: " + stock.getQuantity() + "ед.\n" +
                    "Минимальный размер заказа: " + min + "ед.";

            for (Subscribe subscribe : subscribeList) {
                String to = subscribe.getUser().getMail();
                sendMessage(to, subject, text);
            }
        } catch (Exception e) { e.printStackTrace(); }

    }
}
