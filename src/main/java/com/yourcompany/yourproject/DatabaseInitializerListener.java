package com.yourcompany.yourproject;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dbutils.DBUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebListener
public class DatabaseInitializerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// Database initialization code
		try {
			initializeDatabase();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// Cleanup code if needed
	}

	private void initializeDatabase() throws URISyntaxException {
		/*
		 * String dbName = "main_project";
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * URI jdbUri = new URI(System.getenv(
		 * "mysql://q4gj8bafulof74zc:n2ltxi0cusi30fhl@fojvtycq53b2f2kx.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/ld20zgpxb95sjt1g"
		 * )); String user = jdbUri.getUserInfo().split(":")[0]; String password =
		 * jdbUri.getUserInfo().split(":")[1]; String port =
		 * String.valueOf(jdbUri.getPort()); String jdbcUrl = "jdbc:mysql://" +
		 * jdbUri.getHost() + ":" + port + jdbUri.getPath();
		 * 
		 * 
		 * try (Connection connection = DBUtils.getConnection(); Statement stmt =
		 * connection.createStatement()) {
		 * 
		 * // Check if the schema exists ResultSet rs =
		 * stmt.executeQuery("SHOW SCHEMAS LIKE '" + dbName + "'"); if (!rs.next()) { //
		 * Schema does not exist, proceed with creation and initialization
		 * 
		 * // Create schema stmt.execute("CREATE SCHEMA `" + dbName +
		 * "` DEFAULT CHARACTER SET utf8");
		 * 
		 * // Use the schema stmt.execute("USE `" + dbName + "`");
		 * 
		 * // Create tables stmt.execute("CREATE TABLE IF NOT EXISTS `users` (" +
		 * "`user_id` VARCHAR(50) NOT NULL, " +
		 * "`user_first_name` VARCHAR(45) NOT NULL, " +
		 * "`user_last_name` VARCHAR(45) NOT NULL, " +
		 * "`user_password` VARCHAR(45) NOT NULL, " +
		 * "`user_mobile` BIGINT UNSIGNED NOT NULL, " +
		 * "`user_address` VARCHAR(255) NOT NULL, " + "PRIMARY KEY (`user_id`)) " +
		 * "ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb3");
		 * 
		 * stmt.execute("CREATE TABLE IF NOT EXISTS `customer` (" +
		 * "`cus_id` VARCHAR(40) NOT NULL, " + "`cus_first_name` VARCHAR(45) NOT NULL, "
		 * + "`cus_last_name` VARCHAR(45) NOT NULL, " +
		 * "`cus_password` VARCHAR(45) NOT NULL, " +
		 * "`cus_mobile` BIGINT UNSIGNED NOT NULL, " +
		 * "`cus_email` VARCHAR(45) NOT NULL, " +
		 * "`cus_address` VARCHAR(255) NOT NULL, " +
		 * "`Users_user_id` VARCHAR(50) NOT NULL, " +
		 * "PRIMARY KEY (`cus_id`, `Users_user_id`), " +
		 * "CONSTRAINT `fk_Customer_Users1` FOREIGN KEY (`Users_user_id`) " +
		 * "REFERENCES `users` (`user_id`)) " +
		 * "ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb3");
		 * 
		 * stmt.execute("CREATE TABLE IF NOT EXISTS `roles` (" +
		 * "`role_id` VARCHAR(40) NOT NULL, " + "`role_name` VARCHAR(45) NOT NULL, " +
		 * "`role_description` VARCHAR(255) NOT NULL, " + "PRIMARY KEY (`role_id`)) " +
		 * "ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb3");
		 * 
		 * stmt.execute("CREATE TABLE IF NOT EXISTS `login` (" +
		 * "`login_id` VARCHAR(45) NOT NULL, " +
		 * "`login_role_id` VARCHAR(45) NOT NULL, " +
		 * "`login_first_name` VARCHAR(45) NOT NULL, " +
		 * "`login_last_name` VARCHAR(45) NOT NULL, " +
		 * "`login_password` VARCHAR(45) NOT NULL, " + "PRIMARY KEY (`login_id`), " +
		 * "CONSTRAINT `login_role_id` FOREIGN KEY (`login_role_id`) " +
		 * "REFERENCES `roles` (`role_id`)) " +
		 * "ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb3");
		 * 
		 * stmt.execute("CREATE TABLE IF NOT EXISTS `permission` (" +
		 * "`per_id` VARCHAR(40) NOT NULL, " + "`per_module` VARCHAR(45) NOT NULL, " +
		 * "`per_name` VARCHAR(45) NOT NULL, " +
		 * "`Roles_role_id` VARCHAR(40) NOT NULL, " + "PRIMARY KEY (`per_id`), " +
		 * "CONSTRAINT `fk_Permission_Roles1` FOREIGN KEY (`Roles_role_id`) " +
		 * "REFERENCES `roles` (`role_id`)) " +
		 * "ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb3");
		 * 
		 * stmt.execute("CREATE TABLE IF NOT EXISTS `product` (" +
		 * "`pro_id` VARCHAR(40) NOT NULL, " +
		 * "`pro_type` VARCHAR(45) NULL DEFAULT NULL, " +
		 * "`pro_item_name` VARCHAR(45) NULL DEFAULT NULL, " +
		 * "`pro_quantity` INT NULL DEFAULT NULL, " +
		 * "`pro_description` VARCHAR(255) NULL DEFAULT NULL, " +
		 * "`Users_user_id` VARCHAR(50) NOT NULL, " + "PRIMARY KEY (`pro_id`), " +
		 * "CONSTRAINT `fk_product_Users1` FOREIGN KEY (`Users_user_id`) " +
		 * "REFERENCES `users` (`user_id`)) " +
		 * "ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb3");
		 * 
		 * stmt.execute("CREATE TABLE IF NOT EXISTS `schedule` (" +
		 * "`sch_id` VARCHAR(55) NOT NULL, " +
		 * "`sch_type` VARCHAR(45) NULL DEFAULT NULL, " +
		 * "`sch_description` VARCHAR(255) NULL DEFAULT NULL, " +
		 * "PRIMARY KEY (`sch_id`)) " + "ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb3");
		 * 
		 * stmt.execute("CREATE TABLE IF NOT EXISTS `salesman` (" +
		 * "`slmn_id` VARCHAR(40) NOT NULL, " +
		 * "`slmn_first_name` VARCHAR(45) NOT NULL, " +
		 * "`slmn_last_name` VARCHAR(45) NOT NULL, " +
		 * "`slmn_password` VARCHAR(45) NOT NULL, " +
		 * "`slmn_mobile` BIGINT UNSIGNED NOT NULL, " +
		 * "`slmn_address` VARCHAR(255) NOT NULL, " +
		 * "`Schedule_sch_id` VARCHAR(55) NOT NULL, " +
		 * "`slmn_email` VARCHAR(45) NOT NULL, " +
		 * "`Users_user_id` VARCHAR(50) NOT NULL, " +
		 * "PRIMARY KEY (`slmn_id`, `Users_user_id`), " +
		 * "CONSTRAINT `fk_Salesman_Schedule1` FOREIGN KEY (`Schedule_sch_id`) " +
		 * "REFERENCES `schedule` (`sch_id`), " +
		 * "CONSTRAINT `fk_Salesman_Users1` FOREIGN KEY (`Users_user_id`) " +
		 * "REFERENCES `users` (`user_id`)) " +
		 * "ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb3");
		 * 
		 * // Insert sample data stmt.
		 * execute("INSERT INTO `users` (`user_id`, `user_first_name`, `user_last_name`, `user_password`, `user_mobile`, `user_address`) VALUES "
		 * +
		 * "('u1111111111', 'sumit', 'kaushik', 'Sumit@123', 7417929862, 'house number 6 bagla enclave sikandra bodla road agra'), "
		 * +
		 * "('u1111111112', 'pritam', 'sharma', 'Sumit@124', 6395923974, 'house number 7 bagla enclave sikandra bodla road agra'), "
		 * +
		 * "('u1111111113', 'akash', 'pandey', 'Sumit@125', 9456617331, 'house number 8 bagla enclave sikandra bodla road agra'), "
		 * +
		 * "('u1111111114', 'devesh', 'singh', 'Sumit@126', 9457874139, 'house number 9 bagla enclave sikandra bodla road agra')"
		 * );
		 * 
		 * stmt.
		 * execute("INSERT INTO `roles` (`role_id`, `role_name`, `role_description`) VALUES "
		 * + "('r1111111111', 'admin', 'all access'), " +
		 * "('r1111111112', 'manager', 'manage salesman details and check customer details'), "
		 * + "('r1111111113', 'engineer', 'manage only product details'), " +
		 * "('r1111111114', 'CEO', 'has access to see all details')");
		 * 
		 * stmt.
		 * execute("INSERT INTO `permission` (`per_id`, `per_module`, `per_name`, `Roles_role_id`) VALUES "
		 * + "('p1111111111', 'customer', 'create', 'r1111111111'), " +
		 * "('p1111111112', 'customer', 'read', 'r1111111111'), " +
		 * "('p1111111113', 'customer', 'update', 'r1111111111'), " +
		 * "('p1111111114', 'customer', 'delete', 'r1111111111'), " +
		 * "('p1111111115', 'salesman', 'create', 'r1111111111'), " +
		 * "('p1111111116', 'salesman', 'read', 'r1111111111'), " +
		 * "('p1111111117', 'salesman', 'update', 'r1111111111'), " +
		 * "('p1111111118', 'salesman', 'delete', 'r1111111111'), " +
		 * "('p1111111119', 'product', 'create', 'r1111111111'), " +
		 * "('p1111111120', 'product', 'read', 'r1111111111'), " +
		 * "('p1111111121', 'product', 'update', 'r1111111111'), " +
		 * "('p1111111122', 'product', 'delete', 'r1111111111')");
		 * 
		 * 
		 * 
		 * stmt.
		 * execute("INSERT INTO `schedule` (`sch_id`, `sch_type`, `sch_description`) VALUES "
		 * + "('s1111111111', 'Morning', 'morning shift from 9am to 5 pm'), " +
		 * "('s1111111112', 'Night', 'night shift from 8pm  to 5 am')");
		 * 
		 * 
		 * 
		 * } else { // Schema exists, no need to recreate
		 * System.out.println("Schema already exists, skipping initialization."); } }
		 * catch (SQLException e) { e.printStackTrace(); // You might want to use a
		 * logger instead }
		 */
	}
}
