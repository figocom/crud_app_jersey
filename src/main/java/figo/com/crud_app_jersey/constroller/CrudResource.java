package figo.com.crud_app_jersey.constroller;


import figo.com.crud_app_jersey.domain.Product;
import figo.com.crud_app_jersey.domain.ProductType;
import figo.com.crud_app_jersey.dtos.ProductDto;
import figo.com.crud_app_jersey.service.ApplicationService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.ibatis.annotations.Param;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/crud")
public class CrudResource {
    private final ApplicationService service = ApplicationService.getInstance();

    @GET
    @Path("{id}")
    public Response getProduct(@PathParam("id") Integer id) {
        Product product = service.getProduct(id);
        if (Objects.isNull(product)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(product, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/productTypes")
    public Response getProductTyp() {
        List<ProductType> products = service.getAllProductTypes();
        if (Objects.isNull(products)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(products, MediaType.APPLICATION_JSON).build();
    }
    @GET
    @Path("/productType/{id}")
    public Response getProductType(@PathParam("id") Integer id ) {
        ProductType products = service.getProductType(id);
        if (Objects.isNull(products)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(products, MediaType.APPLICATION_JSON).build();
    }
    @GET
    @Path("/getAll")
    public Response getProductPage() {
        List<Product> products = service.getProducts();
        if (products.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(products, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByType(@QueryParam("typeId") Integer typeID) {
        List<Product> products = service.getProductsByType(typeID);
        if (products.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(products, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/addProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(  ProductDto productDto){

        Product savedProduct = service.createProduct(productDto);
        URI uri = URI.create("crudApi/crud/" +savedProduct.getId() );
        return Response.created(uri).build();
    }

    @POST
    @Path("/addType")
    public Response createProductType(@QueryParam("name_uz") String name_uz) {
        if (Objects.isNull(name_uz)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ProductType savedProducttype = service.createProductType(ProductType.builder().name_uz(name_uz).build());
        if (Objects.isNull(savedProducttype)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        URI uri = URI.create("crudApi/crud/" + savedProducttype.getId());
        return Response.created(uri).build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(Product product) {
        Product updateProduct = service.updateProduct(product);
        if (Objects.isNull(updateProduct)) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
        return Response.ok(product, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduct(@PathParam("id") Integer id) {
        if (service.deleteProduct(id)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_MODIFIED).build();
    }
}