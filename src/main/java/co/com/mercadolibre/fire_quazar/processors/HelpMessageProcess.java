package co.com.mercadolibre.fire_quazar.processors;

import co.com.mercadolibre.fire_quazar.exceptions.HelpMessageException;
import co.com.mercadolibre.fire_quazar.utils.Messages;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class HelpMessageProcess {

    public List<String> getMsgPhrases(List<List<String>> msgList) {

        List<String> listWords = new ArrayList<String>();
        for (List<String> msg : msgList) {
            listWords = Stream.concat(listWords.stream(), msg.stream())
                    .distinct()
                    .collect(Collectors.toList());
        }
        listWords.remove("");
        return listWords;
    }

    public void removeGap(List<List<String>> msgList, int gapSize) {

        int s = 0;
        for (int i = 0; i < msgList.size(); i++) {
            s = msgList.get(i).size();
            msgList.set(i, msgList.get(i).subList(s - gapSize, s));
        }

    }

    public String completeMessage(List<List<String>> msgList) {

        String phrase = "";
        for (List<String> m : msgList) {

            if (m.size() > 0 && !m.get(0).equals("")) {
                phrase = (m.size() == 1) ? m.get(0) : m.get(0) + " ";
                msgList.stream().forEach(s -> s.remove(0));
                return phrase + completeMessage(msgList);
            }
        }
        return "";
    }

    public String getMessage(List<List<String>> msgList) throws HelpMessageException {

        List<String> msgPhrases = getMsgPhrases(msgList);
        if (!validateMessagesSize(msgList, msgPhrases.size()))
            throw new HelpMessageException(Messages.WRONG_MESSAGE_SIZE);

        removeGap(msgList, msgPhrases.size());
        String message = completeMessage(msgList);
        if (!validateMessagePhrases(msgPhrases, message))
            throw new HelpMessageException(Messages.CANNOT_MESSAGE_BE_KNOWN);

        return message;
    }

    public boolean validateMessagesSize(List<List<String>> messages, int size) {
        for (List<String> m : messages) {
            if (m.size() < size) {
                return false;
            }
        }
        return true;
    }

    public boolean validateMessagePhrases(List<String> phrases, String message) {
        List<String> msg = Arrays.stream(message.split(" ")).collect(Collectors.toList());
        Collections.sort(phrases);
        Collections.sort(msg);
        return Arrays.equals(phrases.toArray(), msg.toArray());
    }

}
