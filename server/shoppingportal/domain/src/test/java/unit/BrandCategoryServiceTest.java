package unit;

import com.ecommerce.domain.exception.BrandCategoriesNotFound;
import com.ecommerce.domain.model.category.BrandCategoriesDomainResponse;
import com.ecommerce.domain.model.category.BrandCategory;
import com.ecommerce.domain.model.category.BrandCategoryImage;
import com.ecommerce.domain.model.category.GenderCategory;
import com.ecommerce.domain.port.adapters.repositories.BrandCategoryImageRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.BrandCategoryRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.GenderCategoryRepositoryPort;
import com.ecommerce.domain.port.drivers.BrandCategoryDriverPort;
import com.ecommerce.domain.service.BrandCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BrandCategoryServiceTest {

    private BrandCategoryDriverPort brandCategoryService;

    @Mock
    private BrandCategoryRepositoryPort brandCategoryRepository;
    @Mock
    private BrandCategoryImageRepositoryPort brandCategoryImageRepository;
    @Mock
    private GenderCategoryRepositoryPort genderCategoryRepository;

    @BeforeEach
    void setup() {
        brandCategoryService = new BrandCategoryService(brandCategoryRepository, brandCategoryImageRepository,genderCategoryRepository);
    }

    @Test
    void givenGenderIdAndApparelCategoryId_whenGetBrandCategories_thenSuccess() {
        int genderId = 1;
        int apparelCategoryId = 2;
        GenderCategory genderCategory = new GenderCategory();
        genderCategory.setId(genderId);
        genderCategory.setName("Men");
        BrandCategory brandCategory = new BrandCategory();
        brandCategory.setId(1);
        List<BrandCategory> brandCategories = List.of(brandCategory);

        when(genderCategoryRepository.findById(genderId)).thenReturn(genderCategory);
        when(brandCategoryRepository.findByGenderCategoryIdAndApparelCategoryIdAndProductsIsNotNull(genderId, apparelCategoryId))
                .thenReturn(brandCategories);
        when(brandCategoryImageRepository.findByBrandCategoryIdAndGenderCategoryId(brandCategory.getId(), genderId))
                .thenReturn(new BrandCategoryImage());

        BrandCategoriesDomainResponse response = brandCategoryService.getBrandCategoriesByGenderIdAndApparelCategoryId(genderId, apparelCategoryId);

        assertNotNull(response);
        assertEquals(genderCategory.getName(), response.getGender());
        assertEquals(brandCategories.size(), response.getBrandCategories().size());
    }

    @Test
    void givenGenderIdAndApparelCategoryId_whenNoBrandCategories_thenThrowException() {
        int genderId = 1;
        int apparelCategoryId = 2;
        GenderCategory genderCategory = new GenderCategory();
        genderCategory.setName("Men");

        when(genderCategoryRepository.findById(genderId)).thenReturn(genderCategory);
        when(brandCategoryRepository.findByGenderCategoryIdAndApparelCategoryIdAndProductsIsNotNull(genderId, apparelCategoryId))
                .thenReturn(Collections.emptyList());

        assertThrows(BrandCategoriesNotFound.class, () -> {
            brandCategoryService.getBrandCategoriesByGenderIdAndApparelCategoryId(genderId, apparelCategoryId);
        });
    }
}
