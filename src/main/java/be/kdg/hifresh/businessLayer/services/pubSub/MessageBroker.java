package be.kdg.hifresh.businessLayer.services.pubSub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class is responsible for managing messages between different parts of the application.
 * It provides methods to send messages, filter recipients, get recipients and subscribe to the message broker.
 */
@Service
public class MessageBroker {
    /**
     * List of subscribers to the message broker.
     */
    private final List<Object> subscribers;

    /**
     * Constructor for MessageBroker.
     * Initializes the list of subscribers.
     */
    public MessageBroker() {
        this.subscribers = new ArrayList<>();
    }

    /**
     * Sends a message to a receiver with specific identifiers.
     *
     * @param receiver    The receiver of the message.
     * @param identifiers The identifiers of the message. Should be in the form of a map with the key being the full name of the function and the value being the value of the identifier.
     * @param subject     The subject of the message.
     * @param msg         The message to be sent.
     * @param <T>         The type of the receiver.
     * @param <V>         The type of the value of the identifiers.
     * @throws NoSuchMethodException     If a matching method is not found.
     * @throws InvocationTargetException If the underlying method throws an exception.
     * @throws IllegalAccessException    If this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    public <T, V> void send(T receiver, Map<String, V> identifiers, String subject, String msg) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (T t : getRecipients(filter(receiver), identifiers)) {
            System.out.printf("Mail send to: %s %s\tSubject: %s\tMessage: %s%n",
                    t.getClass().getSimpleName(),
                    t.getClass().getMethod("getID").invoke(t),
                    subject,
                    msg);
        }
    }

    /**
     * Filters the subscribers based on the type of the receiver.
     *
     * @param receiver The receiver used to filter the subscribers.
     * @param <T>      The type of the receiver.
     * @return A list of subscribers of the same type as the receiver.
     */
    @SuppressWarnings("unchecked")
    private <T> List<T> filter(T receiver) {
        return subscribers.stream()
                .filter(subscriber -> subscriber.getClass() == receiver.getClass())
                .map(subscriber -> (T) subscriber)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the recipients from the subscribers based on the identifiers.
     *
     * @param subscribers The list of subscribers to filter.
     * @param identifiers The identifiers used to filter the subscribers.
     * @param <T>         The type of the subscribers.
     * @param <V>         The type of the value of the identifiers.
     * @return A list of recipients.
     */
    private <T, V> List<T> getRecipients(List<T> subscribers, Map<String, V> identifiers) {
        return subscribers.stream()
                .filter(s -> identifiers.entrySet().stream()
                        .allMatch(entry -> {
                            try {
                                Method method = s.getClass().getMethod(entry.getKey());
                                return entry.getValue().equals(method.invoke(s));
                            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                                throw new RuntimeException(e);
                            }
                        }))
                .collect(Collectors.toList());
    }

    /**
     * Adds an object to the list of subscribers.
     *
     * @param object The object to be added to the list of subscribers.
     */
    public void subscribe(Object object) {
        subscribers.add(object);
    }

}