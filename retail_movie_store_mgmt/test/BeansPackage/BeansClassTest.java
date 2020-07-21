/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansPackage;

import java.util.Timer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import retail_movie_store_mgmt.AddMediaItemsSaleController;
import retail_movie_store_mgmt.AddMediaSaleFormController;
import retail_movie_store_mgmt.AddMediaSaleLogic;
import retail_movie_store_mgmt.AddSoftwareSaleFormController;
import retail_movie_store_mgmt.AddSoftwareSaleLogic;
import retail_movie_store_mgmt.Customer.Customer;
import retail_movie_store_mgmt.CustomerManagerController;
import retail_movie_store_mgmt.DeleteMediaSaleFormController;
import retail_movie_store_mgmt.EnterProductController;
import retail_movie_store_mgmt.ForgotPasswordController;
import retail_movie_store_mgmt.HandleFxmlPages;
import retail_movie_store_mgmt.HomeController;
import retail_movie_store_mgmt.HomeLogic;
import retail_movie_store_mgmt.Logic.HandleCustomerLogic;
import retail_movie_store_mgmt.Logic.HandleMainLogic;
import retail_movie_store_mgmt.Logic.HandleMediaPurchaseLogic;
import retail_movie_store_mgmt.Logic.HandlePasswordLogic;
import retail_movie_store_mgmt.Logic.HandlePreorderLogic;
import retail_movie_store_mgmt.Logic.HandleProductLogic;
import retail_movie_store_mgmt.Logic.HandleProfileLogic;
import retail_movie_store_mgmt.Logic.HandleQuestionsLogic;
import retail_movie_store_mgmt.Logic.HandleSoftwarePurchaseLogic;
import retail_movie_store_mgmt.Logic.HandleTodoLogic;
import retail_movie_store_mgmt.Logic.HandleUsersLogic;
import retail_movie_store_mgmt.Logic.SoftwareLogic;
import retail_movie_store_mgmt.LoginPageController;
import retail_movie_store_mgmt.LogsLogic;
import retail_movie_store_mgmt.NewPasswordController;
import retail_movie_store_mgmt.ProfileManagerController;
import retail_movie_store_mgmt.Sales.MediaAndCustomSaleEntry;
import retail_movie_store_mgmt.Sales.MediaInvoice;
import retail_movie_store_mgmt.Sales.SaleEntry;
import retail_movie_store_mgmt.Sales.SaleIdGen;
import retail_movie_store_mgmt.Sales.SoftwareInvoice;
import retail_movie_store_mgmt.Sales.SoftwareSaleEntry;
import retail_movie_store_mgmt.SoftwareInventoryController;
import retail_movie_store_mgmt.ToDoController;
import retail_movie_store_mgmt.User;
import retail_movie_store_mgmt.commonUtil.Calculator;
import retail_movie_store_mgmt.commonUtil.DateTime;
import retail_movie_store_mgmt.database.CustomerHandle;
import retail_movie_store_mgmt.database.Database_start_stop;
import retail_movie_store_mgmt.database.Logs;
import retail_movie_store_mgmt.database.MediaPurchaseHandle;
import retail_movie_store_mgmt.database.PasswordsHandle;
import retail_movie_store_mgmt.database.PreorderHandle;
import retail_movie_store_mgmt.database.ProductHandle;
import retail_movie_store_mgmt.database.SecurityQueryCheck;
import retail_movie_store_mgmt.database.SecurityQuestionsHandle;
import retail_movie_store_mgmt.database.SoftwareHandle;
import retail_movie_store_mgmt.database.SoftwarePurchaseHandle;
import retail_movie_store_mgmt.database.TodoHandle;
import retail_movie_store_mgmt.database.UsersHandle;
import retail_movie_store_mgmt.database.nosql.MediaSalesHandle;
import retail_movie_store_mgmt.database.nosql.SoftwareSalesHandle;
import retail_movie_store_mgmt.preorders.Preorder;
import retail_movie_store_mgmt.product.Product;
import retail_movie_store_mgmt.product.Software;
import retail_movie_store_mgmt.purchases.media.MediaPurchase;
import retail_movie_store_mgmt.purchases.software.SoftwarePurchase;
import retail_movie_store_mgmt.report.BarChartBuild;
import retail_movie_store_mgmt.report.CustomerReport;
import retail_movie_store_mgmt.report.LineChartBuild;
import retail_movie_store_mgmt.report.LogsReport;
import retail_movie_store_mgmt.report.MediaProductSubReport;
import retail_movie_store_mgmt.report.MediaPurchasesReport;
import retail_movie_store_mgmt.report.MediaPurchasesSubReport;
import retail_movie_store_mgmt.report.MediaSalesReport;
import retail_movie_store_mgmt.report.MediaSalesSubReport;
import retail_movie_store_mgmt.report.PieChartBuild;
import retail_movie_store_mgmt.report.PreorderReport;
import retail_movie_store_mgmt.report.PreorderSubReport;
import retail_movie_store_mgmt.report.SoftwarePurchasesReport;
import retail_movie_store_mgmt.report.SoftwarePurchasesSubReport;
import retail_movie_store_mgmt.report.SoftwareSalesReport;
import retail_movie_store_mgmt.report.SoftwareSalesSubReport;
import retail_movie_store_mgmt.security.Hashing;
import retail_movie_store_mgmt.todo.Todo;
import retail_movie_store_mgmt.ui.DialogPane;
import retail_movie_store_mgmt.ui.PieChartBuilder;
import retail_movie_store_mgmt.ui.RoleBarchartBuilder;
import retail_movie_store_mgmt.ui.TrayNotice;

