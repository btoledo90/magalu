package communication.magalu;

import communication.magalu.dto.MagaluDto;
import communication.magalu.entity.Magalu;
import communication.magalu.repository.MagaluRepository;
import communication.magalu.service.MagaluService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class MagaluServiceTest {

    @Mock
    private MagaluRepository magaluRepository;

    @InjectMocks
    private MagaluService magaluService;

    private MagaluDto testDto;
    private Magalu testMagalu;

    @BeforeEach
    void setUp() {
        testDto = new MagaluDto();
        testDto.setRecipient("Test Recipient");
        testDto.setMessage("Test Message");
        testDto.setType("Test Type");

        testMagalu = new Magalu();
        testMagalu.setId(1L);
        testMagalu.setScheduledTime(LocalDateTime.now());
        testMagalu.setRecipient("Test Recipient");
        testMagalu.setMessage("Test Message");
        testMagalu.setType("Test Type");
        testMagalu.setStatus("SCHEDULED");
    }

    @Test
    void scheduleMagaluShouldReturnMagaluDtoWhenDtoIsValid() {
        when(magaluRepository.save(any(Magalu.class))).thenReturn(testMagalu);

        MagaluDto result = magaluService.scheduleMagalu(testDto);

        assertNotNull(result);
        assertEquals(testDto.getRecipient(), result.getRecipient());
        assertEquals(testDto.getMessage(), result.getMessage());
        assertEquals(testDto.getType(), result.getType());
    }

    @Test
    void getMagaluShouldReturnMagaluDtoWhenIdExists() {
        when(magaluRepository.findById(anyLong())).thenReturn(Optional.of(testMagalu));

        MagaluDto result = magaluService.getMagalu(1L);

        assertNotNull(result);
        assertEquals(testMagalu.getRecipient(), result.getRecipient());
        assertEquals(testMagalu.getMessage(), result.getMessage());
        assertEquals(testMagalu.getType(), result.getType());
    }

    @Test
    void getAllMagaluShouldReturnListOfMagaluDtoWhenMagalusExist() {
        when(magaluRepository.findAll()).thenReturn(Collections.singletonList(testMagalu));

        List<MagaluDto> result = magaluService.getAllMagalu();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(testMagalu.getRecipient(), result.get(0).getRecipient());
        assertEquals(testMagalu.getMessage(), result.get(0).getMessage());
        assertEquals(testMagalu.getType(), result.get(0).getType());
    }

    @Test
    void getAllMagaluShouldReturnEmptyListWhenNoMagalusExist() {
        when(magaluRepository.findAll()).thenReturn(Collections.emptyList());

        List<MagaluDto> result = magaluService.getAllMagalu();

        assertTrue(result.isEmpty());
    }

    @Test
    void deleteMagaluShouldDeleteMagaluWhenIdExists() {
        when(magaluRepository.existsById(anyLong())).thenReturn(true);

        magaluService.deleteMagalu(1L);

        verify(magaluRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteMagaluShouldThrowIllegalArgumentExceptionWhenIdDoesNotExist() {
        when(magaluRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> magaluService.deleteMagalu(1L));
    }
}
