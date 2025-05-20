package com.user.controller;

import com.user.entities.Inventory;
import com.user.entities.LoginCredentials;
import com.user.entities.OrderItems;
import com.user.entities.Orders;
import com.user.entities.Payment;
import com.user.entities.Supplier;
import com.user.entities.User;
import com.user.exception.InvalidOrderException;
import com.user.exception.InvalidRoleException;
import com.user.exception.InvalidUserIdException;
import com.user.feignclient.FeignToInventory;
import com.user.feignclient.FeignToOrderTable;
import com.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private FeignToOrderTable feign;

    @Mock
    private FeignToInventory feignInv;

    @Mock
    private UserService service;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private UserController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void sample_shouldReturnSessionId() throws InvalidRoleException {
//        String sessionId = "testSessionId";
//        when(request.getSession().getId()).thenReturn(sessionId);
//
//        String result = controller.sample(request);
//
//        assertEquals(sessionId, result);
//        verify(request.getSession(), times(1)).getId();
//    }

//    @Test
//    void register_shouldRegisterUser() throws InvalidRoleException {
//        User user = new User();
//        when(service.add(user)).thenReturn(user);
//
//        User result = controller.register(user);
//
//        assertEquals(user, result);
//        verify(service, times(1)).add(user);
//    }
//
//    @Test
//    void login_shouldReturnVerificationResult() {
//        LoginCredentials credentials = new LoginCredentials();
//        credentials.setUserName("testUser");
//        credentials.setPassword("testPassword");
//        when(service.verification("testUser", "testPassword")).thenReturn("Login Success");
//
//        String result = controller.login(credentials);
//
//        assertEquals("Login Success", result);
//        verify(service, times(1)).verification("testUser", "testPassword");
//    }

    @Test
    void sample_shouldReturnUrls() {
        String expected = "<h1> URLS </h1><br>"
                + "<h2>For User [ 'add', 'viewusers', 'viewbyrole/{role}', '{userid}', 'delete/{userid}' ]</h2><br>"
                + "<h2>For Admin [ 'admin/order(GET)', admin/order/{orderid}(GET),'admin/order(POST)', 'admin/order/{orderid}(DELETE)']</h2><br>"
                + "<h2>For Admin [ 'admin/orderitems(GET)', admin/orderitems/{itemid}(GET), 'admin/orderitems(POST)', 'admin/orderitems/{orderitemid}(DELETE)' ]</h2><br>"
                + "<h2>For Admin [ 'admin/inventory(GET)', admin/inventory/{drugid}(GET),'admin/inventory(POST)', 'admin/inventory/{drugid}(DELETE)' ]</h2><br>"
                + "<h2>For Admin [ 'admin/supplier(GET)', admin/supplier/{supplierid}(GET),'admin/supplier(POST)', 'admin/supplier/{supplierId}(DELETE)' ]</h2><br>"
                + "<h2>For Doctor [ 'doctor/order(GET)','doctor/purchase/{id}(PURCHASE)'";

        String result = controller.sample();

        assertEquals(expected, result);
    }

    @Test
    void add_shouldAddUser() throws InvalidRoleException {
        User user = new User();
        when(service.add(user)).thenReturn(user);

        User result = controller.add(user);

        assertEquals(user, result);
        verify(service, times(1)).add(user);
    }

    @Test
    void allUser_shouldReturnListOfUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        when(service.view()).thenReturn(users);

        List<User> result = controller.allUser();

        assertEquals(users, result);
        verify(service, times(1)).view();
    }

    @Test
    void allUserbyid_shouldReturnUserById() throws InvalidUserIdException {
        int userId = 1;
        User user = new User();
        when(service.viewById(userId)).thenReturn(user);

        User result = controller.allUserbyid(userId);

        assertEquals(user, result);
        verify(service, times(1)).viewById(userId);
    }

    @Test
    void viewUserByRole_shouldReturnListOfUsersByRole() throws InvalidRoleException {
        String role = "ADMIN";
        List<User> users = Arrays.asList(new User(), new User());
        when(service.viewByRole(role)).thenReturn(users);

        List<User> result = controller.viewUserByRole(role);

        assertEquals(users, result);
        verify(service, times(1)).viewByRole(role);
    }

    @Test
    void delete_shouldDeleteUser() throws InvalidUserIdException {
        int userId = 1;
        when(service.delete(userId)).thenReturn("User deleted");

        String result = controller.delete(userId);

        assertEquals("User deleted", result);
        verify(service, times(1)).delete(userId);
    }

    @Test
    void getorder_shouldReturnListOfOrders() {
        List<Orders> orders = Arrays.asList(new Orders(), new Orders());
        when(feign.getorders()).thenReturn(orders);

        List<Orders> result = controller.getorder();

        assertEquals(orders, result);
        verify(feign, times(1)).getorders();
    }

    @Test
    void getorderbyid_shouldReturnOrderById() {
        int orderId = 1;
        Orders order = new Orders();
        when(feign.getordersbyid(orderId)).thenReturn(order);

        Orders result = controller.getorderbyid(orderId);

        assertEquals(order, result);
        verify(feign, times(1)).getordersbyid(orderId);
    }

    @Test
    void addorder_shouldAddOrder() throws InvalidOrderException {
        Orders order = new Orders();
        when(feign.addorder(order)).thenReturn(order);

        Orders result = controller.addorder(order);

        assertEquals(order, result);
        verify(feign, times(1)).addorder(order);
    }

    @Test
    void deleteorder_shouldDeleteOrder() {
        int orderId = 1;
        when(feign.deleteorder(orderId)).thenReturn("Order deleted");

        String result = controller.deleteorder(orderId);

        assertEquals("Order deleted", result);
        verify(feign, times(1)).deleteorder(orderId);
    }

    @Test
    void getorderitems_shouldReturnListOfOrderItems() {
        List<OrderItems> items = Arrays.asList(new OrderItems(), new OrderItems());
        when(feign.getOrderItems()).thenReturn(items);

        List<OrderItems> result = controller.getorderitems();

        assertEquals(items, result);
        verify(feign, times(1)).getOrderItems();
    }

    @Test
    void getorderitems_shouldReturnOrderItemById() {
        int itemId = 1;
        OrderItems item = new OrderItems();
        when(feign.getOrderItemsbyid(itemId)).thenReturn(item);

        OrderItems result = controller.getorderitems(itemId);

        assertEquals(item, result);
        verify(feign, times(1)).getOrderItemsbyid(itemId);
    }

    @Test
    void addorderitems_shouldAddOrderItem() {
        OrderItems item = new OrderItems();
        when(feign.addorderitems(item)).thenReturn(item);

        OrderItems result = controller.addorderitems(item);

        assertEquals(item, result);
        verify(feign, times(1)).addorderitems(item);
    }

    @Test
    void deleteorderitems_shouldDeleteOrderItem() {
        int orderItemId = 1;
        when(feign.deleteorder(orderItemId)).thenReturn("Item deleted");

        String result = controller.deleteorderitems(orderItemId);

        assertEquals("Item deleted", result);
        verify(feign, times(1)).deleteorder(orderItemId);
    }

    @Test
    void viewinventory_shouldReturnListOfInventory() {
        List<Inventory> inventories = Arrays.asList(new Inventory(), new Inventory());
        when(feignInv.getinventory()).thenReturn(inventories);

        List<Inventory> result = controller.viewinventory();

        assertEquals(inventories, result);
        verify(feignInv, times(1)).getinventory();
    }

    @Test
    void viewinventorybyid_shouldReturnInventoryById() {
        int drugId = 1;
        Inventory inventory = new Inventory();
        when(feignInv.getinventorybyid(drugId)).thenReturn(inventory);

        Inventory result = controller.viewinventorybyid(drugId);

        assertEquals(inventory, result);
        verify(feignInv, times(1)).getinventorybyid(drugId);
    }

    @Test
    void addinventory_shouldAddInventory() {
        Inventory inventory = new Inventory();
        when(feignInv.addinventory(inventory)).thenReturn(inventory);

        Inventory result = controller.addinventory(inventory);

        assertEquals(inventory, result);
        verify(feignInv, times(1)).addinventory(inventory);
    }

    @Test
    void deleteinventory_shouldDeleteInventory() {
        int drugId = 1;
        when(feignInv.deleteinventory(drugId)).thenReturn("Inventory deleted");

        String result = controller.deleteinventory(drugId);

        assertEquals("Inventory deleted", result);
        verify(feignInv, times(1)).deleteinventory(drugId);
    }

    @Test
    void viewsup_shouldReturnListOfSuppliers() {
        List<Supplier> suppliers = Arrays.asList(new Supplier(), new Supplier());
        when(feignInv.getsupplier()).thenReturn(suppliers);

        List<Supplier> result = controller.viewsup();

        assertEquals(suppliers, result);
        verify(feignInv, times(1)).getsupplier();
    }

    @Test
    void viewsup_shouldReturnSupplierById() {
        int supplierId = 1;
        Supplier supplier = new Supplier();
        when(feignInv.getsupplierbyid(supplierId)).thenReturn(supplier);

        Supplier result = controller.viewsup(supplierId);

        assertEquals(supplier, result);
        verify(feignInv, times(1)).getsupplierbyid(supplierId);
    }

    @Test
    void addsup_shouldAddSupplier() {
        Supplier supplier = new Supplier();
        when(feignInv.addsupplier(supplier)).thenReturn(supplier);

        Supplier result = controller.addsup(supplier);

        assertEquals(supplier, result);
        verify(feignInv, times(1)).addsupplier(supplier);
    }

    @Test
    void deletesup_shouldDeleteSupplier() {
        int supplierId = 1;
        when(feignInv.deletesupplier(supplierId)).thenReturn("Supplier deleted");

        String result = controller.deletesup(supplierId);

        assertEquals("Supplier deleted", result);
        verify(feignInv, times(1)).deletesupplier(supplierId);
    }

    @Test
    void getdoctororder_shouldReturnListOfOrderItems_doctor() {
        List<OrderItems> items = Arrays.asList(new OrderItems(), new OrderItems());
        when(feign.getOrderItems()).thenReturn(items);

        List<OrderItems> result = controller.getdoctororder();

        assertEquals(items, result);
        verify(feign, times(1)).getOrderItems();
    }

//    @Test
//    void buy_shouldUpdateOrder_doctor() {
//        int userId = 1;
//        int orderId = 1;
//        Orders order = new Orders();
//        when(feign.updateOrder(userId,orderId, "PLACED")).thenReturn(order);
//
//        Orders result = controller.buy(userId, orderId);
//
//        assertEquals(order, result);
//        verify(feign, times(1)).updateOrder(userId,orderId, "PLACED");
//    }

//    @Test
//    void payment_shouldReturnPaymentList_doctor() {
//        int userId = 1;
//        List<Payment> payments = Arrays.asList(new Payment(), new Payment());
//        when(feign.getPaymentBuUserId(userId)).thenReturn(payments);
//
//        List<Payment> result = controller.payment(userId);
//
//        assertEquals(payments, result);
//        verify(feign, times(1)).getPaymentBuUserId(userId);
//    }
}