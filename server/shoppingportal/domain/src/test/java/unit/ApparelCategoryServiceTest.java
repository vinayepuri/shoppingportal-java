package unit;

import com.ecommerce.domain.exception.ApparelCategoriesNotFound;
import com.ecommerce.domain.model.category.ApparelCategoriesDomainResponse;
import com.ecommerce.domain.model.category.ApparelCategory;
import com.ecommerce.domain.model.category.GenderCategory;
import com.ecommerce.domain.port.adapters.repositories.ApparelCategoryRepositoryPort;
import com.ecommerce.domain.port.adapters.repositories.GenderCategoryRepositoryPort;
import com.ecommerce.domain.port.drivers.ApparelCategoryDriverPort;
import com.ecommerce.domain.service.ApparelCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApparelCategoryServiceTest {

    private ApparelCategoryDriverPort apparelCategoryService;

    @Mock
    private ApparelCategoryRepositoryPort apparelCategoryRepository;

    @Mock
    private GenderCategoryRepositoryPort genderCategoryRepository;


    @BeforeEach
    void setup() {
        apparelCategoryService = new ApparelCategoryService(apparelCategoryRepository, genderCategoryRepository);
    }

    @Test
    void givenBrandAndGender_whenGetApparelCategories_thenSuccess() {
        int genderId = 1;
        int brandId = 1;
        GenderCategory genderCategory = new GenderCategory();
        genderCategory.setName("Men");
        List<ApparelCategory> apparelCategories = List.of(new ApparelCategory(), new ApparelCategory());

        when(genderCategoryRepository.findById(genderId)).thenReturn(genderCategory);
        when(apparelCategoryRepository.findByBrandCategoryIdAndGenderCategoryIdAndProductsIsNotNull(brandId, genderId))
                .thenReturn(apparelCategories);

        ApparelCategoriesDomainResponse response = apparelCategoryService.getApparelCategoriesByGenderIdAndBranCategoryId(genderId, brandId);

        assertNotNull(response);
        assertEquals(genderCategory.getName(), response.getGender());
        assertEquals(apparelCategories.size(), response.getApparelCategories().size());
    }

    @Test
    void givenBrandAndGender_whenNoApparelCategories_thenThrowException() {
        int genderId = 1;
        int brandId = 1;
        GenderCategory genderCategory = new GenderCategory();

        when(genderCategoryRepository.findById(genderId)).thenReturn(genderCategory);
        when(apparelCategoryRepository.findByBrandCategoryIdAndGenderCategoryIdAndProductsIsNotNull(brandId, genderId))
                .thenReturn(Collections.emptyList());

        assertThrows(ApparelCategoriesNotFound.class, () -> {
            apparelCategoryService.getApparelCategoriesByGenderIdAndBranCategoryId(genderId, brandId);
        });
    }

    @Test
    void givenGenderAndBrandZero_whenGetApparelCategories_thenSuccess() {
        int genderId = 1;
        int brandId = 0;
        GenderCategory genderCategory = new GenderCategory();
        List<ApparelCategory> apparelCategories = List.of(new ApparelCategory(), new ApparelCategory());

        when(genderCategoryRepository.findById(genderId)).thenReturn(genderCategory);
        when(apparelCategoryRepository.findByGenderCategoryIdAndProductsIsNotNull(genderId))
                .thenReturn(apparelCategories);

        ApparelCategoriesDomainResponse response = apparelCategoryService.getApparelCategoriesByGenderIdAndBranCategoryId(genderId, brandId);

        assertNotNull(response);
        assertEquals(genderCategory.getName(), response.getGender());
        assertEquals(apparelCategories.size(), response.getApparelCategories().size());
    }


}

