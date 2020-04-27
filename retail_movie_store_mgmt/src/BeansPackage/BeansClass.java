
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansPackage;

import java.util.Timer;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import retail_movie_store_mgmt.AddMediaItemsSaleController;
import retail_movie_store_mgmt.AddMediaSaleFormController;
import retail_movie_store_mgmt.AddMediaSaleLogic;
import retail_movie_store_mgmt.AddSoftwareSaleFormController;
import retail_movie_store_mgmt.AddSoftwareSaleLogic;
import retail_movie_store_mgmt.Customer.Customer;
import retail_movie_store_mgmt.CustomerManagerController;
import retail_movie_store_mgmt.DeleteMediaSaleFormController;
import retail_movie_store_mgmt.EnterProductController;
import retail_movie_store_mgmt.EnterSecurityQuestionController;
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
import retail_movie_store_mgmt.Logic.HandleReportLogic;
import retail_movie_store_mgmt.Logic.HandleSoftwarePurchaseLogic;
import retail_movie_store_mgmt.Logic.HandleTodoLogic;
import retail_movie_store_mgmt.Logic.HandleUsersLogic;
import retail_movie_store_mgmt.Logic.SoftwareLogic;
import retail_movie_store_mgmt.LoginPageController;
import retail_movie_store_mgmt.LogsLogic;
import retail_movie_store_mgmt.NewPasswordController;
import retail_movie_store_mgmt.PreorderManagerController;
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
import retail_movie_store_mgmt.report.ProductReport;
import retail_movie_store_mgmt.report.SoftwareProductSubReport;
import retail_movie_store_mgmt.report.SoftwarePurchasesReport;
import retail_movie_store_mgmt.report.SoftwarePurchasesSubReport;
import retail_movie_store_mgmt.report.SoftwareSalesReport;
import retail_movie_store_mgmt.report.SoftwareSalesSubReport;
import retail_movie_store_mgmt.security.Hashing;
import retail_movie_store_mgmt.todo.Todo;
import retail_movie_store_mgmt.ui.DialogPane;
import retail_movie_store_mgmt.ui.DonutChartBuilder;
import retail_movie_store_mgmt.ui.PieChartBuilder;
import retail_movie_store_mgmt.ui.RoleBarchartBuilder;
import retail_movie_store_mgmt.ui.SalesBarChartBuilder;
import retail_movie_store_mgmt.ui.TrayNotice;

/**
 *
 * @author Ian Mburu
 */
@Configuration
@ComponentScan(basePackages={"BeansPackage","retail_movie_store_mgmt","retail_movie_store_mgmt.database"})
public class BeansClass {
    
    public static BeansClass beansClass(){
        return new BeansClass();
    }
    
    @Bean
    public static Database_start_stop database_begin_end(){
        return new Database_start_stop();
    }
   
    //jdbc
    @Bean
    public static DataSource dataSource(){
        DriverManagerDataSource ds =  new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/retail_mgmt");
        ds.setUsername("root");
        ds.setPassword("");
        return ds;
    }
    
    @Bean
    public static JdbcTemplate jdbcTemplate(DataSource dataSource){ 
        return new JdbcTemplate(dataSource);
    }
    
    
    @Bean
    public static User user(){
        return new User();
    }
    
    @Bean
    public static SecurityQueryCheck security_query_check(){
        return new SecurityQueryCheck();
    }
    
    @Bean
    public static SecurityQuestionsHandle securityQuestionsHandle(){
        return new SecurityQuestionsHandle();
    }
    
    @Bean
    public static HandleQuestionsLogic handleQuestionsLogic(){
        return new HandleQuestionsLogic();
    }
    
    
    public static EnterSecurityQuestionController enterSecurityQuestionController(){
        return new EnterSecurityQuestionController();
    }
    
    @Bean
    public static HandlePasswordLogic handlePasswordLogic(){
        return new HandlePasswordLogic();
    }
    
    @Bean
    public static PasswordsHandle passwordsHandle(){
        return new PasswordsHandle();
    }
    
    @Bean
    public static UsersHandle usersHandle(){
        return new UsersHandle();
    }
    
    @Bean
    public static HandleUsersLogic handleUsersLogic(){
        return new HandleUsersLogic();
    }
    
