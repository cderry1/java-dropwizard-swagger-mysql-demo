package org.kainos.ea.resources;
import org.kainos.ea.api.ProductService;
import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import  io.swagger.annotations.Api;

@Api("Engineering Academy Dropwizard Product API")
@Path("/api")
public class ProductController {

    private ProductService productservice = new ProductService();
    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct()
    {
        try {
            return Response.ok(productservice.getAllProducts()).build();
        } catch (FailedToGetProductsException e) {
        System.err.println(e.getMessage());
        return Response.serverError().build();
        }
    }
    @GET
    @Path("/products/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") int id)
    {
        try {
            return Response.ok(productservice.getProductById(id)).build();
        } catch (FailedToGetProductsException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (ProductDoesNotExistException e) {
            throw new RuntimeException(e);
        }
    }
    @POST
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(ProductRequest product)
    {
        try {
            return Response.status(Response.Status.CREATED).entity(productservice.createProduct(product)).build();
        }
         catch (FailedToCreateProductException e) {

            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (InvalidProductException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    @PUT
    @Path("/products/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(@PathParam("id") int id, ProductRequest productRequest)
    {
        try {
            productservice.updateProduct(id,productRequest);
            return Response.ok().build();
        }
        catch (FailedToGetProductsException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (ProductDoesNotExistException | InvalidProductException e) {
            System.err.println(e.getMessage());
            return  Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToUpdateProductException e) {
            System.err.println(e.getMessage());
            return  Response.serverError().build();
        }
    }
    @DELETE
    @Path("/products/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") int id)
    {
        try {
            productservice.deleteProduct(id);
            return Response.ok().build();
        } catch (ProductDoesNotExistException e) {
            System.err.println(e.getMessage());
            return  Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToDeleteProductException e) {
            throw new RuntimeException(e);
        }
    }
}
