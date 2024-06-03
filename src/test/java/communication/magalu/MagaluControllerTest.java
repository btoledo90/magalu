package communication.magalu;

import communication.magalu.controller.MagaluController;
import communication.magalu.dto.MagaluDto;
import communication.magalu.entity.Magalu;
import communication.magalu.service.MagaluService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MagaluControllerTest {

    @Mock
    private MagaluService magaluService;

    @InjectMocks
    private MagaluController magaluController;

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
        testMagalu.setRecipient("Test Recipient");
        testMagalu.setMessage("Test Message");
        testMagalu.setType("Test Type");
    }

    @Test
    void scheduleMagaluShouldReturnCreatedStatusCodeWhenDtoIsValid() {
        when(magaluService.scheduleMagalu(any(MagaluDto.class))).thenReturn(testDto);

        ResponseEntity<MagaluDto> response = magaluController.scheduleMagalu(testDto);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testDto, response.getBody());
    }

    @Test
    void getMagaluShouldReturnMagaluDtoWhenIdExists() {
        when(magaluService.getMagalu(anyLong())).thenReturn(testDto);

        ResponseEntity<MagaluDto> response = magaluController.getMagalu(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testDto, response.getBody());
    }

    @Test
    void getAllMagaluShouldReturnListOfMagaluDtoWhenMagalusExist() {
        when(magaluService.getAllMagalu()).thenReturn(Collections.singletonList(testDto));

        ResponseEntity<List<MagaluDto>> response = magaluController.getAllMagalu();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(testDto, response.getBody().get(0));
    }

    @Test
    void getAllMagaluShouldReturnEmptyListWhenNoMagalusExist() {
        when(magaluService.getAllMagalu()).thenReturn(Collections.emptyList());

        ResponseEntity<List<MagaluDto>> response = magaluController.getAllMagalu();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void deleteMagaluShouldReturnNoContentStatusCodeWhenIdExists() {
        doNothing().when(magaluService).deleteMagalu(anyLong());

        ResponseEntity<Void> response = magaluController.deleteMagalu(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }


}
