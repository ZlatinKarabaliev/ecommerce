package com.ecommerce.server.demo;

import com.ecommerce.server.demo.dto.ProductDto;
import com.ecommerce.server.demo.model.trg.Product;
import com.ecommerce.server.demo.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class DemoApplicationTests
{
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository productRepository;

	@PersistenceContext
	EntityManager em;

	@AfterEach
	private void flushData()
	{
		em.flush();
	}

	@Test
	public void saveProductTest() throws Exception
	{
		String name = "Laptop Lenovo";

		saveProduct(name);

		Product productEntity = productRepository.findProductByName(name).orElse(null);

		assertThat(Objects.requireNonNull(productEntity).getName()).isEqualTo(name);
	}

	private ProductDto saveProduct(String name) throws Exception
	{
		ProductDto product = new ProductDto();
		product.setName(name);
		product.setQuantity(155);
		product.setCreatedDate(LocalDate.now());
		ResultActions resultActions = mockMvc.perform(post("/product", product)
				.flashAttr("user", product)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isCreated());

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		return objectMapper.readValue(contentAsString, ProductDto.class);
	}

	@Test
	public void deleteProductTest()
			throws Exception
	{
		String name = "Laptop Lenovo";

		ProductDto savedProduct = saveProduct(name);

		mockMvc.perform(delete("/product/delete/{id}", savedProduct.getId())
				.contentType("application/json"));

		Optional<ProductDto> productAfterDelete = Optional.ofNullable(getProductById(savedProduct.getId()));

		assert (productAfterDelete.get().getId() == null);
	}

	private ProductDto getProductById(long id) throws Exception
	{
		ResultActions resultActions = mockMvc.perform(get("/product/{id}", id)
				.contentType("application/json"));

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		if (!contentAsString.isEmpty())
		{
			return objectMapper.readValue(contentAsString, ProductDto.class);
		} else
		{
			return null;
		}
	}

	@Autowired
	private ProductRepository repository;

	private Product product1;
	private Product product2;
	private Product product3;


	@Before
	public void init()
	{

		product1 = new Product();
		product1.setName("Lenovo");
		product1.setQuantity(5);
		product1.setDescription("john@doe.com");
		product1.setCategory("some category");
		repository.save(product1);

		product2 = new Product();
		product2.setName("Lenovo");
		product2.setQuantity(5555);
		product2.setDescription("lenovo@doe.com");
		product2.setCategory("No category ibm");
		repository.save(product2);

		product3 = new Product();
		product3.setName("Ibm");
		product3.setQuantity(15225);
		product3.setDescription("ibm@doe.com");
		product3.setCategory("some categ");
		repository.save(product3);
	}


}