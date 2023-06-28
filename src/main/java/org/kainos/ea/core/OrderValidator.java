package org.kainos.ea.core;

import org.kainos.ea.api.OrderService;
import org.kainos.ea.api.ProductService;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.FailedToGetOrdersException;

import javax.validation.constraints.Null;
import java.util.Calendar;
import java.util.Date;

public class OrderValidator {
    public  String isValidOrder(OrderRequest orderRequest) throws FailedToGetOrdersException {
        OrderService orderService = new OrderService();


        for(int i = 0; i <orderService.getAllOrders().size(); i++ )
        {
            if(orderRequest.getClientId() == orderService.getAllOrders().get(i).getClientId())
            {
                break;
            } else if(orderRequest.getClientId() != orderService.getAllOrders().get(i).getClientId())
            {
            }

            else if(i > orderService.getAllOrders().size())
            {

                return "The Client Id couldn't be found";
            }

        }
        Calendar calendar = null;
        calendar.set(2023, Calendar.JANUARY, 1);
        Date date = calendar.getTime();
        if(orderRequest.getStartDate().before(date))
        {
            return  "The Date entered is before this year";
        }
        return null;
    }
}
