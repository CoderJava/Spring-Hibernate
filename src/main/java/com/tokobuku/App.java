package com.tokobuku;

import com.tokobuku.bo.CustomerBo;
import com.tokobuku.model.Customer;
import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.function.Consumer;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/config/Main.xml");

        CustomerBo customerBo = (CustomerBo) context.getBean("customerBo");

        customerBo.insert(new Customer(1, "Nicholas Freeman", "06 Thackeray Lane", "nfreeman@mac.com"));
        customerBo.insert(new Customer(2, "Yudi Setiawan", "Jalan Klambir 5 no.65 Medan", "kolonel.yudisetiawan@gmail.com"));
        customerBo.insert(new Customer(3, "Arie Dwi Nugraha", "Jalan Setia Budi", "aridwinugraha@gmail.com"));
        customerBo.insert(new Customer(4, "Dian Ika Wahyuni", "Jalan Gaperta Ujung", "dianikawahyuni@gmail.com"));

        List<Customer> customers = customerBo.findAllCustomers();
        System.out.println("data awal: " + customers);

        Customer customer = customerBo.findByCustomerId(1);
        customer.setFullName("User edit");
        customerBo.update(customer);
        customers = customerBo.findAllCustomers();
        System.out.println("data edit: " + customers);

        customerBo.delete(1);
        customers = customerBo.findAllCustomers();
        System.out.println("data hapus: " + customers);

        customerBo.batch(new Customer(5, "user unknown", "N/A", "na@gmail.com"));
        customers = customerBo.findAllCustomers();
        System.out.println("data batch: " + customers);
    }
}
