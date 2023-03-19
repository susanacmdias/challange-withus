package org.acme;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.math.BigInteger;


@Path("/labseq")
public class Calculate_labseq {
    Cache myCache = new Cache(30000);
    BigInteger nr = BigInteger.valueOf(0);

    @GET
    @Path("{n}")
    public BigInteger get_labseq(@PathParam("n") int n) {
        String error_message = "The entered number must be positive and integer";
        if(n<0){
            throw new WebApplicationException(Response.status(400).entity(error_message).build());
        }
        nr = calculate_labseq(n);
        return nr;
    }

    /*Method used to calculate the labseq,
    Uses Cache to get values calculated before or calculated during the process to get the final value
    If the number is not in cache it will calculate it
    Here is used BigInteger since numbers like l(10000) need more memory than long can offer.*/
    public BigInteger calculate_labseq(int nr) {

        BigInteger l0 = BigInteger.valueOf(0);
        BigInteger l1 = BigInteger.valueOf(1);
        BigInteger l2 = BigInteger.valueOf(0);
        BigInteger l3 = BigInteger.valueOf(1);
        BigInteger fn = BigInteger.valueOf(0);

        if (nr == 0 || nr == 2) return l0;
        else if (nr == 1 || nr == 3) return l1;
        if(myCache.get(nr) == null){
            for (int i = 4; i <= nr; i++) {
                fn = l0.add(l1);
                l0 = l1;
                l1 = l2;
                l2 = l3;
                l3 = fn;
                myCache.put(i, fn);
            }
            return fn;

        }else{
            return (BigInteger) myCache.get(nr);
        }
}
}