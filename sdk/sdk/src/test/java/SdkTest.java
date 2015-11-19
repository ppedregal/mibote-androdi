

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thinknowa.botin.sdk.Sdk;
import com.thinknowa.botin.sdk.exceptions.SdkException;
import com.thinknowa.botin.sdk.interceptors.LoggingInterceptor;
import com.thinknowa.botin.sdk.model.Account;
import com.thinknowa.botin.sdk.model.Tin;

/**
 * Created by ppedregal on 17/11/15.
 */
public class SdkTest {

    private Sdk sdk;

    @Before
    public void setup(){
        sdk = new Sdk("http://172.26.0.223:3000/api/");
        sdk.logLevel(LoggingInterceptor.Level.BODY);

    }
    
    @Test
    public void testAccounts() throws IOException, InterruptedException {

        Assert.assertNull(sdk.accessToken());

        sdk.login("email1@example.com", "test");

        Assert.assertNotNull(sdk.accessToken());

        Account user = sdk.me();

        Assert.assertNotNull(user);

        sdk.logout();

        Assert.assertNull(sdk.accessToken());

        sdk.reset("email1@example.com");

    }

    @Test
    public void testBotes(){

        sdk.login("email1@example.com", "test");
        List<Tin> account1BotesBefore = sdk.tins();
        Assert.assertNotNull(account1BotesBefore);
        Tin account1Tin = sdk.createTin("nuevo bote");
        Assert.assertNotNull(account1Tin);
        List<Tin> account1BotesAfter = sdk.tins();
        Assert.assertNotNull(account1BotesAfter);
        Assert.assertEquals(account1BotesBefore.size() + 1, account1BotesAfter.size());

        sdk.logout();

        sdk.login("email2@example.com", "test");
        List<Tin> account2Tins = sdk.tins();
        Assert.assertNotNull(account2Tins);
        Assert.assertEquals(0, account2Tins.size());

    }
    
    @Test
    public void createAccounts(){
    	try {
    		sdk.register("email3@example.com", "test");
    	} catch (SdkException ex){
//    		ex.printStackTrace();
    	}
    	try {
    		sdk.register("email4@example.com", "test");
    	} catch (SdkException ex){
//    		ex.printStackTrace();
    	}
    }
    
}
