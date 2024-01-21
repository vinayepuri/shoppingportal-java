package unit;

import com.ecommerce.domain.model.country.Country;
import com.ecommerce.domain.port.adapters.repositories.CountryRepositoryPort;
import com.ecommerce.domain.port.drivers.CountryDriverPort;
import com.ecommerce.domain.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class CountryServiceTest {

    private CountryDriverPort countryService;

    @Mock
    private CountryRepositoryPort countryRepository;

    @BeforeEach
    void setUp(){
        countryService = new CountryService(countryRepository);
    }

    @Test
    public void testFetchCountries() {
        List<Country> expectedCountries = Arrays.asList(new Country(1, "USA", "US"), new Country(2, "Canada", "CA"));
        when(countryRepository.findAll()).thenReturn(expectedCountries);

        List<Country> actualCountries = countryService.fetchCountries();

        assertEquals(expectedCountries, actualCountries, "The fetched countries should match the expected list");
        verify(countryRepository).findAll();
    }
}