    @Bean
    public static Hashing hashing(){
        return new Hashing();
    }
    
    @Bean
    public static LoginPageController loginPageController(){
        return new LoginPageController();
    }
    
    @Bean
    public static HandleMainLogic handleMainLogic(){
        return new HandleMainLogic();
    }
    
    @Bean
    public static HandleFxmlPages handleFxmlPages(){
        return new HandleFxmlPages();
    }
    
    @Bean
    public static NewPasswordController newPasswordController(){
        return new NewPasswordController();
    }
    
    @Bean
    public static DateTime dateTime(){
        return new DateTime();
    }
    
    @Bean
    public static Logs logs(){
        return new Logs(); 
    }
    
    @Bean
    public static LogsLogic logsLogic(){
        return new LogsLogic(); 
    }
    
    @Bean
    public static ForgotPasswordController forgotPasswordController(){
        return new ForgotPasswordController();
    }
    
    @Bean
    public static ProductHandle productHandle(){
        return new ProductHandle();
    }
    
        
    public static EnterProductController enterProductController(){
        return new EnterProductController();
    }
    
    @Bean
    public static HandleProductLogic handleProductLogic(){
        return new HandleProductLogic();
    }
    
    @Bean
    public static HandleProfileLogic handleProfileLogic(){
        return new HandleProfileLogic();
    }
    
    @Bean
    public static Product product(){
        return new Product("");
    }

    @Bean
    public static Product product(String title){
        return new Product(title);
    }
    
    @Bean
    public static SoftwareLogic softwareLogic(){
        return new SoftwareLogic();
    }
    
    @Bean
    public static SoftwareHandle softwareHandle(){
        return new SoftwareHandle();
    }
    
//    @Bean
    public static Software software(String title){
        return new Software(title);
    }
    
    @Bean
    public static SoftwareInventoryController softwareInventoryController(){
        return new SoftwareInventoryController();
    }
    
    public static HomeLogic homeLogic(){
        return new HomeLogic();
    }
    
    public static HomeController homeController(){
        return new HomeController();
    }
    @Bean
    public static Calculator calculator(){
        return new Calculator();
    }
    
    @Bean
    public static AddMediaSaleLogic addMediaSaleLogic(){
        return new AddMediaSaleLogic();
    }
    
    @Bean
    public static AddSoftwareSaleLogic addSoftwareSaleLogic(){
        return new AddSoftwareSaleLogic();
    }
    
    @Bean
    public static AddMediaSaleFormController addMediaSaleFormController(){
        return new AddMediaSaleFormController();
    }
    
    @Bean
    public static DeleteMediaSaleFormController deleteMediaSaleFormController(){
        return new DeleteMediaSaleFormController();
    }
    
    @Bean
    public static AddMediaItemsSaleController addMediaSaleController(){
        return new AddMediaItemsSaleController();
    }
    
    @Bean
    public static SaleEntry sale(){
        return new SaleEntry();
    }
    
    @Bean
    public static MediaAndCustomSaleEntry mediaAndCustomSaleEntry(){
        return new MediaAndCustomSaleEntry();
    }

    public static AddMediaItemsSaleController addMediaItemsSaleController() {
        return new AddMediaItemsSaleController();
    }
    
    
    public static SoftwareSaleEntry softwareSaleEntry() {
        return new SoftwareSaleEntry();
    }
    
    public static MediaSalesHandle mediaSalesHandle() {
        return new MediaSalesHandle();
    }

    public static SaleIdGen saleIdGen(){
        return new SaleIdGen();
    }
    
    public static SoftwareSalesHandle softwareSalesHandle(){
        return new SoftwareSalesHandle();
    }
    
    public static AddSoftwareSaleFormController addSoftwareSaleFormController(){
        return new AddSoftwareSaleFormController();
    }
    
    public static ProfileManagerController profileManagerController(){
        return new ProfileManagerController();
    }
    
    public static Customer customer(){
        return new Customer();
    }
    
    public static HandleCustomerLogic handleCustomerLogic(){
        return new HandleCustomerLogic();
    }
    
    public static CustomerHandle customerHandle(){
        return new CustomerHandle();
    }
    
    public static CustomerManagerController customerManagerController(){
        return new CustomerManagerController();
    }
    
    public static Todo todo(){
        return new Todo();
    }
    
