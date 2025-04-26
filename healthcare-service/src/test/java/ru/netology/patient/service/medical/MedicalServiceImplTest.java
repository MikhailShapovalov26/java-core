package ru.netology.patient.service.medical;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoFileRepository;
import ru.netology.patient.service.alert.SendAlertService;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;


public class MedicalServiceImplTest {

    private static final String PATIENT_ID = "test-id";
    private static final String PATIENT_LAST_NAME = "Иванов";
    private static final String PATIENT_FIRST_NAME = "Иван";
    private static final LocalDate PATIENT_BIRTH_DATE = LocalDate.of(2000, 11, 26);

    private PatientInfoFileRepository repository;
    private SendAlertService alertService;
    private MedicalService medicalService;

    @Before
    public void setUp() {
        repository = Mockito.mock(PatientInfoFileRepository.class);
        alertService = Mockito.mock(SendAlertService.class);

        // Создание тестируемого сервиса
        medicalService = new MedicalServiceImpl(repository, alertService);
    }

    private PatientInfo createTestPatient(BigDecimal normalTemp, BloodPressure pressure) {
        return new PatientInfo(
                PATIENT_ID,
                PATIENT_LAST_NAME,
                PATIENT_FIRST_NAME,
                PATIENT_BIRTH_DATE,
                new HealthInfo(normalTemp, pressure)
        );
    }

    @Test
    public void checkBloodPressure() {

        BloodPressure highPressure = new BloodPressure(200, 200);
        BloodPressure normalPressure = new BloodPressure(120, 80);

        PatientInfo patient = createTestPatient(new BigDecimal("36.6"), highPressure);

        Mockito.when(repository.getById(PATIENT_ID)).thenReturn(patient);

        medicalService.checkBloodPressure(PATIENT_ID, normalPressure);

        Mockito.verify(alertService).send(contains("Warning"));


    }

    @Test
    public void checkTemperature() {
        BigDecimal currentLowTemp = new BigDecimal("34.0");
        BigDecimal normalTemp = new BigDecimal("36.6");

        PatientInfo patient = createTestPatient(normalTemp, new BloodPressure(120, 80));

        Mockito.when(repository.getById(PATIENT_ID)).thenReturn(patient);

        medicalService.checkTemperature(PATIENT_ID, currentLowTemp);

        Mockito.verify(alertService).send(anyString());
    }
}