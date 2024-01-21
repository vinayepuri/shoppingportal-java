package unit;

import com.ecommerce.domain.model.product.Product;
import com.ecommerce.domain.model.product.ProductsDomainResponse;
import com.ecommerce.domain.port.adapters.repositories.ApparelCategoryRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.BrandCategoryRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.GenderCategoryRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.ProductRepositoryPort;
import com.ecommerce.domain.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {


    @Mock
    private ProductRepositoryPort productRepository;
    @Mock
    private GenderCategoryRepositoryPort genderCategoryRepository;
    @Mock
    private ApparelCategoryRepositoryPort apparelCategoryRepository;
    @Mock
    private BrandCategoryRepositoryPort brandCategoryRepository;


    private ProductService productService;

    @BeforeEach
    public void setUp() {
       productService = new ProductService(productRepository,genderCategoryRepository, apparelCategoryRepository, brandCategoryRepository);
    }

    @Test
    public void whenGetProductByValidId_thenProductIsReturned() {
        Long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(product);

        Product foundProduct = productService.getProductById(productId);

        assertEquals(product, foundProduct);
        verify(productRepository).findById(productId);
    }


    @Test
    public void whenGetProductsWithValidParams_thenProductsAreReturned() {
        int gender = 1;
        List<Integer> categories = Arrays.asList(1, 2);
        List<Integer> brands = Arrays.asList(3, 4);
        int page = 0;
        int size = 10;
        String[] sort = {"name", "asc"};
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        List<Product> productList = Arrays.asList(new Product(), new Product());
        Page<Product> productPage = new PageImpl<>(productList, pageable, productList.size());

        when(productRepository.findByGenderCategoryIdAndAndBrandCategoryIdInApparelCategoryIdIn(gender, brands, categories, pageable)).thenReturn(productPage);

        ProductsDomainResponse response = productService.getProducts(gender, brands, categories, page, size, sort);

        assertFalse(response.getProducts().isEmpty());
        assertEquals(productList.size(), response.getProducts().size());
        assertEquals(page, response.getCurrentPage());
        verify(productRepository).findByGenderCategoryIdAndAndBrandCategoryIdInApparelCategoryIdIn(gender, brands, categories, pageable);
    }


}
