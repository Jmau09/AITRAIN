package com.ecommerce.notification.infraestructure.sqs;

import com.ecommerce.notification.infraestructure.sns.SnsSmsSender;
import com.ecommerce.notification.infraestructure.sqs.dto.EventoNotificacionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class SqsListener {

    private final SqsClient sqsClient;
    private final ObjectMapper objectMapper;
    private final SnsSmsSender smsSender;

    @Value("${aws.queue-url}")
    private String QUEUE_URL;

    @PostConstruct
    public void escucharMensajes() {
        Executors.newSingleThreadExecutor().submit(() -> {
            while (true) {
                ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
                        .queueUrl(QUEUE_URL)
                        .maxNumberOfMessages(5)
                        .waitTimeSeconds(10)
                        .build();

                List<Message> messages = sqsClient.receiveMessage(receiveRequest).messages();

                for (Message message : messages) {
                    try {
                        System.out.println("Mensaje recibido de SQS: "+message);
                        EventoNotificacionDTO evento = objectMapper.readValue(message.body(), EventoNotificacionDTO.class);

                        System.out.println("Procesando evento: "+evento);
                        smsSender.enviarSms(evento.getMensaje(), evento.getNumeroTelefono());
                        //emailSender.enviarEmail(evento.getEmail(), evento.getTipo(), evento.getMensaje());

                        System.out.println("Mensaje procesado:" +message.messageId());
                        sqsClient.deleteMessage(DeleteMessageRequest.builder()
                                .queueUrl(QUEUE_URL)
                                .receiptHandle(message.receiptHandle())
                                .build());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
