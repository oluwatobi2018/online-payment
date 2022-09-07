import java.net.MalformedURLException; 
import java.net.URL; 
 
import com.mypos.myposcheckout.ipc.Config; 
import com.mypos.myposcheckout.ipc.IPCException; 
 
// ... 
 
Config cnf = new Config(); 
 
URL ipcApiUrl = null; 
try { 
    ipcApiUrl = new URL("https://mypos.com/vmp/checkout-test/"); 
} catch (MalformedURLException ex) { 
    // Handle the malformed URL exception 
} 
 
cnf.setIpcUrl(ipcApiUrl); 
cnf.setLang("en"); 
cnf.loadPrivateKeyFromFile("path_to_directory/storePrivateKey.pem"); // Replace `path_to_directory` with the actual file path 
cnf.loadPublicKeyFromFile("path_to_directory/apiPublicKey.pem"); // Replace `path_to_directory` with the actual file path 
cnf.setKeyIndex(1); 
cnf.setSid("000000000000010"); 
cnf.setVersion("1.3"); 
cnf.setWalletNumber("61938166610");
import com.mypos.myposcheckout.ipc.Customer; 
 
// ... 
 
Customer customer = new Customer(); 
customer.setFirstName("John"); 
customer.setLastName("Smith"); 
customer.setEmail("demo@demo.demo"); 
customer.setPhone("+359888123456"); 
customer.setCountry("BGR"); 
customer.setAddress("Business Park Varna"); 
customer.setCity("Varna"); 
customer.setZip("9000");
import com.mypos.myposcheckout.ipc.Cart; 
import com.mypos.myposcheckout.ipc.CartItem; 
import com.mypos.myposcheckout.ipc.IPCException; 

// ... 

Cart cart = new Cart(); 
cart.addItem(new CartItem("Some Book", 101.23, 2)); 
cart.addItem(new CartItem("Some other book", 1.25, 8)); 
cart.addItem(new CartItem("Stuff", 0.63, 5)); 
cart.addItem(new CartItem("Discount", -5.3, 1));
import java.net.MalformedURLException; 
import java.net.URL; 

import com.mypos.myposcheckout.ipc.enumerable.Currency; 
import com.mypos.myposcheckout.ipc.request.Purchase; 

// ... 

Purchase purchase = new Purchase(cnf); 
try { 
    purchase.setCancelUrl(new URL("https://mysite.com/ipc_cancel")); // User comes here after purchase cancelation 
    purchase.setSuccessUrl(new URL("https://mysite.com/ipc_ok")); // User comes here after purchase success 
    purchase.setNotifyUrl(new URL("https://mysite.com/ipc_notify")); // IPC sends POST reuquest to this address with purchase status 
} catch (MalformedURLException ex) { 
    // Handle the malformed URL exception 
} 
purchase.setOrderId("123456"); // Some unique ID 
purchase.setCurrency(Currency.EUR); 
purchase.setNote("Some note"); // Not required 
purchase.setCardTokenRequest(CardTokenRequest.DO_NOT_REQUEST_TOKEN); 
purchase.setParametersRequired(PaymentParametersRequired.FULL_REQUEST); 
purchase.setCustomer(customer); 
purchase.setCart(cart);
	
import com.mypos.myposcheckout.ipc.IPCException; 
 
// ... 
 
try { 
    purchase.process(); 
} catch (IPCException ex) { 
    // Invalid parameters. To see details use `ex.getMessage();`. 
}
	
import java.net.MalformedURLException; 
import java.net.URL; 
 
import com.mypos.myposcheckout.ipc.Config; 
import com.mypos.myposcheckout.ipc.IPCException; 
 
// ... 
 
Config cnf = new Config(); 
 
URL ipcApiUrl = null; 
try { 
    ipcApiUrl = new URL("https://mypos.com/vmp/checkout-test/"); 
} catch (MalformedURLException ex) { 
    // Handle the malformed URL exception 
} 
 
cnf.setIpcUrl(ipcApiUrl); 
cnf.setLang("en"); 
cnf.loadPrivateKeyFromFile("path_to_directory/storePrivateKey.pem"); // Replace `path_to_directory` with the actual file path 
cnf.loadPublicKeyFromFile("path_to_directory/apiPublicKey.pem"); // Replace `path_to_directory` with the actual file path 
cnf.setKeyIndex(1); 
cnf.setSid("000000000000010"); 
cnf.setVersion("1.3"); 
cnf.setWalletNumber("61938166610");
import com.mypos.myposcheckout.ipc.request.RequestMoney; 

// ... 

RequestMoney request = new RequestMoney(cnf);
import java.math.BigDecimal; 

import com.mypos.myposcheckout.ipc.enumerable.CommunicationFormat; 
import com.mypos.myposcheckout.ipc.enumerable.Currency; 

// ... 

request.setMandateReference("126ca831-93d2-4dfc-ab1f-0cce1d0abe9e"); 
request.setCustomerWalletNumber("61938166612"); 
request.setOrderId("201203319999999"); 
request.setAmount(BigDecimal.valueOf(23.45)); 
request.setCurrency(Currency.EUR); 
request.setReason("test reason"); 
request.setOutputFormat(CommunicationFormat.XML);
import com.mypos.myposcheckout.ipc.IPCException; 
import com.mypos.myposcheckout.ipc.enumerable.StatusCode; 
import com.mypos.myposcheckout.ipc.response.BasicResponse; 

// ... 

BasicResponse ipcResponse = request.process(); 
ipcResponse.processApiResponse(); 
if (ipcResponse.getStatusCode() == StatusCode.SUCCESS) { 
    // Success 
}
	
import java.net.MalformedURLException; 
import java.net.URL; 
 
import com.mypos.myposcheckout.ipc.Config; 
import com.mypos.myposcheckout.ipc.IPCException; 
 
// ... 
 
Config cnf = new Config(); 
 
URL ipcApiUrl = null; 
try { 
    ipcApiUrl = new URL("https://mypos.com/vmp/checkout-test/"); 
} catch (MalformedURLException ex) { 
    // Handle the malformed URL exception 
} 
 
cnf.setIpcUrl(ipcApiUrl); 
cnf.setLang("en"); 
cnf.loadPrivateKeyFromFile("path_to_directory/storePrivateKey.pem"); // Replace `path_to_directory` with the actual file path 
cnf.loadPublicKeyFromFile("path_to_directory/apiPublicKey.pem"); // Replace `path_to_directory` with the actual file path 
cnf.setKeyIndex(1); 
cnf.setSid("000000000000010"); 
cnf.setVersion("1.3"); 
cnf.setWalletNumber("61938166610");
	
import com.mypos.myposcheckout.ipc.IPCException; 
import com.mypos.myposcheckout.ipc.enumerable.CommunicationFormat; 
import com.mypos.myposcheckout.ipc.response.BasicResponse; 
 
// ... 
 
try { 
    // `postPayload` should contain the POST request body 
    BasicResponse notification = new BasicResponse(cnf, postPayload, CommunicationFormat.POST); 
    notification.processApiResponse(); 
} catch (IPCException ex) { 
    // Error 
}
import java.util.Map; 

import com.mypos.myposcheckout.ipc.Constants; 

// ... 

Map<String, String> notificationData = notification.getDataNormalized(); 
if (/* ... check and update order */) { 
    // Output `Constants.SUCCESSFUL_RESPONSE` ("OK") 
} else { 
    // Output anything different from `Constants.SUCCESSFUL_RESPONSE` ("OK") 
}
