package org.kainos.ea.api;

import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.ProductValidator;
import org.kainos.ea.db.OrderDao.OrderDao;
import org.kainos.ea.db.OrderDao.ProductDao;

import java.sql.SQLException;
import java.util.*;

public class ProductService {
    private ProductDao productDao = new ProductDao();
    private ProductValidator productValidation = new ProductValidator();
    public List<Product> getAllProducts() throws FailedToGetProductsException {

        List<Product> productList = null;
        productList = productDao.getAllProducts();
        //try{
          //  Product product = orderList.get(1000);
         //   return orderList;
        //}
        //catch (IndexOutOfBoundsException e)
        //{
        //System.out.println(e.getMessage());
       // }
      // System.out.println(orderList);

     //  throw new FailedToGetProductsException();
        // print every Product with Price under 10
        //    orderList.stream().filter(product -> product.getPrice() < 10).forEach(System.out::println);
    // print every Product with Price over 10
      //  orderList.stream().filter(product -> product.getPrice() > 10).forEach(System.out::println);

        // print Max Item
      //  System.out.println(Collections.min(orderList));

        //Print Min Item
        // System.out.println(Collections.min(orderList));

//  Sort Product
       //Collections.sort(orderList);

            // Priniting all exisitng data within a list/Set
        //    List<Integer> ints = Arrays.asList(1,2,3,4,5);

       //     Set<Integer> SetI = new HashSet<>(ints);
      //  SetI.stream().forEach(System.out::println);

//          For Each to display the prices of certain named items
          //  for(Product product: orderList)
           // {
            //    switch (product.getName())
             //   {
              //      case("as"):
               //         System.out.println("The Price of as is " + product.getPrice());
                //        break;
                 //   case("aa"):
                  //      System.out.println("The Price of aa is " + product.getPrice());
                   //     break;
                    //default:
                     //   System.out.println("The price of the other item is " + product.getPrice());
               // }
            //}


//  For Each to get total of expensive and cheao items
        //double totalCheapest = 0;
        //double totalExpensive = 0;
       // for(Product product : orderList)
       // {
            //if(product.getPrice() < 100)
           // {
            //    totalCheapest += product.getPrice();
           // }
          //  else
         //   {
        //        totalExpensive += product.getPrice();
       //     }
      //  }


//  Total Variable
      // double totalPriceofProducts = 0;

        //  Getting total by using stream
      //  totalPriceofProducts = orderList.stream().mapToDouble(Product -> Product.getPrice()).sum();

        // Getting total by using a do while/ While loop below it
        //Iterator<Product> productoriterator = orderList.listIterator();

        //do {
          //  Product product = productoriterator.next();
         //    totalPriceofProducts += product.getPrice();
        //}
       // while (productoriterator.hasNext());


        //while(productoriterator.hasNext())
        //{
          //  Product product = productoriterator.next();
         //   totalPriceofProducts += product.getPrice();
       // }


        // total by For Each and for loop below it
        //for(Product product: orderList)
        //{
         //   totalPriceofProducts += product.getPrice();
        //}


          //   for(int i = 0; i< orderList.size(); i++)
           // {
            //   totalPriceofProducts += orderList.get(i).getPrice();
         // }
       //System.out.println("Total price of all products: £" + totalPriceofProducts);
        return productList;
    }
    public  Product getProductById(int id) throws FailedToGetProductsException, ProductDoesNotExistException {
    try
    {
        Product product = productDao.getProductById(id);
        if(product == null)
        {
            throw new ProductDoesNotExistException();

        }
        return  product;
    }
    catch (SQLException e)
    {
        System.err.println(e.getMessage());
    throw new FailedToGetProductsException();
    }
    }
    public  int createProduct(ProductRequest product)throws FailedToCreateProductException, InvalidProductException
    {
        try{
            String validation = productValidation.isValidProduct(product);
            if(validation != null)
            {
                throw  new InvalidProductException(validation);
            }
            int id = productDao.createProduct(product);
            if(id == 1)
            {
                throw new FailedToCreateProductException();

            }
            return  id;
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            throw new FailedToCreateProductException();
        }
    }
    public  void updateProduct(int id, ProductRequest productRequest) throws InvalidProductException, ProductDoesNotExistException, FailedToGetProductsException, FailedToUpdateProductException {
        try{
            String validation = productValidation.isValidProduct(productRequest);
            if(validation != null)
            {
                throw new InvalidProductException(validation);
            }
            Product productToUp = productDao.getProductById(id);
            if(productToUp == null)
            {
                throw new ProductDoesNotExistException();
            }
            productDao.updateProduct(id,productRequest);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToUpdateProductException();

        }
    }
    public void deleteProduct(int id) throws ProductDoesNotExistException, FailedToDeleteProductException
    {
    try
    {
        Product productToDelete = productDao.getProductById(id);
        if(productToDelete == null)
        {
    throw new ProductDoesNotExistException();
        }
    } catch (SQLException e) {
        System.err.println(e.getMessage());
        throw new FailedToDeleteProductException();
    }
    }
}
