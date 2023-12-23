package be.kdg.hifresh.businessLayer.services.pubSub;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageBroker {
    private final List<Object> subscribers;

    public MessageBroker() {
        this.subscribers = new ArrayList<>();
    }

    /**
     * @param receiver    The receiver of the message.
     * @param identifiers The identifiers of the message. Should be in the form of a map with the key being the full name of the function and the value being the value of the identifier.
     * @param msg         The message to be sent.
     * @param <T>         The type of the receiver.
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

    @SuppressWarnings("unchecked")
    private <T> List<T> filter(T receiver) {
        return subscribers.stream()
                .filter(subscriber -> subscriber.getClass() == receiver.getClass())
                .map(subscriber -> (T) subscriber)
                .collect(Collectors.toList());
    }

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

    public void subscribe(Object object) {
        subscribers.add(object);
    }

}
