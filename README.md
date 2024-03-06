## Testing in Spring Boot: `spring-boot-starter-test` dependency

This dependency, which includes essential libraries like JUnit and Mockito, helps you reliably test your code.

👉 [For more information, you can check out my blog post.](https://ayseozcan.com/2024/02/28/spring-boot-test-junit5mockito/)

### Requirements
- IDE
- JDK 17+
- Gradle 7.5+

### Dependencies

```
    implementation 'org.springframework.boot:spring-boot-starter-validation:3.2.3'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    runtimeOnly 'org.postgresql:postgresql'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
```
### DrugService

```java
@Service
public class DrugService {

    private final DrugRepository drugRepository;

    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public Drug save(SaveDrugDto dto) {

        Drug drug = new Drug();
        drug.setDrugName(dto.drugName());
        drug.setCompanyName(dto.companyName());
        drug.setStock(dto.stock());

        return drugRepository.save(drug);
    }

    public Optional<Drug> findById(Long id) {
        return drugRepository.findById(id);
    }
}
```

### DrugServiceTest

```java
@ExtendWith(MockitoExtension.class)
@DisplayName("Drug service test class")
class DrugServiceTest {

    @Mock
    private DrugRepository drugRepository;

    @InjectMocks
    private DrugService drugService;

    @Test
    @DisplayName("Saving drug")
    void shouldSaveDrugSuccessfully() {

        // Given
        SaveDrugDto dto = new SaveDrugDto("Sodium Chloride", "Baxter Healthcare Corporation", 809);

        Drug savedDrug = new Drug();
        savedDrug.setId(1L);
        savedDrug.setDrugName(dto.drugName());
        savedDrug.setCompanyName(dto.companyName());
        savedDrug.setStock(dto.stock());

        // Mock the calls
        when(drugRepository.save(any(Drug.class))).thenReturn(savedDrug);

        // When
        Drug result = drugService.save(dto);

        // Then
        assertEquals(savedDrug.getId(), result.getId());
        assertEquals(savedDrug.getDrugName(), result.getDrugName());
        assertEquals(savedDrug.getCompanyName(), result.getCompanyName());
        assertEquals(savedDrug.getStock(), result.getStock());

        verify(drugRepository).save(any(Drug.class));
    }

    @Test
    @DisplayName("Find drug by id - Success")
    void shouldFindDrugById_Success() {

        // Given
        Long drugId = 1L;
        Drug mockDrug = new Drug();
        mockDrug.setId(drugId);
        mockDrug.setDrugName("Bacitracin Zinc");
        mockDrug.setCompanyName("Dynarex Corporation");
        mockDrug.setStock(704);

        // Mock the calls
        when(drugRepository.findById(drugId)).thenReturn(Optional.of(mockDrug));

        // When
        Optional<Drug> result = drugService.findById(drugId);

        // Then
        assertTrue(result.isPresent());
        assertEquals(mockDrug, result.get());

        verify(drugRepository).findById(drugId);
    }

    @Test
    @DisplayName("Find drug by id - Not Found")
    void shouldFindDrugById_NotFound() {

        // Given
        Long nonExistingDrugId = 2L;
        when(drugRepository.findById(nonExistingDrugId)).thenReturn(Optional.empty());

        // When
        Optional<Drug> result = drugService.findById(nonExistingDrugId);

        // Then
        assertEquals(Optional.empty(), result);

        verify(drugRepository).findById(nonExistingDrugId);
    }
}
```
### Getting started

Clone this project and run.

```
https://github.com/ayse-ozcan/spring-boot-junit-mockito.git
```
