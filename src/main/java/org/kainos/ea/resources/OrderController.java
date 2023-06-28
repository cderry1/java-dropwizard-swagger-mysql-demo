package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.OrderService;
import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Api("Engineering Academy Dropwizard Order API")
@Path("/api")
public class OrderController {
    private OrderService orderservice = new OrderService();
    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders()
    {
        try {
            return Response.ok(orderservice.getAllOrders()).build();
        } catch (FailedToGetOrdersException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }
    @GET
    @Path("/orders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("id") int id)
    {
        try {
            try {
                return Response.ok(orderservice.getOrderById(id)).build();
            } catch (ProductDoesNotExistException e) {
                throw new RuntimeException(e);
            }
        } catch (FailedToGetProductsException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @POST
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(OrderRequest order)
    {
        try {
            return Response.status(Response.Status.CREATED).entity(orderservice.createOrder(order)).build();
        }
        catch (FailedToCreateOrderException e) {

            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (InvalidOrderException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/orders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrder(@PathParam("id") int id, OrderRequest orderRequest)
    {
        try {
            orderservice.updateOrder(id,orderRequest);
            return  Response.status(Response.Status.ACCEPTED).build();
        }
         catch (OrderDoesNotExistException | InvalidOrderException e) {
            System.err.println(e.getMessage());
            return  Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToUpdateOrderException e) {
            System.err.println(e.getMessage());
            return  Response.serverError().build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @DELETE
    @Path("/orders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrder(@PathParam("id") int id)
    {
        try {
            orderservice.deleteOrder(id);
            return Response.ok().build();
        } catch (OrderDoesNotExistException e) {
            System.err.println(e.getMessage());
            return  Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToDeleteOrderException e) {
            throw new RuntimeException(e);
        }
    }
}