/**
 *
 * @author Ian Mburu
 */
public class BeansClassTest {
    
    public BeansClassTest() {
        
    }
    
    /**
     * Test of beansClass method, of class BeansClass.
     */
    @Test
    public void testBeansClass() {
        System.out.println("beansClass");
        BeansClass result = BeansClass.beansClass();
        
        assertNotNull("Ensuring beans class is not null", result);
    }

    /**
     * Test of database_begin_end method, of class BeansClass.
     */
    @Test
    public void testDatabase_begin_end() {
        System.out.println("database_begin_end");
        Database_start_stop expResult = new Database_start_stop();
        Database_start_stop result = BeansClass.database_begin_end();
        assertNotNull("Does not return a null object",
                result);
        assertEquals("Objects of the database class",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of dataSource method, of class BeansClass.
     */
    @Test
    @Ignore(value="Ignore until we can test for database connection")
    public void testDataSource() {
        
    }

    /**
     * Test of jdbcTemplate method, of class BeansClass.
     */
    @Test
    public void testJdbcTemplate() {
        JdbcTemplate jdbcTemp = BeansClass.jdbcTemplate(BeansClass.dataSource());        
        assertNotNull("Checking that jdbc template is not null",jdbcTemp);
    }

    /**
     * Test of user method, of class BeansClass.
     */
    @Test
    public void testUser() {
        System.out.println("user");
        User expResult = new User();
        User result = BeansClass.user();
        assertNotNull("Checking that User object is not null",
                result);
        assertEquals("Object of User class",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of security_query_check method, of class BeansClass.
     */
    @Test
    public void testSecurity_query_check() {
        System.out.println("security_query_check");
        SecurityQueryCheck expResult = new SecurityQueryCheck();
        SecurityQueryCheck result = BeansClass.security_query_check();
        assertNotNull("Checking that SecurityQueryCheck object is not null",
                result);
        assertEquals("Should return security_query_check class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of securityQuestionsHandle method, of class BeansClass.
     */
    @Test
    public void testSecurityQuestionsHandle() {
        System.out.println("securityQuestionsHandle");
        SecurityQuestionsHandle expResult = new SecurityQuestionsHandle();
        SecurityQuestionsHandle result = BeansClass.securityQuestionsHandle();
        assertNotNull("Checking that SecurityQuestionsHandle object is not null",
                result);
        assertEquals("Should return securityQuestionsHandle class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of handleQuestionsLogic method, of class BeansClass.
     */
    @Test
    public void testHandleQuestionsLogic() {
        System.out.println("handleQuestionsLogic");
        HandleQuestionsLogic expResult = new HandleQuestionsLogic();
        HandleQuestionsLogic result = BeansClass.handleQuestionsLogic();
        assertNotNull("Checking that HandleQuestionsLogic object is not null",
                result);
        assertEquals("Should return HandleQuestionsLogic class object",
                expResult.getClass(), result.getClass());
    }


    /**
     * Test of handlePasswordLogic method, of class BeansClass.
     */
    @Test
    public void testHandlePasswordLogic() {
        System.out.println("handlePasswordLogic");
        HandlePasswordLogic expResult = new HandlePasswordLogic();
        HandlePasswordLogic result = BeansClass.handlePasswordLogic();
        assertNotNull("Checking that HandlePasswordLogic object is not null",
                result);
        assertEquals("Should return HandlePasswordLogic class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of passwordsHandle method, of class BeansClass.
     */
    @Test
    public void testPasswordsHandle() {
        System.out.println("passwordsHandle");
        PasswordsHandle expResult = new PasswordsHandle();
        PasswordsHandle result = BeansClass.passwordsHandle();
        assertNotNull("Checking that PasswordsHandle object is not null",
                result);
        assertEquals("Should return PasswordsHandle class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of usersHandle method, of class BeansClass.
     */
    @Test
    public void testUsersHandle() {
        System.out.println("usersHandle");
        UsersHandle expResult = new UsersHandle();
        UsersHandle result = BeansClass.usersHandle();
        assertNotNull("Checking that UsersHandle object is not null",
                result);
        assertEquals("Should return UsersHandle class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of handleUsersLogic method, of class BeansClass.
     */
    @Test
    public void testHandleUsersLogic() {
        System.out.println("handleUsersLogic");
        HandleUsersLogic expResult = new HandleUsersLogic();
        HandleUsersLogic result = BeansClass.handleUsersLogic();
        assertNotNull("Checking that HandleUsersLogic object is not null",
                result);
        assertEquals("Should return HandleUsersLogic class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of hashing method, of class BeansClass.
     */
    @Test
    public void testHashing() {
        System.out.println("hashing");
        Hashing expResult = new Hashing();
        Hashing result = BeansClass.hashing();
        assertNotNull("Checking that Hashing object is not null",
                result);
        assertEquals("Should return Hashing class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of loginPageController method, of class BeansClass.
     */
    @Test
    public void testLoginPageController() {
        System.out.println("loginPageController");
        LoginPageController expResult = new LoginPageController();
        LoginPageController result = BeansClass.loginPageController();
        assertNotNull("Checking that LoginPageController object is not null",
                result);
        assertEquals("Should return LoginPageController class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of handleMainLogic method, of class BeansClass.
     */
    @Test
    public void testHandleMainLogic() {
        System.out.println("handleMainLogic");
        HandleMainLogic expResult = new HandleMainLogic();
        HandleMainLogic result = BeansClass.handleMainLogic();
        assertNotNull("Checking that HandleMainLogic object is not null",
                result);
        assertEquals("Should return HandleMainLogic class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of handleFxmlPages method, of class BeansClass.
     */
    @Test
    public void testHandleFxmlPages() {
        System.out.println("handleFxmlPages");
        HandleFxmlPages expResult = new HandleFxmlPages();
        HandleFxmlPages result = BeansClass.handleFxmlPages();
        assertNotNull("Checking that HandleFxmlPages object is not null",
                result);
        assertEquals("Should return HandleFxmlPages class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of newPasswordController method, of class BeansClass.
     */
    @Test
    public void testNewPasswordController() {
        System.out.println("newPasswordController");
        NewPasswordController expResult = new NewPasswordController();
        NewPasswordController result = BeansClass.newPasswordController();
        assertNotNull("Checking that NewPasswordController object is not null",
                result);
        assertEquals("Should return NewPasswordController class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of dateTime method, of class BeansClass.
     */
    @Test
    public void testDateTime() {
        System.out.println("dateTime");
        DateTime expResult = new DateTime();
        DateTime result = BeansClass.dateTime();
        assertNotNull("Checking that DateTime object is not null",
                result);
        assertEquals("Should return DateTime class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of logs method, of class BeansClass.
     */
    @Test
    public void testLogs() {
        System.out.println("logs");
        Logs expResult = new Logs();
        Logs result = BeansClass.logs();
        assertNotNull("Checking that Logs object is not null",
                result);
        assertEquals("Should return Logs class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of logsLogic method, of class BeansClass.
     */
    @Test
    public void testLogsLogic() {
        System.out.println("logsLogic");
        LogsLogic expResult = new LogsLogic();
        LogsLogic result = BeansClass.logsLogic();
        assertNotNull("Checking that LogsLogic object is not null",
                result);
        assertEquals("Should return LogsLogic class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of forgotPasswordController method, of class BeansClass.
     */
    @Test
    public void testForgotPasswordController() {
        System.out.println("forgotPasswordController");
        ForgotPasswordController expResult = new ForgotPasswordController();
        ForgotPasswordController result = BeansClass.forgotPasswordController();
        assertNotNull("Checking that ForgotPasswordController object is not null",
                result);
        assertEquals("Should return ForgotPasswordController class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of productHandle method, of class BeansClass.
     */
    @Test
    public void testProductHandle() {
        System.out.println("productHandle");
        ProductHandle expResult = new ProductHandle();
        ProductHandle result = BeansClass.productHandle();
        assertNotNull("Checking that ProductHandle object is not null",
                result);
        assertEquals("Should return ProductHandle class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of enterProductController method, of class BeansClass.
     */
    @Test
    public void testEnterProductController() {
        System.out.println("enterProductController");
        EnterProductController expResult = new EnterProductController();
        EnterProductController result = BeansClass.enterProductController();
        assertNotNull("Checking that EnterProductController object is not null",
                result);
        assertEquals("Should return EnterProductController class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of handleProductLogic method, of class BeansClass.
     */
    @Test
    public void testHandleProductLogic() {
        System.out.println("handleProductLogic");
        HandleProductLogic expResult = new HandleProductLogic();
        HandleProductLogic result = BeansClass.handleProductLogic();
        assertNotNull("Checking that HandleProductLogic object is not null",
                result);
        assertEquals("Should return HandleProductLogic class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of handleProfileLogic method, of class BeansClass.
     */
    @Test
    public void testHandleProfileLogic() {
        System.out.println("handleProfileLogic");
        HandleProfileLogic expResult = new HandleProfileLogic();
        HandleProfileLogic result = BeansClass.handleProfileLogic();
        assertNotNull("Checking that HandleProfileLogic object is not null",
                result);
        assertEquals("Should return HandleProfileLogic class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of product method, of class BeansClass.
     */
    @Test
    public void testProduct_0args() {
        System.out.println("product");
        Product expResult = new Product();
        Product result = BeansClass.product();
        assertNotNull("Checking that Product object is not null",
                result);
        assertEquals("Should return Product class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of product method, of class BeansClass.
     */
    @Test
    public void testProduct_String() {
        System.out.println("product");
        String title = "test";
        Product expResult = new Product(title);
        Product result = BeansClass.product(title);
        assertNotNull("Checking that Product object is not null",
                result);
        assertEquals("Should return Product class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of softwareLogic method, of class BeansClass.
     */
    @Test
    public void testSoftwareLogic() {
        System.out.println("softwareLogic");
        SoftwareLogic expResult = new SoftwareLogic();
        SoftwareLogic result = BeansClass.softwareLogic();
        assertNotNull("Checking that SoftwareLogic object is not null",
                result);
        assertEquals("Should return SoftwareLogic class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of softwareHandle method, of class BeansClass.
     */
    @Test
    public void testSoftwareHandle() {
        System.out.println("softwareHandle");
        SoftwareHandle expResult = new SoftwareHandle();
        SoftwareHandle result = BeansClass.softwareHandle();
        assertNotNull("Checking that SoftwareHandle object is not null",
                result);
        assertEquals("Should return SoftwareHandle class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of software method, of class BeansClass.
     */
    @Test
    public void testSoftware() {
        System.out.println("software");
        String title = "test";
        Software expResult = new Software(title);
        Software result = BeansClass.software(title);
        assertNotNull("Checking that Software object is not null",
                result);
        assertEquals("Should return Software class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of softwareInventoryController method, of class BeansClass.
     */
    @Test
    public void testSoftwareInventoryController() {
        System.out.println("softwareInventoryController");
        SoftwareInventoryController expResult = new SoftwareInventoryController();
        SoftwareInventoryController result = BeansClass.softwareInventoryController();
        assertNotNull("Checking that SoftwareInventoryController object is not null",
                result);
        assertEquals("Should return SoftwareInventoryController class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of homeLogic method, of class BeansClass.
     */
    @Test
    public void testHomeLogic() {
        System.out.println("homeLogic");
        HomeLogic expResult = new HomeLogic() ;
        HomeLogic result = BeansClass.homeLogic();
        assertNotNull("Checking that HomeLogic object is not null",
                result);
        assertEquals("Should return HomeLogic class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of homeController method, of class BeansClass.
     */
    @Test
    public void testHomeController() {
        System.out.println("homeController");
        HomeController expResult = new HomeController();
        HomeController result = BeansClass.homeController();
        assertNotNull("Checking that HomeController object is not null",
                result);
        assertEquals("Should return HomeController class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of calculator method, of class BeansClass.
     */
    @Test
    public void testCalculator() {
        System.out.println("calculator");
        Calculator expResult = new Calculator();
        Calculator result = BeansClass.calculator();
        assertNotNull("Checking that Calculator object is not null",
                result);
        assertEquals("Should return Calculator class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of addMediaSaleLogic method, of class BeansClass.
     */
    @Test
    public void testAddMediaSaleLogic() {
        System.out.println("addMediaSaleLogic");
        AddMediaSaleLogic expResult = new AddMediaSaleLogic();
        AddMediaSaleLogic result = BeansClass.addMediaSaleLogic();
        assertNotNull("Checking that AddMediaSaleLogic object is not null",
                result);
        assertEquals("Should return AddMediaSaleLogic class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of addSoftwareSaleLogic method, of class BeansClass.
     */
    @Test
    public void testAddSoftwareSaleLogic() {
        System.out.println("addSoftwareSaleLogic");
        AddSoftwareSaleLogic expResult = new AddSoftwareSaleLogic();
        AddSoftwareSaleLogic result = BeansClass.addSoftwareSaleLogic();
        assertNotNull("Checking that AddSoftwareSaleLogic object is not null",
                result);
        assertEquals("Should return AddSoftwareSaleLogic class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of addMediaSaleFormController method, of class BeansClass.
     */
    @Test
    public void testAddMediaSaleFormController() {
        System.out.println("addMediaSaleFormController");
        AddMediaSaleFormController expResult = new AddMediaSaleFormController();
        AddMediaSaleFormController result = BeansClass.addMediaSaleFormController();
        assertNotNull("Checking that AddMediaSaleFormController object is not null",
                result);
        assertEquals("Should return AddMediaSaleFormController class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of deleteMediaSaleFormController method, of class BeansClass.
     */
    @Test
    public void testDeleteMediaSaleFormController() {
        System.out.println("deleteMediaSaleFormController");
        DeleteMediaSaleFormController expResult = new DeleteMediaSaleFormController();
        DeleteMediaSaleFormController result = BeansClass.deleteMediaSaleFormController();
        assertNotNull("Checking that DeleteMediaSaleFormController object is not null",
                result);
        assertEquals("Should return DeleteMediaSaleFormController class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of addMediaSaleController method, of class BeansClass.
     */
    @Test
    public void testAddMediaSaleController() {
        System.out.println("addMediaSaleController");
        AddMediaItemsSaleController expResult = new AddMediaItemsSaleController();
        AddMediaItemsSaleController result = BeansClass.addMediaSaleController();
        assertNotNull("Checking that AddMediaItemsSaleController object is not null",
                result);
        assertEquals("Should return AddMediaItemsSaleController class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of sale method, of class BeansClass.
     */
    @Test
    public void testSale() {
        System.out.println("sale");
        SaleEntry expResult = new SaleEntry();
        SaleEntry result = BeansClass.sale();
        assertNotNull("Checking that SaleEntry object is not null",
                result);
        assertEquals("Should return SaleEntry class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of mediaAndCustomSaleEntry method, of class BeansClass.
     */
    @Test
    public void testMediaAndCustomSaleEntry() {
        System.out.println("mediaAndCustomSaleEntry");
        MediaAndCustomSaleEntry expResult = new MediaAndCustomSaleEntry();
        MediaAndCustomSaleEntry result = BeansClass.mediaAndCustomSaleEntry();
        assertNotNull("Checking that MediaAndCustomSaleEntry object is not null",
                result);
        assertEquals("Should return MediaAndCustomSaleEntry class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of addMediaItemsSaleController method, of class BeansClass.
     */
    @Test
    public void testAddMediaItemsSaleController() {
        System.out.println("addMediaItemsSaleController");
        AddMediaItemsSaleController expResult = new AddMediaItemsSaleController();
        AddMediaItemsSaleController result = BeansClass.addMediaItemsSaleController();
        assertNotNull("Checking that AddMediaItemsSaleController object is not null",
                result);
        assertEquals("Should return AddMediaItemsSaleController class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of softwareSaleEntry method, of class BeansClass.
     */
    @Test
    public void testSoftwareSaleEntry() {
        System.out.println("softwareSaleEntry");
        SoftwareSaleEntry expResult = new SoftwareSaleEntry();
        SoftwareSaleEntry result = BeansClass.softwareSaleEntry();
        assertNotNull("Checking that SoftwareSaleEntry object is not null",
                result);
        assertEquals("Should return SoftwareSaleEntry class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of mediaSalesHandle method, of class BeansClass.
     */
    @Test
    public void testMediaSalesHandle() {
        System.out.println("mediaSalesHandle");
        MediaSalesHandle expResult = new MediaSalesHandle();
        MediaSalesHandle result = BeansClass.mediaSalesHandle();
        assertNotNull("Checking that MediaSalesHandle object is not null",
                result);
        assertEquals("Should return MediaSalesHandle class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of saleIdGen method, of class BeansClass.
     */
    @Test
    public void testSaleIdGen() {
        System.out.println("saleIdGen");
        SaleIdGen expResult = new SaleIdGen();
        SaleIdGen result = BeansClass.saleIdGen();
        assertNotNull("Checking that SaleIdGen object is not null",
                result);
        assertEquals("Should return SaleIdGen class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of softwareSalesHandle method, of class BeansClass.
     */
    @Test
    public void testSoftwareSalesHandle() {
        System.out.println("softwareSalesHandle");
        SoftwareSalesHandle expResult = new SoftwareSalesHandle();
        SoftwareSalesHandle result = BeansClass.softwareSalesHandle();
        assertNotNull("Checking that SoftwareSalesHandle object is not null",
                result);
        assertEquals("Should return SoftwareSalesHandle class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of addSoftwareSaleFormController method, of class BeansClass.
     */
    @Test
    public void testAddSoftwareSaleFormController() {
        System.out.println("addSoftwareSaleFormController");
        AddSoftwareSaleFormController expResult = new AddSoftwareSaleFormController();
        AddSoftwareSaleFormController result = BeansClass.addSoftwareSaleFormController();
        assertNotNull("Checking that AddSoftwareSaleFormController object is not null",
                result);
        assertEquals("Should return AddSoftwareSaleFormController class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of profileManagerController method, of class BeansClass.
     */
    @Test
    public void testProfileManagerController() {
        System.out.println("profileManagerController");
        ProfileManagerController expResult = new ProfileManagerController();
        ProfileManagerController result = BeansClass.profileManagerController();
        assertNotNull("Checking that ProfileManagerController object is not null",
                result);
        assertEquals("Should return ProfileManagerController class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of customer method, of class BeansClass.
     */
    @Test
    public void testCustomer() {
        System.out.println("customer");
        Customer expResult = new Customer();
        Customer result = BeansClass.customer();
        assertNotNull("Checking that Customer object is not null",
                result);
        assertEquals("Should return Customer class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of handleCustomerLogic method, of class BeansClass.
     */
    @Test
    public void testHandleCustomerLogic() {
        System.out.println("handleCustomerLogic");
        HandleCustomerLogic expResult = new HandleCustomerLogic();
        HandleCustomerLogic result = BeansClass.handleCustomerLogic();
        assertNotNull("Checking that HandleCustomerLogic object is not null",
                result);
        assertEquals("Should return HandleCustomerLogic class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of customerHandle method, of class BeansClass.
     */
    @Test
    public void testCustomerHandle() {
        System.out.println("customerHandle");
        CustomerHandle expResult = new CustomerHandle();
        CustomerHandle result = BeansClass.customerHandle();
        assertNotNull("Checking that CustomerHandle object is not null",
                result);
        assertEquals("Should return CustomerHandle class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of customerManagerController method, of class BeansClass.
     */
    @Test
    public void testCustomerManagerController() {
        System.out.println("customerManagerController");
        CustomerManagerController expResult = new CustomerManagerController();
        CustomerManagerController result = BeansClass.customerManagerController();
        assertNotNull("Checking that CustomerManagerController object is not null",
                result);
        assertEquals("Should return CustomerManagerController class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of todo method, of class BeansClass.
     */
    @Test
    public void testTodo() {
        System.out.println("todo");
        Todo expResult = new Todo();
        Todo result = BeansClass.todo();
        assertNotNull("Checking that Todo object is not null",
                result);
        assertEquals("Should return Todo class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of todoHandle method, of class BeansClass.
     */
    @Test
    public void testTodoHandle() {
        System.out.println("todoHandle");
        TodoHandle expResult = new TodoHandle();
        TodoHandle result = BeansClass.todoHandle();
        assertNotNull("Checking that TodoHandle object is not null",
                result);
        assertEquals("Should return TodoHandle class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of handleTodoLogic method, of class BeansClass.
     */
    @Test
    public void testHandleTodoLogic() {
        System.out.println("handleTodoLogic");
        HandleTodoLogic expResult = new HandleTodoLogic();
        HandleTodoLogic result = BeansClass.handleTodoLogic();
        assertNotNull("Checking that HandleTodoLogic object is not null",
                result);
        assertEquals("Should return HandleTodoLogic class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of toDoController method, of class BeansClass.
     */
    @Test
    public void testToDoController() {
        System.out.println("toDoController");
        ToDoController expResult = new ToDoController();
        ToDoController result = BeansClass.toDoController();
        assertNotNull("Checking that ToDoController object is not null",
                result);
        assertEquals("Should return ToDoController class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of pieChartBuild method, of class BeansClass.
     */
    @Test
    public void testPieChartBuild() {
        System.out.println("pieChartBuild");
        PieChartBuild expResult = new PieChartBuild();
        PieChartBuild result = BeansClass.pieChartBuild();
        assertNotNull("Checking that PieChartBuild object is not null",
                result);
        assertEquals("Should return PieChartBuild class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of barChartBuild method, of class BeansClass.
     */
    @Test
    public void testBarChartBuild() {
        System.out.println("barChartBuild");
        BarChartBuild expResult = new BarChartBuild();
        BarChartBuild result = BeansClass.barChartBuild();
        assertNotNull("Checking that BarChartBuild object is not null",
                result);
        assertEquals("Should return BarChartBuild class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of lineChartBuild method, of class BeansClass.
     */
    @Test
    public void testLineChartBuild() {
        System.out.println("lineChartBuild");
        LineChartBuild expResult = new LineChartBuild();
        LineChartBuild result = BeansClass.lineChartBuild();
        assertNotNull("Checking that LineChartBuild object is not null",
                result);
        assertEquals("Should return LineChartBuild class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of mediaSalesReport method, of class BeansClass.
     */
    @Test
    public void testMediaSalesReport() {
        System.out.println("mediaSalesReport");
        MediaSalesReport expResult = new MediaSalesReport();
        MediaSalesReport result = BeansClass.mediaSalesReport();
        assertNotNull("Checking that MediaSalesReport object is not null",
                result);
        assertEquals("Should return MediaSalesReport class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of mediaSalesSubReport method, of class BeansClass.
     */
    @Test
    public void testMediaSalesSubReport() {
        System.out.println("mediaSalesSubReport");
        MediaSalesSubReport expResult = new MediaSalesSubReport();
        MediaSalesSubReport result = BeansClass.mediaSalesSubReport();
        assertNotNull("Checking that MediaSalesSubReport object is not null",
                result);
        assertEquals("Should return MediaSalesSubReport class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of softwareSalesReport method, of class BeansClass.
     */
    @Test
    public void testSoftwareSalesReport() {
        System.out.println("softwareSalesReport");
        SoftwareSalesReport expResult = new SoftwareSalesReport();
        SoftwareSalesReport result = BeansClass.softwareSalesReport();
        assertNotNull("Checking that SoftwareSalesReport object is not null",
                result);
        assertEquals("Should return SoftwareSalesReport class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of softwareSalesSubReport method, of class BeansClass.
     */
    @Test
    public void testSoftwareSalesSubReport() {
        System.out.println("softwareSalesSubReport");
        SoftwareSalesSubReport expResult = new SoftwareSalesSubReport();
        SoftwareSalesSubReport result = BeansClass.softwareSalesSubReport();
        assertNotNull("Checking that SoftwareSalesSubReport object is not null",
                result);
        assertEquals("Should return SoftwareSalesSubReport class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of logsReport method, of class BeansClass.
     */
    @Test
    public void testLogsReport() {
        System.out.println("logsReport");
        LogsReport expResult = new LogsReport();
        LogsReport result = BeansClass.logsReport();
        assertNotNull("Checking that LogsReport object is not null",
                result);
        assertEquals("Should return LogsReport class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of productReport method, of class BeansClass.
     */
    @Test
    public void testProductReport() {
        System.out.println("productReport");
        MediaProductSubReport expResult = new MediaProductSubReport();
        MediaProductSubReport result = BeansClass.mediaProductSubReport();
        assertNotNull("Checking that ProductReport object is not null",
                result);
        assertEquals("Should return ProductReport class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of preorder method, of class BeansClass.
     */
    @Test
    public void testPreorder() {
        System.out.println("preorder");
        Preorder expResult = new Preorder();
        Preorder result = BeansClass.preorder();
        assertNotNull("Checking that Preorder object is not null",
                result);
        assertEquals("Should return Preorder class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of preorderHandle method, of class BeansClass.
     */
    @Test
    public void testPreorderHandle() {
        System.out.println("preorderHandle");
        PreorderHandle expResult = new PreorderHandle();
        PreorderHandle result = BeansClass.preorderHandle();
        assertNotNull("Checking that PreorderHandle object is not null",
                result);
        assertEquals("Should return PreorderHandle class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of handlePreorderLogic method, of class BeansClass.
     */
    @Test
    public void testHandlePreorderLogic() {
        System.out.println("handlePreorderLogic");
        HandlePreorderLogic expResult = new HandlePreorderLogic();
        HandlePreorderLogic result = BeansClass.handlePreorderLogic();
        assertNotNull("Checking that HandlePreorderLogic object is not null",
                result);
        assertEquals("Should return HandlePreorderLogic class object",
                expResult.getClass(), result.getClass());
    }
    /**
     * Test of preorderSubReport method, of class BeansClass.
     */
    @Test
    public void testPreorderSubReport() {
        System.out.println("preorderSubReport");
        PreorderSubReport expResult = new PreorderSubReport();
        PreorderSubReport result = BeansClass.preorderSubReport();
        assertNotNull("Checking that PreorderSubReport object is not null",
                result);
        assertEquals("Should return PreorderSubReport class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of dialogPane method, of class BeansClass.
     */
    @Test
    public void testDialogPane() {
        System.out.println("dialogPane");
        DialogPane result = BeansClass.dialogPane();
        DialogPane expResult = new DialogPane();
        
        assertNotNull("Checking that DialogPane object is not null",result);
        assertEquals("Should return DialogPane class object",expResult.getClass(),result.getClass());
    }

    /**
     * Test of preorderReport method, of class BeansClass.
     */
    @Test
    public void testPreorderReport() {
        System.out.println("preorderReport");
        PreorderReport expResult = new PreorderReport();
        PreorderReport result = BeansClass.preorderReport();
        assertNotNull("Checking that PreorderReport object is not null",
                result);
        assertEquals("Should return PreorderReport class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of trayNotice method, of class BeansClass.
     */
    @Test
    public void testTrayNotice() {
        System.out.println("trayNotice");
        TrayNotice expResult = new TrayNotice();
        TrayNotice result = BeansClass.trayNotice();
        assertNotNull("Checking that TrayNotice object is not null",
                result);
        assertEquals("Should return TrayNotice class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of timer method, of class BeansClass.
     */
    @Test
    public void testTimer() {
        System.out.println("timer");
        Timer expResult = new Timer();
        Timer result = BeansClass.timer();
        assertNotNull("Checking that Timer object is not null",
                result);
        assertEquals("Should return Timer class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of mediaPurchase method, of class BeansClass.
     */
    @Test
    public void testMediaPurchase() {
        System.out.println("mediaPurchase");
        MediaPurchase expResult = new MediaPurchase();
        MediaPurchase result = BeansClass.mediaPurchase();
        assertNotNull("Checking that MediaPurchase object is not null",
                result);
        assertEquals("Should return MediaPurchase class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of mediaPurchaseHandle method, of class BeansClass.
     */
    @Test
    public void testMediaPurchaseHandle() {
        System.out.println("mediaPurchaseHandle");
        MediaPurchaseHandle expResult = new MediaPurchaseHandle();
        MediaPurchaseHandle result = BeansClass.mediaPurchaseHandle();
        assertNotNull("Checking that MediaPurchaseHandle object is not null",
                result);
        assertEquals("Should return MediaPurchaseHandle class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of handleMediaPurchaseLogic method, of class BeansClass.
     */
    @Test
    public void testHandleMediaPurchaseLogic() {
        System.out.println("handleMediaPurchaseLogic");
        HandleMediaPurchaseLogic expResult = new HandleMediaPurchaseLogic();
        HandleMediaPurchaseLogic result = BeansClass.handleMediaPurchaseLogic();
        assertNotNull("Checking that HandleMediaPurchaseLogic object is not null",
                result);
        assertEquals("Should return HandleMediaPurchaseLogic class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of mediaPurchasesSubReport method, of class BeansClass.
     */
    @Test
    public void testMediaPurchasesSubReport() {
        System.out.println("mediaPurchasesSubReport");
        MediaPurchasesSubReport expResult = new MediaPurchasesSubReport();
        MediaPurchasesSubReport result = BeansClass.mediaPurchasesSubReport();
        assertNotNull("Checking that MediaPurchasesSubReport object is not null",
                result);
        assertEquals("Should return MediaPurchasesSubReport class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of mediaPurchasesReport method, of class BeansClass.
     */
    @Test
    public void testMediaPurchasesReport() {
        System.out.println("mediaPurchasesReport");
        MediaPurchasesReport expResult = new MediaPurchasesReport();
        MediaPurchasesReport result = BeansClass.mediaPurchasesReport();
        assertNotNull("Checking that MediaPurchasesReport object is not null",
                result);
        assertEquals("Should return MediaPurchasesReport class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of softwarePurchase method, of class BeansClass.
     */
    @Test
    public void testSoftwarePurchase() {
        System.out.println("softwarePurchase");
        SoftwarePurchase expResult = new SoftwarePurchase();
        SoftwarePurchase result = BeansClass.softwarePurchase();
        assertNotNull("Checking that SoftwarePurchase object is not null",
                result);
        assertEquals("Should return SoftwarePurchase class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of softwarePurchaseHandle method, of class BeansClass.
     */
    @Test
    public void testSoftwarePurchaseHandle() {
        System.out.println("softwarePurchaseHandle");
        SoftwarePurchaseHandle expResult = new SoftwarePurchaseHandle();
        SoftwarePurchaseHandle result = BeansClass.softwarePurchaseHandle();
        assertNotNull("Checking that SoftwarePurchaseHandle object is not null",
                result);
        assertEquals("Should return SoftwarePurchaseHandle class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of handleSoftwarePurchaseLogic method, of class BeansClass.
     */
    @Test
    public void testHandleSoftwarePurchaseLogic() {
        System.out.println("handleSoftwarePurchaseLogic");
        HandleSoftwarePurchaseLogic expResult = new HandleSoftwarePurchaseLogic();
        HandleSoftwarePurchaseLogic result = BeansClass.handleSoftwarePurchaseLogic();
        assertNotNull("Checking that HandleSoftwarePurchaseLogic object is not null",
                result);
        assertEquals("Should return HandleSoftwarePurchaseLogic class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of softwarePurchasesSubReport method, of class BeansClass.
     */
    @Test
    public void testSoftwarePurchasesSubReport() {
        System.out.println("softwarePurchasesSubReport");
        SoftwarePurchasesSubReport expResult = new SoftwarePurchasesSubReport();
        SoftwarePurchasesSubReport result = BeansClass.softwarePurchasesSubReport();
        assertNotNull("Checking that SoftwarePurchasesSubReport object is not null",
                result);
        assertEquals("Should return SoftwarePurchasesSubReport class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of softwarePurchasesReport method, of class BeansClass.
     */
    @Test
    public void testSoftwarePurchasesReport() {
        System.out.println("softwarePurchasesReport");
        SoftwarePurchasesReport expResult = new SoftwarePurchasesReport();
        SoftwarePurchasesReport result = BeansClass.softwarePurchasesReport();
        assertNotNull("Checking that SoftwarePurchasesReport object is not null",
                result);
        assertEquals("Should return SoftwarePurchasesReport class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of mediaInvoice method, of class BeansClass.
     */
    @Test
    public void testMediaInvoice() {
        System.out.println("mediaInvoice");
        MediaInvoice expResult = new MediaInvoice();
        MediaInvoice result = BeansClass.mediaInvoice();
        assertNotNull("Checking that MediaInvoice object is not null",
                result);
        assertEquals("Should return MediaInvoice class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of softwareInvoice method, of class BeansClass.
     */
    @Test
    public void testSoftwareInvoice() {
        System.out.println("softwareInvoice");
        SoftwareInvoice expResult = new SoftwareInvoice();
        SoftwareInvoice result = BeansClass.softwareInvoice();
        assertNotNull("Checking that SoftwareInvoice object is not null",
                result);
        assertEquals("Should return SoftwareInvoice class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of pieChartBuilder method, of class BeansClass.
     */
    @Test
    public void testPieChartBuilder() {
        System.out.println("pieChartBuilder");
        PieChartBuilder expResult = new PieChartBuilder();
        PieChartBuilder result = BeansClass.pieChartBuilder();
        assertNotNull("Checking that PieChartBuilder object is not null",
                result);
        assertEquals("Should return PieChartBuilder class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of customerReport method, of class BeansClass.
     */
    @Test
    public void testCustomerReport() {
        System.out.println("customerReport");
        CustomerReport expResult = new CustomerReport();
        CustomerReport result = BeansClass.customerReport();
        assertNotNull("Checking that CustomerReport object is not null",
                result);
        assertEquals("Should return CustomerReport class object",
                expResult.getClass(), result.getClass());
    }

    /**
     * Test of roleBarChartBuilder method, of class BeansClass.
     */
    @Test
    public void testRoleBarChartBuilder() {
        System.out.println("roleBarChartBuilder");
        RoleBarchartBuilder expResult = new RoleBarchartBuilder();
        RoleBarchartBuilder result = BeansClass.roleBarChartBuilder();
        assertNotNull("Checking that RoleBarchartBuilder object is not null",
                result);
        assertEquals("Should return RoleBarchartBuilder class object",
                expResult.getClass(), result.getClass());
    }
    
}
