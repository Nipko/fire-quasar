package co.com.mercadolibre.fire_quazar;

import co.com.mercadolibre.fire_quazar.exceptions.HelpMessageException;
import co.com.mercadolibre.fire_quazar.processors.HelpMessageProcess;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private HelpMessageProcess messageService;

    @Test
    public void getMessage3Satellites() throws HelpMessageException {
        List<List<String>> messages = new ArrayList<List<String>>();
        String [] m1 = {"este", "", "", "mensaje", ""};
        String [] m2 = {"", "es", "", "", "secreto"};
        String [] m3 = {"este", "", "un", "", ""};
        messages.add(Arrays.stream(m1).collect(Collectors.toList()));
        messages.add(Arrays.stream(m2).collect(Collectors.toList()));
        messages.add(Arrays.stream(m3).collect(Collectors.toList()));
        String message = messageService.getMessage(messages);
        String expectedMsg = "este es un mensaje secreto";
        assertEquals(message,expectedMsg);
    }

    @Test
    public void getMessage3SatellitesError(){
        List<List<String>> messages = new ArrayList<List<String>>();
        String [] m1 = {"este", "", "", "mensaje", ""};
        String [] m2 = {"", "es", "", "", "secreto"};
        String [] m3 = {"este", "", "un", "", "",""};
        messages.add(Arrays.stream(m1).collect(Collectors.toList()));
        messages.add(Arrays.stream(m2).collect(Collectors.toList()));
        messages.add(Arrays.stream(m3).collect(Collectors.toList()));
        try {
            String message = messageService.getMessage(messages);
        }catch (HelpMessageException e){
            assertEquals(" the message cannot be know",e.getMessage());
        }
    }

    @Test
    public void getMessage4Satellites() throws HelpMessageException {
        List<List<String>> messages = new ArrayList<List<String>>();
        String [] m1 = {"este", "", "", "mensaje", ""};
        String [] m2 = {"", "es", "", "", "secreto"};
        String [] m3 = {"este", "", "un", "", ""};
        String [] m4 = {"", "", "un", "", "secreto"};
        messages.add(Arrays.stream(m1).collect(Collectors.toList()));
        messages.add(Arrays.stream(m2).collect(Collectors.toList()));
        messages.add(Arrays.stream(m3).collect(Collectors.toList()));
        messages.add(Arrays.stream(m4).collect(Collectors.toList()));
        String message = messageService.getMessage(messages);
        String expectedMsg = "este es un mensaje secreto";
        assertEquals(message,expectedMsg);
    }
}