    public static TodoHandle todoHandle(){
        return new TodoHandle();
    }
    
    public static HandleTodoLogic handleTodoLogic(){
        return new HandleTodoLogic();
    }
    
    public static ToDoController toDoController(){
        return new ToDoController();
    }
    
    public static PieChartBuild pieChartBuild(){
        return new PieChartBuild();
    }
    
    public static BarChartBuild barChartBuild(){
        return new BarChartBuild();
    }
    
    public static LineChartBuild lineChartBuild(){
        return new LineChartBuild();
    }
    
    public static HandleReportLogic handleReportLogic(){
        return new HandleReportLogic();
    }
    
    public static MediaSalesReport mediaSalesReport(){
        return new MediaSalesReport();
    }
    
    public static MediaSalesSubReport mediaSalesSubReport(){
        return new MediaSalesSubReport();
    }
    
    public static SoftwareSalesReport softwareSalesReport(){
        return new SoftwareSalesReport();
    }
    
    public static SoftwareSalesSubReport softwareSalesSubReport(){
        return new SoftwareSalesSubReport();
    }
    
    public static LogsReport logsReport(){
        return new LogsReport();
    }
    
    public static MediaProductSubReport mediaProductSubReport(){
        return new MediaProductSubReport();
    }
    
    public static SoftwareProductSubReport softwareProductSubReport(){
        return new SoftwareProductSubReport();
    }
    
    public static ProductReport productReport(){
        return new ProductReport();
    }
    
    public static Preorder preorder(){
        return new Preorder();
    }
    
    public static PreorderHandle preorderHandle(){
        return new PreorderHandle();
    }
    
    public static HandlePreorderLogic handlePreorderLogic(){
        return new HandlePreorderLogic();
    }
    
    public static PreorderManagerController preorderManagerController(){
        return new PreorderManagerController();
    }
    
    public static PreorderSubReport preorderSubReport(){
       return new PreorderSubReport();
    }
    
    public static DialogPane dialogPane(){
        return new DialogPane();
    }
    
    public static PreorderReport preorderReport(){
        return new PreorderReport();
    }
    
    public static TrayNotice trayNotice(){
        return new TrayNotice();
    }
    
    public static Timer timer(){
        return new Timer();
    }
    
    public static MediaPurchase mediaPurchase(){
        return new MediaPurchase();
    }
    
    public static MediaPurchaseHandle mediaPurchaseHandle(){
        return new MediaPurchaseHandle();
    }
    
    public static HandleMediaPurchaseLogic handleMediaPurchaseLogic(){
        return new HandleMediaPurchaseLogic();
    }
    
    public static MediaPurchasesSubReport mediaPurchasesSubReport(){
        return new MediaPurchasesSubReport();
    }
    
    public static MediaPurchasesReport mediaPurchasesReport(){
        return new MediaPurchasesReport();
    }
    
    public static SoftwarePurchase softwarePurchase(){
        return new SoftwarePurchase();
    }
    
    public static SoftwarePurchaseHandle softwarePurchaseHandle(){
        return new SoftwarePurchaseHandle();
    }
    
    public static HandleSoftwarePurchaseLogic handleSoftwarePurchaseLogic(){
        return new HandleSoftwarePurchaseLogic();
    }
    
    public static SoftwarePurchasesSubReport softwarePurchasesSubReport(){
        return new SoftwarePurchasesSubReport();
    }
    
    public static SoftwarePurchasesReport softwarePurchasesReport(){
        return new SoftwarePurchasesReport();
    }
    
    public static MediaInvoice mediaInvoice(){
        return new MediaInvoice();
    }
    
    public static SoftwareInvoice softwareInvoice(){
        return new SoftwareInvoice();
    }
    
    public static PieChartBuilder pieChartBuilder(){
        return new PieChartBuilder();
    }
    
    public static DonutChartBuilder donutChartBuilder(ObservableList<PieChart.Data> pieData){
        return new DonutChartBuilder(pieData);
    }
    
    public static CustomerReport customerReport(){
        return new CustomerReport();
    }
    
    public static RoleBarchartBuilder roleBarChartBuilder(){
        return new RoleBarchartBuilder();
    }
    
    public static SalesBarChartBuilder salesBarChartBuilder() {
        return new SalesBarChartBuilder();
    }
    
}
