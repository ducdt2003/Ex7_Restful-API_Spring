package com.example.Ex7_Restful.API_Spring;

import com.example.Ex7_Restful.API_Spring.entity.Category;
import com.example.Ex7_Restful.API_Spring.entity.Product;
import com.example.Ex7_Restful.API_Spring.repository.CategoryRepository;
import com.example.Ex7_Restful.API_Spring.repository.ProductRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Locale;
import java.util.Random;

@SpringBootTest
class Ex7RestfulApiSpringApplicationTests {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	private final Faker faker = new Faker(new Locale("vi"));
	private final Random random = new Random();

	@Test
//	@Order(1)
	void seedCategories() {

		if (categoryRepo.count() > 0) {
			System.out.println("Category đã tồn tại, bỏ qua!");
			return;
		}

		for (int i = 0; i < 5; i++) {
			Category c = new Category();
			c.setName(faker.commerce().department());
			categoryRepo.save(c);
		}

		System.out.println("Seed Category thành công!");
	}


	@Test
//	@Order(2)
	void seedProducts() {

		List<Category> categories = categoryRepo.findAll();

		if (categories.isEmpty()) {
			throw new RuntimeException("Chưa có category, chạy seedCategories trước!");
		}

		for (int i = 0; i < 20; i++) {
			Product p = new Product();

			p.setName(faker.commerce().productName());
			p.setPrice(Double.parseDouble(faker.commerce().price()));

			Category randomCategory = categories.get(random.nextInt(categories.size()));
			p.setCategory(randomCategory);

			productRepo.save(p);
		}

		System.out.println("Seed Product thành công!");
	}
}
