package tacos.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by phyriak on 11/12/2020
 */
public class TestCore {
    public static void main(String[] args) {



        String[] objectedTicketFormParams = {"ccc","ddd"};
        String[] ticketFormParams = {"aaa","bbb"};
        List<String> allTicketFormParams = new ArrayList<String>(Arrays.asList(ticketFormParams));
        String ccc = Arrays.asList(objectedTicketFormParams).stream()
                .filter(a -> a.contains("ccc"))
                .findAny().get();

        allTicketFormParams.add("ccc");
        String[] objects = allTicketFormParams.toArray(new String [allTicketFormParams.size()]);


        for (String a: allTicketFormParams) {
            System.out.println(a);
        }
    }
}
