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

    private final IDrugRepository drugRepository;

    public DrugService(IDrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public Drug save(SaveDrugDto dto) {

        Drug drug = new Drug();
        drug.setDrugName(dto.getDrugName());
        drug.setCompanyName(dto.getCompanyName());
        drug.setStock(dto.getStock());

        return drugRepository.save(drug);
    }

    public Optional<Drug> findById(Long id) {
        Optional<Drug> drug = drugRepository.findById(id);
        if (drug.isPresent()) {
            return drug;
        }
        throw new RuntimeException("Drug not found");
    }
}
```

### DrugServiceTest

```java
@ExtendWith(MockitoExtension.class)
@DisplayName("Drug service test class")
public class DrugServiceTest {

    @Mock
    private IDrugRepository drugRepository;

    @InjectMocks
    private DrugService drugService;

    @Test
    @DisplayName("Saving drug")
    public void testSaveDrug() {

        // Given
        SaveDrugDto dto = new SaveDrugDto();
        dto.setDrugName("Sodium Chloride");
        dto.setCompanyName("Baxter Healthcare Corporation");
        dto.setStock(809);

        Drug savedDrug = new Drug();
        savedDrug.setId(1L);
        savedDrug.setDrugName(dto.getDrugName());
        savedDrug.setCompanyName(dto.getCompanyName());
        savedDrug.setStock(dto.getStock());

        // Mock the calls
        when(drugRepository.save(any(Drug.class))).thenReturn(savedDrug);

        // When
        Drug result = drugService.save(dto);

        // Then
        assertEquals(savedDrug.getId(), result.getId());
        assertEquals(savedDrug.getDrugName(), result.getDrugName());
        assertEquals(savedDrug.getCompanyName(), result.getCompanyName());
        assertEquals(savedDrug.getStock(), result.getStock());

        verify(drugRepository, times(1)).save(any(Drug.class));
    }

    @Test
    @DisplayName("Find drug by id - Success")
    public void testFindDrugById() {

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

        verify(drugRepository, times(1)).findById(drugId);
    }

    @Test
    @DisplayName("Find drug by id - Not Found")
    public void testFindDrugById_NotFound() {

        // Given
        Long nonExistingDrugId = 2L;
        when(drugRepository.findById(nonExistingDrugId)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> drugService.findById(nonExistingDrugId));
        assertEquals("Drug not found", exception.getMessage());

        verify(drugRepository, times(1)).findById(nonExistingDrugId);
    }
}
```
### Getting started

Clone this project and run.

```
https://github.com/ayse-ozcan/spring-boot-junit-mockito.git
```
